package me.bjtmastermind.mcpi_parser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import me.bjtmastermind.mcpi_parser.utils.ArrayUtils;
import me.bjtmastermind.mcpi_parser.utils.LittleEndianUtils;

public class ChunksDatParser {

    public HashMap<String, PiChunk> parse(String filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

        HashMap<String, PiChunk> chunks = new HashMap<>();

        int chunkX = 0;
        int chunkZ = 0;
        int parsedChunks = 0;
        while (parsedChunks < 1024) {
            int header = buffer.getInt();

            if ((header & 0xFF) != 0) {
                int chunkLocation = (header >> 8) * 4096;
                int chunkDataSize = (header & 0xFF) * 4096;

                byte[] chunkData = new byte[chunkDataSize];
                buffer.get(chunkLocation, chunkData, 0, chunkDataSize);

                PiChunk chunk = new PiChunk(chunkX, chunkZ);

                int bytesCounted = 0;

                byte[][][] blocks = ArrayUtils.read3D(chunkData, bytesCounted + 4, 16, 16, 128);
                bytesCounted += (16*16*128);
                chunk.setBlocks(blocks);

                byte[][][] data = ArrayUtils.read3D(chunkData, bytesCounted + 4, 16, 16, 128 / 2);
                bytesCounted += (16*16*(128 / 2));
                chunk.setData(data);

                byte[][][] skyLight = ArrayUtils.read3D(chunkData, bytesCounted + 4, 16, 16, 128 / 2);
                bytesCounted += (16*16*(128 / 2));
                chunk.setSkyLight(skyLight);

                byte[][][] blockLight = ArrayUtils.read3D(chunkData, bytesCounted + 4, 16, 16, 128 / 2);
                bytesCounted += (16*16*(128 / 2));
                chunk.setBlockLight(blockLight);

                byte[][] biomes = ArrayUtils.read2D(chunkData, bytesCounted + 4, 16, 16);
                chunk.setBiomes(biomes);

                chunks.put(String.format("%d,%d", chunkX, chunkZ), chunk);
            }

            chunkX++;
            parsedChunks++;
            if (chunkX % 32 == 0) {
                chunkX = 0;
                chunkZ++;
            }
        }
        return chunks;
    }

    public void assemble(String filepath, HashMap<String, PiChunk> chunks) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream rawOutput = new DataOutputStream(baos);

        try {
            // First pass: Insert chunk indexes
            for (int z = 0, sectorsFromStart = 1; z < 32; z++) {
                for (int x = 0; x < 32; x++) {
                    PiChunk chunk = chunks.get(String.format("%d,%d", x, z));

                    if (chunk == null) {
                        rawOutput.write(LittleEndianUtils.intAsLEByteArray(0));
                        continue;
                    }

                    rawOutput.write(21);
                    rawOutput.write(LittleEndianUtils.int24AsLEByteArray(sectorsFromStart));
                    sectorsFromStart += 21;
                }
            }

            // Second pass: Insert chunk data
            for (int z = 0; z < 32; z++) {
                for (int x = 0; x < 32; x++) {
                    PiChunk chunk = chunks.get(String.format("%d,%d", x, z));

                    if (chunk == null) {
                        continue;
                    }

                    rawOutput.write(LittleEndianUtils.intAsLEByteArray(82180)); // Should always be the same if chunk exists.
                    ArrayUtils.write3D(rawOutput, chunk.getBlocks());
                    ArrayUtils.write3D(rawOutput, chunk.getData());
                    ArrayUtils.write3D(rawOutput, chunk.getSkyLight());
                    ArrayUtils.write3D(rawOutput, chunk.getBlockLight());
                    ArrayUtils.write2D(rawOutput, chunk.getBiomes());
                    rawOutput.write(new byte[3836]); // extra bytes padding
                }
            }

            File output = new File(filepath);
            Files.write(output.toPath(), baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
