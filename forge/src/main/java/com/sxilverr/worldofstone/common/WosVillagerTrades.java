package com.sxilverr.worldofstone.common;

import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.api.enums.IgneousVariant;
import com.sxilverr.worldofstone.api.enums.MetamorphicVariant;
import com.sxilverr.worldofstone.api.enums.SedimentaryVariant;
import com.sxilverr.worldofstone.config.WosConfig;
import com.sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = ModInfo.MODID)
public final class WosVillagerTrades {

    private WosVillagerTrades() {
    }

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (!WosConfig.masonTradesEnabled) return;
        if (!WosConfig.stoneReplacementEnabled) return;
        if (event.getType() != VillagerProfession.MASON) return;

        List<Block> sellPool = new ArrayList<>();
        List<Block> buyPool = new ArrayList<>();

        for (IgneousVariant v : IgneousVariant.VALUES) {
            buyPool.add(WosBlocks.IGNEOUS_COBBLE.get(v).get());
            buyPool.add(WosBlocks.IGNEOUS_STONE.get(v).get());
            sellPool.add(WosBlocks.IGNEOUS_BRICK.get(v).get());
            sellPool.add(WosBlocks.IGNEOUS_SANDSTONE.get(v).get());
            sellPool.add(WosBlocks.IGNEOUS_SANDSTONE_CHISELED.get(v).get());
            sellPool.add(WosBlocks.IGNEOUS_SANDSTONE_SMOOTH.get(v).get());
            sellPool.add(WosBlocks.IGNEOUS_MOSSY_BRICK.get(v).get());
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            buyPool.add(WosBlocks.METAMORPHIC_COBBLE.get(v).get());
            buyPool.add(WosBlocks.METAMORPHIC_STONE.get(v).get());
            sellPool.add(WosBlocks.METAMORPHIC_BRICK.get(v).get());
            sellPool.add(WosBlocks.METAMORPHIC_SANDSTONE.get(v).get());
            sellPool.add(WosBlocks.METAMORPHIC_SANDSTONE_CHISELED.get(v).get());
            sellPool.add(WosBlocks.METAMORPHIC_SANDSTONE_SMOOTH.get(v).get());
            sellPool.add(WosBlocks.METAMORPHIC_MOSSY_BRICK.get(v).get());
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            buyPool.add(WosBlocks.SEDIMENTARY_COBBLE.get(v).get());
            buyPool.add(WosBlocks.SEDIMENTARY_STONE.get(v).get());
            sellPool.add(WosBlocks.SEDIMENTARY_BRICK.get(v).get());
            sellPool.add(WosBlocks.SEDIMENTARY_SANDSTONE.get(v).get());
            sellPool.add(WosBlocks.SEDIMENTARY_SANDSTONE_CHISELED.get(v).get());
            sellPool.add(WosBlocks.SEDIMENTARY_SANDSTONE_SMOOTH.get(v).get());
            sellPool.add(WosBlocks.SEDIMENTARY_MOSSY_BRICK.get(v).get());
        }

        List<Block> chiseledPool = new ArrayList<>();
        for (IgneousVariant v : IgneousVariant.VALUES) {
            chiseledPool.add(WosBlocks.IGNEOUS_CHISELED_BRICK.get(v).get());
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            chiseledPool.add(WosBlocks.METAMORPHIC_CHISELED_BRICK.get(v).get());
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            chiseledPool.add(WosBlocks.SEDIMENTARY_CHISELED_BRICK.get(v).get());
        }

        List<VillagerTrades.ItemListing> level2 = event.getTrades().get(2);
        for (Block b : chiseledPool) {
            level2.add(new BasicItemListing(1, new ItemStack(b, 4), 12, 5, 0.05F));
        }

        List<VillagerTrades.ItemListing> level3 = event.getTrades().get(3);

        for (Block b : buyPool) {
            level3.add(new BasicItemListing(new ItemStack(b, 16),
                    new ItemStack(Items.EMERALD, 1), 16, 10, 0.05F));
        }
        for (Block b : sellPool) {
            level3.add(new BasicItemListing(1, new ItemStack(b, 4), 12, 10, 0.05F));
        }

        if (WosConfig.allowPillarVariantTrades) {
            List<Block> pillarPool = new ArrayList<>();
            for (IgneousVariant v : IgneousVariant.VALUES) {
                pillarPool.add(WosBlocks.IGNEOUS_PILLAR.get(v).get());
            }
            for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
                pillarPool.add(WosBlocks.METAMORPHIC_PILLAR.get(v).get());
            }
            for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
                pillarPool.add(WosBlocks.SEDIMENTARY_PILLAR.get(v).get());
            }
            List<VillagerTrades.ItemListing> level5 = event.getTrades().get(5);
            for (Block b : pillarPool) {
                level5.add(new BasicItemListing(1, new ItemStack(b, 1), 12, 30, 0.2F));
            }
        }
    }
}
