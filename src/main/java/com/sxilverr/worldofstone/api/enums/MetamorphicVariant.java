package com.sxilverr.worldofstone.api.enums;

import net.minecraft.util.StringRepresentable;

public enum MetamorphicVariant implements StringRepresentable {
    GNEISS(1.1F, 1.11F),
    ECLOGITE(1.0F, 1.0F),
    MARBLE(1.1F, 1.11F),
    QUARTZITE(1.3F, 1.26F),
    BLUESCHIST(0.7F, 0.54F),
    GREENSCHIST(0.7F, 0.54F),
    SOAPSTONE(0.4F, 0.2F),
    MIGMATITE(0.9F, 0.86F);

    public static final MetamorphicVariant[] VALUES = values();

    private final float hardness;
    private final float resistance;

    MetamorphicVariant(float hardness, float resistance) {
        this.hardness = hardness;
        this.resistance = resistance;
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
