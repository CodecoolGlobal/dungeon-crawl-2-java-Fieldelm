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
        int newX = GameMap.randomInt(-1,1);
        int newY = GameMap.randomInt(-1,1);
        if (height > x+newX && width > y+newY  && x-newX > 0 && y-newY > 0 )
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

    @Override
    public String toString(){
        return "G";
    }
}
