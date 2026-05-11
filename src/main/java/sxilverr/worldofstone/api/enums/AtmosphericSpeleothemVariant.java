package sxilverr.worldofstone.api.enums;

public enum AtmosphericSpeleothemVariant {
    ATMOSPHERIC_IVORY_TRAVERTINE("atmospheric_ivory_travertine", "Ivory Travertine Speleothem", "atmospheric:block/ivory_travertine"),
    ATMOSPHERIC_PEACH_TRAVERTINE("atmospheric_peach_travertine", "Peach Travertine Speleothem", "atmospheric:block/peach_travertine"),
    ATMOSPHERIC_PERSIMMON_TRAVERTINE("atmospheric_persimmon_travertine", "Persimmon Travertine Speleothem", "atmospheric:block/persimmon_travertine"),
    ATMOSPHERIC_SAFFRON_TRAVERTINE("atmospheric_saffron_travertine", "Saffron Travertine Speleothem", "atmospheric:block/saffron_travertine"),
    ATMOSPHERIC_DOLERITE("atmospheric_dolerite", "Dolerite Speleothem", "atmospheric:block/dolerite"),
    ATMOSPHERIC_ARID_SANDSTONE("atmospheric_arid_sandstone", "Arid Sandstone Speleothem", "atmospheric:block/arid_sandstone"),
    ATMOSPHERIC_RED_ARID_SANDSTONE("atmospheric_red_arid_sandstone", "Red Arid Sandstone Speleothem", "atmospheric:block/red_arid_sandstone");

    public static final AtmosphericSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    AtmosphericSpeleothemVariant(String registryName, String displayName, String textureRef) {
        this.registryName = registryName;
        this.displayName = displayName;
        this.textureRef = textureRef;
    }

    public String getRegistryName() { return registryName + "_speleothem"; }
    public String getDisplayName() { return displayName; }
    public String getTextureRef() { return textureRef; }

    @Override
    public String toString() { return name().toLowerCase(); }
}
