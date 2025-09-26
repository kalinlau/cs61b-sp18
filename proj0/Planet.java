public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Planet(double xP, double yP, double xV, double yV, double mass, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = mass;
    this.imgFileName = img;
  }

  public Planet(Planet b) {
    // this.xxPos = b.xxPos;
    // this.yyPos = b.yyPos;
    // this.xxVel = b.xxVel;
    // this.yyVel = b.yyVel;
    // this.mass = b.mass;
    // this.imgFileName = b.imgFileName;
    this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
  }

  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }

  public double calcDistance(Planet p) {
    double xrel = p.xxPos - this.xxPos;
    double yrel = p.yyPos - this.yyPos;
    double dis = Math.sqrt(xrel * xrel + yrel * yrel);
    return dis;
  }

  public double calcForceExertedBy(Planet p) {
    double relForce = 6.67e-11 * this.mass * p.mass;
    double dis = this.calcDistance(p);
    relForce /= (dis * dis);

    return relForce;
  }

  public double calcForceExertedByX(Planet p) {
    double xdt = p.xxPos - this.xxPos;
    double dis = this.calcDistance(p);
    if (dis == 0) return 0;

    double xForce = this.calcForceExertedBy(p) * xdt / this.calcDistance(p);
    return xForce;
  }

  public double calcForceExertedByY(Planet p) {
    double ydt = p.yyPos - this.yyPos;
    double dis = this.calcDistance(p);
    if (dis == 0) return 0;

    double yForce = this.calcForceExertedBy(p) * ydt / this.calcDistance(p);
    return yForce;
  }

  public double calcNetForceExertedByX(Planet[] allPlanets) {
    double xNetForce = 0;
    for (Planet p : allPlanets) {
      xNetForce += this.calcForceExertedByX(p);
    }
    return xNetForce;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double yNetForce = 0;
    for (Planet p : allPlanets) {
      yNetForce += this.calcForceExertedByY(p);
    }

    return yNetForce;
  }

  public void update(double interval, double xForce, double yForce) {
    double xAcc = xForce / this.mass;
    double yAcc = yForce / this.mass;

    /* Update velocity */
    this.xxVel += xAcc * interval;
    this.yyVel += yAcc * interval;

    /* Update position */
    this.xxPos += this.xxVel * interval;
    this.yyPos += this.yyVel * interval;
  }
}
