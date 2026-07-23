package com.sxilverr.worldofstone.api.enums;

public enum TwigsSpeleothemVariant {
    TWIGS_SCHIST("twigs_schist", "Schist Speleothem", "twigs:block/schist"),
    TWIGS_RHYOLITE("twigs_rhyolite", "Rhyolite Speleothem", "twigs:block/rhyolite"),
    TWIGS_BLOODSTONE("twigs_bloodstone", "Bloodstone Speleothem", "twigs:block/bloodstone");

    public static final TwigsSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    TwigsSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
