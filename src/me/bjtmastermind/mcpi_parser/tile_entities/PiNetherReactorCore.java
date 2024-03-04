package me.bjtmastermind.mcpi_parser.tile_entities;

import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;

public class PiNetherReactorCore extends PiTileEntity {
    private boolean isInitialized;
    private short progress;
    private boolean hasFinished;

    public PiNetherReactorCore(int x, int y, int z) {
        this.id = TileEntityType.NETHER_REACTOR_CORE.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.isInitialized = false;
        this.progress = 0;
        this.hasFinished = false;
    }

    public boolean getIsInitialized() {
        return this.isInitialized;
    }

    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    public short getProgress() {
        return this.progress;
    }

    public void setProgress(short progress) {
        this.progress = progress;
    }

    public boolean getHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tileEntity = new CompoundTag();

        tileEntity.put("id", this.id);
        tileEntity.put("x", this.x);
        tileEntity.put("y", this.y);
        tileEntity.put("z", this.z);
        tileEntity.put("IsInitialized", (byte) (this.isInitialized ? 1 : 0));
        tileEntity.put("Progress", this.progress);
        tileEntity.put("HasFinished", (byte) (this.hasFinished ? 1 : 0));

        return tileEntity;
    }

    public static PiNetherReactorCore fromCompoundTag(CompoundTag nbtTileEntity) {
        PiNetherReactorCore tileEntity = new PiNetherReactorCore(nbtTileEntity.getInt("x"), nbtTileEntity.getInt("y"), nbtTileEntity.getInt("z"));
        tileEntity.isInitialized = nbtTileEntity.getByte("IsInitialized") == 1 ? true : false;
        tileEntity.progress = nbtTileEntity.getShort("Progress");
        tileEntity.hasFinished = nbtTileEntity.getByte("HasFinished") == 1 ? true : false;

        return tileEntity;
    }
}
