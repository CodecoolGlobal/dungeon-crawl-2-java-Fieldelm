package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.MapItemModel;
import com.codecool.dungeoncrawl.model.PlayerItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private PlayerItemDao playerItemDao;
    private MapItemDao mapItemDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource, playerDao);
        playerItemDao = new PlayerItemDaoJdbc(dataSource, playerDao);
        mapItemDao = new MapItemDaoJdbc(dataSource, gameStateDao);
    }

    public void saveGameState(GameMap map){
        PlayerModel playerModel = new PlayerModel(map.getPlayer());
        String currentMap = map.getFileName();
        java.sql.Date saved_at = new java.sql.Date(System.currentTimeMillis());
        GameState gameState = new GameState(currentMap, saved_at, playerModel);
        playerDao.add(playerModel);
        gameStateDao.add(gameState);
        map.getPlayer().getItemInventory().getItems().forEach((name, quantity) -> {
            PlayerItemModel playerItemModel = new PlayerItemModel(name, quantity, playerModel);
            playerItemDao.add(playerItemModel);
        });
        map.getItems().forEach(item -> {
            String name = item.getTileName();
            int x = item.getX();
            int y = item.getY();
            MapItemModel mapItemModel = new MapItemModel(name, x, y, gameState);
            mapItemDao.add(mapItemModel);
        });
        map.getMonsters().forEach(monster -> {
            String name = monster.getTileName();
            int x = monster.getX();
            int y = monster.getY();
            MapItemModel mapItemModel = new MapItemModel(name, x, y, gameState);
            mapItemDao.add(mapItemModel);
        });
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
