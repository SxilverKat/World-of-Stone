package sxilverr.worldofstone.api.enums;

public enum MysticalAgricultureSpeleothemVariant {
    MYSTICALAGRICULTURE_SOULSTONE("mysticalagriculture_soulstone", "Soulstone Speleothem", "mysticalagriculture:block/soulstone");

    public static final MysticalAgricultureSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    MysticalAgricultureSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
