package com.sxilverr.worldofstone.common.block;

import com.sxilverr.worldofstone.config.WosConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WosInfestedBlock extends InfestedBlock {
    public WosInfestedBlock(Block host, Properties props) {
        super(host, props);
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
        if (!WosConfig.enableInfestedBlocks) {
            return;
        }
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
    }
}
