package sxilverr.worldofstone.api.enums;

public enum TheDeepVoidSpeleothemVariant {
    THE_DEEP_VOID_ANCIENT_DEEPSLATE("the_deep_void_ancient_deepslate", "Ancient Deepslate Speleothem", "the_deep_void:block/ancient_deepslate"),
    THE_DEEP_VOID_PRIMORDIAL_STONE("the_deep_void_primordial_stone", "Primordial Stone Speleothem", "the_deep_void:block/primordial_stone"),
    THE_DEEP_VOID_SOLID_VOID_BLOCK("the_deep_void_solid_void_block", "Solid Void Speleothem", "the_deep_void:block/solid_void_block"),
    THE_DEEP_VOID_MONOLITHIC_STONE("the_deep_void_monolithic_stone", "Monolithic Stone Speleothem", "the_deep_void:block/monolithic_stone");

    public static final TheDeepVoidSpeleothemVariant[] VALUES = values();

    private final String registryName;
    private final String displayName;
    private final String textureRef;

    TheDeepVoidSpeleothemVariant(String registryName, String displayName, String textureRef) {
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
