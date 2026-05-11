package sxilverr.worldofstone.common;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.registry.WosItems;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModInfo.MODID)
public final class WosFuelHandler {

    private WosFuelHandler() {
    }

    @SubscribeEvent
    public static void onFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == WosItems.LIGNITE_COAL.get()) {
            event.setBurnTime(1600);
        }
    }
}
