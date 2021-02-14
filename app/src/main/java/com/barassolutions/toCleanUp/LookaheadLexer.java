package com.barassolutions.toCleanUp;

import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Vector;

/**
 * Lexical analyzer. Provides a backtracking mechanism and uses the hand-written Lexer.
 */
public class LookaheadLexer {

  /**
   * The underlying, hand-written Lexer
   */
  private final Lexer lexer;

  /**
   * Backtracking vector
   */
  private Vector<TokenInfo> backtrackingQueue;

  /**
   * Nested queues for nested lookahead
   */
  private Vector<TokenInfo> nextQueue;
  private final Stack<Vector<TokenInfo>> queueStack;

  public boolean isLookingAhead;

  private TokenInfo previousToken;

  /**
   * Current token
   */
  private TokenInfo token;

  /**
   * Constructor from a file name
   */
  public LookaheadLexer(String filename) throws FileNotFoundException {
    lexer = new Lexer(filename);
    backtrackingQueue = new Vector<>();
    nextQueue = new Vector<>();
    queueStack = new Stack<>();
    isLookingAhead = false;
  }

  /**
   * Scan to the next token in input
   */
  public void next() {
    previousToken = token;
    if (backtrackingQueue.size() == 0) {
      token = lexer.getNextToken();
    } else {
      token = backtrackingQueue.remove(0);
    }
    if (isLookingAhead) {
      nextQueue.add(token);
    }
  }

  /**
   * Record the current pos to ba able to come back to it later.
   * Can be nested
   */
  public void recordPos() {
    isLookingAhead = true;
    queueStack.push(nextQueue);
    nextQueue = new Vector<>();
    nextQueue.add(previousToken);
    nextQueue.add(token);
  }

  public void returnToPos() {
    while (backtrackingQueue.size() > 0) {
      nextQueue.add(backtrackingQueue.remove(0));
    }
    backtrackingQueue = nextQueue;
    nextQueue = queueStack.pop();
    isLookingAhead = !(queueStack.empty());

    // Restore previous and current tokens
    previousToken = backtrackingQueue.remove(0);
    token = backtrackingQueue.remove(0);
  }

  public TokenInfo token() {
    return token;
  }
  public TokenInfo previousToken() {
    return previousToken;
  }

  public boolean errorHasOccurred() {
    return lexer.errorHasOccurred();
  }

  public String fileName() {
    return lexer.fileName();
  }
}
