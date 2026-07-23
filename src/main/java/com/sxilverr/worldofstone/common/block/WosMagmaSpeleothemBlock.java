package com.sxilverr.worldofstone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class WosMagmaSpeleothemBlock extends WosSpeleothemBlock {

    public WosMagmaSpeleothemBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully()
                && entity instanceof LivingEntity living
                && !hasFrostWalker(living)) {
            entity.hurt(level.damageSources().hotFloor(), 1.0F);
        }
        super.stepOn(level, pos, state, entity);
    }

    private static boolean hasFrostWalker(LivingEntity living) {
        //? if >=1.21.1 {
        /*net.minecraft.core.Holder<net.minecraft.world.item.enchantment.Enchantment> frostWalker =
                living.level().registryAccess()
                        .lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                        .getOrThrow(net.minecraft.world.item.enchantment.Enchantments.FROST_WALKER);
        return EnchantmentHelper.getEnchantmentLevel(frostWalker, living) > 0;
        *///?} else {
        return EnchantmentHelper.hasFrostWalker(living);
        //?}
    }
}
