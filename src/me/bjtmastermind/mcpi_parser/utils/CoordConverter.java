package me.bjtmastermind.mcpi_parser.utils;

public class CoordConverter {

    public static int[] asPiCoords(int realX, int realY, int realZ) {
        return new int[] {realX - 128, realY - 64, realZ - 128};
    }

    public static int[] asRealCoords(int piX, int piY, int piZ) {
        return new int[] {piX + 128, piY + 64, piZ + 128};
    }

    public static float[] asPiCoords(float realX, float realY, float realZ) {
        return new float[] {realX - 128.0f, realY - 64.0f, realZ - 128.0f};
    }

    public static float[] asRealCoords(float piX, float piY, float piZ) {
        return new float[] {piX + 128.0f, piY + 64.0f, piZ + 128.0f};
    }
}
