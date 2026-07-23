package com.sxilverr.worldofstone.api.enums;

public enum TwilightForestSpeleothemVariant {
    TWILIGHTFOREST_MAZESTONE("twilightforest_mazestone", "Mazestone Speleothem", "twilightforest:block/mazestone"),
    TWILIGHTFOREST_DEADROCK("twilightforest_deadrock", "Deadrock Speleothem", "twilightforest:block/deadrock"),
    TWILIGHTFOREST_TROLLSTEINN("twilightforest_trollsteinn", "Trollsteinn Speleothem", "twilightforest:block/trollsteinn");

    public static final TwilightForestSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    TwilightForestSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
