package synthesizer;

import java.util.Iterator;
import java.util.NoSuchElementException;

// import java.util.Iterator;

// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
  /* Index for the next dequeue or peek. */
  private int first; // index for the next dequeue or peek
  /* Index for the next enqueue. */
  private int last;
  /* Array for storing the buffer data. */
  private T[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    first = 0;
    last = 0;
    this.fillCount = 0;
    this.capacity = capacity;
    rb = (T[]) new Object[this.capacity];
  }

  /**
   * Adds x to the end of the ring buffer. If there is no room, then throw new
   * RuntimeException("Ring buffer overflow"). Exceptions covered Monday.
   */
  public void enqueue(T x) {
    if (this.fillCount == this.capacity) {
      throw new RuntimeException("Ring buffer overflow");
    }
    rb[last] = x;
    last += 1;
    last = (last >= this.capacity) ? last % this.capacity : last;
    this.fillCount += 1;
  }

  /**
   * Dequeue oldest item in the ring buffer. If the buffer is empty, then throw
   * new
   * RuntimeException("Ring buffer underflow"). Exceptions covered Monday.
   */
  public T dequeue() {
    if (this.fillCount == 0) {
      throw new RuntimeException("Ring buffer underflow");
    }
    T t = rb[first];
    rb[first] = null;
    first += 1;
    first = (first >= this.capacity) ? first % this.capacity : first;
    fillCount -= 1;

    return t;
  }

  /** Return oldest item, but don't remove it. */
  public T peek() {
    if (this.fillCount == 0) {
      throw new RuntimeException("Ring buffer underflow");
    }
    return rb[first];
  }

  // public String toString() {
  // return Arrays.toString(rb);
  // }

  // DONE: When you get to part 5, implement the needed code to support iteration.
  @Override
  public Iterator<T> iterator() {
    return new Bterator();
  }

  private class Bterator implements Iterator<T> {
    @Override
    public boolean hasNext() {
      return !ArrayRingBuffer.this.isEmpty();
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException("Out of BoundedQueue Size");
      }
      return ArrayRingBuffer.this.dequeue();
    }
  }
}
