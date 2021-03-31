package junit;

import com.barassolutions.Nozc;
import com.barassolutions.util.Logger;
import java.io.File;
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
   *
   * @see <a href="https://stefanbirkner.github.io/system-rules/index.html">System Rules Online
   * documentation</a>
   */
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  private static String[] args = null;

  /**
   * Run the nozc compiler against a pass-test file.
   */
  @Test
  public void testPassHelloWorld() {
    String testedFile = "HelloWorld.noz";

    File passTest = new File("src/test/java/pass/"+testedFile);
    File genClassDir = new File("src/test/java/pass");
    System.out.print("\n\n");
    System.out.println("================================================");
    System.out.println("Starting testPass on " + passTest.toString() + " to test and compile.");

    args = new String[]{"-v", "DEBUG", "-d", genClassDir.getAbsolutePath(), passTest.toString()};

    exit.expectSystemExitWithStatus(0);
    Nozc.main(args);
  }

  /**
   * Run the nozc compiler against a pass-test file.
   */
  @Test
  public void testPassRecursion() {
    String testedFile = "Recursion.noz";

    File passTest = new File("src/test/java/pass/"+testedFile);
    File genClassDir = new File("src/test/java/pass");
    System.out.print("\n\n");
    System.out.println("================================================");
    System.out.println("Starting testPass on " + passTest.toString() + " to test and compile.");

    args = new String[]{"-v", "DEBUG", "-d", genClassDir.getAbsolutePath(), passTest.toString()};

    exit.expectSystemExitWithStatus(0);
    Nozc.main(args);
  }

  /**
   * Run the nozc compiler against a fail-test file.
   */
  @Test
  public void testFailAll() {
    String testedFile = "HelloWorldFailing.noz";

    File failTest = new File("src/test/java/pass/"+testedFile);
    File genClassDir = new File("src/test/java/pass");
    System.out.print("\n\n");
    System.out.println("================================================");
    System.out.println("Starting testFail on " + failTest.toString() + " to test and compile.");

    args = new String[]{"-v", "DEBUG", "-d", genClassDir.getAbsolutePath(), failTest.toString()};

    exit.expectSystemExitWithStatus(1);
    Nozc.main(args);
  }
}
