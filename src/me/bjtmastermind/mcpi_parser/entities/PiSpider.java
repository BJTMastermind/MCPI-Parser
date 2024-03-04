package me.bjtmastermind.mcpi_parser.entities;

import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.FloatTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class PiSpider extends PiMob {

    public PiSpider(float x, float y, float z) {
        this.id = EntityType.SPIDER.getID();
        this.pos = new float[] {x, y, z};
        this.motion = new float[3];
        this.rotation = new float[2];
        this.fallDistance = 0f;
        this.fire = -1;
        this.air = 300;
        this.onGround = false;
        this.attackTime = 0;
        this.deathTime = 0;
        this.health = 16;
        this.hurtTime = 0;
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
        entity.put("AttackTime", this.attackTime);
        entity.put("DeathTime", this.deathTime);
        entity.put("Health", this.health);
        entity.put("HurtTime", this.hurtTime);

        return entity;
    }

    public static PiSpider fromCompoundTag(CompoundTag entityTag) {
        ListTag<FloatTag> pos = entityTag.getListTag("Pos").asFloatTagList();
        ListTag<FloatTag> motion = entityTag.getListTag("Motion").asFloatTagList();
        ListTag<FloatTag> rotation = entityTag.getListTag("Rotation").asFloatTagList();

        PiSpider outEntity = new PiSpider(pos.get(0).asFloat(), pos.get(1).asFloat(), pos.get(2).asFloat());
        outEntity.motion = new float[] {motion.get(0).asFloat(), motion.get(1).asFloat(), motion.get(2).asFloat()};
        outEntity.rotation = new float[] {rotation.get(0).asFloat(), rotation.get(1).asFloat()};
        outEntity.fallDistance = entityTag.getFloat("FallDistance");
        outEntity.fire = entityTag.getShort("Fire");
        outEntity.air = entityTag.getShort("Air");
        outEntity.onGround = entityTag.getByte("OnGround") == 1 ? true : false;
        outEntity.attackTime = entityTag.getShort("AttackTime");
        outEntity.deathTime = entityTag.getShort("DeathTime");
        outEntity.health = entityTag.getShort("Health");
        outEntity.hurtTime = entityTag.getShort("HurtTime");

        return outEntity;
    }
}
