package com.barassolutions.core;

import java.util.Map;

public class Record extends Term {

  private final String name;

  private final Map<Feature, Expression> members;

  public Record(int line, String name, Map<Feature, Expression> map) {
    super(line);
    this.name = name;
    this.members = map;
  }
}
