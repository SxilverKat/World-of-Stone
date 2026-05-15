package sxilverr.worldofstone.config;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.IgneousVariant;
import sxilverr.worldofstone.api.enums.MetamorphicVariant;
import sxilverr.worldofstone.api.enums.NoiseType;
import sxilverr.worldofstone.api.enums.QuarkSpeleothemVariant;
import sxilverr.worldofstone.api.enums.SedimentaryVariant;
import sxilverr.worldofstone.api.enums.TerracottaSpeleothemVariant;
import sxilverr.worldofstone.api.enums.VanillaOreHost;
import sxilverr.worldofstone.api.enums.VanillaSpeleothemVariant;
import sxilverr.worldofstone.api.enums.WorldgenSystem;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ModInfo.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class WosConfig {

    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.BooleanValue STONE_REPLACEMENT_ENABLED;
    public static final ForgeConfigSpec.EnumValue<WorldgenSystem> WORLDGEN_SYSTEM;

    public static final ForgeConfigSpec.BooleanValue REPLACE_STONE_ABOVE_GROUND;
    public static final ForgeConfigSpec.BooleanValue REPLACE_SAND;
    public static final ForgeConfigSpec.BooleanValue REPLACE_SANDSTONE;
    public static final ForgeConfigSpec.BooleanValue REPLACE_GRAVEL;
    public static final ForgeConfigSpec.BooleanValue REPLACE_RED_SAND_AND_SANDSTONE;
    public static final ForgeConfigSpec.IntValue VARIANT_CHANCE;
    public static final ForgeConfigSpec.DoubleValue STRATA_BOTTOM_RATIO;
    public static final ForgeConfigSpec.DoubleValue STRATA_MIDDLE_RATIO;
    public static final ForgeConfigSpec.BooleanValue IGNORE_STRATA_HEIGHT_RESTRICTIONS;
    public static final ForgeConfigSpec.BooleanValue ALLOW_STRATA_IN_DEEPSLATE;
    public static final ForgeConfigSpec.BooleanValue ALLOW_VANILLA_ORES_IN_NETHER;
    public static final ForgeConfigSpec.BooleanValue ALLOW_VANILLA_ORES_IN_END;
    public static final ForgeConfigSpec.BooleanValue ALLOW_OBSIDIAN_ORE_VARIANTS;
    public static final ForgeConfigSpec.DoubleValue NETHER_ORE_ATTEMPTS_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue END_ORE_ATTEMPTS_MULTIPLIER;
    public static final ForgeConfigSpec.BooleanValue ALLOW_OVERGROWN_STRATA;
    public static final ForgeConfigSpec.BooleanValue ALLOW_SNOWED_STRATA;
    public static final ForgeConfigSpec.BooleanValue ALLOW_OVERGROWN_VANILLA_VARIANTS;
    public static final ForgeConfigSpec.BooleanValue ALLOW_SNOWED_VANILLA_VARIANTS;

    public static final ForgeConfigSpec.IntValue PATCH_SIZE;
    public static final ForgeConfigSpec.BooleanValue REPLACE_VANILLA_ORES;
    public static final ForgeConfigSpec.BooleanValue ENABLE_STRUCTURE_VARIANT_GENERATION;
    public static final ForgeConfigSpec.IntValue STRATA_XZ_DITHER;
    public static final ForgeConfigSpec.IntValue STRATA_Y_DITHER;

    public static final ForgeConfigSpec.IntValue BLOBS_PER_CHUNK;
    public static final ForgeConfigSpec.IntValue BLOB_SIZE;

    public static final ForgeConfigSpec.IntValue NOISE_SCALE;
    public static final ForgeConfigSpec.EnumValue<NoiseType> NOISE_TYPE;
    public static final ForgeConfigSpec.IntValue NOISE_OCTAVES;

    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_ENABLED;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_GENERATE;
    public static final ForgeConfigSpec.BooleanValue SANDSTONE_SPELEOTHEMS_ENABLED;
    public static final ForgeConfigSpec.IntValue SPELEOTHEM_RARITY;
    public static final ForgeConfigSpec.IntValue SPELEOTHEM_ATTEMPTS_PER_CHUNK;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_WATERLOGGABLE;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_GENERATE_IN_WATER;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_GENERATE_IN_LAVA;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_BREAK_BY_WATER;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_BREAK_BY_LAVA;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_DROP_WITHOUT_SILK_TOUCH;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_DRIPSTONE_DAMAGE;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_CAN_FALL;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_DROP_ON_FALL;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_ALLOW_SKY_VIEW;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_ALLOW_SKY_VIEW_END;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_ALLOW_ABOVE_GROUND;
    public static final ForgeConfigSpec.BooleanValue SPELEOTHEMS_ALLOW_POINTING_UP_ABOVE_GROUND;
    public static final ForgeConfigSpec.DoubleValue FOSSIL_DROP_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> FOSSIL_DROP_BLOCKS;
    public static final ForgeConfigSpec.BooleanValue MASON_TRADES_ENABLED;
    public static final ForgeConfigSpec.BooleanValue ALLOW_PILLAR_VARIANT_TRADES;
    public static final ForgeConfigSpec.BooleanValue STONE_DROPS_COBBLESTONE;
    public static final ForgeConfigSpec.BooleanValue LIGNITE_DROPS_LIGNITE_COBBLESTONE;
    public static final ForgeConfigSpec.BooleanValue ENABLE_INFESTED_BLOCKS;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> MIMIC_SPELEOTHEM_BLOCKS;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> MIMIC_SPELEOTHEM_WORLDGEN_BLOCKS;

    public static final Map<IgneousVariant, ForgeConfigSpec.BooleanValue> IGNEOUS_VARIANT_ENABLED = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, ForgeConfigSpec.BooleanValue> METAMORPHIC_VARIANT_ENABLED = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, ForgeConfigSpec.BooleanValue> SEDIMENTARY_VARIANT_ENABLED = new EnumMap<>(SedimentaryVariant.class);

    public static final Map<IgneousVariant, ForgeConfigSpec.IntValue> IGNEOUS_VARIANT_WEIGHT = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, ForgeConfigSpec.IntValue> METAMORPHIC_VARIANT_WEIGHT = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, ForgeConfigSpec.IntValue> SEDIMENTARY_VARIANT_WEIGHT = new EnumMap<>(SedimentaryVariant.class);

    public static final Map<IgneousVariant, ForgeConfigSpec.BooleanValue> IGNEOUS_SPELEOTHEM = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, ForgeConfigSpec.BooleanValue> METAMORPHIC_SPELEOTHEM = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, ForgeConfigSpec.BooleanValue> SEDIMENTARY_SPELEOTHEM = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<IgneousVariant, ForgeConfigSpec.BooleanValue> IGNEOUS_SANDSTONE_SPELEOTHEM = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, ForgeConfigSpec.BooleanValue> METAMORPHIC_SANDSTONE_SPELEOTHEM = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, ForgeConfigSpec.BooleanValue> SEDIMENTARY_SANDSTONE_SPELEOTHEM = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<VanillaSpeleothemVariant, ForgeConfigSpec.BooleanValue> VANILLA_SPELEOTHEM = new EnumMap<>(VanillaSpeleothemVariant.class);
    public static final Map<QuarkSpeleothemVariant, ForgeConfigSpec.BooleanValue> QUARK_SPELEOTHEM = new EnumMap<>(QuarkSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant, ForgeConfigSpec.BooleanValue> UNDERGARDEN_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.CreateSpeleothemVariant, ForgeConfigSpec.BooleanValue> CREATE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant, ForgeConfigSpec.BooleanValue> BETTEREND_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant, ForgeConfigSpec.BooleanValue> GALOSPHERE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BwgSpeleothemVariant, ForgeConfigSpec.BooleanValue> BWG_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant, ForgeConfigSpec.BooleanValue> TWILIGHTFOREST_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AetherSpeleothemVariant, ForgeConfigSpec.BooleanValue> AETHER_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant, ForgeConfigSpec.BooleanValue> BLUE_SKIES_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant, ForgeConfigSpec.BooleanValue> SPELUNKERY_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant, ForgeConfigSpec.BooleanValue> ICEANDFIRE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant, ForgeConfigSpec.BooleanValue> MYSTICALAGRICULTURE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant, ForgeConfigSpec.BooleanValue> BIOMESOPLENTY_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant, ForgeConfigSpec.BooleanValue> FORBIDDEN_ARCANUS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant, ForgeConfigSpec.BooleanValue> ALEXSCAVES_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant, ForgeConfigSpec.BooleanValue> ARS_NOUVEAU_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant, ForgeConfigSpec.BooleanValue> CATACLYSM_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant, ForgeConfigSpec.BooleanValue> TWIGS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant, ForgeConfigSpec.BooleanValue> ARCHITECTS_PALETTE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant, ForgeConfigSpec.BooleanValue> OUTER_END_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant, ForgeConfigSpec.BooleanValue> BOTANIA_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant, ForgeConfigSpec.BooleanValue> AD_ASTRA_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant, ForgeConfigSpec.BooleanValue> DEEP_AETHER_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant, ForgeConfigSpec.BooleanValue> CAVERNS_AND_CHASMS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant, ForgeConfigSpec.BooleanValue> ATMOSPHERIC_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant, ForgeConfigSpec.BooleanValue> ENDERGETIC_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant, ForgeConfigSpec.BooleanValue> WILDER_WILDS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant, ForgeConfigSpec.BooleanValue> REGIONS_UNEXPLORED_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant, ForgeConfigSpec.BooleanValue> BORN_IN_CHAOS_V1_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant, ForgeConfigSpec.BooleanValue> NATURALIST_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant, ForgeConfigSpec.BooleanValue> YUNGSCAVEBIOMES_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant, ForgeConfigSpec.BooleanValue> NATURES_SPIRIT_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant, ForgeConfigSpec.BooleanValue> NETHEREXP_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant, ForgeConfigSpec.BooleanValue> DEEPERDARKER_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant, ForgeConfigSpec.BooleanValue> THE_DEEP_VOID_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant, ForgeConfigSpec.BooleanValue> DEFILED_LANDS_PREBORN_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.class);
    public static final Map<TerracottaSpeleothemVariant, ForgeConfigSpec.BooleanValue> TERRACOTTA_SPELEOTHEM_ENABLED_MAP = new EnumMap<>(TerracottaSpeleothemVariant.class);
    public static final Map<VanillaOreHost, ForgeConfigSpec.BooleanValue> VANILLA_ORE_HOST_ENABLED = new EnumMap<>(VanillaOreHost.class);
    public static final ForgeConfigSpec.BooleanValue TERRACOTTA_SPELEOTHEMS_ENABLED;
    public static final ForgeConfigSpec.BooleanValue ICE_SPELEOTHEM_ENABLED;

    public static boolean stoneReplacementEnabled = true;
    public static int patchSize = 8;
    public static boolean replaceVanillaOres = true;
    public static double strataBottomRatio = 0.33;
    public static double strataMiddleRatio = 0.66;
    public static boolean ignoreStrataHeightRestrictions = false;
    public static boolean allowStrataInDeepslate = false;
    public static boolean allowVanillaOresInNether = false;
    public static boolean allowVanillaOresInEnd = false;
    public static boolean allowObsidianOreVariants = false;
    public static double netherOreAttemptsMultiplier = 1.0;
    public static double endOreAttemptsMultiplier = 1.0;
    public static boolean allowOvergrownStrata = true;
    public static boolean allowSnowedStrata = true;
    public static boolean allowOvergrownVanillaVariants = true;
    public static boolean allowSnowedVanillaVariants = true;
    public static int strataXZDither = 4;
    public static int strataYDither = 6;
    public static boolean speleothemsEnabled = true;
    public static boolean speleothemsGenerate = true;
    public static boolean sandstoneSpeleothemsEnabled = true;
    public static int speleothemRarity = 1;
    public static int speleothemAttemptsPerChunk = 24;
    public static boolean speleothemsWaterloggable = false;
    public static boolean speleothemsGenerateInWater = false;
    public static boolean speleothemsGenerateInLava = false;
    public static boolean speleothemsBreakByWater = true;
    public static boolean speleothemsBreakByLava = true;
    public static boolean speleothemsDropWithoutSilkTouch = false;
    public static boolean speleothemsDripstoneDamage = false;
    public static boolean speleothemsCanFall = false;
    public static boolean speleothemsDropOnFall = false;
    public static boolean speleothemsAllowSkyView = false;
    public static boolean speleothemsAllowSkyViewEnd = false;
    public static boolean speleothemsAllowAboveGround = true;
    public static boolean speleothemsAllowPointingUpAboveGround = false;
    public static boolean replaceStoneAboveGround = false;
    public static boolean replaceSand = true;
    public static boolean replaceSandstone = true;
    public static boolean replaceGravel = true;
    public static boolean replaceRedSandAndSandstone = false;
    public static int variantChance = 70;
    public static boolean enableStructureVariantGeneration = true;
    public static WorldgenSystem worldgenSystem = WorldgenSystem.NOISE;
    public static int blobsPerChunk = 20;
    public static int blobSize = 35;
    public static int noiseScale = 16;
    public static NoiseType noiseType = NoiseType.FRACTIONAL_BROWNIAN_MOTION;
    public static int noiseOctaves = 4;
    public static boolean terracottaSpeleothemsEnabled = false;
    public static boolean iceSpeleothemEnabled = false;
    public static double fossilDropChance = 0.05;
    public static Set<String> fossilDropBlocks = Collections.emptySet();
    public static List<String> mimicSpeleothemBlocks = Collections.emptyList();
    public static Set<String> mimicSpeleothemWorldgenBlocks = Collections.emptySet();
    public static boolean masonTradesEnabled = true;
    public static boolean allowPillarVariantTrades = true;
    public static boolean stoneDropsCobblestone = true;
    public static boolean ligniteDropsLigniteCobblestone = false;
    public static boolean enableInfestedBlocks = true;
    private static final Map<VanillaOreHost, Boolean> vanillaOreHostEnabledCache = new EnumMap<>(VanillaOreHost.class);

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("worldgen");
        STONE_REPLACEMENT_ENABLED = builder
                .comment("Master toggle for stone replacement.")
                .define("stoneReplacementEnabled", true);
        WORLDGEN_SYSTEM = builder
                .comment("Worldgen system. NOISE = 3D noise field. BLOB = vanilla-style ore-vein blobs. CHUNK = patch regions.")
                .defineEnum("worldgenSystem", WorldgenSystem.NOISE);

        builder.push("shared");
        REPLACE_STONE_ABOVE_GROUND = builder
                .comment("Replace stone above sea level.")
                .define("replaceStoneAboveGround", false);
        REPLACE_SAND = builder
                .comment("Replace vanilla sand with variants.")
                .define("replaceSand", true);
        REPLACE_SANDSTONE = builder
                .comment("Replace vanilla sandstone with variants.")
                .define("replaceSandstone", true);
        REPLACE_GRAVEL = builder
                .comment("Replace vanilla gravel with variants.")
                .define("replaceGravel", true);
        REPLACE_RED_SAND_AND_SANDSTONE = builder
                .comment("Replace red sand and red sandstone with variants.")
                .define("replaceRedSandAndSandstone", false);
        REPLACE_VANILLA_ORES = builder
                .comment("Convert vanilla ores into variant ores.")
                .define("replaceVanillaOres", true);
        VARIANT_CHANCE = builder
                .comment("Chance (0-100) of variant generation per placement.")
                .defineInRange("variantChance", 70, 0, 100);
        STRATA_BOTTOM_RATIO = builder
                .comment("Y ratio below which the bottom (igneous) stratum generates.")
                .defineInRange("strataBottomRatio", 0.33D, 0.0D, 1.0D);
        STRATA_MIDDLE_RATIO = builder
                .comment("Y ratio below which the middle (metamorphic) stratum generates.")
                .defineInRange("strataMiddleRatio", 0.66D, 0.0D, 1.0D);
        IGNORE_STRATA_HEIGHT_RESTRICTIONS = builder
                .comment("Ignore strata height restrictions. When enabled, all 24 variants generate at every Y level above deepslate.")
                .define("ignoreStrataHeightRestrictions", false);
        ALLOW_STRATA_IN_DEEPSLATE = builder
                .comment("Allow strata to generate in deepslate layers.")
                .define("allowStrataInDeepslate", false);
        ALLOW_VANILLA_ORES_IN_NETHER = builder
                .comment("Allow ore generation in The Nether.")
                .define("allowVanillaOresInNether", false);
        ALLOW_VANILLA_ORES_IN_END = builder
                .comment("Allow ore generation in The End.")
                .define("allowVanillaOresInEnd", false);
        ALLOW_OBSIDIAN_ORE_VARIANTS = builder
                .comment("Allow obsidian ore variants to generate where possible.")
                .define("allowObsidianOreVariants", false);
        NETHER_ORE_ATTEMPTS_MULTIPLIER = builder
                .comment("Nether ore attempts multiplier.")
                .defineInRange("netherOreAttemptsMultiplier", 1.0D, 0.0D, 10.0D);
        END_ORE_ATTEMPTS_MULTIPLIER = builder
                .comment("End ore attempts multiplier.")
                .defineInRange("endOreAttemptsMultiplier", 1.0D, 0.0D, 10.0D);
        ALLOW_OVERGROWN_STRATA = builder
                .comment("Allow overgrown strata to generate.")
                .define("allowOvergrownStrata", true);
        ALLOW_SNOWED_STRATA = builder
                .comment("Allow snowed strata to generate.")
                .define("allowSnowedStrata", true);
        ALLOW_OVERGROWN_VANILLA_VARIANTS = builder
                .comment("Allow overgrown vanilla stone variant generation.")
                .define("allowOvergrownVanillaVariants", true);
        ALLOW_SNOWED_VANILLA_VARIANTS = builder
                .comment("Allow snowed vanilla stone variant generation.")
                .define("allowSnowedVanillaVariants", true);
        builder.pop();

        builder.push("chunk");
        PATCH_SIZE = builder
                .comment("Patch size in chunks.")
                .defineInRange("patchSize", 8, 1, 64);
        ENABLE_STRUCTURE_VARIANT_GENERATION = builder
                .comment("Replace structure blocks with variant equivalents.")
                .define("enableStructureVariantGeneration", true);
        STRATA_XZ_DITHER = builder
                .comment("Maximum horizontal dither at patch boundaries.")
                .defineInRange("xzDither", 4, 0, 32);
        STRATA_Y_DITHER = builder
                .comment("Maximum vertical dither at stratum boundaries.")
                .defineInRange("yDither", 6, 0, 64);
        builder.pop();

        builder.push("blob");
        BLOBS_PER_CHUNK = builder
                .comment("Number of variant blobs spawned per chunk.")
                .defineInRange("blobsPerChunk", 20, 0, 10000);
        BLOB_SIZE = builder
                .comment("Blob diameter in blocks. Higher values increase chunk-gen time.")
                .defineInRange("blobSize", 35, 1, 100);
        builder.pop();

        builder.push("noise");
        NOISE_SCALE = builder
                .comment("Noise feature size in blocks.")
                .defineInRange("noiseScale", 16, 1, 128);
        NOISE_TYPE = builder
                .comment("Noise function type.")
                .defineEnum("noiseType", NoiseType.FRACTIONAL_BROWNIAN_MOTION);
        NOISE_OCTAVES = builder
                .comment("Detail octaves. Higher = slower.")
                .defineInRange("octaves", 4, 1, 8);
        builder.pop();

        builder.push("variants");
        builder.push("igneous");
        for (IgneousVariant v : IgneousVariant.VALUES) {
            builder.push(v.toString());
            IGNEOUS_VARIANT_ENABLED.put(v, builder.comment("Whether this variant generates").define("enabled", true));
            IGNEOUS_VARIANT_WEIGHT.put(v, builder.comment("Relative spawn weight").defineInRange("weight", 10, 0, 1000));
            builder.pop();
        }
        builder.pop();
        builder.push("metamorphic");
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            builder.push(v.toString());
            METAMORPHIC_VARIANT_ENABLED.put(v, builder.comment("Whether this variant generates").define("enabled", true));
            METAMORPHIC_VARIANT_WEIGHT.put(v, builder.comment("Relative spawn weight").defineInRange("weight", 10, 0, 1000));
            builder.pop();
        }
        builder.pop();
        builder.push("sedimentary");
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            builder.push(v.toString());
            SEDIMENTARY_VARIANT_ENABLED.put(v, builder.comment("Whether this variant generates").define("enabled", true));
            SEDIMENTARY_VARIANT_WEIGHT.put(v, builder.comment("Relative spawn weight").defineInRange("weight", 10, 0, 1000));
            builder.pop();
        }
        builder.pop();
        builder.pop();

        builder.pop();

        builder.push("speleothems");
        SPELEOTHEMS_ENABLED = builder
                .comment("Master toggle for speleothems.")
                .define("enabled", true);
        SPELEOTHEMS_GENERATE = builder
                .comment("Allow speleothems to generate naturally.")
                .define("speleothemsGenerate", true);
        SPELEOTHEM_RARITY = builder
                .comment("Speleothem spawn rarity. Higher numbers are more rare.")
                .defineInRange("rarity", 1, 1, 256);
        SPELEOTHEM_ATTEMPTS_PER_CHUNK = builder
                .comment("Speleothem density. Default 24 = 768 attempts per chunk.")
                .defineInRange("attemptsPerChunk", 24, 1, 128);
        SPELEOTHEMS_WATERLOGGABLE = builder
                .comment("Allow speleothems to be waterlogged.")
                .define("waterloggable", false);
        SPELEOTHEMS_GENERATE_IN_WATER = builder
                .comment("Allow speleothems to spawn in water during worldgen. Forces 'waterloggable' on.")
                .define("generateInWater", false);
        SPELEOTHEMS_GENERATE_IN_LAVA = builder
                .comment("Allow speleothems to spawn in lava during worldgen.")
                .define("generateInLava", false);
        SPELEOTHEMS_BREAK_BY_WATER = builder
                .comment("Speleothems break when water touches them (ignored if waterloggable is enabled).")
                .define("breakByWater", true);
        SPELEOTHEMS_BREAK_BY_LAVA = builder
                .comment("Speleothems break when lava touches them.")
                .define("breakByLava", true);
        SPELEOTHEMS_DROP_WITHOUT_SILK_TOUCH = builder
                .comment("Speleothems drop themselves even when broken without silk touch.")
                .define("dropWithoutSilkTouch", false);
        SPELEOTHEMS_DRIPSTONE_DAMAGE = builder
                .comment("Speleothems damage entities like dripstone.")
                .define("dripstoneDamage", false);
        SPELEOTHEMS_CAN_FALL = builder
                .comment("Speleothems fall when their support is removed.")
                .define("canFall", false);
        SPELEOTHEMS_DROP_ON_FALL = builder
                .comment("A falling speleothem drops its item when it lands.")
                .define("dropOnFall", false);
        SPELEOTHEMS_ALLOW_SKY_VIEW = builder
                .comment("Allow speleothems to generate at positions that can see the sky.")
                .define("allowSkyView", false);
        SPELEOTHEMS_ALLOW_SKY_VIEW_END = builder
                .comment("Allow speleothems to generate at positions with sky view in The End.")
                .define("allowSkyViewEnd", false);
        SPELEOTHEMS_ALLOW_ABOVE_GROUND = builder
                .comment("Allow speleothems to generate above sea level.")
                .define("allowAboveGround", true);
        SPELEOTHEMS_ALLOW_POINTING_UP_ABOVE_GROUND = builder
                .comment("Allow stalagmites (pointing up) above sea level.")
                .define("allowPointingUpAboveGround", false);

        builder.push("igneous");
        for (IgneousVariant v : IgneousVariant.VALUES) {
            IGNEOUS_SPELEOTHEM.put(v, builder.define(v.toString(), true));
        }
        builder.pop();

        builder.push("metamorphic");
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            METAMORPHIC_SPELEOTHEM.put(v, builder.define(v.toString(), true));
        }
        builder.pop();

        builder.push("sedimentary");
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            SEDIMENTARY_SPELEOTHEM.put(v, builder.define(v.toString(), true));
        }
        builder.pop();

        builder.push("sandstone");
        SANDSTONE_SPELEOTHEMS_ENABLED = builder
                .comment("Enable sandstone speleothem generation.")
                .define("enabled", true);
        builder.push("igneous");
        for (IgneousVariant v : IgneousVariant.VALUES) {
            IGNEOUS_SANDSTONE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
        }
        builder.pop();
        builder.push("metamorphic");
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            METAMORPHIC_SANDSTONE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
        }
        builder.pop();
        builder.push("sedimentary");
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            SEDIMENTARY_SANDSTONE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
        }
        builder.pop();
        builder.pop();

        builder.push("minecraft");
        for (VanillaSpeleothemVariant v : VanillaSpeleothemVariant.VALUES) {
            VANILLA_SPELEOTHEM.put(v, builder.define(v.toString(), true));
        }
        builder.pop();

        builder.push("modded");
        if (ModList.get().isLoaded("quark")) {
            builder.push("quark");
            for (QuarkSpeleothemVariant v : QuarkSpeleothemVariant.VALUES) {
                QUARK_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("undergarden")) {
            builder.push("undergarden");
            for (sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant v : sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.VALUES) {
                UNDERGARDEN_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("create")) {
            builder.push("create");
            for (sxilverr.worldofstone.api.enums.CreateSpeleothemVariant v : sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.VALUES) {
                CREATE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("betterend")) {
            builder.push("betterend");
            for (sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant v : sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.VALUES) {
                BETTEREND_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("galosphere")) {
            builder.push("galosphere");
            for (sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant v : sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.VALUES) {
                GALOSPHERE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("biomeswevegone")) {
            builder.push("biomeswevegone");
            for (sxilverr.worldofstone.api.enums.BwgSpeleothemVariant v : sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.VALUES) {
                BWG_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("twilightforest")) {
            builder.push("twilightforest");
            for (sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant v : sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.VALUES) {
                TWILIGHTFOREST_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("aether")) {
            builder.push("aether");
            for (sxilverr.worldofstone.api.enums.AetherSpeleothemVariant v : sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.VALUES) {
                AETHER_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("blue_skies")) {
            builder.push("blue_skies");
            for (sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant v : sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.VALUES) {
                BLUE_SKIES_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("spelunkery")) {
            builder.push("spelunkery");
            for (sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant v : sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.VALUES) {
                SPELUNKERY_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("iceandfire")) {
            builder.push("iceandfire");
            for (sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v : sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.VALUES) {
                ICEANDFIRE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("mysticalagriculture")) {
            builder.push("mysticalagriculture");
            for (sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v : sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.VALUES) {
                MYSTICALAGRICULTURE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("biomesoplenty")) {
            builder.push("biomesoplenty");
            for (sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant v : sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.VALUES) {
                BIOMESOPLENTY_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("forbidden_arcanus")) {
            builder.push("forbidden_arcanus");
            for (sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant v : sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.VALUES) {
                FORBIDDEN_ARCANUS_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("alexscaves")) {
            builder.push("alexscaves");
            for (sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant v : sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.VALUES) {
                ALEXSCAVES_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("ars_nouveau")) {
            builder.push("ars_nouveau");
            for (sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v : sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.VALUES) {
                ARS_NOUVEAU_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("cataclysm")) {
            builder.push("cataclysm");
            for (sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v : sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.VALUES) {
                CATACLYSM_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("twigs")) {
            builder.push("twigs");
            for (sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant v : sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.VALUES) {
                TWIGS_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("architects_palette")) {
            builder.push("architects_palette");
            for (sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant v : sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.VALUES) {
                ARCHITECTS_PALETTE_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("outer_end")) {
            builder.push("outer_end");
            for (sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant v : sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.VALUES) {
                OUTER_END_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("botania")) {
            builder.push("botania");
            for (sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant v : sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.VALUES) {
                BOTANIA_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("ad_astra")) {
            builder.push("ad_astra");
            for (sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant v : sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.VALUES) {
                AD_ASTRA_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("deep_aether")) {
            builder.push("deep_aether");
            for (sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant v : sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.VALUES) {
                DEEP_AETHER_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("caverns_and_chasms")) {
            builder.push("caverns_and_chasms");
            for (sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant v : sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.VALUES) {
                CAVERNS_AND_CHASMS_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("atmospheric")) {
            builder.push("atmospheric");
            for (sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant v : sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.VALUES) {
                ATMOSPHERIC_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("endergetic")) {
            builder.push("endergetic");
            for (sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v : sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.VALUES) {
                ENDERGETIC_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("wilder_wilds")) {
            builder.push("wilder_wilds");
            for (sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant v : sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.VALUES) {
                WILDER_WILDS_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("regions_unexplored")) {
            builder.push("regions_unexplored");
            for (sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant v : sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.VALUES) {
                REGIONS_UNEXPLORED_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("born_in_chaos_v1")) {
            builder.push("born_in_chaos_v1");
            for (sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v : sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.VALUES) {
                BORN_IN_CHAOS_V1_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("naturalist")) {
            builder.push("naturalist");
            for (sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v : sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.VALUES) {
                NATURALIST_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("yungscavebiomes")) {
            builder.push("yungscavebiomes");
            for (sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v : sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.VALUES) {
                YUNGSCAVEBIOMES_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("natures_spirit")) {
            builder.push("natures_spirit");
            for (sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant v : sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.VALUES) {
                NATURES_SPIRIT_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("netherexp")) {
            builder.push("netherexp");
            for (sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant v : sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.VALUES) {
                NETHEREXP_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("deeperdarker")) {
            builder.push("deeperdarker");
            for (sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant v : sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.VALUES) {
                DEEPERDARKER_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("the_deep_void")) {
            builder.push("the_deep_void");
            for (sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant v : sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.VALUES) {
                THE_DEEP_VOID_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        if (ModList.get().isLoaded("defiled_lands_preborn")) {
            builder.push("defiled_lands_preborn");
            for (sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v : sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.VALUES) {
                DEFILED_LANDS_PREBORN_SPELEOTHEM.put(v, builder.define(v.toString(), true));
            }
            builder.pop();
        }
        builder.pop();

        ICE_SPELEOTHEM_ENABLED = builder
                .comment("Enable generation of Ice, Packed Ice, and Blue Ice speleothems.")
                .define("iceSpeleothemEnabled", false);

        TERRACOTTA_SPELEOTHEMS_ENABLED = builder
                .comment("Toggle for terracotta speleothem generation.")
                .define("terracottaEnabled", false);
        builder.push("terracotta");
        for (TerracottaSpeleothemVariant v : TerracottaSpeleothemVariant.VALUES) {
            if (v.isNaturalGeneration()) {
                TERRACOTTA_SPELEOTHEM_ENABLED_MAP.put(v, builder.define(v.toString(), true));
            }
        }
        builder.pop();

        builder.pop();

        builder.push("fossils");
        FOSSIL_DROP_CHANCE = builder
                .comment("Chance of a fossil dropping when a defined block is broken without silk touch")
                .defineInRange("dropChance", 0.05, 0.0, 1.0);
        FOSSIL_DROP_BLOCKS = builder
                .comment("Block IDs that have a chance to drop fossils when broken.")
                .defineListAllowEmpty("extraBlocks", List.of(),
                        obj -> obj instanceof String);
        builder.pop();

        builder.push("mimicSpeleothems");
        MIMIC_SPELEOTHEM_BLOCKS = builder
                .comment("Block IDs to show as mimic speleothem presets. Each entry creates a creative-tab item that places a mimic speleothem mimicking that block.")
                .defineListAllowEmpty("mimicBlocks", List.of(), obj -> obj instanceof String);
        MIMIC_SPELEOTHEM_WORLDGEN_BLOCKS = builder
                .comment("mimicBlocks that should generate naturally as speleothems on their host block. must also be in mimicBlocks.")
                .defineListAllowEmpty("worldgenBlocks", List.of(), obj -> obj instanceof String);
        builder.pop();

        builder.push("villager");
        MASON_TRADES_ENABLED = builder
                .comment("Add World of Stone variants to Mason villager trades.")
                .define("masonTradesEnabled", true);
        ALLOW_PILLAR_VARIANT_TRADES = builder
                .comment("Allow pillar variant trades.")
                .define("allowPillarVariantTrades", true);
        builder.pop();

        builder.push("gameplay");
        STONE_DROPS_COBBLESTONE = builder
                .comment("Strata stones drop cobblestone unless mined with silk touch.")
                .define("stoneDropsCobblestone", true);
        LIGNITE_DROPS_LIGNITE_COBBLESTONE = builder
                .comment("Lignite drops lignite cobblestone instead of lignite coal.")
                .define("ligniteDropsLigniteCobblestone", false);
        ENABLE_INFESTED_BLOCKS = builder
                .comment("Enable infested block variants.")
                .define("enableInfestedBlocks", true);
        builder.pop();

        builder.push("vanillaStoneOres");
        for (VanillaOreHost host : VanillaOreHost.VALUES) {
            VANILLA_ORE_HOST_ENABLED.put(host, builder.define(host.getRegistryName(), true));
        }
        builder.pop();

        COMMON_SPEC = builder.build();
    }

    public static boolean isVariantEnabled(IgneousVariant v) {
        return IGNEOUS_VARIANT_ENABLED.get(v).get();
    }

    public static boolean isVariantEnabled(MetamorphicVariant v) {
        return METAMORPHIC_VARIANT_ENABLED.get(v).get();
    }

    public static boolean isVariantEnabled(SedimentaryVariant v) {
        return SEDIMENTARY_VARIANT_ENABLED.get(v).get();
    }

    public static boolean isSpeleothemEnabled(IgneousVariant v) {
        return speleothemsEnabled && IGNEOUS_SPELEOTHEM.get(v).get();
    }

    public static boolean isSpeleothemEnabled(MetamorphicVariant v) {
        return speleothemsEnabled && METAMORPHIC_SPELEOTHEM.get(v).get();
    }

    public static boolean isSpeleothemEnabled(SedimentaryVariant v) {
        return speleothemsEnabled && SEDIMENTARY_SPELEOTHEM.get(v).get();
    }

    public static boolean isSandstoneSpeleothemEnabled(IgneousVariant v) {
        if (!speleothemsEnabled || !sandstoneSpeleothemsEnabled) return false;
        ForgeConfigSpec.BooleanValue val = IGNEOUS_SANDSTONE_SPELEOTHEM.get(v);
        return val != null && val.get();
    }

    public static boolean isSandstoneSpeleothemEnabled(MetamorphicVariant v) {
        if (!speleothemsEnabled || !sandstoneSpeleothemsEnabled) return false;
        ForgeConfigSpec.BooleanValue val = METAMORPHIC_SANDSTONE_SPELEOTHEM.get(v);
        return val != null && val.get();
    }

    public static boolean isSandstoneSpeleothemEnabled(SedimentaryVariant v) {
        if (!speleothemsEnabled || !sandstoneSpeleothemsEnabled) return false;
        ForgeConfigSpec.BooleanValue val = SEDIMENTARY_SANDSTONE_SPELEOTHEM.get(v);
        return val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(VanillaSpeleothemVariant v) {
        if (!speleothemsEnabled) return false;
        if ((v == VanillaSpeleothemVariant.SANDSTONE || v == VanillaSpeleothemVariant.RED_SANDSTONE)
                && !sandstoneSpeleothemsEnabled) return false;
        return VANILLA_SPELEOTHEM.get(v).get();
    }

    public static boolean isSpeleothemEnabled(QuarkSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = QUARK_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = UNDERGARDEN_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.CreateSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = CREATE_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = BETTEREND_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = GALOSPHERE_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.BwgSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = BWG_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = TWILIGHTFOREST_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.AetherSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = AETHER_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = BLUE_SKIES_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = SPELUNKERY_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = ICEANDFIRE_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = MYSTICALAGRICULTURE_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = BIOMESOPLENTY_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = FORBIDDEN_ARCANUS_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = ALEXSCAVES_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = ARS_NOUVEAU_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = CATACLYSM_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = TWIGS_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = ARCHITECTS_PALETTE_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = OUTER_END_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = BOTANIA_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = AD_ASTRA_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = DEEP_AETHER_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = CAVERNS_AND_CHASMS_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = ATMOSPHERIC_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = ENDERGETIC_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = WILDER_WILDS_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = REGIONS_UNEXPLORED_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = BORN_IN_CHAOS_V1_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = NATURALIST_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = YUNGSCAVEBIOMES_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = NATURES_SPIRIT_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = NETHEREXP_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = DEEPERDARKER_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = THE_DEEP_VOID_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v) {
        ForgeConfigSpec.BooleanValue val = DEFILED_LANDS_PREBORN_SPELEOTHEM.get(v);
        return speleothemsEnabled && val != null && val.get();
    }

    public static boolean isSpeleothemEnabled(TerracottaSpeleothemVariant v) {
        if (!speleothemsEnabled || !terracottaSpeleothemsEnabled) return false;
        if (!v.isNaturalGeneration()) return false;
        ForgeConfigSpec.BooleanValue val = TERRACOTTA_SPELEOTHEM_ENABLED_MAP.get(v);
        return val == null || val.get();
    }

    @SubscribeEvent
    static void onLoad(ModConfigEvent.Loading event) {
        sync();
    }

    @SubscribeEvent
    static void onReload(ModConfigEvent.Reloading event) {
        sync();
    }

    public static int getVariantWeight(IgneousVariant v) {
        ForgeConfigSpec.IntValue val = IGNEOUS_VARIANT_WEIGHT.get(v);
        return val == null ? 10 : val.get();
    }

    public static int getVariantWeight(MetamorphicVariant v) {
        ForgeConfigSpec.IntValue val = METAMORPHIC_VARIANT_WEIGHT.get(v);
        return val == null ? 10 : val.get();
    }

    public static int getVariantWeight(SedimentaryVariant v) {
        ForgeConfigSpec.IntValue val = SEDIMENTARY_VARIANT_WEIGHT.get(v);
        return val == null ? 10 : val.get();
    }

    private static void sync() {
        stoneReplacementEnabled = STONE_REPLACEMENT_ENABLED.get();
        patchSize = PATCH_SIZE.get();
        replaceVanillaOres = REPLACE_VANILLA_ORES.get();
        strataBottomRatio = STRATA_BOTTOM_RATIO.get();
        strataMiddleRatio = STRATA_MIDDLE_RATIO.get();
        strataXZDither = STRATA_XZ_DITHER.get();
        strataYDither = STRATA_Y_DITHER.get();
        speleothemsEnabled = SPELEOTHEMS_ENABLED.get();
        speleothemsGenerate = SPELEOTHEMS_GENERATE.get();
        sandstoneSpeleothemsEnabled = SANDSTONE_SPELEOTHEMS_ENABLED.get();
        speleothemRarity = SPELEOTHEM_RARITY.get();
        speleothemAttemptsPerChunk = SPELEOTHEM_ATTEMPTS_PER_CHUNK.get();
        speleothemsGenerateInWater = SPELEOTHEMS_GENERATE_IN_WATER.get();
        speleothemsWaterloggable = SPELEOTHEMS_WATERLOGGABLE.get() || speleothemsGenerateInWater;
        speleothemsGenerateInLava = SPELEOTHEMS_GENERATE_IN_LAVA.get();
        speleothemsBreakByWater = SPELEOTHEMS_BREAK_BY_WATER.get();
        speleothemsBreakByLava = SPELEOTHEMS_BREAK_BY_LAVA.get();
        speleothemsDropWithoutSilkTouch = SPELEOTHEMS_DROP_WITHOUT_SILK_TOUCH.get();
        speleothemsDripstoneDamage = SPELEOTHEMS_DRIPSTONE_DAMAGE.get();
        speleothemsCanFall = SPELEOTHEMS_CAN_FALL.get();
        speleothemsDropOnFall = SPELEOTHEMS_DROP_ON_FALL.get();
        speleothemsAllowSkyView = SPELEOTHEMS_ALLOW_SKY_VIEW.get();
        speleothemsAllowSkyViewEnd = SPELEOTHEMS_ALLOW_SKY_VIEW_END.get();
        speleothemsAllowAboveGround = SPELEOTHEMS_ALLOW_ABOVE_GROUND.get();
        speleothemsAllowPointingUpAboveGround = SPELEOTHEMS_ALLOW_POINTING_UP_ABOVE_GROUND.get();
        replaceStoneAboveGround = REPLACE_STONE_ABOVE_GROUND.get();
        ignoreStrataHeightRestrictions = IGNORE_STRATA_HEIGHT_RESTRICTIONS.get();
        allowStrataInDeepslate = ALLOW_STRATA_IN_DEEPSLATE.get();
        allowVanillaOresInNether = ALLOW_VANILLA_ORES_IN_NETHER.get();
        allowVanillaOresInEnd = ALLOW_VANILLA_ORES_IN_END.get();
        allowObsidianOreVariants = ALLOW_OBSIDIAN_ORE_VARIANTS.get();
        netherOreAttemptsMultiplier = NETHER_ORE_ATTEMPTS_MULTIPLIER.get();
        endOreAttemptsMultiplier = END_ORE_ATTEMPTS_MULTIPLIER.get();
        allowOvergrownStrata = ALLOW_OVERGROWN_STRATA.get();
        allowSnowedStrata = ALLOW_SNOWED_STRATA.get();
        allowOvergrownVanillaVariants = ALLOW_OVERGROWN_VANILLA_VARIANTS.get();
        allowSnowedVanillaVariants = ALLOW_SNOWED_VANILLA_VARIANTS.get();
        replaceSand = REPLACE_SAND.get();
        replaceSandstone = REPLACE_SANDSTONE.get();
        replaceGravel = REPLACE_GRAVEL.get();
        replaceRedSandAndSandstone = REPLACE_RED_SAND_AND_SANDSTONE.get();
        variantChance = VARIANT_CHANCE.get();
        enableStructureVariantGeneration = ENABLE_STRUCTURE_VARIANT_GENERATION.get();
        worldgenSystem = WORLDGEN_SYSTEM.get();
        blobsPerChunk = BLOBS_PER_CHUNK.get();
        blobSize = BLOB_SIZE.get();
        noiseScale = NOISE_SCALE.get();
        noiseType = NOISE_TYPE.get();
        noiseOctaves = NOISE_OCTAVES.get();
        terracottaSpeleothemsEnabled = TERRACOTTA_SPELEOTHEMS_ENABLED.get();
        iceSpeleothemEnabled = ICE_SPELEOTHEM_ENABLED.get();
        fossilDropChance = FOSSIL_DROP_CHANCE.get();
        fossilDropBlocks = new HashSet<>(FOSSIL_DROP_BLOCKS.get());
        mimicSpeleothemBlocks = new java.util.ArrayList<>(MIMIC_SPELEOTHEM_BLOCKS.get());
        mimicSpeleothemWorldgenBlocks = new HashSet<>(MIMIC_SPELEOTHEM_WORLDGEN_BLOCKS.get());
        masonTradesEnabled = MASON_TRADES_ENABLED.get();
        allowPillarVariantTrades = ALLOW_PILLAR_VARIANT_TRADES.get();
        stoneDropsCobblestone = STONE_DROPS_COBBLESTONE.get();
        ligniteDropsLigniteCobblestone = LIGNITE_DROPS_LIGNITE_COBBLESTONE.get();
        enableInfestedBlocks = ENABLE_INFESTED_BLOCKS.get();
        vanillaOreHostEnabledCache.clear();
        for (VanillaOreHost host : VanillaOreHost.VALUES) {
            vanillaOreHostEnabledCache.put(host, VANILLA_ORE_HOST_ENABLED.get(host).get());
        }
    }

    public static boolean isVanillaOreHostEnabled(VanillaOreHost host) {
        Boolean v = vanillaOreHostEnabledCache.get(host);
        return v == null || v;
    }

    private WosConfig() {
    }
}
