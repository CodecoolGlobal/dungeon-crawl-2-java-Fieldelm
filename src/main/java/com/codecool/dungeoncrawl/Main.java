package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.util.Duration;


import java.io.File;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    static final int CONST_10 = 10;
    Label message = new Label();
    GameMap map = MapLoader.loadMap(message);
    Canvas canvas = new Canvas(
            21 * Tiles.TILE_WIDTH,
            21 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Font myFont = new Font("Serif", 18);
    GridPane ui = new GridPane();


    Label healthLabel = new Label();
    Label damageLabel = new Label();
    Label inventory = new Label();
    Label gameOver = new Label();

    boolean isGameOver = false;
    Media backgroundMedia;
    MediaPlayer backgroundMediaPlayer;
    GameDatabaseManager dbManager;
    Timer timer = new Timer();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        setupDbManager();
        backgroundMedia = new Media(new File("src/main/resources/background-music.wav").toURI().toString());
        backgroundMediaPlayer = new MediaPlayer(backgroundMedia);
        backgroundMediaPlayer.setAutoPlay(true);
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        ui.add(healthLabel, 0, 3);
        ui.add(damageLabel, 0, 4);
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
        moveMonsters();
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
            showSaveOption();
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

        }
        map.repositionCenter();
        map.actAllMapCreature();
        refresh();
        if (map.getPlayer().hasFriends()) {
            gameWonDisplay();
        }
        if (map.getPlayer().getHealth() < 0) {
            gameOverDisplay();
        }
    }

    private void moveMonsters(){
        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        event -> {
                            map.actAllMapCreature();
                            refresh();
                        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

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
                    Tiles.drawTile(context, cell.getActor(), x - minX, y - minY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x - minX, y - minY);
                } else {
                    Tiles.drawTile(context, cell, x - minX, y - minY);

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
        timer.cancel();
        isGameOver = true;
        Font myFont = new Font("Serif", 36);
        gameOver.setText("Game\nOver!");
        gameOver.setFont(myFont);
        refresh();

    }
    public void gameWonDisplay(){
        timer.cancel();
        isGameOver = true;
        Font myFont = new Font("Serif", 22);
        gameOver.setText("Congratulation!");
        gameOver.setFont(myFont);
        refresh();
    }

    /*public void stepSound() {
        Media stepMedia = new Media(new File("src/main/resources/step.wav").toURI().toString());
        MediaPlayer stepMediaPlayer = new MediaPlayer(stepMedia);
        stepMediaPlayer.play();
    }*/

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    /*public void saveName(Label nameLabel) {

    }*/

    public void showSaveOption() {

        Label nameLabel = new Label("Please enter your name");
        ui.add(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        ui.add(nameInput, 0, 1);
        Button saveButton = new Button("Save");
        ui.add(saveButton, 0, 2);
        saveButton.setOnAction(e -> {
            String name = nameInput.getText();
            map.getPlayer().setName(name);
            //TODO: save gamestate to sql
            dbManager.savePlayer(map.getPlayer());
            ui.getChildren().remove(nameInput);
            ui.getChildren().remove(nameLabel);
            ui.getChildren().remove(saveButton);
            Label playerName = new Label(name);
            ui.add(playerName, 0, 0);
            playerName.setFont(myFont);
            System.out.println(map.getPlayer().getName());
        });
    }
}
