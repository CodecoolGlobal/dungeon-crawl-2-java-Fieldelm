package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Ghost extends Monsters{

    @Override
    public void monsterAct(GameMap map) {
    ghostMove();
    }

    public  void ghostMove(){

    }


    public Ghost(Cell cell) {
        super(cell);
        this.setActual_damage(3);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
