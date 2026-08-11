package com.sxilverr.worldofstone.forge.registry;

import com.sxilverr.worldofstone.registry.WosBlocks;
import com.sxilverr.worldofstone.registry.WosItems;
import com.sxilverr.worldofstone.forge.block.WosDevSpeleothemBlock;
import com.sxilverr.worldofstone.forge.block.WosMimicSpeleothemBlock;
import com.sxilverr.worldofstone.forge.block.WosMimicSpeleothemBlockEntity;
import com.sxilverr.worldofstone.world.MimicHook;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class WosLoaderBlocks {

    public static final SoundType DEV_SPELEOTHEM_SOUNDS = new SoundType(
            1.0F, 1.0F,
            SoundEvents.CAT_DEATH,
            SoundEvents.CAT_AMBIENT,
            SoundEvents.CAT_STRAY_AMBIENT,
            SoundEvents.CAT_AMBIENT,
            SoundEvents.CAT_AMBIENT
    );

    public static void register() {
        WosBlocks.DEV_SPELEOTHEM = WosBlocks.BLOCKS.register("dev_speleothem", () -> new WosDevSpeleothemBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.METAL)
                        .sound(DEV_SPELEOTHEM_SOUNDS)
                        .strength(50.0F, 1200.0F)
                        .noOcclusion()
                        .dynamicShape()
                        .requiresCorrectToolForDrops()));
        WosBlocks.DEV_SPELEOTHEM_ITEM = WosItems.ITEMS.register("dev_speleothem",
                () -> new BlockItem(WosBlocks.DEV_SPELEOTHEM.get(), new Item.Properties()));

        WosBlocks.MIMIC_SPELEOTHEM = WosBlocks.BLOCKS.register("mimic_speleothem",
                () -> new WosMimicSpeleothemBlock(
                        BlockBehaviour.Properties.of()
                                .mapColor(MapColor.STONE)
                                .sound(SoundType.STONE)
                                .strength(1.5F, 6.0F)
                                .noOcclusion()
                                .dynamicShape()
                                .requiresCorrectToolForDrops()));
        WosBlocks.MIMIC_SPELEOTHEM_ITEM = WosItems.ITEMS.register("mimic_speleothem",
                () -> new BlockItem(WosBlocks.MIMIC_SPELEOTHEM.get(), new Item.Properties()));

        MimicHook.INSTANCE = (level, pos, hostState) -> {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
                mimic.setSource(hostState);
            }
        };
    }

    private WosLoaderBlocks() {
    }
}
