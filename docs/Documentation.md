# Introduction
>Disclaimer : this document is meant to be consulted from the GitHub page of the project, located at https://github.com/MaVdbussche/nozc.
> In particular, links will not work otherwise.

This document describes the basics of the syntax of the **NewOz** language.
**NewOz** is in fact, rather than a separate language, a new syntax for the existing **Oz**
programming language. The **Nozc** compiler translates **NewOz** code to equivalent, readable **Oz**
code, which is then fed to the `ozc` compiler. This compiled code can then be executed using
the `ozengine` program. For more background information, please read the main [README](../README.md) file.

This document is a tutorial to get started with the **NewOz** language, including code snippets to
serve as examples. If you are looking for help when using the **Nozc** compiler, please read the
main [README file](../README.md) on GitHub.

>This documentation is inspired by the official Mozart 2 documentation, located at http://mozart2.org

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
Valid names for variables are all series of alphanumeric characters, plus
the '_' character, starting with a lowercase letter : e.g. `a`, `myVariable95`, `f2ze_5d4f6d`.

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
- **Records** : records are dictionaries. They are described by a name, and a set of features. Each
  of those features may store a value, which can of course be a record itself, allowing to build
  complex data structures. Records can be declared as
  follows : `'myRecord(name:"John Doe" age:22 weight:85.3)`. Note the apostrophe, which is a
  mandatory character at the beginning of records names.\
  Note that you can omit features in a record description; in that case, the compiler will place
  numbers in their place for you. As such, `'exampleRecord(25 a:'c' 2)` is equivalent
  to `'exampleRecord(1:25 a:'c' 2:2)`.
- **Tuples** are ordered sets of elements, made accessible through their ordinal. They can be
  declared by using the `#` operator as follows : `2#4#5#'c'`. Access elements through their
  ordinal, e.g. : `variableStoringMyTuple.3`
- **Lists** : in **NewOz**, lists have a recursive definition. The head of a list is an expression
  of any type; while the tail of a list is an expression evaluated to a list. The last element of a
  list is indicated using the keyword `nil`. List can be defined in two ways : `(1::2::5::'c'::nil)` is
  equivalent to `[1, 2, 5, 'c']` (`nil` should be omitted when using the second notation). Accessing
  lists has to be done recursively : `listName.1` is the head, `listName.2` is the tailing list.
  Accessing the 4th element of a list could be done by writing `listName.2.2.2.1`, but **NewOz**'
  basic library also provides helper functions to do this.
- **Strings** : strings are sequences of characters enclosed in quotation marks.
  e.g. `"Hello World !"`.
- **Features** : features are denoted by integers, or literals similar to variable names

### Variable status
A variable `x` is always in one of the following statuses :
- **Free**: the constraint store does not know anything about `x`, apart from variables equalities
- **Determined** : the constraint store knows of a value assigned to `x`
- **Future** : the constraint store entails `x = f` for some future `f`
- **Kinded** : `x` is neither free, nor determined, nor future.

# **NewOz**'s Standard Library
//TODO generate it using https://delight-im.github.io/Javadoc-to-Markdown/, from the Javadoc of BuiltIns class
Every function or procedure built into the language is described on [this page](./Library.md), with a signature as follows :
```
map(+xs, +p, ?ys)
```
This signature specifies the number of arguments, their type, and their mode.
- **Types** : the type of an argument is indicated by its name, using the abbreviations in the following table :

| Abbreviation | Type    |
|--------------|---------|
| b            | Boolean |
| f            | Float   |
| i            | Integer |
| k            | Class   |
| p            | Procedure |
| r            | Record |
| s            | String |
| t            | Tuple |
| x y z        | Value |
| li           | Feature |
| xs           | List of elements of type x |
We use indices such as `r1` or `r2` to disambiguate several occurrences of arguments of the same type.
We combine these abbreviations as in `li` meaning `literal or integer` (i. e., feature).
We use the plural-`s` suffix to indicate lists of values of a certain type.
For instance, `is` stands for a list of integers.
This suffix can be repeated to indicate lists of lists etc.
Additionally, these arguments can be prefixed as in `lowI`, which indicates that the integer represents a lower bound.

- **Modes** : The arguments can have a mode denoted by the `+` and `?` symbols.
  Modes indicate the synchronisation behaviour of a procedure.
  The application of a procedure `P` waits until its inputs marked `+` are determined.
  If the input arguments are well-typed, `P` will return outputs, marked `?` of the specified types.
  Ill-typed input arguments will raise a type error exception.
  Types may be incompletely checked, especially those of output arguments: this happens when a value doesn't need to be completely processed to perform an operation, e. g., in `List.dropWhile`. 

>Important note : a nice feature of the **Oz** standard library is that many functions define an equivalent procedure which stores the result in an output variable.
> **NewOz** mimics this behavior in its library as well.
> For example, `isList(+x)` has an equivalent `isList(+x, ?b)`, in which the result of the operation is stored in `b` instead of being returned.\
> **Be careful though :** when calling one of those "Procedure versions" of functions, the last parameter (`b` here) has to denote an **unassigned / free value (as in *val*)**. Passing an assigned *val*, or a *var* will result in an error at runtime !