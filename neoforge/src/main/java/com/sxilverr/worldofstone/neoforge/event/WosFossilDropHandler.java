package com.sxilverr.worldofstone.neoforge.event;

import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.api.enums.FossilVariant;
import com.sxilverr.worldofstone.config.WosConfig;
import com.sxilverr.worldofstone.registry.WosItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.Random;

@EventBusSubscriber(modid = ModInfo.MODID)
public final class WosFossilDropHandler {

    private static final Random RANDOM = new Random();

    private WosFossilDropHandler() {
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!WosConfig.isFossilsEnabled()) return;
        if (WosConfig.fossilDropBlocks.isEmpty()) return;
        if (event.getPlayer() == null || event.getPlayer().isCreative()) return;
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        ItemStack tool = event.getPlayer().getMainHandItem();
        Holder<Enchantment> silk = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.SILK_TOUCH);
        if (EnchantmentHelper.getItemEnchantmentLevel(silk, tool) > 0) return;

        BlockState state = event.getState();
        Block block = state.getBlock();
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        if (!WosConfig.fossilDropBlocks.contains(key.toString())) return;

        if (RANDOM.nextDouble() >= WosConfig.fossilDropChance) return;

        ItemStack fossil = pickRandomFossil();
        if (fossil.isEmpty()) return;

        BlockPos pos = event.getPos();
        ItemEntity drop = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, fossil);
        drop.setDefaultPickUpDelay();
        level.addFreshEntity(drop);
    }

    private static ItemStack pickRandomFossil() {
        int roll = RANDOM.nextInt(9);
        if (roll < 3) return new ItemStack(WosItems.FOSSILS.get(FossilVariant.BONE_FOSSIL).get());
        if (roll < 5) return new ItemStack(WosItems.FOSSILS.get(FossilVariant.RIB_FOSSIL).get());
        if (roll < 7) return new ItemStack(WosItems.FOSSILS.get(FossilVariant.SHELL_FOSSIL).get());
        if (roll < 8) return new ItemStack(WosItems.FOSSILS.get(FossilVariant.AMMONITE_FOSSIL).get());
        return new ItemStack(WosItems.FOSSILS.get(FossilVariant.SKULL_FOSSIL).get());
    }
}
