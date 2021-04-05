# NewOz and Nozc

[![Travis (.com)](https://img.shields.io/travis/com/MaVdbussche/nozc?label=Build)](https://www.travis-ci.com/github/MaVdbussche/nozc)
[![GitHub](https://img.shields.io/github/license/MaVdbussche/nozc?label=License)](LICENSE)
[![GitHub tag (latest SemVer pre-release)](https://img.shields.io/github/v/tag/MaVdbussche/nozc?include_prereleases&label=Release&sort=semver)](releases)
[![GitHub repo size](https://img.shields.io/github/repo-size/MaVdbussche/nozc?label=Size)](https://github.com/MaVdbussche/nozc)

**Nozc** is a compiler for **NewOz**, written in *Java*. It is able to translate **NewOz** files (denoted by the use of `.noz` extension) to **Oz** files (`.oz`), which can then be fed to the existing **Mozart** compiler (`ozc`).\
It provides an elegant command-line interface with various options, allowing for example to finely custom the logging verbosity, or to print a visual representation of the program's syntax tree in the terminal.\
This compiler was developed in the context of my master thesis, which can be found here : [LINK]() (*coming soon*).

## Table of Contents

- [Background](#background)
- [Project structure](#project-structure)
- [Install](#install)
- [Requirements](#requirements)
- [Usage](#usage)
- [Contributing](#contributing)
- [Issues and discussion](#issues-and-discussion)
- [License](#license)

## Background

The [**Oz** programming language](http://mozart2.org) is a multi-paradigm language that is designed for advanced, concurrent, networked, soft real-time, and reactive applications.
**Oz** provides the salient features of **object-oriented programming** (including state, abstract data types, objects, classes, and inheritance),
**functional programming** (including compositional syntax, first-class procedures/functions, and lexical scoping), as well as
**logic programming and constraint programming** (including logic variables, constraints, disjunction constructs, and programmable search mechanisms).
It allows users to dynamically create any number of sequential threads.
The threads are dataflow threads in the sense that a thread executing an operation will suspend until all needed operands have a well-defined value.

The [**Mozart** programming system](https://github.com/mozart/mozart2), which is the official implementation of **Oz**, has been **developed in the 1990s** by researchers from DFKI (the German Research Center for Artificial Intelligence), SICS (the Swedish Institute of Computer Science), the University of the Saarland, UCLouvain (the Université catholique de Louvain), and others.

Over the years, the **Oz** programming language has been used with success in various MOOCs and university courses.
It's **multi-paradigm philosophy** proved to be an invaluable strength in teaching students the basics of programming paradigms, through its *one-fits-all* approach.

However, it has become obvious over the years that the syntax of the language has become a drawback.
In particular, **Oz** has not been updated like other languages have, which is hindering its ability to keep a growing community of developers around it.

The **objective behind NewOz**, launched by Prof. Peter Van Roy at [UCLouvain](https://uclouvain.be/fr/index.html) is ambitious : bringing the syntax of Oz to par with modern programming languages, while **keeping alive the philosophy** that makes its strength :
giving access to a plethora of programming paradigms in a single, unique environment. This process has started in 2020, with the [master thesis](https://dial.uclouvain.be/memoire/ucl/object/thesis:25311) of Jean-Pacifique Mbonyincungu, who created a first design for the **NewOz** syntax, heavily inspired by *Scala*.
The 2021 [thesis]() (_coming soon_) of Martin Vandenbussche continues this work by making more refinements to the syntax, as well as creating a fully fletched compiler around it.

### Want to help ?
All your help is welcome and greatly appreciated. Please visit the [CONTRIBUTING](CONTRIBUTING.md) file to get in touch !

## Project structure

    .
    ├── bin                     # Binaries used to compile the program, shipped to ease the installation process
    ├── examples                # Examples of NewOz programs, useful to get familiar with the syntax
    ├── gradle                  # A Gradle distribution used to simplify to build process
    ├── nozc
    │   ├── src
    │   │   ├── main            # Nozc compiler's source code
    │   │   └── test            # JUnit4 tests
    │   ├── build.gradle        # Gradle build configurations
    │   └── gradle.properties   # Gradle build properties
    ├── releases                # All the multi-platform, distributable Nozc releases to date.
    ├── README.md               # This README file
    └── ...

## Install

This application does not need to be installed *per se*.
Instead, you will find in the [releases](releases) folder all versions of Nozc published to date, both in `.tar` and `.zip` formats.\
Please note that the last version should always be used; no support will be offered for outdated versions.

### By downloading files

The downloaded archive will have this form :

    .                           # nozc-X.X.zip
    ├── bin
    │   ├── nozc                # Unix executable file (shell script)
    │   └── nozc.bat            # Windows executable
    ├── lib                     # Librairies and JARs necessary to run the app
    └── README.md               # A small installation guide and contribution invite
> Please note that the *Windows* version of the program, although it should behave exactly the same way,
> has not been as thoroughly tested as the *Linux* one, and is thus more prone to bugs. 

Simply extract the archive in an empty folder, and run the executable script applicable to your platform in `bin`. See the [usage section](#usage).

### By compiling `nozc` yourself

See [BUILD](BUILD.md)

## Requirements

*These are the prerequisites to run this project. If you are looking for the requirements to **build** it, please consult the [BUILD](BUILD.md) file.*

* This application requires **Java 16 or higher** to run. You can download it from
[Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html),
or from other vendors like [AdoptOpenJDK](https://adoptopenjdk.net).
  >On Linux, you probably should install `openjdk-16` via your OS package manager instead.
  
  Run `java -version` in your terminal to see the installed and configured *Java* version.
  Note that, if you have multiple *Java* versions installed, your operating system might not use
  the last one by default.
  Please refer to your OS documentation for help on how to configure a specific *Java* version
  as default.
  

* If you want to compile your **NewOz** code to a `.ozf` file executable by the `ozengine` command,
  you will need to have [Mozart](https://github.com/mozart/mozart2) installed on your system.
  In particular, you should have access to the `ozc` and `ozengine` commands in your environment.
  >Note that this requirement is not yet present in the current version of **Nozc**, as the system is not yet able to run **Mozart** by itself.
  > For now, you still need to copy the generated **Oz** code in **Mozart**'s *Emacs* environment and run it from there.
  > This will change in a future release.
  
## Usage

When placed in the extracted folder, run the following command to see the valid usage and get started :
```
./bin/nozc -help
```
>On *Windows*, type `.\bin\nozc.bat -help` instead.

## Contributing

All contributions, to both **NewOz** as a language, and **Nozc** as a compiler, are welcome. See [CONTRIBUTING](CONTRIBUTING.md) for more information.

Note: When editing the Readme, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## Issues and discussion

You are free to open issues on this repository for any remarks, suggestion, or bug reports.
I will try to address them as much as possible.

## Reference

Tools used for the making of this program include :

* [JavaCC](https://javacc.github.io/javacc/), a parser generator for use with *Java* applications, published under the [BSD License 2.0](https://javacc.github.io/javacc/#license).
* [Gradle](https://gradle.org/), a multi-platform build and packaging system.
* IntelliJ IDEA Utlimate, a modern *Java* IDE by [JetBrains](https://www.jetbrains.com/idea/).
* [JUnit4](https://junit.org/junit4/), a powerful testing framework for Java programs, with the [System Rules](https://stefanbirkner.github.io/system-rules/index.html) library on top.

The general structure of the program is inpired by the works of Bill Campbell, Swami Iyer, and Bahar Akbal-Delibaş on the [j--](https://www.cs.umb.edu/j--/index.html) compiler. The accompanying book has been of great help as well.

## License

[BSD © Martin Vandenbussche](LICENSE)
