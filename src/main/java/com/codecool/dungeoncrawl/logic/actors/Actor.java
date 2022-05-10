package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    protected int actual_damage;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public boolean move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            nextCell.getActor().takeDamage(actual_damage, this);
        }
        else if(nextCell.getType() == CellType.FLOOR){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            return true;
        }
        return false;
    }

    public int getHealth() {
        return health;
    }

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
