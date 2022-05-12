package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.MagicKey;
import com.codecool.dungeoncrawl.logic.items.MagicWand;
import javafx.scene.control.Label;

public class Player extends Actor {
    private Inventory itemInventory;
    private Label messageLabel;

    private final int maxHealth = 30;

    public Player(Cell cell, Label messageLabel) {
        super(cell);
        this.messageLabel = messageLabel;
        this.setActual_damage(5);
        this.itemInventory = new Inventory();
    }

    public void pickUpItem(){
       if (this.getCell().getItem() != null) {
           Item item = this.getCell().getItem();
           itemInventory.addItem(item.getTileName());
           if(item instanceof MagicWand){
               this.setActual_damage(((MagicWand) item).getDamage());
           }
           this.getCell().setItem(null);
           setLabelText("");
       }else {
           setLabelText("There is nothing");
       }

    }

    public Inventory getItemInventory(){
        return this.itemInventory;
    }

    public String getTileName() {
        return "player";
    }

    public void increaseHealth(int health){
        for(int i = 0; i< health; i++){
            if(getHealth() < maxHealth)
                this.incraseHealth();
        }
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            if(itemInventory.hasMagicItem("magicWand")) {
                nextCell.getActor().takeDamage(this.getActual_damage(), this);

            }else{
                setLabelText("You don't have \nyour magic wand.");
            }
        }
        else if(nextCell.isSteppable()){
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }else if (nextCell.isClosedDoor()){
            if(itemInventory.hasMagicItem("magicKey")){

                getCell().getGameMap().openDoor(nextCell.getX(), nextCell.getY());
                itemInventory.useItem("magicKey");

            }else{
                setLabelText("You don't have\nthe magic key.");
            }
        }else if (nextCell.isWater()){
            if(itemInventory.hasMagicItem("Broom")){
                getCell().getGameMap().crossWater(nextCell.getX(), nextCell.getY());
            }else{
                setLabelText("You need\na broom to\n cross.");
            }
        }

    }

    public void setLabelText(String text){
        messageLabel.setText(text);
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public boolean hasFriends(){
        return itemInventory.hasFriends();
    }
}