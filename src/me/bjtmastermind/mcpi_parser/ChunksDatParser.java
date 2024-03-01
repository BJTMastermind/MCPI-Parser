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

import me.bjtmastermind.mcpi_parser.utils.MultiDimentionalArrayUtils;
import me.bjtmastermind.mcpi_parser.utils.NumberToLEArray;

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
                        rawOutput.write(NumberToLEArray.toLEInt(0));
                        continue;
                    }

                    rawOutput.write(21);
                    rawOutput.write(NumberToLEArray.toLEInt24(sectorsFromStart));
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

                    rawOutput.write(NumberToLEArray.toLEInt(82180)); // Should always be the same if chunk exists.
                    MultiDimentionalArrayUtils.write(rawOutput, chunk.getBlocks());
                    MultiDimentionalArrayUtils.write(rawOutput, chunk.getData());
                    MultiDimentionalArrayUtils.write(rawOutput, chunk.getSkylight());
                    MultiDimentionalArrayUtils.write(rawOutput, chunk.getBlockLight());
                    MultiDimentionalArrayUtils.write(rawOutput, chunk.getBiomes());
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
