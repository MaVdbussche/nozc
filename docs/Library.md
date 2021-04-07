# Documentation

## `public enum BuiltIns`

An enum containing all the functions, procedures, classes and functors known by the compiler. They can be considered as built into the language itself, meaning they are available to be referenced from any scope in your NewOz program.

## `abs("Abs", "abs", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "fi1", true, false)), null, null),`

`abs(fi1)`

Returns the absolute value of `fi1`.

## `absP("Abs", "abs", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fi1", true, false), new Variable(-1, "fi2", true, false)), null, null),`

`abs(fi1, ?fi2)`

Procedure version of `abs(fi1)`.

## `acos("Acos", "acos", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`acos(f1)`

Returns the arc cosine of `f1`.

## `acosP("Acos", "acos", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`acos(f1, ?f2)`

Procedure version of `acos(f1)`.

## `acosh("Float.acosh", "acosh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`acosh(f1)`

Returns the inverse hyperbolic cosine of `f1`.

## `acoshP("Float.acosh", "acosh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`acosh(f1, ?f2)`

Procedure version of `acosh(f1)`.

## `and("And", "and", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false)), null, null),`

`and(b1, b2)`

Returns the conjunction of truth value `b1` and `b2`. Note that `and()` is different from the operation available via the keyword `&amp;&amp;` in that it always evaluates its second argument. For instance, `false &amp;&amp; p` reduces without reducing application of `p`, whereas reduction of `and(false, p)` always applies `p`.

## `andP("And", "and", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false), new Variable(-1, "b3", true, false)), null, null),`

`and(b1, b2, ?b3)`

Procedure version of `and(b1, b2)`.

## `asin("Asin", "asin", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`asin(f1)`

Returns the arc sine of `f1`.

## `asinP("Asin", "asin", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`asin(f1, ?f2)`

Procedure version of `asin(f1)`.

## `asinh("Float.asinh", "asinh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`asinh(f1)`

Returns the inverse hyperbolic sine of `f1`.

## `asinhP("Float.asinh", "asinh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`asinh(f1, ?f2)`

Procedure version of `asinh(f1)`.

## `atan("Atan", "atan", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`atan(f1)`

Returns the arc tangent of `f1`.

## `atanP("Atan", "atan", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`atan(f1, ?f2)`

Procedure version of `atan(f1)`.

## `atanh("Float.atanh", "atanh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`atanh(f1)`

Returns the inverse hyperbolic tangent of `f1`.

## `atanhP("Float.atanh", "atanh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`atanh(f1, ?f2)`

Procedure version of `atanh(f1)`.

## `atan2("Atan2", "atan2", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`atan2(f1, f2)`

Returns the principal value of the arc tangent of `f1` <i>/</i> `f2`, using the signs of both arguments to determine the quadrant of the return value. An error exception may (but needs not) be raised if both arguments are <i>0</i>.

## `atan2P("Atan2", "atan2", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false), new Variable(-1, "f3", true, false)), null, null),`

`atan2(f1, f2, ?f3)`

Procedure version of `atan2(f1, f2)`.

## `BROWSE("Browse", "browse", BuiltInType.PROCEDURE, Collections.singletonList(new Variable(-1, "ToPrint", true, false)), null, null),`

`browse(X)`

Displays `X` in the Mozart Browser

## `ceil("Ceil", "ceil", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`ceil(f1)`

Returns the ceiling of `f1` (rounding towards positive infinity).

## `ceilP("Ceil", "ceil", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`ceil(f1, ?f2)`

Procedure version of `ceil(f1)`.

## `condSelect("CondSelect", "condSelect", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false), new Variable(-1, "x", true, false)), null, null),`

`condSelect(rc, li, x)`

Returns the field of record `rc` at feature `li`, if `rc` has feature `li`. Otherwise, return `x`.

## `condSelectP("HasFeature", "hasFeature", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`condSelect(rc, li, x, ?y)`

Procedure version of `condSelect(rc, li, x)`.

## `cos("Cos", "cos", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`cos(f1)`

Returns the cosine of `f1`.

## `cosP("Cos", "cos", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`cos(f1, ?f2)`

Procedure version of `cos(f1)`.

## `cosh("Float.cosh", "cosh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`cosh(f1)`

Returns the hyperbolic cosine of `f1`.

## `coshP("Float.cosh", "cosh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`cosh(f1, ?f2)`

Procedure version of `cosh(f1)`.

## `exp("Exp", "exp", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`exp(f1)`

Returns `f1` to the power of <i>e</i>.

## `expP("Exp", "exp", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`exp(f1, ?f2)`

Procedure version of `exp(f1)`.

## `floatToInt("FloatToInt", "floatToInt", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "f", true, false)), null, null),`

`floatToInt(f)`

Returns the integer closest to float `f`. If there are two candidates, `f` is rounded to the closest even integer, e. g., `floatToInt(1.5)` and `floatToInt(2.5)` both return <i>2</i>.

<b><i>Attention</i></b>

In the current Oz implementation, the value is converted through a signed 32-bit integer, so this function is bogus when applied to large floats.

## `floatToIntP("FloatToInt", "floatToInt", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f", true, false), new Variable(-1, "i", true, false)), null, null),`

`floatToInt(f, ?i)`

Procedure version of `floatToInt(f)`.

## `floatToString("FloatToString", "floatToString", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "f", true, false)), null, null),`

`floatToString(f)`

Returns the string describing the float `f` in Oz concrete syntax.

## `floatToStringP("FloatToString", "floatToString", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f", true, false), new Variable(-1, "s", true, false)), null, null),`

`floatToString(f, ?s)`

Procedure version of `floatToString(f)`.

## `floor("Floor", "floor", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`floor(f1)`

Returns the floor of `f1` (rounding towards negative infinity).

## `floorP("Floor", "floor", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`floor(f1, ?f2)`

Procedure version of `floor(f1)`.

## `hasFeature("HasFeature", "hasFeature", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false)), null, null),`

`hasFeature(rc, li)`

Tests whether the record `rc` has feature `li`.

## `hasFeatureP("HasFeature", "hasFeature", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false), new Variable(-1, "b", true, false)), null, null),`

`hasFeature(rc, li, ?b)`

Procedure version of `hasFeature(rc, li)`.

## `intToFloat("IntToFloat", "intToFloat", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`intToFloat(i)`

Returns the float closest to the integer `i`.

## `intToFloatP("IntToFloat", "intToFloat", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "f", true, false)), null, null),`

`intToFloat(i, ?f)`

Procedure version of `intToFloat(i)`.

## `intToString("IntToString", "intToString", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`intToString(i)`

Returns the string describing the integer `i` in Oz concrete syntax.

## `intToStringP("IntToString", "intToString", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "s", true, false)), null, null),`

`intToString(i, ?s)`

Procedure version of `intToString(i)`.

## `isBool("IsBool", "isBool", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isBool(x)`

Tests whether `x` is a boolean.

## `isBoolP("IsBool", "isBool", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isBool(x, ?b)`

Procedure version of `isBool(x)`.

## `isDet("IsDet", "isDet", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isDet(x)`

Tests whether `x` is determined.

## `isDetP("IsDet", "isDet", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isDet(x, ?b)`

Procedure version of `isDet(x)`.

## `isEven("IsEven", "isEven", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`isEven(i)`

Tests whether `i` is an even integer.

## `isEvenP("IsEven", "isEven", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "b", true, false)), null, null),`

`isEven(i, ?b)`

Procedure version of `isEven(i)`.

## `isFailed("IsFailed", "isFailed", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isFailed(x)`

Tests whether `x` is a failed value.

## `isFailedP("IsFailed", "isFailed", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isFailed(x, ?b)`

Procedure version of `isFailed(x)`.

## `isFloat("IsFloat", "isFloat", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isFloat(x)`

Tests whether `x` is a float.

## `isFloatP("IsFloat", "isFloat", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isFloat(x, ?b)`

Procedure version of `isFloat(x)`.

## `isFree("IsFree", "isFree", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "X", true, false)), null, null),`

`isFree(x)`

Tests whether `x` is currently free.

## `isFreeP("IsFree", "isFree", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false) ), null, null),`

`isFree(x, ?b)`

Procedure version of `isFree(x)`.

## `isFuture("IsFuture", "isFuture", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isFuture(x)`

Tests whether `x` is a future.

## `isFutureP("IsFuture", "isFuture", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isFuture(x, ?b)`

Procedure version of `isFuture(x)`.

## `isInt("IsInt", "isInt", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isInt(x)`

Tests whether `x` is an integer.

## `isIntP("IsInt", "isInt", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isInt(x, ?b)`

Procedure version of `isInt(x)`.

## `isKinded("IsKinded", "isKinded", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isKinded(x)`

Tests whether `x` is currently kinded, i. e., is constrained but not yet determined. For example, foo(a:12, ...) is kinded because it is constrained to be a record, yet its arity is not yet known. Also, a non-determined finite domain variable is kinded: its type is known to be integer, but its value is not yet determined. Similarly for finite set variables.

## `isKindedP("IsKinded", "isKinded", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isKinded(x, ?b)`

Procedure version of `isKinded(x)`.

## `isNat("IsNat", "isNat", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`isNat(i)`

Tests whether `i` is a natural number, i.e., an integer greater than or equal to `0`.

## `isNatP("IsNat", "isNat", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "b", true, false)), null, null),`

`isNat(i, ?b)`

Procedure version of `isNat(i)`.

## `isNumber("IsNumber", "isNumber", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isNumber(x)`

Tests whether `x` is a number.

## `isNumberP("IsNumber", "isNumber", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isNumber(x, ?b)`

Procedure version of `isNumber(x)`.

## `isOdd("IsOdd", "isOdd", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`isOdd(i)`

Tests whether `i` is an odd integer.

## `isOddP("IsOdd", "isOdd", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "b", true, false)), null, null),`

`isOdd(i, ?b)`

Procedure version of `isOdd(i)`.

## `isRecord("IsRecord", "isRecord", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isRecord(x)`

Tests whether `x` is a record.

## `isRecordP("IsRecord", "isRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isRecord(x, ?b)`

Procedure version of `isRecord(x)`.

## `log("Log", "log", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`log(f1)`

Returns the logarithm to the base <i>e</i> of `f1`.

## `logP("Log", "log", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`log(f1, ?f2)`

Procedure version of `log(f1)`.

## `max("Max", "max", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false)), null, null),`

`max(afi1, afi2)`

Returns the maximum of `afi1` and `afi2`.

## `maxP("Max", "max", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false), new Variable(-1, "afi3", true, false)), null, null),`

`max(x, afi1, afi2, ?afi3)`

Procedure version of `max(afi1, afi2)`.

## `min("Min", "min", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false)), null, null),`

`min(afi1, afi2)`

Returns the minimum of `afi1` and `afi2`.

## `minP("Min", "min", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false), new Variable(-1, "afi3", true, false)), null, null),`

`min(x, afi1, afi2, ?afi3)`

Procedure version of `min(afi1, afi2)`.

## `NEWCELL("NewCell", "newCell", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "InitValue", true, false)), null, null),`


## `not("Not", "not", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "b1", true, false)), null, null),`

`not(b1)`

Returns the negation of truth value `b1`.

## `notP("Not", "not", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false)), null, null),`

`not(b1, ?b2)`

Procedure version of `not(b1)`.

## `or("Or", "or", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false)), null, null),`

`or(b1, b2)`

Returns the disjunction of truth value `b1` and `b2`. Note that `or()` is different from the operation available via the keyword `||` in that it always evaluates its second argument. For instance, `true || p` reduces without reducing application of `p`, whereas reduction of `or(true, p)` always applies `p`.

## `orP("Or", "or", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false), new Variable(-1, "b3", true, false)), null, null),`

`or(b1, b2, ?b3)`

Procedure version of `or(b1, b2)`.

## `pow("Pow", "pow", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "fi1", true, false), new Variable(-1, "fi2", true, false)), null, null),`

`pow(fi1, fi2)`

Returns `fi1` to the power of `fi2`.

## `powP("Pow", "pow", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fi1", true, false), new Variable(-1, "fi2", true, false), new Variable(-1, "fi3", true, false)), null, null),`

`pow(fi1, fi2, ?fi3)`

Procedure version of `pow(fi1, fi2)`.

## `round("Round", "round", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`round(f1)`

Returns the integral value closest to `f1`. If there are two candidates, `f1` is rounded to the closest even integral value, e. g., `round(1.5)` and `round(2.5)` both return <i>2.0</i>.

## `roundP("Round", "round", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`round(f1, ?f2)`

Procedure version of `round(f1)`.

## `sine("Sin", "sin", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`sin(f1)`

Returns the sine of `f1`.

## `sineP("Sine", "sine", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`sine(f1, ?f2)`

Procedure version of `sine(f1)`.

## `sinh("Float.sinh", "sinh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`sinh(f1)`

Returns the hyperbolic sine of `f1`.

## `sinhP("Float.sinh", "sinh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`sinh(f1, ?f2)`

Procedure version of `sinh(f1)`.

## `sqrt("Sqrt", "sqrt", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`sqrt(f1)`

Returns the square root of `f1`.

## `sqrtP("Sqrt", "sqrt", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`sqrt(f1, ?f2)`

Procedure version of `sqrt(f1)`.


`status(x)`

Returns status and type information on `x`.

If `x` is free, the atom `free` is returned. If `x` is a future, the atom `future` is returned. If `x` is a failed value, the atom `failed` is returned. If `x` is kinded, the tuple `kinded(y)` is returned, where `y` is bound to either the atoms `int`, `fset` or `record`, depending on the type of `x`. If `x` is determined, the tuple `det(Y)` is returned, where `y` is bound to the atom as returned by `type(x)`.


`status(x, ?t)`

Procedure version of `status(x)`.

## `tan("Tan", "tan", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`tan(f1)`

Returns the tangent of `f1`.

## `tanP("Tan", "tan", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`tan(f1, ?f2)`

Procedure version of `tan(f1)`.

## `tanh("Float.tanh", "tanh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`tanh(f1)`

Returns the hyperbolic tangent of `f1`.

## `tanhP("Float.tanh", "tanh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`tanh(f1, ?f2)`

Procedure version of `tanh(f1)`.

## `toVirtualString("Value.toVirtualString", "toVirtualString", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "depthI", true, false), new Variable(-1, "widthI", true, false)), null, null),`

`toVirtualString(x, depthI, widthI)`

Returns a virtual string describing the value of `x`. Note that this does not block on `x`. The value of `depthI` and `widthI` are used to limit output of records in depth and width respectively.

## `toVirtualStringP("Value.toVirtualString", "toVirtualString", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "depthI", true, false), new Variable(-1, "widthI", true, false), new Variable(-1, "vs", true, false)), null, null),`

`toVirtualString(x, depthI, widthI, ?vs)`

Procedure version of `teVirtualString(x, depthI, widthI)`

## `type("Value.type", "type", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`type(x)`

Returns an atom describing the type of `x`. If `x` is of one of the standard primary types, then the returned value is constrained to the most specific of int, float, record, tuple, atom, name, procedure, cell, byteString, bitString, chunk, array, dictionary, bitArray, 'class', object, 'lock', port, space, or 'thread'. If any other atom is returned, this means that `x` is of no standard primary type, but an implementation-dependent extension.

## `typeP("Value.type", "type", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "a", true, false)), null, null),`

`status(x, ?a)`

Procedure version of `type(x)`.

## `public String ozString()`

Returns the name of this function, procedure, class or functor in Oz.

* **Returns:** the name as known by Mozart

## `public String nozString()`

Returns the name of this function, procedure, class or functor in NewOz.

* **Returns:** the name as known by Nozc

## `public BuiltInType type()`

* **Returns:** the {@link BuiltInType} type of this BuiltIn

## `public List<Pattern> args()`

* **Returns:** the list of arguments of this built-in if this is a function/procedure, or null

  otherwise.

## `public List<ClassDescriptor> descrs()`

* **Returns:** the descriptors of this built-in if it is a class, or null otherwise.

## `public List<MethodDef> meths()`

* **Returns:** the method definitions of this built-in if it is a class, or null otherwise.
