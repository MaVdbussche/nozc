##NewOz Standard Library

> This file describes all the functions, procedures, classes and functors known by the compiler.
They can be considered as built into the language itself, meaning they are available to be referenced from any scope in any **NewOz** program.


`abs(+fi1)`

Returns the absolute value of `fi1`.


`abs(+fi1, ?fi2)`

Procedure version of `abs(fi1)`.


`acos(+f1)`

Returns the arc cosine of `f1`.


`acos(+f1, ?f2)`

Procedure version of `acos(f1)`.


`acosh(+f1)`

Returns the inverse hyperbolic cosine of `f1`.


`acosh(+f1, ?f2)`

Procedure version of `acosh(f1)`.


`adjoin(+r1, +r2)`

Returns the result of adjoining `r2` to `r1`. Note that features and label in `r2` take precedence over `r1`.

For example, `adjoin('a(a, b, c:1), 'b(4, b:3, c:2))` yields the record `'b(4, b, b:3, c:2)` as output.


`adjoin(+r1, +r2, ?r3)`

Procedure version of `adjoin(r1, r2)`.


`adjoinAt(+r1, +li, x)`

Returns the result of adjoining the field `x` to `r1` at feature `li`.

For example, `adjoinAt('a(a, c:1), 2, b)` yields the record `'a(a, b, c:1)` as output, whereas `adjoinAt('a(a, c:1), c, b)` yields `'a(a, c:b)` as output.


`adjoinAt(+r1, +li, x, ?r2)`

Procedure version of `adjoinAt(r1, li, x)`.


`adjoinList(+r1, +ts)`

Returns the result of adjoining to `r1` all entries of `ts`, a finite list of pairs whose first components are literals or integers, representing features. Features further to the right overwrite features further to the left.

For example, adjoinList('a(b:1, c:2), [(d#3), (c#4), (d#5)]) yields 'a(b:1, c:4, d:5) as output.


`adjoinList(+r1, +ts, ?r2)`

Procedure version of `adjoinList(r1, ts)`.


`alarm(+i)`

Returns `unit` after `i` milliseconds. This is done asynchronously in that it is evaluated in its own thread.


`alarm(+i, ?u)`

Procedure version of `alarm(i)`.


`allList(+xs, +f)`

Tests whether the unary boolean function `f` yields `true` when applied to all elements of `xs`. Stops at the first element for which `f` yields `false`.


`allList(+xs, +f, ?b)`

Procedure version of `allList(xs, f)`.


`allRecord(+r, +f)`

Tests whether the unary boolean function `f` yields `true` when applied to all fields of `r`. Stops at the first field for which `f` yields `false`. The fields are tested in the order given by `arity()` (which see).


`allRecord(+r, +f, ?b)`

Procedure version of `all(r, f)`.


`and(+b1, +b2)`

Returns the conjunction of truth value `b1` and `b2`. Note that `and()` is different from the operation available via the keyword `&&` in that it always evaluates its second argument.
For instance, `false && p` reduces without reducing application of `p`, whereas reduction of <code>and(false, p)</code> always applies `p`.


`and(+b1, +b2, ?b3)`

Procedure version of `and(b1, b2)`.


`appendList(+xs, y)`

Returns the result of appending `y` to `xs`. `Y` does not need to be a list. However, `zs` is only a proper list, if `y` is also a proper list.

For example, `appendList((1::2::nil), (3::4::nil))` returns the list `(1::2::3::4::nil)`, whereas `appendList((1::2::nil), (3::4))` returns `(1::2::(3::4))`, which is not a proper list (because `(3::4)` is not a proper list and by the recursive definition of lists).


`appendList(+xs, y, ?zs)`

Procedure version of `appendList(xs, y)`.


`appendTuple(+t1, +t2)`

Returns a tuple `t3` with the same label as `t2`. Given that `t1` has width <i>i</i> and `t2` has width <i>j</i>, `t3` will have width <i>i+j</i>, and the first <i>i</i> fields of `t3` will be the same as the fields of `t1` in their original order, and the fields <i>i+1</i> through <i>i+j</i> will be the same as the fields of `t2` in their original order.


`appendTuple(+t1, +t2, ?t3)`

Procedure version of `appendTuple(t1, t2)`.


`apply(+p, +xs)`

Applies the procedure `p` to the arguments given by the elements of the list `xs`, provided that `procArity(p) == length(xs)`. `p` has to be a procedure (i.e., not a function).


`arity(+r)`

Returns the arity of `r`. The arity of `r` is the list of its features, beginning with all integer features in ascending order, followed by the literal features.

For example, `arity('a(nil, 7, c:1, b:c))` yields `[1, 2, b, c]` as output.


`arity(+r, ?lis)`

Procedure version of `arity(r)`.


`arrayToRecord(+l, +a)`

Returns a new record `r` with label `l` that contains as features the integers between `low(a)` and `high(a)` and with the corresponding fields.


`arrayToRecord(+l, +a, ?r)`

Procedure version of `arrayToRecord(l, a)`.


`asin(+f1)`

Returns the arc sine of `f1`.


`asin(+f1, ?f2)`

Procedure version of `asin(f1)`.


`asinh(+f1)`

Returns the inverse hyperbolic sine of `f1`.


`asinh(+f1, ?f2)`

Procedure version of `asinh(f1)`.


`atan(+f1)`

Returns the arc tangent of `f1`.


`atan(+f1, ?f2)`

Procedure version of `atan(f1)`.


`atanh(+f1)`

Returns the inverse hyperbolic tangent of `f1`.


`atanh(+f1, ?f2)`

Procedure version of `atanh(f1)`.


`atan2(+f1, +f2)`

Returns the principal value of the arc tangent of `f1` <i>/</i> `f2`, using the signs of both arguments to determine the quadrant of the return value. An error exception may (but needs not) be raised if both arguments are <i>0</i>.


`atan2(+f1, +f2, ?f3)`

Procedure version of `atan2(f1, f2)`.


`byNeed(+fp)`

Concurrently evaluates `fp(x)` as soon as `x`, the returned value, becomes needed.
It can be defined as follows :
     
    defproc byNeed(p, x) {
       thread { waitNeeded(x) p(x) }
    }


`byNeed(+fp, ?x)`

Procedure version of `byNeed(fp)`


`byNeedFuture(p)`

Creates a by-need computation that evaluates `fp`, and then returns a future `x` of its result.
If the call to `fp` raises an expression `e`, then `x` is bound to a failed value (see `failed()`) that encapsulates `e`.
It can be defined as follows :

    defproc byNeedFuture(fp) {
      future(
        byNeed(
          fun $() {
            try {
                fp()
            } catch {
                case e => failed(e)
            }
          }
        )
      )
    }


`byNeedFuture(fp, ?x)`

Procedure version of `byNeedFuture(fp)`


`ceil(+f1)`

Returns the ceiling of `f1` (rounding towards positive infinity).


`ceil(+f1, ?f2)`

Procedure version of `ceil(f1)`.


`cloneArray(+a)`

Returns a new array with the same bounds and contents as `a`.


`cloneArray(+a1, ?a2)`

Procedure version of `cloneArray(a1)`.


`cloneDictionary(+dictionary)`

Returns a new dictionary containing the currently valid keys and corresponding items of `dictionary`.


`cloneDictionary(+dictionary1, ?dictionary2)`

Procedure version of `cloneDictionary(dictionary1)`.


`cloneRecord(+r1)`

Returns a new record with the same label (name) and features as `r1` and fresh variables at every field.


`cloneRecord(+r1, ?r2)`

Procedure version of `cloneRecord(r1)`.


`condExchange(+dictionary, +li, defVal, oldVal, newVal)`

If `li`is a valid key of `dictionary`, then returns the current value of `dictionary` under key `li` as item `oldVal`. Otherwise, returns `defVal` as item `oldVal`. Sets the value of `dictionary` under key `li` to be `newVal`.


`condGet(+dictionary, +li, defVal)`

Returns the item `x` of `dictionary` under key `li`, if `li`is a valid key of `dictionary`. Otherwise, returns `defVal`.


`condGet(+dictionary, +li, defVal, ?x)`

Procedure version of `condSelect(dictionary, li, defVal)`.


`condSelect(+rc, +li, x)`

Returns the field of `rc` at feature `li`, if `rc` has feature `li`. Otherwise, return `x`.

This operation if defined on records, tuples, arrays, and dictionaries.


`condSelect(+rc, +li, x, ?y)`

Procedure version of `condSelect(rc, li, x)`.


`cos(+f1)`

Returns the cosine of `f1`.


`cos(+f1, ?f2)`

Procedure version of `cos(f1)`.


`cosh(+f1)`

Returns the hyperbolic cosine of `f1`.


`cosh(+f1, ?f2)`

Procedure version of `cosh(f1)`.


`delay(+i)`

reduces ro `skip` after `i` milliseconds. Whenever `i &lt;= 0`, `delay(i)`reduces immediately.


`dictionaryToRecord(+l, +dictionary)`

Returns a new record `r` with label `l` whose features and their fields correspond to the keys and their entries in `dictionary`.


`dictionaryToRecord(+l, +dictionary, ?r)`

Procedure version of `dictionaryToRecord(l, dictionary)`.


`drop(+xs, +i)`

Returns the list `xs` with the first `i` elements removed, os `nil` if it is shorter.


`drop(+xs, +i, ?ys)`

Procedure version of `drop(xs, i)`.


`dropWhileList(+xs, +f)`

See takeDropWhileList()


`dropWhileList(+xs, +f, ?ys)`

See takeDropWhileList()


`dropWhileRecord(+r1, +f)`

See takeDropWhileRecord()


`dropWhileRecord(+r1, +f, ?r2)`

See takeDropWhileRecord()


`entries(+dictionary)`

Returns the list of current entries of `dictionary`. An entry is a pair `(li#x)`, where `li` is a valid key of `dictionary` and `x` the corresponding item.


`entries(+dictionary, ?ts)`

Procedure version of `entries(dictionary)`.


`error(x)`

Returns an error exception record with dispatch field `x`.


`errorException(x, ?y)`

Procedure version of `errorException(x)`.


`exchangeArray(+array, +i, oldVal, newVal)`

Returns the current value of `array` under key `i` as item `oldVal` and updates the value of `array` under key `i` to be `newVal`.


`exchangeDictionary(+dictionary, +li, oldVal, newVal)`

Returns the current value of `dictionary` under key `li` as item `oldVal` and updates the value of `dictionary` under key `li` to be `newVal`.


`exp(+f1)`

Returns `f1` to the power of <i>e</i>.


`exp(+f1, ?f2)`

Procedure version of `exp(f1)`.


`failed(e)`

Creates a failed value `x` encapsulating exception `e`. Whenever a statement is needs `x`, in particular, whenever a thread synchronises on `x`, exception term `e` is raised. This is convenient in concurrent designs; if a concurrent generator encounters a problem while computing a value, it may catch the corresponding exception, package it as a failed value and return the latter instead. Thus eac consumer will be able to synchronously handle the exception when it attempts to use the "failed" value.


`failed(e, ?x)`

Procedure version of `failed(e)`


`failureException(x)`

Returns a failure exception record with dispatch field `x`.


`failureException(x, ?y)`

Procedure version of `failureException(x)`.


`filterList(+xs, +f)`

Returns the list which contains all the elements of `xs`, for which the unary boolean function `f` yields `true`. The ordering is preserved.


`filterList(+xs, +f, ?ys)`

Procedure version of `filterList(xs, f)`.


`filterRecord(+r, +f)`

Returns the record which contains all the features and fields of the record `r`, for which the unary boolean function `f` applied to the fields yields `true`.


`filterRecord(+r1, +f, ?r2)`

Procedure version of `filterRecord(r1, f)`.


`flatten(+xs)`

Returns the list obtained by flattening `xs`, i.e. by concatenating all sub-lists of `xs` recursively.


`flatten(+xs, ?ys)`

Procedure version of `flatten(xs)`.


`floatToInt(+f)`

Returns the integer closest to float `f`. If there are two candidates, `f` is rounded to the closest even integer, e. g., `floatToInt(1.5)` and `floatToInt(2.5)` both return <i>2</i>.\
<b><i>Attention</i></b>\
In the current Oz implementation, the value is converted through a signed 32-bit integer, so this function is bogus when applied to large floats.


`floatToInt(+f, ?i)`

Procedure version of `floatToInt(f)`.


`floatToString(+f)`

Returns the string describing the float `f` in Oz concrete syntax.


`floatToString(+f, ?s)`

Procedure version of `floatToString(f)`.


`floor(+f1)`

Returns the floor of `f1` (rounding towards negative infinity).


`floor(+f1, ?f2)`

Procedure version of `floor(f1)`.


`foldListL(+xs, +f, x)`

Used for folding the elements of `xs` by applying the binary function `f`.

Applying the left folding function `foldListL([X1,... Xn], f, x)` reduces to `f(...f(f(x, X1), X2), Xn)`. The first actual argument of `f` is the accumulator in which the result of the previous application, or the start value `z`, is passed. The second actual argument is an element of `xs`.

Besides the left folding function there exists a right folding variant. The application `foldListR([X1,... Xn], f, x)` reduces to `f(X1, f(X2, ...f(Xn, x)))`. The first actual argument of `f` is an element of `xs`; the second actual argument is the accumulator in which the result of the previous application or the start value `x` is passed.

For example, `foldListL([1,2,3], fun $(xr, x){(x::xr)}, nil)` yields the output `(3::2::1::nil)`, whereas `foldListR([b,c,d], fun $(x, xr){(x#r)}, a)` yields the output `(b#(c#(d#a)))`.


`foldListL(+xs, +f, x, ?y)`

Procedure version of `foldListL(xs, f, x)`.


`foldListR(+xs, +f, x)`

See foldListL().


`foldListR(+xs, +f, x, ?y)`

Procedure version of `foldListR(xs, f, x)`.


`foldRecordL(+r, +f, x)`

Used for folding the fields of `r` by applying the binary function `f`.

Supposing that `r` has the arity [F1, ... Fn], applying the left folding function `foldRecordL(r, f, x)` reduces to `f(...f(f(x, r.F1), r.F2), r.Fn)`. The first actual argument of `f` is the accumulator in which the result of the previous application, or the start value of `x`, is passed. The second actual argument is the field of `r`.

Besides the left folding function there exists a right folding variant. The application `foldRecordR(r, f, x)` reduces to `f(r.F1, f(r.F2, ...f(r.Fn, x)))`. The first actual argument of `f` is a field of `r`; the second actual argument is the accumulator in which the result of the previous application or the start value `x` is passed.

For example, `foldRecordL('a(3, a:7, b:4), fun $(xr, x){(x::xr)}, nil)` yields the output <code>[4, 7, 3]</code>, whereas `foldRecordR('a(3, a:7, b:4), fun $(x, xr){(x::xr)}, nil)` yields the output `[3, 7, 4]`.


`foldRecordL(+r, +f, x, ?y)`

Procedure version of `foldRecordL(r, f, x)`.


`foldRecordR(+r, +f, x)`

See foldRecordL().


`foldRecordR(+r, +f, x, ?y)`

Procedure version of `foldRecordR(r, f, x)`.


`foldTailL(+xs, +f, x)`

Used for folding all non-`nil` tails of `xs` by applying the binary function `f`, i.e. application of the left folding function <code>foldTailL([X1,... Xn], f, z)</code> reduces to `f(...f(f(z, [X1,...XN]), [X2,...Xn]), ...Xn])`.

The right folding function is analogous.


`foldTailL(+xs, +f, x, ?y)`

Procedure version of `foldTailL(xs, f, x)`.


`foldTailR(+xs, +f, x)`

See foldTailL().


`foldTailR(+xs, +f, x, ?y)`

Procedure version of `foldTailR(xs, f, x)`.


`forAllList(+xs, +p)`

Applies the unary procedure `p` to each element of `xs`, i.e., the application `forAllList([X1,...Xn], p)` reduces to the sequence of statements `p(X1) ... p(Xn)`.


`forAllRecord(+r, +p)`

Applies the unary procedure `p` to each field of `r`. Suppose that the arity of `r` is [F1, ... Fn]. The application `forAll(r, p)` reduces to the sequence of statements `p(r.F1) ... p(r.Fn)`.


`forAllTail(+xs, +p)`

Applies the unary procedure `p` to each non-`nil` tail of `xs`, i.e., the application `forAllTail([X1,...Xn], p)` reduces to the sequence of statements `p([X1,...Xn]) p([X2,...Xn])... p([Xn])`.


`future(x)`

Returns a future `y` for `x`, i.e. a read-only placeholder for `x`. If `y` becomes needed, `x` is made needed too.


`future(x, ?y)`

Procedure version of `future(x)`.


`getArray(+array, +i)`

Returns the item of `array` under key `i`.


`getArray(+array, +i, ?x)`

Procedure version of `getArray(array, i)`.


`getAttr(+k, +li)`

Returns the initial value `x` for attribute `li` as defined by class `k`.

For example, the call `getAttr(class $ attr a=4 {...}, a)` returns `4`.


`getAttr(+k, +li, ?x)`

Procedure version of `getAttr(k, li)`.


`getDictionary(+dictionary, +i)`

Returns the item of `dictionary` under key `i`.


`getDictionary(+dictionary, +i, ?x)`

Procedure version of `getDictionary(dictionary, i)`.


`getPriority(+thread)`

Returns `low`, `medium`, or `high` according to the priority of `thread`.


`getPriority(+thread, ?x)`

Procedure version of `getPriority(thread)`.


`getPriorityThis()`

Returns `low`, `medium`, or `high` according to the priority of the current thread.


`getPriorityThis(?x)`

Procedure version of `getPriorityThis()`.


`hasFeature(+rc, +li)`

Tests whether `rc` has feature `li`.

This operation if defined on records, tuples, arrays, and dictionaries.


`hasFeature(+rc, +li, ?b)`

Procedure version of `hasFeature(rc, li)`.


`high(+array)`

Returns the upper bound of the key range of `array`.


`high(+array, ?highI)`

Procedure version of `high(array)`.


`injectException(+thread, +x)`

Raises `x` as exception on `thread`. If `thread` is terminated, an error exception is raised in the current thread.


`intToFloat(+i)`

Returns the float closest to the integer `i`.


`intToFloat(+i, ?f)`

Procedure version of `intToFloat(i)`.


`intToString(+i)`

Returns the string describing the integer `i` in Oz concrete syntax.


`intToString(+i, ?s)`

Procedure version of `intToString(i)`.


`isArray(+x)`

Tests whether `x` is an array.


`isArray(+x, ?b)`

Procedure version of `isArray(x)`.


`isBool(+x)`

Tests whether `x` is a boolean value.


`isBool(+x, ?b)`

Procedure version of `isBool(x)`.


`isClass(+x)`

Tests whether `x` is a class.


`isClass(+x, ?b)`

Procedure version of `isClass(x)`.


`isDictionary(x)`

Tests whether `x` is a dictionary.


`isDictionary(x, ?b)`

Procedure version of `isDictionary(x)`.


`isDet(x)`

Tests whether `x` is determined.


`isDet(x, ?b)`

Procedure version of `isDet(x)`.


`isEmpty(+dictionary)`

Tests whether `dictionary` contains an entry.


`isEmpty(+dictionary, ?b)`

Procedure version of `isEmpty(dictionary)`.


`isEven(+i)`

Tests whether `i` is an even integer.


`isEven(+i, ?b)`

Procedure version of `isEven(i)`.


`isFailed(x)`

Tests whether `x` is a failed value.


`isFailed(x, ?b)`

Procedure version of `isFailed(x)`.


`isFloat(+x)`

Tests whether `x` is a float.


`isFloat(+x, ?b)`

Procedure version of `isFloat(x)`.


`isFree(x)`

Tests whether `x` is currently free.


`isFree(x, ?b)`

Procedure version of `isFree(x)`.


`isFunctor(+x)`

Tests whether `x` is a functor.


`isFunctor(+x, ?b)`

Procedure version of `isFunctor(x)`.


`isFuture(x)`

Tests whether `x` is a future.


`isFuture(x, ?b)`

Procedure version of `isFuture(x)`.


`isInt(+x)`

Tests whether `x` is an integer.


`isInt(+x, ?b)`

Procedure version of `isInt(x)`.


`isKinded(x)`

Tests whether `x` is currently kinded, i.e., is constrained but not yet determined.
For example, `'foo(a:12, ...)` is kinded because it is constrained to be a record, yet its arity is not yet known. Also, a non-determined finite domain variable is kinded: its type is known to be integer, but its value is not yet determined. Similarly for finite set variables.


`isKinded(x, ?b)`

Procedure version of `isKinded(x)`.


`isList(+x)`

Tests whether `x` is a list.


`isList(+x, ?b)`

Procedure version of `isList(x)`.


`isLock(+x)`

Tests whether `x` is a lock.


`isLock(+x, ?b)`

Procedure version of `isLock(x)`.


`isNat(+i)`

Tests whether `i` is a natural number, i.e., an integer greater than or equal to `0`.


`isNat(+i, ?b)`

Procedure version of `isNat(i)`.


`isNeeded(+x)`

Tests whether `x` is needed.


`isNeeded(+x, ?b)`

Procedure version of `isNeeded(x)`.


`isNumber(+x)`

Tests whether `x` is a number.


`isNumber(+x, ?b)`

Procedure version of `isNumber(x)`.


`isObject(+x)`

Tests whether `x` is an object (an instance of a class).


`isObject(+x, ?b)`

Procedure version of `isObject(x)`.


`isOdd(+i)`

Tests whether `i` is an odd integer.


`isOdd(+i, ?b)`

Procedure version of `isOdd(i)`.


`isPort(+x)`

Tests whether `x` is a port.


`isPort(+x, ?b)`

Procedure version of `isPort(x)`.


`isPrefix(+xs, +ys)`

Tests whether `xs` is a prefix of `ys`. Given that `xs` has length <i>i</i>, it is a prefix of `ys` if `ys` has at least length <i>i</i> and the first elements of `ys` are equal to the corresponding elements of `xs`.


`isPrefix(+xs, +ys, ?b)`

Procedure version of `isPrefix(xs, ys)`.


`isProcedure(+x)`

Returns `true` if `x` is a procedure or a function, and false otherwise.


`isProcedure(+x, ?b)`

Procedure version of `isProcedure(x)`.


`isRecord(+x)`

Tests whether `x` is a record.


`isRecord(+x, ?b)`

Procedure version of `isRecord(x)`.


`isSuspended(+thread)`

Tests whether `thread` is currently suspended.


`isSuspended(+thread, ?b)`

Procedure version of `isSuspended(thread)`.


`isThread(+x)`

Tests whether `x` is a thread.


`isThread(+x, ?b)`

Procedure version of `isThread(x)`.


`isTuple(+x)`

Tests whether `x` is a tuple.


`isTuple(+x, ?b)`

Procedure version of `isTuple(x)`.


`items(+dictionary)`

Returns the list of all items currently in `dictionary`.


`items(+dictionary, ?xs)`

Procedure version of `items(dictionary)`.


`keys(+dictionary)`

Returns a list of all currently valid keys of `dictionary`.


`keys(+dictionary, ?lis)`

Procedure version of `keys(dictionary)`.


`label(+r)`

Returns the label (name) of `r` (without the leading apostrophe).


`label(+r, ?l)`

Procedure version of `label(r)`.


`last(+xs)`

Returns the last element of `xs`. Raises an exception if `xs` is (nil).


`last(+xs, ?y)`

Procedure version of `last(xs)`.


`length(+xs)`

Returns the length of `xs`.


`length(+xs, ?i)`

Procedure version of `width(r)`.


`log(+f1)`

Returns the logarithm to the base <i>e</i> of `f1`.


`log(+f1, ?f2)`

Procedure version of `log(f1)`.


`low(+array)`

Returns the lower bound of the key range of `array`.


`low(+array, ?lowI)`

Procedure version of `low(array)`.


`makeList(+i)`

Returns a new list of length `i`. All elements are fresh variables.


`makeList(+i, ?xs)`

Procedure version of `makeList(i)`.


`makeNeeded(+x)`

Makes `x` needed. this procedure is useful for triggering by-need computations on `x` without having to suspend on `x`.


`makeRecord(+l, +lis)`

Returns a new record with label (name) `l`, features `lis`, and fresh variables at every field. All elements of `lis` must be pairwise distinct, else an error exception is raised at runtime.

For example, `makeRecord(l, a, r)` waits until `l` is bound to a name literal, say `b`, and `a` is bound to a list of literals and integers, say `[c, d, 1]`, and then binds `r` to `'b(_, c:_, d:_)`.


`makeRecord(+l, +lis, ?r)`

Procedure version of `makeRecord(l, lis)`.


`makeTuple(+l, +i)`

Returns a new tuple with label `l` and fresh variables at features `1` to `i`. `I` must be non-negative, else an exception is raised.

For example, `makeTuple(l, n, t)` waits until `l` is bound to a name literal, say `b`, and `n` is bound to a number, say `3`, whereupon `t` is bound to `'b(_, _, _)`.


`makeTuple(+l, +i, ?t)`

Procedure version of `makeTuple(l, i)`.


`mapList(+xs, +f)`

Returns a list obtained by applying the unary function `f` to each element of `xs`.


`mapList(+xs, +f, ?ys)`

Procedure version of `mapList(xs, f)`.


`mapRecord(+r1, +f)`

Returns a record with same label and arity as `r1`, whose fields are computed by applying the unary function `f` to all fields of `r1`.

For example, `map('a(12, b:13, c:1), fun $ (a) {(a*2)})` yields the output record <code>'a(24, b:26, c:2)</code>.


`mapRecord(+r1, +f, ?r2)`

Procedure version of `mapRecord(r1, f)`.


`max(+afi1, +afi2)`

Returns the maximum of `afi1` and `afi2`.


`max(+afi1, +afi2, ?afi3)`

Procedure version of `max(afi1, afi2)`.


`memberDictionary(+dictionary, +li)`

Tests whether `li` is a valid key of `dictionary`.


`memberDictionary(+dictionary, +li, ?b)`

Procedure version of `memberDictionary(dictionary, li)`.


`memberList(x, +ys)`

Tests whether `x` is equal (in the sense of `==`) to some element of `ys`.\
**Note** : all other built-in functions that operate on lists take them as their first argument.
`memberList()` is the only exception (for historical reasons).


`memberList(x, +ys, ?b)`

Procedure version of `memberList(x, ys)`.


`merge(+xs, +ys, +f)`

Returns a list `zs` obtained by merging `xs` and `ys` using the ordering described by `f`. The lists `xs` and `ys` must be sorted.


`merge(+xs, +ys, +f, ?zs)`

Procedure version of `merge(xs, ys, f)`.


`min(+afi1, +afi2)`

Returns the minimum of `afi1` and `afi2`.


`min(+afi1, +afi2, ?afi3)`

Procedure version of `min(afi1, afi2)`.


`new(+k, +initCall)`

Creates a new object from class `k` with initial call to `initCall`.


`new(+k, +initCall, ?o)`

Procedure version of `newObject(k, initCall)`.


`newArray(+lowI, +highI, initX)`

Returns a new array with key range from `lowI` to `highI` including both. All items are initialized to `initX`.


`newArrayP(+lowI, +highI, +initX, ?array)`

Procedure version of `newArray(lowI, highI, initX)`.




`newClass(+parentKs, +attrR, +featR, +propAs)`

Creates a new class by inheriting from `ParentKs` with new attributes `attrR`. Since features and properties are not yet supported in <b>NewOz</b>, arguments `featR` and `propAs` should always be `_`. The fields with integer values in `attrR` define the free attributes. The fields with literal features defines attributes with initial values, where the features is the attribute name and the field its initial value.

For example, the statement `c = newClass([d, e], 'a(a:1, b), _ _)` is equivalent to :

    class c extends d, e
      attr a=1 attr b
    {
    }


`newClass(+parentKs, +attrR, +featR, +propAs, ?k)`

Procedure version of `newClass(parentKs, attrR, featR, propAs)`.


`newDictionary()`

Returns a new empty dictionary.


`newDictionary(?dictionary)`

Procedure version of `newDictionary()`.


`newLock()`

Creates and returns a new lock.


`newLock(?lock)`

Procedure version of `newLock()`.


`newPort(?xs)`

Returns a new port, together with its associated stream `xs`.


`newPort(?xs, ?port)`

Procedure version of `newPort(xs)`.


`not(+b1)`

Returns the negation of truth value `b1`.


`not(+b1, ?b2)`

Procedure version of `not(b1)`.


`nth(+xs, +i)`

Returns the `i`th element of `xs` (counting from 1).


`nth(+xs, +i, ?y)`

Procedure version of `nth(xs, i)`.


`numbers(+fromI, +toI, stepI)`

Returns a list with elements from `fromI` to `toI` with step `stepI`.

For example, `numbers(1, 5, 2)` returns `[1, 3, 5]`, <code>numbers(5, 1, 2)</code> returns the list (nil), and `numbers(5, 0, -2)` yields the list <code>[5, 3, 1]</code>.


`numbers(+fromI, +toI, +stepI, ?xs)`

Procedure version of `numbers(fromI, toI, stepI)`.


`or(+b1, +b2)`

Returns the disjunction of truth value `b1` and `b2`. Note that `or()` is different from the operation available via the keyword `||` in that it always evaluates its second argument. For instance, `true || p` reduces without reducing application of `p`, whereas reduction of `or(true, p)` always applies `p`.


`or(+b1, +b2, ?b3)`

Procedure version of `or(b1, b2)`.


`partitionList(+xs, +f, ?ys, ?zs)`

`partitionList` computes a list `ys` which contains all the elements of `xs` for which the unary boolean function `f` yields `true`, and a list `zs` with the remaining fields of `xs`. The ordering is preserved.


`partitionRecord(+r1, +f, ?r2, ?r3)`

`partitionRecord` computes a record `r2` which contains all the features and fields of the record `r1` for which the unary boolean function `f` yields `true`, and a record `r3` with the remaining fields of `r1`.


`pow(+fi1, +fi2)`

Returns `fi1` to the power of `fi2`.


`pow(+fi1, +fi2, ?fi3)`

Procedure version of `pow(fi1, fi2)`.


`preempt(+thread)`

Preempts the current thread, i.e., immediately schedules another runnable thread (if there is one). `thread` stays runnable.


`procArity(+fp)`

Returns the procedure arity of `fp`, i.e., the number of arguments it takes.\
<b>Warning : if `fp` is a function (as opposed to a procedure),
the result returned by this operation is currently 1 too big.
This will be fixed in a future release.</b>


`procArity(+fp, ?i)`

Procedure version of `procArity(fp)`.\
<b>Warning : if `fp` is a function, the result returned by this operation is currently 1 too big.
This will be fixed in a future release.</b>


`putArray(+array, +i, x)`

Sets the item of `array` under key `i` to `x`.


`putDictionary(+dictionary, +li, x)`

Sets the item of `dictionary` under key `li` to `x`.


`raiseError(+x)`

Wraps `x` in an error exception and raises this.
This procedure can be defined as follows, except that it always adds debug information :

    defproc raiseError(x) {
        raise {errorException(x)}
    }

`remove(+dictionary, +li)`

Removes the item under key `li` from `dictionary` if `li` is a valid key. Otherwise, does nothing.


`removeAll(+dictionary)`

Removes all entries currently in `dictionary`.


`resumeThread(+thread)`

Resumes `thread`. Resumption undoes suspension.


`reverse(+xs)`

Returns the elements of `xs` in reverse order.


`reverse(xs, ?ys)`

Procedure version of `reverse(xs)`.


`round(+f1)`

Returns the integral value closest to `f1`. If there are two candidates, `f1` is rounded to the closest even integral value, e. g., `round(1.5)` and `round(2.5)` both return <i>2.0</i>.


`round(+f1, ?f2)`

Procedure version of `round(f1)`.


`send(+port, x)`

Sends `x` to the port `port`: the stream pointed to by `port` is unified with `(x::_)` (in a newly created thread), and the pointer advances to the stream's new tail.


`sendReceive(+port, x, y)`

Sends the pair `(x#y)` to the port `port`: the stream pointed to by `port` is unified with `((x#y)::_)` (in a newly created thread), and the pointer advances to the stream's new tail.

The argument `x` is commonly used as message to be sent, while `y` serves as a reply to that message.


`setPriority(+thread, +s)`

Sets priority of thread `thread` to the priority described in `s`. `s` must be one of the strings `"low"`, `"medium"`, or `"high"`.


`setPriorityThis(+s)`

Sets priority of the current thread to the priority described in `s`. `s` must be one of the strings `"low"`, `"medium"`, or `"high"`.


`sin(+f1)`

Returns the sine of `f1`.


`sine(+f1, ?f2)`

Procedure version of `sine(f1)`.


`sinh(+f1)`

Returns the hyperbolic sine of `f1`.


`sinh(+f1, ?f2)`

Procedure version of `sinh(f1)`.


`someList(+xs, +f)`

Tests whether the unary boolean function `f` yields `true` when applied to some elements of `xs`. Stops at the first field for which `f` yields `true`.


`someList(+xs, +f, ?b)`

Procedure version of `someList(xs, f)`.


`someRecord(+r, +f)`

Tests whether the unary boolean function `f` yields `code` when applied to some fields of `r`. Stops at the first field for which `f` yields `true`. The fields are tested in the order given by `arity()` (which see).


`someRecord(+r, +f, ?b)`

Procedure version of `someRecord(r, f)`.


`sort(+xs, +f)`

Returns a list `ys` obtained by sorting `xs` using the comparison function `f`. `F` must be a binary function returning a boolean value. `sort()` is implemented using the mergesort algorithm.

For example,`sort([2 3 4 1 1], fun$(a, b){ (a&lt;b) })` returns the list `[1 1 2 3 4]`.


`sort(+xs, +f, ?ys)`

Procedure version of `sort(xs, f)`.


`sqrt(+f1)`

Returns the square root of `f1`.


`sqrt(+f1, ?f2)`

Procedure version of `sqrt(f1)`.


`stateThread(+thread)`

Returns `runnable`, `blocked`, or `terminated`, according to the current state of `thread`.


`state(+thread, ?x)`

Procedure version of `stateThread(thread)`.


`status(x)`

Returns status and type information on `x`.

If `x` is free, the atom `free` is returned. If `x` is a future, the atom `future` is returned. If `x` is a failed value, the atom `failed` is returned. If `x` is kinded, the tuple `'kinded(y)` is returned, where `y` is bound to either the atoms `int`, `fset` or `record`, depending on the type of `x`. If `x` is determined, the tuple `det(Y)` is returned, where `y` is bound to the atom as returned by `type(x)`.


`status(x, ?t)`

Procedure version of `status(x)`.


`suspendThread(+thread)`

Suspends `thread` such that it cannot be further reduced.


`sub(+xs, +ys)`

Tests whether `xs` is a sublist of `ys`, i.e., whether it contains all elements of `xs` in the same order as `xs` but not necessarily in succession.

For example, `[a, b]` is a sublist of both `[1, a, b, 2]` and <code>[1, a, 2, b, 3]</code>, but not of `[b, a]`.


`sub(+xs, +ys, ?b)`

Procedure version of `sub(xs, ys)`.


`subtractList(+xs, y)`

Returns `xs` without the leftmost occurrence of `y` if there is one.


`subtractList(+xs, y, ?zs)`

Procedure version of `subtractList(xs, y)`.


`subtractListRecord(+r1, +lis)`

Returns record `r1` with all features in `lis` removed.

For example, `subtractList('f(jim:1, jack:2, jesse:4), [jesse, jim])` returns the record `'f(jack:2)`.


`subtractListRecord(+r1, +lis, ?r2)`

Procedure version of `subtractListRecord(r1, lis)`.


`subtractRecord(+r1, +li)`

If `r1` has feature `li`, returns record `r1` with feature `li` removed. Otherwise, return `r1`.


`subtractRecord(+r1, +li, ?r2)`

Procedure version of `subtractRecord(r1, li)`.


`systemException(x)`

Returns a system exception record with dispatch field `x`.


`systemException(x, ?y)`

Procedure version of `systemException(x)`.


`tail(+i, y)`

Returns a list with at least `i` elements whose rest is `y` (which doesn't need to be a list). The first `i` elements are fresh variables.

For example, `tail(2, (a::b::nil))` returns `(_::_::a::b::nil)`.


`tail(+i, y, ?xs)`

Procedure version of `tail(i, y)`.


`take(+xs, +i)`

Returns the list that contains the first `i` elements of `xs`, or `xs` if it is shorter.


`take(+xs, +i, ?ys)`

Procedure version of `take(xs, i)`.


`takeDrop(+xs, +i, ?ys, ?zs)`

Binds `ys` to `take(xs, i)` and `zs` to <code>drop(xs, i)</code>.


`takeDropWhileList(+xs, +f, ?ys, ?zs)`

While `filterList` selects all elements of a list which satisfy a certain condition, `takeWhileList` selects only the starting sequence of elements which fulfill this condition. `dropWhileList` computes a list with the remaining elements. `takeDropWhileList` combines the functionality of both previous methods.


`takeWhileList(+xs, +f)`

See takeDropWhileList()


`takeWhileList(+xs, +f, ?r2)`

See takeDropWhileList()


`takeDropWhileRecord(+r1, +f, ?r2, ?r3)`

While `filterRecord` selects all fields and features of a record which satisfy a certain condition, `takeWhileRecord` selects only the starting sequence of features and fields which fulfill this condition. `dropWhileRecord` computes a record with the remaining features and fields. `takeDropWhileRecord` computes both records.


`takeWhileRecord(+r1, +f)`

See takeDropWhileRecord()


`takeWhileRecord(+r1, +f, ?r2)`

See takeDropWhileRecord()


`tan(+f1)`

Returns the tangent of `f1`.


`tan(+f1, ?f2)`

Procedure version of `tan(f1)`.


`tanh(+f1)`

Returns the hyperbolic tangent of `f1`.


`tanh(+f1, ?f2)`

Procedure version of `tanh(f1)`.


`terminate(+thread)`

Raises an exception `'kernel(terminate, ...)` on `thread`.


`thisThread()`

Returns the current thread.


`thisThread(?thread)`

Procedure version of `thisThread(x)`.


`time()`

Returns the number of seconds elapsed since January 1st of the current year.


`time(?t)`

Procedure version of `time()`.


`toArray(+t)`

Returns an array with bounds between `1` and `width(t)`, where the elements of the array are the fields of `t`.


`toArray(+t, ?a)`

Procedure version of `toArray(t)`.


`toDictionary(+r)`

Returns a dictionary whose keys, and their entries correspond to the features and their fields of `r`.


`toDictionary(+r, ?dictionary)`

Procedure version of `toDictionary(r)`.


`toList(+r)`

Returns the list of all fields of `r`, in the order given by `arity()` (which see).

For example, `toList('f(a, a:2, b:3))` yields `[a, 2, 3]` as output.


`toList(+r, ?xs)`

Procedure version of `toList(r)`.


`toListInd(+r)`

Returns the list of pairs that contains the feature-field pairs of `r`, in the order given by `arity()` (which see).

For example, `toListInd('f(a, a:2, b:3))` yields `[1#a, a#2, b#3]` as output.


`toListInd(+r, ?ts)`

Procedure version of `toListInd(r)`.


`listToRecord(+l, +ts)`

Returns a new record `r` with label `l` whose fields are given by the list of pairs `ts` : for every element <i>li</i>#<i>xi</i> of `xs`, `r` has a field <i>xi</i> at feature <i>li</i>. The features in the pairs list must be pairwise distinct, else an error exception is raised.

For example, `listToRecord("f", [(a#1), (b#2), (c#3)])` yields <code>'f(a:1, b:2, c:3)</code> as output.


`listToRecord(+l, +ts, ?r)`

Procedure version of `listToRecord(r)`.


`toTuple(+l, +xs)`

Returns a new tuple with label `l` that contains the elements of `xs` as fields, in the given order. As a limitation in this release of <b>NewOz</b>, `l` has to a string.

For example, `toTuple("f", (a::b::c::nil))` yields `'f(a, b, c)` as output.


`toList(+l, +xs, ?t)`

Procedure version of `toTuple(l, xs)`.


`toVirtualString(x, +depthI, +widthI)`

Returns a virtual string describing the value of `x`. Note that this does not block on `x`. The value of `depthI` and `widthI` are used to limit output of records in depth and width respectively.


`toVirtualString(x, +depthI, +widthI, ?vs)`

Procedure version of `teVirtualString(x, depthI, widthI)`


`type(+x)`

Returns an atom describing the type of `x`. If `x` is of one of the standard primary types, then the returned value is constrained to the most specific of int, float, record, tuple, atom, name, procedure, cell, byteString, bitString, chunk, array, dictionary, bitArray, 'class', object, 'lock', port, space, or 'thread'. If any other atom is returned, this means that `x` is of no standard primary type, but an implementation-dependent extension.


`type(+x, ?a)`

Procedure version of `type(x)`.


`wait(+x)`

Blocks until `x` is determined. This statement makes `x` needed, causing all by-need computations on `x` to be triggered. If `x` is or becomes bound to a failed value, then its encapsulated exception is raised.


`waitNeeded(+x)`

Blocks until `x` is needed. This operation is the by-need synchronization.


`waitOr(x, y)`

Blocks until at least one of `x` or `y` is determined.


`waitQuiet(+x)`

Blocks until `x` is determined or failed. Contrary to `wait()`, this procedure does not make `x` needed. Also, if `x` is or becomes bound to a failed value, no exception is raised.


`waitRecord(+r1)`

Blocks until at least one field of `r` is determined. Returns the feature of a determined field. Raises an exception if `r` is not a proper record, that is, if `r` is a literal.

For example, `waitRecord('a(_, b:1))` returns `b`, while `waitRecord('a(2, b:_))` returns `1`, and <code>waitRecord('a(_, b:_))</code> blocks.


`waitRecord(+r1, ?li)`

Procedure version of `waitRecord(r1)`.


`width(+r)`

Returns the width of `r`.


`width(+r, ?i)`

Procedure version of `width(r)`.


`zipLists(+xs, +ys, +f)`

Returns the list of all elements <code>z<i>i</i></code> computed by applying <code>f(x<i>i</i>, y<i>i</i>)</code>,
where <code>x<i>i</i></code> is the <i>i</i>th element of `xs` and <code>y<i>i</i></code> is the <i>i</i>th element of `ys`.
The two inputs lists must be of equal length, else an error exception is raised.

For example, `zipLists([1,6,3], [4,5,6], def $(x, y) {(x>y)}` yields the output list `(false::true::false::nil)`.


`zipLists(+xs, +ys, +f, ?zs)`

Procedure version of `zipLists(xs, ys, f)`.


`zipRecords(+r1, +r2, +f)`

Given two records `r1` and `r2` and a binary function `f`, returns a record `r3` with the same label as `r1`, and whose features are those common to `r1` and `r2`. Features appearing only in one of the records are silently dropped. Each field `x` of `r3` is computed by applying `f(r1.x, r2.x)`.

For example, `zip( 'f(jim:1, jack:2, jesse:4), 'g(jim:1, jack:b, joe:c), def $(x, y) {(x#y)}` yields the output record `'f(jim:1#a, jack:2#b)`.


`zipRecords(+r1, +r2, +f, ?r3)`

Procedure version of `zipRecords(r1, r2, f)`.
