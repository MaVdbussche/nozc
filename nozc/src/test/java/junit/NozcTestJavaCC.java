// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import com.barassolutions.Nozc;
import java.io.File;
import junit.framework.TestCase;

/**
 * JUnit test case for running the nozc compiler on the NewOz test programs under tests/pass and
 * tests/fail folders using JavaCC frontend.
 */
public class NozcTestJavaCC extends TestCase {

  /**
   * Construct a NozcTestJavaCC object.
   */
  public NozcTestJavaCC() {
    super("JUnit test case for the nozc compiler");
  }

  /**
   * Entry point.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(NozcTestJavaCC.class);
  }

  /**
   * Run the nozc compiler against each pass-test file.
   */
  public void testPass() {
    File passTestsDir = new File("../pass");
    File genClassDir = new File("../pass/generated");
    File[] files = passTestsDir.listFiles();
    boolean errorHasOccurred = false;
    for (int i = 0; files != null && i < files.length; i++) {
      if (files[i].toString().endsWith(".noz")) {
        String[] args;
        System.out.printf("""
            Running nozc (with javacc frontend) on %s ...

            """, files[i].toString());
        args = new String[]{"-d", genClassDir.getAbsolutePath(),
            files[i].toString()};
        Nozc.main(args);
        System.out.print("\n\n");

        // true even if a single test fails
        errorHasOccurred |= Nozc.errorHasOccurred;
        System.out.println("ErrorHasOccured :"+errorHasOccurred);
      }
    }

    // We want all tests to pass
    assertFalse(errorHasOccurred);
  }

  /**
   * Run the nozc compiler against each fail-test file.
   */
  public void testFail() {
    File failTestsDir = new File("../fail");
    File genClassDir = new File("../fail/generated");
    File[] files = failTestsDir.listFiles();
    boolean errorHasOccurred = true;
    for (int i = 0; files != null && i < files.length; i++) {
      if (files[i].toString().endsWith(".noz")) {
        String[] args;
        System.out.printf("""
            Running nozc (with javacc frontend) on %s ...

            """, files[i].toString());
        args = new String[]{"-d", genClassDir.getAbsolutePath(),
            files[i].toString()};
        Nozc.main(args);
        System.out.print("\n\n");

        // true only if all tests fail
        errorHasOccurred &= Nozc.errorHasOccurred;
      }
    }

    // We want all tests to fail
    assertTrue(errorHasOccurred);
  }
}
