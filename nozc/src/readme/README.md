# README

Thanks for downloading **Nozc** ! Please make sure you downloaded this from the official repository,
located at https://github.com/MaVdbussche/nozc.

## Requirements
*Those are the requirements for running **Nozc**. If you are looking for the requirements to build **Nozc** locally, head over to https://github.com/MaVdbussche/nozc/blob/master/BUILD.md*

* This application requires **Java 16 or higher** to run. You can download it from
  [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html),
  or from other vendors like [AdoptOpenJDK](https://adoptopenjdk.net).
  >On Linux, you probably should install `openjdk-16` via you OS package manager instead.

  Run `java -version` in your terminal to see the installed and configured *Java* version.
  Note that, if you have multiple *Java* versions installed, your operating system might not use
  the last one by default.
  Please refer to your OS documentation for help on how to configure a specific *Java* version
  as default.
  

* If you want to compile your **NewOz** code to a `.ozf` file executable by the `ozengine` command,
  you will need to have [**Mozart**](https://github.com/mozart/mozart2) installed on your system.
  In particular, you should have access to the `ozc` and `ozengine` commands in your environment.
  >Note that this requirement is not yet present in the current version of **Nozc**, as the system is not yet able to run **Mozart** by itself.
  > For now, you still need to copy the generated **Oz** code in **Mozart**'s *Emacs* environment and run it from there.
  > This will change in a future release.

## Running Nozc
To run `nozc`, place your terminal at the root of this downloaded folder, and run `./bin/nozc` (or `.\bin\nozc.bat`).
Use the `-help` option to print help on the correct usage.

## Want to contribute ?
**Nozc**, and **NewOz** as a language, are still in the early days of their development. Any help and suggestions are welcome !\
In particular, we are looking for feedback on the syntax : what looks good to you, what could be improved, what should be removed,...
If you would like to contribute to this project, you are welcomed to do so by reading the contribution guidelines located on [GitHub](https://github.com/MaVdbussche/nozc/blob/master/CONTRIBUTING.md).
I will try my best to address them as fast as I can.\
For now, **Nozc** is being developed in the context of my master thesis.
In the future, this compiler and new syntax might be fully integrated into the **Mozart** ecosystem,
which is being used in various university courses, as well as online MOOCs, as a learning tool on programming paradigms.\
Do not hesitate to contact me if you have any questions !