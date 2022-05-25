package com.codecool.dungeoncrawl.JSON;

import com.codecool.dungeoncrawl.logic.actors.Actor;

public class ActorJson {
    private String name;
    private int hp;
    private int x;
    private int y;

    public ActorJson(Actor actor) {
        this.name = actor.getTileName();
        this.x = actor.getX();
        this.y = actor.getY();
        this.hp = actor.getHealth();
    }
    /*public String convertPlayerJson(){
        return ConvertToJSON.convertObjectToJson(this);
    }*/


}
