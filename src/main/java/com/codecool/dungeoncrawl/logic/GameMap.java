package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monsters;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.ActableItem;
import com.codecool.dungeoncrawl.logic.items.Campfire;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private Cell centerCell;
    private Player player;

    private final String fileName;

    public GameMap(int width, int height, CellType defaultCellType, String fileName) {
        this.width = width;
        this.height = height;
        this.fileName = fileName;
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

    public void setCells(Cell[][] cells){
        for (int x = 0; x < width; x++) {
            if (height >= 0) System.arraycopy(cells[x], 0, this.cells[x], 0, height);
        }
    }

    public Cell[][] getCells(){return cells;}
    public void setPlayer(Player player) {
        this.player = player;
        repositionCenter();
    }

    public void placeItem(Object obj){
        if(obj instanceof Actor){
            Actor actor = (Actor)obj;
            int x = actor.getX();
            int y = actor.getY();
            cells[x][y].setActor(actor);
        }
        else if (obj instanceof Item){
            Item item = (Item)obj;
            int x = item.getX();
            int y = item.getY();
            cells[x][y].setItem(item);
        }
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

    public Cell getCenterCell() {
        return centerCell;
    }

    public void repositionCenter() {
        int nextX;
        int nextY;
        if (player.getCell().getX() <= 10) {
            nextX = 10;
        } else nextX = Math.min(player.getCell().getX(), width - 11);

        if (player.getCell().getY() <= 10) {
            nextY = 10;
        } else nextY = Math.min(player.getCell().getY(), height - 11);

        centerCell = cells[nextX][nextY];

    }


    public void actAllMapCreature() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y].getActor() instanceof Monsters) {
                    ((Monsters) cells[x][y].getActor()).monsterAct();

                }
                if (cells[x][y].getItem() instanceof ActableItem) {
                    ((ActableItem) cells[x][y].getItem()).act(this);
                }
            }
        }
    }

    public void openDoor(int x, int y) {
        cells[x][y].setType(CellType.ENTRANCE);
    }

    public void crossWater(int x, int y) {
        cells[x][y].setType(CellType.CROSS_WATER);
    }

    public void remakeWater(int x, int y) {
        cells[x][y].setType(CellType.WATER);
    }

    @Override
    public String toString(){
        StringBuilder map = new StringBuilder();
        for(int i = 0; i < height; i++){
            StringBuilder row = new StringBuilder();
            for(int j = 0; j < width; j++){
                row.append(cells[j][i]);
            }
            map.append(row.toString()).append("\n");
        }
        map.append("\n   ");
        return map.toString();
    }

    public String getFileName(){return fileName;}

    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[j][i].getItem() != null){
                    items.add(cells[j][i].getItem());
                }
            }
        }
        return items;
    }

    public List<Monsters> getMonsters(){
        List<Monsters> monsters = new ArrayList<>();
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[j][i].getActor() != null && cells[j][i].getActor() instanceof Monsters){
                    monsters.add((Monsters) cells[j][i].getActor());
                }
            }
        }
        return monsters;
    }

    static public int randomInt(int min, int max) {
        Random random = new Random();
        int difference = 0;
        if (min < 0) {
            difference = Math.abs(min);
            return random.nextInt(max + difference) - difference;
        }

        return random.nextInt(max - min) + min;
    }
}
