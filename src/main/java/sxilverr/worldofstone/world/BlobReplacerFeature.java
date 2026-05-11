package sxilverr.worldofstone.world;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.IgneousVariant;
import sxilverr.worldofstone.api.enums.MetamorphicVariant;
import sxilverr.worldofstone.api.enums.OreVariant;
import sxilverr.worldofstone.api.enums.SedimentaryVariant;
import sxilverr.worldofstone.api.enums.WorldgenSystem;
import sxilverr.worldofstone.config.WosConfig;
import sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class BlobReplacerFeature extends Feature<NoneFeatureConfiguration> {

    private static final Map<Block, OreVariant> VANILLA_ORE_TO_VARIANT = new HashMap<>();
    private static final java.util.HashSet<Block> DEEPSLATE_ORES = new java.util.HashSet<>();

    static {
        VANILLA_ORE_TO_VARIANT.put(Blocks.COAL_ORE, OreVariant.COAL);
        VANILLA_ORE_TO_VARIANT.put(Blocks.IRON_ORE, OreVariant.IRON);
        VANILLA_ORE_TO_VARIANT.put(Blocks.GOLD_ORE, OreVariant.GOLD);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DIAMOND_ORE, OreVariant.DIAMOND);
        VANILLA_ORE_TO_VARIANT.put(Blocks.EMERALD_ORE, OreVariant.EMERALD);
        VANILLA_ORE_TO_VARIANT.put(Blocks.REDSTONE_ORE, OreVariant.REDSTONE);
        VANILLA_ORE_TO_VARIANT.put(Blocks.LAPIS_ORE, OreVariant.LAPIS);
        VANILLA_ORE_TO_VARIANT.put(Blocks.COPPER_ORE, OreVariant.COPPER);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_COAL_ORE, OreVariant.COAL);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_IRON_ORE, OreVariant.IRON);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_GOLD_ORE, OreVariant.GOLD);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_DIAMOND_ORE, OreVariant.DIAMOND);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_EMERALD_ORE, OreVariant.EMERALD);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_REDSTONE_ORE, OreVariant.REDSTONE);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_LAPIS_ORE, OreVariant.LAPIS);
        VANILLA_ORE_TO_VARIANT.put(Blocks.DEEPSLATE_COPPER_ORE, OreVariant.COPPER);

        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_COAL_ORE);
        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_IRON_ORE);
        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_GOLD_ORE);
        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_DIAMOND_ORE);
        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_EMERALD_ORE);
        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_REDSTONE_ORE);
        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_LAPIS_ORE);
        DEEPSLATE_ORES.add(Blocks.DEEPSLATE_COPPER_ORE);
    }

    public BlobReplacerFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        if (!WosConfig.stoneReplacementEnabled) return false;
        if (WosConfig.worldgenSystem != WorldgenSystem.BLOB) return false;

        WorldGenLevel level = ctx.level();
        RandomSource rand = ctx.random();
        ChunkAccess chunk = level.getChunk(ctx.origin());
        int chunkX = chunk.getPos().getMinBlockX();
        int chunkZ = chunk.getPos().getMinBlockZ();

        int minY = level.getMinBuildHeight();
        int seaLevel = level.getSeaLevel();
        int range = seaLevel - minY;
        if (range <= 0) return false;

        int blobsPerChunk = Math.max(0, WosConfig.blobsPerChunk);
        int blobSize = Math.max(1, WosConfig.blobSize);
        int maxYReplaced = WosConfig.replaceStoneAboveGround ? level.getMaxBuildHeight() : seaLevel;
        int yScanRange = Math.max(1, maxYReplaced - minY);

        double bottomRatio = WosConfig.strataBottomRatio;
        double middleRatio = Math.max(bottomRatio, WosConfig.strataMiddleRatio);

        int variantChance = Math.max(0, Math.min(100, WosConfig.variantChance));
        for (int i = 0; i < blobsPerChunk; i++) {
            if (variantChance < 100 && rand.nextInt(100) >= variantChance) continue;
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            int y = minY + rand.nextInt(yScanRange);

            String variantName = pickVariantForY(y, minY, range, bottomRatio, middleRatio, rand);
            if (variantName == null) continue;
            placeBlob(level, new BlockPos(x, y, z), blobSize, variantName, rand);
        }
        return true;
    }

    private String pickVariantForY(int y, int minY, int range, double bottomRatio, double middleRatio, RandomSource rand) {
        if (WosConfig.ignoreStrataHeightRestrictions) {
            return pickAnyWeighted(rand);
        }
        double ratio = (double) (y - minY) / range;
        if (ratio < bottomRatio) {
            IgneousVariant v = pickIgneousWeighted(rand);
            return v != null ? v.toString() : null;
        }
        if (ratio < middleRatio) {
            MetamorphicVariant v = pickMetamorphicWeighted(rand);
            return v != null ? v.toString() : null;
        }
        SedimentaryVariant v = pickSedimentaryWeighted(rand);
        return v != null ? v.toString() : null;
    }

    private static String pickAnyWeighted(RandomSource rand) {
        int total = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        if (total <= 0) return null;
        int roll = rand.nextInt(total);
        int acc = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v.toString();
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v.toString();
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v.toString();
        }
        return null;
    }

    private static IgneousVariant pickIgneousWeighted(RandomSource rand) {
        int total = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        if (total <= 0) return null;
        int roll = rand.nextInt(total);
        int acc = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v;
        }
        return null;
    }

    private static MetamorphicVariant pickMetamorphicWeighted(RandomSource rand) {
        int total = 0;
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        if (total <= 0) return null;
        int roll = rand.nextInt(total);
        int acc = 0;
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v;
        }
        return null;
    }

    private static SedimentaryVariant pickSedimentaryWeighted(RandomSource rand) {
        int total = 0;
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        if (total <= 0) return null;
        int roll = rand.nextInt(total);
        int acc = 0;
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v;
        }
        return null;
    }

    private void placeBlob(WorldGenLevel level, BlockPos center, int size, String variantName, RandomSource rand) {
        Block stoneVariant = lookup(variantName);
        if (stoneVariant == null) return;
        Block sandVariant = lookup(variantName + "_sand");
        Block sandstoneVariant = lookup(variantName + "_sandstone");
        Block gravelVariant = lookup(variantName + "_gravel");
        boolean replaceSand = WosConfig.replaceSand;
        boolean replaceSandstone = WosConfig.replaceSandstone;
        boolean replaceGravel = WosConfig.replaceGravel;
        boolean replaceRed = WosConfig.replaceRedSandAndSandstone;
        boolean replaceOres = WosConfig.replaceVanillaOres;

        double radius = Math.max(1.0, size / 2.0);
        int r = (int) Math.ceil(radius);
        double rSq = radius * radius;
        double edgeSq = (radius - 0.7) * (radius - 0.7);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = -r; dx <= r; dx++) {
            for (int dy = -r; dy <= r; dy++) {
                for (int dz = -r; dz <= r; dz++) {
                    double distSq = dx * dx + dy * dy + dz * dz;
                    if (distSq > rSq) continue;
                    if (distSq > edgeSq && rand.nextFloat() < 0.45f) continue;
                    pos.set(center.getX() + dx, center.getY() + dy, center.getZ() + dz);
                    BlockState s = level.getBlockState(pos);
                    Block target = null;
                    if (s.is(Blocks.STONE) || (WosConfig.allowStrataInDeepslate && s.is(Blocks.DEEPSLATE))) {
                        target = stoneVariant;
                        if (SurfaceVariantHelper.isAtSurface(level, pos)) {
                            Block surfaceVariant = SurfaceVariantHelper.surfaceVariantFor(level, pos, variantName, WosConfig.allowSnowedStrata, WosConfig.allowOvergrownStrata);
                            if (surfaceVariant != null) target = surfaceVariant;
                        }
                    }
                    else if (replaceSand && s.is(Blocks.SAND)) target = sandVariant;
                    else if (replaceSandstone && s.is(Blocks.SANDSTONE)) target = sandstoneVariant;
                    else if (replaceGravel && s.is(Blocks.GRAVEL)) target = gravelVariant;
                    else if (replaceRed && s.is(Blocks.RED_SAND)) target = sandVariant;
                    else if (replaceRed && s.is(Blocks.RED_SANDSTONE)) target = sandstoneVariant;
                    else if (replaceOres && !DEEPSLATE_ORES.contains(s.getBlock())) {
                        OreVariant ore = VANILLA_ORE_TO_VARIANT.get(s.getBlock());
                        if (ore != null) {
                            RegistryObject<Block> variantOre = WosBlocks.ORES.get(variantName + "_" + ore.suffix);
                            if (variantOre != null && variantOre.isPresent()) {
                                target = variantOre.get();
                            }
                        }
                    }
                    if (target != null) {
                        level.setBlock(pos, target.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    private static Block lookup(String name) {
        ResourceLocation rl = new ResourceLocation(ModInfo.MODID, name);
        return ForgeRegistries.BLOCKS.containsKey(rl) ? ForgeRegistries.BLOCKS.getValue(rl) : null;
    }
}
