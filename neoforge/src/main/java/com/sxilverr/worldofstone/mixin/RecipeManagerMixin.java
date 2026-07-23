package com.sxilverr.worldofstone.mixin;

import com.google.gson.JsonElement;
import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.config.WosConfig;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {

    @Shadow public abstract Collection<RecipeHolder<?>> getRecipes();

    @Shadow public abstract void replaceRecipes(Iterable<RecipeHolder<?>> recipes);

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("TAIL"))
    private void wos$filterRecipes(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        if (!wos$filteringActive()) {
            return;
        }
        List<RecipeHolder<?>> keep = new ArrayList<>();
        boolean changed = false;
        for (RecipeHolder<?> holder : getRecipes()) {
            if (wos$isRemoved(holder.value())) {
                changed = true;
            } else {
                keep.add(holder);
            }
        }
        if (changed) {
            replaceRecipes(keep);
        }
    }

    private static boolean wos$filteringActive() {
        return !WosConfig.stoneReplacementEnabled
                || !WosConfig.allowOvergrownStrata
                || !WosConfig.allowSnowedStrata
                || !WosConfig.allowOvergrownVanillaVariants
                || !WosConfig.allowSnowedVanillaVariants;
    }

    private static boolean wos$isRemoved(Recipe<?> recipe) {
        ItemStack result;
        try {
            result = recipe.getResultItem(RegistryAccess.EMPTY);
        } catch (Throwable t) {
            return false;
        }
        if (result == null || result.isEmpty()) {
            return false;
        }
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(result.getItem());
        if (key == null || !ModInfo.MODID.equals(key.getNamespace())) {
            return false;
        }
        String path = key.getPath();
        boolean strata = WosConfig.isStrataVariantPath(path);
        if (!WosConfig.stoneReplacementEnabled && strata) {
            return true;
        }
        if (path.startsWith("overgrown_")) {
            return strata ? !WosConfig.allowOvergrownStrata : !WosConfig.allowOvergrownVanillaVariants;
        }
        if (path.startsWith("snowed_")) {
            return strata ? !WosConfig.allowSnowedStrata : !WosConfig.allowSnowedVanillaVariants;
        }
        return false;
    }
}
