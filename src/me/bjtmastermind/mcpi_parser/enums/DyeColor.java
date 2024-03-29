package me.bjtmastermind.mcpi_parser.enums;

public enum DyeColor {
    WHITE(0),
    ORANGE(1),
    MAGENTA(2),
    LIGHT_BLUE(3),
    YELLOW(4),
    LIME(5),
    PINK(6),
    GRAY(7),
    LIGHT_GRAY(8),
    CYAN(9),
    PURPLE(10),
    BLUE(11),
    BROWN(12),
    GREEN(13),
    RED(14),
    BLACK(15);

    private int value;

    private DyeColor(int value) {
        this.value = value;
    }

    public int getDataValue() {
        return this.value;
    }

    public static DyeColor fromDataValue(int dataValue) {
        for (DyeColor color : DyeColor.values()) {
            if (color.getDataValue() == dataValue) {
                return color;
            }
        }
        return null;
    }
}
