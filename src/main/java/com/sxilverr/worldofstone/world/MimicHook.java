package com.sxilverr.worldofstone.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public final class MimicHook {

    @FunctionalInterface
    public interface Setter {
        void setSource(WorldGenLevel level, BlockPos pos, BlockState hostState);
    }

    public static volatile Setter INSTANCE = (level, pos, hostState) -> {
    };

    private MimicHook() {
    }
}
