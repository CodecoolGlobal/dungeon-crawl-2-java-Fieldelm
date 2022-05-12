package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Drawable {
    static List<CellType> MOVABLE_CELL_TYPES = new ArrayList<>();
    private CellType type;
    private Actor actor;
    private Item item;
    private final GameMap gameMap;
    private final int x;
    private final int y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }
    static {
        MOVABLE_CELL_TYPES.add(CellType.FLOOR);
        MOVABLE_CELL_TYPES.add(CellType.GRASS);
        MOVABLE_CELL_TYPES.add(CellType.LESSFLOOR);
        MOVABLE_CELL_TYPES.add(CellType.ENTRANCE);
        MOVABLE_CELL_TYPES.add(CellType.ROOM);
    }
    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setItem(Item item){ this.item = item;}

    public Item getItem() {
        return item;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);

    }
    public boolean isSteppable(){
        return MOVABLE_CELL_TYPES.contains(this.type);
    }
    public boolean isClosedDoor(){
        return this.type == CellType.CLOSED_DOOR;
    }
    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
