package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.MagicKey;
import javafx.scene.control.Label;

public class Player extends Actor {
    private Inventory itemInventory;
    private Label messageLabel;

    public Player(Cell cell, Label messageLabel) {
        super(cell);
        this.messageLabel = messageLabel;
        actual_damage = 5;
        this.itemInventory = new Inventory();
    }

    public void pickUpItem(){
       if (this.getCell().getItem() != null) {
           itemInventory.addItem(this.getCell().getItem());
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
            this.incraseHealth();
        }
    }

    @Override
    public void move(int dx, int dy, GameMap map) {

        Cell nextCell = getCell().getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            if(itemInventory.hasMagicWand()) {
                nextCell.getActor().takeDamage(actual_damage, this);

            }else{
                setLabelText("You don't have \nyour magic wand.");
            }
        }
        else if(nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.GRASS
                || nextCell.getType() == CellType.LESSFLOOR || nextCell.getType() == CellType.ENTRANCE
                || nextCell.getType() == CellType.ROOM){
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }else if (nextCell.getType() == CellType.CLOSED_DOOR){
            if(itemInventory.hasMagicKey()){
                itemInventory.useItem("magicKey");
                map.openDoor(nextCell.getX(), nextCell.getY());

            }else{
                setLabelText("You don't have\nthe magic key.");
            }
        }

    }

    public void setLabelText(String text){
    messageLabel.setText(text);}


}