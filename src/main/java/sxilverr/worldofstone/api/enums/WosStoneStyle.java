package sxilverr.worldofstone.api.enums;

import net.minecraft.util.StringRepresentable;

public enum WosStoneStyle implements StringRepresentable {
    STONE,
    COBBLE,
    BRICK,
    MOSSY_COBBLE,
    SAND,
    SANDSTONE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    @Override
    public String getSerializedName() {
        return toString();
    }
}
