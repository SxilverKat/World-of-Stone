package sxilverr.worldofstone.api.enums;

import net.minecraft.util.valueproviders.UniformInt;

public enum OreVariant {
    COAL("coal_ore", "minecraft:coal", "ore_drops", 1, 1, 0, 2),
    IRON("iron_ore", "minecraft:raw_iron", "ore_drops", 1, 1, 0, 0),
    GOLD("gold_ore", "minecraft:raw_gold", "ore_drops", 1, 1, 0, 0),
    DIAMOND("diamond_ore", "minecraft:diamond", "ore_drops", 1, 1, 3, 7),
    EMERALD("emerald_ore", "minecraft:emerald", "ore_drops", 1, 1, 3, 7),
    REDSTONE("redstone_ore", "minecraft:redstone", "uniform", 4, 5, 1, 5),
    LAPIS("lapis_ore", "minecraft:lapis_lazuli", "uniform", 4, 9, 2, 5),
    COPPER("copper_ore", "minecraft:raw_copper", "uniform", 2, 5, 0, 2);

    public static final OreVariant[] VALUES = values();

    public final String suffix;
    public final String dropItem;
    public final String fortuneFormula;
    public final int dropMin;
    public final int dropMax;
    public final int xpMin;
    public final int xpMax;

    OreVariant(String suffix, String dropItem, String fortuneFormula, int dropMin, int dropMax, int xpMin, int xpMax) {
        this.suffix = suffix;
        this.dropItem = dropItem;
        this.fortuneFormula = fortuneFormula;
        this.dropMin = dropMin;
        this.dropMax = dropMax;
        this.xpMin = xpMin;
        this.xpMax = xpMax;
    }

    public UniformInt getXpProvider() {
        return UniformInt.of(xpMin, xpMax);
    }

    public boolean needsStonePick() {
        return this == IRON || this == LAPIS || this == COPPER;
    }

    public boolean needsIronPick() {
        return this == GOLD || this == DIAMOND || this == EMERALD || this == REDSTONE;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
