package com.sxilverr.worldofstone.neoforge.block;

import com.sxilverr.worldofstone.common.block.WosSpeleothemBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class WosDevSpeleothemBlock extends WosSpeleothemBlock {

    public WosDevSpeleothemBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        ItemStack tool = player.getMainHandItem();
        return tool.getItem() instanceof PickaxeItem pickaxe && pickaxe.getTier() == Tiers.NETHERITE;
    }
}
