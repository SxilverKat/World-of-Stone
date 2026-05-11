package sxilverr.worldofstone.api.enums;

import net.minecraft.util.StringRepresentable;

public enum WosStoneType implements StringRepresentable {
    IGNEOUS,
    METAMORPHIC,
    SEDIMENTARY;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    @Override
    public String getSerializedName() {
        return toString();
    }
}
