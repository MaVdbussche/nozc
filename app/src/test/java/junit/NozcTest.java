// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import com.barassolutions.NewOzToOzMain;
import junit.framework.TestCase;

import java.io.File;

/**
 * JUnit test case for running the nozc compiler on the nozc test programs under
 * tests/pass and tests/fail folders.
 */

public class NozcTest extends TestCase {

    /**
     * Construct a NozcTest object.
     */
    public NozcTest() {
        super("JUnit test case for the nozc compiler");
    }

    /**
     * Run the nozc compiler against each pass-test file under the folder
     * specified by PASS_TESTS_DIR property in the build.xml file. FRONT_END
     * property determines the frontend (handwritten or JavaCC) to use.
     */
    public void testPass() {
        File passTestsDir = new File(System.getProperty("PASS_TESTS_DIR"));
        File genClassDir = new File(System.getProperty("GEN_CLASS_DIR"));
        File[] files = passTestsDir.listFiles();
        boolean errorHasOccurred = false;
        for (int i = 0; files != null && i < files.length; i++) {
            if (files[i].toString().endsWith(".java")) {
                String[] args;
                System.out.printf("""
                        Running j-- (with handwritten frontend) on %s ...

                        """, files[i]
                        .toString());
                args = new String[] { "-d", genClassDir.getAbsolutePath(),
                        files[i].toString() };
                NewOzToOzMain.main(args);
                System.out.print("\n\n");

                // true even if a single test fails
                errorHasOccurred |= NewOzToOzMain.errorHasOccurred();
            }
        }

        // We want all tests to pass
        assertFalse(errorHasOccurred);
    }

    /**
     * Run the j-- compiler against each fail-test file under the folder
     * specified by FAIL_TESTS_DIR property in the build.xml file. FRONT_END
     * property determines the frontend (handwritten or JavaCC) to use.
     */

    public void testFail() {
        File failTestsDir = new File(System.getProperty("FAIL_TESTS_DIR"));
        File genClassDir = new File(System.getProperty("GEN_CLASS_DIR"));
        File[] files = failTestsDir.listFiles();
        boolean errorHasOccurred = true;
        for (int i = 0; files != null && i < files.length; i++) {
            if (files[i].toString().endsWith(".java")) {
                String[] args;
                System.out.printf("""
                        Running j-- (with handwritten frontend) on %s ...

                        """, files[i]
                        .toString());
                args = new String[] { "-d", genClassDir.getAbsolutePath(),
                        files[i].toString() };
                NewOzToOzMain.main(args);
                System.out.print("\n\n");

                // true only if all tests fail
                errorHasOccurred &= NewOzToOzMain.errorHasOccurred();
            }
        }

        // We want all tests to fail
        assertTrue(errorHasOccurred);
    }

    /**
     * Entry point.
     * 
     * @param args
     *            command-line arguments.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(NozcTest.class);
    }

}
