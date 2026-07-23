package com.sxilverr.worldofstone.api.enums;

public enum VanillaOreHost {
    GRANITE("granite", "Granite", HostDimension.OVERWORLD),
    DIORITE("diorite", "Diorite", HostDimension.OVERWORLD),
    ANDESITE("andesite", "Andesite", HostDimension.OVERWORLD),
    TUFF("tuff", "Tuff", HostDimension.OVERWORLD),
    NETHERRACK("netherrack", "Netherrack", HostDimension.NETHER),
    BLACKSTONE("blackstone", "Blackstone", HostDimension.NETHER),
    BASALT("basalt", "Basalt", HostDimension.NETHER),
    END_STONE("end_stone", "End Stone", HostDimension.END),
    OBSIDIAN("obsidian", "Obsidian", HostDimension.ANY);

    public enum HostDimension { OVERWORLD, NETHER, END, ANY }

    public static final VanillaOreHost[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final HostDimension dimension;

    VanillaOreHost(String registryName, String displayName, HostDimension dimension) {
        this.registryName = registryName;
        this.displayName = displayName;
        this.dimension = dimension;
    }

    public String getRegistryName() {
        return registryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public HostDimension getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        return registryName;
    }
}
