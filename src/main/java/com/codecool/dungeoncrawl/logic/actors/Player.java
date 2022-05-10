package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;

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

    public String getTileName() {
        return "player";
    }
}
