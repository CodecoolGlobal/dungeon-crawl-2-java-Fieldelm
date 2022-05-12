package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Random;

public class Ghost extends Monsters{

    @Override
    public void monsterAct() {
        ghostMove();
    }

    public void ghostMove() {
        GameMap gameMap = this.getCell().getGameMap();
        int height = gameMap.getHeight();
        int width = gameMap.getWidth();
        int x = this.getX();
        int y = this.getY();
        int newX = GameMap.randomInt(-2,2);
        int newY = GameMap.randomInt(-2,2);
        if (height > x+newX+1 && width > y+newY+1  && x-newX-1 > 0 && y-newY-1 > 0 )
            this.move(newX, newY);
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
