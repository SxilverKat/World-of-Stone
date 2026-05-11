package sxilverr.worldofstone.registry;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.DecorativeSpeleothemVariant;
import sxilverr.worldofstone.api.enums.IgneousVariant;
import sxilverr.worldofstone.api.enums.MetamorphicVariant;
import sxilverr.worldofstone.api.enums.OreVariant;
import sxilverr.worldofstone.api.enums.QuarkSpeleothemVariant;
import sxilverr.worldofstone.api.enums.SedimentaryVariant;
import sxilverr.worldofstone.api.enums.TerracottaSpeleothemVariant;
import sxilverr.worldofstone.api.enums.VanillaOreHost;
import sxilverr.worldofstone.api.enums.VanillaSpeleothemVariant;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class WosCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModInfo.MODID);

    public static final RegistryObject<CreativeModeTab> UB_TAB = TABS.register("ub_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.worldofstone"))
                    .icon(() -> WosBlocks.IGNEOUS_STONE.get(IgneousVariant.ADAMELLITE).get().asItem().getDefaultInstance())
                    .displayItems((parameters, output) -> {
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
                            if (sxilverr.worldofstone.config.WosConfig.enableInfestedBlocks) {
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
                            if (sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.IGNEOUS_SPELEOTHEM.get(v).get());
                            if (sxilverr.worldofstone.config.WosConfig.isSandstoneSpeleothemEnabled(v)) output.accept(WosBlocks.IGNEOUS_SANDSTONE_SPELEOTHEM.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_OVERGROWN.get(v).get());
                            output.accept(WosBlocks.IGNEOUS_OVERGROWN_SNOWED.get(v).get());
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
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(v.toString() + "_" + ore.suffix).get());
                            }
                        }
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
                            if (sxilverr.worldofstone.config.WosConfig.enableInfestedBlocks) {
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
                            if (sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.METAMORPHIC_SPELEOTHEM.get(v).get());
                            if (sxilverr.worldofstone.config.WosConfig.isSandstoneSpeleothemEnabled(v)) output.accept(WosBlocks.METAMORPHIC_SANDSTONE_SPELEOTHEM.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_OVERGROWN.get(v).get());
                            output.accept(WosBlocks.METAMORPHIC_OVERGROWN_SNOWED.get(v).get());
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
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(v.toString() + "_" + ore.suffix).get());
                            }
                        }
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
                            if (sxilverr.worldofstone.config.WosConfig.enableInfestedBlocks) {
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
                            if (sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.SEDIMENTARY_SPELEOTHEM.get(v).get());
                            if (sxilverr.worldofstone.config.WosConfig.isSandstoneSpeleothemEnabled(v)) output.accept(WosBlocks.SEDIMENTARY_SANDSTONE_SPELEOTHEM.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_OVERGROWN.get(v).get());
                            output.accept(WosBlocks.SEDIMENTARY_OVERGROWN_SNOWED.get(v).get());
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
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(v.toString() + "_" + ore.suffix).get());
                            }
                        }
                        for (VanillaOreHost host : VanillaOreHost.VALUES) {
                            for (OreVariant ore : OreVariant.VALUES) {
                                output.accept(WosBlocks.ORES.get(host.getRegistryName() + "_" + ore.suffix).get());
                            }
                        }
                        for (String host : new String[]{"stone", "granite", "diorite", "andesite", "tuff"}) {
                            output.accept(WosBlocks.VANILLA_HOST_OVERGROWN.get(host).get());
                            output.accept(WosBlocks.VANILLA_HOST_SNOWED.get(host).get());
                        }
                        for (VanillaSpeleothemVariant v : VanillaSpeleothemVariant.VALUES) {
                            if (sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.VANILLA_SPELEOTHEM.get(v).get());
                        }
                        for (TerracottaSpeleothemVariant v : TerracottaSpeleothemVariant.VALUES) {
                            if (sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(WosBlocks.TERRACOTTA_SPELEOTHEM.get(v).get());
                        }
                        for (QuarkSpeleothemVariant v : QuarkSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.QUARK_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant v : sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.UNDERGARDEN_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.CreateSpeleothemVariant v : sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.CREATE_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant v : sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.BETTEREND_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant v : sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.GALOSPHERE_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.BwgSpeleothemVariant v : sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.BWG_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant v : sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.TWILIGHTFOREST_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.AetherSpeleothemVariant v : sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.AETHER_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant v : sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.BLUE_SKIES_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant v : sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.SPELUNKERY_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v : sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.ICEANDFIRE_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v : sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.MYSTICALAGRICULTURE_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant v : sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.BIOMESOPLENTY_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant v : sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.FORBIDDEN_ARCANUS_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant v : sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.ALEXSCAVES_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v : sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.ARS_NOUVEAU_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v : sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.CATACLYSM_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant v : sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.TWIGS_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant v : sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.ARCHITECTS_PALETTE_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant v : sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.OUTER_END_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant v : sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.BOTANIA_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant v : sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.AD_ASTRA_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant v : sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.DEEP_AETHER_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant v : sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.CAVERNS_AND_CHASMS_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant v : sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.ATMOSPHERIC_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v : sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.ENDERGETIC_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant v : sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.WILDER_WILDS_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant v : sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.REGIONS_UNEXPLORED_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v : sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.BORN_IN_CHAOS_V1_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v : sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.NATURALIST_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v : sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.YUNGSCAVEBIOMES_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant v : sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.NATURES_SPIRIT_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant v : sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.NETHEREXP_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant v : sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.DEEPERDARKER_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant v : sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.THE_DEEP_VOID_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        for (sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v : sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.VALUES) {
                            net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.DEFILED_LANDS_PREBORN_SPELEOTHEM.get(v);
                            if (ro != null && sxilverr.worldofstone.config.WosConfig.isSpeleothemEnabled(v)) output.accept(ro.get());
                        }
                        if (sxilverr.worldofstone.config.WosConfig.speleothemsEnabled) {
                            for (DecorativeSpeleothemVariant v : DecorativeSpeleothemVariant.VALUES) {
                                net.minecraftforge.registries.RegistryObject<net.minecraft.world.level.block.Block> ro = WosBlocks.DECORATIVE_SPELEOTHEM.get(v);
                                if (ro != null && ro.isPresent()) output.accept(ro.get());
                            }
                        }
                        for (String blockId : sxilverr.worldofstone.config.WosConfig.mimicSpeleothemBlocks) {
                            net.minecraft.resources.ResourceLocation rl = net.minecraft.resources.ResourceLocation.tryParse(blockId);
                            if (rl == null) continue;
                            net.minecraft.world.level.block.Block source = net.minecraftforge.registries.ForgeRegistries.BLOCKS.getValue(rl);
                            if (source == null || source == net.minecraft.world.level.block.Blocks.AIR) continue;
                            net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack(WosBlocks.MIMIC_SPELEOTHEM.get());
                            net.minecraft.nbt.CompoundTag tag = new net.minecraft.nbt.CompoundTag();
                            tag.putString("Source", rl.toString());
                            stack.addTagElement("BlockEntityTag", tag);
                            stack.setHoverName(net.minecraft.network.chat.Component.literal(source.getName().getString() + " Speleothem"));
                            output.accept(stack);
                        }
                        output.accept(WosItems.LIGNITE_COAL.get());
                        WosItems.FOSSILS.forEach((k, ro) -> output.accept(ro.get()));
                    })
                    .build());

    public static void init() {
    }

    private WosCreativeTabs() {
    }
}
