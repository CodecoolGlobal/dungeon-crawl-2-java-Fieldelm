package com.codecool.dungeoncrawl.JSON;

import com.codecool.dungeoncrawl.logic.items.Item;

public class ItemJson {
    private String name;
    private int x;
    private int y;

    public ItemJson(Item item) {
        this.name = item.getTileName();
        this.x = item.getX();
        this.y = item.getY();
    }

}