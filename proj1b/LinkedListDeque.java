/**
 * Project 1A, part I, Linked List Deque
 *
 * <p>Linked List Implementation for CS61b SP18
 *
 * @author: LAU Kalin
 */
public class LinkedListDeque<T> implements Deque<T> {
  private int size;
  private TNode fp; // head sentinal to the first
  private TNode rp; // rear sentinal to the last

  /** Inner data structure: linked list node */
  private class TNode {
    public T item;
    public TNode prev;
    public TNode next;

    public TNode() {
      prev = null;
      next = null;
    }

    public TNode(T item, TNode prev, TNode next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }

  /** Empty constructor */
  public LinkedListDeque() {
    fp = new TNode();
    rp = new TNode();
    fp.next = rp;
    rp.prev = fp;
    size = 0;
  }

  /** Create a deep copy of another LLD */
  // public LinkedListDeque(LinkedListDeque<T> other) {
  // this();

  // if (other == null) {
  // System.err.println("ERROR: null deque copy");
  // System.exit(-1);
  // }
  // deepCopy(other.fp.next);
  // }

  // private void deepCopy(TNode node) {
  // while (node.next != null) {
  // TNode newNode = new TNode(node.item, rp.prev, rp);
  // rp.prev.next = newNode;
  // rp.prev = newNode;
  // size += 1;
  // node = node.next;
  // }
  // }

  /** Add items in front */
  @Override
  public void addFirst(T item) {
    TNode newNode = new TNode(item, fp, fp.next);
    fp.next.prev = newNode;
    fp.next = newNode;
    size += 1;
  }

  /** Add items in bask */
  @Override
  public void addLast(T item) {
    TNode newNode = new TNode(item, rp.prev, rp);
    rp.prev.next = newNode;
    rp.prev = newNode;
    size += 1;
  }

  /** Test if dequery is empty */
  @Override
  public boolean isEmpty() {
    return this.fp.next == rp && this.rp.prev == fp;
  }

  /** length of the Deque */
  @Override
  public int size() {
    return size;
  }

  /** Print the items from first to last separated by space. */
  @Override
  public void printDeque() {
    TNode tmp = fp.next;

    while (tmp != rp) {
      System.out.print(tmp.item + " ");
      tmp = tmp.next;
    }
    System.out.println();
  }

  @Override
  public T removeFirst() {
    if (this.isEmpty()) {
      return null;
    }
    TNode first = fp.next;
    fp.next = first.next;
    first.next.prev = fp;
    size -= 1;
    return first.item;
  }

  @Override
  public T removeLast() {
    if (this.isEmpty()) {
      return null;
    }
    TNode last = rp.prev;
    rp.prev = last.prev;
    last.prev.next = rp;
    size -= 1;
    return last.item;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      System.err.println("ERROR: Index out of the boundry");
      System.exit(-1);
    }
    TNode tmp = fp.next;
    for (int i = 0; i < index; i++) {
      tmp = tmp.next;
    }
    return tmp.item;
  }

  public T getRecursive(int index) {
    if (index < 0 || index >= size) {
      System.err.println("ERROR: Index out of the boundry");
      System.exit(-1);
    }
    return getRecursive(fp.next, index);
  }

  private T getRecursive(TNode node, int index) {
    if (node.next == null) {
      return null;
    } else if (index == 0) {
      return node.item;
    } else {
      return getRecursive(node.next, --index);
    }
  }
}
