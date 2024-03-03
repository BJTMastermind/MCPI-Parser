package me.bjtmastermind.mcpi_parser.enums;

public enum TileEntityType {
    FURNACE("Furnace"),
    CHEST("Chest"),
    SIGN("Sign"),
    NETHER_REACTOR_CORE("NetherReactor");

    private String value;

    private TileEntityType(String value) {
        this.value = value;
    }

    public String getID() {
        return this.value;
    }
}
