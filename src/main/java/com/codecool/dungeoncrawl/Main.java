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

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

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
                if(map.getPlayer().move(0, -1))
                    map.setCenterCell(0, -1);
                refresh();
                break;
            case DOWN:
                if(map.getPlayer().move(0, 1))
                    map.setCenterCell(0, 1);
                refresh();
                break;
            case LEFT:
                if(map.getPlayer().move(-1, 0))
                    map.setCenterCell(-1, 0);
                refresh();
                break;
            case RIGHT:
                if(map.getPlayer().move(1,0))
                    map.setCenterCell(1, 0);
                refresh();
                break;
        }
    }

    private void refresh() {
        int minX = map.getCenterCell().getX() - 10;
        int minY = map.getCenterCell().getY() - 10;
        int maxX = map.getCenterCell().getX() + 10;
        int maxY = map.getCenterCell().getY() + 10;
        context.setFill(Color.BLACK);
        context.fillRect(10, 10, 20, 20);
        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x - minX, y - minY);
                } else {
                    Tiles.drawTile(context, cell, x - minX, y - minY);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
