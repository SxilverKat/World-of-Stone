package com.sxilverr.worldofstone.mixin;

import com.sxilverr.worldofstone.common.block.WosInfestedBlock;
import com.sxilverr.worldofstone.config.WosConfig;
import com.sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InfestedBlock.class)
public abstract class InfestedBlockMixin {

    @Inject(method = "isCompatibleHostBlock", at = @At("HEAD"), cancellable = true)
    private static void wos$gateInfestedConversion(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (WosConfig.enableInfestedBlocks) return;
        Block host = state.getBlock();
        if (wos$isWosHost(host)) {
            cir.setReturnValue(false);
        }
    }

    private static boolean wos$isWosHost(Block host) {
        for (var ro : WosBlocks.IGNEOUS_STONE.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.IGNEOUS_COBBLE.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.IGNEOUS_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.IGNEOUS_MOSSY_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.IGNEOUS_CRACKED_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.IGNEOUS_CHISELED_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.METAMORPHIC_STONE.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.METAMORPHIC_COBBLE.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.METAMORPHIC_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.METAMORPHIC_MOSSY_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.METAMORPHIC_CRACKED_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.METAMORPHIC_CHISELED_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.SEDIMENTARY_STONE.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.SEDIMENTARY_COBBLE.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.SEDIMENTARY_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.SEDIMENTARY_MOSSY_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.SEDIMENTARY_CRACKED_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        for (var ro : WosBlocks.SEDIMENTARY_CHISELED_BRICK.values()) {
            if (ro.isPresent() && ro.get() == host) return true;
        }
        return host instanceof WosInfestedBlock;
    }
}
