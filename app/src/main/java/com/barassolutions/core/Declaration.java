package com.barassolutions.core;

import com.barassolutions.Emitter;

public interface Declaration {

  /**
   * Declare the name(s) in the specified context. All declarations must support this
   * method.
   *
   * @param context
   *            class context in which names are resolved.
   * @param partial
   *            the code emitter (basically an abstraction for producing the
   *            partial class).
   */
  public void preAnalyze(Context context, Emitter partial);
}
