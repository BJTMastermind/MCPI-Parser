package me.bjtmastermind.mcpi_parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.FloatTag;
import me.bjtmastermind.nbt.tag.IntTag;
import me.bjtmastermind.nbt.tag.ListTag;
import me.bjtmastermind.nbt.tag.ShortTag;

public class Main {

    public static void main(String[] args) throws IOException {
        ChunksDatParser chunkParser = new ChunksDatParser();
        HashMap<String, PiChunk> chunks = chunkParser.parse("/home/bjtmastermind/.var/app/com.thebrokenrail.MCPIReborn/.minecraft-pi/games/com.mojang/minecraftWorlds/world/chunks.dat");

        // System.out.println(chunks+"\n"+chunks.size());
        chunkParser.assemble("./chunks.dat", chunks);

        /*EntitiesDatParser entitiesParser = new EntitiesDatParser();
        entitiesParser.parse("/home/bjtmastermind/.var/app/com.thebrokenrail.MCPIReborn/.minecraft-pi/games/com.mojang/minecraftWorlds/world/entities_old.dat");

        CompoundTag hackedInEntity = new CompoundTag();
        hackedInEntity.put("id", new IntTag(13));
        hackedInEntity.put("Health", new ShortTag((short) 8));
        ListTag<FloatTag> pos = new ListTag<>();
        pos.add(64f);
        pos.add(72f);
        pos.add(64f);
        hackedInEntity.put("Pos", pos);

        ListTag<CompoundTag> entities = entitiesParser.getEntities();
        entities.add(hackedInEntity);

        entitiesParser.assemble("/home/bjtmastermind/.var/app/com.thebrokenrail.MCPIReborn/.minecraft-pi/games/com.mojang/minecraftWorlds/world/entities.dat", entities, entitiesParser.getTileEntities());
        */
    }
}
