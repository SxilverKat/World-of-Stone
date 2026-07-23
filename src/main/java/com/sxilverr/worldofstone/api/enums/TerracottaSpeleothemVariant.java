package com.sxilverr.worldofstone.api.enums;

public enum TerracottaSpeleothemVariant {
    TERRACOTTA("terracotta", "Terracotta Speleothem", "minecraft:block/terracotta", true),
    WHITE("white_terracotta", "White Terracotta Speleothem", "minecraft:block/white_terracotta", true),
    ORANGE("orange_terracotta", "Orange Terracotta Speleothem", "minecraft:block/orange_terracotta", true),
    YELLOW("yellow_terracotta", "Yellow Terracotta Speleothem", "minecraft:block/yellow_terracotta", true),
    LIGHT_GRAY("light_gray_terracotta", "Light Gray Terracotta Speleothem", "minecraft:block/light_gray_terracotta", true),
    RED("red_terracotta", "Red Terracotta Speleothem", "minecraft:block/red_terracotta", true),
    BROWN("brown_terracotta", "Brown Terracotta Speleothem", "minecraft:block/brown_terracotta", true),
    MAGENTA("magenta_terracotta", "Magenta Terracotta Speleothem", "minecraft:block/magenta_terracotta", false),
    LIGHT_BLUE("light_blue_terracotta", "Light Blue Terracotta Speleothem", "minecraft:block/light_blue_terracotta", false),
    LIME("lime_terracotta", "Lime Terracotta Speleothem", "minecraft:block/lime_terracotta", false),
    PINK("pink_terracotta", "Pink Terracotta Speleothem", "minecraft:block/pink_terracotta", false),
    GRAY("gray_terracotta", "Gray Terracotta Speleothem", "minecraft:block/gray_terracotta", false),
    CYAN("cyan_terracotta", "Cyan Terracotta Speleothem", "minecraft:block/cyan_terracotta", false),
    PURPLE("purple_terracotta", "Purple Terracotta Speleothem", "minecraft:block/purple_terracotta", false),
    BLUE("blue_terracotta", "Blue Terracotta Speleothem", "minecraft:block/blue_terracotta", false),
    GREEN("green_terracotta", "Green Terracotta Speleothem", "minecraft:block/green_terracotta", false),
    BLACK("black_terracotta", "Black Terracotta Speleothem", "minecraft:block/black_terracotta", false);

    public static final TerracottaSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;
    private final boolean naturalGeneration;

    TerracottaSpeleothemVariant(String registryName, String displayName, String textureRef, boolean naturalGeneration) {
        this.registryName = registryName;
        this.displayName = displayName;
        this.textureRef = textureRef;
        this.naturalGeneration = naturalGeneration;
    }

    public String getRegistryName() {
        return registryName + "_speleothem";
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTextureRef() {
        return textureRef;
    }

    public boolean isNaturalGeneration() {
        return naturalGeneration;
    }

    public String getHostBlock() {
        return "minecraft:" + registryName;
    }

    @Override
    public String toString() {
        return registryName;
    }
}
