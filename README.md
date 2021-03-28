# NOZC

[![GitHub](https://img.shields.io/github/license/mavdbussche/nozc)](LICENSE)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/mavdbussche/nozc)](releases)
[![GitHub repo size](https://img.shields.io/github/repo-size/mavdbussche/nozc)](https://github.com/MaVdbussche/nozc)


Nozc is compiler for NewOz, written in Java. It is able to translate NewOz files (denoted by the use of `.noz` extension) to Oz files (`.oz`), which are then be fed to the Mozart compiler (`ozc`).\
It provides an elegant command-line interface with various options, allowing for example to consult the intermediary Oz files, or to print a visual representation of the program's syntax tree in the terminal.\
This compiler was developed in the context of my master thesis, which can be found here : LINK.

## Table of Contents

- [Background](#background)
- [Install](#Install)
- [Requirements](#requirements)
- [Usage](#usage)
- [Contributing](#contributing)
- [Issues and discussion](#issues-and-discussion)
- [License](#license)

## Background


## Project structure

    .
    ├── examples                # Examples of NewOz programs, useful to get familiar with the syntax
    ├── gradle                  # A Gradle distribution used to simplify to build process
    ├── nozc
    │   ├── src
    │   │   ├── main            # Nozc compiler's source code
    │   │   └── test            # JUnit4 tests
    │   ├── build.gradle        # Gradle build configurations
    │   └── gradle.properties   # Gradle build configurations
    ├── releases                # Multi-platform, distributable Nozc releases
    ├── README.md               # This README file
    └── ...

## Install

This application does not need to be installe _per se_.
Instead, you will find in the [releases](releases) folder all versions of nozc published to date, both in `.tar` and `.zip` formats.\
Please take note that the last version should always be used; no support will be offered for outdated versions.

### By downloading files

The downloaded archive will have this form :

    .                           # nozc-X.X.zip
    ├── bin
    │   ├── nozc                # Unix executable file (shell script)
    │   └── nozc.bat            # Windows executable
    └── lib                     # Librairies and JARs necessary to run the app
> Please note that the Windows version of the program, although it should behave exactly the same way,
> has not been as thoroughly tested as the Linux one, and is thus more susceptible to bugs. 

Simply extract the archive in an empty folder, and you are set !

### By compiling `nozc` yourself

See [BUILD](BUILD.md)

## Requirements

* This application requires **Java 16 or higher** to run. You can download it from
[Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html),
or from other vendors like [AdoptOpenJDK](https://adoptopenjdk.net).
  >On Linux, you probably should install `openjdk-16` via you OS package manager instead.
  
  Run `java -version` in your terminal to see the installed and configured Java version.
  Note that, if you have multiple Java versions installed, your operating system might not use
  the last one by default.
  Please refer to your OS documentation for help on how to configure a specific Java version
  as default.
  

* If you want to compile your NewOz code to a `.ozf` file executable by the `ozengine` command,
  you will need to have [Mozart](https://github.com/mozart/mozart2) installed on your system.
  In particular, you should have access to the `ozc` and `ozengine` commands in your environment.
  >Note that this requirement in not technically true if you only want to use **Nozc** to translate
  > NewOz files to Oz. How much sense that really makes is a question only you can answer.
  
## Usage

When placed in the extracted folder, run the following command to see the valid usage and get started :
```
./bin/nozc -help
```
>On Windows, type `.\bin\nozc.bat -help` instead.

## Contributing

All contributions to this compiler are welcome. See [CONTRIBUTING](CONTRIBUTING.md) for more info.

Note: When editing the Readme, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## Issues and discussion

You are free to open issues on this repository for any remarks, suggestion, or bug reports.
I will try to address them as much as possible.

## Reference

Tools used for the making of this program include :

* [JavaCC](https://javacc.github.io/javacc/), a parser generator for use with Java applications, published under the [BSD License 2.0](https://javacc.github.io/javacc/#license).
* [Gradle](https://gradle.org/), a multi-platform build and packaging system.
* IntelliJ IDEA Utlimate, a modern Java IDE by [JetBrains](https://www.jetbrains.com/idea/).
* [JUnit4](https://junit.org/junit4/), a powerful testing framework for Java programs, with the [System Rules](https://stefanbirkner.github.io/system-rules/index.html) library on top.

The general structure of the program is inpired by the works of Bill Campbell, Swami Iyer, and Bahar Akbal-Delibaş on the [j--](https://www.cs.umb.edu/j--/index.html) compiler. The accompanying book has been of great help as well.

## License

[BSD © Martin Vandenbussche](LICENSE)
