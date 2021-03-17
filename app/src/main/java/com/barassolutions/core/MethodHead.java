package com.barassolutions.core;

import java.util.ArrayList;

/**
 * Remember that in Oz, method heads are in fact records
 */
public class MethodHead {

  private final String name;

  private final boolean isPrivate;

  private final ArrayList<MethodArg> args;

  private final boolean hasMoreArgs;

  public MethodHead(int line, String name, boolean isPrivate, ArrayList<MethodArg> args, boolean moreArgs) {
    this.name = name;
    this.isPrivate = isPrivate;
    this.args = args;
    this.hasMoreArgs = moreArgs;
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

}
