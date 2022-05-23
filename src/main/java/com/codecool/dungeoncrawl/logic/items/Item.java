package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;

public Item(Cell cell){
    this.cell = cell;
    this.cell.setItem(this);

}

public Cell getCell(){ return this.cell;}

public void setCell(Cell cell){
    this.cell = cell;
}

public int getX(){ return cell.getX();}

    public int getY(){ return cell.getY();}

}
