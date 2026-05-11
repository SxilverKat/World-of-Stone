package sxilverr.worldofstone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class WosSlimeSpeleothemBlock extends WosSpeleothemBlock {

    public WosSlimeSpeleothemBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, state, pos, entity, fallDistance);
        } else {
            entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
        } else {
            Vec3 v = entity.getDeltaMovement();
            if (v.y < 0.0D) {
                double bounce = entity instanceof LivingEntity ? 1.0D : 0.8D;
                entity.setDeltaMovement(v.x, -v.y * bounce, v.z);
            }
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        double dy = Math.abs(entity.getDeltaMovement().y);
        if (dy < 0.1D && !entity.isSteppingCarefully()) {
            double damp = 0.4D + dy * 0.2D;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(damp, 1.0D, damp));
        }
        super.stepOn(level, pos, state, entity);
    }
}
