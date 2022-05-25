package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Dementor;
import com.codecool.dungeoncrawl.logic.actors.Troll;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.items.Character;
import javafx.scene.control.Label;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String fileName) {
        InputStream is = MapLoader.class.getResourceAsStream(fileName);
        assert is != null;
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case 'r':
                            cell.setType(CellType.TREE);
                            break;
                        case '*':
                            cell.setType(CellType.ROOM);
                            break;
                        case 'e':
                            cell.setType(CellType.TREETWO);
                            break;
                        case 'b':
                            cell.setType(CellType.TABLE);
                            break;
                        case 'i':
                            cell.setType(CellType.HOUSETWO);
                            break;
                        case 'g':
                            cell.setType(CellType.ENTRANCE);
                            break;
                        case 'í':
                            cell.setType(CellType.HOUSE);
                            break;
                        case 'c':
                            cell.setType(CellType.CAMPFIRE);
                            new Campfire(cell);
                            break;
                        case 'f':
                            cell.setType(CellType.FENCE);
                            break;
                        case 'l':
                            cell.setType(CellType.LESSFLOOR);
                            break;
                        case 'y':
                        cell.setType(CellType.LOOT);
                            break;
                        case 's':
                            cell.setType(CellType.STONEFENCE);
                            break;
                        case 'k':
                            cell.setType(CellType.SKULL);
                            break;
                        case 't':
                            cell.setType(CellType.STONEFENCETWO);
                            break;
                        case '-':
                            cell.setType(CellType.NOWALL);
                            break;
                        case 'w':
                            cell.setType(CellType.WATER);
                            break;
                        case '_':
                            cell.setType(CellType.GRASS);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new Dementor(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'G':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case 'A':
                            cell.setType(CellType.FLOOR);
                            new Troll(cell);
                            break;
                        case 'W':
                            cell.setType(CellType.FLOOR);
                            new MagicWand(cell);
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            new MagicKey(cell);
                            break;
                        case 'C':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Broom(cell);
                            break;
                        case 'D':
                            cell.setType(CellType.FLOOR);
                            new BertieBottsEveryFlavorBeans(cell);
                            break;
                        case '$':
                            cell.setType(CellType.CROSS_WATER);
                            break;
                        case 'R':
                            cell.setType(CellType.FLOOR);
                            new Character(cell, "ron");
                            break;
                        case 'H':
                            cell.setType(CellType.FLOOR);
                            new Character(cell, "hermione");
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
