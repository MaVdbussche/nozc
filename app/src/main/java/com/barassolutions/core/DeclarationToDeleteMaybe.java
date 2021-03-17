package com.barassolutions.core;

import com.barassolutions.Emitter;

public interface DeclarationToDeleteMaybe {

  /**
   * Declare the name(s) in the specified context. All declarations must support this
   * method.
   *
   * @param context
   *            class context in which names are resolved.
   * @param partial
   *            the code emitter (basically an abstraction for producing the
   *            partial class). //TODO not used ?
   */
  public void preAnalyze(Context context, Emitter partial);
}
