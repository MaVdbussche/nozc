package jminusminus;

import com.barassolutions.AST;
import com.barassolutions.Context;

/**
 * Any reference that can be denoted as a (possibly qualified) identifier.
 */

public class TypeName {

    /**
     * The line in which the identifier occurs in the source file.
     */
    private final int line;

    /**
     * The identifier's name IN THE CURRENT FILE.
     */
    private final String name;
    /**
     * The identifier's absolute name in Mozart
     */
    private final String internalName;

    /**
     * Construct an TypeName given its line number, and string spelling out its
     * fully qualified name.
     *
     * @param line the line in which the identifier occurs in the source file.
     * @param name fully qualified name for the identifier.
     */
    public TypeName(int line, String name) {
        this.line = line;
        this.name = name;
    }

    /**
     * Return the line in which the identifier occurs in the source file.
     *
     * @return the line number.
     */
    public int line() {
        return line;
    }

    /**
     * Return the JVM name for this (identifier) type.
     *
     * @return the JVM name.
     */
    public String jvmName() {
        return name.replace('.', '/');
    }

    /**
     * Return the JVM descriptor for this type.
     *
     * @return the descriptor.
     */

    public String toDescriptor() {
        return "L" + jvmName() + ";";
    }

    /**
     * Return the Java representation of this type. Eg, java.lang.String.
     *
     * @return the qualified name.
     */

    public String toString() {
        return name;
    }

    /**
     * Return the simple name for this type. Eg, String for java.lang.String.
     *
     * @return simple name.
     */

    public String simpleName() {
        return name.substring(name.lastIndexOf('.') + 1);
    }

    /**
     * Resolve this type in the given context. Notice that this has meaning only
     * for TypeName and ArrayTypeName, where names are replaced by real types.
     * Names are looked up in the context.
     *
     * @param context context in which the names are resolved.
     * @return the resolved type.
     */

    public Type resolve(Context context) {
        Type resolvedType = context.lookupType(name);
        if (resolvedType == null) {
            // Try loading a type with the give fullname
            try {
                resolvedType = typeFor(Class.forName(name));
                context.addType(line, resolvedType);
                // context.compilationUnitContext().addEntry(line,
                // resolvedType.toString(),
                // new TypeNameDefn(resolvedType));
            } catch (Exception e) {
                AST.compilationBlock.reportSemanticError(line,
                        "Unable to locate a type named %s", name);
                resolvedType = Type.ANY;
            }
        }
        if (resolvedType != Type.ANY) {
            Type referencingType = ((TypeDecl) (context.classContext
                    .definition())).thisType();
            Type.checkAccess(line, referencingType.classRep(), resolvedType
                    .classRep());
        }
        return resolvedType;
    }
}
