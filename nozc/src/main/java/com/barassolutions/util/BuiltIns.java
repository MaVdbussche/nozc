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
   * <code>abs(fi1)</code>
   * <p>
   * Returns the absolute value of <code>fi1</code>.
   */
  abs("Abs", "abs", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "fi1", true, false)),
      null, null),
  /**
   * <code>abs(fi1, ?fi2)</code>
   * <p>
   * Procedure version of <code>abs(fi1)</code>.
   */
  absP("Abs", "abs", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "fi1", true, false),
          new Variable(-1, "fi2", true, false)),
      null, null),
  /**
   * <code>acos(f1)</code>
   * <p>
   * Returns the arc cosine of <code>f1</code>.
   */
  acos("Acos", "acos", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>acos(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>acos(f1)</code>.
   */
  acosP("Acos", "acos", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>acosh(f1)</code>
   * <p>
   * Returns the inverse hyperbolic cosine of <code>f1</code>.
   */
  acosh("Float.acosh", "acosh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>acosh(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>acosh(f1)</code>.
   */
  acoshP("Float.acosh", "acosh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>and(b1, b2)</code>
   * <p>
   * Returns the conjunction of truth value <code>b1</code> and <code>b2</code>. Note that
   * <code>and()</code> is different from the operation available via the keyword <code>&amp;&amp;</code> in
   * that it always evaluates its second argument. For instance, <code>false &amp;&amp; p</code> reduces
   * without reducing application of <code>p</code>, whereas reduction of <code>and(false, p)</code>
   * always applies <code>p</code>.
   */
  and("And", "and", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "b1", true, false),
          new Variable(-1, "b2", true, false)),
      null, null),
  /**
   * <code>and(b1, b2, ?b3)</code>
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
   * <code>asin(f1)</code>
   * <p>
   * Returns the arc sine of <code>f1</code>.
   */
  asin("Asin", "asin", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>asin(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>asin(f1)</code>.
   */
  asinP("Asin", "asin", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>asinh(f1)</code>
   * <p>
   * Returns the inverse hyperbolic sine of <code>f1</code>.
   */
  asinh("Float.asinh", "asinh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>asinh(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>asinh(f1)</code>.
   */
  asinhP("Float.asinh", "asinh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>atan(f1)</code>
   * <p>
   * Returns the arc tangent of <code>f1</code>.
   */
  atan("Atan", "atan", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>atan(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>atan(f1)</code>.
   */
  atanP("Atan", "atan", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>atanh(f1)</code>
   * <p>
   * Returns the inverse hyperbolic tangent of <code>f1</code>.
   */
  atanh("Float.atanh", "atanh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>atanh(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>atanh(f1)</code>.
   */
  atanhP("Float.atanh", "atanh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>atan2(f1, f2)</code>
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
   * <code>atan2(f1, f2, ?f3)</code>
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
   * <p>
   * Displays <code>X</code> in the Mozart Browser
   */
  BROWSE("Browse", "browse", BuiltInType.PROCEDURE,
      Collections.singletonList(new Variable(-1, "ToPrint", true, false)),
      null, null),
  /**
   * <code>ceil(f1)</code>
   * <p>
   * Returns the ceiling of <code>f1</code> (rounding towards positive infinity).
   */
  ceil("Ceil", "ceil", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>ceil(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>ceil(f1)</code>.
   */
  ceilP("Ceil", "ceil", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>condSelect(rc, li, x)</code>
   * <p>
   * Returns the field of record <code>rc</code> at feature <code>li</code>, if <code>rc</code> has feature <code>li</code>. Otherwise, return <code>x</code>.
   */
  condSelect("CondSelect", "condSelect", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "rc", true, false),
          new Variable(-1, "li", true, false),
          new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>condSelect(rc, li, x, ?y)</code>
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
   * <code>cos(f1)</code>
   * <p>
   * Returns the cosine of <code>f1</code>.
   */
  cos("Cos", "cos", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>cos(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>cos(f1)</code>.
   */
  cosP("Cos", "cos", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>cosh(f1)</code>
   * <p>
   * Returns the hyperbolic cosine of <code>f1</code>.
   */
  cosh("Float.cosh", "cosh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>cosh(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>cosh(f1)</code>.
   */
  coshP("Float.cosh", "cosh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>exp(f1)</code>
   * <p>
   * Returns <code>f1</code> to the power of <i>e</i>.
   */
  exp("Exp", "exp", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>exp(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>exp(f1)</code>.
   */
  expP("Exp", "exp", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>floatToInt(f)</code>
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
   * <code>floatToInt(f, ?i)</code>
   * <p>
   * Procedure version of <code>floatToInt(f)</code>.
   */
  floatToIntP("FloatToInt", "floatToInt", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f", true, false),
          new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>floatToString(f)</code>
   * <p>
   * Returns the string describing the float <code>f</code> in Oz concrete syntax.
   */
  floatToString("FloatToString", "floatToString", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>floatToString(f, ?s)</code>
   * <p>
   * Procedure version of <code>floatToString(f)</code>.
   */
  floatToStringP("FloatToString", "floatToString", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f", true, false),
          new Variable(-1, "s", true, false)),
      null, null),
  /**
   * <code>floor(f1)</code>
   * <p>
   * Returns the floor of <code>f1</code> (rounding towards negative infinity).
   */
  floor("Floor", "floor", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>floor(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>floor(f1)</code>.
   */
  floorP("Floor", "floor", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>hasFeature(rc, li)</code>
   * <p>
   * Tests whether the record <code>rc</code> has feature <code>li</code>.
   */
  hasFeature("HasFeature", "hasFeature", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "rc", true, false),
          new Variable(-1, "li", true, false)),
      null, null),
  /**
   * <code>hasFeature(rc, li, ?b)</code>
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
   * <code>intToFloat(i)</code>
   * <p>
   * Returns the float closest to the integer <code>i</code>.
   */
  intToFloat("IntToFloat", "intToFloat", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>intToFloat(i, ?f)</code>
   * <p>
   * Procedure version of <code>intToFloat(i)</code>.
   */
  intToFloatP("IntToFloat", "intToFloat", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "f", true, false)),
      null, null),
  /**
   * <code>intToString(i)</code>
   * <p>
   * Returns the string describing the integer <code>i</code> in Oz concrete syntax.
   */
  intToString("IntToString", "intToString", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>intToString(i, ?s)</code>
   * <p>
   * Procedure version of <code>intToString(i)</code>.
   */
  intToStringP("IntToString", "intToString", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "s", true, false)),
      null, null),
  /**
   * <code>isBool(x)</code>
   * <p>
   * Tests whether <code>x</code> is a boolean.
   */
  isBool("IsBool", "isBool", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isBool(x, ?b)</code>
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
   * <code>isEven(i)</code>
   * <p>
   * Tests whether <code>i</code> is an even integer.
   */
  isEven("IsEven", "isEven", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>isEven(i, ?b)</code>
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
   * <code>isFloat(x)</code>
   * <p>
   * Tests whether <code>x</code> is a float.
   */
  isFloat("IsFloat", "isFloat", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isFloat(x, ?b)</code>
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
   * <code>isInt(x)</code>
   * <p>
   * Tests whether <code>x</code> is an integer.
   */
  isInt("IsInt", "isInt", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isInt(x, ?b)</code>
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
   * For example, foo(a:12, ...) is kinded because it is constrained to be a record, yet its arity
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
   * <code>isNat(i)</code>
   * <p>
   * Tests whether <code>i</code> is a natural number, i.e., an integer greater than or equal to
   * <code>0</code>.
   */
  isNat("IsNat", "isNat", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>isNat(i, ?b)</code>
   * <p>
   * Procedure version of <code>isNat(i)</code>.
   */
  isNatP("IsNat", "isNat", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isNumber(x)</code>
   * <p>
   * Tests whether <code>x</code> is a number.
   */
  isNumber("IsNumber", "isNumber", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isNumber(x, ?b)</code>
   * <p>
   * Procedure version of <code>isNumber(x)</code>.
   */
  isNumberP("IsNumber", "isNumber", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isOdd(i)</code>
   * <p>
   * Tests whether <code>i</code> is an odd integer.
   */
  isOdd("IsOdd", "isOdd", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "i", true, false)),
      null, null),
  /**
   * <code>isOdd(i, ?b)</code>
   * <p>
   * Procedure version of <code>isOdd(i)</code>.
   */
  isOddP("IsOdd", "isOdd", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "i", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>isRecord(x)</code>
   * <p>
   * Tests whether <code>x</code> is a record.
   */
  isRecord("IsRecord", "isRecord", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "x", true, false)),
      null, null),
  /**
   * <code>isRecord(x, ?b)</code>
   * <p>
   * Procedure version of <code>isRecord(x)</code>.
   */
  isRecordP("IsRecord", "isRecord", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "b", true, false)),
      null, null),
  /**
   * <code>log(f1)</code>
   * <p>
   * Returns the logarithm to the base <i>e</i> of <code>f1</code>.
   */
  log("Log", "log", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>log(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>log(f1)</code>.
   */
  logP("Log", "log", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>max(afi1, afi2)</code>
   * <p>
   * Returns the maximum of <code>afi1</code> and <code>afi2</code>.
   */
  max("Max", "max", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "afi1", true, false),
          new Variable(-1, "afi2", true, false)),
      null, null),
  /**
   * <code>max(x, afi1, afi2, ?afi3)</code>
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
   * <code>min(afi1, afi2)</code>
   * <p>
   * Returns the minimum of <code>afi1</code> and <code>afi2</code>.
   */
  min("Min", "min", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "afi1", true, false),
          new Variable(-1, "afi2", true, false)),
      null, null),
  /**
   * <code>min(x, afi1, afi2, ?afi3)</code>
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
   * <code>not(b1)</code>
   * <p>
   * Returns the negation of truth value <code>b1</code>.
   */
  not("Not", "not", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "b1", true, false)),
      null, null),
  /**
   * <code>not(b1, ?b2)</code>
   * <p>
   * Procedure version of <code>not(b1)</code>.
   */
  notP("Not", "not", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "b1", true, false),
          new Variable(-1, "b2", true, false)),
      null, null),
  /**
   * <code>or(b1, b2)</code>
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
   * <code>or(b1, b2, ?b3)</code>
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
   * <code>pow(fi1, fi2)</code>
   * <p>
   * Returns <code>fi1</code> to the power of <code>fi2</code>.
   */
  pow("Pow", "pow", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "fi1", true, false),
          new Variable(-1, "fi2", true, false)),
      null, null),
  /**
   * <code>pow(fi1, fi2, ?fi3)</code>
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
   * <code>round(f1)</code>
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
   * <code>round(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>round(f1)</code>.
   */
  roundP("Round", "round", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>sin(f1)</code>
   * <p>
   * Returns the sine of <code>f1</code>.
   */
  sine("Sin", "sin", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>sine(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>sine(f1)</code>.
   */
  sineP("Sine", "sine", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>sinh(f1)</code>
   * <p>
   * Returns the hyperbolic sine of <code>f1</code>.
   */
  sinh("Float.sinh", "sinh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>sinh(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>sinh(f1)</code>.
   */
  sinhP("Float.sinh", "sinh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>sqrt(f1)</code>
   * <p>
   * Returns the square root of <code>f1</code>.
   */
  sqrt("Sqrt", "sqrt", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>sqrt(f1, ?f2)</code>
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
   * <code>tan(f1)</code>
   * <p>
   * Returns the tangent of <code>f1</code>.
   */
  tan("Tan", "tan", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>tan(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>tan(f1)</code>.
   */
  tanP("Tan", "tan", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>tanh(f1)</code>
   * <p>
   * Returns the hyperbolic tangent of <code>f1</code>.
   */
  tanh("Float.tanh", "tanh", BuiltInType.FUNCTION,
      Collections.singletonList(
          new Variable(-1, "f1", true, false)),
      null, null),
  /**
   * <code>tanh(f1, ?f2)</code>
   * <p>
   * Procedure version of <code>tanh(f1)</code>.
   */
  tanhP("Float.tanh", "tanh", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "f1", true, false),
          new Variable(-1, "f2", true, false)),
      null, null),
  /**
   * <code>toVirtualString(x, depthI, widthI)</code>
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
   * <code>toVirtualString(x, depthI, widthI, ?vs)</code>
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
   * <code>type(x)</code>
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
   * <code>status(x, ?a)</code>
   * <p>
   * Procedure version of <code>type(x)</code>.
   */
  typeP("Value.type", "type", BuiltInType.PROCEDURE,
      Arrays.asList(
          new Variable(-1, "x", true, false),
          new Variable(-1, "a", true, false)),
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
