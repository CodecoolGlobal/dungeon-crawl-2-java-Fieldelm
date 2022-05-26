package com.codecool.dungeoncrawl.JSON;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HandleJSON {
    public static String convertObjectToJson(Object obj) {
        Gson JSONObject = new Gson();
        return JSONObject.toJson(obj);
    }

    public static List<Object> getEveryMember(GameMap map){
        List<Object> members = new ArrayList<>();
        for (Actor actor: map.getActors()){
            if(actor instanceof Player){
                members.add( new PlayerJson((Player) actor));
            }else{
                members.add(new ActorJson(actor));
            }
        }
        for (Item item : map.getItems()){
            members.add(new ItemJson(item));
        }
        return members;
    }
    public static String createStringFromJSONList(List<String> JSONList){
        StringBuilder JSONbuilder = new StringBuilder();

        for(String objectJson : JSONList){
            JSONbuilder.append(objectJson);
        }
        return JSONbuilder.toString();
    }

    public static String createFinalJsonString(GameMap map){
        List<String> list = mapConverter(map);
        return createStringFromJSONList(list);
    }

    public static List<String> mapConverter(GameMap map) {
        List<Object> mapObjects = getEveryMember(map);
        List<String> JSONList = new ArrayList<>();
        for (Object obj : mapObjects) {
            JSONList.add(convertObjectToJson(obj));

        }
        for (String objectJson : JSONList) {
            System.out.println(objectJson);
        }
        return JSONList;
    }

}
