package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.scene.input.KeyEvent;

import java.util.Random;

public class Troll extends Monsters{

    @Override
    public void monsterAct(GameMap map) {
    trollMove(map);
    }

    public Troll(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "troll";
    }

    public void trollMove(GameMap gameMap){
        Random random = new Random();
        switch (random.nextInt(4)){
            case 0: this.move(0,-1, gameMap);
                break;
            case 1: this.move(0,1, gameMap);
                break;
            case 2: this.move(-1,0, gameMap);
                break;
            case 3: this.move(1,0, gameMap);
                break;
        }
    }
}


