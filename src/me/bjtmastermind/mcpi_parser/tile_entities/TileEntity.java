package me.bjtmastermind.mcpi_parser.tile_entities;

import me.bjtmastermind.mcpi_parser.enums.TileEntityType;

public class TileEntity {
    String id;
    int x;
    int y;
    int z;

    TileEntity() {}

    public String getID() {
        return this.id;
    }

    public void setID(TileEntityType id) {
        this.id = id.getID();
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+String.format("(x=%d,y=%d,z=%d)", this.x, this.y, this.z);
    }
}
