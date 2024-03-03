package me.bjtmastermind.mcpi_parser.enums;

public enum EntityType {
    CHICKEN(10),
    COW(11),
    PIG(12),
    SHEEP(13),
    ZOMBIE(32),
    CREEPER(33),
    SKELETON(34),
    SPIDER(35),
    PIG_ZOMBIE(36),
    ITEM_ENTITY(64),
    PRIMED_TNT(65),
    FALLING_TILE(66),
    ARROW(80),
    SNOWBALL(81),
    THROWN_EGG(82),
    PAINTING(83);

    private int value;

    private EntityType(int value) {
        this.value = value;
    }

    public int getID() {
        return this.value;
    }

    public static EntityType fromID(int id) {
        for (EntityType entity : EntityType.values()) {
            if (entity.getID() == id) {
                return entity;
            }
        }
        return null;
    }
}
