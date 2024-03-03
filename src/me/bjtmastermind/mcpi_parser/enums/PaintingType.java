package me.bjtmastermind.mcpi_parser.enums;

public enum PaintingType {
    KEBAB("Kebab"),
    // AZTEC("Aztec"), - Doesn't exist due to (MCPE-7899)
    ALBAN("Alban"),
    AZTEC2("Aztec2"),
    BOMB("Bomb"),
    PLANT("Plant"),
    WASTELAND("Wasteland"),
    POOL("Pool"),
    COURBET("Courbet"),
    SEA("Sea"),
    SUNSET("Sunset"),
    CREEBET("Creebet"),
    WANDERER("Wanderer"),
    SKELETON("Skeleton"),
    GRAHAM("Graham"),
    FIGHTERS("Fighters"),
    MATCH("Match"),
    BUST("Bust"),
    STAGE("Stage"),
    VOID("Void"),
    SKULL_AND_ROSES("SkullAndRoses"),
    DONKEY_KONG("DonkeyKong"),
    EARTH("Earth"),
    WIND("Wind"),
    FIRE("Fire"),
    WATER("Water"),
    POINTER("Pointer"),
    PIGSCENE("Pigscene"),
    BURNING_SKULL("BurningSkull");

    private String value;

    private PaintingType(String value) {
        this.value = value;
    }

    public String getMotiveID() {
        return this.value;
    }
}
