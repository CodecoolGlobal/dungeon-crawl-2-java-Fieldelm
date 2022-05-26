package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BertieBottsEveryFlavorBeans extends Item{
    public BertieBottsEveryFlavorBeans (Cell cell){
        super(cell);
    }

    @Override
    public String getTileName(){
        return "BBs E. Flavor";
    }

    @Override
    public String toString(){
        return "D";
    }

    public int randomHealingRate(){
        return ThreadLocalRandom.current().nextInt(-3, 10 + 1);
    }
}
