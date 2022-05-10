package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    private HashMap <Item, Integer> items;

    public Inventory(){
        this.items = new HashMap<>();
    }

    public void addItem(Item item){
        if (items.containsKey(item)) {
            items.put(item, items.get(item) +1);
        }
        items.put(item, 1);
    }

    public void pickOutItem(Item item){
        if (items.containsKey(item)){
            if (items.get(item) >1){
                items.put(item, items.get(item) -1);
            }else {
                items.remove(item);
            }
            }
        else{
            System.out.println("No such item in inventory");
        }
    }

    @Override
    public String toString() {
        StringBuilder inventoryString = new StringBuilder("Inventory items:\n");

        items.forEach((item, amount) -> {
            inventoryString.append(item.getTileName() + amount);
        });
        return (items.size() >0 ? inventoryString.toString(): "No items in inventory");
    }


}
