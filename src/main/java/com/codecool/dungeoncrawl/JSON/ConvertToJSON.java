package com.codecool.dungeoncrawl.JSON;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.google.gson.Gson;

public class ConvertToJSON {
    public static String convertPlayer(Object obj) {
        Gson JSONObject = new Gson();
        return JSONObject.toJson(obj);
    }

}
