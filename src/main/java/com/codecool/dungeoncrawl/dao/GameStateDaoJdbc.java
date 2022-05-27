package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private final DataSource dataSource;
    private final PlayerDao playerDao;

    public GameStateDaoJdbc(DataSource dataSource, PlayerDao playerDao) {
        this.dataSource = dataSource;
        this.playerDao = playerDao;
    }
    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) VALUES ( ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setDate(2, state.getSavedAt());
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "UPDATE game_state SET current_map = ?, saved_at = ?, player_id = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, state.getCurrentMap());
            statement.setDate(2, state.getSavedAt());
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT current_map, saved_at, player_id FROM game_state WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            PlayerModel playerModel = playerDao.get(resultSet.getInt(3));

            GameState gameState = new GameState(resultSet.getString(1), resultSet.getDate(2), playerModel);
            gameState.setId(id);
            return gameState;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading game state with id: " + id, e);
        }
    }

    @Override
    public List<GameState> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, current_map, saved_at, player_id FROM game_state";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            List<GameState> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String current_map = resultSet.getString(2);
                Date saved_at = resultSet.getDate(3);
                int player_id = resultSet.getInt(4);

                PlayerModel playerModel = playerDao.get(player_id);

                GameState gameState = new GameState(current_map, saved_at, playerModel);
                gameState.setId(id);
                result.add(gameState);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState getGameStateByPlayerId(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, current_map, saved_at FROM game_state WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, player_id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            PlayerModel playerModel = playerDao.get(player_id);
            int id = resultSet.getInt(1);
            GameState gameState = new GameState(resultSet.getString(2), resultSet.getDate(3), playerModel);
            gameState.setId(id);
            return gameState;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading game state with id: " + player_id, e);
        }
    }
}
