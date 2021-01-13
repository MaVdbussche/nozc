package junit;

import junit.framework.TestCase;
import pass.LogicalOp;

public class LogicalOpTest extends TestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testAnd() {
    assertTrue(LogicalOp.and(true, true));
    assertFalse(LogicalOp.and(true, false));
    assertFalse(LogicalOp.and(false, true));
    assertFalse(LogicalOp.and(false, false));
  }

  public void testOr() {
    assertTrue(LogicalOp.or(true, true));
    assertTrue(LogicalOp.or(true, false));
    assertTrue(LogicalOp.or(false, true));
    assertFalse(LogicalOp.or(false, false));
  }
}
