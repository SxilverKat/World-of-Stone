package com.sxilverr.worldofstone.api.enums;

import net.minecraft.util.StringRepresentable;

public enum SedimentaryVariant implements StringRepresentable {
    LIMESTONE(0.5F, 0.29F),
    CHALK(0.5F, 0.29F),
    SHALE(0.5F, 0.29F),
    SILTSTONE(0.6F, 0.4F),
    LIGNITE(0.5F, 0.29F),
    DOLOMITE(0.5F, 0.29F),
    GREYWACKE(1.0F, 1.0F),
    CHERT(0.9F, 0.86F);

    public static final SedimentaryVariant[] VALUES = values();

    private final float hardness;
    private final float resistance;

    SedimentaryVariant(float hardness, float resistance) {
        this.hardness = hardness * 1.5F;
        this.resistance = resistance * 1.66F;
    }

    public float getHardness() {
        return hardness;
    }

    public float getResistance() {
        return resistance;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    @Override
    public String getSerializedName() {
        return toString();
    }
}
