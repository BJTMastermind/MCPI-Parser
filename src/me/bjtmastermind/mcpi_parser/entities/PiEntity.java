package me.bjtmastermind.mcpi_parser.entities;

public class PiEntity {
    int id;
    float[] pos;
    float[] motion;
    float[] rotation;
    float fallDistance;
    short fire;
    short air;
    boolean onGround;

    PiEntity() {}

    public int getID() {
        return this.id;
    }

    public float[] getPos() {
        return this.pos;
    }

    public void setPos(float[] pos) throws Exception {
        if (pos.length != 3) {
            throw new Exception("Float array is not a length of 3.");
        }
        this.pos = pos;
    }

    public float[] getMotion() {
        return this.motion;
    }

    public void setMotion(float[] motion) throws Exception {
        if (motion.length != 3) {
            throw new Exception("Float array is not a length of 3.");
        }
        this.motion = motion;
    }

    public float[] getRotation() {
        return this.rotation;
    }

    public void setRotation(float[] rotation) throws Exception {
        if (rotation.length != 2) {
            throw new Exception("Float array is not a length of 2.");
        }
        this.rotation = rotation;
    }

    public float getFallDistance() {
        return this.fallDistance;
    }

    public void setFallDistance(float fallDistance) {
        this.fallDistance = fallDistance;
    }

    public short getFire() {
        return this.fire;
    }

    public void setFire(short fire) {
        this.fire = fire;
    }

    public short getAir() {
        return this.air;
    }

    public void setAir(short air) {
        this.air = air;
    }

    public boolean getOnGround() {
        return this.onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+String.format("(x=%.02ff,y=%.02ff,z=%.02ff)", this.pos[0], this.pos[1], this.pos[2]);
    }
}
