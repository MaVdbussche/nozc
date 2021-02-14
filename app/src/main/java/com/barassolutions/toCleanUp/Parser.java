package com.barassolutions.toCleanUp;

import static com.barassolutions.toCleanUp.TokenKind.EOF;
import static com.barassolutions.toCleanUp.TokenKind.IDENTIFIER;
import static com.barassolutions.toCleanUp.TokenKind.LPAREN;

/**
 * Recursive descent parser. Using a LookaheadLexer, reads the tokens and produces an AST.
 */
public class Parser {

  private LookaheadLexer lexer;

  private boolean isInError;
  private boolean isRecovered;

  /**
   * Constructs a Parser from the given lexical analyzer
   */
  public Parser(LookaheadLexer lexer) {
    this.lexer = lexer;
    isInError = false;
    isRecovered = true;
    lexer.next(); //Prepare for reading
  }

  public boolean errorHasOccurred() {
    return isInError;
  }

  //////////////////////////////
  // "Utility" methods
  //////////////////////////////

  /**
   * Is the current token the same as the one in param ?
   */
  private boolean see(TokenKind other) {
    return (other == lexer.token().kind());
  }

  /**
   * Is the next token the same as the one in param ?
   */
  private boolean have(TokenKind other) {
    if (see(other)) {
      lexer.next();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Attempt to match a token we're looking for with the current input token. If we succeed, scan
   * the token and go into a "isRecovered" state. If we fail, then what we do next depends on
   * whether or not we're currently in a "isRecovered" state: if so, we report the error and go into
   * an "Unrecovered" state; if not, we repeatedly scan tokens until we find the one we're looking
   * for (or EOF) and then return to a "isRecovered" state. This gives us a kind of poor man's
   * syntactic error recovery. The strategy is due to David Turner and Ron Morrison.
   */
  private void mustBe(TokenKind other) {
    if (lexer.token().kind() == other) {
      lexer.next();
      isRecovered = true;
    } else if (isRecovered) {
      isRecovered = false;
      reportParserError("%s found while expecting %s", lexer.token().image(), other.image());
    } else {
      // Attempt to recover by forcing a match
      while (!see(other) && !see(EOF)) {
        lexer.next();
      }
      if (see(other)) {
        lexer.next();
        isRecovered = true;
      }
    }
  }

  /**
   * Report a syntax error.
   */
  private void reportParserError(String message, Object... args) {
    isInError = true;
    isRecovered = false;
    System.err
        .printf("%s:%d: ", lexer.fileName(), lexer.token().line());
    System.err.printf(message, args);
    System.err.println();
  }

  //////////////////////////////
  // Lookahead
  //////////////////////////////

  /**
   * Are we looking at an IDENTIFIER followed by a LPAREN? Look ahead to find out.
   */
  private boolean seeIdentLParen() {
    lexer.recordPos();
    boolean res = have(IDENTIFIER) && see(LPAREN);
    lexer.returnToPos();
    return res;
  }
}
