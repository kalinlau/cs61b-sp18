import static org.junit.Assert.*;

import org.junit.Test;

public class TestOffByN {

  @Test
  public void testOffByN() {
    CharacterComparator cc = new OffByN(5);
    assertTrue(cc.equalChars('a', 'f'));
    assertTrue(cc.equalChars('f', 'a'));
    assertFalse(cc.equalChars('f', 'h'));
  }
}
