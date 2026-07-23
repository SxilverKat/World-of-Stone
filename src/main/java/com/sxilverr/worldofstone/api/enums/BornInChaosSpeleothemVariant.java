package com.sxilverr.worldofstone.api.enums;

public enum BornInChaosSpeleothemVariant {
    BORN_IN_CHAOS_V1_BLACK_ARGILLITE("born_in_chaos_v1_black_argillite", "Black Argillite Speleothem", "born_in_chaos_v1:block/black_argillite");

    public static final BornInChaosSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    BornInChaosSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
