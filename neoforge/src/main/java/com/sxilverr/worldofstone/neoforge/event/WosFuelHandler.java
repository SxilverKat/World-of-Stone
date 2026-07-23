package com.sxilverr.worldofstone.neoforge.event;

import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.registry.WosItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

@EventBusSubscriber(modid = ModInfo.MODID)
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
