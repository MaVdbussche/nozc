package com.barassolutions.core;

import java.util.Arrays;

public enum Type {
  ANY,
  ATOM,
  BOOLEAN,
  CHAR,
  FLOAT,
  INT,
  LIST,
  NIL,
  RECORD,
  STRING,
  UNDERSCORE,
  UNKNOWN,
  UNIT;

  /**
   * An assertion that this type matches the specified type. If there is no
   * match, an error message is written.
   *
   * @param line
   *            the line near which the mismatch occurs.
   * @param expectedType
   *            type with which to match.
   */
  public void mustMatchExpected(int line, Type expectedType) {
    if (!matchesExpected(expectedType)) {
      AST.interStatement.reportSemanticError(line,
          "Type %s doesn't match type %s", this, expectedType);
    }
  }

  /**
   * An assertion that this type matches one of the specified types. If there
   * is no match, an error message is returned.
   *
   * @param line
   *            the line near which the mismatch occurs.
   * @param expectedTypes
   *            expected types.
   */
  public void mustMatchExpected(int line, Type ... expectedTypes) {
    if (this == Type.ANY)
      return;
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
   * Does this type match the expected type? For now, "matches" means
   * "equals".
   *
   * @param expected
   *            the type that this might match.
   * @return true or false.
   */
  public boolean matchesExpected(Type expected) {
    return this == Type.ANY || this.equals(expected);
  }
}
