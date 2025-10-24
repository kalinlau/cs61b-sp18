import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number
 * of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and
 *         melaniecebula [Do not modify
 *         this file.]
 */
public class IntList {
  /** First element of list. */
  public int first;

  /** Remaining elements of list. */
  public IntList rest;

  /** A List with first FIRST0 and rest REST0. */
  public IntList(int first0, IntList rest0) {
    first = first0;
    rest = rest0;
  }

  /** A List with null rest, and first = 0. */
  public IntList() {
    /* NOTE: public IntList () { } would also work. */
    this(0, null);
  }

  /** Returns a list equal to L with all elements squared. Destructive. */
  public static void dSquareList(IntList L) {

    while (L != null) {
      L.first = L.first * L.first;
      L = L.rest;
    }
  }

  /** Returns a list equal to L with all elements squared. Non-destructive. */
  public static IntList squareListIterative(IntList L) {
    if (L == null) {
      return null;
    }
    IntList res = new IntList(L.first * L.first, null);
    IntList ptr = res;
    L = L.rest;
    while (L != null) {
      ptr.rest = new IntList(L.first * L.first, null);
      L = L.rest;
      ptr = ptr.rest;
    }
    return res;
  }

  /** Returns a list equal to L with all elements squared. Non-destructive. */
  public static IntList squareListRecursive(IntList L) {
    if (L == null) {
      return null;
    }
    return new IntList(L.first * L.first, squareListRecursive(L.rest));
  }

  /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */

  /**
   * Returns a list consisting of the elements of A followed by the * elements of
   * B. May modify
   * items of A. Don't use 'new'.
   */
  public static IntList dcatenate(IntList A, IntList B) {
    // DONE
    if (A == null) {
      A = B;
      return A;
    }
    IntList ptr = A;
    while (ptr.rest != null) {
      ptr = ptr.rest;
    }
    ptr.rest = B;
    return A;
  }

  /**
   * Returns a list consisting of the elements of A followed by the * elements of
   * B. May NOT modify
   * items of A. Use 'new'.
   */
  public static IntList catenate(IntList A, IntList B) {
    // DONE
    return recursiveCatenate(A, B);
  }

  /**
   * non-destructive catenate via Recursion
   *
   * @param a: IntList type to concatenate first
   * @param b: IntList, append to a
   * @return new IntList()
   */
  private static IntList recursiveCatenate(IntList a, IntList b) {
    if (a != null) {
      IntList ptr = new IntList(a.first, null);
      ptr.rest = recursiveCatenate(a.rest, b);
      return ptr;
    } else if (b != null) {
      IntList ptrb = new IntList(b.first, null);
      ptrb.rest = recursiveCatenate(null, b.rest);
      return ptrb;
    } else {
      return null;
    }
  }

  /**
   * non-destructive catenate via Iteration
   *
   * @param a: IntList type to concatenate first
   * @param b: IntList, append to a
   * @return new IntList()
   */
  private static IntList iterateCatenate(IntList a, IntList b) {
    if (a == null && b == null)
      return null;
    else if (a == null || b == null) {
      IntList c = (a != null) ? a : b;
      IntList newlist = new IntList(c.first, null);
      IntList ptr = newlist;
      while (c.rest != null) {
        ptr.rest = new IntList(c.rest.first, null);
        ptr = ptr.rest;
        c = c.rest;
      }
      return newlist;
    } else {
      IntList newlist = new IntList(a.first, null);
      IntList ptr = newlist;
      while (a.rest != null) {
        ptr.rest = new IntList(a.rest.first, null);
        a = a.rest;
        ptr = ptr.rest;
      }
      while (b != null) {
        ptr.rest = new IntList(b.first, null);
        b = b.rest;
        ptr = ptr.rest;
      }
      return newlist;
    }
  }

  public static IntList reverseObsoluate(IntList A) {
    if (A == null || A.rest == null) {
      return A;
    } else {
      // WARNING: VERY INEFFICIENT
      return catenate(reverse(A.rest), new IntList(A.first, null));
    }
  }

  // reverse a list, destructive
  public static IntList reverseIterative(IntList A) {
    if (A == null) {
      return null;
    }
    IntList aplus = new IntList(0, A);
    IntList bplus = new IntList();
    while (aplus.rest != null) {
      // add to b front
      IntList tmp = new IntList(aplus.rest.first, null);
      tmp.rest = bplus.rest;
      bplus.rest = tmp;
      // remove a front
      aplus.rest = aplus.rest.rest;
    }
    return bplus.rest;
  }

  public static IntList reverse(IntList A) {
    if (A == null) {
      return null;
    }
    IntList b = null;
    while (A != null) {
      IntList tmp = A;
      A = A.rest;
      tmp.rest = b;
      b = tmp;
    }
    return b;
  }

  /**
   * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here will
   * be introduced
   * later in the course or feature some form of advanced trickery which we
   * implemented to help make
   * your life a little easier for the lab.
   */
  @Override
  public int hashCode() {
    return first;
  }

  /**
   * Returns a new IntList containing the ints in ARGS. You are not expected to
   * read or understand
   * this method.
   */
  public static IntList of(Integer... args) {
    IntList result, p;

    if (args.length > 0) {
      result = new IntList(args[0], null);
    } else {
      return null;
    }

    int k;
    for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
      p.rest = new IntList(args[k], null);
    }
    return result;
  }

  /**
   * Returns true iff X is an IntList containing the same sequence of ints as
   * THIS. Cannot handle
   * IntLists with cycles. You are not expected to read or understand this method.
   */
  public boolean equals(Object x) {
    if (!(x instanceof IntList)) {
      return false;
    }
    IntList L = (IntList) x;
    IntList p;

    for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
      if (p.first != L.first) {
        return false;
      }
    }
    if (p != null || L != null) {
      return false;
    }
    return true;
  }

  /**
   * If a cycle exists in the IntList, this method returns an integer equal to the
   * item number of
   * the location where the cycle is detected.
   *
   * <p>
   * If there is no cycle, the number 0 is returned instead. This is a utility
   * method for lab2.
   * You are not expected to read, understand, or even use this method. The point
   * of this method is
   * so that if you convert an IntList into a String and that IntList has a loop,
   * your computer
   * doesn't get stuck in an infinite loop.
   */
  private int detectCycles(IntList A) {
    IntList tortoise = A;
    IntList hare = A;

    if (A == null) {
      return 0;
    }

    int cnt = 0;

    while (true) {
      cnt++;
      if (hare.rest != null) {
        hare = hare.rest.rest;
      } else {
        return 0;
      }

      tortoise = tortoise.rest;

      if (tortoise == null || hare == null) {
        return 0;
      }

      if (hare == tortoise) {
        return cnt;
      }
    }
  }

  @Override
  /**
   * Outputs the IntList as a String. You are not expected to read or understand
   * this method.
   */
  public String toString() {
    Formatter out = new Formatter();
    String sep;
    sep = "(";
    int cycleLocation = detectCycles(this);
    int cnt = 0;

    for (IntList p = this; p != null; p = p.rest) {
      out.format("%s%d", sep, p.first);
      sep = ", ";

      cnt++;
      if ((cnt > cycleLocation) && (cycleLocation > 0)) {
        out.format("... (cycle exists) ...");
        break;
      }
    }
    out.format(")");
    return out.toString();
  }
}
