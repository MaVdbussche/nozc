package com.barassolutions;

import java.util.Arrays;

public enum Type {
  ANY("Any"),
  ATOM("Atom"),
  BOOLEAN("Boolean"),
  CHAR("Char"),
  FLOAT("Float"),
  INT("Int"),
  LIST("List"),
  NIL("Nil"),
  RECORD("Record"),
  STRING("String"),
  UNDERSCORE("_"),
  UNKNOWN("Unknown"),
  UNIT("Unit");

  private final String image;

  Type(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return this.image;
  }

  /**
   * An assertion that this type matches the specified type. If there is no match, an error message
   * is written.
   *
   * @param line         the line near which the mismatch occurs.
   * @param expectedType type with which to match.
   */
  public void mustMatchExpected(int line, Type expectedType) {
    if (!matchesExpected(expectedType)) {
      AST.interStatement.reportSemanticError(line,
          "Type %s doesn't match type %s", this, expectedType);
    }
  }

  /**
   * An assertion that this type matches one of the specified types. If there is no match, an error
   * message is returned.
   *
   * @param line          the line near which the mismatch occurs.
   * @param expectedTypes expected types.
   */
  public void mustMatchExpected(int line, Type... expectedTypes) {
    if (this == Type.ANY) {
      return;
    }
    for (Type expectedType : expectedTypes) {
      if (matchesExpected(expectedType)) {
        return;
      }
    }
    AST.interStatement.reportSemanticError(line,
        "Type %s doesn't match any of the expected types %s", this,
        Arrays.toString(expectedTypes));
  }

  /**
   * Does this type match the expected type? For now, "matches" means "equals".
   *
   * @param expected the type that this might match.
   * @return true or false.
   */
  public boolean matchesExpected(Type expected) {
    return this == ANY || expected == ANY || this.equals(expected);
  }
}
