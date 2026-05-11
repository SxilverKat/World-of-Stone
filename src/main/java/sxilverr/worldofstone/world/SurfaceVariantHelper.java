package sxilverr.worldofstone.world;

import sxilverr.worldofstone.api.ModInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.registries.ForgeRegistries;

public final class SurfaceVariantHelper {

    private SurfaceVariantHelper() {
    }

    public static boolean isAtSurface(WorldGenLevel level, BlockPos pos) {
        BlockState above = level.getBlockState(pos.above());
        if (above.is(Blocks.SNOW) || above.is(Blocks.SNOW_BLOCK)) return true;
        int worldSurfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
        return pos.getY() + 1 >= worldSurfaceY;
    }

    public static Block surfaceVariantFor(WorldGenLevel level, BlockPos pos, String variantName, boolean allowSnowed, boolean allowOvergrown) {
        Holder<Biome> biome = level.getBiome(pos);
        float temp = biome.value().getBaseTemperature();
        if (temp <= 0.15f) {
            return allowSnowed ? lookup("snowed_" + variantName) : null;
        }
        Biome.Precipitation prec = biome.value().getPrecipitationAt(pos);
        if (prec == Biome.Precipitation.RAIN) {
            return allowOvergrown ? lookup("overgrown_" + variantName) : null;
        }
        return null;
    }

    private static Block lookup(String name) {
        ResourceLocation rl = new ResourceLocation(ModInfo.MODID, name);
        return ForgeRegistries.BLOCKS.containsKey(rl) ? ForgeRegistries.BLOCKS.getValue(rl) : null;
    }
}
