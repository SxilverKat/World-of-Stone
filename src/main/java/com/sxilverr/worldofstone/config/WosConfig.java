package com.sxilverr.worldofstone.config;

import com.sxilverr.worldofstone.api.enums.IgneousVariant;
import com.sxilverr.worldofstone.api.enums.MetamorphicVariant;
import com.sxilverr.worldofstone.api.enums.NoiseType;
import com.sxilverr.worldofstone.api.enums.QuarkSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.SedimentaryVariant;
import com.sxilverr.worldofstone.api.enums.TerracottaSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.VanillaOreHost;
import com.sxilverr.worldofstone.api.enums.VanillaSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.WorldgenSystem;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class WosConfig {

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
    public static boolean replaceClay = true;
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
    public static boolean creativeSpeleothemsEnabled = true;
    public static boolean fossilsEnabled = true;
    public static double fossilDropChance = 0.05;
    public static Set<String> fossilDropBlocks = Collections.emptySet();
    public static List<String> mimicSpeleothemBlocks = Collections.emptyList();
    public static Set<String> mimicSpeleothemWorldgenBlocks = Collections.emptySet();
    public static boolean masonTradesEnabled = true;
    public static boolean allowPillarVariantTrades = true;
    public static boolean stoneDropsCobblestone = true;
    public static boolean ligniteDropsLigniteCobblestone = false;
    public static boolean enableInfestedBlocks = true;

    public static final Map<IgneousVariant, Boolean> IGNEOUS_VARIANT_ENABLED = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, Boolean> METAMORPHIC_VARIANT_ENABLED = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, Boolean> SEDIMENTARY_VARIANT_ENABLED = new EnumMap<>(SedimentaryVariant.class);

    public static final Map<IgneousVariant, Integer> IGNEOUS_VARIANT_WEIGHT = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, Integer> METAMORPHIC_VARIANT_WEIGHT = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, Integer> SEDIMENTARY_VARIANT_WEIGHT = new EnumMap<>(SedimentaryVariant.class);

    public static final Map<IgneousVariant, Boolean> IGNEOUS_SPELEOTHEM = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, Boolean> METAMORPHIC_SPELEOTHEM = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, Boolean> SEDIMENTARY_SPELEOTHEM = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<IgneousVariant, Boolean> IGNEOUS_SANDSTONE_SPELEOTHEM = new EnumMap<>(IgneousVariant.class);
    public static final Map<MetamorphicVariant, Boolean> METAMORPHIC_SANDSTONE_SPELEOTHEM = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<SedimentaryVariant, Boolean> SEDIMENTARY_SANDSTONE_SPELEOTHEM = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<VanillaSpeleothemVariant, Boolean> VANILLA_SPELEOTHEM = new EnumMap<>(VanillaSpeleothemVariant.class);
    public static final Map<QuarkSpeleothemVariant, Boolean> QUARK_SPELEOTHEM = new EnumMap<>(QuarkSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant, Boolean> UNDERGARDEN_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant, Boolean> CREATE_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant, Boolean> BETTEREND_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant, Boolean> GALOSPHERE_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant, Boolean> BWG_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant, Boolean> TWILIGHTFOREST_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant, Boolean> AETHER_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant, Boolean> BLUE_SKIES_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant, Boolean> SPELUNKERY_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant, Boolean> ICEANDFIRE_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant, Boolean> MYSTICALAGRICULTURE_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant, Boolean> BIOMESOPLENTY_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant, Boolean> FORBIDDEN_ARCANUS_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant, Boolean> ALEXSCAVES_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant, Boolean> ARS_NOUVEAU_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant, Boolean> CATACLYSM_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant, Boolean> TWIGS_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant, Boolean> ARCHITECTS_PALETTE_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant, Boolean> OUTER_END_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant, Boolean> BOTANIA_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant, Boolean> AD_ASTRA_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant, Boolean> DEEP_AETHER_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant, Boolean> CAVERNS_AND_CHASMS_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant, Boolean> ATMOSPHERIC_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant, Boolean> ENDERGETIC_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant, Boolean> WILDER_WILDS_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant, Boolean> REGIONS_UNEXPLORED_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant, Boolean> BORN_IN_CHAOS_V1_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant, Boolean> NATURALIST_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant, Boolean> YUNGSCAVEBIOMES_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant, Boolean> NATURES_SPIRIT_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant, Boolean> NETHEREXP_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant, Boolean> DEEPERDARKER_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant, Boolean> THE_DEEP_VOID_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.class);
    public static final Map<com.sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant, Boolean> DEFILED_LANDS_PREBORN_SPELEOTHEM = new EnumMap<>(com.sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.class);
    public static final Map<TerracottaSpeleothemVariant, Boolean> TERRACOTTA_SPELEOTHEM_ENABLED_MAP = new EnumMap<>(TerracottaSpeleothemVariant.class);
    public static final Map<VanillaOreHost, Boolean> VANILLA_ORE_HOST_ENABLED = new EnumMap<>(VanillaOreHost.class);

    public static boolean isVariantEnabled(IgneousVariant v) {
        return IGNEOUS_VARIANT_ENABLED.getOrDefault(v, true);
    }

    public static boolean isVariantEnabled(MetamorphicVariant v) {
        return METAMORPHIC_VARIANT_ENABLED.getOrDefault(v, true);
    }

    public static boolean isVariantEnabled(SedimentaryVariant v) {
        return SEDIMENTARY_VARIANT_ENABLED.getOrDefault(v, true);
    }

    public static boolean isSpeleothemEnabled(IgneousVariant v) {
        return speleothemsEnabled && IGNEOUS_SPELEOTHEM.getOrDefault(v, true);
    }

    public static boolean isSpeleothemEnabled(MetamorphicVariant v) {
        return speleothemsEnabled && METAMORPHIC_SPELEOTHEM.getOrDefault(v, true);
    }

    public static boolean isSpeleothemEnabled(SedimentaryVariant v) {
        return speleothemsEnabled && SEDIMENTARY_SPELEOTHEM.getOrDefault(v, true);
    }

    public static boolean isSandstoneSpeleothemEnabled(IgneousVariant v) {
        if (!speleothemsEnabled || !sandstoneSpeleothemsEnabled) return false;
        return IGNEOUS_SANDSTONE_SPELEOTHEM.getOrDefault(v, true);
    }

    public static boolean isSandstoneSpeleothemEnabled(MetamorphicVariant v) {
        if (!speleothemsEnabled || !sandstoneSpeleothemsEnabled) return false;
        return METAMORPHIC_SANDSTONE_SPELEOTHEM.getOrDefault(v, true);
    }

    public static boolean isSandstoneSpeleothemEnabled(SedimentaryVariant v) {
        if (!speleothemsEnabled || !sandstoneSpeleothemsEnabled) return false;
        return SEDIMENTARY_SANDSTONE_SPELEOTHEM.getOrDefault(v, true);
    }

    public static boolean isSpeleothemEnabled(VanillaSpeleothemVariant v) {
        if (!speleothemsEnabled) return false;
        if ((v == VanillaSpeleothemVariant.SANDSTONE || v == VanillaSpeleothemVariant.RED_SANDSTONE)
                && !sandstoneSpeleothemsEnabled) return false;
        return VANILLA_SPELEOTHEM.getOrDefault(v, true);
    }

    public static boolean isSpeleothemEnabled(QuarkSpeleothemVariant v) {
        return speleothemsEnabled && QUARK_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant v) {
        return speleothemsEnabled && UNDERGARDEN_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant v) {
        return speleothemsEnabled && CREATE_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant v) {
        return speleothemsEnabled && BETTEREND_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant v) {
        return speleothemsEnabled && GALOSPHERE_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant v) {
        return speleothemsEnabled && BWG_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant v) {
        return speleothemsEnabled && TWILIGHTFOREST_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant v) {
        return speleothemsEnabled && AETHER_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant v) {
        return speleothemsEnabled && BLUE_SKIES_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant v) {
        return speleothemsEnabled && SPELUNKERY_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v) {
        return speleothemsEnabled && ICEANDFIRE_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v) {
        return speleothemsEnabled && MYSTICALAGRICULTURE_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant v) {
        return speleothemsEnabled && BIOMESOPLENTY_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant v) {
        return speleothemsEnabled && FORBIDDEN_ARCANUS_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant v) {
        return speleothemsEnabled && ALEXSCAVES_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v) {
        return speleothemsEnabled && ARS_NOUVEAU_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v) {
        return speleothemsEnabled && CATACLYSM_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant v) {
        return speleothemsEnabled && TWIGS_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant v) {
        return speleothemsEnabled && ARCHITECTS_PALETTE_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant v) {
        return speleothemsEnabled && OUTER_END_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant v) {
        return speleothemsEnabled && BOTANIA_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant v) {
        return speleothemsEnabled && AD_ASTRA_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant v) {
        return speleothemsEnabled && DEEP_AETHER_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant v) {
        return speleothemsEnabled && CAVERNS_AND_CHASMS_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant v) {
        return speleothemsEnabled && ATMOSPHERIC_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v) {
        return speleothemsEnabled && ENDERGETIC_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant v) {
        return speleothemsEnabled && WILDER_WILDS_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant v) {
        return speleothemsEnabled && REGIONS_UNEXPLORED_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v) {
        return speleothemsEnabled && BORN_IN_CHAOS_V1_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v) {
        return speleothemsEnabled && NATURALIST_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v) {
        return speleothemsEnabled && YUNGSCAVEBIOMES_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant v) {
        return speleothemsEnabled && NATURES_SPIRIT_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant v) {
        return speleothemsEnabled && NETHEREXP_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant v) {
        return speleothemsEnabled && DEEPERDARKER_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant v) {
        return speleothemsEnabled && THE_DEEP_VOID_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v) {
        return speleothemsEnabled && DEFILED_LANDS_PREBORN_SPELEOTHEM.getOrDefault(v, false);
    }

    public static boolean isSpeleothemEnabled(TerracottaSpeleothemVariant v) {
        if (!speleothemsEnabled || !terracottaSpeleothemsEnabled) return false;
        if (!v.isNaturalGeneration()) return false;
        return TERRACOTTA_SPELEOTHEM_ENABLED_MAP.getOrDefault(v, true);
    }

    public static boolean isFossilsEnabled() {
        return fossilsEnabled;
    }

    public static boolean isCreativeSpeleothemEnabled(com.sxilverr.worldofstone.api.enums.DecorativeSpeleothemVariant v) {
        if (!speleothemsEnabled) return false;
        switch (v) {
            case ICE:
            case PACKED_ICE:
            case BLUE_ICE:
                return iceSpeleothemEnabled;
            default:
                return creativeSpeleothemsEnabled;
        }
    }

    private static volatile Set<String> strataVariantBases;

    private static Set<String> strataVariantBases() {
        Set<String> cached = strataVariantBases;
        if (cached == null) {
            cached = new HashSet<>();
            for (IgneousVariant v : IgneousVariant.VALUES) cached.add(v.toString());
            for (MetamorphicVariant v : MetamorphicVariant.VALUES) cached.add(v.toString());
            for (SedimentaryVariant v : SedimentaryVariant.VALUES) cached.add(v.toString());
            strataVariantBases = cached;
        }
        return cached;
    }

    public static boolean isStrataVariantPath(String path) {
        for (String base : strataVariantBases()) {
            if (path.contains(base)) return true;
        }
        return false;
    }

    public static int getVariantWeight(IgneousVariant v) {
        return IGNEOUS_VARIANT_WEIGHT.getOrDefault(v, 10);
    }

    public static int getVariantWeight(MetamorphicVariant v) {
        return METAMORPHIC_VARIANT_WEIGHT.getOrDefault(v, 10);
    }

    public static int getVariantWeight(SedimentaryVariant v) {
        return SEDIMENTARY_VARIANT_WEIGHT.getOrDefault(v, 10);
    }

    public static boolean isVanillaOreHostEnabled(VanillaOreHost host) {
        return VANILLA_ORE_HOST_ENABLED.getOrDefault(host, true);
    }

    private WosConfig() {
    }
}
