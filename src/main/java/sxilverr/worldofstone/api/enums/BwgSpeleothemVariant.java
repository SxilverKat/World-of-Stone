package sxilverr.worldofstone.api.enums;

public enum BwgSpeleothemVariant {
    BWG_DACITE("bwg_dacite", "Dacite Speleothem", "biomeswevegone:block/dacite"),
    BWG_WHITE_DACITE("bwg_white_dacite", "White Dacite Speleothem", "biomeswevegone:block/white_dacite"),
    BWG_RED_ROCK("bwg_red_rock", "Red Rock Speleothem", "biomeswevegone:block/red_rock"),
    BWG_BLACK_SANDSTONE("bwg_black_sandstone", "Black Sandstone Speleothem", "biomeswevegone:block/black_sandstone"),
    BWG_WHITE_SANDSTONE("bwg_white_sandstone", "White Sandstone Speleothem", "biomeswevegone:block/white_sandstone"),
    BWG_BLUE_SANDSTONE("bwg_blue_sandstone", "Blue Sandstone Speleothem", "biomeswevegone:block/blue_sandstone"),
    BWG_PURPLE_SANDSTONE("bwg_purple_sandstone", "Purple Sandstone Speleothem", "biomeswevegone:block/purple_sandstone"),
    BWG_PINK_SANDSTONE("bwg_pink_sandstone", "Pink Sandstone Speleothem", "biomeswevegone:block/pink_sandstone"),
    BWG_WINDSWEPT_SANDSTONE("bwg_windswept_sandstone", "Windswept Sandstone Speleothem", "biomeswevegone:block/windswept_sandstone");

    public static final BwgSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    BwgSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
