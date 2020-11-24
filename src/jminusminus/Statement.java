// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import com.barassolutions.AST;

/**
 * The AST node for a statement (includes expressions). The mother of all
 * statements.
 */

abstract class Statement extends AST {

    /**
     * Construct an AST node for a statement given its line number.
     *
     * @param line
     *            line in which the statement occurs in the source file.
     */

    protected Statement(int line) {
        super(line);
    }

}