package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Monsters extends Actor {

    public abstract void monsterAct(GameMap map);
    public abstract void directionCalculator();

@Override
public void move(int dx, int dy, GameMap map) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            nextCell.getActor().takeDamage(actual_damage, this);
        }
        else if(nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.GRASS
                || nextCell.getType() == CellType.LESSFLOOR || nextCell.getType() == CellType.ENTRANCE){
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);

        }

}

    public Monsters(Cell cell) {
        super(cell);
    }

}
