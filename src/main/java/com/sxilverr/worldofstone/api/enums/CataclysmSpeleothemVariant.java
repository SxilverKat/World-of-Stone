package com.sxilverr.worldofstone.api.enums;

public enum CataclysmSpeleothemVariant {
    CATACLYSM_AZURE_SEASTONE("cataclysm_azure_seastone", "Azure Seastone Speleothem", "cataclysm:block/azure_seastone");

    public static final CataclysmSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    CataclysmSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
