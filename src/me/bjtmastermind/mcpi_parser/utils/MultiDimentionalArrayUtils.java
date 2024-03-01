package me.bjtmastermind.mcpi_parser.utils;

import java.io.DataOutputStream;
import java.io.IOException;

public class MultiDimentionalArrayUtils {

    public static void write(DataOutputStream src, byte[][] array2d) throws IOException {
        for (int x = 0; x < array2d.length; x++) {
            for (int z = 0; z < array2d[x].length; z++) {
                src.write(array2d[x][z]);
            }
        }
    }

    public static void write(DataOutputStream src, byte[][][] array3d) throws IOException {
        for (int x = 0; x < array3d.length; x++) {
            for (int z = 0; z < array3d[x].length; z++) {
                for (int y = 0; y < array3d[x][z].length; y++) {
                    src.write(array3d[x][z][y]);
                }
            }
        }
    }
}
