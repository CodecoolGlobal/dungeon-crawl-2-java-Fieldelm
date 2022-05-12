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
    ROOM("room");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
