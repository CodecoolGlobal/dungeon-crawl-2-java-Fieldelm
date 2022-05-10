package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Ghost extends Monsters{

    @Override
    public void monsterAct(GameMap map) {


    }

    public Ghost(Cell cell) {
        super(cell);
        actual_damage = 2;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
