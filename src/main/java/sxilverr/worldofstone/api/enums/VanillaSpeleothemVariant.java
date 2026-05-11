package sxilverr.worldofstone.api.enums;

public enum VanillaSpeleothemVariant {
    STONE("stone", "Stone Speleothem", "minecraft:block/stone"),
    GRANITE("granite", "Granite Speleothem", "minecraft:block/granite"),
    DIORITE("diorite", "Diorite Speleothem", "minecraft:block/diorite"),
    ANDESITE("andesite", "Andesite Speleothem", "minecraft:block/andesite"),
    DEEPSLATE("deepslate", "Deepslate Speleothem", "minecraft:block/deepslate"),
    TUFF("tuff", "Tuff Speleothem", "minecraft:block/tuff"),
    CALCITE("calcite", "Calcite Speleothem", "minecraft:block/calcite"),
    DRIPSTONE("dripstone", "Dripstone Speleothem", "minecraft:block/dripstone_block"),
    NETHERRACK("netherrack", "Netherrack Speleothem", "minecraft:block/netherrack"),
    BASALT("basalt", "Basalt Speleothem", "minecraft:block/basalt_side"),
    SMOOTH_BASALT("smooth_basalt", "Smooth Basalt Speleothem", "minecraft:block/smooth_basalt"),
    BLACKSTONE("blackstone", "Blackstone Speleothem", "minecraft:block/blackstone"),
    END_STONE("end_stone", "End Stone Speleothem", "minecraft:block/end_stone"),
    SANDSTONE("sandstone", "Sandstone Speleothem", "minecraft:block/sandstone"),
    RED_SANDSTONE("red_sandstone", "Red Sandstone Speleothem", "minecraft:block/red_sandstone");

    public static final VanillaSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    VanillaSpeleothemVariant(String registryName, String displayName, String textureRef) {
        this.registryName = registryName;
        this.displayName = displayName;
        this.textureRef = textureRef;
    }

    public String getRegistryName() {
        return registryName + "_speleothem";
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTextureRef() {
        return textureRef;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
