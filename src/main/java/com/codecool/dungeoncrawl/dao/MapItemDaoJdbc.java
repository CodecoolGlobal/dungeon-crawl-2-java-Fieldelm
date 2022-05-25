package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.MapItemModel;
import com.codecool.dungeoncrawl.model.PlayerItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MapItemDaoJdbc implements MapItemDao {
    private final DataSource dataSource;
    private final GameStateDao gameStateDao;
    public MapItemDaoJdbc(DataSource dataSource, GameStateDao gameStateDao) {
        this.dataSource = dataSource;
        this.gameStateDao = gameStateDao;
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
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT map_id, item_name, x, y FROM map_items WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            String name = resultSet.getString(2);
            int x = resultSet.getInt(3);
            int y = resultSet.getInt(4);
            GameState map = gameStateDao.get(resultSet.getInt(1));

            MapItemModel item = new MapItemModel(name, x, y, map);
            item.setId(id);
            return item;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading map_items with id: " + id, e);
        }
    }

    @Override
    public List<MapItemModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, map_id, item_name, x, y FROM map_items";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            List<MapItemModel> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int map_id = resultSet.getInt(2);
                String item_name = resultSet.getString(3);
                int x = resultSet.getInt(4);
                int y = resultSet.getInt(5);

                GameState map = gameStateDao.get(map_id);

                MapItemModel item = new MapItemModel(item_name, x, y, map);
                item.setId(id);
                result.add(item);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
