package me.bjtmastermind.mcpi_parser.utils;

import java.io.DataOutputStream;
import java.io.IOException;

public class ArrayUtils {

    public static byte[][] read2D(byte[] src, int offset, int sizeX, int sizeZ) throws IOException {
        byte[][] array2d = new byte[sizeX][sizeZ];
        for (int x = 0; x < array2d.length; x++) {
            for (int z = 0; z < array2d[x].length; z++) {
                array2d[x][z] = src[offset++];
            }
        }
        return array2d;
    }

    public static void write2D(DataOutputStream src, byte[][] array2d) throws IOException {
        for (int x = 0; x < array2d.length; x++) {
            for (int z = 0; z < array2d[x].length; z++) {
                src.write(array2d[x][z]);
            }
        }
    }

    public static byte[][][] read3D(byte[] src, int offset, int sizeX, int sizeY, int sizeZ) throws IOException {
        byte[][][] array3d = new byte[sizeX][sizeZ][sizeY];
        for (int x = 0; x < array3d.length; x++) {
            for (int z = 0; z < array3d[x].length; z++) {
                for (int y = 0; y < array3d[x][z].length; y++) {
                    array3d[x][z][y] = src[offset++];
                }
            }
        }
        return array3d;
    }

    public static void write3D(DataOutputStream src, byte[][][] array3d) throws IOException {
        for (int x = 0; x < array3d.length; x++) {
            for (int z = 0; z < array3d[x].length; z++) {
                for (int y = 0; y < array3d[x][z].length; y++) {
                    src.write(array3d[x][z][y]);
                }
            }
        }
    }
}
