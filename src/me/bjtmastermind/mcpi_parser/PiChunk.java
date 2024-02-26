package me.bjtmastermind.mcpi_parser;

public class PiChunk {
    private int x;
    private int z;
    private int piXPos;
    private int piZPos;
    private byte[][][] blocks;
    private byte[][][] data;
    private byte[][][] skylight;
    private byte[][][] blocklight;
    private byte[][] biomes;

    public PiChunk(int x, int z) {
        this.x = x;
        this.z = z;
        this.piXPos = x - 128;
        this.piZPos = z - 128;
        this.blocks = new byte[16][16][128];
        this.data = new byte[16][16][128 / 2];
        this.skylight = new byte[16][16][128 / 2];
        this.blocklight = new byte[16][16][128 / 2];
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

    public byte[][][] getSkylight() {
        return this.skylight;
    }

    public void setSkylight(byte[][][] skylight) {
        this.skylight = skylight;
    }

    public byte[][][] getBlockLight() {
        return this.blocklight;
    }

    public void setBlockLight(byte[][][] blocklight) {
        this.blocklight = blocklight;
    }

    public byte[][] getBiomes() {
        return this.biomes;
    }

    public void setBiomes(byte[][] biomes) {
        this.biomes = biomes;
    }

    @Override
    public String toString() {
        return String.format("PiChunk(x=%d,z=%d)", this.x, this.z, this.piXPos, this.piZPos);
    }
}
