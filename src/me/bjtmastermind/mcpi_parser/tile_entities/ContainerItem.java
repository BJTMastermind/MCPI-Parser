package me.bjtmastermind.mcpi_parser.tile_entities;

import me.bjtmastermind.mcpi_parser.enums.BlockItemType;
import me.bjtmastermind.mcpi_parser.enums.BlockType;
import me.bjtmastermind.mcpi_parser.enums.ItemType;
import me.bjtmastermind.nbt.tag.CompoundTag;

public class ContainerItem {
    private short id;
    private short damageValue;
    private byte count;
    private byte slot;

    public ContainerItem(BlockItemType id, byte slot) {
        this(id, (byte) 1, slot);
    }

    public ContainerItem(BlockItemType id, byte count, byte slot) {
        this(id, (short) 0, count, slot);
    }

    public ContainerItem(BlockItemType id, short damage, byte count, byte slot) {
        this.id = (short) id.getID();
        this.damageValue = damage;
        this.count = count;
        this.slot = slot;
    }

    public short getID() {
        return this.id;
    }

    public void setID(BlockItemType id) {
        this.id = (short) id.getID();
    }

    public short getDamageValue() {
        return this.damageValue;
    }

    public void setDamageValue(short damage) {
        this.damageValue = damage;
    }

    public byte getCount() {
        return this.count;
    }

    public void setCount(byte count) {
        this.count = count;
    }

    public byte getSlot() {
        return this.slot;
    }

    public void setSlot(byte slot) {
        this.slot = slot;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag item = new CompoundTag();

        item.put("id", this.id);
        item.put("Damage", this.damageValue);
        item.put("Count", this.count);
        item.put("Slot", this.slot);

        return item;
    }

    public static ContainerItem fromCompoundTag(CompoundTag nbtItem) {
        if (BlockType.fromID(nbtItem.getShort("id")) != null) {
            return new ContainerItem(
                BlockType.fromID(nbtItem.getShort("id")),
                nbtItem.getShort("Damage"),
                nbtItem.getByte("Count"),
                nbtItem.getByte("Slot")
            );
        }
        return new ContainerItem(
            ItemType.fromID(nbtItem.getShort("id")),
            nbtItem.getShort("Damage"),
            nbtItem.getByte("Count"),
            nbtItem.getByte("Slot")
        );
    }

    @Override
    public String toString() {
        return String.format("PiContainerItem(id=%d,Damage=%d,Count=%d,Slot=%d)", this.id, this.damageValue, this.count, this.slot);
    }
}
