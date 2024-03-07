package me.bjtmastermind.mcpi_parser.enums;

public enum ItemType implements BlockItemType {
    IRON_SHOVEL(256),
    IRON_PICKAXE(257),
    IRON_AXE(258),
    FLINT_STEEL(259),
    APPLE(260),
    BOW(261),
    ARROW(262),
    COAL(263),
    DIAMOND(264),
    IRON_INGOT(265),
    GOLD_INGOT(266),
    IRON_SWORD(267),
    WOODEN_SWORD(268),
    WOODEN_SHOVEL(269),
    WOODEN_PICKAXE(270),
    WOODEN_AXE(271),
    STONE_SWORD(272),
    STONE_SHOVEL(273),
    STONE_PICKAXE(274),
    STONE_AXE(275),
    DIAMOND_SWORD(276),
    DIAMOND_SHOVEL(277),
    DIAMOND_PICKAXE(278),
    DIAMOND_AXE(279),
    STICK(280),
    BOWL(281),
    MUSHROOM_STEW(282),
    GOLD_SWORD(283),
    GOLD_SHOVEL(284),
    GOLD_PICKAXE(285),
    GOLD_AXE(286),
    STRING(287),
    FEATHER(288),
    GUNPOWDER(289),
    WOODEN_HOE(290),
    STONE_HOE(291),
    IRON_HOE(292),
    DIAMOND_HOE(293),
    GOLD_HOE(294),
    SEEDS(295),
    WHEAT(296),
    BREAD(297),
    LEATHER_CAP(298),
    LEATHER_TUNIC(299),
    LEATHER_PANTS(300),
    LEATHER_BOOTS(301),
    CHAIN_HELMET(302),
    CHAIN_CHESTPLATE(303),
    CHAIN_LEGGINGS(304),
    CHAIN_BOOTS(305),
    IRON_HELMET(306),
    IRON_CHESTPLATE(307),
    IRON_LEGGINGS(308),
    IRON_BOOTS(309),
    DIAMOND_HELMET(310),
    DIAMOND_CHESTPLATE(311),
    DIAMOND_LEGGINGS(312),
    DIAMOND_BOOTS(313),
    GOLD_HELMET(314),
    GOLD_CHESTPLATE(315),
    GOLD_LEGGINGS(316),
    GOLD_BOOTS(317),
    FLINT(318),
    RAW_PORKCHOP(319),
    COOKED_PORKCHOP(320),
    PAINTING(321),
    SIGN(323),
    WOODEN_DOOR(324),
    IRON_DOOR(330),
    SNOWBALL(332),
    LEATHER(334),
    CLAY_BRICK(336),
    CLAY(337),
    SUGAR_CANE(338),
    PAPER(339),
    BOOK(340),
    SLIMEBALL(341),
    EGG(344),
    COMPASS(345),
    CLOCK(347),
    GLOWSTONE_DUST(348),
    DYE(351),
    BONE(352),
    SUGAR(353),
    BED(356),
    SHEARS(359),
    MELON(360),
    MELON_SEEDS(362),
    RAW_BEEF(363),
    STEAK(364),
    RAW_CHICKEN(365),
    COOKED_CHICKEN(366),
    NETHER_BRICK(405),
    NETHER_QUARTZ(406),
    CAMERA(456);

    private int value;

    private ItemType(int value) {
        this.value = value;
    }

    public int getID() {
        return this.value;
    }

    public static ItemType fromID(int id) {
        for (ItemType item : ItemType.values()) {
            if (item.getID() == id) {
                return item;
            }
        }
        return null;
    }
}
