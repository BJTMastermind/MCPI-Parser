package me.bjtmastermind.mcpi_parser.utils;

public class NumberToLEArray {

    public static byte[] toLEShort(short value) {
        byte[] leOutput = new byte[2];
        leOutput[0] = (byte) ((value >> 0) & 0xFF);
        leOutput[1] = (byte) ((value >> 8) & 0xFF);
        return leOutput;
    }

    public static byte[] toLEChar(char value) {
        byte[] leOutput = new byte[2];
        leOutput[0] = (byte) ((value >> 0) & 0xFF);
        leOutput[1] = (byte) ((value >> 8) & 0xFF);
        return leOutput;
    }

    public static byte[] toLEInt(int value) {
        byte[] leOutput = new byte[4];
        leOutput[0] = (byte) ((value >> 0) & 0xFF);
        leOutput[1] = (byte) ((value >> 8) & 0xFF);
        leOutput[2] = (byte) ((value >> 16) & 0xFF);
        leOutput[3] = (byte) ((value >> 24) & 0xFF);
        return leOutput;
    }

    public static byte[] toLELong(long value) {
        byte[] leOutput = new byte[8];
        leOutput[0] = (byte) ((value >> 0) & 0xFF);
        leOutput[1] = (byte) ((value >> 8) & 0xFF);
        leOutput[2] = (byte) ((value >> 16) & 0xFF);
        leOutput[3] = (byte) ((value >> 24) & 0xFF);
        leOutput[4] = (byte) ((value >> 32) & 0xFF);
        leOutput[5] = (byte) ((value >> 40) & 0xFF);
        leOutput[6] = (byte) ((value >> 48) & 0xFF);
        leOutput[7] = (byte) ((value >> 56) & 0xFF);
        return leOutput;
    }

    public static byte[] toLEFloat(float value) {
        byte[] leOutput = new byte[4];
        leOutput[0] = (byte) ((Float.floatToIntBits(value) >> 0) & 0xFF);
        leOutput[1] = (byte) ((Float.floatToIntBits(value) >> 8) & 0xFF);
        leOutput[2] = (byte) ((Float.floatToIntBits(value) >> 16) & 0xFF);
        leOutput[3] = (byte) ((Float.floatToIntBits(value) >> 24) & 0xFF);
        return leOutput;
    }

    public static byte[] toLEDouble(double value) {
        byte[] leOutput = new byte[8];
        leOutput[0] = (byte) ((Double.doubleToLongBits(value) >> 0) & 0xFF);
        leOutput[1] = (byte) ((Double.doubleToLongBits(value) >> 8) & 0xFF);
        leOutput[2] = (byte) ((Double.doubleToLongBits(value) >> 16) & 0xFF);
        leOutput[3] = (byte) ((Double.doubleToLongBits(value) >> 24) & 0xFF);
        leOutput[4] = (byte) ((Double.doubleToLongBits(value) >> 32) & 0xFF);
        leOutput[5] = (byte) ((Double.doubleToLongBits(value) >> 40) & 0xFF);
        leOutput[6] = (byte) ((Double.doubleToLongBits(value) >> 48) & 0xFF);
        leOutput[7] = (byte) ((Double.doubleToLongBits(value) >> 56) & 0xFF);
        return leOutput;
    }
}
