package com.sxilverr.worldofstone.api.enums;

public enum OuterEndSpeleothemVariant {
    OUTER_END_VIOLITE("outer_end_violite", "Violite Speleothem", "outer_end:block/violite"),
    OUTER_END_STROMATOLITE("outer_end_stromatolite", "Stromatolite Speleothem", "outer_end:block/stromatolite"),
    OUTER_END_HALITE("outer_end_halite", "Halite Speleothem", "outer_end:block/halite"),
    OUTER_END_ANCIENT_STONE("outer_end_ancient_stone", "Ancient Stone Speleothem", "outer_end:block/ancient_stone");

    public static final OuterEndSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    OuterEndSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
