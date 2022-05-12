package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import java.util.Random;

public class Dementor extends Monsters {




    @Override
    public void monsterAct(GameMap map) {
        if (Math.abs(map.getPlayer().getCell().getX() - this.getCell().getX()) < 2 && Math.abs(map.getPlayer().getCell().getY() - this.getCell().getY()) < 2){
            map.getPlayer().takeDamage(this.getActual_damage(),this);
            incraseHealth();

        }
    }


    public Dementor(Cell cell) {
        super(cell);
        this.setActual_damage(2);
    }



    @Override
    public String getTileName() {
        return "dementor";
    }
}
