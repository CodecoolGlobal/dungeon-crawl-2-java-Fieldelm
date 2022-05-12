package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class Main extends Application {
    static final int CONST_10 = 10;
    Label message = new Label();
    GameMap map = MapLoader.loadMap(message);
    Canvas canvas = new Canvas(
            21 * Tiles.TILE_WIDTH,
            21 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventory = new Label();
    Button pickUpItem = new Button("Pick up");



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(pickUpItem, 0, 2 );
        ui.add(inventory, 0, 3);
        ui.add(message, 0, 4);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1, map);
                map.repositionCenter();
                map.monsterMove();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1, map);
                map.repositionCenter();
                map.monsterMove();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0, map);
                map.repositionCenter();
                map.monsterMove();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0, map);
                map.repositionCenter();
                map.monsterMove();
                refresh();
                break;
            case SPACE:
                map.getPlayer().pickUpItem();
                break;


        }
    }

    private void refresh() {
        int minX = map.getCenterCell().getX() - CONST_10;
        int minY = map.getCenterCell().getY() - CONST_10;
        int maxX = map.getCenterCell().getX() + CONST_10 + 1;
        int maxY = map.getCenterCell().getY() + CONST_10 + 1;
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, 20, 20);
        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x-minX, y-minY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x-minX, y-minY);
                }else {
                    Tiles.drawTile(context, cell, x-minX, y-minY);

                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        pickUpItem.setOnAction(mousedown -> map.getPlayer().pickUpItem());
        pickUpItem.setFocusTraversable(false);
        inventory.setText(map.getPlayer().getItemInventory().toString());
    }
}
