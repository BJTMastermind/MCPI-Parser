package me.bjtmastermind.mcpi_parser.utils;

import java.util.ArrayList;

import me.bjtmastermind.mcpi_parser.entities.Entity;
import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.mcpi_parser.tile_entities.TileEntity;

public class LocateHelper {

    public static Entity locateEntity(ArrayList<Entity> entities, EntityType type, float x, float y, float z) {
        for (Entity entity : entities) {
            float[] pos = entity.getPos();
            if (Math.floor(pos[0]) == Math.floor(x) && Math.floor(pos[1]) == Math.floor(y) && Math.floor(pos[2]) == Math.floor(z)) {
                if (EntityType.fromID(entity.getID()) == type) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static TileEntity locateTileEntity(ArrayList<TileEntity> tileEntities, TileEntityType type, int x, int y, int z) {
        for (TileEntity tileEntity : tileEntities) {
            if (tileEntity.getX() == x && tileEntity.getY() == y && tileEntity.getZ() == z) {
                if (TileEntityType.fromID(tileEntity.getID()) == type) {
                    return tileEntity;
                }
            }
        }
        return null;
    }
}
