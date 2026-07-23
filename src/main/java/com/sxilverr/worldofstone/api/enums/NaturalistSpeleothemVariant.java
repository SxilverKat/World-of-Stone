package com.sxilverr.worldofstone.api.enums;

public enum NaturalistSpeleothemVariant {
    NATURALIST_SHELLSTONE("naturalist_shellstone", "Shellstone Speleothem", "naturalist:block/shellstone");

    public static final NaturalistSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    NaturalistSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
