package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Dementor;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
Label testlabel;

    @BeforeEach
    void setUp() {
        testlabel = new Label();
    }

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1), testlabel);
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertNull(gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1), testlabel);
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveOutOfMap() {
        Player player = new Player(gameMap.getCell(2, 1), testlabel);
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(gameMap.getCell(1, 1),testlabel);
        Dementor skeleton = new Dementor(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, skeleton.getX());
        assertEquals(1, skeleton.getY());
        assertEquals(skeleton, gameMap.getCell(2, 1).getActor());
    }
}