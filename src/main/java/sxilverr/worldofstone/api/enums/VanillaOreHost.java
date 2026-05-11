package sxilverr.worldofstone.api.enums;

public enum VanillaOreHost {
    GRANITE("granite", "Granite"),
    DIORITE("diorite", "Diorite"),
    ANDESITE("andesite", "Andesite"),
    TUFF("tuff", "Tuff"),
    NETHERRACK("netherrack", "Netherrack"),
    BLACKSTONE("blackstone", "Blackstone"),
    BASALT("basalt", "Basalt"),
    END_STONE("end_stone", "End Stone"),
    OBSIDIAN("obsidian", "Obsidian");

    public static final VanillaOreHost[] VALUES = values();

    private final String registryName;
    private final String displayName;

    VanillaOreHost(String registryName, String displayName) {
        this.registryName = registryName;
        this.displayName = displayName;
    }

    public String getRegistryName() {
        return registryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return registryName;
    }
}
