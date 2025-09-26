/** Test Planet */
public class TestPlanet {
  /** Test Planet */
  public static void main(String[] args) {
    checkPlanet();
  }

  private static void checkPlanet() {
    Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
    Planet p2 = new Planet(2.0, 2.0, 3.0, 3.0, 10.0, "mars.gif");

    double d12 = p1.calcDistance(p2);

    System.out.println("Distance between 1 and 2: " + d12);
    p1.update(0.4, 4, 6);
    d12 = p1.calcDistance(p2);
    System.out.println("Update: Distance between 1 and 2: " + d12);
  }
  ;
}
