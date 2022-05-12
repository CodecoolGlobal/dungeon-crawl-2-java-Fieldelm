package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("room", new Tile(16, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("noWall", new Tile(3, 0));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("lessfloor", new Tile(1, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("dementor", new Tile(27, 6));
        tileMap.put("ghost", new Tile(27, 8));
        tileMap.put("troll", new Tile(29, 2));
        tileMap.put("tree", new Tile(0, 1));
        tileMap.put("treetwo", new Tile(3, 1));
        tileMap.put("table", new Tile(0, 7));
        tileMap.put("stonefence", new Tile(2, 11));
        tileMap.put("entrance", new Tile(6, 9));
        tileMap.put("stonefencetwo", new Tile(19, 0));
        tileMap.put("grass", new Tile(5, 0));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("house", new Tile(6, 20));
        tileMap.put("housetwo", new Tile(8, 20));
        tileMap.put("campfire", new Tile(14, 10));
        tileMap.put("fence", new Tile(0, 3));
        tileMap.put("magicWand", new Tile(0, 26));
        tileMap.put("magicKey", new Tile(16, 23));
        tileMap.put("closed_door", new Tile(3,3));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
