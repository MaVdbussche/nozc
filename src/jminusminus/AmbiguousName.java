package jminusminus;

import com.barassolutions.AST;
import com.barassolutions.Context;
import com.barassolutions.Definition;
import java.util.StringTokenizer;

/**
 * Ambiguous names are meant to deal with snippets like
 *
 * <pre>
 *   x.y.z
 *   a.b.c()
 * </pre>
 *
 * Clearly, z is a field and c is a method. But what about x.y and a.b ? x could
 * be a package name and y a type, making for a (static) class field selection.
 * Or, x could be a local variable and y an instance field. The parser cannot
 * know how to parse these.
 *
 * Disambiguating the ambiguity must wait until analysis time. The parser can,
 * with x.y.z, treat the .z as a field selection, but constructs an
 * AmbiguousName object encapsulating the x.y . And it can, with a.b.c(), treat
 * the .c() as a message expression, but constructs an AmbiguousName object
 * encapsulating a.b.
 *
 * reclassify() is called upon in FieldSelection.analyze() and
 * MessageExpression.analyze() to reclassify the components and construct the
 * proper AST, following the rules for names. In Oz, both x.y and a.b are clearly
 * expressions in these contexts. If inner types were to be introduced, their
 * meaning and their reclassification would necessarily be more complicated.
 */ //TODO rewrite this
public class AmbiguousName {

  /**
   * Line in which the ambiguous name occurs in the source file.
   */
  private int line;

  /** The ambiguous part, eg x.y */
  private String name;

  /**
   * Construct an encapsulation of the ambiguous portion of a snippet like
   * x.y.z.
   *
   * @param line
   *            line in which the ambiguous name occurs in the source file.
   * @param name
   *            the ambiguous part.
   */
  public AmbiguousName(int line, String name) {
    this.line = line;
    this.name = name;
  }

  /**
   * Reclassify the name according to the rules in the Java Language
   * Specification.
   *
   * @param context
   *            context in which we look up the component names.
   * @return the properly parsed AST.
   */ //TODO rewrite this
  public Expression reclassify(Context context) {
    // Easier because we require all types to be imported.
    Expression result = null;
    StringTokenizer st = new StringTokenizer(name, ".");

    // Firstly, find a variable or Type.
    String newName = st.nextToken();
    Definition iDefn = null;

    do {
      iDefn = context.lookup(newName);
      if (iDefn != null) {
        result = new Variable(line, newName);
        break;
      } else if (!st.hasMoreTokens()) {
        // Nothing found. :(
        AST.compilationBlock.reportSemanticError(line,
            "Cannot find name " + newName);
        return null;
      } else {
        newName += "." + st.nextToken();
      }
    } while (true);

    // For now we can assume everything else is fields.
    while (st.hasMoreTokens()) {
      result = new FieldSelection(line, result, st.nextToken());
    }
    return result;
  }
}
