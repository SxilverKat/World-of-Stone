package sxilverr.worldofstone.api.enums;

public enum CreateSpeleothemVariant {
    CREATE_LIMESTONE("create_limestone", "Limestone Speleothem", "create:block/limestone"),
    CREATE_SCORIA("create_scoria", "Scoria Speleothem", "create:block/scoria"),
    CREATE_SCORCHIA("create_scorchia", "Scorchia Speleothem", "create:block/scorchia"),
    CREATE_ASURINE("create_asurine", "Asurine Speleothem", "create:block/asurine"),
    CREATE_OCHRUM("create_ochrum", "Ochrum Speleothem", "create:block/ochrum"),
    CREATE_VERIDIUM("create_veridium", "Veridium Speleothem", "create:block/veridium"),
    CREATE_CRIMSITE("create_crimsite", "Crimsite Speleothem", "create:block/crimsite");

    public static final CreateSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    CreateSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
