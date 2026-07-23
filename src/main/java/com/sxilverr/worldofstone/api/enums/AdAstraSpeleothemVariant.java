package com.sxilverr.worldofstone.api.enums;

public enum AdAstraSpeleothemVariant {
    AD_ASTRA_SKY_STONE("ad_astra_sky_stone", "Sky Stone Speleothem", "ad_astra:block/sky_stone"),
    AD_ASTRA_MOON_STONE("ad_astra_moon_stone", "Moon Stone Speleothem", "ad_astra:block/moon_stone"),
    AD_ASTRA_MOON_DEEPSLATE("ad_astra_moon_deepslate", "Moon Deepslate Speleothem", "ad_astra:block/moon_deepslate"),
    AD_ASTRA_MARS_STONE("ad_astra_mars_stone", "Mars Stone Speleothem", "ad_astra:block/mars_stone"),
    AD_ASTRA_VENUS_STONE("ad_astra_venus_stone", "Venus Stone Speleothem", "ad_astra:block/venus_stone"),
    AD_ASTRA_VENUS_SANDSTONE("ad_astra_venus_sandstone", "Venus Sandstone Speleothem", "ad_astra:block/venus_sandstone"),
    AD_ASTRA_MERCURY_STONE("ad_astra_mercury_stone", "Mercury Stone Speleothem", "ad_astra:block/mercury_stone"),
    AD_ASTRA_GLACIO_STONE("ad_astra_glacio_stone", "Glacio Stone Speleothem", "ad_astra:block/glacio_stone"),
    AD_ASTRA_PERMAFROST("ad_astra_permafrost", "Permafrost Speleothem", "ad_astra:block/permafrost");

    public static final AdAstraSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    AdAstraSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
