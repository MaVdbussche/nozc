package junit;

import com.barassolutions.Nozc;
import java.io.File;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * JUnit test case for running the nozc compiler on the NewOz test programs under tests/pass and
 * tests/fail folders using JavaCC frontend.
 */
public class NozcJavaCCTestCase {

  /**
   * This library allows us to test programs which call System#exit() through JUnit ! Which is the
   * case for our CLI application here.
   *
   * @see <a href="https://stefanbirkner.github.io/system-rules/index.html">System Rules Online
   * documentation</a>
   */
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  private static String[] args = null;

  /**
   * Run the nozc compiler against all pass-test files.
   */
  @Test
  public void testPassAll() {
    System.out.println("================================================");
    System.out.println("Starting testPass");

    File passTestsDir = new File("src/test/java/pass");
    File genClassDir = new File("src/test/java/pass");
    File[] files = passTestsDir.listFiles();
    System.out.println("Found " + files.length + " potential files to test and compile.");

    testPassFiles(files, genClassDir);
  }

  /**
   * Not pretty, but the best way I found to test all files is to do this recursion, since the Main
   * method calls System#exit()...
   */
  private void testPassFiles(File[] files, File genClassDir) {
    if (files[0].toString().endsWith(".noz")) {
      System.out.printf("""
          Running nozc (with javacc frontend) on %s ...

          """, files[0].toString());
      args = new String[]{"-d", genClassDir.getAbsolutePath(),
          files[0].toString()};

      exit.expectSystemExitWithStatus(0);
      exit.checkAssertionAfterwards(
          () -> testPassFiles(Arrays.copyOfRange(files, 1, files.length), genClassDir));
      Nozc.main(args);

      System.out.print("\n\n");
    } else {
      testPassFiles(Arrays.copyOfRange(files, 1, files.length), genClassDir);
    }


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
