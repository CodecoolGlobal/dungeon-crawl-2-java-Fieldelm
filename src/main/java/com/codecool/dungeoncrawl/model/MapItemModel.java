package com.codecool.dungeoncrawl.model;

public class MapItemModel extends BaseModel{
    private String name;
    private int x;
    private int y;
    private GameState map;

    MapItemModel(String name, int x, int y, GameState map){
        this.name = name;
        this.x = x;
        this.y = y;
        this. map = map;
    }

    public String getName(){return name;}
    public int getX(){return x;}
    public int getY(){return y;}
    public GameState getMap(){return map;}

    public void setName(String name){this.name = name;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setMap(GameState map){this.map = map;}
}
