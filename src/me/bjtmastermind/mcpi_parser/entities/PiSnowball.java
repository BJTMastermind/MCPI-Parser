package me.bjtmastermind.mcpi_parser.entities;

import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.FloatTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class PiSnowball extends PiEntity {
    private byte inTile;
    private boolean inGround;
    private byte shake;
    private short xTile;
    private short yTile;
    private short zTile;

    public PiSnowball(float x, float y, float z) {
        this.id = EntityType.SNOWBALL.getID();
        this.pos = new float[] {x, y, z};
        this.motion = new float[3];
        this.rotation = new float[2];
        this.fallDistance = 0f;
        this.fire = -1;
        this.air = 300;
        this.onGround = false;
        this.inTile = 0;
        this.inGround = false;
        this.shake = 0;
        this.xTile = 0;
        this.yTile = 0;
        this.zTile = 0;
    }

    public byte getInTile() {
        return this.inTile;
    }

    public void setInTile(byte inTile) {
        this.inTile = inTile;
    }

    public boolean getInGround() {
        return this.inGround;
    }

    public void setInGround(boolean inGround) {
        this.inGround = inGround;
    }

    public byte getShake() {
        return this.shake;
    }

    public void setShake(byte shake) {
        this.shake = shake;
    }

    public short getXTile() {
        return this.xTile;
    }

    public void setXTile(short xTile) {
        this.xTile = xTile;
    }

    public short getYTile() {
        return this.yTile;
    }

    public void setYTile(short yTile) {
        this.yTile = yTile;
    }

    public short getZTile() {
        return this.zTile;
    }

    public void setZTile(short zTile) {
        this.zTile = zTile;
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
        entity.put("inTile", this.inTile);
        entity.put("inGround", this.inGround ? 1 : 0);
        entity.put("shake", this.shake);
        entity.put("xTile", this.xTile);
        entity.put("yTile", this.yTile);
        entity.put("zTile", this.zTile);

        return entity;
    }
}
