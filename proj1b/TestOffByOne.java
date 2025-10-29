import static org.junit.Assert.*;

import org.junit.Test;

public class TestOffByOne {
  // You must use this CharacterComparator and not instantiate
  // new ones, or the autograder might be upset.
  static CharacterComparator offByOne = new OffByOne();

  // Your tests go here.
  @Test
  public void testEqualChars() {
    char a = 'a';
    char b = 'a';
    assertTrue(offByOne.equalChars(a, b));
  }
}
