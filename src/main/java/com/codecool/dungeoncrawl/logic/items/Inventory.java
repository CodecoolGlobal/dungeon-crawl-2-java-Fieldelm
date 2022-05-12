package com.codecool.dungeoncrawl.logic.items;

import java.util.HashMap;
import java.util.Objects;

public class Inventory {
    private HashMap <String, Integer> items;

    public Inventory(){
        this.items = new HashMap<>();
    }

    public void addItem(String itemName){
        if (items.containsKey(itemName)) {
            int x = items.get(itemName) + 1;
            items.replace(itemName, x);
        }
        items.put(itemName, 1);
    }


public boolean hasMagicWand(){
    for (String key : items.keySet()) {
        if (Objects.equals(key, "magicWand")) return true;
    }
    return false;
}
    public boolean hasMagicKey(){
        for (String key : items.keySet()) {
            if (Objects.equals(key, "magicKey")) return true;
        }
        return false;
    }

    public void useItem(String itemName){
        if(items.get(itemName) > 1){
            items.put(itemName, items.get(itemName) -1);
        }else{
            items.remove(itemName);
        }
    }

    @Override
    public String toString() {
        StringBuilder inventoryString = new StringBuilder("Inventory items:\n");

        items.forEach((item, amount) -> {
            inventoryString.append(item).append(": ").append(amount).append("\n");
        });
        return (items.size() >0 ? inventoryString.toString(): "No items in inventory");
    }


}
