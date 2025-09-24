public class HelloNumbers {
  public static void main(String[] args) {
    int x = 0;
    int y = 0;
    while (x < 10) {
      y += x;
      x += 1;
      System.out.print(y + " ");
    }
    System.out.println();
  }
}
