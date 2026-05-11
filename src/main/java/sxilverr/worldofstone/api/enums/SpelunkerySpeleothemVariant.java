package sxilverr.worldofstone.api.enums;

public enum SpelunkerySpeleothemVariant {
    SPELUNKERY_ROCK_SALT_BLOCK("spelunkery_rock_salt_block", "Rock Salt Speleothem", "spelunkery:block/rock_salt_block"),
    SPELUNKERY_NEPHRITE("spelunkery_nephrite", "Nephrite Speleothem", "spelunkery:block/nephrite");

    public static final SpelunkerySpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    SpelunkerySpeleothemVariant(String registryName, String displayName, String textureRef) {
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
