package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class BertieBottsEveryFlavorBeans extends Item{
    public BertieBottsEveryFlavorBeans (Cell cell){
        super(cell);
    }

    @Override
    public String getTileName(){
        return "BB's E. Flavor";
    }

    @Override
    public String toString(){
        return "D";
    }
}
