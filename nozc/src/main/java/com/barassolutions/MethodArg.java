package com.barassolutions;

import com.barassolutions.util.Utils;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;

public class MethodArg extends Pattern {

  @Nullable
  private Feature feature;

  private final String name;

  @Nullable
  private Expression defaultValue;

  public MethodArg(int line, @Nullable Feature feature, String name,
      @Nullable Expression defaultValue) {
    super(line);
    this.feature = feature;
    this.name = name;
    this.defaultValue = defaultValue;
  }

  public String name() {
    return name;
  }

  @Override
  public Iterable<Pattern> patterns() {
    return Collections.singleton(this);
  }

  public MethodArg analyze(Context context) {
    if (feature != null) {
      feature = (Feature) feature.analyze(context);
    }

    if (defaultValue != null) {
      defaultValue = defaultValue.analyze(context);
    }

    return this;
  }

  public void codegen(Emitter output) {
    if (feature != null) {
      output.literal(feature.image());
      output.token(TokenOz.COLON);
    }
    output.literal(Utils.ozFriendlyName(name));

    if (defaultValue != null) {
      output.space();
      output.token(TokenOz.LBARROW);
      output.space();
      defaultValue.codegen(output);
    }
  }

  public void writeToStdOut(PrettyPrinter p) {
    p.printf("<Argument>\n");
    p.indentRight();
    p.printf("<Feature: \"%s\">\n", feature != null ? feature.image() : "<none>");
    p.printf("<ArgumentDefinition name: \"%s\">\n", name);
    if (defaultValue != null) {
      p.printf("<Default value:>\n");
      p.indentRight();
      defaultValue.writeToStdOut(p);
      p.indentLeft();
      p.printf("</Default value>\n");
    }
    p.printf("</ArgumentDefinition>\n");
    p.indentLeft();
    p.printf("</Argument>\n");
  }
}
