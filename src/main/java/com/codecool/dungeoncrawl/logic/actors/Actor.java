package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private int actual_damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public abstract void move(int dx, int dy);

    public int getHealth() {
        return health;
    }
    public void setHealth(int health){this.health = health;}

    public void incraseHealth() {
        health++;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void setCell(Cell cell){
        this.cell = cell;
    }
    public int getActual_damage(){
        return actual_damage;
    }
    public void setActual_damage(int dmg){
        actual_damage += dmg;
    }
    public void takeDamage(int damage, Actor actor){
        health -= damage;
        if(health <= 0){
            cell.setActor(null);
        }
        else if(actor.getTileName().equals("player")){
            actor.takeDamage(actual_damage, this);
        }
    }

}
