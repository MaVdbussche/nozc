package junit;

import com.barassolutions.Nozc;
import java.io.File;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * JUnit test case for running the nozc compiler on the NewOz test programs under tests/pass and
 * tests/fail folders using JavaCC frontend.
 */
public class NozcJavaCCTestCase {

  /**
   * This library allows us to test programs which call System#exit() through JUnit ! Which is the
   * case for our CLI application here.
   * @see <a href="https://stefanbirkner.github.io/system-rules/index.html">System Rules Online documentation</a>
   */
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  private static String[] args = null;

  /**
   * Run the nozc compiler against each pass-test file.
   */
  @Test
  public void testPassAll() {
    System.out.println("================================================");
    System.out.println("Starting testPass");

    File passTestsDir = new File("src/test/java/pass");
    File genClassDir = new File("src/test/java/pass");
    File[] files = passTestsDir.listFiles();
    for (int i = 0; files != null && i < files.length; i++) {
      if (files[i].toString().endsWith(".noz")) {
        System.out.printf("""
            Running nozc (with javacc frontend) on %s ...

            """, files[i].toString());
        args = new String[]{"-d", genClassDir.getAbsolutePath(),
            files[i].toString()};
        testPassFile();
        System.out.print("\n\n");
      }
    }
  }

  /**
   * Run the nozc compiler against a specific pass-test file.
   */
  private void testPassFile() {
    exit.expectSystemExitWithStatus(0);
    Nozc.main(args);
  }

  /**
   * Run the nozc compiler against each fail-test file.
   */
  @Test
  public void testFailAll() {
    System.out.println("================================================");
    System.out.println("Starting testFail");

    File failTestsDir = new File("src/test/java/fail");
    File genClassDir = new File("src/test/java/fail");
    File[] files = failTestsDir.listFiles();
    for (int i = 0; files != null && i < files.length; i++) {
      if (files[i].toString().endsWith(".noz")) {
        System.out.printf("""
            Running nozc (with javacc frontend) on %s ...

            """, files[i].toString());
        args = new String[]{"-d", genClassDir.getAbsolutePath(),
            files[i].toString()};
        testFailFile();
        System.out.print("\n\n");
      }
    }
  }

  /**
   * Run the nozc compiler against a specific fail-test file.
   */
  private void testFailFile() {
    exit.expectSystemExitWithStatus(1);
    Nozc.main(args);
  }
}
