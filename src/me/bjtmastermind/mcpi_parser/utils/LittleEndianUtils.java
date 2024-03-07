package me.bjtmastermind.mcpi_parser.utils;

public class LittleEndianUtils {

    public static byte[] int24AsLEByteArray(int value) {
        byte[] leOutput = new byte[3];
        leOutput[0] = (byte) ((value >> 0) & 0xFF);
        leOutput[1] = (byte) ((value >> 8) & 0xFF);
        leOutput[2] = (byte) ((value >> 16) & 0xFF);
        return leOutput;
    }

    public static byte[] intAsLEByteArray(int value) {
        byte[] leOutput = new byte[4];
        leOutput[0] = (byte) ((value >> 0) & 0xFF);
        leOutput[1] = (byte) ((value >> 8) & 0xFF);
        leOutput[2] = (byte) ((value >> 16) & 0xFF);
        leOutput[3] = (byte) ((value >> 24) & 0xFF);
        return leOutput;
    }
}
