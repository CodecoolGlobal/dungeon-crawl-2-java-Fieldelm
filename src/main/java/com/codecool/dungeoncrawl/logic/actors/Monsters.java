package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Monsters extends Actor {

    public abstract void monsterAct(GameMap map);
    public abstract void directionCalculator();


    public Monsters(Cell cell) {
        super(cell);
    }

}
