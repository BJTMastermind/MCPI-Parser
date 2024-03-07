package me.bjtmastermind.mcpi_parser.tile_entities;

import java.util.ArrayList;

import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class Chest extends TileEntity {
    private ArrayList<ContainerItem> items;

    public Chest(int x, int y, int z) {
        this.id = TileEntityType.CHEST.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.items = new ArrayList<>();
    }

    public Chest(int x, int y, int z, ArrayList<ContainerItem> items) throws Exception {
        this.id = TileEntityType.CHEST.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.setItems(items);
    }

    public ArrayList<ContainerItem> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<ContainerItem> items) throws Exception {
        if (items.size() > 27) {
            throw new Exception("Can only have a maximum of 27 items, "+items.size()+" given.");
        }
        this.items = items;
    }

    public void addItem(ContainerItem item) throws Exception {
        if (items.size() == 27) {
            throw new Exception("Failed to add item, can only have a maximum of 3 items.");
        }
        this.items.add(item);
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

        return tileEntity;
    }

    public static Chest fromCompoundTag(CompoundTag nbtTileEntity) {
        Chest tileEntity = new Chest(nbtTileEntity.getInt("x"), nbtTileEntity.getInt("y"), nbtTileEntity.getInt("z"));

        for (CompoundTag nbtItem : nbtTileEntity.getListTag("Items").asCompoundTagList()) {
            tileEntity.items.add(ContainerItem.fromCompoundTag(nbtItem));
        }

        return tileEntity;
    }
}
