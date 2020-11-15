package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;

    private static final long SEED = 99999999;
    private static final Random RANDOM = new Random(SEED);


    public static void addHexagon(int side, int x, int y, TETile[][] world, TETile t) {
        if (side < 2) {
            throw new IllegalArgumentException();
        }

        int cap = side, height = side * 2, factor = side - 1;

        for (int i = 1; i < height + 1; i++) {
            addLine(side, x + factor, y, world, t);
            if (i < cap) {
                side += 2;
                factor -= 1;
            } else if (i > cap) {
                side -= 2;
                factor += 1;
            }
            y += 1;
        }
    }

    private static void addLine(int side, int x, int y, TETile[][] world, TETile t) {
        if (side > 0) {
            world[x][y] = t;
            addLine(side - 1, x + 1, y, world, t);
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile background = Tileset.NOTHING;
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = background;
            }
        }

        addHexagon(4, 0, 0, world, Tileset.WATER);
        ter.renderFrame(world);
    }
}
