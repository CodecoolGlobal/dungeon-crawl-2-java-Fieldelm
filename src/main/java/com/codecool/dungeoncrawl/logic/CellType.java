package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    TREE("tree"),
    TREETWO("treetwo"),
    NOWALL("noWall"),
    GRASS("grass"),
    WATER("water"),
    CAMPFIRE("campfire"),
    FENCE("fence"),
    LESSFLOOR("lessfloor"),
    TABLE("table"),
    STONEFENCE("stonefence"),
    STONEFENCETWO("stonefencetwo"),
    HOUSE("house"),
    ENTRANCE("entrance"),
    HOUSETWO("housetwo"),
    CLOSED_DOOR("closed_door"),
    ROOM("room"),
    SKULL("skull"),
    LOOT("loot"),
    CROSS_WATER("crossWater");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }

    public String getMapCharacter(){
        switch(tileName){
            case "empty": return " ";
            case "floor": return ".";
            case "wall": return "#";
            case "tree": return "r";
            case "treeTwo": return "e";
            case "noWall": return "-";
            case "grass": return "_";
            case "water": return "w";
            case "campfire": return "c";
            case "fence": return "f";
            case "lessfloor": return "l";
            case "table": return "b";
            case "stonefence": return "s";
            case "stonefencetwo": return "t";
            case "house": return "í";
            case "entrance": return "g";
            case "housetwo": return "i";
            case "closed_door": return "C";
            case "room": return "*";
            case "skull": return "k";
            case "loot": return "y";
            case "crossWater": return "$";
            default: return "#";
        }
    }
}
