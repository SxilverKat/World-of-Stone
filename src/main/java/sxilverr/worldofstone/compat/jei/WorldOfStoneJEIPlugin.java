package sxilverr.worldofstone.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.DecorativeSpeleothemVariant;
import sxilverr.worldofstone.api.enums.OreVariant;
import sxilverr.worldofstone.api.enums.VanillaOreHost;
import sxilverr.worldofstone.config.WosConfig;
import sxilverr.worldofstone.registry.WosBlocks;
import sxilverr.worldofstone.registry.WosItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JeiPlugin
public class WorldOfStoneJEIPlugin implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(ModInfo.MODID, "jei");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        List<ItemStack> hide = new ArrayList<>();

        if (!WosConfig.stoneReplacementEnabled) {
            addTag(hide, "stone_variants");
            add(hide, WosItems.LIGNITE_COAL);
        }

        if (!WosConfig.enableInfestedBlocks) {
            addBlockMaps(hide,
                    WosBlocks.IGNEOUS_INFESTED, WosBlocks.IGNEOUS_INFESTED_COBBLE, WosBlocks.IGNEOUS_INFESTED_BRICK,
                    WosBlocks.IGNEOUS_INFESTED_MOSSY_BRICK, WosBlocks.IGNEOUS_INFESTED_CRACKED_BRICK, WosBlocks.IGNEOUS_INFESTED_CHISELED_BRICK,
                    WosBlocks.METAMORPHIC_INFESTED, WosBlocks.METAMORPHIC_INFESTED_COBBLE, WosBlocks.METAMORPHIC_INFESTED_BRICK,
                    WosBlocks.METAMORPHIC_INFESTED_MOSSY_BRICK, WosBlocks.METAMORPHIC_INFESTED_CRACKED_BRICK, WosBlocks.METAMORPHIC_INFESTED_CHISELED_BRICK,
                    WosBlocks.SEDIMENTARY_INFESTED, WosBlocks.SEDIMENTARY_INFESTED_COBBLE, WosBlocks.SEDIMENTARY_INFESTED_BRICK,
                    WosBlocks.SEDIMENTARY_INFESTED_MOSSY_BRICK, WosBlocks.SEDIMENTARY_INFESTED_CRACKED_BRICK, WosBlocks.SEDIMENTARY_INFESTED_CHISELED_BRICK);
        }

        if (!WosConfig.isFossilsEnabled()) {
            for (RegistryObject<Item> ro : WosItems.FOSSILS.values()) {
                add(hide, ro);
            }
        }

        for (DecorativeSpeleothemVariant v : DecorativeSpeleothemVariant.VALUES) {
            if (!WosConfig.isCreativeSpeleothemEnabled(v)) {
                addDecorative(hide, v);
            }
        }

        if (!WosConfig.allowVanillaOresInNether) {
            addHostOres(hide, VanillaOreHost.HostDimension.NETHER);
        }
        if (!WosConfig.allowVanillaOresInEnd) {
            addHostOres(hide, VanillaOreHost.HostDimension.END);
        }
        if (!WosConfig.allowObsidianOreVariants) {
            addHostOres(hide, VanillaOreHost.HostDimension.ANY);
        }

        if (!WosConfig.replaceVanillaOres) {
            addBlockMaps(hide, WosBlocks.ORES);
        }

        if (!WosConfig.allowOvergrownStrata) {
            addBlockMaps(hide, WosBlocks.IGNEOUS_OVERGROWN, WosBlocks.METAMORPHIC_OVERGROWN, WosBlocks.SEDIMENTARY_OVERGROWN);
        }
        if (!WosConfig.allowSnowedStrata) {
            addBlockMaps(hide, WosBlocks.IGNEOUS_OVERGROWN_SNOWED, WosBlocks.METAMORPHIC_OVERGROWN_SNOWED, WosBlocks.SEDIMENTARY_OVERGROWN_SNOWED);
        }
        if (!WosConfig.allowOvergrownVanillaVariants) {
            addBlockMaps(hide, WosBlocks.VANILLA_HOST_OVERGROWN);
        }
        if (!WosConfig.allowSnowedVanillaVariants) {
            addBlockMaps(hide, WosBlocks.VANILLA_HOST_SNOWED);
        }

        if (!WosConfig.speleothemsEnabled) {
            addTag(hide, "speleothem");
            add(hide, WosBlocks.MIMIC_SPELEOTHEM);
        }

        if (hide.isEmpty()) {
            return;
        }

        IIngredientManager manager = jeiRuntime.getIngredientManager();
        manager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, hide);
    }

    private static void addTag(List<ItemStack> hide, String tagPath) {
        TagKey<Item> tag = TagKey.create(Registries.ITEM, new ResourceLocation(ModInfo.MODID, tagPath));
        for (Item item : ForgeRegistries.ITEMS.tags().getTag(tag)) {
            hide.add(new ItemStack(item));
        }
    }

    private static void add(List<ItemStack> hide, RegistryObject<? extends ItemLike> ro) {
        if (ro != null && ro.isPresent()) {
            hide.add(new ItemStack(ro.get()));
        }
    }

    private static void addDecorative(List<ItemStack> hide, DecorativeSpeleothemVariant v) {
        RegistryObject<Block> ro = WosBlocks.DECORATIVE_SPELEOTHEM.get(v);
        if (ro != null && ro.isPresent()) {
            hide.add(new ItemStack(ro.get()));
        }
    }

    @SafeVarargs
    private static void addBlockMaps(List<ItemStack> hide, Map<?, RegistryObject<Block>>... maps) {
        for (Map<?, RegistryObject<Block>> map : maps) {
            for (RegistryObject<Block> ro : map.values()) {
                if (ro != null && ro.isPresent()) {
                    hide.add(new ItemStack(ro.get()));
                }
            }
        }
    }

    private static void addHostOres(List<ItemStack> hide, VanillaOreHost.HostDimension dimension) {
        for (VanillaOreHost host : VanillaOreHost.VALUES) {
            if (host.getDimension() != dimension) {
                continue;
            }
            for (OreVariant ore : OreVariant.VALUES) {
                RegistryObject<Block> ro = WosBlocks.ORES.get(host.getRegistryName() + "_" + ore.suffix);
                if (ro != null && ro.isPresent()) {
                    hide.add(new ItemStack(ro.get()));
                }
            }
        }
    }
}
