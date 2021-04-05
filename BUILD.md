# Requirements

_These are the prerequisites to build this project. If you are just looking for the requirements to **run** it, please consult the main [README](README.md) file._\
Building this application requires to have to following components installed :

* [Gradle](https://gradle.org/) is the used build system (the Gradle Wrapper shipped with the
  project will select and download an appropriate version for you);
* [Java 16+](README.md#requirements) for the compilation itself.
* Other requirements are shipped with the code in the `bin` folder, or automatically downloaded
  by Gradle.

# Usage

In a terminal opened at the root of the project (the folder where this [BUILD](BUILD.md)
file is situated), run the following commands :
```
./gradlew init
./gradlew assembleDist
```
>This command will create a distributable, under both `.zip` and `.tar` formats, under `./nozc/build/distributions`.

>Gradle might signal you that some files are obsolete. If so, navigate to `nozc/src/main/java/com/barassolutions` and delete them.
> Then, rerun `.\gradlew assembleDist`

You may see all available Gradle tasks by running :
```
./gradlew tasks
```
>Interesting tasks include :
> * `./gradlew clean` to delete the folder `./nozc/build`
> * `./gradlew build` to fully build the project, including a run of the included JUnit tests.