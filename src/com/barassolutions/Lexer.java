package com.barassolutions;

import java.io.*;
import java.util.Hashtable;

import static com.barassolutions.TokenKind.*;

/**
 * A lexical analyzer/scanner. Maps the file input to the language tokens in the TokenInfo class.
 */
public class Lexer {

    public static final char EOF = CharReader.EOFCH;

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
            reportScannerError("Error while reading input at line "+line);
        }
    }

    public TokenInfo getNextToken() {
        StringBuffer sb;
        boolean moreWhite = true;
        while (moreWhite) {
            while (isWhiteSpace(ch)) {
                nextCh();
            }
            if (ch == COMMENTCHAR.image().charAt(0)) {
                nextCh();
                if (ch == COMMENTCHAR.image().charAt(0)) {
                    // One-line comment -> skip until next "\n"
                    while (ch != '\n' && ch != EOF) {
                        nextCh();
                    }
                } else if (ch == STAR.image().charAt(0)) {
                    // Multi-line comment -> skip until next */ TODO this means no support for nested comments
                    nextCh();
                    char prevCh = ch;
                    while ((!(prevCh == STAR.image().charAt(0) && ch == COMMENTCHAR.image().charAt(0))) && (ch != EOF)) {
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
        //TODO continue here
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
     * TODO refine this to make sure we don't miss anything. There are more, see JP's thesis !
     * TODO see also Java code for : Character#isJavaIdentifierStart(int)
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