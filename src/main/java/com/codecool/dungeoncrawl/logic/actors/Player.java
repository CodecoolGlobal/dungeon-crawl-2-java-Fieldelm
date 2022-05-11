package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;

public class Player extends Actor {
    private Inventory itemInventory;

    public Player(Cell cell) {
        super(cell);
        actual_damage = 5;
        this.itemInventory = new Inventory();

    }

    public void pickUpItem(){
       itemInventory.addItem(this.getCell().getItem());
       this.getCell().setItem(null);

    }

    public Inventory getItemInventory(){
        return this.itemInventory;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public boolean move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            if(itemInventory.hasMagicWand()) {
                nextCell.getActor().takeDamage(actual_damage, this);
            }else{
                System.out.println("You don't have your magic wand, pick it up or you will die");
            }
        }
        else if(nextCell.getType() == CellType.FLOOR){
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
            return true;
        }
        return false;
    }

    public static String noMagicWand(){
        return "You don't have your magic wand, pick it up or you will die";
    }
}
