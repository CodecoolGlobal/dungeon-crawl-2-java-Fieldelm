package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerItemDaoJdbc implements PlayerItemDao{
    private final DataSource dataSource;
    private final PlayerDao playerDao;

    public PlayerItemDaoJdbc(DataSource dataSource, PlayerDao playerDao) {
        this.dataSource = dataSource;
        this.playerDao = playerDao;
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
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT player_id, item_name FROM player_items WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            PlayerModel playerModel = playerDao.get(resultSet.getInt(1));

            PlayerItemModel item = new PlayerItemModel(resultSet.getString(2), playerModel);
            item.setId(id);
            return item;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading player_items with id: " + id, e);
        }
    }

    @Override
    public List<PlayerItemModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, player_id, item_name FROM player_items";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            List<PlayerItemModel> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int player_id = resultSet.getInt(2);
                String item_name = resultSet.getString(3);

                PlayerModel playerModel = playerDao.get(player_id);

                PlayerItemModel item = new PlayerItemModel(item_name, playerModel);
                item.setId(id);
                result.add(item);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
