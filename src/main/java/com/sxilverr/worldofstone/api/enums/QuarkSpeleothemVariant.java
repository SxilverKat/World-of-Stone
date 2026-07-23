package com.sxilverr.worldofstone.api.enums;

public enum QuarkSpeleothemVariant {
    QUARK_LIMESTONE("quark_limestone", "Limestone Speleothem", "quark:block/limestone"),
    QUARK_SHALE("quark_shale", "Shale Speleothem", "quark:block/shale"),
    QUARK_MYALITE("quark_myalite", "Myalite Speleothem", "quark:block/myalite"),
    QUARK_PERMAFROST("quark_permafrost", "Permafrost Speleothem", "quark:block/permafrost"),
    QUARK_JASPER("quark_jasper", "Jasper Speleothem", "quark:block/jasper"),
    QUARK_DUSKY_MYALITE("quark_dusky_myalite", "Dusky Myalite Speleothem", "quark:block/dusky_myalite");

    public static final QuarkSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    QuarkSpeleothemVariant(String registryName, String displayName, String textureRef) {
        this.registryName = registryName;
        this.displayName = displayName;
        this.textureRef = textureRef;
    }

    public String getRegistryName() {
        return registryName + "_speleothem";
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTextureRef() {
        return textureRef;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
