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
// TODO http://mozart2.org/mozart-v1/doc-1.4.0/base/node9.html has been skipped, to discuss
// http://mozart2.org/mozart-v1/doc-1.4.0/base/cell.html#section.proccells.cells was intentionally dropped, as Cells don't exist in NewOz.
// http://mozart2.org/mozart-v1/doc-1.4.0/base/chunk.html#section.chunks.general was dropped, at least in the first release
// http://mozart2.org/mozart-v1/doc-1.4.0/base/weakdictionary.html#section.chunks.weakdictionaries was dropped, at least in the first release
// http://mozart2.org/mozart-v1/doc-1.4.0/base/bitarray.html#section.chunks.bitarrays was dropped, at least in the first release
// TODO http://mozart2.org/mozart-v1/doc-1.4.0/base/functor.html#section.chunks.functors was dropped for now, because of its complexity

/**
 * An enum containing all the functions, procedures, classes and functors known by the compiler.
 * They can be considered as built into the language itself, meaning they are available to be
 * referenced from any scope in any NewOz program.
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
   * <code>adjoinList(+r1, +ts)</code>
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
   * <code>alarm(+i)</code>
   * <p>
   * Returns <code>unit</code> after <code>i</code> milliseconds.
   * This is done asynchronously in that it is evaluated in its own thread.
   */
  alarm("Alarm", "alarm", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>alarm(+i, ?u)</code>
   * <p>
   * Procedure version of <code>alarm(i)</code>.
   */
  alarmP("Alarm", "alarm", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "u", true, false)),
      null, null),
  /**
   * <code>allList(+xs, +f)</code>
   * <p>
   * Tests whether the unary boolean function <code>f</code> yields <code>true</code> when applied
   * to all elements of <code>xs</code>. Stops at the first element for which <code>f</code> yields
   * <code>false</code>.
   */
  allList("List.all", "allList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>allList(+xs, +f, ?b)</code>
   * <p>
   * Procedure version of <code>allList(xs, f)</code>.
   */
  allListP("List.all", "allList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>allRecord(+r, +f)</code>
   * <p>
   * Tests whether the unary boolean function <code>f</code> yields <code>true</code> when applied
   * to all fields of <code>r</code>. Stops at the first field for which <code>f</code> yields
   * <code>false</code>. The fields are tested in the order given by <code>arity()</code> (which
   * see).
   */
  allRecord("Record.all", "allRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>allRecord(+r, +f, ?b)</code>
   * <p>
   * Procedure version of <code>all(r, f)</code>.
   */
  allRecordP("Record.all", "allRecord", BuiltInType.PROCEDURE,
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
   * <code>apply(+p, +xs)</code>
   * <p>
   * Applies the procedure <code>p</code> to the arguments given by the elements of the list
   * <code>xs</code>, provided that <code>procArity(p) == length(xs)</code>. <code>p</code> has to
   * be a procedure (i.e., not a function).
   */
  apply("Procedure.apply", "apply", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "p", true, false),
          new Variable(-1, "xs", true, false)),
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
   * <code>arrayToRecord(+l, +a)</code>
   * <p>
   * Returns a new record <code>r</code> with label <code>l</code> that contains as features the
   * integers between <code>low(a)</code> and <code>high(a)</code> and with the corresponding
   * fields.
   */
  arrayToRecord("Array.toRecord", "arrayToRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "a", true, false)),
      null, null),
  /**
   * <code>arrayToRecord(+l, +a, ?r)</code>
   * <p>
   * Procedure version of <code>arrayToRecord(l, a)</code>.
   */
  arrayToRecordP("Array.toRecord", "arrayToRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "a", true, false),
          new Variable(-1, "r", true, false)),
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
   * <code>byNeed(+fp)</code>
   * <p>
   * Concurrently evaluates <code>fp(x)</code> as soon as <code>x</code>, the returned value,
   * becomes needed. It can be defined as follows :
   * <pre><code>
   * defproc byNeed(p, x) {
   *  thread {waitNeeded(x) p(x)}
   * }
   * </code></pre>
   */
  byNeed("ByNeed", "byNeed", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "fp", true, false)),
      null, null),
  /**
   * <code>byNeed(+fp, ?x)</code>
   * <p>
   * Procedure version of <code>byNeed(fp)</code>
   */
  byNeedP("ByNeed", "byNeed", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "fp", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>byNeedFuture(p)</code>
   * <p>
   * Creates a by-need computation that evaluates <code>fp</code>, and then returns a future
   * <code>x</code> of its result. If the call to <code>fp</code> raises an expression
   * <code>e</code>, then <code>x</code> is bound to a failed value (see <code>failed()</code>)
   * that encapsulates <code>e</code>. It can be defined as follows :
   * <pre><code>
   *  defproc byNeedFuture(fp) {
   *    future(
   *      byNeed(
   *        fun $() {try {fp()} catch {case e => failed(e)}}
   *      )
   *    )
   *  }
   * </code></pre>
   */
  byNeedFuture("ByNeedFuture", "byNeedFuture", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "p", true, false)),
      null, null),
  /**
   * <code>byNeedFuture(fp, ?x)</code>
   * <p>
   * Procedure version of <code>byNeedFuture(fp)</code>
   */
  byNeedFutureP("ByNeedFuture", "byNeedFuture", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "fp", true, false),
          new Variable(-1, "x", true, false)),
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
   * <code>cloneArray(+a)</code>
   * <p>
   * Returns a new array with the same bounds and contents as <code>a</code>.
   */
  cloneArray("Array.clone", "cloneArray", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "a", true, false)),
      null, null),
  /**
   * <code>cloneArray(+a1, ?a2)</code>
   * <p>
   * Procedure version of <code>cloneArray(a1)</code>.
   */
  cloneArrayP("Array.clone", "cloneArray", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "a1", true, false),
          new Variable(-1, "a2", true, false)),
      null, null),
  /**
   * <code>cloneDictionary(+dictionary)</code>
   * <p>
   * Returns a new dictionary containing the currently valid keys and corresponding items of
   * <code>dictionary</code>.
   */
  cloneDictionary("Dictionary.clone", "cloneDictionary", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>cloneDictionary(+dictionary1, ?dictionary2)</code>
   * <p>
   * Procedure version of <code>cloneDictionary(dictionary1)</code>.
   */
  cloneDictionaryP("Dictionary.clone", "cloneDictionary", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary1", true, false),
          new Variable(-1, "dictionary2", true, false)),
      null, null),
  /**
   * <code>cloneRecord(+r1)</code>
   * <p>
   * Returns a new record with the same label (name) and features as <code>r1</code> and fresh
   * variables at every field.
   */
  cloneRecord("Record.clone", "cloneRecord", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "r1", true, false)),
      null, null),
  /**
   * <code>cloneRecord(+r1, ?r2)</code>
   * <p>
   * Procedure version of <code>cloneRecord(r1)</code>.
   */
  cloneRecordP("Record.clone", "cloneRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>condExchange(+dictionary, +li, defVal, oldVal, newVal)</code>
   * <p>
   * If <code>li</code>is a valid key of <code>dictionary</code>, then returns the current value of
   * <code>dictionary</code> under key <code>li</code> as item <code>oldVal</code>. Otherwise,
   * returns <code>defVal</code> as item <code>oldVal</code>. Sets the value of
   * <code>dictionary</code> under key <code>li</code> to be <code>newVal</code>.
   */
  condExchange("Dictionary.condExchange", "condExchange", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "defVal", true, false),
          new Variable(-1, "oldVal", true, false),
          new Variable(-1, "newVal", true, false)),
      null, null),
  /**
   * <code>condGet(+dictionary, +li, defVal)</code>
   * <p>
   * Returns the item <code>x</code> of <code>dictionary</code> under key <code>li</code>, if
   * <code>li</code>is a valid key of <code>dictionary</code>. Otherwise, returns
   * <code>defVal</code>.
   */
  condGet("Dictionary.condGet", "condGet", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "defVal", true, false)),
      null, null),
  /**
   * <code>condGet(+dictionary, +li, defVal, ?x)</code>
   * <p>
   * Procedure version of <code>condSelect(dictionary, li, defVal)</code>.
   */
  condGetP("Dictionary.condGet", "condGet", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "defVal", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>condSelect(+rc, +li, x)</code>
   * <p>
   * Returns the field of <code>rc</code> at feature <code>li</code>, if <code>rc</code> has
   * feature
   * <code>li</code>. Otherwise, return <code>x</code>.
   * <p>
   * This operation if defined on records, tuples, arrays, and dictionaries.
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
  condSelectP("CondSelect", "condSelect", BuiltInType.PROCEDURE,
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
   * <code>delay(+i)</code>
   * <p>
   * reduces ro <code>skip</code> after <code>i</code> milliseconds.
   * Whenever <code>i &lt;= 0</code>, <code>delay(i)</code>reduces immediately.
   */
  delay("Delay", "delay", BuiltInType.PROCEDURE,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>dictionaryToRecord(+l, +dictionary)</code>
   * <p>
   * Returns a new record <code>r</code> with label <code>l</code> whose features and their fields
   * correspond to the keys and their entries in <code>dictionary</code>.
   */
  dictionaryToRecord("Dictionary.toRecord", "dictionaryToRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>dictionaryToRecord(+l, +dictionary, ?r)</code>
   * <p>
   * Procedure version of <code>dictionaryToRecord(l, dictionary)</code>.
   */
  dictionaryToRecordP("Dictionary.toRecord", "dictionaryToRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>drop(+xs, +i)</code>
   * <p>
   * Returns the list <code>xs</code> with the first <code>i</code> elements removed, os
   * <code>nil</code> if it is shorter.
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
   * <code>dropWhileList(+xs, +f)</code>
   * <p>
   * See takeDropWhileList()
   */
  dropWhileList("List.dropWhile", "dropWhileList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>dropWhileList(+xs, +f, ?ys)</code>
   * <p>
   * See takeDropWhileList()
   */
  dropWhileListP("List.dropWhile", "dropWhileList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>dropWhileRecord(+r1, +f)</code>
   * <p>
   * See takeDropWhileRecord()
   */
  dropWhileRecord("Record.dropWhile", "dropWhileRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>dropWhileRecord(+r1, +f, ?r2)</code>
   * <p>
   * See takeDropWhileRecord()
   */
  dropWhileRecordP("Record.dropWhile", "dropWhileRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false)),
      null, null),
  /**
   * <code>entries(+dictionary)</code>
   * <p>
   * Returns the list of current entries of <code>dictionary</code>. An entry is a pair
   * <code>(li#x)</code>, where <code>li</code> is a valid key of <code>dictionary</code> and
   * <code>x</code> the corresponding item.
   */
  entries("Dictionary.entries", "entries", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>entries(+dictionary, ?ts)</code>
   * <p>
   * Procedure version of <code>entries(dictionary)</code>.
   */
  entriesP("Dictionary.entries", "entries", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "ts", true, false)),
      null, null),
  /**
   * <code>error(x)</code>
   * <p>
   * Returns an error exception record with dispatch field <code>x</code>.
   */
  errorException("Exception.error", "errorException", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>errorException(x, ?y)</code>
   * <p>
   * Procedure version of <code>errorException(x)</code>.
   */
  errorExceptionP("Exception.error", "errorException", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>exchangeArray(+array, +i, oldVal, newVal)</code>
   * <p>
   * Returns the current value of <code>array</code> under key <code>i</code> as item
   * <code>oldVal</code> and updates the value of <code>array</code> under key <code>i</code> to be
   * <code>newVal</code>.
   */
  exchangeArray("Array.exchange", "exchangeArray", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "array", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "oldVal", true, false),
          new Variable(-1, "newVal", true, false)),
      null, null),
  /**
   * <code>exchangeDictionary(+dictionary, +li, oldVal, newVal)</code>
   * <p>
   * Returns the current value of <code>dictionary</code> under key <code>li</code> as item
   * <code>oldVal</code> and updates the value of <code>dictionary</code> under key <code>li</code>
   * to be <code>newVal</code>.
   */
  exchangeDictionary("Dictionary.exchange", "exchangeDictionary", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "oldVal", true, false),
          new Variable(-1, "newVal", true, false)),
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
   * <code>failed(e)</code>
   * <p>
   * Creates a failed value <code>x</code> encapsulating exception <code>e</code>.
   * Whenever a statement is needs <code>x</code>, in particular, whenever a thread synchronises on <code>x</code>,
   * exception term <code>e</code> is raised.
   * This is convenient in concurrent designs;
   * if a concurrent generator encounters a problem while computing a value,
   * it may catch the corresponding exception, package it as a failed value and return the latter instead.
   * Thus eac consumer will be able to synchronously handle the exception when it attempts to use
   * the "failed" value.
   */
  failed("Value.failed", "failed", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "e", true, false)),
      null, null),
  /**
   * <code>failed(e, ?x)</code>
   * <p>
   * Procedure version of <code>failed(e)</code>
   */
  failedP("Value.failed", "failed", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "e", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>failureException(x)</code>
   * <p>
   * Returns a failure exception record with dispatch field <code>x</code>.
   */
  failureException("Exception.failure", "failureException", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>failureException(x, ?y)</code>
   * <p>
   * Procedure version of <code>failureException(x)</code>.
   */
  failureExceptionP("Exception.failure", "failureException", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>filterList(+xs, +f)</code>
   * <p>
   * Returns the list which contains all the elements of <code>xs</code>, for which the unary
   * boolean function <code>f</code> yields <code>true</code>. The ordering is preserved.
   */
  filterList("List.filter", "filterList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>filterList(+xs, +f, ?ys)</code>
   * <p>
   * Procedure version of <code>filterList(xs, f)</code>.
   */
  filterListP("List.filter", "filterList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>filterRecord(+r, +f)</code>
   * <p>
   * Returns the record which contains all the features and fields of the record <code>r</code>, for
   * which the unary boolean function <code>f</code> applied to the fields yields
   * <code>true</code>.
   */
  filterRecord("Record.filter", "filterRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>filterRecord(+r1, +f, ?r2)</code>
   * <p>
   * Procedure version of <code>filterRecord(r1, f)</code>.
   */
  filterRecordP("Record.filter", "filterRecord", BuiltInType.PROCEDURE,
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
   * <code>foldListL(+xs, +f, x)</code>
   * <p>
   * Used for folding the elements of <code>xs</code> by applying the binary function
   * <code>f</code>.
   * <p>
   * Applying the left folding function <code>foldListL([X1,... Xn], f, x)</code> reduces to
   * <code>f(...f(f(x, X1), X2), Xn)</code>. The first actual argument of <code>f</code> is the
   * accumulator in which the result of the previous application, or the start value <code>z</code>,
   * is passed. The second actual argument is an element of <code>xs</code>.
   * <p>
   * Besides the left folding function there exists a right folding variant. The application
   * <code>foldListR([X1,... Xn], f, x)</code> reduces to <code>f(X1, f(X2, ...f(Xn, x)))</code>.
   * The first actual argument of <code>f</code> is an element of <code>xs</code>; the second actual
   * argument is the accumulator in which the result of the previous application or the start value
   * <code>x</code> is passed.
   * <p>
   * For example,
   * <code>foldListL([1,2,3], fun $(xr, x){(x::xr)}, nil)</code> yields the output
   * <code>(3::2::1::nil)</code>, whereas <code>foldListR([b,c,d], fun $(x, xr){(x#r)}, a)</code>
   * yields the output <code>(b#(c#(d#a)))</code>.
   */
  foldListL("List.foldL", "foldListL", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldListL(+xs, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldListL(xs, f, x)</code>.
   */
  foldListLP("List.foldL", "foldListL", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>foldListR(+xs, +f, x)</code>
   * <p>
   * See foldListL().
   */
  foldListR("List.foldR", "foldListR", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldListR(+xs, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldListR(xs, f, x)</code>.
   */
  foldListRP("List.foldR", "foldListR", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>foldRecordL(+r, +f, x)</code>
   * <p>
   * Used for folding the fields of <code>r</code> by applying the binary function
   * <code>f</code>.
   * <p>
   * Supposing that <code>r</code> has the arity [F1, ... Fn], applying the left folding function
   * <code>foldRecordL(r, f, x)</code> reduces to <code>f(...f(f(x, r.F1), r.F2), r.Fn)</code>. The
   * first actual argument of <code>f</code> is the accumulator in which the result of the previous
   * application, or the start value of <code>x</code>, is passed. The second actual argument is the
   * field of <code>r</code>.
   * <p>
   * Besides the left folding function there exists a right folding variant. The application
   * <code>foldRecordR(r, f, x)</code> reduces to
   * <code>f(r.F1, f(r.F2, ...f(r.Fn, x)))</code>. The first actual argument of <code>f</code> is a
   * field of <code>r</code>; the second actual argument is the accumulator in which the result of
   * the previous application or the start value <code>x</code> is passed.
   * <p>
   * For example,
   * <code>foldRecordL('a(3, a:7, b:4), fun $(xr, x){(x::xr)}, nil)</code> yields the output
   * <code>[4, 7,
   * 3]</code>, whereas <code>foldRecordR('a(3, a:7, b:4), fun $(x, xr){(x::xr)}, nil)</code> yields
   * the output <code>[3, 7, 4]</code>.
   */
  foldRecordL("Record.foldL", "foldRecordL", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldRecordL(+r, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldRecordL(r, f, x)</code>.
   */
  foldRecordLP("Record.foldL", "foldRecordL", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>foldRecordR(+r, +f, x)</code>
   * <p>
   * See foldRecordL().
   */
  foldRecordR("Record.foldR", "foldRecordR", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldRecordR(+r, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldRecordR(r, f, x)</code>.
   */
  foldRecordRP("Record.foldR", "foldRecordR", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>foldTailL(+xs, +f, x)</code>
   * <p>
   * Used for folding all non-<code>nil</code> tails of <code>xs</code> by applying the binary
   * function
   * <code>f</code>, i.e. application of the left folding function <code>foldTailL([X1,... Xn], f,
   * z)</code> reduces to <code>f(...f(f(z, [X1,...XN]), [X2,...Xn]), ...Xn])</code>.
   * <p>
   * The right folding function is analogous.
   */
  foldTailL("List.foldLTail", "foldTailL", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldTailL(+xs, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldTailL(xs, f, x)</code>.
   */
  foldTailLP("List.foldLTail", "foldTailL", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>foldTailR(+xs, +f, x)</code>
   * <p>
   * See foldTailL().
   */
  foldTailR("List.foldRTail", "foldTailR", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "X", true, false)),
      null, null),
  /**
   * <code>foldTailR(+xs, +f, x, ?y)</code>
   * <p>
   * Procedure version of <code>foldTailR(xs, f, x)</code>.
   */
  foldTailRP("List.foldRTail", "foldTailR", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>forAllList(+xs, +p)</code>
   * <p>
   * Applies the unary procedure <code>p</code> to each element of <code>xs</code>, i.e., the
   * application <code>forAllList([X1,...Xn], p)</code> reduces to the sequence of statements
   * <code>p(X1) ... p(Xn)</code>.
   */
  forAllList("List.forAll", "forAllList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "p", true, false)),
      null, null),
  /**
   * <code>forAllRecord(+r, +p)</code>
   * <p>
   * Applies the unary procedure <code>p</code> to each field of <code>r</code>. Suppose that the
   * arity of <code>r</code> is [F1, ... Fn]. The application <code>forAll(r, p)</code> reduces to
   * the sequence of statements <code>p(r.F1) ... p(r.Fn)</code>.
   */
  forAllRecord("Record.forAll", "forAllRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "p", true, false)),
      null, null),
  /**
   * <code>forAllTail(+xs, +p)</code>
   * <p>
   * Applies the unary procedure <code>p</code> to each non-<code>nil</code> tail of
   * <code>xs</code>, i.e., the application <code>forAllTail([X1,...Xn], p)</code> reduces to the
   * sequence of statements <code>p([X1,...Xn]) p([X2,...Xn])... p([Xn])</code>.
   */
  forAllTail("List.forAllTail", "forAllTail", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "p", true, false)),
      null, null),
  /**
   * <code>future(x)</code>
   * <p>
   * Returns a future <code>y</code> for <code>x</code>, i.e. a read-only placeholder for
   * <code>x</code>. If <code>y</code> becomes needed, <code>x</code> is made needed too.
   */
  future("Value.'!!'", "future", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>future(x, ?y)</code>
   * <p>
   * Procedure version of <code>future(x)</code>.
   */
  futureP("Value.'!!'", "future", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>getArray(+array, +i)</code>
   * <p>
   * Returns the item of <code>array</code> under key <code>i</code>.
   */
  getArray("Get", "getArray", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "array", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>getArray(+array, +i, ?x)</code>
   * <p>
   * Procedure version of <code>getArray(array, i)</code>.
   */
  getArrayP("Get", "getArray", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "array", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>getAttr(+k, +li)</code>
   * <p>
   * Returns the initial value <code>x</code> for attribute <code>li</code> as defined by class
   * <code>k</code>.
   * <p>
   * For example, the call <code>getAttr(class $ attr a=4 {...}, a)</code> returns <code>4</code>.
   */
  getAttr("Class.getAttr", "getAttr", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "k", true, false),
          new Variable(-1, "li", true, false)),
      null, null),
  /**
   * <code>getAttr(+k, +li, ?x)</code>
   * <p>
   * Procedure version of <code>getAttr(k, li)</code>.
   */
  getAttrP("Class.getAttr", "getAttr", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "k", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>getDictionary(+dictionary, +i)</code>
   * <p>
   * Returns the item of <code>dictionary</code> under key <code>i</code>.
   */
  getDictionary("Dictionary.get", "getDictionary", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>getDictionary(+dictionary, +i, ?x)</code>
   * <p>
   * Procedure version of <code>getDictionary(dictionary, i)</code>.
   */
  getDictionaryP("Dictionary.get", "getDictionary", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>getPriority(+thread)</code>
   * <p>
   * Returns <code>low</code>, <code>medium</code>, or <code>high</code> according to the priority of <code>thread</code>.
   */
  getPriority("Thread.getPriority", "getPriority", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "thread", true, false)),
      null, null),
  /**
   * <code>getPriority(+thread, ?x)</code>
   * <p>
   * Procedure version of <code>getPriority(thread)</code>.
   */
  getPriorityP("Thread.getPriority", "getPriority", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "thread", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>getPriorityThis()</code>
   * <p>
   * Returns <code>low</code>, <code>medium</code>, or <code>high</code> according to the priority of the current thread.
   */
  getPriorityThis("Thread.getThisPriority", "getPriorityThis", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "thread", true, false)),
      null, null),
  /**
   * <code>getPriorityThis(?x)</code>
   * <p>
   * Procedure version of <code>getPriorityThis()</code>.
   */
  getPriorityThisP("Thread.getThisPriority", "getPriorityThis", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>hasFeature(+rc, +li)</code>
   * <p>
   * Tests whether <code>rc</code> has feature <code>li</code>.
   * <p>
   * This operation if defined on records, tuples, arrays, and dictionaries.
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
   * <code>high(+array)</code>
   * <p>
   * Returns the upper bound of the key range of <code>array</code>.
   */
  high("Array.high", "high", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "array", true, false)),
      null, null),
  /**
   * <code>high(+array, ?highI)</code>
   * <p>
   * Procedure version of <code>high(array)</code>.
   */
  highP("Array.high", "high", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "array", true, false),
          new Variable(-1, "highI", true, false)),
      null, null),
  /**
   * <code>injectException(+thread, +x)</code>
   * <p>
   * Raises <code>x</code> as exception on <code>thread</code>.
   * If <code>thread</code> is terminated, an error exception is raised in the current thread.
   */
  injectException("Thread.injectException", "injectException", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "thread", true, false),
          new Variable(-1, "x", true, false)),
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
   * <code>isArray(+x)</code>
   * <p>
   * Tests whether <code>x</code> is an array.
   */
  isArray("IsArray", "isArray", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isArray(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isArray(x)</code>.
   */
  isArrayP("IsArray", "isArray", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
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
   * <code>isClass(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a class.
   */
  isClass("IsClass", "isClass", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isClass(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isClass(x)</code>.
   */
  isClassP("isClass", "isClass", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isDictionary(x)</code>
   * <p>
   * Tests whether <code>x</code> is a dictionary.
   */
  isDictionary("IsDictionary", "isDictionary", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isDictionary(x, ?b)</code>
   * <p>
   * Procedure version of <code>isDictionary(x)</code>.
   */
  isDictionaryP("IsDictionary", "isDictionary", BuiltInType.PROCEDURE,
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
   * <code>isEmpty(+dictionary)</code>
   * <p>
   * Tests whether <code>dictionary</code> contains an entry.
   */
  isEmpty("Dictionary.isEmpty", "isEmpty", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>isEmpty(+dictionary, ?b)</code>
   * <p>
   * Procedure version of <code>isEmpty(dictionary)</code>.
   */
  isEmptyP("Dictionary.isEmpty", "isEmpty", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
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
   * <code>isFunctor(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a functor.
   */
  isFunctor("Functor.is", "isFunctor", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isFunctor(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isFunctor(x)</code>.
   */
  isFunctorP("Functor.is", "isFunctor", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
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
   * <code>isLock(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a lock.
   */
  isLock("IsLock", "isLock", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isLock(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isLock(x)</code>.
   */
  isLockP("IsLock", "isLock", BuiltInType.PROCEDURE,
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
   * <code>isNeeded(+x)</code>
   * <p>
   * Tests whether <code>x</code> is needed.
   */
  isNeeded("IsNeeded", "isNeeded", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isNeeded(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isNeeded(x)</code>.
   */
  isNeededP("IsNeeded", "isNeeded", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
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
   * <code>isObject(+x)</code>
   * <p>
   * Tests whether <code>x</code> is an object (an instance of a class).
   */
  isObject("IsObject", "isObject", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isObject(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isObject(x)</code>.
   */
  isObjectP("IsObject", "isObject", BuiltInType.PROCEDURE,
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
   * <code>isPort(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a port.
   */
  isPort("IsPort", "isPort", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isPort(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isPort(x)</code>.
   */
  isPortP("IsPort", "isPort", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isPrefix(+xs, +ys)</code>
   * <p>
   * Tests whether <code>xs</code> is a prefix of <code>ys</code>. Given that <code>xs</code> has
   * length <i>i</i>, it is a prefix of <code>ys</code> if <code>ys</code> has at least length
   * <i>i</i> and the first elements of <code>ys</code> are equal to the corresponding elements of
   * <code>xs</code>.
   */
  isPrefix("List.isPrefix", "isPrefix", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>isPrefix(+xs, +ys, ?b)</code>
   * <p>
   * Procedure version of <code>isPrefix(xs, ys)</code>.
   */
  isPrefixP("List.isPrefix", "isPrefix", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isProcedure(+x)</code>
   * <p>
   * Returns <code>true</code> if <code>x</code> is a procedure or a function, and false otherwise.
   */
  isProcedure("IsProcedure", "isProcedure", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isProcedure(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isProcedure(x)</code>.
   */
  isProcedureP("IsProcedure", "isProcedure", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
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
   * <code>isSuspended(+thread)</code>
   * <p>
   * Tests whether <code>thread</code> is currently suspended.
   */
  isSuspended("Thread.isSuspended", "isSuspended", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "thread", true, false)),
      null, null),
  /**
   * <code>isSuspended(+thread, ?b)</code>
   * <p>
   * Procedure version of <code>isSuspended(thread)</code>.
   */
  isSuspendedP("Thread.isSuspended", "isSuspended", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isThread(+x)</code>
   * <p>
   * Tests whether <code>x</code> is a thread.
   */
  isThread("IsThread", "isThread", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isThread(+x, ?b)</code>
   * <p>
   * Procedure version of <code>isThread(x)</code>.
   */
  isThreadP("IsThread", "isThread", BuiltInType.PROCEDURE,
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
   * <code>items(+dictionary)</code>
   * <p>
   * Returns the list of all items currently in <code>dictionary</code>.
   */
  items("Dictionary.items", "items", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>items(+dictionary, ?xs)</code>
   * <p>
   * Procedure version of <code>items(dictionary)</code>.
   */
  itemsP("Dictionary.items", "items", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>keys(+dictionary)</code>
   * <p>
   * Returns a list of all currently valid keys of <code>dictionary</code>.
   */
  keys("Dictionary.keys", "keys", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>keys(+dictionary, ?lis)</code>
   * <p>
   * Procedure version of <code>keys(dictionary)</code>.
   */
  keysP("Dictionary.keys", "keys", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "lis", true, false)),
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
   * <code>last(+xs)</code>
   * <p>
   * Returns the last element of <code>xs</code>. Raises an exception if <code>xs</code> is (nil).
   */
  last("List.last", "last", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>last(+xs, ?y)</code>
   * <p>
   * Procedure version of <code>last(xs)</code>.
   */
  lastP("List.last", "last", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "y", true, false)),
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
  lengthP("Length", "length", BuiltInType.PROCEDURE,
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
   * <code>low(+array)</code>
   * <p>
   * Returns the lower bound of the key range of <code>array</code>.
   */
  low("Array.low", "low", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "array", true, false)),
      null, null),
  /**
   * <code>low(+array, ?lowI)</code>
   * <p>
   * Procedure version of <code>low(array)</code>.
   */
  lowP("Array.low", "low", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "array", true, false),
          new Variable(-1, "lowI", true, false)),
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
   * <code>makeNeeded(+x)</code>
   * <p>
   * Makes <code>x</code> needed. this procedure is useful for triggering by-need computations on
   * <code>x</code> without having to suspend on <code>x</code>.
   */
  makeNeeded("Value.makeNeeded", "makeNeeded", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
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
   * <code>mapList(+xs, +f)</code>
   * <p>
   * Returns a list obtained by applying the unary function <code>f</code> to each element of
   * <code>xs</code>.
   */
  mapList("List.map", "mapList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>mapList(+xs, +f, ?ys)</code>
   * <p>
   * Procedure version of <code>mapList(xs, f)</code>.
   */
  mapListP("List.map", "mapList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>mapRecord(+r1, +f)</code>
   * <p>
   * Returns a record with same label and arity as <code>r1</code>, whose fields are computed by
   * applying the unary function <code>f</code> to all fields of <code>r1</code>.
   * <p>
   * For example,
   * <code>map('a(12, b:13, c:1), fun $ (a) {(a*2)})</code> yields the output record <code>'a(24,
   * b:26, c:2)</code>.
   */
  mapRecord("Record.map", "mapRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>mapRecord(+r1, +f, ?r2)</code>
   * <p>
   * Procedure version of <code>mapRecord(r1, f)</code>.
   */
  mapRecordP("Record.map", "mapRecord", BuiltInType.PROCEDURE,
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
   * <code>memberDictionary(+dictionary, +li)</code>
   * <p>
   * Tests whether <code>li</code> is a valid key of <code>dictionary</code>.
   */
  memberDictionary("Dictionary.member", "memberDictionary", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "li", true, false)),
      null, null),
  /**
   * <code>memberDictionary(+dictionary, +li, ?b)</code>
   * <p>
   * Procedure version of <code>memberDictionary(dictionary, li)</code>.
   */
  memberDictionaryP("Dictionary.member", "memberDictionary", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>memberList(x, +ys)</code>
   * <p>
   * Tests whether <code>x</code> is equal (in the sense of <code>==</code>) to some element of
   * <code>ys</code>. Note : all other built-in functions that operate on lists take them as their
   * first argument. <code>memberList()</code> is the only exception (for historical reasons). TODO
   * resolve this incoherence ?
   */
  memberList("Member", "memberList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>memberList(x, +ys, ?b)</code>
   * <p>
   * Procedure version of <code>memberList(x, ys)</code>.
   */
  memberListP("Member", "memberList", BuiltInType.PROCEDURE,
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
   * <code>new(+k, +initCall)</code>
   * <p>
   * Creates a new object from class <code>k</code> with initial call to <code>initCall</code>.
   */
  newObject("New", "new", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "k", true, false),
          new Variable(-1, "initCall", true, false)),
      null, null),
  /**
   * <code>newObject(+k, +initCall, ?o)</code>
   * <p>
   * Procedure version of <code>newObject(k, initCall)</code>.
   */
  newObjectP("New", "new", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "k", true, false),
          new Variable(-1, "initCall", true, false),
          new Variable(-1, "o", true, false)),
      null, null),
  /**
   * <code>newArray(+lowI, +highI, initX)</code>
   * <p>
   * Returns a new array with key range from <code>lowI</code> to <code>highI</code> including both.
   * All items are initialized to <code>initX</code>.
   */
  newArray("NewArray", "newArray", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "lowI", true, false),
          new Variable(-1, "highI", true, false),
          new Variable(-1, "initX", true, false)),
      null, null),
  /**
   * <code>newArrayP(+lowI, +highI, +initX, ?array)</code>
   * <p>
   * Procedure version of <code>newArray(lowI, highI, initX)</code>.
   */
  newArrayP("NewArray", "newArray", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "lowI", true, false),
          new Variable(-1, "highI", true, false),
          new Variable(-1, "initX", true, false),
          new Variable(-1, "array", true, false)),
      null, null),
  /**
   * @hidden
   */
  newCell("NewCell", "<hidden>", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>newClass(+parentKs, +attrR, +featR, +propAs)</code>
   * <p>
   * Creates a new class by inheriting from <code>ParentKs</code> with new attributes
   * <code>attrR</code>. Since features and properties are not yet supported in <b>NewOz</b>,
   * arguments <code>featR</code> and <code>propAs</code> should always be <code>_</code>. The
   * fields with integer values in <code>attrR</code> define the free attributes. The fields with
   * literal features defines attributes with initial values, where the features is the attribute
   * name and the field its initial value.
   * <p>
   * For example, the statement <code>c = newClass([d, e], 'a(a:1, b), _ _)</code> is equivalent to
   * :
   * <pre><code>
   *  class c extends d, e
   *  attr a=1 attr b
   *  {
   *
   *  }
   * </code></pre>
   */
  newClass("Class.new", "newClass", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "parentKs", true, false),
          new Variable(-1, "attrR", true, false),
          new Variable(-1, "featR", true, false),
          new Variable(-1, "propAs", true, false)),
      null, null),
  /**
   * <code>newClass(+parentKs, +attrR, +featR, +propAs, ?k)</code>
   * <p>
   * Procedure version of <code>newClass(parentKs, attrR, featR, propAs)</code>.
   */
  newClassP("Class.new", "newClass", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "parentKs", true, false),
          new Variable(-1, "attrR", true, false),
          new Variable(-1, "featR", true, false),
          new Variable(-1, "propAs", true, false),
          new Variable(-1, "k", true, false)),
      null, null),
  /**
   * <code>newDictionary()</code>
   * <p>
   * Returns a new empty dictionary.
   */
  newDictionary("NewDictionary", "newDictionary", BuiltInType.FUNCTION,
      Collections.emptyList(),
      null, null),
  /**
   * <code>newDictionary(?dictionary)</code>
   * <p>
   * Procedure version of <code>newDictionary()</code>.
   */
  newDictionaryP("NewDictionary", "newDictionary", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>newLock()</code>
   * <p>
   * Creates and returns a new lock.
   */
  newLock("NewLock", "newLock", BuiltInType.FUNCTION,
      Collections.emptyList(),
      null, null),
  /**
   * <code>newLock(?lock)</code>
   * <p>
   * Procedure version of <code>newLock()</code>.
   */
  newLockP("NewLock", "newLock", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "lock", true, false)),
      null, null),
  /**
   * <code>newPort(?xs)</code>
   * <p>
   * Returns a new port, together with its associated stream <code>xs</code>.
   */
  newPort("NewPort", "newPort", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>newPort(?xs, ?port)</code>
   * <p>
   * Procedure version of <code>newPort(xs)</code>.
   */
  newPortP("NewPort", "newPort", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "port", true, false)),
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
          new Variable(-1, "fromI", true, false),
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
   * <code>partitionList(+xs, +f, ?ys, ?zs)</code>
   * <p>
   * <code>partitionList</code> computes a list <code>ys</code> which contains all the elements of
   * <code>xs</code> for which the unary boolean function <code>f</code> yields <code>true</code>,
   * and a list <code>zs</code> with the remaining fields of <code>xs</code>. The ordering is
   * preserved.
   */
  partitionList("List.partition", "partitionList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>partitionRecord(+r1, +f, ?r2, ?r3)</code>
   * <p>
   * <code>partitionRecord</code> computes a record <code>r2</code> which contains all the features
   * and fields of the record <code>r1</code> for which the unary boolean function <code>f</code>
   * yields
   * <code>true</code>, and a record <code>r3</code> with the remaining fields of <code>r1</code>.
   */
  partitionRecord("Record.partition", "partitionRecord", BuiltInType.PROCEDURE,
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
   * <code>preempt(+thread)</code>
   * <p>
   * Preempts the current thread, i.e., immediately schedules another runnable thread (if there is one).
   * <code>thread</code> stays runnable.
   */
  preempt("Thread.preempt", "preempt", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "thread", true, false)),
      null, null),
  /**
   * <code>procArity(+fp)</code>
   * <p>
   * Returns the procedure arity of <code>fp</code>, i.e., the number of arguments it takes.
   * <p>
   * <b>Warning : if <code>fp</code> is a function (as opposed to a procedure), the result returned
   * by this operation is currently 1 too big. This will be fixed in a future release.</b>
   */
  procArity("Procedure.arity", "procArity", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "fp", true, false)),
      null, null),
  /**
   * <code>procArity(+fp, ?i)</code>
   * <p>
   * Procedure version of <code>procArity(fp)</code>.
   * <p>
   * <b>Warning : if <code>fp</code> is a function, the result returned by this operation is
   * currently 1 too big. This will be fixed in a future release.</b>
   */
  procArityP("Procedure.arity", "procArity", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "fp", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>putArray(+array, +i, x)</code>
   * <p>
   * Sets the item of <code>array</code> under key <code>i</code> to <code>x</code>.
   */
  putArray("Put", "putArray", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "array", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>putDictionary(+dictionary, +li, x)</code>
   * <p>
   * Sets the item of <code>dictionary</code> under key <code>li</code> to <code>x</code>.
   */
  putDictionary("Dictionary.put", "putDictionary", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>raiseError(+x)</code>
   * <p>
   * Wraps <code>x</code> in an error exception and raises this.
   * This procedure can be defined as follows, except that it always adds debug information :
   * <pre>
   *   <code>
   *     defproc raiseError(x) {
   *       raise {errorException(x)}
   *     }
   *   </code>
   * </pre>
   */
  raiseError("Exception.raiseError", "raiseError", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>remove(+dictionary, +li)</code>
   * <p>
   * Removes the item under key <code>li</code> from <code>dictionary</code> if <code>li</code> is a
   * valid key. Otherwise, does nothing.
   */
  remove("Dictionary.remove", "remove", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "dictionary", true, false),
          new Variable(-1, "li", true, false)),
      null, null),
  /**
   * <code>removeAll(+dictionary)</code>
   * <p>
   * Removes all entries currently in <code>dictionary</code>.
   */
  removeAll("Dictionary.removeAll", "removeAll", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "dictionary", true, false)),
      null, null),
  /**
   * <code>resumeThread(+thread)</code>
   * <p>
   * Resumes <code>thread</code>. Resumption undoes suspension.
   */
  resumeThread("Thread.resume", "resumeThread", BuiltInType.PROCEDURE,
      Collections.singletonList(new Variable(-1, "thread", true, false)),
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
   * <code>send(+port, x)</code>
   * <p>
   * Sends <code>x</code> to the port <code>port</code>: the stream pointed to by <code>port</code>
   * is unified with <code>(x::_)</code> (in a newly created thread), and the pointer advances to
   * the stream's new tail.
   */
  send("Send", "send", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "port", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>sendReceive(+port, x, y)</code>
   * <p>
   * Sends the pair <code>(x#y)</code> to the port <code>port</code>: the stream pointed to by
   * <code>port</code> is unified with <code>((x#y)::_)</code> (in a newly created thread), and the
   * pointer advances to the stream's new tail.
   * <p>
   * The argument <code>x</code> is commonly used as message to be sent, while <code>y</code> serves
   * as a reply to that message.
   */
  sendReceive("SendRecv", "sendReceive", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "port", true, false),
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>setPriority(+thread, +s)</code>
   * <p>
   * Sets priority of thread <code>thread</code> to the priority described in <code>s</code>.
   * <code>s</code> must be one of the strings <code>"low"</code>, <code>"medium"</code>, or <code>"high"</code>.
   */
  setPriority("Thread.setPriority", "setPriority", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "thread", true, false),
          new Variable(-1, "s", true, false)),
      null, null),
  /**
   * <code>setPriorityThis(+s)</code>
   * <p>
   * Sets priority of the current thread to the priority described in <code>s</code>.
   * <code>s</code> must be one of the strings <code>"low"</code>, <code>"medium"</code>, or <code>"high"</code>.
   */
  setPriorityThis("Thread.setThisPriority", "setPriorityThis", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "s", true, false)),
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
   * <code>someList(+xs, +f)</code>
   * <p>
   * Tests whether the unary boolean function <code>f</code> yields <code>true</code> when applied
   * to some elements of <code>xs</code>. Stops at the first field for which <code>f</code> yields
   * <code>true</code>.
   */
  someList("List.some", "someList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>someList(+xs, +f, ?b)</code>
   * <p>
   * Procedure version of <code>someList(xs, f)</code>.
   */
  someListP("List.some", "someList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>someRecord(+r, +f)</code>
   * <p>
   * Tests whether the unary boolean function <code>f</code> yields <code>code</code> when applied
   * to some fields of <code>r</code>. Stops at the first field for which <code>f</code> yields
   * <code>true</code>. The fields are tested in the order given by <code>arity()</code> (which
   * see).
   */
  someRecord("Record.some", "someRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>someRecord(+r, +f, ?b)</code>
   * <p>
   * Procedure version of <code>someRecord(r, f)</code>.
   */
  someRecordP("Record.some", "someRecord", BuiltInType.PROCEDURE,
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
   * For example,<code>sort([2 3 4 1 1], fun$(a, b){ (a&lt;b) })</code> returns the list
   * <code>[1 1 2 3 4]</code>.
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
   * <code>stateThread(+thread)</code>
   * <p>
   * Returns <code>runnable</code>, <code>blocked</code>, or <code>terminated</code>, according to the current state of <code>thread</code>.
   */
  stateThread("Thread.state", "stateThread", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>state(+thread, ?x)</code>
   * <p>
   * Procedure version of <code>stateThread(thread)</code>.
   */
  stateThreadP("Thread.state", "state", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "thread", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>status(x)</code>
   * <p>
   * Returns status and type information on <code>x</code>.
   * <p>
   * If <code>x</code> is free, the atom <code>free</code> is returned. If <code>x</code> is a
   * future, the atom <code>future</code> is returned. If <code>x</code> is a failed value, the
   * atom
   * <code>failed</code> is returned. If <code>x</code> is kinded, the tuple
   * <code>'kinded(y)</code>
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
   * <code>suspendThread(+thread)</code>
   * <p>
   * Suspends <code>thread</code> such that it cannot be further reduced.
   */
  suspendThread("Thread.suspend", "suspendThread", BuiltInType.PROCEDURE,
      Collections.singletonList(new Variable(-1, "thread", true, false)),
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
   * <code>systemException(x)</code>
   * <p>
   * Returns a system exception record with dispatch field <code>x</code>.
   */
  systemException("Exception.system", "systemException", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>systemException(x, ?y)</code>
   * <p>
   * Procedure version of <code>systemException(x)</code>.
   */
  systemExceptionP("Exception.system", "systemException", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
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
   * Binds <code>ys</code> to <code>take(xs, i)</code> and <code>zs</code> to <code>drop(xs,
   * i)</code>.
   */
  takeDrop("List.takeDrop", "takeDrop", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "i", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>takeDropWhileList(+xs, +f, ?ys, ?zs)</code>
   * <p>
   * While <code>filterList</code> selects all elements of a list which satisfy a certain
   * condition,
   * <code>takeWhileList</code> selects only the starting sequence of elements which fulfill this
   * condition. <code>dropWhileList</code> computes a list with the remaining elements.
   * <code>takeDropWhileList</code> combines the functionality of both previous methods.
   */
  takeDropWhileList("List.takeDropWhile", "takeDropWhileList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>takeWhileList(+xs, +f)</code>
   * <p>
   * See takeDropWhileList()
   */
  takeWhileList("List.takeWhile", "takeWhileList", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>takeWhileList(+xs, +f, ?r2)</code>
   * <p>
   * See takeDropWhileList()
   */
  takeWhileListP("List.takeWhile", "takeWhileList", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "ys", true, false)),
      null, null),
  /**
   * <code>takeDropWhileRecord(+r1, +f, ?r2, ?r3)</code>
   * <p>
   * While <code>filterRecord</code> selects all fields and features of a record which satisfy a
   * certain condition, <code>takeWhileRecord</code> selects only the starting sequence of features
   * and fields which fulfill this condition. <code>dropWhileRecord</code> computes a record with
   * the remaining features and fields. <code>takeDropWhileRecord</code> computes both records.
   */
  takeDropWhileRecord("Record.takeDropWhile", "takeDropWhileRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "r2", true, false),
          new Variable(-1, "r3", true, false)),
      null, null),
  /**
   * <code>takeWhileRecord(+r1, +f)</code>
   * <p>
   * See takeDropWhileRecord()
   */
  takeWhileRecord("Record.takeWhile", "takeWhileRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>takeWhileRecord(+r1, +f, ?r2)</code>
   * <p>
   * See takeDropWhileRecord()
   */
  takeWhileRecordP("Record.takeWhile", "takeWhileRecord", BuiltInType.PROCEDURE,
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
   * <code>terminate(+thread)</code>
   * <p>
   * Raises an exception <code>'kernel(terminate, ...)</code> on <code>thread</code>.
   */
  terminate("Thread.terminate", "terminate", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "thread", true, false)),
      null, null),
  /**
   * <code>thisThread()</code>
   * <p>
   * Returns the current thread.
   */
  thisThread("Thread.this", "thisThread", BuiltInType.FUNCTION,
      Collections.emptyList(),
      null, null),
  /**
   * <code>thisThread(?thread)</code>
   * <p>
   * Procedure version of <code>thisThread(x)</code>.
   */
  thisThreadP("Thread.this", "thisThread", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "thread", true, false)),
      null, null),
  /**
   * <code>time()</code>
   * <p>
   * Returns the number of seconds elapsed since January 1st of the current year.
   */
  time("Time.time", "time", BuiltInType.FUNCTION,
      Collections.emptyList(),
      null, null),
  /**
   * <code>time(?t)</code>
   * <p>
   * Procedure version of <code>time()</code>.
   */
  timeP("Time.time", "time", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "t", true, false)),
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
   * <code>listToRecord(+l, +ts)</code>
   * <p>
   * Returns a new record <code>r</code> with label <code>l</code> whose fields are given by the
   * list of pairs <code>ts</code> : for every element <i>li</i>#<i>xi</i> of <code>xs</code>,
   * <code>r</code> has a field <i>xi</i> at feature <i>li</i>. The features in the pairs list must
   * be pairwise distinct, else an error exception is raised.
   * <p>
   * For example, <code>listToRecord("f", [(a#1), (b#2), (c#3)])</code> yields <code>'f(a:1, b:2,
   * c:3)</code> as output.
   */
  listToRecord("List.toRecord", "listToRecord", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "ts", true, false)),
      null, null),
  /**
   * <code>listToRecord(+l, +ts, ?r)</code>
   * <p>
   * Procedure version of <code>listToRecord(r)</code>.
   */
  listToRecordP("List.toRecord", "listToRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "ts", true, false),
          new Variable(-1, "r", true, false)),
      null, null),
  /**
   * <code>toTuple(+l, +xs)</code>
   * <p>
   * Returns a new tuple with label <code>l</code> that contains the elements of <code>xs</code> as
   * fields, in the given order. As a limitation in this release of <b>NewOz</b>, <code>l</code> has
   * to a string.
   * <p>
   * For example, <code>toTuple("f", (a::b::c::nil))</code> yields <code>'f(a, b, c)</code> as
   * output.
   */
  toTuple("List.toTuple", "toTuple", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "xs", true, false)),
      null, null),
  /**
   * <code>toList(+l, +xs, ?t)</code>
   * <p>
   * Procedure version of <code>toTuple(l, xs)</code>.
   */
  toTupleP("List.toTuple", "toTuple", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "l", true, false),
          new Variable(-1, "xs", true, false),
          new Variable(-1, "t", true, false)),
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
   * implementation-dependent extension. TODO restrict to types existing in NewOz ?
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
   * <code>wait(+x)</code>
   * <p>
   * Blocks until <code>x</code> is determined. This statement makes <code>x</code> needed, causing
   * all by-need computations on <code>x</code> to be triggered. If <code>x</code> is or becomes
   * bound to a failed value, then its encapsulated exception is raised.
   */
  wait("Wait", "wait", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>waitNeeded(+x)</code>
   * <p>
   * Blocks until <code>x</code> is needed. This operation is the by-need synchronization.
   */
  waitNeeded("WaitNeeded", "waitNeeded", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>waitOr(x, y)</code>
   * <p>
   * Blocks until at least one of <code>x</code> or <code>y</code> is determined.
   */
  waitOr("WaitOr", "waitOr", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "y", true, false)),
      null, null),
  /**
   * <code>waitQuiet(+x)</code>
   * <p>
   * Blocks until <code>x</code> is determined or failed. Contrary to <code>wait()</code>, this
   * procedure does not make <code>x</code> needed. Also, if <code>x</code> is or becomes bound to a
   * failed value, no exception is raised.
   */
  waitQuiet("Value.waitQuiet", "waitQuiet", BuiltInType.PROCEDURE,
      Collections.singletonList(
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>waitRecord(+r1)</code>
   * <p>
   * Blocks until at least one field of <code>r</code> is determined. Returns the feature of a
   * determined field. Raises an exception if <code>r</code> is not a proper record, that is, if
   * <code>r</code> is a literal.
   * <p>
   * For example, <code>waitRecord('a(_, b:1))</code> returns <code>b</code>, while
   * <code>waitRecord('a(2, b:_))</code> returns <code>1</code>, and <code>waitRecord('a(_,
   * b:_))</code> blocks.
   */
  waitRecord("Record.waitOr", "waitRecord", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "r1", true, false)),
      null, null),
  /**
   * <code>waitRecord(+r1, ?li)</code>
   * <p>
   * Procedure version of <code>waitRecord(r1)</code>.
   */
  waitRecordP("Record.waitOr", "waitRecord", BuiltInType.PROCEDURE,
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
   * <code>zipLists(+xs, +ys, +f)</code>
   * <p>
   * Returns the list of all elements <code>z<i>i</i></code> computed by applying <code>f(x<i>i</i>,
   * y<i>i</i>)</code>, where <code>x<i>i</i></code> is the <i>i</i>th element of <code>xs</code>
   * and <code>y<i>i</i></code> is the <i>i</i>th element of <code>ys</code>. The two inputs lists
   * must be of equal length, else an error exception is raised.
   * <p>
   * For example,
   * <code>zipLists([1,6,3], [4,5,6], def $(x, y) {(x>y)}</code> yields the output list
   * <code>(false::true::false::nil)</code>.
   */
  zipLists("List.zip", "zipLists", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>zipLists(+xs, +ys, +f, ?zs)</code>
   * <p>
   * Procedure version of <code>zipLists(xs, ys, f)</code>.
   */
  zipListsP("List.zip", "zipLists", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "xs", true, false),
          new Variable(-1, "ys", true, false),
          new Variable(-1, "f", true, false),
          new Variable(-1, "zs", true, false)),
      null, null),
  /**
   * <code>zipRecords(+r1, +r2, +f)</code>
   * <p>
   * Given two records <code>r1</code> and <code>r2</code> and a binary function <code>f</code>,
   * returns a record <code>r3</code> with the same label as <code>r1</code>, and whose features are
   * those common to <code>r1</code> and <code>r2</code>. Features appearing only in one of the
   * records are silently dropped. Each field <code>x</code> of <code>r3</code> is computed by
   * applying <code>f(r1.x, r2.x)</code>.
   * <p>
   * For example,
   * <code>zip( 'f(jim:1, jack:2, jesse:4), 'g(jim:1, jack:b, joe:c), def $(x, y) {(x#y)}</code>
   * yields the output record <code>'f(jim:1#a, jack:2#b)</code>.
   */
  zipRecords("Record.zip", "zipRecords", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "r1", true, false),
          new Variable(-1, "r2", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>zipRecords(+r1, +r2, +f, ?r3)</code>
   * <p>
   * Procedure version of <code>zipRecords(r1, r2, f)</code>.
   */
  zipRecordsP("Record.zip", "zipRecords", BuiltInType.PROCEDURE,
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
