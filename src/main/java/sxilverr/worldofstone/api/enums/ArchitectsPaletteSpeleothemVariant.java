package sxilverr.worldofstone.api.enums;

public enum ArchitectsPaletteSpeleothemVariant {
    ARCHITECTS_PALETTE_ABYSSALINE("architects_palette_abyssaline", "Abyssaline Speleothem", "architects_palette:block/abyssaline"),
    ARCHITECTS_PALETTE_MYONITE("architects_palette_myonite", "Myonite Speleothem", "architects_palette:block/myonite"),
    ARCHITECTS_PALETTE_HADALINE("architects_palette_hadaline", "Hadaline Speleothem", "architects_palette:block/hadaline"),
    ARCHITECTS_PALETTE_ESOTERRACK("architects_palette_esoterrack", "Esoterrack Speleothem", "architects_palette:block/esoterrack"),
    ARCHITECTS_PALETTE_ONYX("architects_palette_onyx", "Onyx Speleothem", "architects_palette:block/onyx"),
    ARCHITECTS_PALETTE_WARDSTONE("architects_palette_wardstone", "Wardstone Speleothem", "architects_palette:block/wardstone"),
    ARCHITECTS_PALETTE_MOONSHALE("architects_palette_moonshale", "Moonshale Speleothem", "architects_palette:block/moonshale"),
    ARCHITECTS_PALETTE_NEBULITE("architects_palette_nebulite", "Nebulite Speleothem", "architects_palette:block/nebulite");

    public static final ArchitectsPaletteSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    ArchitectsPaletteSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
