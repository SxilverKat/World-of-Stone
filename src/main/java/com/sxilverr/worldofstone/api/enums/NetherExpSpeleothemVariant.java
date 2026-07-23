package com.sxilverr.worldofstone.api.enums;

public enum NetherExpSpeleothemVariant {
    NETHEREXP_SOUL_SLATE("netherexp_soul_slate", "Soul Slate Speleothem", "netherexp:block/soul_slate"),
    NETHEREXP_PALE_SOUL_SLATE("netherexp_pale_soul_slate", "Pale Soul Slate Speleothem", "netherexp:block/pale_soul_slate"),
    NETHEREXP_BLACK_ICE("netherexp_black_ice", "Black Ice Speleothem", "netherexp:block/black_ice");

    public static final NetherExpSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    NetherExpSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
