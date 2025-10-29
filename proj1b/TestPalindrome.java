import static org.junit.Assert.*;

import org.junit.Test;

public class TestPalindrome {
  // You must use this palindrome, and not instantiate
  // new Palindromes, or the autograder might be upset.
  static Palindrome palindrome = new Palindrome();

  @Test
  public void testWordToDeque() {
    Deque<Character> d = palindrome.wordToDeque("persiflage");
    String actual = "";
    for (int i = 0; i < "persiflage".length(); i++) {
      actual += d.removeFirst();
    }
    assertEquals("persiflage", actual);
  }

  @Test
  public void testIsPalindrome() {
    assertTrue(palindrome.isPalindrome("abcdcba"));
    assertTrue(palindrome.isPalindrome(""));
    assertFalse(palindrome.isPalindrome("cat"));
    assertFalse(palindrome.isPalindrome(null));
  }

  @Test
  public void testIsPalindromePro() {
    CharacterComparator cc = new OffByOne();
    assertTrue(palindrome.isPalindrome("abcdcba", cc));
    assertTrue(palindrome.isPalindrome("", cc));
    assertFalse(palindrome.isPalindrome("cat", cc));
    assertFalse(palindrome.isPalindrome(null, cc));
  }
}
