package sxilverr.worldofstone.api.enums;

public enum NaturesSpiritSpeleothemVariant {
    NATURES_SPIRIT_TRAVERTINE("natures_spirit_travertine", "Travertine Speleothem", "natures_spirit:block/travertine"),
    NATURES_SPIRIT_CHERT("natures_spirit_chert", "Chert Speleothem", "natures_spirit:block/chert"),
    NATURES_SPIRIT_PINK_SANDSTONE("natures_spirit_pink_sandstone", "Pink Sandstone Speleothem", "natures_spirit:block/pink_sandstone");

    public static final NaturesSpiritSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    NaturesSpiritSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
