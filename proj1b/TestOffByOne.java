import static org.junit.Assert.*;

import org.junit.Test;

public class TestOffByOne {
  // You must use this CharacterComparator and not instantiate
  // new ones, or the autograder might be upset.
  static CharacterComparator offByOne = new OffByOne();

  // Your tests go here.
  @Test
  public void testEqualChars() {
    assertTrue(offByOne.equalChars('a', 'b'));
    assertFalse(offByOne.equalChars('a', 'B'));
    assertTrue(offByOne.equalChars('r', 'q'));
    assertTrue(offByOne.equalChars('q', 'r'));
    assertTrue(offByOne.equalChars('&', '%'));
  }
}
