package sxilverr.worldofstone.compat.jer;

import sxilverr.worldofstone.api.enums.FossilVariant;
import sxilverr.worldofstone.api.enums.OreVariant;
import sxilverr.worldofstone.api.enums.SedimentaryVariant;
import sxilverr.worldofstone.registry.WosBlocks;
import sxilverr.worldofstone.registry.WosItems;
import net.minecraft.world.item.Item;
import com.mojang.logging.LogUtils;
import jeresources.api.IJERAPI;
import jeresources.api.IWorldGenRegistry;
import jeresources.api.conditionals.Conditional;
import jeresources.api.distributions.DistributionBase;
import jeresources.api.distributions.DistributionSquare;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.DimensionRestriction;
import jeresources.api.restrictions.Restriction;
import jeresources.compatibility.api.JERAPI;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class WorldOfStoneJERPlugin {
    private static final Logger LOGGER = LogUtils.getLogger();

    private WorldOfStoneJERPlugin() {
    }

    public static void register() {
        IJERAPI api;
        try {
            api = JERAPI.getInstance();
        } catch (Throwable t) {
            LOGGER.warn("[WorldOfStone] JER API unavailable: {}", t.toString());
            return;
        }
        if (api == null) {
            LOGGER.warn("[WorldOfStone] JER API was null");
            return;
        }

        IWorldGenRegistry registry = api.getWorldGenRegistry();
        Restriction overworld = new Restriction(DimensionRestriction.OVERWORLD);
        DistributionBase sedimentaryDist = new DistributionSquare(20, 80, 0.01f);

        RegistryObject<Block> lignite = WosBlocks.SEDIMENTARY_STONE.get(SedimentaryVariant.LIGNITE);
        if (lignite != null && lignite.isPresent() && WosItems.LIGNITE_COAL.isPresent()) {
            registry.register(
                    new ItemStack(lignite.get()),
                    sedimentaryDist,
                    overworld,
                    new LootDrop(new ItemStack(WosItems.LIGNITE_COAL.get()), 1, 1)
            );
        }

        SedimentaryVariant[] fossilStones = {
                SedimentaryVariant.CHALK,
                SedimentaryVariant.LIMESTONE,
                SedimentaryVariant.DOLOMITE,
                SedimentaryVariant.SHALE
        };
        List<LootDrop> fossilDrops = new ArrayList<>();
        for (FossilVariant fv : FossilVariant.VALUES) {
            RegistryObject<Item> fossil = WosItems.FOSSILS.get(fv);
            if (fossil != null && fossil.isPresent()) {
                fossilDrops.add(new LootDrop(new ItemStack(fossil.get()), 0.01f, 0));
            }
        }
        if (!fossilDrops.isEmpty()) {
            LootDrop[] dropsArr = fossilDrops.toArray(new LootDrop[0]);
            for (SedimentaryVariant v : fossilStones) {
                RegistryObject<Block> stone = WosBlocks.SEDIMENTARY_STONE.get(v);
                if (stone != null && stone.isPresent()) {
                    registry.register(new ItemStack(stone.get()), sedimentaryDist, overworld, dropsArr);
                }
            }
        }

        registerOtherDimensionOres(registry);

        LOGGER.info("[WorldOfStone] JER plugin: registered lignite/fossils; ore variants surface via mixin");
    }

    private static Map<OreVariant, LootDrop> oreDrops() {
        Map<OreVariant, LootDrop> drops = new EnumMap<>(OreVariant.class);
        drops.put(OreVariant.COAL, new LootDrop(new ItemStack(Items.COAL), 1, 1, Conditional.affectedByFortune));
        drops.put(OreVariant.IRON, new LootDrop(new ItemStack(Items.RAW_IRON), 1, 1, Conditional.affectedByFortune));
        drops.put(OreVariant.GOLD, new LootDrop(new ItemStack(Items.RAW_GOLD), 1, 1, Conditional.affectedByFortune));
        drops.put(OreVariant.DIAMOND, new LootDrop(new ItemStack(Items.DIAMOND), 1, 1, Conditional.affectedByFortune));
        drops.put(OreVariant.EMERALD, new LootDrop(new ItemStack(Items.EMERALD), 1, 1, Conditional.affectedByFortune));
        drops.put(OreVariant.REDSTONE, new LootDrop(new ItemStack(Items.REDSTONE), 4, 5, Conditional.affectedByFortune));
        drops.put(OreVariant.LAPIS, new LootDrop(new ItemStack(Items.LAPIS_LAZULI), 4, 9, Conditional.affectedByFortune));
        drops.put(OreVariant.COPPER, new LootDrop(new ItemStack(Items.RAW_COPPER), 2, 5, Conditional.affectedByFortune));
        return drops;
    }

    private static void registerOtherDimensionOres(IWorldGenRegistry registry) {
        Map<OreVariant, LootDrop> drops = oreDrops();
        if (sxilverr.worldofstone.config.WosConfig.allowVanillaOresInNether) {
            Restriction nether = new Restriction(DimensionRestriction.NETHER);
            DistributionBase netherDist = new DistributionSquare(8, 8, 0, 128);
            for (OreVariant ore : OreVariant.VALUES) {
                RegistryObject<Block> block = sxilverr.worldofstone.registry.WosBlocks.ORES.get("netherrack_" + ore.suffix);
                LootDrop drop = drops.get(ore);
                if (block != null && block.isPresent() && drop != null) {
                    registry.register(new ItemStack(block.get()), netherDist, nether, drop);
                }
            }
        }
        if (sxilverr.worldofstone.config.WosConfig.allowVanillaOresInEnd) {
            Restriction end = new Restriction(DimensionRestriction.END);
            DistributionBase endDist = new DistributionSquare(4, 6, 0, 128);
            for (OreVariant ore : OreVariant.VALUES) {
                RegistryObject<Block> block = sxilverr.worldofstone.registry.WosBlocks.ORES.get("end_stone_" + ore.suffix);
                LootDrop drop = drops.get(ore);
                if (block != null && block.isPresent() && drop != null) {
                    registry.register(new ItemStack(block.get()), endDist, end, drop);
                }
            }
        }
        if (sxilverr.worldofstone.config.WosConfig.allowObsidianOreVariants) {
            Restriction anyDim = new Restriction(DimensionRestriction.NONE);
            DistributionBase obsidianDist = new DistributionSquare(2, 2, 0, 128);
            for (OreVariant ore : OreVariant.VALUES) {
                RegistryObject<Block> block = sxilverr.worldofstone.registry.WosBlocks.ORES.get("obsidian_" + ore.suffix);
                LootDrop drop = drops.get(ore);
                if (block != null && block.isPresent() && drop != null) {
                    registry.register(new ItemStack(block.get()), obsidianDist, anyDim, drop);
                }
            }
        }
    }
}
