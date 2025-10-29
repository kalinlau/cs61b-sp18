public class Palindrome {

  public Deque<Character> wordToDeque(final String word) {
    Deque<Character> weque = new ArrayDeque<Character>();
    if (word == null) {
      return weque;
    }
    for (int i = 0; i < word.length(); i++) {
      weque.addLast(word.charAt(i));
    }
    return weque;
  }

  public boolean isPalindrome(final String word, final CharacterComparator cc) {
    /*
     * IDEA:
     * 1. getFirst() == getLast()
     * 2. compare 2nd and -2nd. Recursively
     */
    if (word == null) {
      return false;
    }
    int size = word.length();
    if (size == 0 || size == 1) {
      return true;
    } else if (cc.equalChars(word.charAt(0), word.charAt(size - 1))) {
      return isPalindrome(word.substring(1, size - 1), cc);
    } else {
      return false;
    }
  }

  public boolean isPalindrome(final String word) {
    if (word == null) return false;

    Deque<Character> q = wordToDeque(word);
    return isPalindromeQueue(q);
  }

  private boolean isPalindromeQueue(Deque<Character> q) {
    if (q.size() <= 1) {
      return true;
    }
    return (q.removeFirst() == q.removeLast()) ? isPalindromeQueue(q) : false;
  }
}
