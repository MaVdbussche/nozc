// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions.toCleanUp;

/**
 * This class provides a high level interface for creating representation of Oz files.
 *
 * newOzC uses this interface to produce target Oz code from a newOz source
 * program. During the pre-analysis and analysis phases, newOzC produces partial
 * (in-memory) classes for the type declarations within the compilation block,
 * and during the code generation phase, it produces file-based classes for the
 * declarations.
 */
public class Emitter {

    /** Name of the file. */
    private String name;

    /** Destination directory for the class. */
    private String destDir;

    /**
     * Whether an error occurred while creating/writing the file.
     */
    private boolean errorHasOccurred;

    /**
     * Construct a CLEmitter instance.
     */
    public Emitter() {
        destDir = ".";
    }

    /**
     * Set the destination directory for the class file to the specified value.
     *
     * @param destDir
     *            destination directory.
     */
    public void destinationDir(String destDir) {
        this.destDir = destDir;
    }

    /**
     * Has an emitter error occurred up to now?
     *
     * @return true or false.
     */
    public boolean isInErrorState() {
        return errorHasOccurred;
    }

    public void addNoArgInstruction(int opcode) {}
}
