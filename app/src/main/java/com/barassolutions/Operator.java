package com.barassolutions;

import com.barassolutions.JavaCCParserConstants;
import com.barassolutions.TokenOz;

public enum Operator {
  LAND(JavaCCParserConstants.tokenImage[JavaCCParserConstants.LAND]),
  LOR(JavaCCParserConstants.tokenImage[JavaCCParserConstants.LOR]),
  HASHTAG(JavaCCParserConstants.tokenImage[JavaCCParserConstants.HASHTAG]),
  COLCOL(JavaCCParserConstants.tokenImage[JavaCCParserConstants.COLCOL]);

  private final String image;

  Operator(String image) { this.image = image; }

  public String image() {
    return this.image;
  }

  public TokenOz getOzToken() {
    switch (this) {
      case LAND -> {
        return TokenOz.ANDTHEN;
      }
      case LOR -> {
        return TokenOz.ORELSE;
      }
      case HASHTAG -> {
        return TokenOz.HASHTAG;
      }
      case COLCOL -> {
        return TokenOz.COLCOL;
      }
      default -> {
        return null;
      }
    }
  }

  public String toString() {
    return this.image;
  }
}
