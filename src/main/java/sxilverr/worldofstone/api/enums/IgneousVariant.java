package sxilverr.worldofstone.api.enums;

import net.minecraft.util.StringRepresentable;

public enum IgneousVariant implements StringRepresentable {
    ADAMELLITE(1.7F, 1.42F),
    PYROXENITE(1.6F, 1.39F),
    RHYOLITE(1.3F, 1.26F),
    TONALITE(1.4F, 1.31F),
    GABBRO(1.0F, 1.0F),
    LATITE(1.4F, 1.31F),
    KOMATIITE(1.5F, 1.35F),
    DACITE(1.2F, 1.2F);

    public static final IgneousVariant[] VALUES = values();

    private final float hardness;
    private final float resistance;

    IgneousVariant(float hardness, float resistance) {
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
