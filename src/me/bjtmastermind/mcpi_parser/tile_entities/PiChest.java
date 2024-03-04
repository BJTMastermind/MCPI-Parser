package me.bjtmastermind.mcpi_parser.tile_entities;

import java.util.ArrayList;

import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class PiChest extends PiTileEntity {
    private ArrayList<PiContainerItem> items;

    public PiChest(int x, int y, int z) {
        this.id = TileEntityType.CHEST.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.items = new ArrayList<>();
    }

    public PiChest(int x, int y, int z, ArrayList<PiContainerItem> items) throws Exception {
        this.id = TileEntityType.CHEST.getID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.setItems(items);
    }

    public ArrayList<PiContainerItem> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<PiContainerItem> items) throws Exception {
        if (items.size() > 27) {
            throw new Exception("Can only have a maximum of 27 items, "+items.size()+" given.");
        }
        this.items = items;
    }

    public void addItem(PiContainerItem item) throws Exception {
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
        for (PiContainerItem item : this.items) {
            items.add(item.toCompoundTag());
        }
        tileEntity.put("Items", items);

        return tileEntity;
    }

    public static PiChest fromCompoundTag(CompoundTag nbtTileEntity) {
        PiChest tileEntity = new PiChest(nbtTileEntity.getInt("x"), nbtTileEntity.getInt("y"), nbtTileEntity.getInt("z"));

        for (CompoundTag nbtItem : nbtTileEntity.getListTag("Items").asCompoundTagList()) {
            tileEntity.items.add(PiContainerItem.fromCompoundTag(nbtItem));
        }

        return tileEntity;
    }
}
