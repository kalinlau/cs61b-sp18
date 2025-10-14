public class ArrayDeque<T> {
  private final int presize = 8;
  private int delta;
  private int size;
  private int nextFirst, nextLast, BEG;
  public T[] D;

  public ArrayDeque() {
    D = (T[]) new Object[presize];
    BEG = presize / 2;
    nextFirst = BEG - 1; // nextFirst --
    nextLast = BEG + 1; // nextLast ++
    size = 0;
  }

  public ArrayDeque(ArrayDeque<T> other) {}

  public void addFirst(T item) {
    int Length = D.length;
    if (size >= Length - 1) {
      // out of boundry
      int newLength = Length * 4; // always > 25% possesion
      int newBeg = newLength / 2;
      T[] newD = (T[]) new Object[newLength];

      arrayExtend(D, newD);

      // Update
      D = newD;
      nextFirst = newBeg - (BEG - nextFirst);
      nextLast = newBeg + (nextLast - BEG);
      BEG = newBeg;
      Length = D.length;
    }
    int pos = nextFirst < 0 ? (nextFirst + Length) % Length : nextFirst;
    D[pos] = item;
    size += 1;
    nextFirst -= 1;
  }

  public void addLast(T item) {
    int Length = D.length;
    if (size >= Length - 1) {
      // OUT OF BOUNDRY
      int newLength = Length * 4; // 32
      int newBeg = newLength / 2; // 16
      T[] newD = (T[]) new Object[newLength];

      arrayExtend(D, newD);

      // Update
      D = newD;
      nextFirst = newBeg - (BEG - nextFirst);
      nextLast = newBeg + (nextLast - BEG);
      BEG = newBeg;
      Length = D.length;
    }
    int pos = (nextLast > Length - 1) ? nextLast - Length : nextLast;
    D[pos] = item;
    nextLast += 1;
    size += 1;
  }

  /** Resize && Update to larger array */
  private void arrayExtend(T[] src, T[] dest) {
    int destLength = dest.length;
    int srcLength = src.length;
    int srcBeg = srcLength / 2;
    int destBeg = destLength / 2;
    int fl = nextFirst;
    int ll = nextLast;
    for (int s = 0; s < size; s++) {
      if (fl < srcBeg) {
        fl += 1;
        int spos = fl < 0 ? fl + srcLength : fl;
        int dpos = destBeg - (srcBeg - fl);
        dest[dpos] = src[spos];
      }
      if (ll > srcBeg) {
        ll -= 1;
        int spos = ll < srcLength ? ll : ll - srcLength;
        int dpos = destBeg + (ll - srcBeg);
        dest[dpos] = src[spos];
      }
    }
  }

  /** Resize && Update to smaller array */
  private void arrayShrink(T[] src, T[] dest) {
    int dLen = dest.length;
    int dBEG = dLen / 2;
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  public int size() {
    return size;
  }

  public void printDeque() {
    int len = D.length;
    for (int i = nextFirst + 1; i < BEG; i++) {
      // print addFirst
      int pos = i < 0 ? i + len : i;
      System.out.print(D[pos] + " ");
    }
    for (int i = BEG + 1; i < nextLast; i++) {
      // print addLast
      int pos = i > len - 1 ? i - len : i;
      System.out.print(D[pos] + " ");
    }
    System.out.println();
  }

  public T removeFirst() {
    if (nextFirst < BEG - 1) {
      // nextFirst + 1 is the 1st element
    } else if (nextLast > BEG + 1) {
      // BEG + 1 is the first
    } else {
      // NULL Deque
    }
    return null;
  }

  public T removeLast() {
    // State Vars
    nextLast -= 1;
    size -= 1;

    // Resize
    if (size < D.length / 4 && D.length > presize) {}

    return null;
  }

  public T get(int index) {
    // Legal range [0, size - 1]
    int pos = (nextFirst + 1) + index;
    if (nextFirst < pos && pos < BEG) {
      pos = pos < 0 ? pos + D.length : pos;
    } else if (pos >= BEG && pos < nextLast) {
      pos += 1; // crossover BEG
      pos = pos > D.length - 1 ? pos - D.length : pos;
    } else {
      pos = BEG;
    }
    return D[pos];
  }

  public static void main(String[] args) {
    ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

    for (int i = 0; i < 16; i++) {
      if (i % 2 == 1) {
        ad.addFirst(i);
        // System.out.println(Arrays.toString(ad.D));
      } else {
        ad.addLast(i);
        // System.out.println(Arrays.toString(ad.D));
      }
      System.out.print("Get 0:" + ad.get(0) + " ");
      System.out.print(i + ":" + ad.get(i) + "\t");
      ad.printDeque();
    }
  }
}
