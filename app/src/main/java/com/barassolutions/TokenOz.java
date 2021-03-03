package com.barassolutions;

/**
 * Token literal values and constants for Oz.
 * Manually maintained to mirror JavaCCParserConstants.java TODO use TokenInfo/TokenKind instead
 */
public interface TokenOz {

  int EOF = 0;
  int ANDTHEN = 1;
  int AT = 2;
  int ATTR = 3;
  int BREAK = 4;
  int CASE = 5 ;
  int CATCH = 6 ;
  int CHOICE = 7 ;
  int CLASS = 8 ;
  int COLLECT = 9 ;
  int COND = 10 ;
  int CONTINUE = 11 ;
  int DECLARE = 12 ;
  int DEFAULT = 13 ;
  int DEFINE = 14 ;
  int DIS = 15 ;
  int DIV = 16 ;
  int DO = 17 ;
  int ELSE = 18 ;
  int ELSECASE = 19 ;
  int ELSEIF = 20 ;
  int ELSEOF = 21 ;
  int END = 22 ;
  int EXPORT = 23 ;
  int FAIL = 24 ;
  int FALSE = 25 ;
  int FEAT = 26 ;
  int FINALLY = 27 ;
  int FOR = 28 ;
  int FROM = 29 ;
  int FUN = 30 ;
  int FUNCTOR = 31 ;
  int IF = 32 ;
  int IMPORT = 33 ;
  int IN = 34 ;
  int LAZY = 35 ;
  int LOCAL = 36 ;
  int LOCK = 37 ;
  int METH = 38 ;
  int MOD = 39 ;
  int NIL = 40 ;
  int NOT = 41 ;
  int OF = 42 ;
  int OR = 43;
  int ORELSE = 44;
  int PREPARE = 45;
  int PROC = 46;
  int PROP = 47;
  int RAISE = 48;
  int REQUIRE = 49;
  int RETURN = 50;
  int SELF = 51;
  int SKIP = 52;
  int THEN = 53;
  int THREAD = 54;
  int TRUE = 55;
  int TRY = 56;
  int UNIT = 57;

  /** Literal token values */
  String[] tokenImage = {
      "<EOF>",
      "andthen",
      "at",
      "attr",
      "break",
      "case",
      "catch",
      "choice",
      "class",
      "collect",
      "cond",
      "continue",
      "declare",
      "default",
      "define",
      "dis",
      "div",
      "do",
      "else",
      "elsecase",
      "elseif",
      "elseof",
      "end",
      "export",
      "fail",
      "false",
      "feat",
      "finally",
      "for",
      "from",
      "fun",
      "functor",
      "if",
      "import",
      "in",
      "lazy",
      "local",
      "lock",
      "meth",
      "mod",
      "nil",
      "not",
      "of",
      "or",
      "orelse",
      "prepare",
      "proc",
      "prop",
      "raise",
      "require",
      "return",
      "self",
      "skip",
      "then",
      "thread",
      "true",
      "try",
      "unit"
  };
}
