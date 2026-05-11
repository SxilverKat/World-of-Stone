package sxilverr.worldofstone.registry;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.world.BlobReplacerFeature;
import sxilverr.worldofstone.world.NoiseReplacerFeature;
import sxilverr.worldofstone.world.OtherDimensionOreFeature;
import sxilverr.worldofstone.world.SpeleothemFeature;
import sxilverr.worldofstone.world.StrataReplacerFeature;
import sxilverr.worldofstone.world.VanillaHostSurfaceFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class WosFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ModInfo.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> STRATA_REPLACER =
            FEATURES.register("strata_replacer", StrataReplacerFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SPELEOTHEMS =
            FEATURES.register("speleothems", SpeleothemFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BLOB_REPLACER =
            FEATURES.register("blob_replacer", BlobReplacerFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> NOISE_REPLACER =
            FEATURES.register("noise_replacer", NoiseReplacerFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> OTHER_DIMENSION_ORES =
            FEATURES.register("other_dimension_ores", OtherDimensionOreFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> VANILLA_HOST_SURFACE =
            FEATURES.register("vanilla_host_surface", VanillaHostSurfaceFeature::new);

    public static void init() {
    }

    private WosFeatures() {
    }
}
