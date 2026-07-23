package com.sxilverr.worldofstone.api.enums;

public enum CavernsAndChasmsSpeleothemVariant {
    CAVERNS_AND_CHASMS_SUGILITE("caverns_and_chasms_sugilite", "Sugilite Speleothem", "caverns_and_chasms:block/sugilite"),
    CAVERNS_AND_CHASMS_CYLINDRITE("caverns_and_chasms_cylindrite", "Cylindrite Speleothem", "caverns_and_chasms:block/cylindrite"),
    CAVERNS_AND_CHASMS_RHYOLITE("caverns_and_chasms_rhyolite", "Rhyolite Speleothem", "caverns_and_chasms:block/rhyolite");

    public static final CavernsAndChasmsSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    CavernsAndChasmsSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
