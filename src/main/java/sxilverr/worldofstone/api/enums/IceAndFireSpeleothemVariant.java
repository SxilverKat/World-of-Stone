package sxilverr.worldofstone.api.enums;

public enum IceAndFireSpeleothemVariant {
    ICEANDFIRE_DREAD_STONE("iceandfire_dread_stone", "Dread Stone Speleothem", "iceandfire:block/dread_stone");

    public static final IceAndFireSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    IceAndFireSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
