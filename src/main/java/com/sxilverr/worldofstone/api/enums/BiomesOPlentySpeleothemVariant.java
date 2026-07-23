package com.sxilverr.worldofstone.api.enums;

public enum BiomesOPlentySpeleothemVariant {
    BIOMESOPLENTY_WHITE_SANDSTONE("biomesoplenty_white_sandstone", "White Sandstone Speleothem", "biomesoplenty:block/white_sandstone"),
    BIOMESOPLENTY_ORANGE_SANDSTONE("biomesoplenty_orange_sandstone", "Orange Sandstone Speleothem", "biomesoplenty:block/orange_sandstone"),
    BIOMESOPLENTY_BLACK_SANDSTONE("biomesoplenty_black_sandstone", "Black Sandstone Speleothem", "biomesoplenty:block/black_sandstone"),
    BIOMESOPLENTY_BRIMSTONE("biomesoplenty_brimstone", "Brimstone Speleothem", "biomesoplenty:block/brimstone");

    public static final BiomesOPlentySpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    BiomesOPlentySpeleothemVariant(String registryName, String displayName, String textureRef) {
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
