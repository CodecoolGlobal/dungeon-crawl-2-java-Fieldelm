package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerItemModel;

import java.util.List;

public interface PlayerItemDao {
    void add(PlayerItemModel item);
    void update(PlayerItemModel item);
    PlayerItemModel get(int id);
    List<PlayerItemModel> getAll();
}
