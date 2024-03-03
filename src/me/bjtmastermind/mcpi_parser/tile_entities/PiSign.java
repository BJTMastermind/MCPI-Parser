package me.bjtmastermind.mcpi_parser.tile_entities;

import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;

public class PiSign extends PiTileEntity {
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    public PiSign(int x, int y, int z) {
        this.id = TileEntityType.SIGN.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.text1 = "";
        this.text2 = "";
        this.text3 = "";
        this.text4 = "";
    }

    public String getText1() {
        return this.text1;
    }

    public void setText1(String text) {
        this.text1 = text;
    }

    public String getText2() {
        return this.text2;
    }

    public void setText2(String text) {
        this.text2 = text;
    }

    public String getText3() {
        return this.text3;
    }

    public void setText3(String text) {
        this.text3 = text;
    }

    public String getText4() {
        return this.text4;
    }

    public void setText4(String text) {
        this.text4 = text;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tileEntity = new CompoundTag();

        tileEntity.put("id", this.id);
        tileEntity.put("x", this.x);
        tileEntity.put("y", this.y);
        tileEntity.put("z", this.z);
        tileEntity.put("Text1", this.text1);
        tileEntity.put("Text2", this.text2);
        tileEntity.put("Text3", this.text3);
        tileEntity.put("Text4", this.text4);

        return tileEntity;
    }
}
