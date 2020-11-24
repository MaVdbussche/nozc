// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package com.barassolutions;

/**
 * This class provides a high level interface for creating (in-memory and file
 * based) representation of Java classes.
 *
 * newOzC uses this interface to produce target Oz code from a newOz source
 * program. During the pre-analysis and analysis phases, newOzC produces partial
 * (in-memory) classes for the type declarations within the compilation block,
 * and during the code generation phase, it produces file-based classes for the
 * declarations.
 */
public class Emitter {
}
