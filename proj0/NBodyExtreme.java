

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.*;




public class NBodyExtreme{

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]), dt = Double.parseDouble(args[1]), time = 0;
    String filename = args[2];
    double R = readRadius(filename);
    BodyExtreme[] allBodies = readBodies(filename);

    Random rand = new Random();

    // Spaceship initialization, Random Position
    BodyExtreme ship = new BodyExtreme(Math.pow(-1, rand.nextInt(2))*R*Math.random(),
                          Math.pow(-1, rand.nextInt(2))*R*Math.random(),
                          0, 0, 2e24, "star_destroyer.gif");
    //////////////////////

    //StdAudio.play("audio/2001.mid");
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-R, R);
    StdDraw.clear();
    StdDraw.picture(0, 0, "images/starfield.jpg");

    for (BodyExtreme b: allBodies){
      b.draw();
    }

    // Spaceshit Draw
    ship.draw();
    /////////////


    while (time < T){

      Double[] xForces = new Double[allBodies.length];
      Double[] yForces = new Double[allBodies.length];

      StdDraw.setScale(-R, R);
      StdDraw.clear();
      StdDraw.picture(0, 0, "images/starfield.jpg");


      /// Detect Elastic Collision
      for (int i = 0; i < allBodies.length; i++){
        if (allBodies[i].checker(ship, R)){
          allBodies[i].updateCollide(ship);
          ship.updateCollide(allBodies[i]);
        }
        for (int j = 0; j < allBodies.length; j++){
          if (i == j)
            continue;
          if(allBodies[i].checker(allBodies[j], R)){
            allBodies[i].updateCollide(allBodies[j]);
            allBodies[j].updateCollide(allBodies[i]);
          }
        }
      }
      ///////////////////////

      for (int i = 0; i < allBodies.length; i++){
        xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
        yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
        xForces[i] += allBodies[i].calcForceExertedByX(ship);
        yForces[i] += allBodies[i].calcForceExertedByY(ship);
        allBodies[i].update(dt, xForces[i], yForces[i]);
        allBodies[i].draw();
      }

      // Spaceship Control
      ship.update(dt, ship.calcNetForceExertedByX(allBodies),
                      ship.calcNetForceExertedByY(allBodies));
      ship.draw();
      if (StdDraw.isKeyPressed(KeyEvent.VK_UP))
        ship.yyVel += 500;
      if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
        ship.yyVel -= 500;
      if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
        ship.xxVel -= 500;
      if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
        ship.xxVel += 500;
      //////////////


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
    in.readInt();
    return in.readDouble();
  }

  public static BodyExtreme[] readBodies(String fname){
    In in = new In(fname);
    int n = in.readInt();
    in.readDouble();
    BodyExtreme[] allb = new BodyExtreme[n];
    for(int i = 0; i < n; i++)
    {
      allb[i] = new BodyExtreme(in.readDouble(), in.readDouble(), in.readDouble(),
                      in.readDouble(), in.readDouble(), in.readString());
    }
    return allb;
  }

}
