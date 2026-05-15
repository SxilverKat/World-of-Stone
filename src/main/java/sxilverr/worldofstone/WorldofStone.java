package sxilverr.worldofstone;

import com.mojang.logging.LogUtils;
import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.config.WosConfig;
import sxilverr.worldofstone.registry.WosBlockEntities;
import sxilverr.worldofstone.registry.WosBlocks;
import sxilverr.worldofstone.registry.WosCreativeTabs;
import sxilverr.worldofstone.registry.WosFeatures;
import sxilverr.worldofstone.registry.WosItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ModInfo.MODID)
public class WorldofStone {

    public static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public WorldofStone(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        WosBlocks.init();
        WosItems.init();
        WosBlockEntities.init();
        WosCreativeTabs.init();
        WosFeatures.init();

        WosBlocks.BLOCKS.register(modEventBus);
        WosItems.ITEMS.register(modEventBus);
        WosBlockEntities.BES.register(modEventBus);
        WosCreativeTabs.TABS.register(modEventBus);
        WosFeatures.FEATURES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WosConfig.COMMON_SPEC);

        modEventBus.addListener(this::onCommonSetup);

        LOGGER.info("World of Stone loaded");
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("jeresources")) {
            event.enqueueWork(sxilverr.worldofstone.compat.jer.WorldOfStoneJERPlugin::register);
        }
    }
}
