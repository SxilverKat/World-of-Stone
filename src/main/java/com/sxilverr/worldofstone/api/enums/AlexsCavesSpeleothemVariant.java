package com.sxilverr.worldofstone.api.enums;

public enum AlexsCavesSpeleothemVariant {
    ALEXSCAVES_GALENA("alexscaves_galena", "Galena Speleothem", "alexscaves:block/galena"),
    ALEXSCAVES_LIMESTONE("alexscaves_limestone", "Limestone Speleothem", "alexscaves:block/limestone"),
    ALEXSCAVES_RADROCK("alexscaves_radrock", "Radrock Speleothem", "alexscaves:block/radrock"),
    ALEXSCAVES_ABYSSMARINE("alexscaves_abyssmarine", "Abyssmarine Speleothem", "alexscaves:block/abyssmarine"),
    ALEXSCAVES_GUANOSTONE("alexscaves_guanostone", "Guanostone Speleothem", "alexscaves:block/guanostone"),
    ALEXSCAVES_COPROLITH("alexscaves_coprolith", "Coprolith Speleothem", "alexscaves:block/coprolith"),
    ALEXSCAVES_GINGERBREAD_BLOCK("alexscaves_gingerbread_block", "Gingerbread Speleothem", "alexscaves:block/gingerbread_block");

    public static final AlexsCavesSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    AlexsCavesSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
