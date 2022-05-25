package com.codecool.dungeoncrawl.JSON;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Inventory;

public class PlayerJson {

    private String playerName;
    private int hp;
    private int x;
    private int y;

    public PlayerJson(Player player) {
        this.playerName = player.getName();
        this.inventory = player.getItemInventory();
        this.x = player.getX();
        this.y = player.getY();

        this.hp = player.getHealth();

    }

    public String convertPlayerJson(){
    return ConvertToJSON.convertPlayer(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private Inventory inventory;

    public String getPlayerName() {
        return playerName;
    }

    public int getHp() {
        return hp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
