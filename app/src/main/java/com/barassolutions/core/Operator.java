package com.barassolutions.core;

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

  public int getOzTokenInt() {
    switch (this) {
      case LAND -> {
        return TokenOz.LAND;
      }
      case LOR -> {
        return TokenOz.LOR;
      }
      case HASHTAG -> {
        return TokenOz.HASHTAG;
      }
      case COLCOL -> {
        return TokenOz.COLCOL;
      }
      default -> {
        return -1;
      }
    }
  }

  public String toString() {
    return this.image;
  }
}
