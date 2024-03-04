package me.bjtmastermind.mcpi_parser.utils;

import java.util.ArrayList;

import me.bjtmastermind.mcpi_parser.entities.PiEntity;
import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.mcpi_parser.tile_entities.PiTileEntity;

public class LocateHelper {

    public static PiEntity locateEntity(ArrayList<PiEntity> entities, EntityType type, float x, float y, float z) {
        for (PiEntity entity : entities) {
            float[] pos = entity.getPos();
            if (Math.floor(pos[0]) == Math.floor(x) && Math.floor(pos[1]) == Math.floor(y) && Math.floor(pos[2]) == Math.floor(z)) {
                if (EntityType.fromID(entity.getID()) == type) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static PiTileEntity locateTileEntity(ArrayList<PiTileEntity> tileEntities, TileEntityType type, int x, int y, int z) {
        for (PiTileEntity tileEntity : tileEntities) {
            if (tileEntity.getX() == x && tileEntity.getY() == y && tileEntity.getZ() == z) {
                if (TileEntityType.fromID(tileEntity.getID()) == type) {
                    return tileEntity;
                }
            }
        }
        return null;
    }
}
