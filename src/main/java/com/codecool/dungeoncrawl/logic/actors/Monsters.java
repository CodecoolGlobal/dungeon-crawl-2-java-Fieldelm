package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Monsters extends Actor {

    public abstract void monsterAct();





@Override
public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            nextCell.getActor().takeDamage(this.getActual_damage(), this);

            }
        else if(nextCell.isSteppable()){
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
}

    public Monsters(Cell cell) {
        super(cell);
    }

}
