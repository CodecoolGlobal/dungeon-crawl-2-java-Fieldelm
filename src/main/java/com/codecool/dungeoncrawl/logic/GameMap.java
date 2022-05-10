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

    public void setCenterCell(int dx, int dy){
        if(centerCell.getX() + dx >= 8 && centerCell.getY() + dy >= 8
        && centerCell.getX() + dx < width - 8 && centerCell.getY() < height - 8){
            centerCell = cells[centerCell.getX() + dx][centerCell.getY() + dy];
        }

    }

}
