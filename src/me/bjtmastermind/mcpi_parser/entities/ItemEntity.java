package me.bjtmastermind.mcpi_parser.entities;

import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.mcpi_parser.enums.BlockItemType;
import me.bjtmastermind.mcpi_parser.enums.BlockType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.FloatTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class ItemEntity extends Entity {
    private short health;
    private short age;
    private short itemId;
    private short itemDamageValue;
    private byte itemCount;

    public ItemEntity(float x, float y, float z) {
        this(x, y, z, BlockType.STONE, (byte) 1);
    }

    public ItemEntity(float x, float y, float z, BlockItemType item) {
        this(x, y, z, item, (byte) 1);
    }

    public ItemEntity(float x, float y, float z, BlockItemType item, byte count) {
        this(x, y, z, item, (short) 0, count);
    }

    public ItemEntity(float x, float y, float z, BlockItemType item, short damageValue, byte count) {
        this.id = EntityType.ITEM_ENTITY.getID();
        this.pos = new float[] {x, y, z};
        this.motion = new float[3];
        this.rotation = new float[2];
        this.fallDistance = 0f;
        this.fire = -1;
        this.air = 300;
        this.onGround = false;
        this.health = 5;
        this.age = 0;
        this.itemId = (short) item.getID();
        this.itemDamageValue = damageValue;
        this.itemCount = count;
    }

    public short getHealth() {
        return this.health;
    }

    public void setHealth(short health) {
        this.health = health;
    }

    public short getAge() {
        return this.age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public short getItemId() {
        return this.itemId;
    }

    public void setItemId(short itemId) {
        this.itemId = itemId;
    }

    public short getItemDamageValue() {
        return this.itemDamageValue;
    }

    public void setItemDamageValue(short itemDamageValue) {
        this.itemDamageValue = itemDamageValue;
    }

    public byte getItemCount() {
        return this.itemCount;
    }

    public void setItemDamageValue(byte itemCount) {
        this.itemCount = itemCount;
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
        entity.put("Health", this.health);
        entity.put("Age", this.age);

        CompoundTag item = new CompoundTag();
        item.put("id", this.itemId);
        item.put("Damage", this.itemDamageValue);
        item.put("Count", this.itemCount);
        entity.put("Item", item);

        return entity;
    }

    public static ItemEntity fromCompoundTag(CompoundTag entityTag) {
        ListTag<FloatTag> pos = entityTag.getListTag("Pos").asFloatTagList();
        ListTag<FloatTag> motion = entityTag.getListTag("Motion").asFloatTagList();
        ListTag<FloatTag> rotation = entityTag.getListTag("Rotation").asFloatTagList();

        ItemEntity outEntity = new ItemEntity(pos.get(0).asFloat(), pos.get(1).asFloat(), pos.get(2).asFloat());
        outEntity.motion = new float[] {motion.get(0).asFloat(), motion.get(1).asFloat(), motion.get(2).asFloat()};
        outEntity.rotation = new float[] {rotation.get(0).asFloat(), rotation.get(1).asFloat()};
        outEntity.fallDistance = entityTag.getFloat("FallDistance");
        outEntity.fire = entityTag.getShort("Fire");
        outEntity.air = entityTag.getShort("Air");
        outEntity.onGround = entityTag.getByte("OnGround") == 1 ? true : false;
        outEntity.health = entityTag.getShort("Health");
        outEntity.age = entityTag.getShort("Age");

        CompoundTag nbtItemInfo = entityTag.getCompoundTag("Item");
        outEntity.itemId = nbtItemInfo.getShort("id");
        outEntity.itemDamageValue = nbtItemInfo.getShort("Damage");
        outEntity.itemCount = nbtItemInfo.getByte("Count");

        return outEntity;
    }
}
