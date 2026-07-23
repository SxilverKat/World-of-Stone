package com.sxilverr.worldofstone.api.enums;

public enum DeepAetherSpeleothemVariant {
    DEEP_AETHER_ASETERITE("deep_aether_aseterite", "Aseterite Speleothem", "deep_aether:block/aseterite"),
    DEEP_AETHER_RAW_CLORITE("deep_aether_raw_clorite", "Raw Clorite Speleothem", "deep_aether:block/raw_clorite");

    public static final DeepAetherSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    DeepAetherSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
