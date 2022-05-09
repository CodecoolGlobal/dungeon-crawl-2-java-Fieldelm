package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor{
    public Ghost(Cell cell) {
        super(cell);
        actual_damage = 2;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
