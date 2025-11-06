package synthesizer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ArrayRingBufferTest {
  private ArrayRingBuffer<String> q;

  @Test
  @Tag("ArrayRingBuffer")
  @BeforeEach
  void testConstruct() {
    q = new ArrayRingBuffer<>(8);
    for (int i = 0; i < q.capacity; i++) {
      q.enqueue(Integer.toString(i));
    }

    assertFalse(q.isEmpty());
    assertTrue(q.isFull());
  }

  @Test
  @Tag("ArrayRingBuffer")
  void testDeq() {
    for (int j = 0; j < q.capacity(); j++) {
      String ans = q.dequeue();
      assertEquals(Integer.toString(j), ans);
    }

    assertThrows(
        RuntimeException.class,
        () -> {
          q.dequeue();
        });
  }

  @Test
  @Tag("ArrayRingBuffer")
  void testSize() {
    assertEquals(8, q.fillCount());
  }

  @Test
  @Tag("ArrayRingBuffer")
  @Tag("dev")
  @DisplayName("DEV Test: Nested Iteration")
  void testArray() {
    // String[] expected = {"0", "1", "2", "3", "4", "5", "6", "7"};
    int[] expected1 = {0, 1, 2, 3, 4, 5, 6, 7};
    assertEquals(Arrays.toString(expected1), q.toString());

    q.dequeue();
    q.dequeue();
    q.dequeue();
    assertEquals(5, q.fillCount());

    Integer[] expected2 = {null, null, null, 3, 4, 5, 6, 7};
    assertEquals(Arrays.toString(expected2), q.toString());
    Integer[] expected3 = {8, 9, 10, 3, 4, 5, 6, 7};
    q.enqueue("8");
    q.enqueue("9");
    q.enqueue("10");
    assertEquals(Arrays.toString(expected3), q.toString());

    assertThrows(
        RuntimeException.class,
        () -> {
          q.enqueue("11");
        });
  }

  @Test
  @Tag("ArrayRingBuffer")
  @Tag("dev")
  @DisplayName("DEV Test:Iterable")
  void testForeach() {
    int[] expected1 = {0, 1, 2, 3, 4, 5, 6, 7};
    int i = 0;
    for (String string : q) {
      assertEquals(Integer.toString(expected1[i++]), string);
    }

    assertTrue(q.isEmpty());
  }
}
