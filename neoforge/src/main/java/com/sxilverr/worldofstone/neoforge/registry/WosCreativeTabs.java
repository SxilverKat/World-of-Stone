package com.sxilverr.worldofstone.neoforge.registry;

import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.api.enums.DecorativeSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.IgneousVariant;
import com.sxilverr.worldofstone.api.enums.MetamorphicVariant;
import com.sxilverr.worldofstone.api.enums.OreVariant;
import com.sxilverr.worldofstone.api.enums.QuarkSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.SedimentaryVariant;
import com.sxilverr.worldofstone.api.enums.TerracottaSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.VanillaOreHost;
import com.sxilverr.worldofstone.api.enums.VanillaSpeleothemVariant;
import com.sxilverr.worldofstone.registry.WosBlocks;
import com.sxilverr.worldofstone.registry.WosItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WosCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModInfo.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WOS_TAB = TABS.register("wos_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.worldofstone"))
                    .icon(() -> WosBlocks.IGNEOUS_STONE.get(IgneousVariant.ADAMELLITE).get().asItem().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        if (com.sxilverr.worldofstone.config.WosConfig.stoneReplacementEnabled)
                        for (IgneousVariant v : IgneousVariant.VALUES) {
                            output.accept(WosBlocks.IGNEOUS_STONE.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_COBBLE.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_COBBLE_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_COBBLE_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_COBBLE_WALL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_COBBLE.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_COBBLE_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_COBBLE_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_COBBLE_WALL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_BRICK.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_BRICK_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_BRICK_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_BRICK_WALL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_BRICK.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_BRICK_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_BRICK_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_MOSSY_BRICK_WALL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_CRACKED_BRICK.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_CHISELED_BRICK.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.enableInfestedBlocks) {
                                output.accept(WosBlocks.IGNEOUS_INFESTED.get(v).get());
                                output.accept(WosBlocks.IGNEOUS_INFESTED_COBBLE.get(v).get());
                                output.accept(WosBlocks.IGNEOUS_INFESTED_BRICK.get(v).get());
                                output.accept(WosBlocks.IGNEOUS_INFESTED_MOSSY_BRICK.get(v).get());
                                output.accept(WosBlocks.IGNEOUS_INFESTED_CRACKED_BRICK.get(v).get());
                                output.accept(WosBlocks.IGNEOUS_INFESTED_CHISELED_BRICK.get(v).get());
                            }
                            output.accept(WosBlocks.IGNEOUS_BUTTON.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SAND.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_WALL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_CHISELED.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_CUT.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_CUT_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_SMOOTH.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_SMOOTH_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SANDSTONE_SMOOTH_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_GRAVEL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_CLAY.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.IGNEOUS_SPELEOTHEM.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.isSandstoneSpeleothemEnabled(v)) output.accept(WosBlocks.IGNEOUS_SANDSTONE_SPELEOTHEM.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.allowOvergrownStrata) output.accept(WosBlocks.IGNEOUS_OVERGROWN.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.allowSnowedStrata) output.accept(WosBlocks.IGNEOUS_OVERGROWN_SNOWED.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SMOOTH.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_SMOOTH_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_POLISHED.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_POLISHED_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_POLISHED_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_POLISHED_WALL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_TILE.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_CRACKED_TILE.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_TILE_STAIRS.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_TILE_SLAB.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_TILE_WALL.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_PILLAR.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.replaceVanillaOres)
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(v.toString() + "_" + ore.suffix).get());
                            }
                        }
                        if (com.sxilverr.worldofstone.config.WosConfig.stoneReplacementEnabled)
                        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
                            output.accept(WosBlocks.METAMORPHIC_STONE.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_COBBLE.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_COBBLE_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_COBBLE_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_COBBLE_WALL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_COBBLE.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_COBBLE_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_COBBLE_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_COBBLE_WALL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_BRICK.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_BRICK_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_BRICK_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_BRICK_WALL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_BRICK.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_BRICK_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_BRICK_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_MOSSY_BRICK_WALL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_CRACKED_BRICK.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_CHISELED_BRICK.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.enableInfestedBlocks) {
                                output.accept(WosBlocks.METAMORPHIC_INFESTED.get(v).get());
                                output.accept(WosBlocks.METAMORPHIC_INFESTED_COBBLE.get(v).get());
                                output.accept(WosBlocks.METAMORPHIC_INFESTED_BRICK.get(v).get());
                                output.accept(WosBlocks.METAMORPHIC_INFESTED_MOSSY_BRICK.get(v).get());
                                output.accept(WosBlocks.METAMORPHIC_INFESTED_CRACKED_BRICK.get(v).get());
                                output.accept(WosBlocks.METAMORPHIC_INFESTED_CHISELED_BRICK.get(v).get());
                            }
                            output.accept(WosBlocks.METAMORPHIC_BUTTON.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SAND.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_WALL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_CHISELED.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_CUT.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_CUT_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_SMOOTH.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_SMOOTH_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SANDSTONE_SMOOTH_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_GRAVEL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_CLAY.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.METAMORPHIC_SPELEOTHEM.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.isSandstoneSpeleothemEnabled(v)) output.accept(WosBlocks.METAMORPHIC_SANDSTONE_SPELEOTHEM.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.allowOvergrownStrata) output.accept(WosBlocks.METAMORPHIC_OVERGROWN.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.allowSnowedStrata) output.accept(WosBlocks.METAMORPHIC_OVERGROWN_SNOWED.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SMOOTH.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_SMOOTH_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_POLISHED.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_POLISHED_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_POLISHED_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_POLISHED_WALL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_TILE.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_CRACKED_TILE.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_TILE_STAIRS.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_TILE_SLAB.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_TILE_WALL.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_PILLAR.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.replaceVanillaOres)
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(v.toString() + "_" + ore.suffix).get());
                            }
                        }
                        if (com.sxilverr.worldofstone.config.WosConfig.stoneReplacementEnabled)
                        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
                            output.accept(WosBlocks.SEDIMENTARY_STONE.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_COBBLE.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_COBBLE_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_COBBLE_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_COBBLE_WALL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_COBBLE.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_COBBLE_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_COBBLE_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_COBBLE_WALL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_BRICK.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_BRICK_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_BRICK_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_BRICK_WALL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_BRICK.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_BRICK_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_BRICK_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_MOSSY_BRICK_WALL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_CRACKED_BRICK.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_CHISELED_BRICK.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.enableInfestedBlocks) {
                                output.accept(WosBlocks.SEDIMENTARY_INFESTED.get(v).get());
                                output.accept(WosBlocks.SEDIMENTARY_INFESTED_COBBLE.get(v).get());
                                output.accept(WosBlocks.SEDIMENTARY_INFESTED_BRICK.get(v).get());
                                output.accept(WosBlocks.SEDIMENTARY_INFESTED_MOSSY_BRICK.get(v).get());
                                output.accept(WosBlocks.SEDIMENTARY_INFESTED_CRACKED_BRICK.get(v).get());
                                output.accept(WosBlocks.SEDIMENTARY_INFESTED_CHISELED_BRICK.get(v).get());
                            }
                            output.accept(WosBlocks.SEDIMENTARY_BUTTON.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SAND.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_WALL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_CHISELED.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_CUT.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_CUT_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_SMOOTH.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_SMOOTH_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_SMOOTH_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_GRAVEL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_CLAY.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.SEDIMENTARY_SPELEOTHEM.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.isSandstoneSpeleothemEnabled(v)) output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_SPELEOTHEM.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.allowOvergrownStrata) output.accept(WosBlocks.SEDIMENTARY_OVERGROWN.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.allowSnowedStrata) output.accept(WosBlocks.SEDIMENTARY_OVERGROWN_SNOWED.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SMOOTH.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_SMOOTH_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_POLISHED.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_POLISHED_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_POLISHED_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_POLISHED_WALL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_TILE.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_CRACKED_TILE.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_TILE_STAIRS.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_TILE_SLAB.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_TILE_WALL.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_PILLAR.get(v).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.replaceVanillaOres)
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(v.toString() + "_" + ore.suffix).get());
                            }
                        }
                        if (com.sxilverr.worldofstone.config.WosConfig.replaceVanillaOres)
                        for (VanillaOreHost host : VanillaOreHost.VALUES) {
                            if (host.getDimension() == VanillaOreHost.HostDimension.NETHER
                                    && !com.sxilverr.worldofstone.config.WosConfig.allowVanillaOresInNether) continue;
                            if (host.getDimension() == VanillaOreHost.HostDimension.END
                                    && !com.sxilverr.worldofstone.config.WosConfig.allowVanillaOresInEnd) continue;
                            if (host.getDimension() == VanillaOreHost.HostDimension.ANY
                                    && !com.sxilverr.worldofstone.config.WosConfig.allowObsidianOreVariants) continue;
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(host.getRegistryName() + "_" + ore.suffix).get());
                            }
                        }
                        for (String host : new String[]{"stone", "granite", "diorite", "andesite", "tuff"}) {
                            if (com.sxilverr.worldofstone.config.WosConfig.allowOvergrownVanillaVariants) output.accept(WosBlocks.VANILLA_HOST_OVERGROWN.get(host).get());
                            if (com.sxilverr.worldofstone.config.WosConfig.allowSnowedVanillaVariants) output.accept(WosBlocks.VANILLA_HOST_SNOWED.get(host).get());
                        }
                        for (VanillaSpeleothemVariant v : VanillaSpeleothemVariant.VALUES) {
                            if (com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.VANILLA_SPELEOTHEM.get(v).get());
                        }
                        for (TerracottaSpeleothemVariant v : TerracottaSpeleothemVariant.VALUES) {
                            if (com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.TERRACOTTA_SPELEOTHEM.get(v).get());
                        }
                        for (QuarkSpeleothemVariant v : QuarkSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.QUARK_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.UNDERGARDEN_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.CREATE_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.BETTEREND_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.GALOSPHERE_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.BWG_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.TWILIGHTFOREST_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.AETHER_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.BLUE_SKIES_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant v : com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.SPELUNKERY_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.ICEANDFIRE_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.MYSTICALAGRICULTURE_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant v : com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.BIOMESOPLENTY_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.FORBIDDEN_ARCANUS_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.ALEXSCAVES_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.ARS_NOUVEAU_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.CATACLYSM_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.TWIGS_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.ARCHITECTS_PALETTE_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.OUTER_END_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.BOTANIA_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.AD_ASTRA_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.DEEP_AETHER_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.CAVERNS_AND_CHASMS_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.ATMOSPHERIC_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.ENDERGETIC_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.WILDER_WILDS_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.REGIONS_UNEXPLORED_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.BORN_IN_CHAOS_V1_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.NATURALIST_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.YUNGSCAVEBIOMES_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.NATURES_SPIRIT_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.NETHEREXP_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.DEEPERDARKER_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.THE_DEEP_VOID_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (com.sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.DEFILED_LANDS_PREBORN_SPELEOTHEM.get(v);
                            if (ro != null && com.sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (DecorativeSpeleothemVariant v : DecorativeSpeleothemVariant.VALUES) {
                            dev.architectury.registry.registries.RegistrySupplier<net.minecraft.world.level.block.Block> ro = WosBlocks.DECORATIVE_SPELEOTHEM.get(v);
                            if (ro != null && ro.isPresent() && com.sxilverr.worldofstone.config.WosConfig.isCreativeSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        if (com.sxilverr.worldofstone.config.WosConfig.speleothemsEnabled
                                && WosBlocks.MIMIC_SPELEOTHEM != null && WosBlocks.MIMIC_SPELEOTHEM.isPresent())
                        for (String blockId : com.sxilverr.worldofstone.config.WosConfig.mimicSpeleothemBlocks) {
                            net.minecraft.resources.ResourceLocation rl = net.minecraft.resources.ResourceLocation.tryParse(blockId);
                            if (rl == null) continue;
                            net.minecraft.world.level.block.Block source = BuiltInRegistries.BLOCK.containsKey(rl)
                                    ? BuiltInRegistries.BLOCK.get(rl) : null;
                            if (source == null || source == net.minecraft.world.level.block.Blocks.AIR) continue;
                            net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack(WosBlocks.MIMIC_SPELEOTHEM.get());
                            net.minecraft.nbt.CompoundTag tag = new net.minecraft.nbt.CompoundTag();
                            tag.putString("Source", rl.toString());
                            stack.set(DataComponents.BLOCK_ENTITY_DATA, net.minecraft.world.item.component.CustomData.of(tag));
                            stack.set(DataComponents.CUSTOM_NAME, net.minecraft.network.chat.Component.literal(source.getName().getString() + " Speleothem"));
                            output.accept(stack);
                        }
                        if (com.sxilverr.worldofstone.config.WosConfig.stoneReplacementEnabled) output.accept(WosItems.LIGNITE_COAL.get());
                        if (com.sxilverr.worldofstone.config.WosConfig.isFossilsEnabled()) WosItems.FOSSILS.forEach((k, ro) -> output.accept(ro.get()));
                    })
                    .build());

    public static void init() {
    }

    private WosCreativeTabs() {
    }
}
