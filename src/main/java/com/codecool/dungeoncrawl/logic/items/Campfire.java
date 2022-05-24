package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Random;

public class Campfire extends Item implements ActableItem {

    private int healing_power = 10;
    private final int healing_rate;

    public Campfire(Cell cell) {
        super(cell);
        healing_rate = randomHealingRate();
    }

    @Override
    public String getTileName() {
        return "campfire";
    }

    @Override
    public void act(GameMap map) {
        if (Math.abs(map.getPlayer().getCell().getX() - this.getCell().getX()) < 2 &&
                Math.abs(map.getPlayer().getCell().getY() - this.getCell().getY()) < 2){
            if(healing_power >= 0){
                map.getPlayer().increaseHealth(healing_rate);
                healing_power -= healing_rate;
            }

        }
    }

    public int randomHealingRate(){
        Random rand = new Random();
        return rand.nextInt(2) + 1;
    }

    @Override
    public String toString(){
        return "c";
    }
}
