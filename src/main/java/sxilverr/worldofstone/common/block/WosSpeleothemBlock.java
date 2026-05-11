package sxilverr.worldofstone.common.block;

import sxilverr.worldofstone.config.WosConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Collections;
import java.util.List;

public class WosSpeleothemBlock extends Block implements SimpleWaterloggedBlock, Fallable {

    public static final DirectionProperty TIP_DIRECTION = DirectionProperty.create("tip_direction", Direction.UP, Direction.DOWN);
    public static final EnumProperty<Size> SIZE = EnumProperty.create("size", Size.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;


    private static final VoxelShape SHAPE_SMALL = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape SHAPE_MEDIUM = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private static final VoxelShape SHAPE_LARGE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public WosSpeleothemBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(TIP_DIRECTION, Direction.UP)
                .setValue(SIZE, Size.SMALL)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP_DIRECTION, SIZE, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(SIZE)) {
            case SMALL -> SHAPE_SMALL;
            case MEDIUM -> SHAPE_MEDIUM;
            case LARGE -> SHAPE_LARGE;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return getShape(state, level, pos, ctx);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction face = ctx.getClickedFace();
        Direction tip = (face == Direction.DOWN) ? Direction.DOWN : Direction.UP;
        FluidState fluid = ctx.getLevel().getFluidState(ctx.getClickedPos());
        boolean waterlogged = WosConfig.speleothemsWaterloggable && fluid.is(FluidTags.WATER);
        return defaultBlockState()
                .setValue(TIP_DIRECTION, tip)
                .setValue(SIZE, computeSize(ctx.getLevel(), ctx.getClickedPos()))
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        FluidState neighborFluid = neighborState.getFluidState();
        if (!neighborFluid.isEmpty()) {
            if (neighborFluid.is(FluidTags.LAVA) && WosConfig.speleothemsBreakByLava) {
                return Blocks.AIR.defaultBlockState();
            }
            if (neighborFluid.is(FluidTags.WATER)
                    && !state.getValue(WATERLOGGED)
                    && !WosConfig.speleothemsWaterloggable
                    && WosConfig.speleothemsBreakByWater) {
                return Blocks.AIR.defaultBlockState();
            }
        }

        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (direction != Direction.UP && direction != Direction.DOWN) {
            return state;
        }
        if (!canSurvive(state, level, pos)) {
            if (WosConfig.speleothemsCanFall && state.getValue(TIP_DIRECTION) == Direction.DOWN) {
                level.scheduleTick(pos, this, 2);
                return state;
            }
            return Blocks.AIR.defaultBlockState();
        }
        return state.setValue(SIZE, computeSize(level, pos));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction tip = state.getValue(TIP_DIRECTION);
        BlockPos supportPos = (tip == Direction.DOWN) ? pos.above() : pos.below();
        return getStrength(level, supportPos) > 0;
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        if (fluid != Fluids.WATER) return false;
        if (state.getValue(WATERLOGGED)) return false;
        return WosConfig.speleothemsWaterloggable;
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        if (fluidState.getType() != Fluids.WATER) return false;
        if (state.getValue(WATERLOGGED)) return false;
        if (!WosConfig.speleothemsWaterloggable) return false;
        if (!level.isClientSide()) {
            level.setBlock(pos, state.setValue(WATERLOGGED, true), 3);
            level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
        }
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> drops = super.getDrops(state, params);
        if (drops.isEmpty() && WosConfig.speleothemsDropWithoutSilkTouch) {
            return Collections.singletonList(new ItemStack(this));
        }
        return drops;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (canSurvive(state, level, pos)) return;
        BlockPos.MutableBlockPos chainPos = pos.mutable();
        BlockState chainState = state;
        while (chainState.getBlock() instanceof WosSpeleothemBlock
                && chainState.getValue(TIP_DIRECTION) == Direction.DOWN) {
            FallingBlockEntity entity = FallingBlockEntity.fall(level, chainPos.immutable(), chainState);
            if (WosConfig.speleothemsDripstoneDamage) {
                entity.setHurtsEntities(2.0F, 40);
            }
            entity.disableDrop();
            chainPos.move(Direction.DOWN);
            chainState = level.getBlockState(chainPos);
        }
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (WosConfig.speleothemsDripstoneDamage
                && state.getValue(TIP_DIRECTION) == Direction.UP
                && state.getValue(SIZE) == Size.SMALL) {
            entity.causeFallDamage(fallDistance + 2.0F, 2.0F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState fallingState, BlockState landedState, FallingBlockEntity fallingBlock) {
        if (WosConfig.speleothemsDropOnFall) {
            Block.popResource(level, pos, new ItemStack(this));
        }
        level.destroyBlock(pos, false);
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity fallingBlock) {
        BlockState state = fallingBlock.getBlockState();
        level.levelEvent(2001, pos, Block.getId(state));
        if (WosConfig.speleothemsDropOnFall) {
            Block.popResource(level, pos, new ItemStack(this));
        }
    }

    private Size computeSize(LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        BlockState above = level.getBlockState(pos.above());
        int bearing = Math.max(getStrength(level, pos.below()), getStrength(level, pos.above()));
        Size size = Size.VALUES[Math.max(0, bearing - 1)];
        if (isSpeleothem(below) && isSpeleothem(above)) {
            size = Size.MEDIUM;
        }
        return size;
    }

    private static int getStrength(LevelReader level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof WosSpeleothemBlock) {
            return state.getValue(SIZE).strength;
        }
        if (state.isFaceSturdy(level, pos, Direction.UP) || state.isFaceSturdy(level, pos, Direction.DOWN)) {
            return 3;
        }
        return 0;
    }

    private static boolean isSpeleothem(BlockState state) {
        return state.getBlock() instanceof WosSpeleothemBlock;
    }

    public enum Size implements StringRepresentable {
        SMALL("small", 0),
        MEDIUM("medium", 1),
        LARGE("large", 2);

        public static final Size[] VALUES = values();

        private final String name;
        public final int strength;

        Size(String name, int strength) {
            this.name = name;
            this.strength = strength;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
