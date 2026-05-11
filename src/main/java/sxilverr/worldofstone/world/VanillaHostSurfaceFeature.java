package sxilverr.worldofstone.world;

import sxilverr.worldofstone.config.WosConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class VanillaHostSurfaceFeature extends Feature<NoneFeatureConfiguration> {

    public VanillaHostSurfaceFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        boolean snowed = WosConfig.allowSnowedVanillaVariants;
        boolean overgrown = WosConfig.allowOvergrownVanillaVariants;
        if (!snowed && !overgrown) return false;

        WorldGenLevel level = ctx.level();
        ChunkAccess chunk = level.getChunk(ctx.origin());
        ChunkPos cpos = chunk.getPos();
        int minY = level.getMinBuildHeight();
        int maxY = level.getMaxBuildHeight();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int wx = cpos.getMinBlockX() + dx;
                int wz = cpos.getMinBlockZ() + dz;
                for (int y = minY; y < maxY; y++) {
                    pos.set(wx, y, wz);
                    BlockState state = chunk.getBlockState(pos);
                    String hostName = hostNameFor(state);
                    if (hostName == null) continue;
                    if (!SurfaceVariantHelper.isAtSurface(level, pos)) continue;
                    Block target = SurfaceVariantHelper.surfaceVariantFor(level, pos, hostName, snowed, overgrown);
                    if (target != null) {
                        chunk.setBlockState(pos, target.defaultBlockState(), false);
                    }
                }
            }
        }
        return true;
    }

    private static String hostNameFor(BlockState state) {
        if (state.is(Blocks.GRANITE)) return "granite";
        if (state.is(Blocks.DIORITE)) return "diorite";
        if (state.is(Blocks.ANDESITE)) return "andesite";
        if (state.is(Blocks.TUFF)) return "tuff";
        return null;
    }
}
