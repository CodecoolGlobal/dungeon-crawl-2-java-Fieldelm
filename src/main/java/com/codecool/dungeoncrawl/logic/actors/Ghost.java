package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Random;

public class Ghost extends Monsters{

    @Override
    public void monsterAct(GameMap map) {
    ghostMove(map);
    }

    public void ghostMove(GameMap gameMap) {
        if (gameMap.getHeight() > this.getCell().getX()+3 && gameMap.getWidth()+3 > this.getCell().getY() && this.getCell().getX()-3 >= 0 && this.getCell().getY()-3 >= 0 )
            this.move(GameMap.randomInt(-3,3),GameMap.randomInt(-3,3),gameMap);
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
