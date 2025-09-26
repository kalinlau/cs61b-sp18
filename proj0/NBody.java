public class NBody {
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    double radiusUnv = readRadius(filename);
    Planet[] planets = readPlanets(filename);

    StdDraw.setScale(-radiusUnv, radiusUnv);
    StdDraw.enableDoubleBuffering();
    StdDraw.clear();

    int N = planets.length;
    double xForces;
    double yForces;
    for (int t = 0; t < T; t += dt) {
      for (int i = 0; i < N; i++) {
        xForces = planets[i].calcNetForceExertedByX(planets);
        yForces = planets[i].calcNetForceExertedByY(planets);

        /** Update planet */
        planets[i].update(dt, xForces, yForces);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for (Planet p : planets) {
        p.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
    }

    /** Print out debug INFO */
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radiusUnv);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf(
          "%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos,
          planets[i].yyPos,
          planets[i].xxVel,
          planets[i].yyVel,
          planets[i].mass,
          planets[i].imgFileName);
    }
  }

  /* Read Radius of the Universe from the give description file */
  public static double readRadius(String fileName) {
    In in = new In(fileName);

    int numPlanet = in.readInt();
    double radiusUnv = in.readDouble();

    return radiusUnv;
  }

  /* Read planets from a file */
  public static Planet[] readPlanets(String fileName) {
    In in = new In(fileName);

    int numPlanet = in.readInt();
    double radiusUnv = in.readDouble();
    Planet[] planets = new Planet[numPlanet];

    double xPos, yPos, xVel, yVel, mass;
    String imgName;
    for (int i = 0; i < numPlanet; i++) {
      xPos = in.readDouble();
      yPos = in.readDouble();
      xVel = in.readDouble();
      yVel = in.readDouble();
      mass = in.readDouble();
      imgName = in.readString();

      planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgName);
    }

    return planets;
  }
}
