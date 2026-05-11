package sxilverr.worldofstone.api.enums;

public enum WilderWildsSpeleothemVariant {
    WILDER_WILDS_LAVENDERHARDENEDCLAY("wilder_wilds_lavenderhardenedclay", "Lavender Terracotta Speleothem", "wilder_wilds:block/lavenderhardenedclay"),
    WILDER_WILDS_CORAL_HARDENED_CLAY("wilder_wilds_coral_hardened_clay", "Coral Terracotta Speleothem", "wilder_wilds:block/coral_hardened_clay"),
    WILDER_WILDS_CREAM_HARDENED_CLAY("wilder_wilds_cream_hardened_clay", "Cream Terracotta Speleothem", "wilder_wilds:block/cream_hardened_clay");

    public static final WilderWildsSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    WilderWildsSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
