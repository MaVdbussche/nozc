# Documentation

## `public enum BuiltIns`

An enum containing all the functions, procedures, classes and functors known by the compiler. They can be considered as built into the language itself, meaning they are available to be referenced from any scope in any NewOz program.

## `abs("Abs", "abs", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "fi1", true, false)), null, null),`

`abs(+fi1)`

Returns the absolute value of `fi1`.

## `absP("Abs", "abs", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fi1", true, false), new Variable(-1, "fi2", true, false)), null, null),`

`abs(+fi1, ?fi2)`

Procedure version of `abs(fi1)`.

## `acos("Acos", "acos", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`acos(+f1)`

Returns the arc cosine of `f1`.

## `acosP("Acos", "acos", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`acos(+f1, ?f2)`

Procedure version of `acos(f1)`.

## `acosh("Float.acosh", "acosh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`acosh(+f1)`

Returns the inverse hyperbolic cosine of `f1`.

## `acoshP("Float.acosh", "acosh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`acosh(+f1, ?f2)`

Procedure version of `acosh(f1)`.

## `adjoin("Adjoin", "adjoin", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "r2", true, false)), null, null),`

`adjoin(+r1, +r2)`

Returns the result of adjoining `r2` to `r1`. Note that features and label in `r2` take precedence over `r1`.

For example, adjoin('a(a, b, c:1), 'b(4, b:3, c:2)) yields the record 'b(4, b, b:3, c:2) as output..

## `adjoinP("Adjoin", "adjoin", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "r2", true, false), new Variable(-1, "r3", true, false)), null, null),`

`adjoin(+r1, +r2, ?r3)`

Procedure version of `adjoin(r1, r2)`.

## `adjoinAt("AdjoinAt", "adjoinAt", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "li", true, false), new Variable(-1, "x", true, false)), null, null),`

`adjoinAt(+r1, +li, x)`

Returns the result of adjoining the field `x` to `r1` at feature `li`.

For example, adjoinAt('a(a, c:1), 2, b) yields the record 'a(a, b, c:1) as output, whereas adjoinAt('a(a, c:1), c, b) yields 'a(a, c:b) as output.

## `adjoinAtP("AdjoinAt", "adjoinAt", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "li", true, false), new Variable(-1, "x", true, false), new Variable(-1, "r2", true, false)), null, null),`

`adjoinAt(+r1, +li, x, ?r2)`

Procedure version of `adjoinAt(r1, li, x)`.

## `adjoinList("AdjoinList", "adjoinList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "ts", true, false)), null, null),`

`adjoinList(+r1, +ts)`

Returns the result of adjoining to `r1` all entries of `ts`, a finite list of pairs whose first components are literals or integers, representing features. Features further to the right overwrite features further to the left.

For example, adjoinList('a(b:1, c:2), [(d#3), (c#4), (d#5)]) yields 'a(b:1, c:4, d:5) as output.

## `adjoinListP("AdjoinList", "adjoinList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "ts", true, false), new Variable(-1, "r2", true, false)), null, null),`

`adjoinList(+r1, +ts, ?r2)`

Procedure version of `adjoinList(r1, ts)`.

## `allList("List.all", "allList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false)), null, null),`

`allList(+xs, +f)`

Tests whether the unary boolean function `f` yields `true` when applied to all elements of `xs`. Stops at the first element for which `f` yields `false`.

## `allListP("List.all", "allList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "b", true, false)), null, null),`

`allList(+xs, +f, ?b)`

Procedure version of `allList(xs, f)`.

## `allRecord("Record.all", "allRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false)), null, null),`

`allRecord(+r, +f)`

Tests whether the unary boolean function `f` yields `true` when applied to all fields of `r`. Stops at the first field for which `f` yields `false`. The fields are tested in the order given by `arity()` (which see).

## `allRecordP("Record.all", "allRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false), new Variable(-1, "b", true, false)), null, null),`

`allRecord(+r, +f, ?b)`

Procedure version of `all(r, f)`.

## `and("And", "and", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false)), null, null),`

`and(+b1, +b2)`

Returns the conjunction of truth value `b1` and `b2`. Note that `and()` is different from the operation available via the keyword `&amp;&amp;` in that it always evaluates its second argument. For instance, `false &amp;&amp; p` reduces without reducing application of `p`, whereas reduction of <code>and(false, p)</code> always applies `p`.

## `andP("And", "and", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false), new Variable(-1, "b3", true, false)), null, null),`

`and(+b1, +b2, ?b3)`

Procedure version of `and(b1, b2)`.

## `appendList("List.append", "appendList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "y", true, false)), null, null),`

`appendList(+xs, y)`

Returns the result of appending `y` to `xs`. `Y` does not need to be a list. However, `zs` is only a proper list, if `y` is also a proper list.

For example, `appendList((1::2::nil), (3::4::nil))` returns the list `(1::2::3::4::nil)`, whereas `appendList((1::2::nil), (3::4))` returns `(1::2::(3::4))`, which is not a proper list (because (3::4) is not a proper list and by the recursive definition of lists).

## `appendListP("List.append", "appendList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "y", true, false), new Variable(-1, "zs", true, false)), null, null),`

`appendList(+xs, y, ?zs)`

Procedure version of `appendList(xs, y)`.

## `appendTuple("Tuple.append", "appendTuple", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "t1", true, false), new Variable(-1, "t2", true, false)), null, null),`

`appendTuple(+t1, +t2)`

Returns a tuple `t3` with the same label as `t2`. Given that `t1` has width <i>i</i> and `t2` has width <i>j</i>, `t3` will have width <i>i+j</i>, and the first <i>i</i> fields of `t3` will be the same as the fields of `t1` in their original order, and the fields <i>i+1</i> through <i>i+j</i> will be the same as the fields of `t2` in their original order.

## `appendTupleP("Tuple.append", "appendTuple", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "t1", true, false), new Variable(-1, "t2", true, false), new Variable(-1, "t3", true, false)), null, null),`

`appendTuple(+t1, +t2, ?t3)`

Procedure version of `appendTuple(t1, t2)`.

## `apply("Procedure.apply", "apply", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "p", true, false), new Variable(-1, "xs", true, false)), null, null),`

`apply(+p, +xs)`

Applies the procedure `p` to the arguments given by the elements of the list `xs`, provided that `procArity(p) == length(xs)`. `p` has to be a procedure (i.e., not a function).

## `arity("Arity", "arity", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "r", true, false)), null, null),`

`arity(+r)`

Returns the arity of `r`. The arity of `r` is the list of its features, beginning with all integer features in ascending order, followed by the literal features.

For example, arity('a(nil, 7, c:1, b:c)) yields [1, 2, b, c] as output.

## `arityP("Arity", "arity", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "lis", true, false)), null, null),`

`arity(+r, ?lis)`

Procedure version of `arity(r)`.

## `arrayToRecord("Array.toRecord", "arrayToRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "a", true, false)), null, null),`

`arrayToRecord(+l, +a)`

Returns a new record `r` with label `l` that contains as features the integers between `low(a)` and `high(a)` and with the corresponding fields.

## `arrayToRecordP("Array.toRecord", "arrayToRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "a", true, false), new Variable(-1, "r", true, false)), null, null),`

`arrayToRecord(+l, +a, ?r)`

Procedure version of `arrayToRecord(l, a)`.

## `asin("Asin", "asin", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`asin(+f1)`

Returns the arc sine of `f1`.

## `asinP("Asin", "asin", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`asin(+f1, ?f2)`

Procedure version of `asin(f1)`.

## `asinh("Float.asinh", "asinh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`asinh(+f1)`

Returns the inverse hyperbolic sine of `f1`.

## `asinhP("Float.asinh", "asinh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`asinh(+f1, ?f2)`

Procedure version of `asinh(f1)`.

## `atan("Atan", "atan", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`atan(+f1)`

Returns the arc tangent of `f1`.

## `atanP("Atan", "atan", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`atan(+f1, ?f2)`

Procedure version of `atan(f1)`.

## `atanh("Float.atanh", "atanh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`atanh(+f1)`

Returns the inverse hyperbolic tangent of `f1`.

## `atanhP("Float.atanh", "atanh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`atanh(+f1, ?f2)`

Procedure version of `atanh(f1)`.

## `atan2("Atan2", "atan2", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`atan2(+f1, +f2)`

Returns the principal value of the arc tangent of `f1` <i>/</i> `f2`, using the signs of both arguments to determine the quadrant of the return value. An error exception may (but needs not) be raised if both arguments are <i>0</i>.

## `atan2P("Atan2", "atan2", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false), new Variable(-1, "f3", true, false)), null, null),`

`atan2(+f1, +f2, ?f3)`

Procedure version of `atan2(f1, f2)`.

## `BROWSE("Browse", "browse", BuiltInType.PROCEDURE, Collections.singletonList(new Variable(-1, "ToPrint", true, false)), null, null),`

`browse(X)`

TODO Displays `X` in the Mozart Browser

## `byNeed("ByNeed", "byNeed", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "fp", true, false)), null, null),`

`byNeed(+fp)`

Concurrently evaluates `fp(x)` as soon as `x`, the returned value, becomes needed. It can be defined as follows : <pre><code> defproc byNeed(p, x) { thread {waitNeeded(x) p(x)} } </code></pre>

## `byNeedP("ByNeed", "byNeed", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fp", true, false), new Variable(-1, "x", true, false)), null, null),`

`byNeed(+fp, ?x)`

Procedure version of `byNeed(fp)`

## `byNeedFuture("ByNeedFuture", "byNeedFuture", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "p", true, false)), null, null),`

`byNeedFuture(p)`

Creates a by-need computation that evaluates `fp`, and then returns a future `x` of its result. If the call to `fp` raises an expression `e`, then `x` is bound to a failed value (see `failed()`) that encapsulates `e`. It can be defined as follows : <pre><code> defproc byNeedFuture(fp) { future( byNeed( fun $() {try {fp()} catch {case e => failed(e)}} ) ) } </code></pre>

## `byNeedFutureP("ByNeedFuture", "byNeedFuture", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fp", true, false), new Variable(-1, "x", true, false)), null, null),`

`byNeedFuture(fp, ?x)`

Procedure version of `byNeedFuture(fp)`

## `ceil("Ceil", "ceil", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`ceil(+f1)`

Returns the ceiling of `f1` (rounding towards positive infinity).

## `ceilP("Ceil", "ceil", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`ceil(+f1, ?f2)`

Procedure version of `ceil(f1)`.

## `cloneArray("Array.clone", "cloneArray", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "a", true, false)), null, null),`

`cloneArray(+a)`

Returns a new array with the same bounds and contents as `a`.

## `cloneArrayP("Array.clone", "cloneArray", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "a1", true, false), new Variable(-1, "a2", true, false)), null, null),`

`cloneArray(+a1, ?a2)`

Procedure version of `cloneArray(a1)`.

## `cloneDictionary("Dictionary.clone", "cloneDictionary", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "dictionary", true, false)), null, null),`

`cloneDictionary(+dictionary)`

Returns a new dictionary containing the currently valid keys and corresponding items of `dictionary`.

## `cloneDictionaryP("Dictionary.clone", "cloneDictionary", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary1", true, false), new Variable(-1, "dictionary2", true, false)), null, null),`

`cloneDictionary(+dictionary1, ?dictionary2)`

Procedure version of `cloneDictionary(dictionary1)`.

## `cloneRecord("Record.clone", "cloneRecord", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "r1", true, false)), null, null),`

`cloneRecord(+r1)`

Returns a new record with the same label (name) and features as `r1` and fresh variables at every field.

## `cloneRecordP("Record.clone", "cloneRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "r2", true, false)), null, null),`

`cloneRecord(+r1, ?r2)`

Procedure version of `cloneRecord(r1)`.

## `condExchange("Dictionary.condExchange", "condExchange", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "li", true, false), new Variable(-1, "defVal", true, false), new Variable(-1, "oldVal", true, false), new Variable(-1, "newVal", true, false)), null, null),`

`condExchange(+dictionary, +li, defVal, oldVal, newVal)`

If `li`is a valid key of `dictionary`, then returns the current value of `dictionary` under key `li` as item `oldVal`. Otherwise, returns `defVal` as item `oldVal`. Sets the value of `dictionary` under key `li` to be `newVal`.

## `condGet("Dictionary.condGet", "condGet", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "li", true, false), new Variable(-1, "defVal", true, false)), null, null),`

`condGet(+dictionary, +li, defVal)`

Returns the item `x` of `dictionary` under key `li`, if `li`is a valid key of `dictionary`. Otherwise, returns `defVal`.

## `condGetP("Dictionary.condGet", "condGet", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "li", true, false), new Variable(-1, "defVal", true, false), new Variable(-1, "x", true, false)), null, null),`

`condGet(+dictionary, +li, defVal, ?x)`

Procedure version of `condSelect(dictionary, li, defVal)`.

## `condSelect("CondSelect", "condSelect", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false), new Variable(-1, "x", true, false)), null, null),`

`condSelect(+rc, +li, x)`

Returns the field of `rc` at feature `li`, if `rc` has feature `li`. Otherwise, return `x`.

This operation if defined on records, tuples, arrays, and dictionaries.

## `condSelectP("CondSelect", "condSelect", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`condSelect(+rc, +li, x, ?y)`

Procedure version of `condSelect(rc, li, x)`.

## `cos("Cos", "cos", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`cos(+f1)`

Returns the cosine of `f1`.

## `cosP("Cos", "cos", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`cos(+f1, ?f2)`

Procedure version of `cos(f1)`.

## `cosh("Float.cosh", "cosh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`cosh(+f1)`

Returns the hyperbolic cosine of `f1`.

## `coshP("Float.cosh", "cosh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`cosh(+f1, ?f2)`

Procedure version of `cosh(f1)`.

## `dictionaryToRecord("Dictionary.toRecord", "dictionaryToRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "dictionary", true, false)), null, null),`

`dictionaryToRecord(+l, +dictionary)`

Returns a new record `r` with label `l` whose features and their fields correspond to the keys and their entries in `dictionary`.

## `dictionaryToRecordP("Dictionary.toRecord", "dictionaryToRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "dictionary", true, false), new Variable(-1, "r", true, false)), null, null),`

`dictionaryToRecord(+l, +dictionary, ?r)`

Procedure version of `dictionaryToRecord(l, dictionary)`.

## `drop("List.drop", "drop", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false)), null, null),`

`drop(+xs, +i)`

Returns the list `xs` with the first `i` elements removed, os `nil` if it is shorter.

## `dropP("List.drop", "drop", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false), new Variable(-1, "ys", true, false)), null, null),`

`drop(+xs, +i, ?ys)`

Procedure version of `drop(xs, i)`.

## `dropWhileList("List.dropWhile", "dropWhileList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false)), null, null),`

`dropWhileList(+xs, +f)`

See takeDropWhileList()

## `dropWhileListP("List.dropWhile", "dropWhileList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "ys", true, false)), null, null),`

`dropWhileList(+xs, +f, ?ys)`

See takeDropWhileList()

## `dropWhileRecord("Record.dropWhile", "dropWhileRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false)), null, null),`

`dropWhileRecord(+r1, +f)`

See takeDropWhileRecord()

## `dropWhileRecordP("Record.dropWhile", "dropWhileRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false), new Variable(-1, "r2", true, false)), null, null),`

`dropWhileRecord(+r1, +f, ?r2)`

See takeDropWhileRecord()

## `entries("Dictionary.entries", "entries", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "dictionary", true, false)), null, null),`

`entries(+dictionary)`

Returns the list of current entries of `dictionary`. An entry is a pair `(li#x)`, where `li` is a valid key of `dictionary` and `x` the corresponding item.

## `entriesP("Dictionary.entries", "entries", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "ts", true, false)), null, null),`

`entries(+dictionary, ?ts)`

Procedure version of `entries(dictionary)`.

## `exchangeArray("Array.exchange", "exchangeArray", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "array", true, false), new Variable(-1, "i", true, false), new Variable(-1, "oldVal", true, false), new Variable(-1, "newVal", true, false)), null, null),`

`exchangeArray(+array, +i, oldVal, newVal)`

Returns the current value of `array` under key `i` as item `oldVal` and updates the value of `array` under key `i` to be `newVal`.

## `exchangeDictionary("Dictionary.exchange", "exchangeDictionary", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "li", true, false), new Variable(-1, "oldVal", true, false), new Variable(-1, "newVal", true, false)), null, null),`

`exchangeDictionary(+dictionary, +li, oldVal, newVal)`

Returns the current value of `dictionary` under key `li` as item `oldVal` and updates the value of `dictionary` under key `li` to be `newVal`.

## `exp("Exp", "exp", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`exp(+f1)`

Returns `f1` to the power of <i>e</i>.

## `expP("Exp", "exp", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`exp(+f1, ?f2)`

Procedure version of `exp(f1)`.

## `failed("Value.failed", "failed", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "e", true, false)), null, null),`

`failed(e)`

Creates a failed value `x` encapsulating exception `e`. Whenever a statement is needs `x`, in particular, whenever a thread synchronises on `x`, exception term `e` is raised. This is convenient in concurrent designs; if a concurrent generator encounters a problem while computing a value, it may catch the corresponding exception, package it as a failed value and return the latter instead. Thus eac consumer will be able to synchronously handle the exception when it attempts to use the "failed" value.

## `failedP("Value.failed", "failed", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "e", true, false), new Variable(-1, "x", true, false)), null, null),`

`failed(e, ?x)`

Procedure version of `failed(e)`

## `filterList("List.filter", "filterList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false)), null, null),`

`filterList(+xs, +f)`

Returns the list which contains all the elements of `xs`, for which the unary boolean function `f` yields `true`. The ordering is preserved.

## `filterListP("List.filter", "filterList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "ys", true, false)), null, null),`

`filterList(+xs, +f, ?ys)`

Procedure version of `filterList(xs, f)`.

## `filterRecord("Record.filter", "filterRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false)), null, null),`

`filterRecord(+r, +f)`

Returns the record which contains all the features and fields of the record `r`, for which the unary boolean function `f` applied to the fields yields `true`.

## `filterRecordP("Record.filter", "filterRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false), new Variable(-1, "r2", true, false)), null, null),`

`filterRecord(+r1, +f, ?r2)`

Procedure version of `filterRecord(r1, f)`.

## `flatten("Flatten", "flatten", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "xs", true, false)), null, null),`

`flatten(+xs)`

Returns the list obtained by flattening `xs`, i.e. by concatenating all sub-lists of `xs` recursively.

## `flattenP("Flatten", "flatten", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false)), null, null),`

`flatten(+xs, ?ys)`

Procedure version of `flatten(xs)`.

## `floatToInt("FloatToInt", "floatToInt", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "f", true, false)), null, null),`

`floatToInt(+f)`

Returns the integer closest to float `f`. If there are two candidates, `f` is rounded to the closest even integer, e. g., `floatToInt(1.5)` and `floatToInt(2.5)` both return <i>2</i>.

<b><i>Attention</i></b>

In the current Oz implementation, the value is converted through a signed 32-bit integer, so this function is bogus when applied to large floats.

## `floatToIntP("FloatToInt", "floatToInt", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f", true, false), new Variable(-1, "i", true, false)), null, null),`

`floatToInt(+f, ?i)`

Procedure version of `floatToInt(f)`.

## `floatToString("FloatToString", "floatToString", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "f", true, false)), null, null),`

`floatToString(+f)`

Returns the string describing the float `f` in Oz concrete syntax.

## `floatToStringP("FloatToString", "floatToString", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f", true, false), new Variable(-1, "s", true, false)), null, null),`

`floatToString(+f, ?s)`

Procedure version of `floatToString(f)`.

## `floor("Floor", "floor", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`floor(+f1)`

Returns the floor of `f1` (rounding towards negative infinity).

## `floorP("Floor", "floor", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`floor(+f1, ?f2)`

Procedure version of `floor(f1)`.

## `foldListL("List.foldL", "foldListL", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "X", true, false)), null, null),`

`foldListL(+xs, +f, x)`

Used for folding the elements of `xs` by applying the binary function `f`.

Applying the left folding function `foldListL([X1,... Xn], f, x)` reduces to `f(...f(f(x, X1), X2), Xn)`. The first actual argument of `f` is the accumulator in which the result of the previous application, or the start value `z`, is passed. The second actual argument is an element of `xs`.

Besides the left folding function there exists a right folding variant. The application `foldListR([X1,... Xn], f, x)` reduces to `f(X1, f(X2, ...f(Xn, x)))`. The first actual argument of `f` is an element of `xs`; the second actual argument is the accumulator in which the result of the previous application or the start value `x` is passed.

For example, `foldListL([1,2,3], fun $(xr, x){(x::xr)}, nil)` yields the output `(3::2::1::nil)`, whereas `foldListR([b,c,d], fun $(x, xr){(x#r)}, a)` yields the output `(b#(c#(d#a)))`.

## `foldListLP("List.foldL", "foldListL", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`foldListL(+xs, +f, x, ?y)`

Procedure version of `foldListL(xs, f, x)`.

## `foldListR("List.foldR", "foldListR", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "X", true, false)), null, null),`

`foldListR(+xs, +f, x)`

See foldListL().

## `foldListRP("List.foldR", "foldListR", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`foldListR(+xs, +f, x, ?y)`

Procedure version of `foldListR(xs, f, x)`.

## `foldRecordL("Record.foldL", "foldRecordL", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false), new Variable(-1, "X", true, false)), null, null),`

`foldRecordL(+r, +f, x)`

Used for folding the fields of `r` by applying the binary function `f`.

Supposing that `r` has the arity [F1, ... Fn], applying the left folding function `foldRecordL(r, f, x)` reduces to `f(...f(f(x, r.F1), r.F2), r.Fn)`. The first actual argument of `f` is the accumulator in which the result of the previous application, or the start value of `x`, is passed. The second actual argument is the field of `r`.

Besides the left folding function there exists a right folding variant. The application `foldRecordR(r, f, x)` reduces to `f(r.F1, f(r.F2, ...f(r.Fn, x)))`. The first actual argument of `f` is a field of `r`; the second actual argument is the accumulator in which the result of the previous application or the start value `x` is passed.

For example, `foldRecordL('a(3, a:7, b:4), fun $(xr, x){(x::xr)}, nil)` yields the output <code>[4, 7, 3]</code>, whereas `foldRecordR('a(3, a:7, b:4), fun $(x, xr){(x::xr)}, nil)` yields the output `[3, 7, 4]`.

## `foldRecordLP("Record.foldL", "foldRecordL", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`foldRecordL(+r, +f, x, ?y)`

Procedure version of `foldRecordL(r, f, x)`.

## `foldRecordR("Record.foldR", "foldRecordR", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false), new Variable(-1, "X", true, false)), null, null),`

`foldRecordR(+r, +f, x)`

See foldRecordL().

## `foldRecordRP("Record.foldR", "foldRecordR", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`foldRecordR(+r, +f, x, ?y)`

Procedure version of `foldRecordR(r, f, x)`.

## `foldTailL("List.foldLTail", "foldTailL", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "X", true, false)), null, null),`

`foldTailL(+xs, +f, x)`

Used for folding all non-`nil` tails of `xs` by applying the binary function `f`, i.e. application of the left folding function <code>foldTailL([X1,... Xn], f, z)</code> reduces to `f(...f(f(z, [X1,...XN]), [X2,...Xn]), ...Xn])`.

The right folding function is analogous.

## `foldTailLP("List.foldLTail", "foldTailL", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`foldTailL(+xs, +f, x, ?y)`

Procedure version of `foldTailL(xs, f, x)`.

## `foldTailR("List.foldRTail", "foldTailR", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "X", true, false)), null, null),`

`foldTailR(+xs, +f, x)`

See foldTailL().

## `foldTailRP("List.foldRTail", "foldTailR", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`foldTailR(+xs, +f, x, ?y)`

Procedure version of `foldTailR(xs, f, x)`.

## `forAllList("List.forAll", "forAllList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "p", true, false)), null, null),`

`forAllList(+xs, +p)`

Applies the unary procedure `p` to each element of `xs`, i.e., the application `forAllList([X1,...Xn], p)` reduces to the sequence of statements `p(X1) ... p(Xn)`.

## `forAllRecord("Record.forAll", "forAllRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "p", true, false)), null, null),`

`forAllRecord(+r, +p)`

Applies the unary procedure `p` to each field of `r`. Suppose that the arity of `r` is [F1, ... Fn]. The application `forAll(r, p)` reduces to the sequence of statements `p(r.F1) ... p(r.Fn)`.

## `forAllTail("List.forAllTail", "forAllTail", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "p", true, false)), null, null),`

`forAllTail(+xs, +p)`

Applies the unary procedure `p` to each non-`nil` tail of `xs`, i.e., the application `forAllTail([X1,...Xn], p)` reduces to the sequence of statements `p([X1,...Xn]) p([X2,...Xn])... p([Xn])`.

## `future("Value.'!!'", "future", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "x", true, false)), null, null),`

`future(x)`

Returns a future `y` for `x`, i.e. a read-only placeholder for `x`. If `y` becomes needed, `x` is made needed too.

## `futureP("Value.'!!'", "future", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`future(x, ?y)`

Procedure version of `future(x)`.

## `getArray("Get", "getArray", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "array", true, false), new Variable(-1, "i", true, false)), null, null),`

`getArray(+array, +i)`

Returns the item of `array` under key `i`.

## `getArrayP("Get", "getArray", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "array", true, false), new Variable(-1, "i", true, false), new Variable(-1, "x", true, false)), null, null),`

`getArray(+array, +i, ?x)`

Procedure version of `getArray(array, i)`.

## `getAttr("Class.getAttr", "getAttr", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "k", true, false), new Variable(-1, "li", true, false)), null, null),`

`getAttr(+k, +li)`

Returns the initial value `x` for attribute `li` as defined by class `k`.

For example, the call `getAttr(class $ attr a=4 {...}, a)` returns `4`.

## `getAttrP("Class.getAttr", "getAttr", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "k", true, false), new Variable(-1, "li", true, false), new Variable(-1, "x", true, false)), null, null),`

`getAttr(+k, +li, ?x)`

Procedure version of `getAttr(k, li)`.

## `getDictionary("Dictionary.get", "getDictionary", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "i", true, false)), null, null),`

`getDictionary(+dictionary, +i)`

Returns the item of `dictionary` under key `i`.

## `getDictionaryP("Dictionary.get", "getDictionary", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "i", true, false), new Variable(-1, "x", true, false)), null, null),`

`getDictionary(+dictionary, +i, ?x)`

Procedure version of `getDictionary(dictionary, i)`.

## `hasFeature("HasFeature", "hasFeature", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false)), null, null),`

`hasFeature(+rc, +li)`

Tests whether `rc` has feature `li`.

This operation if defined on records, tuples, arrays, and dictionaries.

## `hasFeatureP("HasFeature", "hasFeature", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "rc", true, false), new Variable(-1, "li", true, false), new Variable(-1, "b", true, false)), null, null),`

`hasFeature(+rc, +li, ?b)`

Procedure version of `hasFeature(rc, li)`.

## `high("Array.high", "high", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "array", true, false)), null, null),`

`high(+array)`

Returns the upper bound of the key range of `array`.

## `highP("Array.high", "high", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "array", true, false), new Variable(-1, "highI", true, false)), null, null),`

`high(+array, ?highI)`

Procedure version of `high(array)`.

## `intToFloat("IntToFloat", "intToFloat", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`intToFloat(+i)`

Returns the float closest to the integer `i`.

## `intToFloatP("IntToFloat", "intToFloat", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "f", true, false)), null, null),`

`intToFloat(+i, ?f)`

Procedure version of `intToFloat(i)`.

## `intToString("IntToString", "intToString", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`intToString(+i)`

Returns the string describing the integer `i` in Oz concrete syntax.

## `intToStringP("IntToString", "intToString", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "s", true, false)), null, null),`

`intToString(+i, ?s)`

Procedure version of `intToString(i)`.

## `isArray("IsArray", "isArray", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isArray(+x)`

Tests whether `x` is an array.

## `isArrayP("IsArray", "isArray", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isArray(+x, ?b)`

Procedure version of `isArray(x)`.

## `isBool("IsBool", "isBool", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isBool(+x)`

Tests whether `x` is a boolean.

## `isBoolP("IsBool", "isBool", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isBool(+x, ?b)`

Procedure version of `isBool(x)`.

## `isClass("IsClass", "isClass", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isClass(+x)`

Tests whether `x` is a class.

## `isClassP("isClass", "isClass", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isClass(+x, ?b)`

Procedure version of `isClass(x)`.

## `isDictionary("IsDictionary", "isDictionary", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isDictionary(x)`

Tests whether `x` is a dictionary.

## `isDictionaryP("IsDictionary", "isDictionary", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isDictionary(x, ?b)`

Procedure version of `isDictionary(x)`.

## `isDet("IsDet", "isDet", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isDet(x)`

Tests whether `x` is determined.

## `isDetP("IsDet", "isDet", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isDet(x, ?b)`

Procedure version of `isDet(x)`.

## `isEmpty("Dictionary.isEmpty", "isEmpty", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`isEmpty(+dictionary)`

Tests whether `dictionary` contains an entry.

## `isEmptyP("Dictionary.isEmpty", "isEmpty", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "b", true, false)), null, null),`

`isEmpty(+dictionary, ?b)`

Procedure version of `isEmpty(dictionary)`.

## `isEven("IsEven", "isEven", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`isEven(+i)`

Tests whether `i` is an even integer.

## `isEvenP("IsEven", "isEven", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "b", true, false)), null, null),`

`isEven(+i, ?b)`

Procedure version of `isEven(i)`.

## `isFailed("IsFailed", "isFailed", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isFailed(x)`

Tests whether `x` is a failed value.

## `isFailedP("IsFailed", "isFailed", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isFailed(x, ?b)`

Procedure version of `isFailed(x)`.

## `isFloat("IsFloat", "isFloat", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isFloat(+x)`

Tests whether `x` is a float.

## `isFloatP("IsFloat", "isFloat", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isFloat(+x, ?b)`

Procedure version of `isFloat(x)`.

## `isFree("IsFree", "isFree", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "X", true, false)), null, null),`

`isFree(x)`

Tests whether `x` is currently free.

## `isFreeP("IsFree", "isFree", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false) ), null, null),`

`isFree(x, ?b)`

Procedure version of `isFree(x)`.

## `isFunctor("Functor.is", "isFunctor", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isFunctor(+x)`

Tests whether `x` is a functor.

## `isFunctorP("Functor.is", "isFunctor", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isFunctor(+x, ?b)`

Procedure version of `isFunctor(x)`.

## `isFuture("IsFuture", "isFuture", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isFuture(x)`

Tests whether `x` is a future.

## `isFutureP("IsFuture", "isFuture", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isFuture(x, ?b)`

Procedure version of `isFuture(x)`.

## `isInt("IsInt", "isInt", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isInt(+x)`

Tests whether `x` is an integer.

## `isIntP("IsInt", "isInt", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isInt(+x, ?b)`

Procedure version of `isInt(x)`.

## `isKinded("IsKinded", "isKinded", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isKinded(x)`

Tests whether `x` is currently kinded, i. e., is constrained but not yet determined. For example, 'foo(a:12, ...) is kinded because it is constrained to be a record, yet its arity is not yet known. Also, a non-determined finite domain variable is kinded: its type is known to be integer, but its value is not yet determined. Similarly for finite set variables.

## `isKindedP("IsKinded", "isKinded", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isKinded(x, ?b)`

Procedure version of `isKinded(x)`.

## `isList("IsList", "isList", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "l", true, false)), null, null),`

`isList(+x)`

Tests whether `x` is a list.

## `isListP("IsList", "isList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isList(+x, ?b)`

Procedure version of `isList(x)`.

## `isLock("IsLock", "isLock", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isLock(+x)`

Tests whether `x` is a lock.

## `isLockP("IsLock", "isLock", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isLock(+x, ?b)`

Procedure version of `isLock(x)`.

## `isNat("IsNat", "isNat", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`isNat(+i)`

Tests whether `i` is a natural number, i.e., an integer greater than or equal to `0`.

## `isNatP("IsNat", "isNat", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "b", true, false)), null, null),`

`isNat(+i, ?b)`

Procedure version of `isNat(i)`.

## `isNeeded("IsNeeded", "isNeeded", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isNeeded(+x)`

Tests whether `x` is needed.

## `isNeededP("IsNeeded", "isNeeded", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isNeeded(+x, ?b)`

Procedure version of `isNeeded(x)`.

## `isNumber("IsNumber", "isNumber", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isNumber(+x)`

Tests whether `x` is a number.

## `isNumberP("IsNumber", "isNumber", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isNumber(+x, ?b)`

Procedure version of `isNumber(x)`.

## `isObject("IsObject", "isObject", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isObject(+x)`

Tests whether `x` is an object (an instance of a class).

## `isObjectP("IsObject", "isObject", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isObject(+x, ?b)`

Procedure version of `isObject(x)`.

## `isOdd("IsOdd", "isOdd", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "i", true, false)), null, null),`

`isOdd(+i)`

Tests whether `i` is an odd integer.

## `isOddP("IsOdd", "isOdd", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "b", true, false)), null, null),`

`isOdd(+i, ?b)`

Procedure version of `isOdd(i)`.

## `isPort("IsPort", "isPort", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isPort(+x)`

Tests whether `x` is a port.

## `isPortP("IsPort", "isPort", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isPort(+x, ?b)`

Procedure version of `isPort(x)`.

## `isPrefix("List.isPrefix", "isPrefix", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false)), null, null),`

`isPrefix(+xs, +ys)`

Tests whether `xs` is a prefix of `ys`. Given that `xs` has length <i>i</i>, it is a prefix of `ys` if `ys` has at least length <i>i</i> and the first elements of `ys` are equal to the corresponding elements of `xs`.

## `isPrefixP("List.isPrefix", "isPrefix", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "b", true, false)), null, null),`

`isPrefix(+xs, +ys, ?b)`

Procedure version of `isPrefix(xs, ys)`.

## `isProcedure("IsProcedure", "isProcedure", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isProcedure(+x)`

Returns `true` if `x` is a procedure or a function, and false otherwise.

## `isProcedureP("IsProcedure", "isProcedure", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isProcedure(+x, ?b)`

Procedure version of `isProcedure(x)`.

## `isRecord("IsRecord", "isRecord", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isRecord(+x)`

Tests whether `x` is a record.

## `isRecordP("IsRecord", "isRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isRecord(+x, ?b)`

Procedure version of `isRecord(x)`.

## `keys("Dictionary.keys", "keys", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "dictionary", true, false)), null, null),`

`keys(+dictionary)`

Returns a list of all currently valid keys of `dictionary`.

## `keysP("Dictionary.keys", "keys", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "lis", true, false)), null, null),`

`keys(+dictionary, ?lis)`

Procedure version of `keys(dictionary)`.

## `isTuple("IsTuple", "isTuple", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`isTuple(+x)`

Tests whether `x` is a tuple.

## `isTupleP("IsTuple", "isTuple", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "b", true, false)), null, null),`

`isTuple(+x, ?b)`

Procedure version of `isTuple(x)`.

## `items("Dictionary.items", "items", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "dictionary", true, false)), null, null),`

`items(+dictionary)`

Returns the list of all items cuurently in `dictionary`.

## `itemsP("Dictionary.items", "items", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "xs", true, false)), null, null),`

`items(+dictionary, ?xs)`

Procedure version of `items(dictionary)`.

## `label("Label", "label", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "r", true, false)), null, null),`

`label(+r)`

Returns the label (name) of `r` (without the leading apostrophe).

## `labelP("Label", "label", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "l", true, false)), null, null),`

`label(+r, ?l)`

Procedure version of `label(r)`.

## `last("List.last", "last", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "xs", true, false)), null, null),`

`last(+xs)`

Returns the last element of `xs`. Raises an exception if `xs` is (nil).

## `lastP("List.last", "last", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "y", true, false)), null, null),`

`last(+xs, ?y)`

Procedure version of `last(xs)`.

## `length("Length", "length", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "xs", true, false)), null, null),`

`length(+xs)`

Returns the length of `xs`.

## `lengthP("Length", "length", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false)), null, null),`

`length(+xs, ?i)`

Procedure version of `width(r)`.

## `log("Log", "log", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`log(+f1)`

Returns the logarithm to the base <i>e</i> of `f1`.

## `logP("Log", "log", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`log(+f1, ?f2)`

Procedure version of `log(f1)`.

## `low("Array.low", "low", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "array", true, false)), null, null),`

`low(+array)`

Returns the lower bound of the key range of `array`.

## `lowP("Array.low", "low", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "array", true, false), new Variable(-1, "lowI", true, false)), null, null),`

`low(+array, ?lowI)`

Procedure version of `low(array)`.

## `makeList("MakeList", "makeList", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "i", true, false)), null, null),`

`makeList(+i)`

Returns a new list of length `i`. All elements are fresh variables.

## `makeListP("MakeList", "makeList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "xs", true, false)), null, null),`

`makeList(+i, ?xs)`

Procedure version of `makeList(i)`.

## `makeNeeded("Value.makeNeeded", "makeNeeded", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "x", true, false)), null, null),`

`makeNeeded(+x)`

Makes `x` needed. this procedure is useful for triggering by-need computations on `x` without having to suspend on `x`.

## `makeRecord("MakeRecord", "makeRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "lis", true, false)), null, null),`

`makeRecord(+l, +lis)`

Returns a new record with label (name) `l`, features `lis`, and fresh variables at every field. All elements of `lis` must be pairwise distinct, else an error exception is raised at runtime.

For example, `makeRecord(l, a, r)` waits until `l` is bound to a name literal, say `b`, and `a` is bound to a list of literals and integers, say `[c, d, 1]`, and then binds `r` to `'b(_, c:_, d:_)`.

## `makeRecordP("MakeRecord", "makeRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "lis", true, false), new Variable(-1, "r", true, false)), null, null),`

`makeRecord(+l, +lis, ?r)`

Procedure version of `makeRecord(l, lis)`.

## `makeTuple("MakeTuple", "makeTuple", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "i", true, false)), null, null),`

`makeTuple(+l, +i)`

Returns a new tuple with label `l` and fresh variables at features `1` to `i`. `I` must be non-negative, else an exception is raised.

For example, `makeTuple(l, n, t)` waits until `l` is bound to a name literal, say `b`, and `n` is bound to a number, say `3`, whereupon `t` is bound to `'b(_, _, _)`.

## `makeTupleP("MakeTuple", "makeTuple", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "i", true, false), new Variable(-1, "r", true, false)), null, null),`

`makeTuple(+l, +i, ?t)`

Procedure version of `makeTuple(l, i)`.

## `mapList("List.map", "mapList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false)), null, null),`

`mapList(+xs, +f)`

Returns a list obtained by applying the unary function `f` to each element of `xs`.

## `mapListP("List.map", "mapList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "ys", true, false)), null, null),`

`mapList(+xs, +f, ?ys)`

Procedure version of `mapList(xs, f)`.

## `mapRecord("Record.map", "mapRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false)), null, null),`

`mapRecord(+r1, +f)`

Returns a record with same label and arity as `r1`, whose fields are computed by applying the unary function `f` to all fields of `r1`.

For example, `map('a(12, b:13, c:1), fun $ (a) {(a*2)})` yields the output record <code>'a(24, b:26, c:2)</code>.

## `mapRecordP("Record.map", "mapRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false), new Variable(-1, "r2", true, false)), null, null),`

`mapRecord(+r1, +f, ?r2)`

Procedure version of `mapRecord(r1, f)`.

## `max("Max", "max", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false)), null, null),`

`max(+afi1, +afi2)`

Returns the maximum of `afi1` and `afi2`.

## `maxP("Max", "max", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false), new Variable(-1, "afi3", true, false)), null, null),`

`max(+afi1, +afi2, ?afi3)`

Procedure version of `max(afi1, afi2)`.

## `memberDictionary("Dictionary.member", "memberDictionary", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "li", true, false)), null, null),`

`memberDictionary(+dictionary, +li)`

Tests whether `li` is a valid key of `dictionary`.

## `memberDictionaryP("Dictionary.member", "memberDictionary", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "li", true, false), new Variable(-1, "b", true, false)), null, null),`

`memberDictionary(+dictionary, +li, ?b)`

Procedure version of `memberDictionary(dictionary, li)`.

## `memberList("Member", "memberList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "ys", true, false)), null, null),`

`memberList(x, +ys)`

Tests whether `x` is equal (in the sense of `==`) to some element of `ys`. Note : all other built-in functions that operate on lists take them as their first argument. `memberList()` is the only exception (for historical reasons). TODO resolve this incoherence ?

## `memberListP("Member", "memberList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "b", true, false)), null, null),`

`memberList(x, +ys, ?b)`

Procedure version of `memberList(x, ys)`.

## `merge("Merge", "merge", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "f", true, false)), null, null),`

`merge(+xs, +ys, +f)`

Returns a list `zs` obtained by merging `xs` and `ys` using the ordering described by `f`. The lists `xs` and `ys` must be sorted.

## `mergeP("Merge", "merge", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "f", true, false), new Variable(-1, "zs", true, false)), null, null),`

`merge(+xs, +ys, +f, ?zs)`

Procedure version of `merge(xs, ys, f)`.

## `min("Min", "min", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false)), null, null),`

`min(+afi1, +afi2)`

Returns the minimum of `afi1` and `afi2`.

## `minP("Min", "min", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "afi1", true, false), new Variable(-1, "afi2", true, false), new Variable(-1, "afi3", true, false)), null, null),`

`min(+afi1, +afi2, ?afi3)`

Procedure version of `min(afi1, afi2)`.

## `newObject("New", "new", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "k", true, false), new Variable(-1, "initCall", true, false)), null, null),`

`new(+k, +initCall)`

Creates a new object from class `k` with initial call to `initCall`.

## `newObjectP("New", "new", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "k", true, false), new Variable(-1, "initCall", true, false), new Variable(-1, "o", true, false)), null, null),`

`newObject(+k, +initCall, ?o)`

Procedure version of `newObject(k, initCall)`.

## `newArray("NewArray", "newArray", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "lowI", true, false), new Variable(-1, "highI", true, false), new Variable(-1, "initX", true, false)), null, null),`

`newArray(+lowI, +highI, initX)`

Returns a new array with key range from `lowI` to `highI` including both. All items are initialized to `initX`.

## `newArrayP("NewArray", "newArray", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "lowI", true, false), new Variable(-1, "highI", true, false), new Variable(-1, "initX", true, false), new Variable(-1, "array", true, false)), null, null),`

`newArrayP(+lowI, +highI, +initX, ?array)`

Procedure version of `newArray(lowI, highI, initX)`.

## `newCell("NewCell", "<hidden>", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`


## `newClass("Class.new", "newClass", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "parentKs", true, false), new Variable(-1, "attrR", true, false), new Variable(-1, "featR", true, false), new Variable(-1, "propAs", true, false)), null, null),`

`newClass(+parentKs, +attrR, +featR, +propAs)`

Creates a new class by inheriting from `ParentKs` with new attributes `attrR`. Since features and properties are not yet supported in <b>NewOz</b>, arguments `featR` and `propAs` should always be `_`. The fields with integer values in `attrR` define the free attributes. The fields with literal features defines attributes with initial values, where the features is the attribute name and the field its initial value.

For example, the statement `c = newClass([d, e], 'a(a:1, b), _ _)` is equivalent to : <pre><code> class c extends d, e attr a=1 attr b {

} </code></pre>

## `newClassP("Class.new", "newClass", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "parentKs", true, false), new Variable(-1, "attrR", true, false), new Variable(-1, "featR", true, false), new Variable(-1, "propAs", true, false), new Variable(-1, "k", true, false)), null, null),`

`newClass(+parentKs, +attrR, +featR, +propAs, ?k)`

Procedure version of `newClass(parentKs, attrR, featR, propAs)`.

## `newDictionary("NewDictionary", "newDictionary", BuiltInType.FUNCTION, Collections.emptyList(), null, null),`

`newDictionary()`

Returns a new empty dictionary.

## `newDictionaryP("NewDictionary", "newDictionary", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "dictionary", true, false)), null, null),`

`newDictionary(?dictionary)`

Procedure version of `newDictionary()`.

## `newLock("NewLock", "newLock", BuiltInType.FUNCTION, Collections.emptyList(), null, null),`

`newLock()`

Creates and returns a new lock.

## `newLockP("NewLock", "newLock", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "lock", true, false)), null, null),`

`newLock(?lock)`

Procedure version of `newLock()`.

## `newPort("NewPort", "newPort", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "xs", true, false)), null, null),`

`newPort(?xs)`

Returns a new port, together with its associated stream `xs`.

## `newPortP("NewPort", "newPort", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "port", true, false)), null, null),`

`newPort(?xs, ?port)`

Procedure version of `newPort(xs)`.

## `not("Not", "not", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "b1", true, false)), null, null),`

`not(+b1)`

Returns the negation of truth value `b1`.

## `notP("Not", "not", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false)), null, null),`

`not(+b1, ?b2)`

Procedure version of `not(b1)`.

## `nth("Nth", "nth", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false)), null, null),`

`nth(+xs, +i)`

Returns the `i`th element of `xs` (counting from 1).

## `nthP("Nth", "nth", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false), new Variable(-1, "y", true, false)), null, null),`

`nth(+xs, +i, ?y)`

Procedure version of `nth(xs, i)`.

## `numbers("List.number", "numbers", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "fromI", true, false), new Variable(-1, "toI", true, false), new Variable(-1, "stepI", true, false)), null, null),`

`numbers(+fromI, +toI, stepI)`

Returns a list with elements from `fromI` to `toI` with step `stepI`.

For example, `numbers(1, 5, 2)` returns `[1, 3, 5]`, <code>numbers(5, 1, 2)</code> returns the list (nil), and `numbers(5, 0, -2)` yields the list <code>[5, 3, 1]</code>.

## `numbersP("List.number", "numbers", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fromI", true, false), new Variable(-1, "toI", true, false), new Variable(-1, "stepI", true, false), new Variable(-1, "xs", true, false)), null, null),`

`numbers(+fromI, +toI, +stepI, ?xs)`

Procedure version of `numbers(fromI, toI, stepI)`.

## `or("Or", "or", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false)), null, null),`

`or(+b1, +b2)`

Returns the disjunction of truth value `b1` and `b2`. Note that `or()` is different from the operation available via the keyword `||` in that it always evaluates its second argument. For instance, `true || p` reduces without reducing application of `p`, whereas reduction of `or(true, p)` always applies `p`.

## `orP("Or", "or", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "b1", true, false), new Variable(-1, "b2", true, false), new Variable(-1, "b3", true, false)), null, null),`

`or(+b1, +b2, ?b3)`

Procedure version of `or(b1, b2)`.

## `partitionList("List.partition", "partitionList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "zs", true, false)), null, null),`

`partitionList(+xs, +f, ?ys, ?zs)`

`partitionList` computes a list `ys` which contains all the elements of `xs` for which the unary boolean function `f` yields `true`, and a list `zs` with the remaining fields of `xs`. The ordering is preserved.

## `partitionRecord("Record.partition", "partitionRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false), new Variable(-1, "r2", true, false), new Variable(-1, "r3", true, false)), null, null),`

`partitionRecord(+r1, +f, ?r2, ?r3)`

`partitionRecord` computes a record `r2` which contains all the features and fields of the record `r1` for which the unary boolean function `f` yields `true`, and a record `r3` with the remaining fields of `r1`.

## `pow("Pow", "pow", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "fi1", true, false), new Variable(-1, "fi2", true, false)), null, null),`

`pow(+fi1, +fi2)`

Returns `fi1` to the power of `fi2`.

## `powP("Pow", "pow", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fi1", true, false), new Variable(-1, "fi2", true, false), new Variable(-1, "fi3", true, false)), null, null),`

`pow(+fi1, +fi2, ?fi3)`

Procedure version of `pow(fi1, fi2)`.

## `procArity("Procedure.arity", "procArity", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "fp", true, false)), null, null),`

`procArity(+fp)`

Returns the procedure arity of `fp`, i.e., the number of arguments it takes.

<b>Warning : if `fp` is a function (as opposed to a procedure), the result returned by this operation is currently 1 too big. This will be fixed in a future release.</b>

## `procArityP("Procedure.arity", "procArity", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "fp", true, false), new Variable(-1, "i", true, false)), null, null),`

`procArity(+fp, ?i)`

Procedure version of `procArity(fp)`.

<b>Warning : if `fp` is a function, the result returned by this operation is currently 1 too big. This will be fixed in a future release.</b>

## `putArray("Put", "putArray", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "array", true, false), new Variable(-1, "i", true, false), new Variable(-1, "x", true, false)), null, null),`

`putArray(+array, +i, x)`

Sets the item of `array` under key `i` to `x`.

## `putDictionary("Dictionary.put", "putDictionary", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "i", true, false), new Variable(-1, "x", true, false)), null, null),`

`putDictionary(+dictionary, +li, x)`

Sets the item of `dictionary` under key `li` to `x`.

## `remove("Dictionary.remove", "remove", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "dictionary", true, false), new Variable(-1, "li", true, false)), null, null),`

`remove(+dictionary, +li)`

Removes the item under key `li` from `dictionary` if `li` is a valid key. Otherwise, does nothing.

## `removeAll("Dictionary.removeAll", "removeAll", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "dictionary", true, false)), null, null),`

`removeAll(+dictionary)`

Removes all entries currently in `dictionary`.

## `reverse("Reverse", "reverse", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "xs", true, false)), null, null),`

`reverse(+xs)`

Returns the elements of `xs` in reverse order.

## `reverseP("Reverse", "reverse", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false)), null, null),`

`reverse(xs, ?ys)`

Procedure version of `reverse(xs)`.

## `round("Round", "round", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`round(+f1)`

Returns the integral value closest to `f1`. If there are two candidates, `f1` is rounded to the closest even integral value, e. g., `round(1.5)` and `round(2.5)` both return <i>2.0</i>.

## `roundP("Round", "round", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`round(+f1, ?f2)`

Procedure version of `round(f1)`.

## `send("Send", "send", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "port", true, false), new Variable(-1, "x", true, false)), null, null),`

`send(+port, x)`

Sends `x` to the port `port`: the stream pointed to by `port` is unified with `(x::_)` (in a newly created thread), and the pointer advances to the stream's new tail.

## `sendReceive("SendRecv", "sendReceive", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "port", true, false), new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`sendReceive(+port, x, y)`

Sends the pair `(x#y)` to the port `port`: the stream pointed to by `port` is unified with `((x#y)::_)` (in a newly created thread), and the pointer advances to the stream's new tail.

The argument `x` is commonly used as message to be sent, while `y` serves as a reply to that message.

## `sine("Sin", "sin", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`sin(+f1)`

Returns the sine of `f1`.

## `sineP("Sine", "sine", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`sine(+f1, ?f2)`

Procedure version of `sine(f1)`.

## `sinh("Float.sinh", "sinh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`sinh(+f1)`

Returns the hyperbolic sine of `f1`.

## `sinhP("Float.sinh", "sinh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`sinh(+f1, ?f2)`

Procedure version of `sinh(f1)`.

## `someList("List.some", "someList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false)), null, null),`

`someList(+xs, +f)`

Tests whether the unary boolean function `f` yields `true` when applied to some elements of `xs`. Stops at the first field for which `f` yields `true`.

## `someListP("List.some", "someList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "b", true, false)), null, null),`

`someList(+xs, +f, ?b)`

Procedure version of `someList(xs, f)`.

## `someRecord("Record.some", "someRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false)), null, null),`

`someRecord(+r, +f)`

Tests whether the unary boolean function `f` yields `code` when applied to some fields of `r`. Stops at the first field for which `f` yields `true`. The fields are tested in the order given by `arity()` (which see).

## `someRecordP("Record.some", "someRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "f", true, false), new Variable(-1, "b", true, false)), null, null),`

`someRecord(+r, +f, ?b)`

Procedure version of `someRecord(r, f)`.

## `sort("Sort", "sort", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false)), null, null),`

`sort(+xs, +f)`

Returns a list `ys` obtained by sorting `xs` using the comparison function `f`. `F` must be a binary function returning a boolean value. `sort()` is implemented using the mergesort algorithm.

For example,`sort([2 3 4 1 1], fun$(a, b){ (a&lt;b) })` returns the list `[1 1 2 3 4]`.

## `sortP("Sort", "sort", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "ys", true, false)), null, null),`

`sort(+xs, +f, ?ys)`

Procedure version of `sort(xs, f)`.

## `sqrt("Sqrt", "sqrt", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`sqrt(+f1)`

Returns the square root of `f1`.

## `sqrtP("Sqrt", "sqrt", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`sqrt(+f1, ?f2)`

Procedure version of `sqrt(f1)`.

## `status("Value.status", "status", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`status(x)`

Returns status and type information on `x`.

If `x` is free, the atom `free` is returned. If `x` is a future, the atom `future` is returned. If `x` is a failed value, the atom `failed` is returned. If `x` is kinded, the tuple `'kinded(y)` is returned, where `y` is bound to either the atoms `int`, `fset` or `record`, depending on the type of `x`. If `x` is determined, the tuple `det(Y)` is returned, where `y` is bound to the atom as returned by `type(x)`.

## `statusP("Value.status", "status", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "t", true, false)), null, null),`

`status(x, ?t)`

Procedure version of `status(x)`.

## `sub("List.sub", "sub", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false)), null, null),`

`sub(+xs, +ys)`

Tests whether `xs` is a sublist of `ys`, i.e., whether it contains all elements of `xs` in the same order as `xs` but not necessarily in succession.

For example, `[a, b]` is a sublist of both `[1, a, b, 2]` and <code>[1, a, 2, b, 3]</code>, but not of `[b, a]`.

## `subP("List.sub", "sub", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "b", true, false)), null, null),`

`sub(+xs, +ys, ?b)`

Procedure version of `sub(xs, ys)`.

## `subtractList("List.subtract", "subtractList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "lis", true, false)), null, null),`

`subtractList(+xs, y)`

Returns `xs` without the leftmost occurrence of `y` if there is one.

## `subtractListP("List.subtract", "subtractList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "y", true, false), new Variable(-1, "zs", true, false)), null, null),`

`subtractList(+xs, y, ?zs)`

Procedure version of `subtractList(xs, y)`.

## `subtractListRecord("Record.subtractList", "subtractListRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "lis", true, false)), null, null),`

`subtractListRecord(+r1, +lis)`

Returns record `r1` with all features in `lis` removed.

For example, `subtractList('f(jim:1, jack:2, jesse:4), [jesse, jim])` returns the record `'f(jack:2)`.

## `subtractListRecordP("Record.subtractList", "subtractListRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "lis", true, false), new Variable(-1, "r2", true, false)), null, null),`

`subtractListRecord(+r1, +lis, ?r2)`

Procedure version of `subtractListRecord(r1, lis)`.

## `subtractRecord("Record.subtract", "subtractRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "li", true, false)), null, null),`

`subtractRecord(+r1, +li)`

If `r1` has feature `li`, returns record `r1` with feature `li` removed. Otherwise, return `r1`.

## `subtractRecordP("Record.subtract", "subtractRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "li", true, false), new Variable(-1, "r2", true, false)), null, null),`

`subtractRecord(+r1, +li, ?r2)`

Procedure version of `subtractRecord(r1, li)`.

## `tail("List.withTail", "tail", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "y", true, false)), null, null),`

`tail(+i, y)`

Returns a list with at least `i` elements whose rest is `y` (which doesn't need to be a list). The first `i` elements are fresh variables.

For example, `tail(2, (a::b::nil))` returns `(_::_::a::b::nil)`.

## `tailP("List.withTail", "tail", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "i", true, false), new Variable(-1, "y", true, false), new Variable(-1, "xs", true, false)), null, null),`

`tail(+i, y, ?xs)`

Procedure version of `tail(i, y)`.

## `take("List.take", "take", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false)), null, null),`

`take(+xs, +i)`

Returns the list that contains the first `i` elements of `xs`, or `xs` if it is shorter.

## `takeP("List.take", "take", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false), new Variable(-1, "ys", true, false)), null, null),`

`take(+xs, +i, ?ys)`

Procedure version of `take(xs, i)`.

## `takeDrop("List.takeDrop", "takeDrop", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "i", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "zs", true, false)), null, null),`

`takeDrop(+xs, +i, ?ys, ?zs)`

Binds `ys` to `take(xs, i)` and `zs` to <code>drop(xs, i)</code>.

## `takeDropWhileList("List.takeDropWhile", "takeDropWhileList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "zs", true, false)), null, null),`

`takeDropWhileList(+xs, +f, ?ys, ?zs)`

While `filterList` selects all elements of a list which satisfy a certain condition, `takeWhileList` selects only the starting sequence of elements which fulfill this condition. `dropWhileList` computes a list with the remaining elements. `takeDropWhileList` combines the functionality of both previous methods.

## `takeWhileList("List.takeWhile", "takeWhileList", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false)), null, null),`

`takeWhileList(+xs, +f)`

See takeDropWhileList()

## `takeWhileListP("List.takeWhile", "takeWhileList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "f", true, false), new Variable(-1, "ys", true, false)), null, null),`

`takeWhileList(+xs, +f, ?r2)`

See takeDropWhileList()

## `takeDropWhileRecord("Record.takeDropWhile", "takeDropWhileRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false), new Variable(-1, "r2", true, false), new Variable(-1, "r3", true, false)), null, null),`

`takeDropWhileRecord(+r1, +f, ?r2, ?r3)`

While `filterRecord` selects all fields and features of a record which satisfy a certain condition, `takeWhileRecord` selects only the starting sequence of features and fields which fulfill this condition. `dropWhileRecord` computes a record with the remaining features and fields. `takeDropWhileRecord` computes both records.

## `takeWhileRecord("Record.takeWhile", "takeWhileRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false)), null, null),`

`takeWhileRecord(+r1, +f)`

See takeDropWhileRecord()

## `takeWhileRecordP("Record.takeWhile", "takeWhileRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "f", true, false), new Variable(-1, "r2", true, false)), null, null),`

`takeWhileRecord(+r1, +f, ?r2)`

See takeDropWhileRecord()

## `tan("Tan", "tan", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`tan(+f1)`

Returns the tangent of `f1`.

## `tanP("Tan", "tan", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`tan(+f1, ?f2)`

Procedure version of `tan(f1)`.

## `tanh("Float.tanh", "tanh", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "f1", true, false)), null, null),`

`tanh(+f1)`

Returns the hyperbolic tangent of `f1`.

## `tanhP("Float.tanh", "tanh", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "f1", true, false), new Variable(-1, "f2", true, false)), null, null),`

`tanh(+f1, ?f2)`

Procedure version of `tanh(f1)`.

## `toArray("Tuple.toArray", "toArray", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "t", true, false)), null, null),`

`toArray(+t)`

Returns an array with bounds between `1` and width(t), where the elements of the array are the fields of `t`.

## `toArrayP("Tuple.toArray", "toArray", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "t", true, false), new Variable(-1, "a", true, false)), null, null),`

`toArray(+t, ?a)`

Procedure version of `toArray(t)`.

## `toDictionary("Record.toDictionary", "toDictionary", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "r", true, false)), null, null),`

`toDictionary(+r)`

Returns a dictionary whose keys and their entries correspond to the features and their fields of `r`.

## `toDictionaryP("Record.toDictionary", "toDictionary", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "dictionary", true, false)), null, null),`

`toDictionary(+r, ?dictionary)`

Procedure version of `toDictionary(r)`.

## `toList("Record.toList", "toList", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "r", true, false)), null, null),`

`toList(+r)`

Returns the list of all fields of `r`, in the order given by `arity()` (which see).

For example, `toList('f(a, a:2, b:3))` yields [a, 2, 3] as output.

## `toListP("Record.toList", "toList", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "xs", true, false)), null, null),`

`toList(+r, ?xs)`

Procedure version of `toList(r)`.

## `toListInd("Record.toListInd", "toListInd", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "r", true, false)), null, null),`

`toListInd(+r)`

Returns the list of pairs that contains the feature-field pairs of `r`, in the order given by `arity()` (which see).

For example, `toListInd('f(a, a:2, b:3))` yields [1#a, a#2, b#3] as output.

## `toListIndP("Record.toListInd", "toListInd", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "ts", true, false)), null, null),`

`toListInd(+r, ?ts)`

Procedure version of `toListInd(r)`.

## `listToRecord("List.toRecord", "listToRecord", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "ts", true, false)), null, null),`

`listToRecord(+l, +ts)`

Returns a new record `r` with label `l` whose fields are given by the list of pairs `ts` : for every element <i>li</i>#<i>xi</i> of `xs`, `r` has a field <i>xi</i> at feature <i>li</i>. The features in the pairs list must be pairwise distinct, else an error exception is raised.

For example, `listToRecord("f", [(a#1), (b#2), (c#3)])` yields <code>'f(a:1, b:2, c:3)</code> as output.

## `listToRecordP("List.toRecord", "listToRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "ts", true, false), new Variable(-1, "r", true, false)), null, null),`

`listToRecord(+l, +ts, ?r)`

Procedure version of `listToRecord(r)`.

## `toTuple("List.toTuple", "toTuple", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "xs", true, false)), null, null),`

`toTuple(+l, +xs)`

Returns a new tuple with label `l` that contains the elements of `xs` as fields, in the given order. As a limitation in this release of <b>NewOz</b>, `l` has to a string.

For example, `toTuple("f", (a::b::c::nil))` yields `'f(a, b, c)` as output.

## `toTupleP("List.toTuple", "toTuple", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "l", true, false), new Variable(-1, "xs", true, false), new Variable(-1, "t", true, false)), null, null),`

`toList(+l, +xs, ?t)`

Procedure version of `toTuple(l, xs)`.

## `toVirtualString("Value.toVirtualString", "toVirtualString", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "depthI", true, false), new Variable(-1, "widthI", true, false)), null, null),`

`toVirtualString(x, +depthI, +widthI)`

Returns a virtual string describing the value of `x`. Note that this does not block on `x`. The value of `depthI` and `widthI` are used to limit output of records in depth and width respectively.

## `toVirtualStringP("Value.toVirtualString", "toVirtualString", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "depthI", true, false), new Variable(-1, "widthI", true, false), new Variable(-1, "vs", true, false)), null, null),`

`toVirtualString(x, +depthI, +widthI, ?vs)`

Procedure version of `teVirtualString(x, depthI, widthI)`

## `type("Value.type", "type", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "x", true, false)), null, null),`

`type(+x)`

Returns an atom describing the type of `x`. If `x` is of one of the standard primary types, then the returned value is constrained to the most specific of int, float, record, tuple, atom, name, procedure, cell, byteString, bitString, chunk, array, dictionary, bitArray, 'class', object, 'lock', port, space, or 'thread'. If any other atom is returned, this means that `x` is of no standard primary type, but an implementation-dependent extension. TODO restrict to types existing in NewOz ?

## `typeP("Value.type", "type", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "a", true, false)), null, null),`

`type(+x, ?a)`

Procedure version of `type(x)`.

## `wait("Wait", "wait", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "x", true, false)), null, null),`

`wait(+x)`

Blocks until `x` is determined. This statement makes `x` needed, causing all by-need computations on `x` to be triggered. If `x` is or becomes bound to a failed value, then its encapsulated exception is raised.

## `waitNeeded("WaitNeeded", "waitNeeded", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "x", true, false)), null, null),`

`waitNeeded(+x)`

Blocks until `x` is needed. This operation is the by-need synchronization.

## `waitOr("WaitOr", "waitOr", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "x", true, false), new Variable(-1, "y", true, false)), null, null),`

`waitOr(x, y)`

Blocks until at least one of `x` or `y` is determined.

## `waitQuiet("Value.waitQuiet", "waitQuiet", BuiltInType.PROCEDURE, Collections.singletonList( new Variable(-1, "x", true, false)), null, null),`

`waitQuiet(+x)`

Blocks until `x` is determined or failed. Contrary to `wait()`, this procedure does not make `x` needed. Also, if `x` is or becomes bound to a failed value, no exception is raised.

## `waitRecord("Record.waitOr", "waitRecord", BuiltInType.FUNCTION, Collections.singletonList( new Variable(-1, "r1", true, false)), null, null),`

`waitRecord(+r1)`

Blocks until at least one field of `r` is determined. Returns the feature of a determined field. Raises an exception if `r` is not a proper record, that is, if `r` is a literal.

For example, `waitRecord('a(_, b:1))` returns `b`, while `waitRecord('a(2, b:_))` returns `1`, and <code>waitRecord('a(_, b:_))</code> blocks.

## `waitRecordP("Record.waitOr", "waitRecord", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "li", true, false)), null, null),`

`waitRecord(+r1, ?li)`

Procedure version of `waitRecord(r1)`.

## `width("Width", "width", BuiltInType.FUNCTION, Collections.singletonList(new Variable(-1, "r", true, false)), null, null),`

`width(+r)`

Returns the width of `r`.

## `widthP("Width", "width", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r", true, false), new Variable(-1, "i", true, false)), null, null),`

`width(+r, ?i)`

Procedure version of `width(r)`.

## `zipLists("List.zip", "zipLists", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "f", true, false)), null, null),`

`zipLists(+xs, +ys, +f)`

Returns the list of all elements `z<i>i</i>` computed by applying <code>f(x<i>i</i>, y<i>i</i>)</code>, where `x<i>i</i>` is the <i>i</i>th element of `xs` and `y<i>i</i>` is the <i>i</i>th element of `ys`. The two inputs lists must be of equal length, else an error exception is raised.

For example, `zipLists([1,6,3], [4,5,6], def $(x, y) {(x>y)}` yields the output list `(false::true::false::nil)`.

## `zipListsP("List.zip", "zipLists", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "xs", true, false), new Variable(-1, "ys", true, false), new Variable(-1, "f", true, false), new Variable(-1, "zs", true, false)), null, null),`

`zipLists(+xs, +ys, +f, ?zs)`

Procedure version of `zipLists(xs, ys, f)`.

## `zipRecords("Record.zip", "zipRecords", BuiltInType.FUNCTION, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "r2", true, false), new Variable(-1, "f", true, false)), null, null),`

`zipRecords(+r1, +r2, +f)`

Given two records `r1` and `r2` and a binary function `f`, returns a record `r3` with the same label as `r1`, and whose features are those common to `r1` and `r2`. Features appearing only in one of the records are silently dropped. Each field `x` of `r3` is computed by applying `f(r1.x, r2.x)`.

For example, `zip( 'f(jim:1, jack:2, jesse:4), 'g(jim:1, jack:b, joe:c), def $(x, y) {(x#y)}` yields the output record `'f(jim:1#a, jack:2#b)`.

## `zipRecordsP("Record.zip", "zipRecords", BuiltInType.PROCEDURE, Arrays.asList( new Variable(-1, "r1", true, false), new Variable(-1, "r2", true, false), new Variable(-1, "f", true, false), new Variable(-1, "r3", true, false)), null, null),`

`zipRecords(+r1, +r2, +f, ?r3)`

Procedure version of `zipRecords(r1, r2, f)`.

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
