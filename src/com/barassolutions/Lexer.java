package com.barassolutions;

import static com.barassolutions.TokenKind.AND;
import static com.barassolutions.TokenKind.ASSIGN;
import static com.barassolutions.TokenKind.AT;
import static com.barassolutions.TokenKind.ATTR;
import static com.barassolutions.TokenKind.BACKSLASH;
import static com.barassolutions.TokenKind.BOX;
import static com.barassolutions.TokenKind.BREAK;
import static com.barassolutions.TokenKind.CATCH;
import static com.barassolutions.TokenKind.CHAR_LITERAL;
import static com.barassolutions.TokenKind.CHOICE;
import static com.barassolutions.TokenKind.CIRCUMFLEX;
import static com.barassolutions.TokenKind.CLASS;
import static com.barassolutions.TokenKind.COLCOL;
import static com.barassolutions.TokenKind.COLCOLCOL;
import static com.barassolutions.TokenKind.COLLECT;
import static com.barassolutions.TokenKind.COLON;
import static com.barassolutions.TokenKind.COMMA;
import static com.barassolutions.TokenKind.COMMENTCHAR;
import static com.barassolutions.TokenKind.COMMERCAT;
import static com.barassolutions.TokenKind.COND;
import static com.barassolutions.TokenKind.CONTINUE;
import static com.barassolutions.TokenKind.DECLARE;
import static com.barassolutions.TokenKind.DEF;
import static com.barassolutions.TokenKind.DEFAULT;
import static com.barassolutions.TokenKind.DEFINE;
import static com.barassolutions.TokenKind.DEFPROC;
import static com.barassolutions.TokenKind.DIS;
import static com.barassolutions.TokenKind.DO;
import static com.barassolutions.TokenKind.DOLLAR;
import static com.barassolutions.TokenKind.DOT;
import static com.barassolutions.TokenKind.DOTDOT;
import static com.barassolutions.TokenKind.ELLIPSIS;
import static com.barassolutions.TokenKind.ELSE;
import static com.barassolutions.TokenKind.ELSECASE;
import static com.barassolutions.TokenKind.EQUAL;
import static com.barassolutions.TokenKind.EXPORT;
import static com.barassolutions.TokenKind.FAIL;
import static com.barassolutions.TokenKind.FALSE;
import static com.barassolutions.TokenKind.FEAT;
import static com.barassolutions.TokenKind.FINALLY;
import static com.barassolutions.TokenKind.FOR;
import static com.barassolutions.TokenKind.FROM;
import static com.barassolutions.TokenKind.FUNCTOR;
import static com.barassolutions.TokenKind.GE;
import static com.barassolutions.TokenKind.GT;
import static com.barassolutions.TokenKind.HASHTAG;
import static com.barassolutions.TokenKind.IDENTIFIER;
import static com.barassolutions.TokenKind.IF;
import static com.barassolutions.TokenKind.IMPL;
import static com.barassolutions.TokenKind.IMPORT;
import static com.barassolutions.TokenKind.IN;
import static com.barassolutions.TokenKind.INT_LITERAL;
import static com.barassolutions.TokenKind.LACCENT;
import static com.barassolutions.TokenKind.LAND;
import static com.barassolutions.TokenKind.LARROW;
import static com.barassolutions.TokenKind.LBARROW;
import static com.barassolutions.TokenKind.LBRACK;
import static com.barassolutions.TokenKind.LCURLY;
import static com.barassolutions.TokenKind.LE;
import static com.barassolutions.TokenKind.LNOT;
import static com.barassolutions.TokenKind.LOCK;
import static com.barassolutions.TokenKind.LOR;
import static com.barassolutions.TokenKind.LPAREN;
import static com.barassolutions.TokenKind.LT;
import static com.barassolutions.TokenKind.MATCH;
import static com.barassolutions.TokenKind.METH;
import static com.barassolutions.TokenKind.MINUS;
import static com.barassolutions.TokenKind.MOD;
import static com.barassolutions.TokenKind.MODULO;
import static com.barassolutions.TokenKind.NE;
import static com.barassolutions.TokenKind.NOT;
import static com.barassolutions.TokenKind.OF;
import static com.barassolutions.TokenKind.OR;
import static com.barassolutions.TokenKind.PIPE;
import static com.barassolutions.TokenKind.PLUS;
import static com.barassolutions.TokenKind.PREPARE;
import static com.barassolutions.TokenKind.PROP;
import static com.barassolutions.TokenKind.RACCENT;
import static com.barassolutions.TokenKind.RAISE;
import static com.barassolutions.TokenKind.RARROW;
import static com.barassolutions.TokenKind.RBRACK;
import static com.barassolutions.TokenKind.RCURLY;
import static com.barassolutions.TokenKind.REQUIRE;
import static com.barassolutions.TokenKind.RETURN;
import static com.barassolutions.TokenKind.RPAREN;
import static com.barassolutions.TokenKind.SEMI;
import static com.barassolutions.TokenKind.SKIP;
import static com.barassolutions.TokenKind.SLASH;
import static com.barassolutions.TokenKind.STAR;
import static com.barassolutions.TokenKind.STRING_LITERAL;
import static com.barassolutions.TokenKind.THIS;
import static com.barassolutions.TokenKind.THREAD;
import static com.barassolutions.TokenKind.TILDE;
import static com.barassolutions.TokenKind.TRUE;
import static com.barassolutions.TokenKind.TRY;
import static com.barassolutions.TokenKind.UNDERSCORE;
import static com.barassolutions.TokenKind.UNIT;
import static com.barassolutions.TokenKind.VAL;
import static com.barassolutions.TokenKind.VAR;
import static com.barassolutions.TokenKind.EOF;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Hashtable;

/**
 * A lexical analyzer/scanner. Maps the file input to the language tokens in the TokenInfo class.
 */
public class Lexer {

  public static final char EOFCH = CharReader.EOFCH;

  /**
   * Reserved keywords
   */
  private final Hashtable<String, TokenKind> reserved;

  /**
   * Source
   */
  private final CharReader input;
  private final String fileName;

  /**
   * Next char (not scanned yet !)
   */
  private char ch;

  /**
   * Current line
   */
  private int line;

  /**
   * Has an error been found ?
   */
  private boolean errorState;

  /**
   * Creates a Lexer.
   *
   * @param fileName the name of the file containing the source.
   * @throws FileNotFoundException when the named file cannot be found.
   */
  public Lexer(String fileName) throws FileNotFoundException {
    this.input = new CharReader(fileName);
    this.fileName = fileName;
    errorState = false;

    // Filling the keywords. We do this manually because we only need the language keywords
    // TODO keep this up-to-date with lexical grammar
    reserved = new Hashtable<>();
    reserved.put(AT.image(), AT);
    reserved.put(ATTR.image(), ATTR);
    reserved.put(BREAK.image(), BREAK);
    reserved.put(CATCH.image(), CATCH);
    reserved.put(CHOICE.image(), CHOICE);
    reserved.put(CLASS.image(), CLASS);
    reserved.put(COLLECT.image(), COLLECT);
    reserved.put(COND.image(), COND);
    reserved.put(CONTINUE.image(), CONTINUE);
    reserved.put(DECLARE.image(), DECLARE);
    reserved.put(DEF.image(), DEF);
    reserved.put(DEFPROC.image(), DEFPROC);
    reserved.put(DEFAULT.image(), DEFAULT);
    reserved.put(DIS.image(), DIS);
    reserved.put(DO.image(), DO);
    reserved.put(ELSE.image(), ELSE);
    reserved.put(ELSECASE.image(), ELSECASE);
    reserved.put(EXPORT.image(), EXPORT);
    reserved.put(FAIL.image(), FAIL);
    reserved.put(FALSE.image(), FALSE);
    reserved.put(FEAT.image(), FEAT);
    reserved.put(FINALLY.image(), FINALLY);
    reserved.put(FOR.image(), FOR);
    reserved.put(FROM.image(), FROM);
    reserved.put(FUNCTOR.image(), FUNCTOR);
    reserved.put(IF.image(), IF);
    reserved.put(IMPORT.image(), IMPORT);
    reserved.put(IN.image(), IN);
    reserved.put(LOCK.image(), LOCK);
    reserved.put(MATCH.image(), MATCH);
    reserved.put(METH.image(), METH);
    reserved.put(MOD.image(), MOD);
    reserved.put(NOT.image(), NOT);
    reserved.put(OF.image(), OF);
    reserved.put(OR.image(), OR);
    reserved.put(PREPARE.image(), PREPARE);
    reserved.put(PROP.image(), PROP);
    reserved.put(RAISE.image(), RAISE);
    reserved.put(REQUIRE.image(), REQUIRE);
    reserved.put(RETURN.image(), RETURN);
    reserved.put(SKIP.image(), SKIP);
    reserved.put(THIS.image(), THIS);
    reserved.put(THREAD.image(), THREAD);
    reserved.put(TRUE.image(), TRUE);
    reserved.put(TRY.image(), TRY);
    reserved.put(UNIT.image(), UNIT);
    reserved.put(VAL.image(), VAL);
    reserved.put(VAR.image(), VAR);

    // Prepare for reading
    nextCh();
  }

  /**
   * Advance the scanner to the next char in input, and update the line nb
   */
  private void nextCh() {
    line = input.line();
    try {
      ch = input.nextChar();
    } catch (Exception e) {
      reportScannerError("Error while reading input at line " + line);
    }
  }

  public TokenInfo getNextToken() {
    StringBuilder sb;
    boolean moreWhite = true;
    while (moreWhite) {
      while (isWhiteSpace(ch)) {
        nextCh();
      }
      if (ch == COMMENTCHAR.img()) {
        nextCh();
        if (ch == COMMENTCHAR.img()) {
          // One-line comment -> skip until next "\n"
          while (ch != '\n' && ch != EOFCH) {
            nextCh();
          }
        } else if (ch == STAR.img()) {
          // Multi-line comment -> skip until next */ TODO this means no support for nested comments
          nextCh();
          char prevCh = ch;
          while ((!(prevCh == STAR.img() && ch == COMMENTCHAR.img())) && (ch != EOFCH)) {
            prevCh = ch;
            nextCh();
          }
          nextCh();
        }
      } else {
        moreWhite = false;
      }
    }
    line = input.line();
    switch (ch) {
      case '(' -> {
        nextCh();
        return new TokenInfo(LPAREN, line);
      }
      case ')' -> {
        nextCh();
        return new TokenInfo(RPAREN, line);
      }
      case '{' -> {
        nextCh();
        return new TokenInfo(LCURLY, line);
      }
      case '}' -> {
        nextCh();
        return new TokenInfo(RCURLY, line);
      }
      case '[' -> {
        nextCh();
        if (ch == ']') {
          nextCh();
          return new TokenInfo(BOX, line);
        } else {
          return new TokenInfo(LBRACK, line);
        }
      }
      case ']' -> {
        nextCh();
        return new TokenInfo(RBRACK, line);
      }
      case ',' -> {
        nextCh();
        return new TokenInfo(COMMA, line);
      }
      case '.' -> {
        nextCh();
        if (ch == '.') {
          nextCh();
          if (ch == '.') {
            nextCh();
            return new TokenInfo(ELLIPSIS, line);
          } else {
            return new TokenInfo(DOTDOT, line);
          }
        } else {
          return new TokenInfo(DOT, line);
        }
      }
      case ';' -> {
        nextCh();
        return new TokenInfo(SEMI, line);
      }
      case ':' -> {
        nextCh();
        if (ch == ':') {
          nextCh();
          if (ch == ':') {
            nextCh();
            return new TokenInfo(COLCOLCOL, line);
          } else {
            return new TokenInfo(COLCOL, line);
          }
        } else if (ch == '=') {
          nextCh();
          return new TokenInfo(DEFINE, line);
        } else {
          return new TokenInfo(COLON, line);
        }
      }
      case '&' -> {
        nextCh();
        if (ch == '&') {
          nextCh();
          return new TokenInfo(LAND, line);
        } else {
          return new TokenInfo(AND, line);
        }
      }
      case '|' -> {
        nextCh();
        if (ch == '|') {
          nextCh();
          return new TokenInfo(LOR, line);
        } else {
          return new TokenInfo(PIPE, line);
        }
      }
      case '!' -> {
        nextCh();
        return new TokenInfo(LNOT, line);
      }
      case '\\' -> {
        nextCh();
        if (ch == '=') {
          nextCh();
          return new TokenInfo(NE, line);
        }
        return new TokenInfo(BACKSLASH, line);
      }
      case '+' -> {
        nextCh();
        return new TokenInfo(PLUS, line);
      }
      case '-' -> {
        nextCh();
        if (ch == '>') {
          nextCh();
          return new TokenInfo(RARROW, line);
        } else {
          return new TokenInfo(MINUS, line);
        }
      }
      case '*' -> {
        nextCh();
        return new TokenInfo(STAR, line);
      }
      case '/' -> { //TODO critical point : test extensively this behavior (conflict with comments ?)
        nextCh();
        return new TokenInfo(SLASH, line);
      }
      case '%' -> {
        nextCh();
        return new TokenInfo(MODULO, line);
      }
      case '#' -> {
        nextCh();
        return new TokenInfo(HASHTAG, line);
      }
      case '_' -> {
        nextCh();
        return new TokenInfo(UNDERSCORE, line);
      }
      case '$' -> {
        nextCh();
        return new TokenInfo(DOLLAR, line);
      }
      case '`' -> {
        nextCh();
        return new TokenInfo(LACCENT, line);
      }
      case '´' -> {
        nextCh();
        return new TokenInfo(RACCENT, line);
      }
      case '^' -> {
        nextCh();
        return new TokenInfo(CIRCUMFLEX, line);
      }
      case '~' -> {
        nextCh();
        return new TokenInfo(TILDE, line);
      }
      case '@' -> {
        nextCh();
        return new TokenInfo(COMMERCAT, line);
      }
      case '<' -> {
        nextCh();
        if (ch == '=') {
          nextCh();
          return new TokenInfo(LBARROW, line);
        } else if (ch == '-') {
          nextCh();
          return new TokenInfo(LARROW, line);
        } else {
          return new TokenInfo(LT, line);
        }
      }
      case '>' -> {
        nextCh();
        if (ch == '=') {
          nextCh();
          return new TokenInfo(GE, line);
        } else {
          return new TokenInfo(GT, line);
        }
      }
      case '=' -> {
        nextCh();
        if (ch == '=') {
          nextCh();
          return new TokenInfo(EQUAL, line);
        } else if (ch == '<') {
          nextCh();
          return new TokenInfo(LE, line);
        } else if (ch == '>') {
          nextCh();
          return new TokenInfo(IMPL, line);
        } else {
          return new TokenInfo(ASSIGN, line);
        }
      }
      case '\'' -> {
        sb = new StringBuilder();
        sb.append('\'');
        nextCh();
        if (ch == '\\') {
          nextCh();
          sb.append(escape());
        } else {
          sb.append(ch);
          nextCh();
        }
        if (ch=='\'') {
          sb.append('\'');
          nextCh();
        } else {
          reportScannerError(ch + " found by scanner where a closing ' was expected.");
          while (ch != '\'' && ch != '\n') {
            nextCh();
          }
        }
        return new TokenInfo(CHAR_LITERAL, sb.toString(), line);
      }
      case '"' -> {
        sb = new StringBuilder();
        sb.append('"');
        nextCh();
        while (ch != '"' && ch != EOFCH) {
          if (ch == '\\') {
            nextCh();
            sb.append(escape());
          } else {
            sb.append(ch);
            nextCh();
          }
        }
        if (ch == EOFCH) {
          reportScannerError("Unexpected end of file found in String");
        } else {
          // Scan the closing '
          sb.append('"');
          nextCh();
        }
        return new TokenInfo(STRING_LITERAL, sb.toString(), line);
      }
      case EOFCH -> {
        return new TokenInfo(EOF, line);
      }
      case '0' -> {
        //TODO do we handle octal numbers ? For now, handle as 0
        nextCh();
        return new TokenInfo(INT_LITERAL, "0", line);
      }
      case '1','2','3','4','5','6','7','8','9' -> {
        sb = new StringBuilder();
        while (isDigit(ch)) {
          sb.append(ch);
          nextCh();
        }
        return new TokenInfo(INT_LITERAL, sb.toString(), line);
      }
      default -> {
        if (isIdentifierStart(ch)) {
          sb = new StringBuilder();
          while (isIdentifierPart(ch)) {
            sb.append(ch);
            nextCh();
          }
          String id = sb.toString();
          if (reserved.containsKey(id)) {
            return new TokenInfo(reserved.get(id), line);
          } else {
            return new TokenInfo(IDENTIFIER, id, line);
          }
        } else {
          reportScannerError("Unidentified input token: '%c'", ch);
          nextCh();
          return getNextToken();
        }
      }
    }
  }

  //TODO what do we actually want to escape ?
  private String escape() {
    switch (ch) {
      case 'b' -> {
        nextCh();
        return "\\b";
      }
      case 't' -> {
        nextCh();
        return "\\t";
      }
      case 'n' -> {
        nextCh();
        return "\\n";
      }
      case 'f' -> {
        nextCh();
        return "\\f";
      }
      case 'r' -> {
        nextCh();
        return "\\r";
      }
      case '"' -> {
        nextCh();
        return "\"";
      }
      case '\'' -> {
        nextCh();
        return "\\'";
      }
      case '\\' -> {
        nextCh();
        return "\\\\";
      }
      default -> {
        reportScannerError("Badly formed escape: \\%c", ch);
        nextCh();
        return "";
      }
    }
  }

  /**
   * Register and report a lexical error
   */
  private void reportScannerError(String message, Object... args) {
    errorState = true;
    System.err.printf("%s:%d: ", fileName, line);
    System.err.printf(message, args);
    System.err.println();
  }

  private boolean isDigit(char c) {
    return Character.isDigit(c);
  }

  private boolean isLetter(char c) {
    return Character.isLetter(c);
  }

  private boolean isWhiteSpace(char c) {
    return Character.isWhitespace(c);
  }

  /**
   * TODO refine this to make sure we don't miss anything. There are more, see JP's thesis ! TODO
   * see also Java code for : Character#isJavaIdentifierStart(int)
   */
  private boolean isIdentifierStart(char c) {
    return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');
  }

  //TODO same thing, check here
  private boolean isIdentifierPart(char c) {
    return (isIdentifierStart(c) || isDigit(c) || isLetter(c));
  }

  public boolean errorHasOccurred() {
    return errorState;
  }

  public String fileName() {
    return fileName;
  }
}

/**
 * A buffered character reader. Abstracts out differences between platforms, mapping all new lines
 * to '\n'. Also, keeps track of line numbers where the first line is numbered 1.
 *
 * @author Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas
 */
class CharReader {

  /**
   * A representation of the end of file as a character.
   */
  public final static char EOFCH = (char) -1;

  /**
   * The underlying reader records line numbers.
   */
  private final LineNumberReader lineNumberReader;

  /**
   * Name of the file that is being read.
   */
  private final String fileName;

  /**
   * Construct a CharReader from a file name.
   *
   * @param fileName the name of the input file.
   * @throws FileNotFoundException if the file is not found.
   */
  public CharReader(String fileName) throws FileNotFoundException {
    lineNumberReader = new LineNumberReader(new FileReader(fileName));
    this.fileName = fileName;
  }

  /**
   * Scan the next character.
   *
   * @return the character scanned.
   * @throws IOException if an I/O error occurs.
   */
  public char nextChar() throws IOException {
    return (char) lineNumberReader.read();
  }

  /**
   * The current line number in the source file, starting at 1.
   *
   * @return the current line number.
   */
  public int line() {
    // LineNumberReader counts lines from 0.
    return lineNumberReader.getLineNumber() + 1;
  }

  /**
   * Return the file name.
   *
   * @return the file name.
   */
  public String fileName() {
    return fileName;
  }

  /**
   * Close the file.
   *
   * @throws IOException if an I/O error occurs.
   */
  public void close() throws IOException {
    lineNumberReader.close();
  }
}