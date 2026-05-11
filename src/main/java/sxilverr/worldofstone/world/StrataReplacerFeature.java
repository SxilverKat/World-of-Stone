package sxilverr.worldofstone.world;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.IgneousVariant;
import sxilverr.worldofstone.api.enums.MetamorphicVariant;
import sxilverr.worldofstone.api.enums.OreVariant;
import sxilverr.worldofstone.api.enums.SedimentaryVariant;
import sxilverr.worldofstone.api.enums.VanillaOreHost;
import sxilverr.worldofstone.api.enums.WorldgenSystem;
import sxilverr.worldofstone.config.WosConfig;
import sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class StrataReplacerFeature extends Feature<NoneFeatureConfiguration> {

    private static final Map<Block, OreVariant> VANILLA_ORE_TO_VARIANT = new HashMap<>();
    private static final Map<Block, VanillaOreHost> VANILLA_HOST_BLOCKS = new HashMap<>();
    private static final java.util.HashSet<Block> DEEPSLATE_ORES = new java.util.HashSet<>();
    private static final Map<Block, String> STRUCTURE_TEMPLATES = new HashMap<>();
    private static final Map<Block, String> RED_SANDSTONE_TEMPLATES = new HashMap<>();

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

        VANILLA_HOST_BLOCKS.put(Blocks.GRANITE, VanillaOreHost.GRANITE);
        VANILLA_HOST_BLOCKS.put(Blocks.DIORITE, VanillaOreHost.DIORITE);
        VANILLA_HOST_BLOCKS.put(Blocks.ANDESITE, VanillaOreHost.ANDESITE);
        VANILLA_HOST_BLOCKS.put(Blocks.TUFF, VanillaOreHost.TUFF);

        STRUCTURE_TEMPLATES.put(Blocks.COBBLESTONE, "{}_cobblestone");
        STRUCTURE_TEMPLATES.put(Blocks.COBBLESTONE_STAIRS, "{}_cobblestone_stairs");
        STRUCTURE_TEMPLATES.put(Blocks.COBBLESTONE_SLAB, "{}_cobblestone_slab");
        STRUCTURE_TEMPLATES.put(Blocks.COBBLESTONE_WALL, "{}_cobblestone_wall");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_COBBLESTONE, "mossy_{}_cobblestone");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_COBBLESTONE_STAIRS, "mossy_{}_cobblestone_stairs");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_COBBLESTONE_SLAB, "mossy_{}_cobblestone_slab");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_COBBLESTONE_WALL, "mossy_{}_cobblestone_wall");
        STRUCTURE_TEMPLATES.put(Blocks.STONE_BRICKS, "{}_bricks");
        STRUCTURE_TEMPLATES.put(Blocks.STONE_BRICK_STAIRS, "{}_brick_stairs");
        STRUCTURE_TEMPLATES.put(Blocks.STONE_BRICK_SLAB, "{}_brick_slab");
        STRUCTURE_TEMPLATES.put(Blocks.STONE_BRICK_WALL, "{}_brick_wall");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_STONE_BRICKS, "mossy_{}_bricks");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_STONE_BRICK_STAIRS, "mossy_{}_brick_stairs");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_STONE_BRICK_SLAB, "mossy_{}_brick_slab");
        STRUCTURE_TEMPLATES.put(Blocks.MOSSY_STONE_BRICK_WALL, "mossy_{}_brick_wall");
        STRUCTURE_TEMPLATES.put(Blocks.SANDSTONE_STAIRS, "{}_sandstone_stairs");
        STRUCTURE_TEMPLATES.put(Blocks.SANDSTONE_SLAB, "{}_sandstone_slab");
        STRUCTURE_TEMPLATES.put(Blocks.SANDSTONE_WALL, "{}_sandstone_wall");
        STRUCTURE_TEMPLATES.put(Blocks.CUT_SANDSTONE, "cut_{}_sandstone");
        STRUCTURE_TEMPLATES.put(Blocks.CUT_SANDSTONE_SLAB, "cut_{}_sandstone_slab");
        STRUCTURE_TEMPLATES.put(Blocks.CHISELED_SANDSTONE, "chiseled_{}_sandstone");
        STRUCTURE_TEMPLATES.put(Blocks.SMOOTH_SANDSTONE, "smooth_{}_sandstone");
        STRUCTURE_TEMPLATES.put(Blocks.SMOOTH_SANDSTONE_STAIRS, "smooth_{}_sandstone_stairs");
        STRUCTURE_TEMPLATES.put(Blocks.SMOOTH_SANDSTONE_SLAB, "smooth_{}_sandstone_slab");
        STRUCTURE_TEMPLATES.put(Blocks.SMOOTH_STONE, "smooth_{}");
        STRUCTURE_TEMPLATES.put(Blocks.SMOOTH_STONE_SLAB, "smooth_{}_slab");

        RED_SANDSTONE_TEMPLATES.put(Blocks.RED_SANDSTONE_STAIRS, "{}_sandstone_stairs");
        RED_SANDSTONE_TEMPLATES.put(Blocks.RED_SANDSTONE_SLAB, "{}_sandstone_slab");
        RED_SANDSTONE_TEMPLATES.put(Blocks.RED_SANDSTONE_WALL, "{}_sandstone_wall");
        RED_SANDSTONE_TEMPLATES.put(Blocks.CUT_RED_SANDSTONE, "cut_{}_sandstone");
        RED_SANDSTONE_TEMPLATES.put(Blocks.CUT_RED_SANDSTONE_SLAB, "cut_{}_sandstone_slab");
        RED_SANDSTONE_TEMPLATES.put(Blocks.CHISELED_RED_SANDSTONE, "chiseled_{}_sandstone");
        RED_SANDSTONE_TEMPLATES.put(Blocks.SMOOTH_RED_SANDSTONE, "smooth_{}_sandstone");
        RED_SANDSTONE_TEMPLATES.put(Blocks.SMOOTH_RED_SANDSTONE_STAIRS, "smooth_{}_sandstone_stairs");
        RED_SANDSTONE_TEMPLATES.put(Blocks.SMOOTH_RED_SANDSTONE_SLAB, "smooth_{}_sandstone_slab");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static BlockState copyMatchingProperties(BlockState target, BlockState source) {
        BlockState result = target;
        for (Property<?> prop : source.getProperties()) {
            if (target.hasProperty(prop)) {
                result = result.setValue((Property) prop, source.getValue(prop));
            }
        }
        return result;
    }

    private static Block lookupVariantBlock(String template, String stratumVariant,
                                            IgneousVariant igneous, MetamorphicVariant metamorphic) {
        Block block = lookupRegisteredBlock(template, stratumVariant);
        if (block != null) return block;
        if (metamorphic != null) {
            block = lookupRegisteredBlock(template, metamorphic.toString());
            if (block != null) return block;
        }
        if (igneous != null) {
            block = lookupRegisteredBlock(template, igneous.toString());
        }
        return block;
    }

    private static Block lookupRegisteredBlock(String template, String variantName) {
        if (variantName == null) return null;
        ResourceLocation rl = new ResourceLocation(ModInfo.MODID, template.replace("{}", variantName));
        return ForgeRegistries.BLOCKS.containsKey(rl) ? ForgeRegistries.BLOCKS.getValue(rl) : null;
    }

    private static final int[][] NEIGHBOR_OFFSETS = {
            {-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}
    };

    private static VanillaOreHost findVanillaHost(ChunkAccess chunk, ChunkPos cpos, int x, int y, int z) {
        Map<VanillaOreHost, Integer> counts = new EnumMap<>(VanillaOreHost.class);
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int minX = cpos.getMinBlockX();
        int maxX = cpos.getMaxBlockX();
        int minZ = cpos.getMinBlockZ();
        int maxZ = cpos.getMaxBlockZ();
        for (int[] o : NEIGHBOR_OFFSETS) {
            int nx = x + o[0];
            int ny = y + o[1];
            int nz = z + o[2];
            if (nx < minX || nx > maxX || nz < minZ || nz > maxZ) continue;
            pos.set(nx, ny, nz);
            VanillaOreHost host = VANILLA_HOST_BLOCKS.get(chunk.getBlockState(pos).getBlock());
            if (host != null) {
                counts.merge(host, 1, Integer::sum);
            }
        }
        VanillaOreHost best = null;
        int bestCount = 0;
        for (Map.Entry<VanillaOreHost, Integer> e : counts.entrySet()) {
            if (e.getValue() > bestCount) {
                bestCount = e.getValue();
                best = e.getKey();
            }
        }
        return best;
    }

    public StrataReplacerFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        if (!WosConfig.stoneReplacementEnabled) return false;
        if (WosConfig.worldgenSystem != WorldgenSystem.CHUNK) return false;
        WorldGenLevel level = ctx.level();
        BlockPos origin = ctx.origin();
        ChunkAccess chunk = level.getChunk(origin);
        ChunkPos cpos = chunk.getPos();

        int minY = level.getMinBuildHeight();
        int seaLevel = level.getSeaLevel();
        int range = seaLevel - minY;
        if (range <= 0) return false;
        int loopMaxY = WosConfig.replaceStoneAboveGround ? level.getMaxBuildHeight() : seaLevel;

        double bottomRatio = WosConfig.strataBottomRatio;
        double middleRatio = Math.max(bottomRatio, WosConfig.strataMiddleRatio);

        int patchBlockSize = Math.max(16, WosConfig.patchSize * 16);
        int patchX = Math.floorDiv(cpos.getMinBlockX(), patchBlockSize);
        int patchZ = Math.floorDiv(cpos.getMinBlockZ(), patchBlockSize);

        int xzDither = Math.max(0, WosConfig.strataXZDither);
        int yDither = Math.max(0, WosConfig.strataYDither);
        double yDitherRatio = (range > 0) ? ((double) yDither / range) : 0.0;

        IgneousVariant[][] igneousNbh = new IgneousVariant[3][3];
        MetamorphicVariant[][] metamorphicNbh = new MetamorphicVariant[3][3];
        SedimentaryVariant[][] sedimentaryNbh = new SedimentaryVariant[3][3];
        String[][] anyNbh = WosConfig.ignoreStrataHeightRestrictions ? new String[3][3] : null;
        boolean[][] patchHasVariants = new boolean[3][3];
        int variantChance = Math.max(0, Math.min(100, WosConfig.variantChance));
        for (int ddx = -1; ddx <= 1; ddx++) {
            for (int ddz = -1; ddz <= 1; ddz++) {
                if (variantChance >= 100) {
                    patchHasVariants[ddx + 1][ddz + 1] = true;
                } else {
                    int neighborRoll = Math.floorMod(noise(patchX + ddx, patchZ + ddz, 7777L), 100);
                    patchHasVariants[ddx + 1][ddz + 1] = neighborRoll < variantChance;
                }
                igneousNbh[ddx + 1][ddz + 1] = pickIgneous(patchX + ddx, patchZ + ddz);
                metamorphicNbh[ddx + 1][ddz + 1] = pickMetamorphic(patchX + ddx, patchZ + ddz);
                sedimentaryNbh[ddx + 1][ddz + 1] = pickSedimentary(patchX + ddx, patchZ + ddz);
                if (anyNbh != null) {
                    anyNbh[ddx + 1][ddz + 1] = pickAny(patchX + ddx, patchZ + ddz);
                }
            }
        }
        if (!patchHasVariants[1][1] && xzDither == 0) return false;

        boolean replaceOres = WosConfig.replaceVanillaOres;

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int wx = cpos.getMinBlockX() + dx;
                int wz = cpos.getMinBlockZ() + dz;

                int offX = 0;
                int offZ = 0;
                if (xzDither > 0) {
                    int xJitter = Math.floorMod(noise(wx, wz, 100L), 2 * xzDither + 1) - xzDither;
                    int zJitter = Math.floorMod(noise(wx, wz, 200L), 2 * xzDither + 1) - xzDither;
                    int effPatchX = Math.floorDiv(wx + xJitter, patchBlockSize);
                    int effPatchZ = Math.floorDiv(wz + zJitter, patchBlockSize);
                    offX = Math.max(-1, Math.min(1, effPatchX - patchX));
                    offZ = Math.max(-1, Math.min(1, effPatchZ - patchZ));
                }

                if (!patchHasVariants[offX + 1][offZ + 1]) continue;
                IgneousVariant igneous = igneousNbh[offX + 1][offZ + 1];
                MetamorphicVariant metamorphic = metamorphicNbh[offX + 1][offZ + 1];
                SedimentaryVariant sedimentary = sedimentaryNbh[offX + 1][offZ + 1];

                for (int y = minY; y < loopMaxY; y++) {
                    pos.set(wx, y, wz);
                    BlockState state = chunk.getBlockState(pos);

                    boolean isStone = state.is(Blocks.STONE) || (WosConfig.allowStrataInDeepslate && state.is(Blocks.DEEPSLATE));
                    boolean isSand = WosConfig.replaceSand && state.is(Blocks.SAND);
                    boolean isSandstone = WosConfig.replaceSandstone && state.is(Blocks.SANDSTONE);
                    boolean isGravel = WosConfig.replaceGravel && state.is(Blocks.GRAVEL);
                    if (WosConfig.replaceRedSandAndSandstone) {
                        if (!isSand && state.is(Blocks.RED_SAND)) isSand = true;
                        if (!isSandstone && state.is(Blocks.RED_SANDSTONE)) isSandstone = true;
                    }
                    String structTemplate = null;
                    if (WosConfig.enableStructureVariantGeneration && !isStone && !isSand && !isSandstone && !isGravel) {
                        structTemplate = STRUCTURE_TEMPLATES.get(state.getBlock());
                        if (structTemplate == null && WosConfig.replaceRedSandAndSandstone) {
                            structTemplate = RED_SANDSTONE_TEMPLATES.get(state.getBlock());
                        }
                    }
                    boolean isOre = !isStone && !isSand && !isSandstone && !isGravel && structTemplate == null
                            && replaceOres && VANILLA_ORE_TO_VARIANT.containsKey(state.getBlock());
                    if (!isStone && !isSand && !isSandstone && !isGravel && !isOre && structTemplate == null) continue;

                    if (isOre) {
                        VanillaOreHost host = findVanillaHost(chunk, cpos, wx, y, wz);
                        if (host != null && WosConfig.isVanillaOreHostEnabled(host)) {
                            OreVariant ore = VANILLA_ORE_TO_VARIANT.get(state.getBlock());
                            if (ore != null) {
                                String oreName = host.getRegistryName() + "_" + ore.suffix;
                                RegistryObject<Block> hostOre = WosBlocks.ORES.get(oreName);
                                if (hostOre != null && hostOre.isPresent()) {
                                    chunk.setBlockState(pos, hostOre.get().defaultBlockState(), false);
                                    continue;
                                }
                            }
                        }
                        if (DEEPSLATE_ORES.contains(state.getBlock())) continue;
                    }

                    double ratio = (double) (y - minY) / range;
                    if (yDitherRatio > 0.0) {
                        double n = noise01(wx, y, wz, 999L);
                        ratio += (n * 2.0 - 1.0) * yDitherRatio;
                    }

                    String stratumVariant = (anyNbh != null)
                            ? anyNbh[offX + 1][offZ + 1]
                            : stratumVariantName(ratio, bottomRatio, middleRatio, igneous, metamorphic, sedimentary);
                    if (stratumVariant == null) continue;

                    if (structTemplate != null) {
                        Block variantBlock = lookupVariantBlock(structTemplate, stratumVariant, igneous, metamorphic);
                        if (variantBlock != null) {
                            BlockState newState = copyMatchingProperties(variantBlock.defaultBlockState(), state);
                            chunk.setBlockState(pos, newState, false);
                        }
                        continue;
                    }
                    if (isStone) {
                        Block variantStone = null;
                        if (SurfaceVariantHelper.isAtSurface(level, pos)) {
                            variantStone = SurfaceVariantHelper.surfaceVariantFor(level, pos, stratumVariant, WosConfig.allowSnowedStrata, WosConfig.allowOvergrownStrata);
                        }
                        if (variantStone == null) {
                            variantStone = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ModInfo.MODID, stratumVariant));
                        }
                        if (variantStone != null) {
                            chunk.setBlockState(pos, variantStone.defaultBlockState(), false);
                        }
                    } else if (isSand) {
                        Block variantSand = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ModInfo.MODID, stratumVariant + "_sand"));
                        if (variantSand != null) {
                            chunk.setBlockState(pos, variantSand.defaultBlockState(), false);
                        }
                    } else if (isSandstone) {
                        Block variantSandstone = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ModInfo.MODID, stratumVariant + "_sandstone"));
                        if (variantSandstone != null) {
                            chunk.setBlockState(pos, variantSandstone.defaultBlockState(), false);
                        }
                    } else if (isGravel) {
                        Block variantGravel = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ModInfo.MODID, stratumVariant + "_gravel"));
                        if (variantGravel != null) {
                            chunk.setBlockState(pos, variantGravel.defaultBlockState(), false);
                        }
                    } else {
                        OreVariant ore = VANILLA_ORE_TO_VARIANT.get(state.getBlock());
                        if (ore != null) {
                            String oreName = stratumVariant + "_" + ore.suffix;
                            RegistryObject<Block> variantOre = WosBlocks.ORES.get(oreName);
                            if (variantOre != null && variantOre.isPresent()) {
                                chunk.setBlockState(pos, variantOre.get().defaultBlockState(), false);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private static String stratumVariantName(double ratio, double bottomRatio, double middleRatio,
                                             IgneousVariant ign, MetamorphicVariant met, SedimentaryVariant sed) {
        if (ratio < bottomRatio) {
            return ign == null ? null : ign.toString();
        }
        if (ratio < middleRatio) {
            return met == null ? null : met.toString();
        }
        return sed == null ? null : sed.toString();
    }

    private static IgneousVariant pickIgneous(int px, int pz) {
        int totalWeight = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) totalWeight += WosConfig.getVariantWeight(v);
        }
        if (totalWeight <= 0) return null;
        int roll = Math.floorMod(noise(px, pz, 1L), totalWeight);
        int acc = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v;
        }
        return null;
    }

    private static MetamorphicVariant pickMetamorphic(int px, int pz) {
        int totalWeight = 0;
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) totalWeight += WosConfig.getVariantWeight(v);
        }
        if (totalWeight <= 0) return null;
        int roll = Math.floorMod(noise(px, pz, 2L), totalWeight);
        int acc = 0;
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v;
        }
        return null;
    }

    private static SedimentaryVariant pickSedimentary(int px, int pz) {
        int totalWeight = 0;
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) totalWeight += WosConfig.getVariantWeight(v);
        }
        if (totalWeight <= 0) return null;
        int roll = Math.floorMod(noise(px, pz, 3L), totalWeight);
        int acc = 0;
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v;
        }
        return null;
    }

    private static String pickAny(int px, int pz) {
        int totalWeight = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) totalWeight += WosConfig.getVariantWeight(v);
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) totalWeight += WosConfig.getVariantWeight(v);
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) totalWeight += WosConfig.getVariantWeight(v);
        }
        if (totalWeight <= 0) return null;
        int roll = Math.floorMod(noise(px, pz, 4L), totalWeight);
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

    private static int noise(int cx, int cz, long salt) {
        long h = (long) cx * 341873128712L + (long) cz * 132897987541L + salt * 7919L;
        h ^= h >>> 33;
        h *= 0xff51afd7ed558ccdL;
        h ^= h >>> 33;
        return (int) (h & 0x7FFFFFFF);
    }

    private static int noise3(int x, int y, int z, long salt) {
        long h = (long) x * 341873128712L + (long) y * 132897987541L + (long) z * 104395303L + salt * 7919L;
        h ^= h >>> 33;
        h *= 0xff51afd7ed558ccdL;
        h ^= h >>> 33;
        return (int) (h & 0x7FFFFFFF);
    }

    private static double noise01(int x, int y, int z, long salt) {
        return (double) noise3(x, y, z, salt) / (double) 0x7FFFFFFF;
    }
}
