package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class MagicWand extends Item{
    public MagicWand(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "magicWand";
    }
}
