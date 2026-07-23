package com.sxilverr.worldofstone.api.enums;

public enum BlueSkiesSpeleothemVariant {
    BLUE_SKIES_LUNAR_STONE("blue_skies_lunar_stone", "Lunar Stone Speleothem", "blue_skies:block/stone/lunar_stone"),
    BLUE_SKIES_TURQUOISE_STONE("blue_skies_turquoise_stone", "Turquoise Stone Speleothem", "blue_skies:block/stone/turquoise_stone"),
    BLUE_SKIES_MIDNIGHT_SANDSTONE("blue_skies_midnight_sandstone", "Midnight Sandstone Speleothem", "blue_skies:block/stone/midnight_sandstone"),
    BLUE_SKIES_CRYSTAL_SANDSTONE("blue_skies_crystal_sandstone", "Crystal Sandstone Speleothem", "blue_skies:block/stone/crystal_sandstone");

    public static final BlueSkiesSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    BlueSkiesSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
