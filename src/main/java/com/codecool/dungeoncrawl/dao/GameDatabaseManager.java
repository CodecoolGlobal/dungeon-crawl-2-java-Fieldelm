package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Dementor;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Troll;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.MapItemModel;
import com.codecool.dungeoncrawl.model.PlayerItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class GameDatabaseManager {
    static final String BLANK_EXTENSION = "_blank";
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

    public GameMap loadGame(String playerName){
        PlayerModel playerModel = playerDao.get(playerName);
        GameState gameState = gameStateDao.getGameStateByPlayerId(playerModel.getId());
        List<MapItemModel> mapItems = mapItemDao.getMapItems(gameState.getId());
        List<PlayerItemModel> playerItems = playerItemDao.getPlayerItems(playerModel.getId());

        GameMap map = MapLoader.loadMap(gameState.getCurrentMap() + BLANK_EXTENSION);

        Cell playerCell = map.getCell(playerModel.getX(), playerModel.getY());
        Player player = new Player(playerCell);
        player.setName(playerModel.getPlayerName());
        player.setHealth(playerModel.getHp());

        playerItems.forEach(item ->{
            for(int i = 0; i < item.getQuantity(); i++){
                player.loadUpItem(item.getName());
            }
        });
        map.setPlayer(player);

        /*mapItems.forEach(item ->{
            Cell itemCell = map.getCell(item.getX(), item.getY());
            switch (item.getName()){
                case "Broom":
                    map.placeItem(new Broom(itemCell));
                    break;
                case "BB's E. Flavor":
                    map.placeItem(new BertieBottsEveryFlavorBeans(itemCell));
                    break;
                case "campfire":
                    map.placeItem(new Campfire(itemCell));
                    break;
                case "magicKey":
                    map.placeItem(new MagicKey(itemCell));
                    break;
                case "magicWand":
                    map.placeItem(new MagicWand(itemCell));
                    break;
                case "ron":
                    map.placeItem(new com.codecool.dungeoncrawl.logic.items.Character(itemCell, "Ron"));
                    break;
                case "dementor":
                    map.placeItem(new Dementor(itemCell));
                    break;
                case "ghost":
                    map.placeItem(new Ghost(itemCell));
                    break;
                case "troll":
                    map.placeItem(new Troll(itemCell));
                    break;
            }
        });*/
        return map;
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
