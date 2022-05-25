package com.codecool.dungeoncrawl.model;

public class PlayerItemModel extends BaseModel{
    private String name;
    private PlayerModel player;

    public PlayerItemModel(String name, PlayerModel player){
        this.name = name;
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
}
