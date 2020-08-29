
import java.lang.Math.*;

public class BodyExtreme{
  public double xxPos, yyPos, xxVel, yyVel, mass;
  private double G=6.67e-11;
  public String imgFileName;
  public BodyExtreme(double xP, double yP, double xV, double yV,
              double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public BodyExtreme(BodyExtreme b){
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(BodyExtreme b){
    return Math.sqrt(Math.pow(xxPos-b.xxPos, 2)+Math.pow(yyPos-b.yyPos, 2));
  }

  public double calcForceExertedBy(BodyExtreme b){
    if (this == b) return 0.0;
    return G*mass*b.mass/Math.pow(calcDistance(b), 2);
  }

  public double calcForceExertedByX(BodyExtreme b){
    if (this == b) return 0.0;
    return calcForceExertedBy(b)*(b.xxPos-xxPos)/calcDistance(b);
  }

  public double calcForceExertedByY(BodyExtreme b){
    if (this == b) return 0.0;
    return calcForceExertedBy(b)*(b.yyPos-yyPos)/calcDistance(b);
  }

  public double calcNetForceExertedByX(BodyExtreme[] allb){
    double ans = 0;
    for(BodyExtreme b : allb){
      ans += calcForceExertedByX(b);
    }
    return ans;
  }

  public double calcNetForceExertedByY(BodyExtreme[] allb){
    double ans = 0;
    for(BodyExtreme b : allb){
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

  ///  Collision Detector
  public boolean checker(BodyExtreme b, double R){
    if (Math.sqrt(Math.pow(b.xxPos-xxPos, 2)+Math.pow(b.yyPos-yyPos, 2)) < R/10 )
      return true;
    return false;
  }

  /// Collision Dealer
  public void updateCollide(BodyExtreme b){
    //// Data generating
    double v_1 = Math.sqrt(Math.pow(xxVel, 2)+Math.pow(yyVel, 2)),
           v_2 = Math.sqrt(Math.pow(b.xxVel, 2)+Math.pow(b.yyVel, 2)),
           theta_1 = Math.atan(yyVel/xxVel),
           theta_2 = Math.atan(b.yyVel/b.xxVel),
           phi;
    double dx = b.xxPos - xxPos,
           dy = b.yyPos - yyPos;
    if (dx == 0)
      phi = Math.PI/2;
    else
      phi = Math.atan(dy/dx);

    /// Calculation
    xxVel = Math.cos(phi)*(v_1*Math.cos(theta_1-phi)*(mass-b.mass)+2*b.mass*v_2*Math.cos(theta_2-phi))/(mass+b.mass) + v_1*Math.sin(theta_1-phi)*Math.cos(phi+Math.PI/2);
    yyVel = Math.sin(phi)*(v_1*Math.cos(theta_1-phi)*(mass-b.mass)+2*b.mass*v_2*Math.cos(theta_2-phi))/(mass+b.mass) + v_1*Math.sin(theta_1-phi)*Math.sin(phi+Math.PI/2);

    /// Separate two Bodies

  }

}
