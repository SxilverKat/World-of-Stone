package com.sxilverr.worldofstone.forge.registry;

import com.sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.forge.block.WosMimicSpeleothemBlockEntity;

public final class WosBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ModInfo.MODID);

    public static final RegistryObject<BlockEntityType<WosMimicSpeleothemBlockEntity>> MIMIC_SPELEOTHEM =
            BES.register("mimic_speleothem",
                    () -> BlockEntityType.Builder.of(WosMimicSpeleothemBlockEntity::new, WosBlocks.MIMIC_SPELEOTHEM.get()).build(null));

    private WosBlockEntities() {}

    public static void init() {}
}
