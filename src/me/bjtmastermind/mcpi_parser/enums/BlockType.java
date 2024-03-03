package me.bjtmastermind.mcpi_parser.enums;

public enum BlockType implements BlockItemType {
    AIR(0),
    STONE(1),
    GRASS_BLOCK(2),
    DIRT(3),
    COBBLESTONE(4),
    PLANKS(5),
    SAPLING(6),
    BEDROCK(7),
    WATER_FLOWING(8),
    WATER(9),
    LAVA_FLOWING(10),
    LAVA(11),
    SAND(12),
    GRAVEL(13),
    GOLD_ORE(14),
    IRON_ORE(15),
    COAL_ORE(16),
    LOG(17),
    LEAVES(18),
    GLASS(20),
    LAPIS_ORE(21),
    LAPIS_BLOCK(22),
    SANDSTONE(24),
    BED(26),
    COBWEB(30),
    BUSH(31),
    WOOL(35),
    DANDELION(37),
    BLUE_ROSE(38),
    BROWN_MUSHROOM(39),
    RED_MUSHROOM(40),
    GOLD_BLOCK(41),
    IRON_BLOCK(42),
    DOUBLE_SLAB(43),
    SLAB(44),
    BRICKS(45),
    TNT(46),
    BOOKSHELF(47),
    MOSSY_COBBLESTONE(48),
    OBSIDIAN(49),
    TORCH(50),
    FIRE(51),
    WOODEN_STAIRS(53),
    CHEST(54),
    DIAMOND_ORE(56),
    DIAMOND_BLOCK(57),
    CRAFTING_TABLE(58),
    WHEAT(59),
    FARMLAND(60),
    FURNACE(61),
    LIT_FURNACE(62),
    SIGN(63),
    WOODEN_DOOR(64),
    LADDER(65),
    COBBLESTONE_STAIRS(67),
    WALL_SIGN(68),
    IRON_DOOR(71),
    REDSTONE_ORE(73),
    LIT_REDSTONE_ORE(74),
    SNOW(78),
    ICE(79),
    SNOW_BLOCK(80),
    CACTUS(81),
    CLAY_BLOCK(82),
    SUGARCANE(83),
    FENCE(85),
    NETHERRACK(87),
    GLOWSTONE(89),
    BEDROCK_INVISIBLE(95),
    TRAPDOOR(96),
    STONE_BRICKS(98),
    GLASS_PANE(102),
    MELON_BLOCK(103),
    MELON_STEM(105),
    FENCE_GATE(107),
    BRICK_STAIRS(108),
    STONE_BRICK_STAIRS(109),
    NETHER_BRICKS(112),
    NETHER_BRICK_STAIRS(114),
    SANDSTONE_STAIRS(128),
    QUARTZ_BLOCK(155),
    QUARTZ_STAIRS(156),
    STONECUTTER(245),
    GLOWING_OBSIDIAN(246),
    NETHER_REACTOR_CORE(247),
    UPDATE(248),
    ATEUPD(249),
    GRASS_BLOCK_CARRIED(253),
    LEAVES_CARRIED(254),
    STONE_1(255);

    private int value;

    private BlockType(int value) {
        this.value = value;
    }

    public int getID() {
        return this.value;
    }

    public static BlockType fromID(int id) {
        for (BlockType block : BlockType.values()) {
            if (block.getID() == id) {
                return block;
            }
        }
        return null;
    }
}
