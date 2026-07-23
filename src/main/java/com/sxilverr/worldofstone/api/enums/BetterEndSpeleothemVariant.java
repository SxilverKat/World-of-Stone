package com.sxilverr.worldofstone.api.enums;

public enum BetterEndSpeleothemVariant {
    BETTEREND_FLAVOLITE("betterend_flavolite", "Flavolite Speleothem", "betterend:block/flavolite"),
    BETTEREND_VIOLECITE("betterend_violecite", "Violecite Speleothem", "betterend:block/violecite"),
    BETTEREND_VIRID_JADESTONE("betterend_virid_jadestone", "Virid Jadestone Speleothem", "betterend:block/virid_jadestone"),
    BETTEREND_AZURE_JADESTONE("betterend_azure_jadestone", "Azure Jadestone Speleothem", "betterend:block/azure_jadestone"),
    BETTEREND_SANDY_JADESTONE("betterend_sandy_jadestone", "Sandy Jadestone Speleothem", "betterend:block/sandy_jadestone"),
    BETTEREND_SULPHURIC_ROCK("betterend_sulphuric_rock", "Sulphuric Rock Speleothem", "betterend:block/sulphuric_rock"),
    BETTEREND_UMBRALITH("betterend_umbralith", "Umbralith Speleothem", "betterend:block/umbralith"),
    BETTEREND_BRIMSTONE("betterend_brimstone", "Brimstone Speleothem", "betterend:block/brimstone");

    public static final BetterEndSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    BetterEndSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
