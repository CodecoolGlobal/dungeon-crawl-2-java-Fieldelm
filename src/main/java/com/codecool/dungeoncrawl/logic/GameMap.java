package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private Cell centerCell;
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
        centerCell = cells[width/2][height/2];
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCenterCell(){
        return centerCell;
    }

    public void repositionCenter(){
        int nextX;
        int nextY;
        if(player.getCell().getX()<= 10){
            nextX = 10;
        }else nextX = Math.min(player.getCell().getX(), width - 10);

        if(player.getCell().getY()<= 10){
            nextY = 10;
        }else nextY = Math.min(player.getCell().getY(), height - 10);

        centerCell = cells[nextX][nextY];

    }

}
