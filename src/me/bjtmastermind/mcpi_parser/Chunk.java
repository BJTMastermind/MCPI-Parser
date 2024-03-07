package me.bjtmastermind.mcpi_parser;

public class Chunk {
    private int x;
    private int z;
    private int piXPos;
    private int piZPos;
    private byte[][][] blocks;
    private byte[][][] data;
    private byte[][][] skyLight;
    private byte[][][] blockLight;
    private byte[][] biomes;

    public Chunk(int x, int z) {
        this.x = x;
        this.z = z;
        this.piXPos = (x * 16) - 128;
        this.piZPos = (z * 16) - 128;
        this.blocks = new byte[16][16][128];
        this.data = new byte[16][16][128 / 2];
        this.skyLight = new byte[16][16][128 / 2];
        this.blockLight = new byte[16][16][128 / 2];
        this.biomes = new byte[16][16];
    }

    public int[] getChunkPosition() {
        return new int[] {this.x, this.z};
    }

    public int[] getPiWorldPosition() {
        return new int[] {this.piXPos, this.piZPos};
    }

    public byte[][][] getBlocks() {
        return this.blocks;
    }

    public void setBlocks(byte[][][] blocks) {
        this.blocks = blocks;
    }

    public byte[][][] getData() {
        return this.data;
    }

    public void setData(byte[][][] data) {
        this.data = data;
    }

    public byte[][][] getSkyLight() {
        return this.skyLight;
    }

    public void setSkyLight(byte[][][] skylight) {
        this.skyLight = skylight;
    }

    public byte[][][] getBlockLight() {
        return this.blockLight;
    }

    public void setBlockLight(byte[][][] blocklight) {
        this.blockLight = blocklight;
    }

    public byte[][] getBiomes() {
        return this.biomes;
    }

    public void setBiomes(byte[][] biomes) {
        this.biomes = biomes;
    }

    @Override
    public String toString() {
        return String.format("PiChunk(x=%d,z=%d)", this.x, this.z);
    }
}
