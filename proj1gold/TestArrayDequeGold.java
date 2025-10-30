import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestArrayDequeGold {
  private ArrayDequeSolution<Integer> ads;
  private StudentArrayDeque<Integer> sad;
  private Integer dsize;
  private List<String> mesg;
  private static final Integer N = 3;

  @Before
  public void setupForEachTest() {
    ads = new ArrayDequeSolution<Integer>();
    sad = new StudentArrayDeque<Integer>();
    dsize = StdRandom.uniform(1, 101);
    mesg = new ArrayList<String>();

    for (int i = 0; i < dsize; i++) {
      Integer item = StdRandom.uniform(1, 101);
      if (item % 2 == 1) {
        ads.addFirst(item);
        sad.addFirst(item);
        addMessage("addFirst(" + item + ")");
      } else {
        ads.addLast(item);
        sad.addLast(item);
        addMessage("addLast(" + item + ")");
      }
    }
  }

  private String getMessage(List<String> list, int n) {
    StringBuilder result = new StringBuilder();
    int size = list.size() - 1;
    for (int i = Math.min(n, size); i >= 0; i--) {
      result.append(list.get(size - i));
    }
    return result.toString();
  }

  private void addMessage(String info) {
    if (mesg.size() == TestArrayDequeGold.N) {
      mesg.removeFirst();
    }
    mesg.addLast("\n" + info);
  }

  @Test
  public void testAdds() {
    for (int i = 0; i < dsize; i++) {
      assertEquals(getMessage(mesg, N), ads.get(i), sad.get(i));
    }
  }

  @Test
  public void testRemoves() {
    Integer expected;
    Integer actual;
    mesg = new ArrayList<String>(N);
    for (int j = 0; j < dsize; j++) {
      assertEquals(ads.size(), sad.size());
      if (j % 2 == 1) {
        expected = ads.removeFirst();
        actual = sad != null ? sad.removeFirst() : null;
        addMessage("removeFirst()");
        assertEquals(getMessage(mesg, N), expected, actual);
      } else {
        expected = ads.removeLast();
        actual = sad != null ? sad.removeLast() : null;
        addMessage("removeLast()");
        assertEquals(getMessage(mesg, N), expected, actual);
      }
    }
  }
}
