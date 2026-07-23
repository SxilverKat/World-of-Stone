package com.sxilverr.worldofstone.api.enums;

public enum AetherSpeleothemVariant {
    AETHER_HOLYSTONE("aether_holystone", "Holystone Speleothem", "aether:block/natural/holystone"),
    AETHER_AEROGEL("aether_aerogel", "Aerogel Speleothem", "aether:block/construction/aerogel");

    public static final AetherSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    AetherSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
