package com.codecool.dungeoncrawl.logic.items;


import com.codecool.dungeoncrawl.logic.Cell;

public class MagicKey extends Item
{
    public MagicKey(Cell cell){super(cell);}

    @Override
    public String getTileName() {
        return "magicKey";
    }
}
