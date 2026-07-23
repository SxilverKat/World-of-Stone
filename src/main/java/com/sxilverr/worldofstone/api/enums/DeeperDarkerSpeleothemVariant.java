package com.sxilverr.worldofstone.api.enums;

public enum DeeperDarkerSpeleothemVariant {
    DEEPERDARKER_SCULK_STONE("deeperdarker_sculk_stone", "Sculk Stone Speleothem", "deeperdarker:block/sculk_stone"),
    DEEPERDARKER_GLOOMSLATE("deeperdarker_gloomslate", "Gloomslate Speleothem", "deeperdarker:block/gloomslate");

    public static final DeeperDarkerSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    DeeperDarkerSpeleothemVariant(String registryName, String displayName, String textureRef) {
        this.registryName = registryName;
        this.displayName = displayName;
        this.textureRef = textureRef;
    }

    public String getRegistryName() { return registryName + "_speleothem"; }
    public String getDisplayName() { return displayName; }
    public String getTextureRef() { return textureRef; }

    @Override
    public String toString() { return name().toLowerCase(); }
}
