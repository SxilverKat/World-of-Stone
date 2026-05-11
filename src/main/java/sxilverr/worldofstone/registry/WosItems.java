package sxilverr.worldofstone.registry;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.FossilVariant;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public final class WosItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.MODID);

    public static final Map<FossilVariant, RegistryObject<Item>> FOSSILS = new EnumMap<>(FossilVariant.class);

    public static final RegistryObject<Item> LIGNITE_COAL = ITEMS.register("lignite_coal",
            () -> new Item(new Item.Properties()));

    static {
        for (FossilVariant v : FossilVariant.VALUES) {
            FOSSILS.put(v, ITEMS.register(v.toString(),
                    () -> new Item(new Item.Properties())));
        }
    }

    public static void init() {
    }

    private WosItems() {
    }
}
