package me.bjtmastermind.mcpi_parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

import me.bjtmastermind.mcpi_parser.utils.NumberToLEArray;
import me.bjtmastermind.nbt.io.NBTUtil;
import me.bjtmastermind.nbt.io.NamedTag;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class EntitiesDatParser {
    private int magic;
    private int version;
    private int byteCount;

    private ListTag<CompoundTag> entities;
    private ListTag<CompoundTag> tileEntities;

    public void parse(String filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

        this.magic = buffer.getInt();
        if (this.magic != 5525061) {
            System.err.println("Failed to parse file, Unsupported magic found. Expected 5525061 found "+this.magic);
            return;
        }

        this.version = buffer.getInt();
        if (this.version != 1) {
            System.err.println("Failed to parse file, Unsupported version found. Expected 1 found "+this.version);
            return;
        }

        this.byteCount = buffer.getInt();

        byte[] nbtBytes = new byte[this.byteCount];
        buffer.get(nbtBytes, 0, this.byteCount);

        NamedTag nbt = NBTUtil.readLE(new ByteArrayInputStream(nbtBytes), false);
        CompoundTag root = (CompoundTag) nbt.getTag();

        this.entities = root.getListTag("Entities").asCompoundTagList();
        this.tileEntities = root.getListTag("TileEntities").asCompoundTagList();
    }

    public ListTag<CompoundTag> getEntities() {
        return this.entities;
    }

    public ListTag<CompoundTag> getTileEntities() {
        return this.tileEntities;
    }

    public void assemble(String filepath, ListTag<CompoundTag> entities, ListTag<CompoundTag> tileEntities) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream rawOutput = new DataOutputStream(baos);

        try {
            CompoundTag data = new CompoundTag();
            data.put("Entities", entities);
            data.put("TileEntities", tileEntities);

            byte[] nbtBytes = NBTUtil.writeLE(data, false).toByteArray();

            rawOutput.write(NumberToLEArray.toLEInt(5525061));
            rawOutput.write(NumberToLEArray.toLEInt(1));
            rawOutput.write(NumberToLEArray.toLEInt(nbtBytes.length));
            rawOutput.write(nbtBytes);

            File output = new File(filepath);
            Files.write(output.toPath(), baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
