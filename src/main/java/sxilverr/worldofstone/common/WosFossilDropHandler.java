package sxilverr.worldofstone.common;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.config.WosConfig;
import sxilverr.worldofstone.registry.WosItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ModInfo.MODID)
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
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0) return;

        BlockState state = event.getState();
        Block block = state.getBlock();
        ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
        if (key == null) return;
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
        if (roll < 3) return new ItemStack(WosItems.FOSSILS.get(sxilverr.worldofstone.api.enums.FossilVariant.BONE_FOSSIL).get());
        if (roll < 5) return new ItemStack(WosItems.FOSSILS.get(sxilverr.worldofstone.api.enums.FossilVariant.RIB_FOSSIL).get());
        if (roll < 7) return new ItemStack(WosItems.FOSSILS.get(sxilverr.worldofstone.api.enums.FossilVariant.SHELL_FOSSIL).get());
        if (roll < 8) return new ItemStack(WosItems.FOSSILS.get(sxilverr.worldofstone.api.enums.FossilVariant.AMMONITE_FOSSIL).get());
        return new ItemStack(WosItems.FOSSILS.get(sxilverr.worldofstone.api.enums.FossilVariant.SKULL_FOSSIL).get());
    }
}
