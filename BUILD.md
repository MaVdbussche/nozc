# Requirements

Building this application requires to have to following components installed :

* [Gradle](https://gradle.org/) is the used build system (the Gradle Wrapper shipped with the
  project will select and download an appropriate version for you);
* [Java 16+](README.md#requirements) for the compilation itself.

# Usage

In a terminal opened at the root of the project (the folder where this [BUILD](BUILD.md)
file is situated), run the following commands :
```
./gradlew init
./gradlew assembleDist
```
>This command will create a distributable `.zip` and `.tar` under `./nozc/build/distributions`.

You may see all available Gradle tasks by running :
```
./gradlew tasks
```
>Interesting tasks include :
> * `./gradlew clean` to delete the folder `./nozc/build`
> * `./gradlew build` to fully build the project, including a run of the included JUnit tests.