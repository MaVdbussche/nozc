package com.barassolutions;

public class BuiltIns {

  public static String newCell(boolean capitalized) {
    if(capitalized) {
      return "NewCell";
    } else {
      return "newCell";
    }
  }

  public static String not(boolean capitalized) {
    if(capitalized) {
      return "Not";
    } else {
      return "not";
    }
  }
}
