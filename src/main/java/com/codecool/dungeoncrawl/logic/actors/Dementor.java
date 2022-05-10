package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Dementor extends Monsters {


    @Override
    public void monsterAct(GameMap map) {
        if (Math.abs(map.getPlayer().getCell().getX() - this.getCell().getX()) < 2 && Math.abs(map.getPlayer().getCell().getY() - this.getCell().getY()) < 2){
            map.getPlayer().takeDamage(actual_damage,this);
            incraseHealth();
        }


//        int minX = this.getCell().getX()-2;
//        int maxX = this.getCell().getX()+2;
//        int minY = this.getCell().getY()-2;
//        int maxY = this.getCell().getY()+2;
//        for (int x = minX; x <= maxX; x++)
//            for (int y = minY; y <= maxY; y++)
//                if (map.getCell(x,y).getActor().getTileName().equals("player")){
//                    map.getCell(x,y).getActor().takeDamage(actual_damage, this);
//                    incraseHealth();
//                }
  }

    public Dementor(Cell cell) {
        super(cell);
        actual_damage = 2;
    }



    @Override
    public String getTileName() {
        return "dementor";
    }
}
