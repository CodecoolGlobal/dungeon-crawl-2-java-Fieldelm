package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monsters;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.ActableItem;

import java.util.Random;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;
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
        centerCell = cells[0][0];
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
        repositionCenter();
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
        }else nextX = Math.min(player.getCell().getX(), width - 11);

        if(player.getCell().getY()<= 10){
            nextY = 10;
        }else nextY = Math.min(player.getCell().getY(), height - 11);

        centerCell = cells[nextX][nextY];

    }


    public void actAllMapCreature() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y].getActor() instanceof Monsters) {
                    ((Monsters) cells[x][y].getActor()).monsterAct(this);
                }
                if( cells[x][y].getItem() instanceof ActableItem){
                    ((ActableItem) cells[x][y].getItem()).act(this);
                }
            }
        }
    }

    public void openDoor(int x, int y){
        cells[x][y].setType(CellType.ENTRANCE);
    }

    static public int randomInt(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
