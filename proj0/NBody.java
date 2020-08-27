public class NBody{

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]), dt = Double.parseDouble(args[1]), time = 0;
    String filename = args[2];
    double R = readRadius(filename);
    Body[] allBodies = readBodies(filename);

    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-R, R);
    StdDraw.clear();
    StdDraw.picture(0, 0, "images/starfield.jpg");

    for (Body b: allBodies){
      b.draw();
    }

    while (time < T){
      Double[] xForces = new Double[allBodies.length];
      Double[] yForces = new Double[allBodies.length];

      StdDraw.setScale(-R, R);
      StdDraw.clear();
      StdDraw.picture(0, 0, "images/starfield.jpg");

      for (int i = 0; i < allBodies.length; i++){
        xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
        yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
        allBodies[i].update(dt, xForces[i], yForces[i]);
        allBodies[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      time += dt;
    }
    StdOut.printf("%d\n", allBodies.length);
    StdOut.printf("%.2e\n", R);
    for (int i = 0; i < allBodies.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                  allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);
}
  }

  public static double readRadius(String fname){
    In in = new In(fname);
    in.readDouble();
    return in.readDouble();
  }

  public static Body[] readBodies(String fname){
    In in = new In(fname);
    int n = in.readInt();
    in.readDouble();
    Body[] allb = new Body[n];
    for(int i = 0; i < n; i++)
    {
      allb[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(),
                      in.readDouble(), in.readDouble(), in.readString());
    }
    return allb;
  }

}
