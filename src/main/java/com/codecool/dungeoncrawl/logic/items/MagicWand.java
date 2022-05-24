package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class MagicWand extends Item{

    private final int dmg;
    public MagicWand(Cell cell){
        super(cell);
        dmg = GameMap.randomInt(5, 10);
    }

    public int getDamage(){
        return dmg;
    }
    @Override
    public String getTileName() {
        return "magicWand";
    }

    @Override
    public String toString(){
        return "W";
    }
}
