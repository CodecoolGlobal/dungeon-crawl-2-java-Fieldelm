package com.codecool.dungeoncrawl.JSON;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.google.gson.Gson;

public class ConvertToJSON {
    public static String convertPlayer(Player player) {
        Gson JSONObject = new Gson();
        return JSONObject.toJson(player.getCell().getX());
    }

}
