package com.sxilverr.worldofstone.api.enums;

public enum RegionsUnexploredSpeleothemVariant {
    REGIONS_UNEXPLORED_CHALK("regions_unexplored_chalk", "Chalk Speleothem", "regions_unexplored:block/chalk"),
    REGIONS_UNEXPLORED_ARGILLITE("regions_unexplored_argillite", "Argillite Speleothem", "regions_unexplored:block/argillite"),
    REGIONS_UNEXPLORED_MOSSY_STONE("regions_unexplored_mossy_stone", "Mossy Stone Speleothem", "regions_unexplored:block/mossy_stone");

    public static final RegionsUnexploredSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    RegionsUnexploredSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
