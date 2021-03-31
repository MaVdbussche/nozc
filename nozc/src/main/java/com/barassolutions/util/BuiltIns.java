package com.barassolutions.util;

import com.barassolutions.ClassDescriptor;
import com.barassolutions.MethodDef;
import com.barassolutions.Pattern;
import com.barassolutions.Variable;
import java.util.Collections;
import java.util.List;


//TODO all implicit functions/procs in Oz (Browse etc.)
public enum BuiltIns {

  BROWSE("Browse", "browse", BuiltInType.PROCEDURE,
      Collections.singletonList(new Variable(-1, "ToPrint", true, false)),
      null, null),
  NEWCELL("NewCell", "newCell", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "InitValue", true, false)),
      null, null),
  NOT("Not", "not", BuiltInType.FUNCTION,
      Collections.singletonList(new Variable(-1, "Target", true, false)),
      null, null);

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

  public String ozString() {
    return ozImage;
  }

  public String nozString() {
    return nozImage;
  }

  public BuiltInType type() {
    return type;
  }

  public List<Pattern> args() {
    return args;
  }

  public List<ClassDescriptor> descrs() {
    return descrs;
  }

  public List<MethodDef> meths() {
    return meths;
  }
}
