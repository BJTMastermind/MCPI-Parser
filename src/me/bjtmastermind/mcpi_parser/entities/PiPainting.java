package me.bjtmastermind.mcpi_parser.entities;

import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.mcpi_parser.enums.PaintingType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.FloatTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class PiPainting extends PiEntity {
    private byte dir;
    private byte direction;
    private String motive;
    private int tileX;
    private int tileY;
    private int tileZ;

    public PiPainting(float x, float y, float z) {
        this(x, y, z, PaintingType.KEBAB);
    }

    public PiPainting(float x, float y, float z, PaintingType motive) {
        this.id = EntityType.PAINTING.getID();
        this.pos = new float[] {x, y, z};
        this.motion = new float[3];
        this.rotation = new float[2];
        this.fallDistance = 0f;
        this.fire = -1;
        this.air = 300;
        this.onGround = false;
        this.dir = 0;
        this.direction = 0;
        this.motive = motive.getMotiveID();
        this.tileX = 0;
        this.tileY = 0;
        this.tileZ = 0;
    }

    public byte getDir() {
        return this.dir;
    }

    public void setDir(byte dir) {
        this.dir = dir;
    }

    public byte getDirection() {
        return this.direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    public String getMotive() {
        return this.motive;
    }

    public void setMotive(PaintingType motive) {
        this.motive = motive.getMotiveID();
    }

    public int getTileX() {
        return this.tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return this.tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public int getTileZ() {
        return this.tileZ;
    }

    public void setTileZ(int tileZ) {
        this.tileZ = tileZ;
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
        entity.put("Dir", this.dir);
        entity.put("Direction", this.direction);
        entity.put("Motive", this.motive);
        entity.put("TileX", this.tileX);
        entity.put("TileY", this.tileY);
        entity.put("TileZ", this.tileZ);

        return entity;
    }
}