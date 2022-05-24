package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Character extends Item{
    String name;
    public Character(Cell cell, String name){
        super(cell);
        this.name = name;
    }
    public String getName(){return name;}

    @Override
    public String getTileName() {
        return name;
    }

    @Override
    public String toString(){
        return "R";
    }
}
