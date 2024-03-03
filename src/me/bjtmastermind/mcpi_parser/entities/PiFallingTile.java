package me.bjtmastermind.mcpi_parser.entities;

import me.bjtmastermind.mcpi_parser.enums.BlockType;
import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.FloatTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class PiFallingTile extends PiEntity {
    private byte tile;
    private byte data;
    private byte time;

    public PiFallingTile(float x, float y, float z) {
        this(x, y, z, BlockType.SAND);
    }

    public PiFallingTile(float x, float y, float z, BlockType tile) {
        this(x, y, z, tile, (byte) 0);
    }

    public PiFallingTile(float x, float y, float z, BlockType tile, byte dataValue) {
        this.id = EntityType.FALLING_TILE.getID();
        this.pos = new float[] {x, y, z};
        this.motion = new float[3];
        this.rotation = new float[2];
        this.fallDistance = 0f;
        this.fire = -1;
        this.air = 300;
        this.onGround = false;
        this.tile = (byte) tile.getID();
        this.data = dataValue;
        this.time = (byte) 1;
    }

    public byte getTile() {
        return this.tile;
    }

    public void setTile(BlockType tile) {
        this.tile = (byte) tile.getID();
    }

    public byte getData() {
        return this.data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public byte getTime() {
        return this.time;
    }

    public void setTime(byte time) {
        this.time = time;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag entity = new CompoundTag();

        entity.put("id", this.id);

        ListTag<FloatTag> posTag = new ListTag<>();
        posTag.add(this.pos[0]);
        posTag.add(this.pos[1]);
        posTag.add(this.pos[2]);
        entity.put("Pos", posTag);

        ListTag<FloatTag> motionTag = new ListTag<>();
        motionTag.add(this.motion[0]);
        motionTag.add(this.motion[1]);
        motionTag.add(this.motion[2]);
        entity.put("Motion", motionTag);

        ListTag<FloatTag> rotationTag = new ListTag<>();
        rotationTag.add(this.rotation[0]);
        rotationTag.add(this.rotation[1]);
        entity.put("Rotation", rotationTag);

        entity.put("FallDistance", this.fallDistance);
        entity.put("Fire", this.fire);
        entity.put("Air", this.air);
        entity.put("OnGround", (byte) (this.onGround ? 1 : 0));
        entity.put("Tile", this.tile);
        entity.put("Data", this.data);
        entity.put("Time", this.time);

        return entity;
    }
}
