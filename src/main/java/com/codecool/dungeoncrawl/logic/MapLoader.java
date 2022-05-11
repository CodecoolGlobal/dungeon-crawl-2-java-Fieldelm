package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Dementor;
import com.codecool.dungeoncrawl.logic.actors.Troll;
import com.codecool.dungeoncrawl.logic.items.magicWand;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/bigmap.txt");
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
                            break;
                        case 'f':
                            cell.setType(CellType.FENCE);
                            break;
                        case 'l':
                            cell.setType(CellType.LESSFLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.STONEFENCE);
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
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new magicWand(cell);
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
