package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

public class Dementor extends Monsters {




    @Override
    public void monsterAct() {
        GameMap map = this.getCell().getGameMap();
        if (Math.abs(map.getPlayer().getCell().getX() - this.getCell().getX()) < 2 && Math.abs(map.getPlayer().getCell().getY() - this.getCell().getY()) < 2){
            map.getPlayer().takeDamage(this.getActual_damage(),this);
            incraseHealth();
            attackSound();

        }
    }

    private void attackSound(){
        Media dementorMedia = new Media(new File("src/main/resources/dementor.wav").toURI().toString());
        MediaPlayer dementorMediaPlayer = new MediaPlayer(dementorMedia);
        dementorMediaPlayer.play();
    }

    public Dementor(Cell cell) {
        super(cell);
        this.setActual_damage(2);
    }



    @Override
    public String getTileName() {
        return "dementor";
    }

    @Override
    public String toString(){
        return "d";
    }
}
