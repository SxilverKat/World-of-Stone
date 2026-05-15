package sxilverr.worldofstone.world;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.IgneousVariant;
import sxilverr.worldofstone.api.enums.MetamorphicVariant;
import sxilverr.worldofstone.api.enums.NoiseType;
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
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class NoiseReplacerFeature extends Feature<NoneFeatureConfiguration> {

    private static final long GATE_SALT = 0xA5A5C3C3L;
    private static final long IGNEOUS_SALT = 0x1111L;
    private static final long METAMORPHIC_SALT = 0x2222L;
    private static final long SEDIMENTARY_SALT = 0x3333L;
    private static final long ANY_SALT = 0x4444L;

    private static final Map<Block, OreVariant> VANILLA_ORE_TO_VARIANT = new HashMap<>();
    private static final Map<Block, VanillaOreHost> VANILLA_HOST_BLOCKS = new HashMap<>();
    private static final java.util.HashSet<Block> DEEPSLATE_ORES = new java.util.HashSet<>();

    private static final int[][] NEIGHBOR_OFFSETS = {
            {-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}
    };

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
    }

    public NoiseReplacerFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        if (!WosConfig.stoneReplacementEnabled) return false;
        if (WosConfig.worldgenSystem != WorldgenSystem.NOISE) return false;

        WorldGenLevel level = ctx.level();
        ChunkAccess chunk = level.getChunk(ctx.origin());
        ChunkPos cpos = chunk.getPos();
        int minY = level.getMinBuildHeight();
        int seaLevel = level.getSeaLevel();
        int range = seaLevel - minY;
        if (range <= 0) return false;

        int loopMaxY = WosConfig.replaceStoneAboveGround ? level.getMaxBuildHeight() : seaLevel;
        double bottomRatio = WosConfig.strataBottomRatio;
        double middleRatio = Math.max(bottomRatio, WosConfig.strataMiddleRatio);
        int variantChance = Math.max(0, Math.min(100, WosConfig.variantChance));
        if (variantChance == 0) return false;

        int scale = Math.max(1, WosConfig.noiseScale);
        long seed = level.getSeed();
        NoiseType noiseType = WosConfig.noiseType;
        int octaves = Math.max(1, Math.min(8, WosConfig.noiseOctaves));

        boolean replaceSand = WosConfig.replaceSand;
        boolean replaceSandstone = WosConfig.replaceSandstone;
        boolean replaceGravel = WosConfig.replaceGravel;
        boolean replaceRed = WosConfig.replaceRedSandAndSandstone;
        boolean replaceOres = WosConfig.replaceVanillaOres;

        int igneousTotal = igneousTotalWeight();
        int metamorphicTotal = metamorphicTotalWeight();
        int sedimentaryTotal = sedimentaryTotalWeight();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int wx = cpos.getMinBlockX() + dx;
                int wz = cpos.getMinBlockZ() + dz;
                for (int y = minY; y < loopMaxY; y++) {
                    pos.set(wx, y, wz);
                    BlockState state = chunk.getBlockState(pos);

                    boolean isStone = state.is(Blocks.STONE) || (WosConfig.allowStrataInDeepslate && state.is(Blocks.DEEPSLATE));
                    boolean isSand = replaceSand && state.is(Blocks.SAND);
                    boolean isSandstone = replaceSandstone && state.is(Blocks.SANDSTONE);
                    boolean isGravel = replaceGravel && state.is(Blocks.GRAVEL);
                    boolean isClay = state.is(Blocks.CLAY);
                    if (replaceRed) {
                        if (!isSand && state.is(Blocks.RED_SAND)) isSand = true;
                        if (!isSandstone && state.is(Blocks.RED_SANDSTONE)) isSandstone = true;
                    }
                    OreVariant ore = (replaceOres && !isStone && !isSand && !isSandstone && !isGravel && !isClay)
                            ? VANILLA_ORE_TO_VARIANT.get(state.getBlock()) : null;
                    boolean isOre = ore != null;
                    if (!isStone && !isSand && !isSandstone && !isGravel && !isClay && !isOre) continue;

                    if (variantChance < 100) {
                        double gate = sample(noiseType, octaves, wx, y, wz, scale, seed ^ GATE_SALT);
                        if (gate * 100.0 >= variantChance) continue;
                    }

                    if (isOre) {
                        VanillaOreHost host = findVanillaHost(chunk, cpos, wx, y, wz);
                        if (host != null && WosConfig.isVanillaOreHostEnabled(host)) {
                            String hostOreName = host.getRegistryName() + "_" + ore.suffix;
                            RegistryObject<Block> hostOre = WosBlocks.ORES.get(hostOreName);
                            if (hostOre != null && hostOre.isPresent()) {
                                chunk.setBlockState(pos, hostOre.get().defaultBlockState(), false);
                                continue;
                            }
                        }
                        if (DEEPSLATE_ORES.contains(state.getBlock())) continue;
                    }

                    String variantName;
                    if (WosConfig.ignoreStrataHeightRestrictions) {
                        variantName = pickAny(wx, y, wz, scale, seed, igneousTotal + metamorphicTotal + sedimentaryTotal, noiseType, octaves);
                    } else {
                        double ratio = (double) (y - minY) / range;
                        if (ratio < bottomRatio) {
                            variantName = pickIgneous(wx, y, wz, scale, seed, igneousTotal, noiseType, octaves);
                        } else if (ratio < middleRatio) {
                            variantName = pickMetamorphic(wx, y, wz, scale, seed, metamorphicTotal, noiseType, octaves);
                        } else {
                            variantName = pickSedimentary(wx, y, wz, scale, seed, sedimentaryTotal, noiseType, octaves);
                        }
                    }
                    if (variantName == null) continue;

                    if (isOre) {
                        String oreName = variantName + "_" + ore.suffix;
                        RegistryObject<Block> variantOre = WosBlocks.ORES.get(oreName);
                        if (variantOre != null && variantOre.isPresent()) {
                            chunk.setBlockState(pos, variantOre.get().defaultBlockState(), false);
                        }
                        continue;
                    }

                    String suffix;
                    if (isStone) suffix = "";
                    else if (isSand) suffix = "_sand";
                    else if (isSandstone) suffix = "_sandstone";
                    else if (isGravel) suffix = "_gravel";
                    else suffix = "_clay";

                    Block target = null;
                    if (isStone && SurfaceVariantHelper.isAtSurface(level, pos)) {
                        target = SurfaceVariantHelper.surfaceVariantFor(level, pos, variantName, WosConfig.allowSnowedStrata, WosConfig.allowOvergrownStrata);
                    }
                    if (target == null) {
                        target = lookup(variantName + suffix);
                    }
                    if (target != null) {
                        chunk.setBlockState(pos, target.defaultBlockState(), false);
                    }
                }
            }
        }
        return true;
    }

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

    private static int igneousTotalWeight() {
        int total = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        return total;
    }

    private static int metamorphicTotalWeight() {
        int total = 0;
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        return total;
    }

    private static int sedimentaryTotalWeight() {
        int total = 0;
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (WosConfig.isVariantEnabled(v)) total += WosConfig.getVariantWeight(v);
        }
        return total;
    }

    private static String pickIgneous(int x, int y, int z, int scale, long seed, int total, NoiseType type, int octaves) {
        if (total <= 0) return null;
        double n = sample(type, octaves, x, y, z, scale, seed ^ IGNEOUS_SALT);
        int roll = clampRoll((int) Math.floor(n * total), total);
        int acc = 0;
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v.toString();
        }
        return null;
    }

    private static String pickMetamorphic(int x, int y, int z, int scale, long seed, int total, NoiseType type, int octaves) {
        if (total <= 0) return null;
        double n = sample(type, octaves, x, y, z, scale, seed ^ METAMORPHIC_SALT);
        int roll = clampRoll((int) Math.floor(n * total), total);
        int acc = 0;
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v.toString();
        }
        return null;
    }

    private static String pickSedimentary(int x, int y, int z, int scale, long seed, int total, NoiseType type, int octaves) {
        if (total <= 0) return null;
        double n = sample(type, octaves, x, y, z, scale, seed ^ SEDIMENTARY_SALT);
        int roll = clampRoll((int) Math.floor(n * total), total);
        int acc = 0;
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (!WosConfig.isVariantEnabled(v)) continue;
            acc += WosConfig.getVariantWeight(v);
            if (roll < acc) return v.toString();
        }
        return null;
    }

    private static String pickAny(int x, int y, int z, int scale, long seed, int total, NoiseType type, int octaves) {
        if (total <= 0) return null;
        double n = sample(type, octaves, x, y, z, scale, seed ^ ANY_SALT);
        int roll = clampRoll((int) Math.floor(n * total), total);
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

    private static int clampRoll(int roll, int total) {
        if (roll < 0) return 0;
        if (roll >= total) return total - 1;
        return roll;
    }

    private static double sample(NoiseType type, int octaves, int x, int y, int z, int scale, long salt) {
        double sx = (double) x / scale;
        double sy = (double) y / scale;
        double sz = (double) z / scale;
        if (octaves <= 1) {
            return clamp01(baseAt(type, sx, sy, sz, salt));
        }
        double total = 0.0;
        double maxAmp = 0.0;
        double amplitude = 1.0;
        double frequency = 1.0;
        for (int o = 0; o < octaves; o++) {
            total += amplitude * baseAt(type, sx * frequency, sy * frequency, sz * frequency, salt + (long) o * 7919L);
            maxAmp += amplitude;
            amplitude *= 0.5;
            frequency *= 2.0;
        }
        return clamp01(total / Math.max(0.001, maxAmp));
    }

    private static double baseAt(NoiseType type, double sx, double sy, double sz, long salt) {
        switch (type) {
            case PERLIN:
                return (perlinD(sx, sy, sz, salt) + 1.0) * 0.5;
            case SIMPLEX:
                return (simplexD(sx, sy, sz, salt) + 1.0) * 0.5;
            case RIDGED:
            case RIDGED_MULTIFRACTAL:
                return 1.0 - Math.abs(perlinD(sx, sy, sz, salt));
            case BILLOW:
                return Math.abs(perlinD(sx, sy, sz, salt));
            case CELLULAR:
                return cellularAt(sx, sy, sz, salt);
            case DOMAIN_WARPED:
                return domainWarpedAt(sx, sy, sz, salt);
            case VALUE:
            case FRACTIONAL_BROWNIAN_MOTION:
            default:
                return valueAt(sx, sy, sz, salt);
        }
    }

    private static double valueAt(double sx, double sy, double sz, long salt) {
        int xi = (int) Math.floor(sx);
        int yi = (int) Math.floor(sy);
        int zi = (int) Math.floor(sz);
        double xf = sx - xi;
        double yf = sy - yi;
        double zf = sz - zi;
        double sxx = xf * xf * (3.0 - 2.0 * xf);
        double syy = yf * yf * (3.0 - 2.0 * yf);
        double szz = zf * zf * (3.0 - 2.0 * zf);
        double v000 = hash01(xi, yi, zi, salt);
        double v100 = hash01(xi + 1, yi, zi, salt);
        double v010 = hash01(xi, yi + 1, zi, salt);
        double v110 = hash01(xi + 1, yi + 1, zi, salt);
        double v001 = hash01(xi, yi, zi + 1, salt);
        double v101 = hash01(xi + 1, yi, zi + 1, salt);
        double v011 = hash01(xi, yi + 1, zi + 1, salt);
        double v111 = hash01(xi + 1, yi + 1, zi + 1, salt);
        double a00 = v000 + sxx * (v100 - v000);
        double a10 = v010 + sxx * (v110 - v010);
        double a01 = v001 + sxx * (v101 - v001);
        double a11 = v011 + sxx * (v111 - v011);
        double b0 = a00 + syy * (a10 - a00);
        double b1 = a01 + syy * (a11 - a01);
        return b0 + szz * (b1 - b0);
    }

    private static double perlinD(double sx, double sy, double sz, long salt) {
        int xi = (int) Math.floor(sx);
        int yi = (int) Math.floor(sy);
        int zi = (int) Math.floor(sz);
        double xf = sx - xi;
        double yf = sy - yi;
        double zf = sz - zi;
        double u = fade(xf);
        double v = fade(yf);
        double w = fade(zf);
        double n000 = grad3D(hashInt(xi, yi, zi, salt), xf, yf, zf);
        double n100 = grad3D(hashInt(xi + 1, yi, zi, salt), xf - 1, yf, zf);
        double n010 = grad3D(hashInt(xi, yi + 1, zi, salt), xf, yf - 1, zf);
        double n110 = grad3D(hashInt(xi + 1, yi + 1, zi, salt), xf - 1, yf - 1, zf);
        double n001 = grad3D(hashInt(xi, yi, zi + 1, salt), xf, yf, zf - 1);
        double n101 = grad3D(hashInt(xi + 1, yi, zi + 1, salt), xf - 1, yf, zf - 1);
        double n011 = grad3D(hashInt(xi, yi + 1, zi + 1, salt), xf, yf - 1, zf - 1);
        double n111 = grad3D(hashInt(xi + 1, yi + 1, zi + 1, salt), xf - 1, yf - 1, zf - 1);
        double a00 = lerp(n000, n100, u);
        double a10 = lerp(n010, n110, u);
        double a01 = lerp(n001, n101, u);
        double a11 = lerp(n011, n111, u);
        double b0 = lerp(a00, a10, v);
        double b1 = lerp(a01, a11, v);
        double n = lerp(b0, b1, w);
        if (n < -1.0) n = -1.0;
        if (n > 1.0) n = 1.0;
        return n;
    }

    private static double simplexD(double sx, double sy, double sz, long salt) {
        final double F3 = 1.0 / 3.0;
        final double G3 = 1.0 / 6.0;
        double s = (sx + sy + sz) * F3;
        int i = (int) Math.floor(sx + s);
        int j = (int) Math.floor(sy + s);
        int k = (int) Math.floor(sz + s);
        double t = (i + j + k) * G3;
        double X0 = i - t;
        double Y0 = j - t;
        double Z0 = k - t;
        double x0 = sx - X0;
        double y0 = sy - Y0;
        double z0 = sz - Z0;

        int i1, j1, k1, i2, j2, k2;
        if (x0 >= y0) {
            if (y0 >= z0) { i1 = 1; j1 = 0; k1 = 0; i2 = 1; j2 = 1; k2 = 0; }
            else if (x0 >= z0) { i1 = 1; j1 = 0; k1 = 0; i2 = 1; j2 = 0; k2 = 1; }
            else { i1 = 0; j1 = 0; k1 = 1; i2 = 1; j2 = 0; k2 = 1; }
        } else {
            if (y0 < z0) { i1 = 0; j1 = 0; k1 = 1; i2 = 0; j2 = 1; k2 = 1; }
            else if (x0 < z0) { i1 = 0; j1 = 1; k1 = 0; i2 = 0; j2 = 1; k2 = 1; }
            else { i1 = 0; j1 = 1; k1 = 0; i2 = 1; j2 = 1; k2 = 0; }
        }

        double x1 = x0 - i1 + G3;
        double y1 = y0 - j1 + G3;
        double z1 = z0 - k1 + G3;
        double x2 = x0 - i2 + 2.0 * G3;
        double y2 = y0 - j2 + 2.0 * G3;
        double z2 = z0 - k2 + 2.0 * G3;
        double x3 = x0 - 1.0 + 3.0 * G3;
        double y3 = y0 - 1.0 + 3.0 * G3;
        double z3 = z0 - 1.0 + 3.0 * G3;

        double n0 = simplexCorner(i, j, k, x0, y0, z0, salt);
        double n1 = simplexCorner(i + i1, j + j1, k + k1, x1, y1, z1, salt);
        double n2 = simplexCorner(i + i2, j + j2, k + k2, x2, y2, z2, salt);
        double n3 = simplexCorner(i + 1, j + 1, k + 1, x3, y3, z3, salt);

        double n = 32.0 * (n0 + n1 + n2 + n3);
        if (n < -1.0) n = -1.0;
        if (n > 1.0) n = 1.0;
        return n;
    }

    private static double simplexCorner(int i, int j, int k, double x, double y, double z, long salt) {
        double t = 0.6 - x * x - y * y - z * z;
        if (t < 0) return 0.0;
        int h = hashInt(i, j, k, salt);
        return t * t * t * t * grad3D(h, x, y, z);
    }

    private static double cellularAt(double sx, double sy, double sz, long salt) {
        int xi = (int) Math.floor(sx);
        int yi = (int) Math.floor(sy);
        int zi = (int) Math.floor(sz);
        double minDist2 = Double.MAX_VALUE;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    int cx = xi + dx;
                    int cy = yi + dy;
                    int cz = zi + dz;
                    double fx = cx + hash01(cx, cy, cz, salt);
                    double fy = cy + hash01(cx, cy, cz, salt + 7L);
                    double fz = cz + hash01(cx, cy, cz, salt + 13L);
                    double ddx = fx - sx;
                    double ddy = fy - sy;
                    double ddz = fz - sz;
                    double d2 = ddx * ddx + ddy * ddy + ddz * ddz;
                    if (d2 < minDist2) minDist2 = d2;
                }
            }
        }
        return Math.min(1.0, Math.sqrt(minDist2) * 1.15);
    }

    private static double domainWarpedAt(double sx, double sy, double sz, long salt) {
        double wx = perlinD(sx, sy, sz, salt + 100L) * 0.5;
        double wy = perlinD(sx, sy, sz, salt + 200L) * 0.5;
        double wz = perlinD(sx, sy, sz, salt + 300L) * 0.5;
        return (perlinD(sx + wx, sy + wy, sz + wz, salt) + 1.0) * 0.5;
    }

    private static double fade(double t) {
        return t * t * t * (t * (t * 6.0 - 15.0) + 10.0);
    }

    private static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    private static double grad3D(int hash, double x, double y, double z) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v;
        if (h < 4) v = y;
        else if (h == 12 || h == 14) v = x;
        else v = z;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    private static int hashInt(int x, int y, int z, long salt) {
        long h = (long) x * 341873128712L + (long) y * 132897987541L + (long) z * 104395303L + salt * 7919L;
        h ^= h >>> 33;
        h *= 0xff51afd7ed558ccdL;
        h ^= h >>> 33;
        return (int) (h & 0x7FFFFFFF);
    }

    private static double hash01(int x, int y, int z, long salt) {
        return (double) hashInt(x, y, z, salt) / (double) 0x7FFFFFFF;
    }

    private static double clamp01(double v) {
        if (v < 0.0) return 0.0;
        if (v > 1.0) return 1.0;
        return v;
    }

    private static Block lookup(String name) {
        ResourceLocation rl = ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, name);
        return ForgeRegistries.BLOCKS.containsKey(rl) ? ForgeRegistries.BLOCKS.getValue(rl) : null;
    }
}
