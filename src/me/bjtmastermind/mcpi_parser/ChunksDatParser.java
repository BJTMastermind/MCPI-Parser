package me.bjtmastermind.mcpi_parser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

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
                byte[][][] blocks = new byte[16][16][128];
                for (int x = 0, j = bytesCounted + 4; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y < 128; y++) {
                            blocks[x][z][y] = chunkData[j];
                            j++;
                            bytesCounted++;
                        }
                    }
                }
                chunk.setBlocks(blocks);

                byte[][][] data = new byte[16][16][128 / 2];
                for (int x = 0, j = bytesCounted + 4; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y < 128 / 2; y++) {
                            data[x][z][y] = chunkData[j];
                            j++;
                            bytesCounted++;
                        }
                    }
                }
                chunk.setData(data);

                byte[][][] skylight = new byte[16][16][128 / 2];
                for (int x = 0, j = bytesCounted + 4; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y < 128 / 2; y++) {
                            skylight[x][z][y] = chunkData[j];
                            j++;
                            bytesCounted++;
                        }
                    }
                }
                chunk.setSkylight(skylight);

                byte[][][] blocklight = new byte[16][16][128 / 2];
                for (int x = 0, j = bytesCounted + 4; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y < 128 / 2; y++) {
                            blocklight[x][z][y] = chunkData[j];
                            j++;
                            bytesCounted++;
                        }
                    }
                }
                chunk.setBlockLight(blocklight);

                byte[][] biomes = new byte[16][16];
                for (int x = 0, j = bytesCounted + 4; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        biomes[x][z] = chunkData[j];
                        j++;
                        bytesCounted++;
                    }
                }
                chunk.setBiomes(biomes);

                chunks.put(String.format("%d,%d", chunkX, chunkZ), chunk);
            }

            if (chunkX % 32 == 0) {
                chunkX = 0;
                chunkZ++;
            }
            chunkX++;
            parsedChunks++;
        }
        return chunks;
    }

    // TODO: Implement this.
    // public void assemble() {

    // }
}
