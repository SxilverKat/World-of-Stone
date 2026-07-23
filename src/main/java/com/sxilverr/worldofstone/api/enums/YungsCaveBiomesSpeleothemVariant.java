package com.sxilverr.worldofstone.api.enums;

public enum YungsCaveBiomesSpeleothemVariant {
    YUNGSCAVEBIOMES_ANCIENT_SANDSTONE("yungscavebiomes_ancient_sandstone", "Ancient Sandstone Speleothem", "yungscavebiomes:block/ancient_sandstone");

    public static final YungsCaveBiomesSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    YungsCaveBiomesSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
