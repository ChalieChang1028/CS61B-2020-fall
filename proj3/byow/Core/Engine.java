package byow.Core;


import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Engine {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public TETile[][] worldFrame;
    private TERenderer renderer;
    Random r;
    private Player p;

    private long seed;
    private boolean loadFlag = false;
    private String prevPath = "";
    private long prevSeed;



    //public Player p;

    public Engine() {
        worldFrame = new TETile[WIDTH][HEIGHT];
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {



    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */


    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        int index = 0;
        seed = 0;

        if (input == null) {
            return null;
        }

            while (index < input.length()) {
                if (input.charAt(index) == 'N' || input.charAt(index) == 'n') {
                    index++;
                    while ((input.charAt(index) >= '0' && input.charAt(index) <= '9')
                            && (input.charAt(index) != 'S' || input.charAt(index) != 's')) {
                        seed = seed * 10 + (input.charAt(index) - 48);
                        index++;
                    }
                    index++;
                    r = new Random(seed);
                    worldFrame = new BSP(WIDTH, HEIGHT, r).createLeaves();
                    p = new Player(WIDTH, HEIGHT, worldFrame, r);
                    p.addPlayer();
                } else if (input.charAt(index) == 'W' || input.charAt(index) == 'w'
                        || input.charAt(index) == 'S' || input.charAt(index) == 's'
                        || input.charAt(index) == 'A' || input.charAt(index) == 'a'
                        || input.charAt(index) == 'D' || input.charAt(index) == 'd') {
                    p.move(input.charAt(index));
                    index++;
                } else if (input.charAt(index) == 'l' || input.charAt(index) == 'L') {
                    loadFlag = true;
                    load(false);
//                    if (load(false)) {
//                        worldFrame = new TETile[WIDTH][HEIGHT];
//                        for (int i = 0; i < WIDTH; i++) {
//                            for (int j = 0; j < HEIGHT; j++) {
//                                worldFrame[i][j] = Tileset.NOTHING;
//                            }
//                        }
//                        return worldFrame;
//                    }
                    index++;
                } else if (input.charAt(index) == 'S' || input.charAt(index) == 's') {
                    save();
                    index++;
                } else if (input.charAt(index) == ':' && (input.charAt(index + 1) == 'q'
                        || input.charAt(index + 1) == 'Q')) {
                    save();
                    break;
                } else if (input.charAt(index) == 'R' || (input.charAt(index) == 'r')) {
                    load(true);
                    index++;
                } else {
                    index++;
                }
            }
            return worldFrame;
    }

    public void initializeRenderer() {
        renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);
    }

    public void renderWorld() {
        renderer.renderFrame(worldFrame);
    }

    public void move(char c) {
        p.move(c);
    }

    private void load(boolean replay) {
        try {
            File inputFile = new File("saved.txt");
            Scanner reader = new Scanner(inputFile);
//            if (!reader.hasNext()) {
//                return true;
//            }

            String sed = reader.next();
            prevSeed = reader.nextLong();
            if (reader.hasNext())
                prevPath = reader.next();

            if (replay) {
                String currentPath = p.getPath();
                interactWithInputString("N" + prevSeed + "S");
                renderWorld();
                int ind = 0;
                while (ind < prevPath.length()) {
                    p.move(prevPath.charAt(ind));
                    renderWorld();
                    Thread.sleep(100);
                    ind++;
                }
                Thread.sleep(2000);
                interactWithInputString("N" + seed + "S" + currentPath);
                renderWorld();

            } else {
                interactWithInputString("N" + prevSeed + "S" + prevPath);
            }
            reader.close();

        } catch (IOException | InterruptedException e) {
            System.out.println("load error");
        }

//        return false;
    }

    public void save() {
        try {
            File outputFile = new File("saved.txt");
            PrintWriter writer = new PrintWriter(outputFile,"UTF-8");
            if (!outputFile.createNewFile()) {
//                if (loadFlag) {
//                    Scanner reader = new Scanner(outputFile);
//                    seed = reader.nextLong();
//                    prevPath = reader.next();
//                } else {
//                    outputFile.delete();
//                    outputFile.createNewFile();
//                }
                outputFile.delete();
                outputFile.createNewFile();
            }
            if (loadFlag) {
                writer.println(prevSeed);
                writer.println(prevPath + p.getPath());
            } else {
                writer.println(seed);
                writer.println(p.getPath());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("save error");
        }
    }

    public static void main(String[] args)
    throws Exception{
        Engine engine = new Engine();
        engine.initializeRenderer();

//        TETile[][] ans = engine.interactWithInputString("l");
//        if (ans == null)
//            return;
//        engine.renderWorld();

//            engine.interactWithInputString("N19980711SDSSSSSSSSSDDD:Q");
//            engine.renderWorld();
//
        engine.interactWithInputString("n7193300625454684331saaawasdaawdwsd");
        engine.renderWorld();
        Thread.sleep(1000);

        engine.interactWithInputString("n7193300625454684331saaawasdaawd:q");
        engine.renderWorld();
        Thread.sleep(1000);
//
        engine.interactWithInputString("Lwsd");
        engine.renderWorld();
        Thread.sleep(1000);
        System.out.println("phase1");
//
//        engine.interactWithInputString("L:Q");
//        Thread.sleep(500);
//        System.out.println("phase2");
//        engine.renderWorld();
//
//        engine.interactWithInputString("LDSS:Q");
//        Thread.sleep(500);
//        System.out.println("phase2");
//        engine.renderWorld();
//
//        engine.interactWithInputString("LSSS:Q");
//        Thread.sleep(500);
//        System.out.println("phase2");
//        engine.renderWorld();
//
//        engine.interactWithInputString("LSSSSD:Q");
//        System.out.println("phase3");
//        engine.renderWorld();
//        Thread.sleep(1000);
//
//        engine.interactWithInputString("LDD:Q");
//        System.out.println("phase4");
//        engine.renderWorld();

        //engine.load(false);
        //System.out.print("hello");


//        Random r = new Random();
//        while (true) {
//            engine.interactWithInputString("N" + r.nextLong() + "SWSSSADDA:Q");
//        }
        //engine.save();
//
//        char input = 0;
//        while (input != 'q') {
//            if (StdDraw.hasNextKeyTyped()) {
//                input = StdDraw.nextKeyTyped();
//                engine.move(input);
//                engine.renderWorld();
//            }
//            Thread.sleep(500);
//        }
//        engine.save();
//        System.out.println("done");
    }
}
