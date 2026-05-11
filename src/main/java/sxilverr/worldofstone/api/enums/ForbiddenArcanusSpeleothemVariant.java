package sxilverr.worldofstone.api.enums;

public enum ForbiddenArcanusSpeleothemVariant {
    FORBIDDEN_ARCANUS_DARKSTONE("forbidden_arcanus_darkstone", "Darkstone Speleothem", "forbidden_arcanus:block/darkstone"),
    FORBIDDEN_ARCANUS_SOULLESS_SANDSTONE("forbidden_arcanus_soulless_sandstone", "Soulless Sandstone Speleothem", "forbidden_arcanus:block/soulless_sandstone");

    public static final ForbiddenArcanusSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    ForbiddenArcanusSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
