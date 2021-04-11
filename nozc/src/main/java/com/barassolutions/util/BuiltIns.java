package com.barassolutions.util;

import com.barassolutions.ClassDescriptor;
import com.barassolutions.MethodDef;
import com.barassolutions.Pattern;
import com.barassolutions.Variable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;

//TODO all implicit functions/procs in Oz (Browse etc.)
// TODO http://mozart2.org/mozart-v1/doc-1.4.0/base/node7.html has been partially skipped, to discuss

/**
 * An enum containing all the functions, procedures, classes and functors known by the compiler.
 * They can be considered as built into the language itself, meaning they are available to be
 * referenced from any scope in your NewOz program.
 */
public enum BuiltIns {

  /**
   * <code>abs(+fi1)</code>
   * <p>
   * Returns the absolute value of <code>fi1</code>.
   */
  abs("Abs", "abs", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "fi1", true, false)),
      null, null),
  /**
   * <code>abs(+fi1, ?fi2)</code>
   * <p>
   * Procedure version of <code>abs(fi1)</code>.
   */
  absP("Abs", "abs", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "fi1", true, false),
          new Variable(-1, "fi2", true, false)),
      null, null),
  /**
   * <code>acos(+f1)</code>
   * <p>
   * Returns the arc cosine of <code>f1</code>.
   */
  acos("Acos", "acos", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>acos(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>acos(f1)</code>.
   */
  acosP("Acos", "acos", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>acosh(+f1)</code>
   * <p>
   * Returns the inverse hyperbolic cosine of <code>f1</code>.
   */
  acosh("Float.acosh", "acosh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>acosh(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>acosh(f1)</code>.
   */
  acoshP("Float.acosh", "acosh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>adjoin(+r1, +r2)</code>
   * <p>
   * Returns the result of adjoining <code>r2</code> to <code>r1</code>. Note that features and
   * label in <code>r2</code> take precedence over <code>r1</code>.
   * <p>
   * For example, adjoin('a(a, b, c:1), 'b(4, b:3, c:2)) yields the record 'b(4, b, b:3, c:2) as
   * output..
   */
  adjoin("Adjoin", "adjoin", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>adjoin(+r1, +r2, ?r3)</code>
   * <p>
   * Procedure version of <code>adjoin(r1, r2)</code>.
   */
  adjoinP("Adjoin", "adjoin", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "r2", true, false),
          new Variable(-1, "r3", true, false)),
      null, null),
  /**
   * <code>adjoinAt(+r1, +li, x)</code>
   * <p>
   * Returns the result of adjoining the field <code>x</code> to <code>r1</code> at feature
   * <code>li</code>.
   * <p>
   * For example, adjoinAt('a(a, c:1), 2, b) yields the record 'a(a, b, c:1) as output, whereas
   * adjoinAt('a(a, c:1), c, b) yields 'a(a, c:b) as output.
   */
  adjoinAt("AdjoinAt", "adjoinAt", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>adjoinAt(+r1, +li, x, ?r2)</code>
   * <p>
   * Procedure version of <code>adjoinAt(r1, li, x)</code>.
   */
  adjoinAtP("AdjoinAt", "adjoinAt", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>adjoinlist(+r1, +ts)</code>
   * <p>
   * Returns the result of adjoining to <code>r1</code> all entries of <code>ts</code>, a finite
   * list of pairs whose first components are literals or integers, representing features. Features
   * further to the right overwrite features further to the left.
   * <p>
   * For example, adjoinList('a(b:1, c:2), [(d#3), (c#4), (d#5)]) yields 'a(b:1, c:4, d:5) as
   * output.
   */
  adjoinList("AdjoinList", "adjoinList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "ts", true, false)),
      null, null),
  /**
   * <code>adjoinList(+r1, +ts, ?r2)</code>
   * <p>
   * Procedure version of <code>adjoinList(r1, ts)</code>.
   */
  adjoinListP("AdjoinList", "adjoinList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "ts", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>all(+r, +f)</code>
   * <p>
   * Tests whether the unary boolean function <code>f</code> yields <code>code</code> when applied
   * to all fields of <code>r</code>. Stops at the first field for which <code>f</code> yields
   * <code>false</code>. The fields are tested in the order given by <code>arity()</code> (which
   * see).
   */
  all("Record.all", "all", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>all(+r, +f, ?b)</code>
   * <p>
   * Procedure version of <code>all(r, f)</code>.
   */
  allP("Record.all", "all", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>and(+b1, +b2)</code>
   * <p>
   * Returns the conjunction of truth value <code>b1</code> and <code>b2</code>. Note that
   * <code>and()</code> is different from the operation available via the keyword
   * <code>&amp;&amp;</code> in
   * that it always evaluates its second argument. For instance, <code>false &amp;&amp; p</code>
   * reduces without reducing application of <code>p</code>, whereas reduction of <code>and(false,
   * p)</code> always applies <code>p</code>.
   */
  and("And", "and", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "b1", true, false),
          new Variable(-1, "b2", true, false)),
      null, null),
  /**
   * <code>and(+b1, +b2, ?b3)</code>
   * <p>
   * Procedure version of <code>and(b1, b2)</code>.
   */
  andP("And", "and", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "b1", true, false),
          new Variable(-1, "b2", true, false),
          new Variable(-1, "b3", true, false)),
      null, null),
  /**
   * <code>appendList(+xs, y)</code>
   * <p>
   * Returns the result of appending <code>y</code> to <code>xs</code>. <code>Y</code> does not need
   * to be a list. However, <code>zs</code> is only a proper list, if <code>y</code> is also a
   * proper list.
   * <p>
   * For example, <code>appendList((1::2::nil), (3::4::nil))</code> returns the list
   * <code>(1::2::3::4::nil)</code>, whereas <code>appendList((1::2::nil), (3::4))</code> returns
   * <code>(1::2::(3::4))</code>, which is not a proper list (because (3::4) is not a proper list
   * and by the recursive definition of lists).
   */
  appendList("List.append", "appendList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>appendList(+xs, y, ?zs)</code>
   * <p>
   * Procedure version of <code>appendList(xs, y)</code>.
   */
  appendListP("List.append", "appendList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "y", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>appendTuple(+t1, +t2)</code>
   * <p>
   * Returns a tuple <code>t3</code> with the same label as <code>t2</code>. Given that
   * <code>t1</code> has width <i>i</i> and <code>t2</code> has width <i>j</i>, <code>t3</code>
   * will have width <i>i+j</i>, and the first <i>i</i> fields of <code>t3</code> will be the same
   * as the fields of <code>t1</code> in their original order, and the fields <i>i+1</i> through
   * <i>i+j</i> will be the same as the fields of <code>t2</code> in their original order.
   */
  appendTuple("Tuple.append", "appendTuple", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "t1", true, false),
          new Variable(-1, "t2", true, false)),
      null, null),
  /**
   * <code>appendTuple(+t1, +t2, ?t3)</code>
   * <p>
   * Procedure version of <code>appendTuple(t1, t2)</code>.
   */
  appendTupleP("Tuple.append", "appendTuple", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "t1", true, false),
          new Variable(-1, "t2", true, false),
          new Variable(-1, "t3", true, false)),
      null, null),
  /**
   * <code>arity(+r)</code>
   * <p>
   * Returns the arity of <code>r</code>. The arity of <code>r</code> is the list of its features,
   * beginning with all integer features in ascending order, followed by the literal features.
   * <p>
   * For example, arity('a(nil, 7, c:1, b:c)) yields [1, 2, b, c] as output.
   */
  arity("Arity", "arity", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>arity(+r, ?lis)</code>
   * <p>
   * Procedure version of <code>arity(r)</code>.
   */
  arityP("Arity", "arity", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "lis", true, false)),
      null, null),
  /**
   * <code>asin(+f1)</code>
   * <p>
   * Returns the arc sine of <code>f1</code>.
   */
  asin("Asin", "asin", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>asin(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>asin(f1)</code>.
   */
  asinP("Asin", "asin", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>asinh(+f1)</code>
   * <p>
   * Returns the inverse hyperbolic sine of <code>f1</code>.
   */
  asinh("Float.asinh", "asinh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>asinh(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>asinh(f1)</code>.
   */
  asinhP("Float.asinh", "asinh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>atan(+f1)</code>
   * <p>
   * Returns the arc tangent of <code>f1</code>.
   */
  atan("Atan", "atan", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>atan(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>atan(f1)</code>.
   */
  atanP("Atan", "atan", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>atanh(+f1)</code>
   * <p>
   * Returns the inverse hyperbolic tangent of <code>f1</code>.
   */
  atanh("Float.atanh", "atanh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>atanh(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>atanh(f1)</code>.
   */
  atanhP("Float.atanh", "atanh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>atan2(+f1, +f2)</code>
   * <p>
   * Returns the principal value of the arc tangent of <code>f1</code> <i>/</i> <code>f2</code>,
   * using the signs of both arguments to determine the quadrant of the return value. An error
   * exception may (but needs not) be raised if both arguments are <i>0</i>.
   */
  atan2("Atan2", "atan2", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>atan2(+f1, +f2, ?f3)</code>
   * <p>
   * Procedure version of <code>atan2(f1, f2)</code>.
   */
  atan2P("Atan2", "atan2", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false),
          new Variable(-1, "f3", true, false)),
      null, null),
  /**
   * <code>browse(X)</code>
   * <p> TODO
   * Displays <code>X</code> in the Mozart Browser
   */
  BROWSE("Browse", "browse", BuiltInType.PROCEDURE,
      Collections.singletonList(new Variable(-1, "ToPrint", true, false)),
      null, null),
  /**
   * <code>ceil(+f1)</code>
   * <p>
   * Returns the ceiling of <code>f1</code> (rounding towards positive infinity).
   */
  ceil("Ceil", "ceil", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>ceil(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>ceil(f1)</code>.
   */
  ceilP("Ceil", "ceil", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>clone(+r1)</code>
   * <p>
   * Returns a new record with the same label (name) and features as <code>r1</code> and fresh
   * variables at every field.
   */
  clone("Record.clone", "clone", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "r1", true, false)),
      null, null),
  /**
   * <code>clone(+r1, ?r2)</code>
   * <p>
   * Procedure version of <code>clone(r1)</code>.
   */
  cloneP("Record.clone", "clone", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>condSelect(+rc, +li, x)</code>
   * <p>
   * Returns the field of record <code>rc</code> at feature <code>li</code>, if <code>rc</code> has
   * feature <code>li</code>. Otherwise, return <code>x</code>.
   */
  condSelect("CondSelect", "condSelect", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "rc", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>condSelect(+rc, +li, x, ?y)</code>
   * <p>
   * Procedure version of <code>condSelect(rc, li, x)</code>.
   */
  condSelectP("HasFeature", "hasFeature", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "rc", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>cos(+f1)</code>
   * <p>
   * Returns the cosine of <code>f1</code>.
   */
  cos("Cos", "cos", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>cos(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>cos(f1)</code>.
   */
  cosP("Cos", "cos", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>cosh(+f1)</code>
   * <p>
   * Returns the hyperbolic cosine of <code>f1</code>.
   */
  cosh("Float.cosh", "cosh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>cosh(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>cosh(f1)</code>.
   */
  coshP("Float.cosh", "cosh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>drop(+xs, +i)</code>
   * <p>
   * Returns the list <code>xs</code> with the first <code>i</code> elements removed, os <code>nil</code> if it is shorter.
   */
  drop("List.drop", "drop", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>drop(+xs, +i, ?ys)</code>
   * <p>
   * Procedure version of <code>drop(xs, i)</code>.
   */
  dropP("List.drop", "drop", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>dropWhile(+r1, +f)</code>
   * <p>
   * See takeDropWhile()
   */
  dropWhile("Record.dropWhile", "dropWhile", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>dropWhile(+r1, +f, ?r2)</code>
   * <p>
   * See takeDropWhile()
   */
  dropWhileP("Record.dropWhile", "dropWhile", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>exp(+f1)</code>
   * <p>
   * Returns <code>f1</code> to the power of <i>e</i>.
   */
  exp("Exp", "exp", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>exp(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>exp(f1)</code>.
   */
  expP("Exp", "exp", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>filter(+r, +f)</code>
   * <p>
   * Returns the record which contains all the features and fields of the record <code>r</code>, for
   * which the unary boolean function <code>f</code> applied to the fields yields
   * <code>true</code>.
   */
  filter("Record.filter", "filter", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>filter(+r1, +f, ?r2)</code>
   * <p>
   * Procedure version of <code>filter(r1, f)</code>.
   */
  filterP("Record.filter", "filter", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>flatten(+xs)</code>
   * <p>
   * Returns the list obtained by flattening <code>xs</code>, i.e. by concatenating all sub-lists
   * of
   * <code>xs</code> recursively.
   */
  flatten("Flatten", "flatten", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>flatten(+xs, ?ys)</code>
   * <p>
   * Procedure version of <code>flatten(xs)</code>.
   */
  flattenP("Flatten", "flatten", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>floatToInt(+f)</code>
   * <p>
   * Returns the integer closest to float <code>f</code>. If there are two candidates,
   * <code>f</code> is rounded to the closest even integer, e. g., <code>floatToInt(1.5)</code> and
   * <code>floatToInt(2.5)</code> both return <i>2</i>.
   * <p><b><i>Attention</i></b></p>
   * In the current Oz implementation, the value is converted through a signed 32-bit integer, so
   * this function is bogus when applied to large floats.
   */
  floatToInt("FloatToInt", "floatToInt", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>floatToInt(+f, ?i)</code>
   * <p>
   * Procedure version of <code>floatToInt(f)</code>.
   */
  floatToIntP("FloatToInt", "floatToInt", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>floatToString(+f)</code>
   * <p>
   * Returns the string describing the float <code>f</code> in Oz concrete syntax.
   */
  floatToString("FloatToString", "floatToString", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>floatToString(+f, ?s)</code>
   * <p>
   * Procedure version of <code>floatToString(f)</code>.
   */
  floatToStringP("FloatToString", "floatToString", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f", true, false),
          new Variable(-1, "s", true, false)),
      null, null),
  /**
   * <code>floor(+f1)</code>
   * <p>
   * Returns the floor of <code>f1</code> (rounding towards negative infinity).
   */
  floor("Floor", "floor", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>floor(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>floor(f1)</code>.
   */
  floorP("Floor", "floor", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>foldL(+r, +f, x)</code>
   * <p>
   * Used for folding the fields of <code>r</code> by applying the binary function
   * <code>f</code>.
   * <p>
   * Supposing that <code>r</code> has the arity [F1, ... Fn], applying the left folding function
   * <code>foldL(r, f, x)</code> reduces to <code>f(...f(f(x, r.F1), r.F2), r.Fn)</code>. The first
   * actual argument of <code>f</code> is the accumulator in which the result of the previous
   * application, or the start value of <code>x</code>, is passed. The second actual argument is the
   * field of <code>r</code>.
   * <p>
   * Besides the left folding function there exists a right folding variant. The application
   * <code>foldR(r, f, x)</code> reduces to
   * <code>f(r.F1, f(r.F2, ...f(r.Fn, x)))</code>. The first actual argument of <code>f</code> is a
   * field of <code>r</code>; the second actual argument is the accumulator in which the result of
   * the previous application or the start value <code>x</code> is passed.
   * <p>
   * For example,
   * <code>foldL('a(3, a:7, b:4), fun $(xr, x){(x::xr)}, nil)</code> yields the output <code>[4, 7,
   * 3]</code>, whereas <code>foldR('a(3, a:7, b:4), fun $(x, xr){(x::xr)}, nil)</code> yields the
   * output <code>[3, 7, 4]</code>.
   */
  foldL("Record.foldL", "foldL", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldL(+r, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldL(r, f, x)</code>.
   */
  foldLP("Record.foldL", "foldL", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>foldR(+r, +f, x)</code>
   * <p>
   * See foldL().
   */
  foldR("Record.foldR", "foldR", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldR(+r, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldR(r, f, x)</code>.
   */
  foldRP("Record.foldR", "foldR", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>forAll(+r, +p)</code>
   * <p>
   * Applies the unary procedure <code>p</code> to each field of <code>r</code>. Suppose that the
   * arity of <code>r</code> is [F1, ... Fn]. The application <code>forAll(r, p)</code> reduces to
   * the sequence of statements <code>p(r.F1) ... p(r.Fn)</code>.
   */
  forAll("Record.forAll", "forAll", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "p", true, false)),
      null, null),
  /**
   * <code>hasFeature(+rc, +li)</code>
   * <p>
   * Tests whether the record <code>rc</code> has feature <code>li</code>.
   */
  hasFeature("HasFeature", "hasFeature", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "rc", true, false),
          new Variable(-1, "li", true, false)),
      null, null),
  /**
   * <code>hasFeature(+rc, +li, ?b)</code>
   * <p>
   * Procedure version of <code>hasFeature(rc, li)</code>.
   */
  hasFeatureP("HasFeature", "hasFeature", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "rc", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>intToFloat(+i)</code>
   * <p>
   * Returns the float closest to the integer <code>i</code>.
   */
  intToFloat("IntToFloat", "intToFloat", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>intToFloat(+i, ?f)</code>
   * <p>
   * Procedure version of <code>intToFloat(i)</code>.
   */
  intToFloatP("IntToFloat", "intToFloat", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>intToString(+i)</code>
   * <p>
   * Returns the string describing the integer <code>i</code> in Oz concrete syntax.
   */
  intToString("IntToString", "intToString", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>intToString(+i, ?s)</code>
   * <p>
   * Procedure version of <code>intToString(i)</code>.
   */
  intToStringP("IntToString", "intToString", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "s", true, false)),
      null, null),
  /**
   * <code>isBool(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a boolean.
   */
  isBool("IsBool", "isBool", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isBool(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isBool(x)</code>.
   */
  isBoolP("IsBool", "isBool", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isDet(x)</code>
   * <p>
   * Tests whether <code>x</code> is determined.
   */
  isDet("IsDet", "isDet", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isDet(x, ?b)</code>
   * <p>
   * Procedure version of <code>isDet(x)</code>.
   */
  isDetP("IsDet", "isDet", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isEven(+i)</code>
   * <p>
   * Tests whether <code>i</code> is an even integer.
   */
  isEven("IsEven", "isEven", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>isEven(+i, ?b)</code>
   * <p>
   * Procedure version of <code>isEven(i)</code>.
   */
  isEvenP("IsEven", "isEven", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isFailed(x)</code>
   * <p>
   * Tests whether <code>x</code> is a failed value.
   */
  isFailed("IsFailed", "isFailed", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isFailed(x, ?b)</code>
   * <p>
   * Procedure version of <code>isFailed(x)</code>.
   */
  isFailedP("IsFailed", "isFailed", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isFloat(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a float.
   */
  isFloat("IsFloat", "isFloat", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isFloat(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isFloat(x)</code>.
   */
  isFloatP("IsFloat", "isFloat", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isFree(x)</code>
   * <p>
   * Tests whether <code>x</code> is currently free.
   */
  isFree("IsFree", "isFree", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>isFree(x, ?b)</code>
   * <p>
   * Procedure version of <code>isFree(x)</code>.
   */
  isFreeP("IsFree", "isFree", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)
      ), null, null),
  /**
   * <code>isFuture(x)</code>
   * <p>
   * Tests whether <code>x</code> is a future.
   */
  isFuture("IsFuture", "isFuture", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isFuture(x, ?b)</code>
   * <p>
   * Procedure version of <code>isFuture(x)</code>.
   */
  isFutureP("IsFuture", "isFuture", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isInt(+x)</code>
   * <p>
   * Tests whether <code>x</code> is an integer.
   */
  isInt("IsInt", "isInt", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isInt(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isInt(x)</code>.
   */
  isIntP("IsInt", "isInt", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isKinded(x)</code>
   * <p>
   * Tests whether <code>x</code> is currently kinded, i. e., is constrained but not yet determined.
   * For example, 'foo(a:12, ...) is kinded because it is constrained to be a record, yet its arity
   * is not yet known. Also, a non-determined finite domain variable is kinded: its type is known to
   * be integer, but its value is not yet determined. Similarly for finite set variables.
   */
  isKinded("IsKinded", "isKinded", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isKinded(x, ?b)</code>
   * <p>
   * Procedure version of <code>isKinded(x)</code>.
   */
  isKindedP("IsKinded", "isKinded", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isList(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a list.
   */
  isList("IsList", "isList", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "l", true, false)),
      null, null),
  /**
   * <code>isList(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isList(x)</code>.
   */
  isListP("IsList", "isList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isNat(+i)</code>
   * <p>
   * Tests whether <code>i</code> is a natural number, i.e., an integer greater than or equal to
   * <code>0</code>.
   */
  isNat("IsNat", "isNat", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>isNat(+i, ?b)</code>
   * <p>
   * Procedure version of <code>isNat(i)</code>.
   */
  isNatP("IsNat", "isNat", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isNumber(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a number.
   */
  isNumber("IsNumber", "isNumber", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isNumber(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isNumber(x)</code>.
   */
  isNumberP("IsNumber", "isNumber", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isOdd(+i)</code>
   * <p>
   * Tests whether <code>i</code> is an odd integer.
   */
  isOdd("IsOdd", "isOdd", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>isOdd(+i, ?b)</code>
   * <p>
   * Procedure version of <code>isOdd(i)</code>.
   */
  isOddP("IsOdd", "isOdd", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isRecord(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a record.
   */
  isRecord("IsRecord", "isRecord", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isRecord(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isRecord(x)</code>.
   */
  isRecordP("IsRecord", "isRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isTuple(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a tuple.
   */
  isTuple("IsTuple", "isTuple", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isTuple(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isTuple(x)</code>.
   */
  isTupleP("IsTuple", "isTuple", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>label(+r)</code>
   * <p>
   * Returns the label (name) of <code>r</code> (without the leading apostrophe).
   */
  label("Label", "label", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>label(+r, ?l)</code>
   * <p>
   * Procedure version of <code>label(r)</code>.
   */
  labelP("Label", "label", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "l", true, false)),
      null, null),
  /**
   * <code>length(+xs)</code>
   * <p>
   * Returns the length of <code>xs</code>.
   */
  length("Length", "length", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>length(+xs, ?i)</code>
   * <p>
   * Procedure version of <code>width(r)</code>.
   */
  lenghtP("Length", "length", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>log(+f1)</code>
   * <p>
   * Returns the logarithm to the base <i>e</i> of <code>f1</code>.
   */
  log("Log", "log", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>log(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>log(f1)</code>.
   */
  logP("Log", "log", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>makeList(+i)</code>
   * <p>
   * Returns a new list of length <code>i</code>. All elements are fresh variables.
   */
  makeList("MakeList", "makeList", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>makeList(+i, ?xs)</code>
   * <p>
   * Procedure version of <code>makeList(i)</code>.
   */
  makeListP("MakeList", "makeList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>makeRecord(+l, +lis)</code>
   * <p>
   * Returns a new record with label (name) <code>l</code>, features <code>lis</code>, and fresh
   * variables at every field. All elements of <code>lis</code> must be pairwise distinct, else an
   * error exception is raised at runtime.
   * <p>
   * For example, <code>makeRecord(l, a, r)</code> waits until <code>l</code> is bound to a name
   * literal, say <code>b</code>, and <code>a</code> is bound to a list of literals and integers,
   * say <code>[c, d, 1]</code>, and then binds <code>r</code> to <code>'b(_, c:_, d:_)</code>.
   */
  makeRecord("MakeRecord", "makeRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "lis", true, false)),
      null, null),
  /**
   * <code>makeRecord(+l, +lis, ?r)</code>
   * <p>
   * Procedure version of <code>makeRecord(l, lis)</code>.
   */
  makeRecordP("MakeRecord", "makeRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "lis", true, false),
          new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>makeTuple(+l, +i)</code>
   * <p>
   * Returns a new tuple with label <code>l</code> and fresh variables at features <code>1</code>
   * to
   * <code>i</code>. <code>I</code> must be non-negative, else an exception is raised.
   * <p>
   * For example, <code>makeTuple(l, n, t)</code> waits until <code>l</code> is bound to a name
   * literal, say <code>b</code>, and <code>n</code> is bound to a number, say <code>3</code>,
   * whereupon <code>t</code> is bound to <code>'b(_, _, _)</code>.
   */
  makeTuple("MakeTuple", "makeTuple", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>makeTuple(+l, +i, ?t)</code>
   * <p>
   * Procedure version of <code>makeTuple(l, i)</code>.
   */
  makeTupleP("MakeTuple", "makeTuple", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>map(+r1, +f)</code>
   * <p>
   * Returns a record with same label and arity as <code>r1</code>, whose fields are computed by
   * applying the unary function <code>f</code> to all fields of <code>r1</code>.
   * <p>
   * For example,
   * <code>map('a(12, b:13, c:1), fun $ (a) {(a*2)})</code> yields the output record <code>'a(24,
   * b:26, c:2)</code>.
   */
  map("Record.map", "map", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>map(+r1, +f, ?r2)</code>
   * <p>
   * Procedure version of <code>map(r1, f)</code>.
   */
  mapP("Record.map", "map", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>max(+afi1, +afi2)</code>
   * <p>
   * Returns the maximum of <code>afi1</code> and <code>afi2</code>.
   */
  max("Max", "max", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "afi1", true, false),
          new Variable(-1, "afi2", true, false)),
      null, null),
  /**
   * <code>max(+afi1, +afi2, ?afi3)</code>
   * <p>
   * Procedure version of <code>max(afi1, afi2)</code>.
   */
  maxP("Max", "max", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "afi1", true, false),
          new Variable(-1, "afi2", true, false),
          new Variable(-1, "afi3", true, false)),
      null, null),
  /**
   * <code>member(x, +ys)</code>
   * <p>
   * Tests whether <code>x</code> is equal (in the sense of <code>==</code>) to some element of
   * <code>ys</code>. Note : all other built-in functions that operate on lists take them as their
   * first argument. <code>member()</code> is the only exception (for historical reasons). TODO
   * resolve this incoherence ?
   */
  member("Member", "member", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>member(x, +ys, ?b)</code>
   * <p>
   * Procedure version of <code>member(x, ys)</code>.
   */
  memberP("Member", "member", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>merge(+xs, +ys, +f)</code>
   * <p>
   * Returns a list <code>zs</code> obtained by merging <code>xs</code> and <code>ys</code> using
   * the ordering described by <code>f</code>. The lists <code>xs</code> and <code>ys</code> must be
   * sorted.
   */
  merge("Merge", "merge", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>merge(+xs, +ys, +f, ?zs)</code>
   * <p>
   * Procedure version of <code>merge(xs, ys, f)</code>.
   */
  mergeP("Merge", "merge", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>min(+afi1, +afi2)</code>
   * <p>
   * Returns the minimum of <code>afi1</code> and <code>afi2</code>.
   */
  min("Min", "min", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "afi1", true, false),
          new Variable(-1, "afi2", true, false)),
      null, null),
  /**
   * <code>min(+afi1, +afi2, ?afi3)</code>
   * <p>
   * Procedure version of <code>min(afi1, afi2)</code>.
   */
  minP("Min", "min", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "afi1", true, false),
          new Variable(-1, "afi2", true, false),
          new Variable(-1, "afi3", true, false)),
      null, null),
  /**
   * @hidden
   */
  NEWCELL("NewCell", "newCell", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "InitValue", true, false)),
      null, null),
  /**
   * <code>not(+b1)</code>
   * <p>
   * Returns the negation of truth value <code>b1</code>.
   */
  not("Not", "not", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "b1", true, false)),
      null, null),
  /**
   * <code>not(+b1, ?b2)</code>
   * <p>
   * Procedure version of <code>not(b1)</code>.
   */
  notP("Not", "not", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "b1", true, false),
          new Variable(-1, "b2", true, false)),
      null, null),
  /**
   * <code>nth(+xs, +i)</code>
   * <p>
   * Returns the <code>i</code>th element of <code>xs</code> (counting from 1).
   */
  nth("Nth", "nth", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>nth(+xs, +i, ?y)</code>
   * <p>
   * Procedure version of <code>nth(xs, i)</code>.
   */
  nthP("Nth", "nth", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>numbers(+fromI, +toI, stepI)</code>
   * <p>
   * Returns a list with elements from <code>fromI</code> to <code>toI</code> with step
   * <code>stepI</code>.
   * <p>
   * For example, <code>numbers(1, 5, 2)</code> returns <code>[1, 3, 5]</code>, <code>numbers(5, 1,
   * 2)</code> returns the list (nil), and <code>numbers(5, 0, -2)</code> yields the list <code>[5,
   * 3, 1]</code>.
   */
  numbers("List.number", "numbers", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "fomI", true, false),
          new Variable(-1, "toI", true, false),
          new Variable(-1, "stepI", true, false)),
      null, null),
  /**
   * <code>numbers(+fromI, +toI, +stepI, ?xs)</code>
   * <p>
   * Procedure version of <code>numbers(fromI, toI, stepI)</code>.
   */
  numbersP("List.number", "numbers", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "fromI", true, false),
          new Variable(-1, "toI", true, false),
          new Variable(-1, "stepI", true, false),
          new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>or(+b1, +b2)</code>
   * <p>
   * Returns the disjunction of truth value <code>b1</code> and <code>b2</code>. Note that
   * <code>or()</code> is different from the operation available via the keyword <code>||</code> in
   * that it always evaluates its second argument. For instance, <code>true || p</code> reduces
   * without reducing application of <code>p</code>, whereas reduction of <code>or(true, p)</code>
   * always applies <code>p</code>.
   */
  or("Or", "or", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "b1", true, false),
          new Variable(-1, "b2", true, false)),
      null, null),
  /**
   * <code>or(+b1, +b2, ?b3)</code>
   * <p>
   * Procedure version of <code>or(b1, b2)</code>.
   */
  orP("Or", "or", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "b1", true, false),
          new Variable(-1, "b2", true, false),
          new Variable(-1, "b3", true, false)),
      null, null),
  /**
   * <code>partition(+r1, +f, ?r2, ?r3)</code>
   * <p>
   * <code>partition</code> computes a record <code>r2</code> which contains all the features and
   * fields of the record <code>r1</code> for which the unary boolean function <code>f</code>
   * yields
   * <code>true</code>, and a record <code>r3</code> with the remaining fields of <code>r1</code>.
   */
  partition("Record.partition", "partition", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false),
          new Variable(-1, "r3", true, false)),
      null, null),
  /**
   * <code>pow(+fi1, +fi2)</code>
   * <p>
   * Returns <code>fi1</code> to the power of <code>fi2</code>.
   */
  pow("Pow", "pow", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "fi1", true, false),
          new Variable(-1, "fi2", true, false)),
      null, null),
  /**
   * <code>pow(+fi1, +fi2, ?fi3)</code>
   * <p>
   * Procedure version of <code>pow(fi1, fi2)</code>.
   */
  powP("Pow", "pow", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "fi1", true, false),
          new Variable(-1, "fi2", true, false),
          new Variable(-1, "fi3", true, false)),
      null, null),
  /**
   * <code>reverse(+xs)</code>
   * <p>
   * Returns the elements of <code>xs</code> in reverse order.
   */
  reverse("Reverse", "reverse", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>reverse(xs, ?ys)</code>
   * <p>
   * Procedure version of <code>reverse(xs)</code>.
   */
  reverseP("Reverse", "reverse", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>round(+f1)</code>
   * <p>
   * Returns the integral value closest to <code>f1</code>. If there are two candidates,
   * <code>f1</code> is rounded to the closest even integral value, e. g., <code>round(1.5)</code>
   * and <code>round(2.5)</code> both return <i>2.0</i>.
   */
  round("Round", "round", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>round(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>round(f1)</code>.
   */
  roundP("Round", "round", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>sin(+f1)</code>
   * <p>
   * Returns the sine of <code>f1</code>.
   */
  sine("Sin", "sin", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>sine(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>sine(f1)</code>.
   */
  sineP("Sine", "sine", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>sinh(+f1)</code>
   * <p>
   * Returns the hyperbolic sine of <code>f1</code>.
   */
  sinh("Float.sinh", "sinh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>sinh(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>sinh(f1)</code>.
   */
  sinhP("Float.sinh", "sinh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>some(+r, +f)</code>
   * <p>
   * Tests whether the unary boolean function <code>f</code> yields <code>code</code> when applied
   * to some fields of <code>r</code>. Stops at the first field for which <code>f</code> yields
   * <code>true</code>. The fields are tested in the order given by <code>arity()</code> (which
   * see).
   */
  some("Record.some", "some", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>some(+r, +f, ?b)</code>
   * <p>
   * Procedure version of <code>some(r, f)</code>.
   */
  someP("Record.some", "some", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>sort(+xs, +f)</code>
   * <p>
   * Returns a list <code>ys</code> obtained by sorting <code>xs</code> using the comparison
   * function <code>f</code>. <code>F</code> must be a binary function returning a boolean value.
   * <code>sort()</code> is implemented using the mergesort algorithm.
   * <p>
   * For example, <code>sort([2 3 4 1 1], fun$(a, b){(a<b)})</code> returns the list <code>[1 1 2 3
   * 4]</code>.
   */
  sort("Sort", "sort", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>sort(+xs, +f, ?ys)</code>
   * <p>
   * Procedure version of <code>sort(xs, f)</code>.
   */
  sortP("Sort", "sort", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>sqrt(+f1)</code>
   * <p>
   * Returns the square root of <code>f1</code>.
   */
  sqrt("Sqrt", "sqrt", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>sqrt(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>sqrt(f1)</code>.
   */
  sqrtP("Sqrt", "sqrt", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>status(x)</code>
   * <p>
   * Returns status and type information on <code>x</code>.
   * <p>
   * If <code>x</code> is free, the atom <code>free</code> is returned. If <code>x</code> is a
   * future, the atom <code>future</code> is returned. If <code>x</code> is a failed value, the
   * atom
   * <code>failed</code> is returned. If <code>x</code> is kinded, the tuple <code>kinded(y)</code>
   * is returned, where <code>y</code> is bound to either the atoms <code>int</code>,
   * <code>fset</code> or <code>record</code>, depending on the type of <code>x</code>. If
   * <code>x</code> is determined, the tuple <code>det(Y)</code> is returned, where <code>y</code>
   * is bound to the atom as returned by <code>type(x)</code>.
   */
  status("Value.status", "status", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>status(x, ?t)</code>
   * <p>
   * Procedure version of <code>status(x)</code>.
   */
  statusP("Value.status", "status", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "t", true, false)),
      null, null),
  /**
   * <code>sub(+xs, +ys)</code>
   * <p>
   * Tests whether <code>xs</code> is a sublist of <code>ys</code>, i.e., whether it contains all
   * elements of <code>xs</code> in the same order as <code>xs</code> but not necessarily in
   * succession.
   * <p>
   * For example, <code>[a, b]</code> is a sublist of both <code>[1, a, b, 2]</code> and <code>[1,
   * a, 2, b, 3]</code>, but not of <code>[b, a]</code>.
   */
  sub("List.sub", "sub", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>sub(+xs, +ys, ?b)</code>
   * <p>
   * Procedure version of <code>sub(xs, ys)</code>.
   */
  subP("List.sub", "sub", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>subtractList(+xs, y)</code>
   * <p>
   * Returns <code>xs</code> without the leftmost occurrence of <code>y</code> if there is one.
   */
  subtractList("List.subtract", "subtractList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "lis", true, false)),
      null, null),
  /**
   * <code>subtractList(+xs, y, ?zs)</code>
   * <p>
   * Procedure version of <code>subtractList(xs, y)</code>.
   */
  subtractListP("List.subtract", "subtractList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "y", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>subtractListRecord(+r1, +lis)</code>
   * <p>
   * Returns record <code>r1</code> with all features in <code>lis</code> removed.
   * <p>
   * For example, <code>subtractList('f(jim:1, jack:2, jesse:4), [jesse, jim])</code> returns the
   * record <code>'f(jack:2)</code>.
   */
  subtractListRecord("Record.subtractList", "subtractListRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "lis", true, false)),
      null, null),
  /**
   * <code>subtractListRecord(+r1, +lis, ?r2)</code>
   * <p>
   * Procedure version of <code>subtractListRecord(r1, lis)</code>.
   */
  subtractListRecordP("Record.subtractList", "subtractListRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "lis", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>subtractRecord(+r1, +li)</code>
   * <p>
   * If <code>r1</code> has feature <code>li</code>, returns record <code>r1</code> with feature
   * <code>li</code> removed. Otherwise, return <code>r1</code>.
   */
  subtractRecord("Record.subtract", "subtractRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "li", true, false)),
      null, null),
  /**
   * <code>subtractRecord(+r1, +li, ?r2)</code>
   * <p>
   * Procedure version of <code>subtractRecord(r1, li)</code>.
   */
  subtractRecordP("Record.subtract", "subtractRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>tail(+i, y)</code>
   * <p>
   * Returns a list with at least <code>i</code> elements whose rest is <code>y</code> (which
   * doesn't need to be a list). The first <code>i</code> elements are fresh variables.
   * <p>
   * For example, <code>tail(2, (a::b::nil))</code> returns <code>(_::_::a::b::nil)</code>.
   */
  tail("List.withTail", "tail", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>tail(+i, y, ?xs)</code>
   * <p>
   * Procedure version of <code>tail(i, y)</code>.
   */
  tailP("List.withTail", "tail", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "y", true, false),
          new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>take(+xs, +i)</code>
   * <p>
   * Returns the list that contains the first <code>i</code> elements of <code>xs</code>, or
   * <code>xs</code> if it is shorter.
   */
  take("List.take", "take", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>take(+xs, +i, ?ys)</code>
   * <p>
   * Procedure version of <code>take(xs, i)</code>.
   */
  takeP("List.take", "take", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>takeDrop(+xs, +i, ?ys, ?zs)</code>
   * <p>
   * Binds <code>ys</code> to <code>take(xs, i)</code> and <code>zs</code> to <code>drop(xs, i)</code>.
   */
  takeDrop("List.takeDrop", "takeDrop", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>takeDropWhile(+r1, +f, ?r2, ?r3)</code>
   * <p>
   * While <code>filter</code> selects all fields and features of a record which satisfy a certain
   * condition, <code>takeWhile</code> selects only the starting sequence of features and fields
   * which fulfill this condition. <code>dropWhile</code> computes a record with the remaining
   * features and fields. <code>takeWhileDrop</code> computes both records.
   */
  takeDropWhile("Record.takeDropWhile", "takeDropWhile", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false),
          new Variable(-1, "r3", true, false)),
      null, null),
  /**
   * <code>takeWhile(+r1, +f)</code>
   * <p>
   * See takeDropWhile()
   */
  takeWhile("Record.takeWhile", "takeWhile", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>takeWhile(+r1, +f, ?r2)</code>
   * <p>
   * See takeDropWhile()
   */
  takeWhileP("Record.takeWhile", "takeWhile", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>tan(+f1)</code>
   * <p>
   * Returns the tangent of <code>f1</code>.
   */
  tan("Tan", "tan", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>tan(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>tan(f1)</code>.
   */
  tanP("Tan", "tan", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>tanh(+f1)</code>
   * <p>
   * Returns the hyperbolic tangent of <code>f1</code>.
   */
  tanh("Float.tanh", "tanh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>tanh(+f1, ?f2)</code>
   * <p>
   * Procedure version of <code>tanh(f1)</code>.
   */
  tanhP("Float.tanh", "tanh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>toArray(+t)</code>
   * <p>
   * Returns an array with bounds between <code>1</code> and width(t), where the elements of the
   * array are the fields of <code>t</code>.
   */
  toArray("Tuple.toArray", "toArray", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "t", true, false)),
      null, null),
  /**
   * <code>toArray(+t, ?a)</code>
   * <p>
   * Procedure version of <code>toArray(t)</code>.
   */
  toArrayP("Tuple.toArray", "toArray", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "t", true, false),
          new Variable(-1, "a", true, false)),
      null, null),
  /**
   * <code>toDictionary(+r)</code>
   * <p>
   * Returns a dictionary whose keys and their entries correspond to the features and their fields
   * of <code>r</code>.
   */
  toDictionary("Record.toDictionary", "toDictionary", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>toDictionary(+r, ?dictionary)</code>
   * <p>
   * Procedure version of <code>toDictionary(r)</code>.
   */
  toDictionaryP("Record.toDictionary", "toDictionary", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>toList(+r)</code>
   * <p>
   * Returns the list of all fields of <code>r</code>, in the order given by <code>arity()</code>
   * (which see).
   * <p>
   * For example, <code>toList('f(a, a:2, b:3))</code> yields [a, 2, 3] as output.
   */
  toList("Record.toList", "toList", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>toList(+r, ?xs)</code>
   * <p>
   * Procedure version of <code>toList(r)</code>.
   */
  toListP("Record.toList", "toList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>toListInd(+r)</code>
   * <p>
   * Returns the list of pairs that contains the feature-field pairs of <code>r</code>, in the order
   * given by <code>arity()</code> (which see).
   * <p>
   * For example, <code>toListInd('f(a, a:2, b:3))</code> yields [1#a, a#2, b#3] as output.
   */
  toListInd("Record.toListInd", "toListInd", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>toListInd(+r, ?ts)</code>
   * <p>
   * Procedure version of <code>toListInd(r)</code>.
   */
  toListIndP("Record.toListInd", "toListInd", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "ts", true, false)),
      null, null),
  /**
   * <code>toVirtualString(x, +depthI, +widthI)</code>
   * <p>
   * Returns a virtual string describing the value of <code>x</code>. Note that this does not block
   * on <code>x</code>. The value of <code>depthI</code> and <code>widthI</code> are used to limit
   * output of records in depth and width respectively.
   */
  toVirtualString("Value.toVirtualString", "toVirtualString", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "depthI", true, false),
          new Variable(-1, "widthI", true, false)),
      null, null),
  /**
   * <code>toVirtualString(x, +depthI, +widthI, ?vs)</code>
   * <p>
   * Procedure version of <code>teVirtualString(x, depthI, widthI)</code>
   */
  toVirtualStringP("Value.toVirtualString", "toVirtualString", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "depthI", true, false),
          new Variable(-1, "widthI", true, false),
          new Variable(-1, "vs", true, false)),
      null, null),
  /**
   * <code>type(+x)</code>
   * <p>
   * Returns an atom describing the type of <code>x</code>. If <code>x</code> is of one of the
   * standard primary types, then the returned value is constrained to the most specific of int,
   * float, record, tuple, atom, name, procedure, cell, byteString, bitString, chunk, array,
   * dictionary, bitArray, 'class', object, 'lock', port, space, or 'thread'. If any other atom is
   * returned, this means that <code>x</code> is of no standard primary type, but an
   * implementation-dependent extension.
   */
  type("Value.type", "type", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>type(+x, ?a)</code>
   * <p>
   * Procedure version of <code>type(x)</code>.
   */
  typeP("Value.type", "type", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "a", true, false)),
      null, null),
  /**
   * <code>waitOr(+r1)</code>
   * <p>
   * Blocks until at least one field of <code>r</code> is determined. Returns the feature of a
   * determined field. Raises an exception if <code>r</code> is not a proper record, that is, if
   * <code>r</code> is a literal.
   * <p>
   * For example, <code>waitOr('a(_, b:1))</code> returns <code>b</code>, while <code>waitOr('a(2,
   * b:_))</code> returns <code>1</code>, and <code>waitOr('a(_, b:_))</code> blocks.
   */
  waitOr("Record.waitOr", "waitOr", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "r1", true, false)),
      null, null),
  /**
   * <code>waitOr(+r1, ?li)</code>
   * <p>
   * Procedure version of <code>waitOr(r1)</code>.
   */
  waitOrP("Record.waitOr", "waitOr", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "li", true, false)),
      null, null),
  /**
   * <code>width(+r)</code>
   * <p>
   * Returns the width of <code>r</code>.
   */
  width("Width", "width", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>width(+r, ?i)</code>
   * <p>
   * Procedure version of <code>width(r)</code>.
   */
  widthP("Width", "width", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>zip(+r1, +r2, +f)</code>
   * <p>
   * Given two records <code>r1</code> and <code>r2</code> and a binary function <code>f</code>,
   * returns a record <code>r3</code> with the same label as <code>r1</code>, and whose features are
   * those common to <code>r1</code> and <code>r2</code>. Features appearing only in one of the
   * records are silently dropped. Each field <code>x</code> of <code>r3</code> is computed by
   * applying <code>f(r1.x, r2.x)</code>.
   * <p>
   * For example,
   * <code>
   * zip( 'f(jim:1, jack:2, jesse:4), 'g(jim:1, jack:b, joe:c), def $(x, y) {(x#y)}
   * </code> yields the output record <code>'f(jim:1#a, jack:2#b)</code>.
   */
  zip("Record.zip", "zip", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "r2", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>zip(+r1, +r2, +f, ?r3)</code>
   * <p>
   * Procedure version of <code>zip(r1, r2, f)</code>.
   */
  zipP("Record.zip", "zip", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "r2", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r3", true, false)),
      null, null),
  ;

  private final String ozImage;
  private final String nozImage;
  private final BuiltInType localType;
  private final List<Pattern> args;
  private final List<ClassDescriptor> descrs;
  private final List<MethodDef> meths;

  BuiltIns(String ozImage, String nozImage, BuiltInType type,
      @Nullable List<Pattern> args, @Nullable List<ClassDescriptor> descrs,
      @Nullable List<MethodDef> meths) {
    this.ozImage = ozImage;
    this.nozImage = nozImage;
    this.localType = type;
    this.args = args;
    this.descrs = descrs;
    this.meths = meths;
  }

  /**
   * Returns the name of this function, procedure, class or functor in Oz.
   *
   * @return the name as known by Mozart
   */
  public String ozString() {
    return ozImage;
  }

  /**
   * Returns the name of this function, procedure, class or functor in NewOz.
   *
   * @return the name as known by Nozc
   */
  public String nozString() {
    return nozImage;
  }

  /**
   * @return the {@link BuiltInType} type of this BuiltIn
   * @hidden
   */
  public BuiltInType type() {
    return localType;
  }

  /**
   * @return the list of arguments of this built-in if this is a function/procedure, or null
   * otherwise.
   * @hidden
   */
  public List<Pattern> args() {
    if (this.type() == BuiltInType.FUNCTION || this.type() == BuiltInType.PROCEDURE) {
      return args;
    } else {
      return null;
    }
  }

  /**
   * @return the descriptors of this built-in if it is a class, or null otherwise.
   * @hidden
   */
  public List<ClassDescriptor> descrs() {
    if (this.type() == BuiltInType.CLASS) {
      return descrs;
    } else {
      return null;
    }
  }

  /**
   * @return the method definitions of this built-in if it is a class, or null otherwise.
   * @hidden
   */
  public List<MethodDef> meths() {
    if (this.type() == BuiltInType.CLASS) {
      return meths;
    } else {
      return null;
    }
  }
}
