package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MapItemModel;

import java.util.List;

public interface MapItemDao {
    void add(MapItemModel item);
    void update(MapItemModel item);
    MapItemModel get(int id);
    List<MapItemModel> getAll();
}
