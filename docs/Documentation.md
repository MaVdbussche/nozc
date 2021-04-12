# Introduction

> Disclaimer : this document is meant to be consulted from the GitHub page of the project, located at https://github.com/MaVdbussche/nozc.
> In particular, links will not work otherwise.

This document describes the basics of the syntax of the **NewOz** language.
**NewOz** is in fact, rather than a separate language, a new syntax for the existing **Oz**
programming language. The **Nozc** compiler translates **NewOz** code to equivalent, readable **Oz**
code, which is then fed to the `ozc` compiler. This compiled code can then be executed using
the `ozengine` program. For more background information, please read the main [README](../README.md)
file.

This document is a tutorial to get started with the **NewOz** language, including code snippets to
serve as examples. If you are looking for help when using the **Nozc** compiler, please read the
main [README file](../README.md) on GitHub.

> This documentation is inspired by the official Mozart 2 documentation, located at http://mozart2.org

# Type Structure

NewOz uses dynamic type checking; this means that type errors will not be caught be the **Nozc**
compiler. Any expression has a type at any given moment during its lifetime.\
Expressions can be stored in *vars* and *vals*. Note that **NewOz** implements higher-order
programming, meaning that functions and procedures are considered values, which can be stored in
*vars* and *vals*.

- *vars* are variables, following the definition you are probably used to in most modern programming
  languages : once declared, they can be reassigned at will. Their assigned type can thus change
  during the execution of the program.
- *vals* are immutable values, sometimes known as final : they can only be assigned once. As such,
  their type is fixed once assigned a value.

In this documentation, we use the word *variable* to refer to *vars* and *vals* indistinctly, unless
specified otherwise.\
Valid names for variables are all series of alphanumeric characters, plus the '_' character,
starting with a lowercase letter : e.g. `a`, `myVariable95`, `f2ze_5d4f6d`.

### NewOz types

- **Characters** : alpha-numeric characters are enclosed between apostrophes : e.g. `'c'`, `'5'`.
  They are encoded as integers in the underlying **Oz** language.
- **Booleans** : either `true` or `false`;
- **Integers** : integer numbers can be described in 4 numerical bases : binary (e.g. `0b1101`
  , `0B1001`), octal (e.g. `05620`, `00101`), decimal (e.g. `0`, `3`, `3501`), and hexadecimal (
  e.g. `0x1A`, `0XFF0`).
- **Floats** : floating-point numbers can be written under one of the following forms : `0.005`
  , `1.5e44`, `0.03E~5`, where the letter *e*/*E* indicates the exponent to 10. Note that the
  symbol *~* is used, in this particular context, to describe the negative exponent.
- **Records** : records are structured compound entities. They are described by a name (called
  label), and a set of features. Each of those features may store a value, which can of course be a
  record itself, allowing to build complex data structures. Records can be declared as
  follows : `'myRecord(name:"John Doe", age:22, weight:85.3)`. Note the apostrophe, which is a
  mandatory character at the beginning of records labels.\
  Note that you can omit features in a record description; in that case, the compiler will place
  ordered numbers in their place for you. As such, `'exampleRecord(25, a:'c', 2)` is equivalent
  to `'exampleRecord(1:25, a:'c', 2:2)`.
- **Tuples** are ordered sets of elements, made accessible through their ordinal. They can be
  declared by using the `#` operator as follows : `2#4#5#'c'`. Access elements through their
  ordinal, e.g. : `variableStoringMyTuple.3`. "Under the hood", tuples are actually modelled as
  records whose features are ordered integers. This will be apparent in some library functions; in
  particular, all of those functions operating on records can be applied to tuples.\
  A two-elements tuple is sometimes called a *pair*.
- **Lists** : in **NewOz**, lists have a recursive definition. The head of a list is an expression
  of any type; while the tail of a list is an expression evaluated to a list. The last element of a
  list is indicated using the keyword `nil`. List can be defined in two ways : `(1::2::5::'c'::nil)`
  is equivalent to `[1, 2, 5, 'c']`. Note that `nil` should be omitted when using the second
  notation. Both notations are totally interchangeable, and will be used indistinctly throughout the
  documentation. Accessing lists has to be done recursively : `listName.1` is the head, `listName.2`
  is the tailing list. Accessing the 4th element of a list could be done by
  writing `listName.2.2.2.1`, but **
  NewOz**'
  basic library also provides helper functions to do this.
- **Strings** : strings are sequences of characters enclosed in quotation marks.
  e.g. `"Hello World !"`.
- **Features** : features are denoted by integers, or literals similar to variable names
- **Dictionaries** : dictionaries are data structures consisting of key-item pairs :
  we say that the item *i* is stored under key *li*.
  If a dictionary contains an item under some key *li*, we say *li* is a valid key.
  Whenever a dictionary access is indexed with an ill-typed key, a type error is raised.
  For a missing but well-typed key, a system exception is raised.
- **Arrays** : arrays can be seen as a subtype of dictionaries, whose keys are integers.
  As such, one could make the parallel to the similarities between records and tuples;
  however, arrays provide no alternative syntax like tuples do.
  On top of this, arrays do not inherit all library methods from dictionaries.
- **Ports** : TODO
- **Locks**: TODO
- **Classes** : TODO
- **Objects** : TODO
- **Functors** : TODO

### Variable status

A variable `x` is always in one of the following statuses :

- **Free**: the constraint store does not know anything about `x`, apart from variables equalities
- **Determined** : the constraint store knows of a value assigned to `x`
- **Future** : the constraint store entails `x = f` for some future `f`
- **Kinded** : `x` is neither free, nor determined, nor future.

# **NewOz**'s Standard Library

//TODO generate it using https://delight-im.github.io/Javadoc-to-Markdown/, from the Javadoc of
BuiltIns class\
Every function or procedure built into the language is described on [this page](./Library.md), with
a signature as follows :

```
name(+xs, +p, ?ys)
```

This signature specifies the number of arguments, their type, and their mode.

- **Types** : the type of an argument is indicated by its name, using the abbreviations in the
  following table :

| Abbreviation | Type    |
|--------------|---------|
| a            | Array |
| b            | Boolean |
| f            | Float   |
| i            | Integer |
| k            | Class   |
| o            | Object |
| p            | Procedure |
| r            | Record |
| s            | String |
| t            | Tuple |
| x y z        | Value |
| li           | Feature |
| xs           | List of elements of type x |

We use indices such as `r1` or `r2` to disambiguate several occurrences of arguments of the same
type. We combine these abbreviations as in `li` meaning `literal or integer` (i. e., feature). We
use the plural-`s` suffix to indicate lists of values of a certain type. For instance, `is` stands
for a list of integers. This suffix can be repeated to indicate lists of lists etc. Additionally,
these arguments can be prefixed as in `lowI`, which indicates that the integer represents a lower
bound.

- **Modes** : The arguments can have a mode denoted by the `+` and `?` symbols. Modes indicate the
  synchronisation behaviour of a procedure.
  - The application of a procedure `P` waits until its inputs marked `+` are determined.
  - If the input arguments are well-typed, `P` will return outputs, marked `?` of the specified types.
    Ill-typed input arguments will raise a type error exception.
    Types may be incompletely checked, especially those of output arguments:
    this happens when a value doesn't need to be completely processed to perform an operation, e. g., in `dropWhileList()`.
    Note that output (`?`) names have to reference an **unassigned / free *val***.
    Passing an assigned *val*, or a *var* will result in an error which cannot always be caught at compilation time.
  - Arguments with no modes specified can be any value or variable, free or not.
    
> Important note : a nice feature of the **Oz** standard library is that many functions define an equivalent procedure which stores the result in an output variable.
> **NewOz** mimics this behavior (only in its library); such "overloading" procedures will appear in the library documentation alongside their "function" version.
> For example, `isList(+x)` has an equivalent `isList(+x, ?b)`, in which the result of the operation is stored in `b` instead of being returned.\
> **Remember :** when calling one of those "Procedure versions" of functions, the last parameter (`?b` here) has to denote an **unassigned / free *val*** !

\
\
The rest of this document will provide code examples to describe common operations you can do in **
NewOz**. We indicate **invalid** code pieces with an explanation.

# Declaring data

## Variables

| Code snippet | Explanation for **valid** code | Explanation for **erroneous** code |
|--------------|-------------|-------------|
| `val a, b, c` | You can declare multiples variables on the same line | |
| `val d=5 var e`<br>*is equivalent to :*<br>`val d=5`<br>`var e` | `d` is a *value* (final), `e` is a *variable* | |
| `val d, var e` | | Commas can only be used to declare variables of the same *nature* on the same line |
| `val d`<br>`d = 5`<br>*is equivalent to :*<br>`val d = 5` | You can split declaration and initialization | |
| `val d=5`<br>`d = 6` | | Not valid since `d` is a *val* (final) |
| `val l = [1,2,3]`<br>*is equivalent to :*<br>`val l = (1::2::3::nil)` | Note that placing the closing `nil` is necessary in the 1st syntax| |
| `val t = 1#2#'c'` | Tuple declaration | |
| `val a = 'name(1:'a' 2'b' 3:'c')`| Record declaration. The `'` is mandatory | |
| `val a = _`<br>*is equivalent to :*<br>`val a` | Variables can be declared or explicitly set to "unassigned" (see *free* status above) |

## Operators

| Code snippet | Explanation for **valid** code | Explanation for **erroneous** code |
|--------------|-------------|-------------|
| `r = a + b`<br>`r = a - -b`<br>`r = a*b`<br>`r = a/b`<br>`r = a%b` | Addition<br>Subtraction<br>Multiplication<br>Division : valid for 2 ints *or* 2 floats<br>Modulo | |
| `r = a && b`<br><code>r = a &#124;&#124; b</code><br>`r = a==b`<br>`r = !b` | Logical operations on booleans | |
| `r = "Hello "+"world"` | String concatenation | |
| `r = a < b`<br>`r = a >= b`<br>`r = a <= b`<br>`r = a > b` | Logical operations on booleans | |
| `r = l.1`<br>`r = l.2` | Select the head (first element) of a list<br>Select the tail of a list (which is a list, see above) | |
| `r = l.3` | | There is no 3rd element in a **NewOz** list. Use the library or create a recursive method to access further elements |

## Functions & Procedures

| Code snippet | Explanation for **valid** code | Explanation for **erroneous** code |
|--------------|-------------|-------------|
| `fun sum(a, b) { (a+b) }` | Function declaration with 2 parameters. Body is between `{}`. Returned expression is between `()` | |
| `fun sum(a, b) { }`<br>`fun sum(a, b) {`<br>`procedureCall(a)`<br>`}` | | Functions must return an expression as the last element of their body |
| `proc sum(a, b, c) { c=a+b}`<br>`proc nothing(a) {}` | Procedure declaration. Procedures cannot return an expression<br>*Note : in this example, c has to be a *var* or an unassigned *val* defined before !*<br>Procedures can be empty (no side-effect) | |
| `proc sum(a, b) { c }` | | Procedures can't return expressions. |
| `fun lazy ints(n) {`<br>`(n::ints(n+1))`<br>`}` | Functions can be declared "lazy" (=*demand-driven*). Evaluation is done only when the result is needed (by-need computation). | |
| `val s = fun $ (a, b) {`<br>`(a+b)`<br>`}`<br>*later, call :*<br>`x = s(3, 4)` | You can declare functions "anonymously". Functions, procedures, and more are values (*higher-order programming*) | |

## Basic structures

| Code snippet | Explanation for **valid** code | Explanation for **erroneous** code |
|--------------|-------------|-------------|
| `if (a<b) {`<br>`(b-a)`<br>`} else if (a>b) {`<br>`(a-b)`<br>`} else {`<br>`0 }` | Conditional structures. All returned expression must be of the same type | |
| `match myList {`<br>`case (e::nil) => { browse(e) }`<br>`case (e::l2) && e==1 => { browse("One") }`<br>`else { browse("Error") }`<br>`}` | Switch statement | |
| For loops : 4 possible notations :<br>1: `for x in 0;x<10;x+1 { ... }`<br>*is equivalent to :*<br>2: `for x in 0 .. 9;1 { ... }`<br>*is equivalent to :*<br>3 :`for x in 0 .. 9 { ... }`<br>4: `for x in 0;x+1 { ... }`| 1 : C-like notation : <*intitial value*> ; <*boolean condition*> ; <*next value*><br>2 : <*lower bound*> .. <*upper bound*>; <*increment value*><br>Bounds are inclusive. All three values are evaluated once, before starting the loop. Step can be negative. Only integers are allowed<br>3 : <*lower bound*> .. <*upper bound*><br>Same as above, with step defaulting to 1<br>4 : <*initial value*> ; <*increment value*><br>Equivalent to `for x in 0;true;x+1 { ... }`| |
| `raise {e}` | `e` is an exception, which in practice can be any expression | |
| `try { ... }`<br>`catch x {`<br>`case 1 && b==true => {...}`<br>`case 2 => {...}`<br>`else {}`<br>`} finally { ... }` | You can catch raised exceptions and do pattern-matching against them. In this example we assume numbers, are raised, but as said above, any expression is a valid exception. | |
| `thread { ... } ` | Create a new thread and run the code block in it | |
| `lock l { ... }` | `l` is a reentrant lock (see the library for how to create them). The code block will be executed by the thread when the lock is available | |
| `skip` | The empty statement | |

## Classes and objects

Including class definitions, private methods, and calls fom outside. + this and super()

## Functors