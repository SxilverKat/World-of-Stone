package com.sxilverr.worldofstone.api.enums;

public enum NoiseType {
    VALUE("Value"),
    PERLIN("Perlin"),
    SIMPLEX("Simplex"),
    FRACTIONAL_BROWNIAN_MOTION("Fractional Brownian Motion"),
    RIDGED("Ridged"),
    BILLOW("Billow"),
    CELLULAR("Cellular"),
    DOMAIN_WARPED("Domain Warped"),
    RIDGED_MULTIFRACTAL("Ridged Multifractal");

    public static final NoiseType[] VALUES = values();

    private final String label;

    NoiseType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static NoiseType fromLabel(String label) {
        if (label == null) return VALUE;
        for (NoiseType t : VALUES) {
            if (t.label.equalsIgnoreCase(label)) return t;
        }
        return VALUE;
    }
}
