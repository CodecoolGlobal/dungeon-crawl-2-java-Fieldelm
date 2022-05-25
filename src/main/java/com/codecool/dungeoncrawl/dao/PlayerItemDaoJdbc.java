package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerItemDaoJdbc implements PlayerItemDao{
    private final DataSource dataSource;

    public PlayerItemDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerItemModel item) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "INSERT INTO player_items (player_id, item_name) VALUES(?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, item.getPlayer().getId());
            statement.setString(2, item.getName());
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
    public void update(PlayerItemModel item) {
        try (Connection conn = dataSource.getConnection()){
            String sql ="UPDATE player_items SET player_id = ?, item_name = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, item.getPlayer().getId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getId());
            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerItemModel get(int id) {
        return null;
    }

    @Override
    public List<PlayerItemModel> getAll() {
        return null;
    }
}
