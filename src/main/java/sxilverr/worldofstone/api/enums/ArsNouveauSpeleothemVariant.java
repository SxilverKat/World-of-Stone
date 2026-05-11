package sxilverr.worldofstone.api.enums;

public enum ArsNouveauSpeleothemVariant {
    ARS_NOUVEAU_SOURCESTONE("ars_nouveau_sourcestone", "Sourcestone Speleothem", "ars_nouveau:block/sourcestone");

    public static final ArsNouveauSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    ArsNouveauSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
