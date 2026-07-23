package com.sxilverr.worldofstone.api.enums;

public enum BotaniaSpeleothemVariant {
    BOTANIA_LIVINGROCK("botania_livingrock", "Livingrock Speleothem", "botania:block/livingrock"),
    BOTANIA_SHIMMERROCK("botania_shimmerrock", "Shimmerrock Speleothem", "botania:block/shimmerrock"),
    BOTANIA_METAMORPHIC_FOREST_STONE("botania_metamorphic_forest_stone", "Fuchsite Speleothem", "botania:block/metamorphic_forest_stone"),
    BOTANIA_METAMORPHIC_PLAINS_STONE("botania_metamorphic_plains_stone", "Talc Speleothem", "botania:block/metamorphic_plains_stone"),
    BOTANIA_METAMORPHIC_MOUNTAIN_STONE("botania_metamorphic_mountain_stone", "Gneiss Speleothem", "botania:block/metamorphic_mountain_stone"),
    BOTANIA_METAMORPHIC_FUNGAL_STONE("botania_metamorphic_fungal_stone", "Mycelite Speleothem", "botania:block/metamorphic_fungal_stone"),
    BOTANIA_METAMORPHIC_SWAMP_STONE("botania_metamorphic_swamp_stone", "Cataclasite Speleothem", "botania:block/metamorphic_swamp_stone"),
    BOTANIA_METAMORPHIC_DESERT_STONE("botania_metamorphic_desert_stone", "Solite Speleothem", "botania:block/metamorphic_desert_stone"),
    BOTANIA_METAMORPHIC_TAIGA_STONE("botania_metamorphic_taiga_stone", "Lunite Speleothem", "botania:block/metamorphic_taiga_stone"),
    BOTANIA_METAMORPHIC_MESA_STONE("botania_metamorphic_mesa_stone", "Rosy Talc Speleothem", "botania:block/metamorphic_mesa_stone");

    public static final BotaniaSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    BotaniaSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
