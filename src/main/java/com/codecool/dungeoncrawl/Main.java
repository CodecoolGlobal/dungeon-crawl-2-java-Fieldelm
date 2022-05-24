package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;


//import javax.print.attribute.standard.Media;


import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {
    static final int CONST_10 = 10;
    Label message = new Label();
    GameMap map = MapLoader.loadMap(message);
    Canvas canvas = new Canvas(
            21 * Tiles.TILE_WIDTH,
            21 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();


    Label healthLabel = new Label();
    Label damageLabel = new Label();
    Label inventory = new Label();
    Label gameOver = new Label();

    boolean isGameOver = false;
    Media backgroundMedia;
    MediaPlayer backgroundMediaPlayer;
    GameDatabaseManager dbManager;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setupDbManager();
        backgroundMedia = new Media(new File("src/main/resources/background-music.wav").toURI().toString());
        backgroundMediaPlayer = new MediaPlayer(backgroundMedia);
        backgroundMediaPlayer.setAutoPlay(true);
        Font myFont = new Font("Serif",  18);
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label ("Please enter your name"),0,0);
        TextField nameInput = new TextField();
        ui.add(nameInput, 0, 1);
        Button saveButton = new Button("Save");
        ui.add(saveButton, 1,2);
        saveButton.setOnAction(e -> {
            String name = nameInput.getText();

        });


        ui.add(healthLabel, 0, 3);
        ui.add(damageLabel, 0,4);
       // ui.add(new Label(), 0,4);
        ui.add(inventory, 0, 5);
        ui.add(message, 0, 6);
        ui.add(gameOver, 0, 8);
        healthLabel.setFont(myFont);
        damageLabel.setFont(myFont);
        message.setFont(myFont);
        inventory.setFont(myFont);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
        else if(saveCombination.match(keyEvent)){
            //ToDo save game
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                break;
            case SPACE:
                map.getPlayer().pickUpItem();
                break;
            case S:
                Player player = map.getPlayer();
                dbManager.savePlayer(player);
                break;
        }
        map.repositionCenter();
        map.actAllMapCreature();
        refresh();
        if(map.getPlayer().hasFriends()){
            gameWonDisplay();
        }
        if (map.getPlayer().getHealth() < 0) {
            gameOverDisplay();
        }
    }


    private void refresh() {
        int minX = map.getCenterCell().getX() - CONST_10;
        int minY = map.getCenterCell().getY() - CONST_10;
        int maxX = map.getCenterCell().getX() + CONST_10;
        int maxY = map.getCenterCell().getY() + CONST_10;
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, 20, 20);
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
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
        healthLabel.setText("Health:  " + map.getPlayer().getHealth() + "/" + map.getPlayer().getMaxHealth());
        damageLabel.setText("Damage:  " + map.getPlayer().getActual_damage());
        inventory.setText(map.getPlayer().getItemInventory().toString());
        //stepSound();
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }
    public void gameOverDisplay(){
        isGameOver = true;
        Font myFont = new Font("Serif",  36);
        gameOver.setText("Game\nOver!");
        gameOver.setFont(myFont);
        refresh();

    }
    public void gameWonDisplay(){
        isGameOver = true;
        Font myFont = new Font("Serif",  22);
        gameOver.setText("Congratulation!");
        gameOver.setFont(myFont);
        refresh();
    }
    public void stepSound(){
        Media stepMedia = new Media(new File("src/main/resources/step.wav").toURI().toString());
        MediaPlayer stepMediaPlayer = new MediaPlayer(stepMedia);
        stepMediaPlayer.play();
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    public void saveName(Label nameLabel){

    }
}
