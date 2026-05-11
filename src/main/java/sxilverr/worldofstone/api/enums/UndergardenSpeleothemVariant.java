package sxilverr.worldofstone.api.enums;

public enum UndergardenSpeleothemVariant {
    UNDERGARDEN_DEPTHROCK("undergarden_depthrock", "Depthrock Speleothem", "undergarden:block/depthrock"),
    UNDERGARDEN_SHIVERSTONE("undergarden_shiverstone", "Shiverstone Speleothem", "undergarden:block/shiverstone");

    public static final UndergardenSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    UndergardenSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
