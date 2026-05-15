package sxilverr.worldofstone.world;

import sxilverr.worldofstone.api.enums.OreVariant;
import sxilverr.worldofstone.config.WosConfig;
import sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.RegistryObject;

public class OtherDimensionOreFeature extends Feature<NoneFeatureConfiguration> {

    public OtherDimensionOreFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos origin = ctx.origin();
        RandomSource rand = ctx.random();
        ResourceKey<Level> dim = level.getLevel().dimension();

        boolean isNether = dim == Level.NETHER;
        boolean isEnd = dim == Level.END;

        boolean genNether = isNether && WosConfig.allowVanillaOresInNether;
        boolean genEnd = isEnd && WosConfig.allowVanillaOresInEnd;
        boolean genObsidian = WosConfig.allowObsidianOreVariants;

        if (!genNether && !genEnd && !genObsidian) return false;

        int placed = 0;

        if (genNether || genEnd) {
            int yMax = isNether ? 128 : level.getMaxBuildHeight();
            for (OreVariant ore : OreVariant.VALUES) {
                int attempts = isNether ? attemptsForOreNether(ore) : attemptsForOreEnd(ore);
                int veinSize = veinSizeForOre(ore);
                for (int i = 0; i < attempts; i++) {
                    int x = origin.getX() + rand.nextInt(16);
                    int z = origin.getZ() + rand.nextInt(16);
                    int y = rand.nextInt(Math.max(1, yMax));
                    if (placeVein(level, new BlockPos(x, y, z), veinSize, ore, isNether, isEnd, rand)) placed++;
                }
            }
        }

        if (genObsidian) {
            for (int i = 0; i < 16; i++) {
                int x = origin.getX() + rand.nextInt(16);
                int z = origin.getZ() + rand.nextInt(16);
                int yRange = level.getMaxBuildHeight() - level.getMinBuildHeight();
                int y = level.getMinBuildHeight() + rand.nextInt(Math.max(1, yRange));
                BlockPos pos = new BlockPos(x, y, z);
                if (level.getBlockState(pos).is(Blocks.OBSIDIAN)) {
                    OreVariant ore = OreVariant.VALUES[rand.nextInt(OreVariant.VALUES.length)];
                    RegistryObject<Block> oreBlock = WosBlocks.ORES.get("obsidian_" + ore.suffix);
                    if (oreBlock != null && oreBlock.isPresent()) {
                        level.setBlock(pos, oreBlock.get().defaultBlockState(), 2);
                        placed++;
                    }
                }
            }
        }

        return placed > 0;
    }

    private boolean placeVein(WorldGenLevel level, BlockPos center, int size, OreVariant ore, boolean isNether, boolean isEnd, RandomSource rand) {
        int radius = Math.max(1, (int) Math.ceil(Math.cbrt(size)));
        java.util.List<BlockPos> candidates = new java.util.ArrayList<>();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dy * dy + dz * dz > radius * radius) continue;
                    candidates.add(new BlockPos(center.getX() + dx, center.getY() + dy, center.getZ() + dz));
                }
            }
        }
        java.util.Collections.shuffle(candidates, new java.util.Random(rand.nextLong()));
        int placed = 0;
        for (BlockPos p : candidates) {
            if (placed >= size) break;
            BlockState here = level.getBlockState(p);
            String hostName = null;
            if (isNether) {
                if (here.is(Blocks.NETHERRACK)) hostName = "netherrack";
                else if (here.is(Blocks.BLACKSTONE)) hostName = "blackstone";
                else if (here.is(Blocks.BASALT)) hostName = "basalt";
            } else if (isEnd) {
                if (here.is(Blocks.END_STONE)) hostName = "end_stone";
            }
            if (hostName == null) continue;
            RegistryObject<Block> oreBlock = WosBlocks.ORES.get(hostName + "_" + ore.suffix);
            if (oreBlock == null || !oreBlock.isPresent()) continue;
            level.setBlock(p, oreBlock.get().defaultBlockState(), 2);
            placed++;
        }
        return placed > 0;
    }

    public static final int OBSIDIAN_ATTEMPTS_PER_CHUNK = 16;

    public static int attemptsForOreNether(OreVariant ore) {
        int base;
        switch (ore) {
            case COAL: base = 6; break;
            case IRON: base = 8; break;
            case COPPER: base = 4; break;
            case GOLD: base = 2; break;
            case DIAMOND: base = 1; break;
            case EMERALD: base = 1; break;
            case REDSTONE: base = 2; break;
            case LAPIS: base = 1; break;
            default: base = 2; break;
        }
        return Math.max(0, (int) Math.round(base * sxilverr.worldofstone.config.WosConfig.netherOreAttemptsMultiplier));
    }

    public static int attemptsForOreEnd(OreVariant ore) {
        int base;
        switch (ore) {
            case COAL: base = 4; break;
            case IRON: base = 6; break;
            case COPPER: base = 3; break;
            case GOLD: base = 1; break;
            case DIAMOND: base = 1; break;
            case EMERALD: base = 1; break;
            case REDSTONE: base = 1; break;
            case LAPIS: base = 1; break;
            default: base = 1; break;
        }
        return Math.max(0, (int) Math.round(base * sxilverr.worldofstone.config.WosConfig.endOreAttemptsMultiplier));
    }

    public static int veinSizeForOre(OreVariant ore) {
        switch (ore) {
            case COAL: return 12;
            case IRON: return 6;
            case COPPER: return 12;
            case GOLD: return 5;
            case DIAMOND: return 4;
            case EMERALD: return 3;
            case REDSTONE: return 5;
            case LAPIS: return 4;
            default: return 5;
        }
    }
}
