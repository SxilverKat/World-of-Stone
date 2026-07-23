package com.sxilverr.worldofstone.neoforge.registry;

import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.neoforge.block.WosMimicSpeleothemBlockEntity;
import com.sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WosBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ModInfo.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WosMimicSpeleothemBlockEntity>> MIMIC_SPELEOTHEM =
            BES.register("mimic_speleothem",
                    () -> BlockEntityType.Builder.of(WosMimicSpeleothemBlockEntity::new, WosBlocks.MIMIC_SPELEOTHEM.get()).build(null));

    public static void init() {
    }

    private WosBlockEntities() {
    }
}
