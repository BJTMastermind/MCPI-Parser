package me.bjtmastermind.mcpi_parser.tile_entities;

import me.bjtmastermind.mcpi_parser.enums.BlockItemType;
import me.bjtmastermind.nbt.tag.CompoundTag;

public class PiContainerItem {
    private short id;
    private short damageValue;
    private byte count;
    private byte slot;

    public PiContainerItem(BlockItemType id, byte slot) {
        this(id, (byte) 1, slot);
    }

    public PiContainerItem(BlockItemType id, byte count, byte slot) {
        this(id, (short) 0, count, slot);
    }

    public PiContainerItem(BlockItemType id, short damage, byte count, byte slot) {
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

    @Override
    public String toString() {
        return String.format("PiContainerItem(id=%d,Damage=%d,Count=%d,Slot=%d)", this.id, this.damageValue, this.count, this.slot);
    }
}
