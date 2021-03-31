package com.barassolutions.util;

import com.barassolutions.ClassDescriptor;
import com.barassolutions.MethodDef;
import com.barassolutions.Pattern;
import com.barassolutions.Variable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TODO all implicit functions/procs in Oz (Browse etc.)
/**
 * An enum containing all the functions, procedures, classes and functors known by the compiler.
 * They can be considered as built into the language itself, meaning they are available to be
 * referenced from any every scope in your NewOz program.
 */
public enum BuiltIns {

  /**
   * <code>browse(X)</code>
   * <p>
   * Displays <code>X</code> in the Mozart Browser
   */
  BROWSE("Browse", "browse", BuiltInType.PROCEDURE,
      Collections.singletonList(new Variable(-1, "ToPrint", true, false)),
      null, null),
  /**
   * @hidden
   */
  NEWCELL("NewCell", "newCell", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "InitValue", true, false)),
      null, null),
  /**
   * @hidden
   */
  NOT("Not", "not", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "Target", true, false)),
      null, null),
  /**
   * <code>toVirtualString(X, DepthI, WidthI)</code>
   * <p>
   * Returns a virtual string describing the value of <code>X</code>. Note that this does not block
   * on <code>X</code>. The value of <code>DepthI</code> and <code>WidthI</code> are used to limit
   * output of records in depth and width respectively.
   */
  toVirtualString("Value.toVirtualString", "toVirtualString", BuiltInType.FUNCTION,
      Arrays.asList(
          new Variable(-1, "X", true, false),
          new Variable(-1, "DepthI", true, false),
          new Variable(-1, "WidthI", true, false)),
      null, null),
  ;

  private final String ozImage;
  private final String nozImage;
  private final BuiltInType type;
  private final List<Pattern> args;
  private final List<ClassDescriptor> descrs;
  private final List<MethodDef> meths;

  BuiltIns(String ozImage, String nozImage, BuiltInType type,
      List<Pattern> args, List<ClassDescriptor> descrs, List<MethodDef> meths) {
    this.ozImage = ozImage;
    this.nozImage = nozImage;
    this.type = type;
    this.args = args;
    this.descrs = descrs;
    this.meths = meths;
  }

  /**
   * Returns the name of this function, procedure, class or functor in Oz.
   *
   * @return the name as known by Mozart
   */
  public String ozString() {
    return ozImage;
  }

  /**
   * Returns the name of this function, procedure, class or functor in NewOz.
   *
   * @return the name as known by Nozc
   */
  public String nozString() {
    return nozImage;
  }

  /**
   * @hidden
   */
  public BuiltInType type() {
    return type;
  }

  /**
   * @hidden
   */
  public List<Pattern> args() {
    return args;
  }

  /**
   * @hidden
   */
  public List<ClassDescriptor> descrs() {
    return descrs;
  }

  /**
   * @hidden
   */
  public List<MethodDef> meths() {
    return meths;
  }
}
