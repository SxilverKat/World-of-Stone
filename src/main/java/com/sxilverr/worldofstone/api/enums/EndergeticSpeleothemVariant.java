package com.sxilverr.worldofstone.api.enums;

public enum EndergeticSpeleothemVariant {
    ENDERGETIC_EUMUS("endergetic_eumus", "Eumus Speleothem", "endergetic:block/eumus");

    public static final EndergeticSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    EndergeticSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
