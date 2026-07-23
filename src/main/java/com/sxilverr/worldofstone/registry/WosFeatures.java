package com.sxilverr.worldofstone.registry;

import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.world.BlobReplacerFeature;
import com.sxilverr.worldofstone.world.NoiseReplacerFeature;
import com.sxilverr.worldofstone.world.OtherDimensionOreFeature;
import com.sxilverr.worldofstone.world.SpeleothemFeature;
import com.sxilverr.worldofstone.world.StrataReplacerFeature;
import com.sxilverr.worldofstone.world.VanillaHostSurfaceFeature;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class WosFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ModInfo.MODID, Registries.FEATURE);

    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> STRATA_REPLACER =
            FEATURES.register("strata_replacer", StrataReplacerFeature::new);

    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> SPELEOTHEMS =
            FEATURES.register("speleothems", SpeleothemFeature::new);

    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> BLOB_REPLACER =
            FEATURES.register("blob_replacer", BlobReplacerFeature::new);

    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> NOISE_REPLACER =
            FEATURES.register("noise_replacer", NoiseReplacerFeature::new);

    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> OTHER_DIMENSION_ORES =
            FEATURES.register("other_dimension_ores", OtherDimensionOreFeature::new);

    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> VANILLA_HOST_SURFACE =
            FEATURES.register("vanilla_host_surface", VanillaHostSurfaceFeature::new);

    public static void init() {
    }

    private WosFeatures() {
    }
}
