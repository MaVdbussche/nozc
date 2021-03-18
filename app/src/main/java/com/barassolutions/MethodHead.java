package com.barassolutions;

import java.util.ArrayList;

/**
 * Remember that in Oz, method heads are in fact records
 */
public class MethodHead {

  private final String name;

  private final boolean isPrivate;

  private final ArrayList<MethodArg> args;

  private final boolean hasMoreArgs;

  private final boolean isAFunction;

  public MethodHead(int line, String name, boolean isPrivate, ArrayList<MethodArg> args, boolean moreArgs, boolean isAFunction) {
    this.name = name;
    this.isPrivate = isPrivate;
    this.args = args;
    this.hasMoreArgs = moreArgs;
    this.isAFunction = isAFunction;
  }

  public String name() {
    return name;
  }

  public ArrayList<MethodArg> args() {
    return this.args;
  }

  public boolean hasMorArgs() {
    return hasMoreArgs;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public boolean isAFunction() {
    return  isAFunction;
  }
}
