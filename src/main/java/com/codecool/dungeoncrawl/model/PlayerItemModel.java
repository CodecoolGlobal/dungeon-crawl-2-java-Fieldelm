package com.codecool.dungeoncrawl.model;

public class PlayerItemModel extends BaseModel{
    private String name;
    private PlayerModel player;
    private int quantity;

    public PlayerItemModel(String name, int quantity, PlayerModel player){
        this.name = name;
        this.quantity = quantity;
        this.player = player;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public PlayerModel getPlayer(){
        return this.player;
    }
    public void setPlayer(PlayerModel player){
        this.player = player;
    }
    public int getQuantity(){return quantity;}
}
