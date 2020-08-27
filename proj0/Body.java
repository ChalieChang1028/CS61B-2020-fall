public class Body{
  public double xxPos, yyPos, xxVel, yyVel, mass;
  private double G=6.67e-11;
  public String imgFileName;
  public Body(double xP, double yP, double xV, double yV,
              double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public Body(Body b){
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(Body b){
    return Math.sqrt(Math.pow(xxPos-b.xxPos, 2)+Math.pow(yyPos-b.yyPos, 2));
  }

  public double calcForceExertedBy(Body b){
    if (b.xxPos==xxPos && b.yyPos==yyPos) return 0.0;
    return G*mass*b.mass/Math.pow(calcDistance(b), 2);
  }

  public double calcForceExertedByX(Body b){
    if (b.xxPos==xxPos && b.yyPos==yyPos) return 0.0;
    return calcForceExertedBy(b)*(b.xxPos-xxPos)/calcDistance(b);
  }

  public double calcForceExertedByY(Body b){
    if (b.xxPos==xxPos && b.yyPos==yyPos) return 0.0;
    return calcForceExertedBy(b)*(b.yyPos-yyPos)/calcDistance(b);
  }

  public double calcNetForceExertedByX(Body[] allb){
    double ans = 0;
    for(Body b : allb){
      ans += calcForceExertedByX(b);
    }
    return ans;
  }

  public double calcNetForceExertedByY(Body[] allb){
    double ans = 0;
    for(Body b : allb){
      ans += calcForceExertedByY(b);
    }
    return ans;
  }

  public void update(double dt, double fx, double fy){
    xxVel += dt*fx/mass;
    yyVel += dt*fy/mass;
    xxPos += dt*xxVel;
    yyPos += dt*yyVel;
  }

  public void draw(){
    StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
  }

}
