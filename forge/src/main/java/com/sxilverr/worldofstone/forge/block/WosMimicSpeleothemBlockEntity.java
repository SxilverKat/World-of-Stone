package com.sxilverr.worldofstone.forge.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import com.sxilverr.worldofstone.forge.registry.WosBlockEntities;

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
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Source")) {
            String id = tag.getString("Source");
            ResourceLocation rl = ResourceLocation.tryParse(id);
            if (rl != null) {
                Block block = ForgeRegistries.BLOCKS.getValue(rl);
                if (block != null && block != Blocks.AIR) {
                    sourceState = block.defaultBlockState();
                }
            }
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        ResourceLocation rl = ForgeRegistries.BLOCKS.getKey(sourceState.getBlock());
        if (rl != null) tag.putString("Source", rl.toString());
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
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
