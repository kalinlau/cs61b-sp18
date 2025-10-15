public class ArrayDequeTest {
  public static void main(String[] args) {
    ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
    int presize = 8;

    for (int i = 1; i < 2 * presize + 1; i++) {
      if (i % 2 == 1) {
        ad.addFirst(i);
      } else {
        ad.addLast(i);
      }
      // System.out.println(Arrays.toString(ad.D));
      ad.printDeque();
    }

    System.out.println("\n>>> Test 2 >>>");
    for (int i = 0; i < presize + 10; i++) {
      System.out.print("Remove: " + ad.removeFirst() + " ");
      System.out.print("Size: " + ad.size() + "\t");
      // System.out.println(Arrays.toString(ad.D));
      ad.printDeque();
    }
    System.out.println("<<< Test 2 DONE <<<");
  }
}
