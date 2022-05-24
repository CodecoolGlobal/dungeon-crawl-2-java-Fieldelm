package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Broom extends Item{

    public Broom(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName(){
        return "Broom";
    }

    @Override
    public String toString(){
        return "B";
    }
}
