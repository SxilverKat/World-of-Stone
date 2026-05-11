package sxilverr.worldofstone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class WosHoneySpeleothemBlock extends WosSpeleothemBlock {

    public WosHoneySpeleothemBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0F, 1.0F);
        if (!level.isClientSide) {
            level.broadcastEntityEvent(entity, (byte) 54);
        }
        if (entity.causeFallDamage(fallDistance, 0.2F, level.damageSources().fall())) {
            entity.playSound(this.soundType.getFallSound(), this.soundType.getVolume() * 0.5F, this.soundType.getPitch() * 0.75F);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully()) {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.4D, 1.0D, 0.4D));
        }
        super.stepOn(level, pos, state, entity);
    }
}
