public class OffByOne implements CharacterComparator {
  public static final Integer N = 1;

  @Override
  public boolean equalChars(char x, char y) {
    return Math.abs(x - y) == N;
  }
}
