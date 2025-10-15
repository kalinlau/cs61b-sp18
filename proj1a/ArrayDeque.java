/**
 * Project 1A, part II, Array Deque
 *
 * <p>Array-based Deque Implementation fo CS61b SP18.
 *
 * @author LAU Kalin
 * @version 2.0
 */
public class ArrayDeque<T> {
  private final int PRESIZE = 8;
  private int size;
  private int nextFirst, nextLast, BEG;
  private T[] D;

  public ArrayDeque() {
    D = (T[]) new Object[PRESIZE];
    BEG = PRESIZE / 2;
    nextFirst = BEG - 1;
    nextLast = BEG;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void addFirst(T item) {
    if (size >= D.length) {
      // Extend to larger array
      reSize(D, false);
    }
    D[nextFirst] = item;
    size += 1;
    nextFirst = getIndex(nextFirst - 1, D);
  }

  public void addLast(T item) {
    if (size >= D.length) {
      // Extend to larger array
      reSize(D, false);
    }
    D[nextLast] = item;
    size += 1;
    nextLast = getIndex(nextLast + 1, D);
  }

  public T removeFirst() {
    if (size == 0) {
      return null;
    }
    int index = getIndex(nextFirst + 1, D);
    T item = D[index];
    D[index] = null;
    size -= 1;
    nextFirst = index;

    if (size <= D.length >> 2 && D.length > PRESIZE) {
      reSize(D, true);
    }
    return item;
  }

  public T removeLast() {
    if (size == 0) {
      return null;
    }
    int index = getIndex(nextLast - 1, D);
    T item = D[index];
    D[index] = null;
    size -= 1;
    nextLast = index;

    if (size <= D.length >> 2 && D.length > PRESIZE) {
      reSize(D, true);
    }
    return item;
  }

  /**
   * Retrieve the index-th item in the ArrayDeque
   *
   * @param index the index ranging from [0, size - 1]
   * @return T element.
   */
  public T get(int index) {
    if (index < 0 || index > size - 1) return null;
    return D[getIndex(nextFirst + 1 + index, D)];
  }

  private int getIndex(int index, T[] arr) {
    int pos = (index + arr.length) % arr.length;
    return pos;
  }

  /**
   * Extend and Update the state of the ArrayDeque
   *
   * @param src: origin source T array.
   * @param smaller: boolean variable to shrink or expand size
   * @return void.
   */
  private void reSize(T[] src, boolean smaller) {
    int newLen = smaller ? src.length >> 2 : src.length << 2;
    T[] Dnew = (T[]) new Object[newLen];
    int newBEG = newLen / 2;
    int newNextFirst = newBEG;
    int newNextLast = newBEG + 1;
    for (int i = 0; i < size; i++) {
      int currentLastIndex = getIndex(nextLast - 1 - i, src);
      Dnew[newNextFirst] = src[currentLastIndex];
      newNextFirst = getIndex(newNextFirst - 1, Dnew);
    }
    D = Dnew;
    nextFirst = newNextFirst;
    nextLast = newNextLast;
  }

  public void printDeque() {
    for (int i = 0; i < size; i++) {
      System.out.print(this.get(i) + " ");
    }
    System.out.println();
  }
}
