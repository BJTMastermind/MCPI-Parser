package me.bjtmastermind.mcpi_parser.tile_entities;

import java.util.ArrayList;

import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class Furnace extends TileEntity {
    private ArrayList<ContainerItem> items;
    private short burnTime;
    private short cookTime;

    public Furnace(int x, int y, int z) {
        this.id = TileEntityType.FURNACE.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.items = new ArrayList<>();
        this.burnTime = 0;
        this.cookTime = 0;
    }

    public Furnace(int x, int y, int z, ArrayList<ContainerItem> items) throws Exception {
        this.id = TileEntityType.FURNACE.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.setItems(items);
        this.burnTime = 0;
        this.cookTime = 0;
    }

    public ArrayList<ContainerItem> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<ContainerItem> items) throws Exception {
        if (items.size() > 3) {
            throw new Exception("Can only have a maximum of 3 items, "+items.size()+" given.");
        }
        this.items = items;
    }

    public void addItem(ContainerItem item) throws Exception {
        if (items.size() == 3) {
            throw new Exception("Failed to add item, can only have a maximum of 3 items.");
        }
        this.items.add(item);
    }

    public short getBurnTime() {
        return this.burnTime;
    }

    public void setBurnTime(short burnTime) {
        this.burnTime = burnTime;
    }

    public short getCookTime() {
        return this.cookTime;
    }

    public void setCookTime(short cookTime) {
        this.cookTime = cookTime;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tileEntity = new CompoundTag();

        tileEntity.put("id", this.id);
        tileEntity.put("x", this.x);
        tileEntity.put("y", this.y);
        tileEntity.put("z", this.z);

        ListTag<CompoundTag> items = new ListTag<>();
        for (ContainerItem item : this.items) {
            items.add(item.toCompoundTag());
        }
        tileEntity.put("Items", items);

        tileEntity.put("BurnTime", this.burnTime);
        tileEntity.put("CookTime", this.cookTime);

        return tileEntity;
    }

    public static Furnace fromCompoundTag(CompoundTag nbtTileEntity) {
        Furnace tileEntity = new Furnace(nbtTileEntity.getInt("x"), nbtTileEntity.getInt("y"), nbtTileEntity.getInt("z"));

        for (CompoundTag nbtItem : nbtTileEntity.getListTag("Items").asCompoundTagList()) {
            tileEntity.items.add(ContainerItem.fromCompoundTag(nbtItem));
        }

        tileEntity.burnTime = nbtTileEntity.getShort("BurnTime");
        tileEntity.cookTime = nbtTileEntity.getShort("CookTime");

        return tileEntity;
    }
}
