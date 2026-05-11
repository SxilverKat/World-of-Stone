package sxilverr.worldofstone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class WosMimicSpeleothemBlock extends WosSpeleothemBlock implements EntityBlock {

    public WosMimicSpeleothemBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WosMimicSpeleothemBlockEntity(pos, state);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
            BlockState source = mimic.getSourceState();
            return source.getBlock().getSoundType(source, level, pos, entity);
        }
        return super.getSoundType(state, level, pos, entity);
    }

    @Override
    public float getDestroyProgress(BlockState state, net.minecraft.world.entity.player.Player player, net.minecraft.world.level.BlockGetter level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
            return mimic.getSourceState().getDestroyProgress(player, level, pos);
        }
        return super.getDestroyProgress(state, player, level, pos);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.entity.player.Player player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
            return mimic.getSourceState().canHarvestBlock(level, pos, player);
        }
        if (!state.requiresCorrectToolForDrops()) return true;
        return player.getMainHandItem().isCorrectToolForDrops(state);
    }

    @Override
    public float getExplosionResistance(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.level.Explosion explosion) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
            return mimic.getSourceState().getExplosionResistance(level, pos, explosion);
        }
        return super.getExplosionResistance(state, level, pos, explosion);
    }

    @Override
    public int getLightEmission(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
            return mimic.getSourceState().getLightEmission(level, pos);
        }
        return super.getLightEmission(state, level, pos);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
            CompoundTag tag = stack.getTagElement("BlockEntityTag");
            if (tag != null && tag.contains("Source")) {
                ResourceLocation rl = ResourceLocation.tryParse(tag.getString("Source"));
                if (rl != null) {
                    Block source = ForgeRegistries.BLOCKS.getValue(rl);
                    if (source != null) {
                        mimic.setSource(source.defaultBlockState());
                    }
                }
            }
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, net.minecraft.world.phys.HitResult target, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.world.entity.player.Player player) {
        ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof WosMimicSpeleothemBlockEntity mimic) {
            ResourceLocation rl = ForgeRegistries.BLOCKS.getKey(mimic.getSourceState().getBlock());
            if (rl != null) {
                CompoundTag tag = new CompoundTag();
                tag.putString("Source", rl.toString());
                stack.addTagElement("BlockEntityTag", tag);
            }
        }
        return stack;
    }
}
