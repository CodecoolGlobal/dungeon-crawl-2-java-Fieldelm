package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class magicWand extends Item{
    public magicWand(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "magicWand";
    }
}
