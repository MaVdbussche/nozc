package com.barassolutions;

/**
 * An enum of token kinds. Each entry in this enum represents the kind of a
 * token along with its image (string representation).
 *
 * When you add a new token to the scanner and lexical grammar, you must also add
 * an entry to this enum specifying the kind and image of the new token.
 */
//TODO keep this up-to-date with lexical grammar
enum TokenKind {
  EOF("<EOF>"), AT("at"), ATTR("attr"), BREAK("break"), CASE("case"),
      CATCH("catch"), CHOICE("choice"), CLASS("class"), COLLECT("collect"),
      COND("cond"), CONTINUE("continue"), DECLARE("declare"), DEF("def"),
      DEFPROC("defproc"), DEFAULT("default"), DIS("dis"), DO("do"),
      ELSE("else"), ELSECASE("elsecase"), EXPORT("export"), EXTENDS("extends"),
      FAIL("fail"), FALSE("false"), FEAT("feat"), FINALLY("finally"), FOR("for"),
      FROM("from"), FUNCTOR("functor"), IF("if"), IMPORT("import"), IN("in"),
      LOCK("lock"), MATCH("match"), METH("meth"), MOD("mod"),
      NOT("not"), OF("of"), OR("or"), PREPARE("prepare"), PROP("prop"),
      RAISE("raise"), REQUIRE("require"), RETURN("return"), SKIP("skip"), THIS("this"),
      THREAD("thread"), TRUE("true"), TRY("try"), UNIT("unit"),
      VAL("val"), VAR("var"),
      COMMENTCHAR("/"),
      ASSIGN("="), DEFINE(":="), EQUAL("=="), NE("\\="), LT("<"), GT(">"),
      LE("=<"), GE(">="), LBARROW("<="), IMPL("=>"), AND("&"), LAND("&&"),
      PIPE("|"), LOR("||"), LNOT("!"), MINUS("-"), PLUS("+"),
      STAR("*"), SLASH("/"), BACKSLASH("\\"), MODULO("%"), HASHTAG("#"),
      UNDERSCORE("_"), DOLLAR("$"), APOSTROPHE("'"), QUOTE("\""), LACCENT("`"),
      RACCENT("´"), CIRCUMFLEX("^"), BOX("[]"), TILDE("~"), DEGREE("°"), COMMERCAT("@"),
      LARROW("<-"), RARROW("->"), COLCOL("::"), COLCOLCOL(":::"),
      COMMA(","), DOT("."), LPAREN("("), RPAREN(")"), LCURLY("{"), RCURLY("}"),
      LBRACK("["), RBRACK("]"), SEMI(";"), COLON(":"), DOTDOT(".."),
      ELLIPSIS("..."),
      IDENTIFIER("<IDENTIFIER>"), INT_LITERAL("<INT_LITERAL>"), CHAR_LITERAL("<CHAR_LITERAL>"), STRING_LITERAL(
      "<STRING_LITERAL>");

  /** The token's string representation. */
  private final String image;

  /**
   * Construct an instance TokenKind given its string representation.
   *
   * @param image
   *            string representation of the token.
   */
  private TokenKind(String image) {
    this.image = image;
  }

  /**
   * Return the image of the token.
   *
   * @return the token's image.
   */
  public String image() {
    return image;
  }

  /**
   * Return the char image of the token.
   * No guarantee made if the image is multiple chars long ! You should check before calling this.
   */
  public char img() {
    return image.charAt(0);
  }
  /**
   * Return the string representation of the token.
   *
   * @return the token's string representation.
   */
  public String toString() {
    return image;
  }

}

/**
 * A representation of tokens returned by the lexical analyzer method,
 * getNextToken(). A token has a kind identifying what kind of token it is, an
 * image for providing any semantic text, and the line in which it occurred in
 * the source file.
 */
class TokenInfo {

  /** Token kind. */
  private final TokenKind kind;

  /**
   * Semantic text (if any). For example, the identifier name when the token
   * kind is IDENTIFIER. For tokens without a semantic text, it is simply its
   * string representation. For example, "+=" when the token kind is
   * PLUS_ASSIGN.
   */
  private final String image;

  /** Line in which the token occurs in the source file. */
  private final int line;

  /**
   * Construct a TokenInfo from its kind, the semantic text forming the token,
   * and its line number.
   *
   * @param kind
   *            the token's kind.
   * @param image
   *            the semantic text comprising the token.
   * @param line
   *            the line in which the token occurs in the source file.
   */
  public TokenInfo(TokenKind kind, String image, int line) {
    this.kind = kind;
    this.image = image;
    this.line = line;
  }

  /**
   * Construct a TokenInfo from its kind, and its line number. Its image is
   * simply its string representation.
   *
   * @param kind
   *            the token's identifying number.
   * @param line
   *            identifying the line on which the token was found.
   */
  public TokenInfo(TokenKind kind, int line) {
    this(kind, kind.toString(), line);
  }

  /**
   * Return the token's string representation.
   *
   * @return the string representation.
   */
  public String tokenRep() {
    return kind.toString();
  }

  /**
   * Return the semantic text associated with the token.
   *
   * @return the semantic text.
   */
  public String image() {
    return image;
  }

  /**
   * Return the line number associated with the token.
   *
   * @return the line number.
   */
  public int line() {
    return line;
  }

  /**
   * Return the token's kind.
   *
   * @return the kind.
   */
  public TokenKind kind() {
    return kind;
  }

  /**
   * Return the token's image.
   *
   * @return the image.
   */
  public String toString() {
    return image;
  }

}

