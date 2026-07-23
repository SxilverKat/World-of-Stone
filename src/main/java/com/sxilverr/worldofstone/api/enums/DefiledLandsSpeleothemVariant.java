package com.sxilverr.worldofstone.api.enums;

public enum DefiledLandsSpeleothemVariant {
    DEFILED_LANDS_PREBORN_DEFILED_STONE("defiled_lands_preborn_defiled_stone", "Defiled Stone Speleothem", "defiled_lands_preborn:block/defiled_stone");

    public static final DefiledLandsSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    DefiledLandsSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
