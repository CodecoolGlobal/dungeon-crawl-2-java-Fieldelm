package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Troll extends Monsters{

    @Override
    public void monsterAct(GameMap map) {

    }

    public Troll(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "troll";
    }

    @Override
    public  void directionCalculator(){
    }
}


