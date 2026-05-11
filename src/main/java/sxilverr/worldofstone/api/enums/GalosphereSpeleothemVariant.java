package sxilverr.worldofstone.api.enums;

public enum GalosphereSpeleothemVariant {
    GALOSPHERE_ALLURITE("galosphere_allurite", "Allurite Speleothem", "galosphere:block/allurite_block"),
    GALOSPHERE_LUMIERE("galosphere_lumiere", "Lumiere Speleothem", "galosphere:block/lumiere_block");

    public static final GalosphereSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    GalosphereSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
