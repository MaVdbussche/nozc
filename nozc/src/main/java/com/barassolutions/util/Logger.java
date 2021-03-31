package com.barassolutions.util;

import com.barassolutions.Nozc;

public class Logger {

  // Convention on Logging levels taken from https://logging.apache.org/log4j/2.x/log4j-api/apidocs/index.html
  public enum LogLevel {
    OFF(""), //No logging whatsoever
    FATAL("[FATAL] : "), //Little logging
    ERROR("[ERROR] : "),
    WARN("[WARN] : "),
    INFO("[INFO] : "),
    DEBUG("[DEBUG] : "), //Used for tests
    TRACE("[TRACE] : "),
    ALL(""); //Gimme the logs. All of them.

    private final String image;

    LogLevel(String image) {
      this.image = image;
    }

    @Override
    public String toString() {
      return image;
    }
  }

  public static void fatal(String message, Object... args) {
    if (Nozc.loglevel.ordinal() >= LogLevel.FATAL.ordinal()) {
      System.out.printf(LogLevel.FATAL + message, args);
      System.out.println();
    }
  }

  public static void error(String message, Object... args) {
    if (Nozc.loglevel.ordinal() >= LogLevel.ERROR.ordinal()) {
      System.out.printf(LogLevel.ERROR + message, args);
      System.out.println();
    }
  }

  public static void warn(String message, Object... args) {
    if (Nozc.loglevel.ordinal() >= LogLevel.WARN.ordinal()) {
      System.out.printf(LogLevel.WARN + message, args);
      System.out.println();
    }
  }

  public static void info(String message, Object... args) {
    if (Nozc.loglevel.ordinal() >= LogLevel.INFO.ordinal()) {
      System.out.printf(LogLevel.INFO + message, args);
      System.out.println();
    }
  }

  public static void debug(String message, Object... args) {
    if (Nozc.loglevel.ordinal() >= LogLevel.DEBUG.ordinal()) {
      System.out.printf(LogLevel.DEBUG + message, args);
      System.out.println();
    }
  }

  @Deprecated
  public static void trace(String message, Object... args) {
    if (Nozc.loglevel.ordinal() >= LogLevel.TRACE.ordinal()) {
      System.out.printf(LogLevel.TRACE + message, args);
      System.out.println();
    }
  }

  public static void log(String message, Object... args) {
    if (Nozc.loglevel.ordinal() >= LogLevel.ALL.ordinal()) {
      System.out.printf(LogLevel.ALL + message, args);
      System.out.println();
    }
  }
}
