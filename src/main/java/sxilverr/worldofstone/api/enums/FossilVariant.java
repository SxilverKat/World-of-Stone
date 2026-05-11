package sxilverr.worldofstone.api.enums;

public enum FossilVariant {
    AMMONITE_FOSSIL,
    SHELL_FOSSIL,
    RIB_FOSSIL,
    SKULL_FOSSIL,
    BONE_FOSSIL;

    public static final FossilVariant[] VALUES = values();

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
