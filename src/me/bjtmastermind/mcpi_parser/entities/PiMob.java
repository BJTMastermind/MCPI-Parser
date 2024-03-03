package me.bjtmastermind.mcpi_parser.entities;

public class PiMob extends PiEntity {
    short attackTime;
    short deathTime;
    short health;
    short hurtTime;

    PiMob() {}

    public short getAttackTime() {
        return this.attackTime;
    }

    public void setAttackTime(short attackTime) {
        this.attackTime = attackTime;
    }

    public short getDeathTime() {
        return this.deathTime;
    }

    public void setDeathTime(short deathTime) {
        this.deathTime = deathTime;
    }

    public short getHealth() {
        return this.health;
    }

    public void setHealth(short health) {
        this.health = health;
    }

    public short getHurtTime() {
        return this.hurtTime;
    }

    public void setHurtTime(short hurtTime) {
        this.hurtTime = hurtTime;
    }
}
