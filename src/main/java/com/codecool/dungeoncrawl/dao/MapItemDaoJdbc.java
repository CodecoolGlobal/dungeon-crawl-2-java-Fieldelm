package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.MapItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class MapItemDaoJdbc implements MapItemDao {
    private final DataSource dataSource;

    public MapItemDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(MapItemModel item) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "INSERT INTO map_items (map_id, item_name, x, y) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, item.getMap().getId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getX());
            statement.setInt(4, item.getY());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            item.setId(resultSet.getInt(1));
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MapItemModel item) {
        try (Connection conn = dataSource.getConnection()){
            String sql ="UPDATE map_items SET map_id = ?, item_name = ?, x = ?, y = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, item.getMap().getId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getX());
            statement.setInt(4, item.getY());
            statement.setInt(5, item.getId());
            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public MapItemModel get(int id) {
        return null;
    }

    @Override
    public List<MapItemModel> getAll() {
        return null;
    }
}
