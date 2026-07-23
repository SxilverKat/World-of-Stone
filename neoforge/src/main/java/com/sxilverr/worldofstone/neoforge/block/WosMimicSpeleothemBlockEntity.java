package com.sxilverr.worldofstone.neoforge.block;

import com.sxilverr.worldofstone.neoforge.registry.WosBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.NotNull;

public class WosMimicSpeleothemBlockEntity extends BlockEntity {

    public static final ModelProperty<BlockState> SOURCE = new ModelProperty<>();

    private BlockState sourceState = Blocks.STONE.defaultBlockState();

    public WosMimicSpeleothemBlockEntity(BlockPos pos, BlockState state) {
        super(WosBlockEntities.MIMIC_SPELEOTHEM.get(), pos, state);
    }

    public BlockState getSourceState() {
        return sourceState;
    }

    public void setSource(BlockState state) {
        if (state == null || state.isAir()) return;
        if (this.sourceState == state) return;
        BlockState old = this.sourceState;
        this.sourceState = state;
        setChanged();
        requestModelDataUpdate();
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            if (state.getLightEmission() != old.getLightEmission()) {
                level.getLightEngine().checkBlock(getBlockPos());
            }
        }
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Source")) {
            ResourceLocation rl = ResourceLocation.tryParse(tag.getString("Source"));
            if (rl != null) {
                Block block = BuiltInRegistries.BLOCK.containsKey(rl) ? BuiltInRegistries.BLOCK.get(rl) : null;
                if (block != null && block != Blocks.AIR) {
                    sourceState = block.defaultBlockState();
                }
            }
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(sourceState.getBlock());
        if (rl != null) tag.putString("Source", rl.toString());
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull ModelData getModelData() {
        return ModelData.builder().with(SOURCE, sourceState).build();
    }
}
