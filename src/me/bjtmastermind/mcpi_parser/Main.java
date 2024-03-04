package me.bjtmastermind.mcpi_parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import me.bjtmastermind.mcpi_parser.entities.PiEntity;
import me.bjtmastermind.mcpi_parser.entities.PiSheep;
import me.bjtmastermind.mcpi_parser.enums.DyeColor;
import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.mcpi_parser.tile_entities.PiSign;
import me.bjtmastermind.mcpi_parser.utils.CoordConverter;
import me.bjtmastermind.mcpi_parser.utils.LocateHelper;

public class Main {

    public static void main(String[] args) throws IOException {
        // ChunksDatParser chunkParser = new ChunksDatParser();
        // HashMap<String, PiChunk> chunks = chunkParser.parse("/home/bjtmastermind/.var/app/com.thebrokenrail.MCPIReborn/.minecraft-pi/games/com.mojang/minecraftWorlds/world/chunks.dat");

        // System.out.println(chunks+"\n"+chunks.size());
        // chunkParser.assemble("./chunks.dat", chunks);

        EntitiesDatParser entitiesParser = new EntitiesDatParser();
        entitiesParser.parse("/home/bjtmastermind/.var/app/com.thebrokenrail.MCPIReborn/.minecraft-pi/games/com.mojang/minecraftWorlds/world/entities.dat");

        float[] real = CoordConverter.asRealCoords(-64f, 8, -64f);
        PiSheep hackedInEntity = new PiSheep(real[0], real[1], real[2], DyeColor.PINK);

        PiSign sign = (PiSign) LocateHelper.locateTileEntity(entitiesParser.getTileEntities(), TileEntityType.SIGN, 21, 66, 29);

        sign.setText2("This sign was");
        sign.setText3("modded in!");

        ArrayList<PiEntity> entities = entitiesParser.getEntities();
        entities.add(hackedInEntity);

        entitiesParser.assemble("/home/bjtmastermind/.var/app/com.thebrokenrail.MCPIReborn/.minecraft-pi/games/com.mojang/minecraftWorlds/world/entities.dat", entitiesParser.getEntities(), entitiesParser.getTileEntities());
    }
}
