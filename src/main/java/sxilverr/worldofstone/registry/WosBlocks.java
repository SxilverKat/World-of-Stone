package sxilverr.worldofstone.registry;

import sxilverr.worldofstone.api.ModInfo;
import sxilverr.worldofstone.api.enums.DecorativeSpeleothemVariant;
import sxilverr.worldofstone.api.enums.IgneousVariant;
import sxilverr.worldofstone.api.enums.MetamorphicVariant;
import sxilverr.worldofstone.api.enums.OreVariant;
import sxilverr.worldofstone.api.enums.QuarkSpeleothemVariant;
import sxilverr.worldofstone.api.enums.SedimentaryVariant;
import sxilverr.worldofstone.api.enums.TerracottaSpeleothemVariant;
import sxilverr.worldofstone.api.enums.VanillaOreHost;
import sxilverr.worldofstone.api.enums.VanillaSpeleothemVariant;
import sxilverr.worldofstone.common.block.StrataStoneBlock;
import sxilverr.worldofstone.common.block.WosDevSpeleothemBlock;
import sxilverr.worldofstone.common.block.WosSpeleothemBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.fml.ModList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class WosBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModInfo.MODID);

    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_STONE = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_COBBLE = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_MOSSY_COBBLE = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_MOSSY_COBBLE_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_MOSSY_COBBLE_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<WallBlock>> IGNEOUS_MOSSY_COBBLE_WALL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_MOSSY_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_MOSSY_BRICK_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_MOSSY_BRICK_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<WallBlock>> IGNEOUS_MOSSY_BRICK_WALL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_COBBLE_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_COBBLE_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<WallBlock>> IGNEOUS_COBBLE_WALL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_BRICK_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_BRICK_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<WallBlock>> IGNEOUS_BRICK_WALL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SandBlock>> IGNEOUS_SAND = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_SANDSTONE = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_SANDSTONE_CHISELED = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_SANDSTONE_CUT = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_SANDSTONE_SMOOTH = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_SANDSTONE_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_SANDSTONE_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<WallBlock>> IGNEOUS_SANDSTONE_WALL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_SANDSTONE_SMOOTH_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_SANDSTONE_SMOOTH_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_SANDSTONE_CUT_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<GravelBlock>> IGNEOUS_GRAVEL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_CLAY = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<ButtonBlock>> IGNEOUS_BUTTON = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_SPELEOTHEM = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_SANDSTONE_SPELEOTHEM = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_CRACKED_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_CHISELED_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_INFESTED = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_INFESTED_COBBLE = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_INFESTED_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_INFESTED_MOSSY_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_INFESTED_CRACKED_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_INFESTED_CHISELED_BRICK = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_OVERGROWN = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_OVERGROWN_SNOWED = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_SMOOTH = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_SMOOTH_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_POLISHED = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_POLISHED_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_POLISHED_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<WallBlock>> IGNEOUS_POLISHED_WALL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_TILE = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<Block>> IGNEOUS_CRACKED_TILE = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<StairBlock>> IGNEOUS_TILE_STAIRS = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<SlabBlock>> IGNEOUS_TILE_SLAB = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<WallBlock>> IGNEOUS_TILE_WALL = new EnumMap<>(IgneousVariant.class);
    public static final Map<IgneousVariant, RegistryObject<RotatedPillarBlock>> IGNEOUS_PILLAR = new EnumMap<>(IgneousVariant.class);

    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_STONE = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_COBBLE = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_MOSSY_COBBLE = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_MOSSY_COBBLE_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_MOSSY_COBBLE_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<WallBlock>> METAMORPHIC_MOSSY_COBBLE_WALL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_MOSSY_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_MOSSY_BRICK_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_MOSSY_BRICK_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<WallBlock>> METAMORPHIC_MOSSY_BRICK_WALL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_COBBLE_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_COBBLE_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<WallBlock>> METAMORPHIC_COBBLE_WALL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_BRICK_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_BRICK_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<WallBlock>> METAMORPHIC_BRICK_WALL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SandBlock>> METAMORPHIC_SAND = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_SANDSTONE = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_SANDSTONE_CHISELED = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_SANDSTONE_CUT = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_SANDSTONE_SMOOTH = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_SANDSTONE_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_SANDSTONE_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<WallBlock>> METAMORPHIC_SANDSTONE_WALL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_SANDSTONE_SMOOTH_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_SANDSTONE_SMOOTH_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_SANDSTONE_CUT_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<GravelBlock>> METAMORPHIC_GRAVEL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_CLAY = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<ButtonBlock>> METAMORPHIC_BUTTON = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_SPELEOTHEM = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_SANDSTONE_SPELEOTHEM = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_CRACKED_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_CHISELED_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_INFESTED = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_INFESTED_COBBLE = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_INFESTED_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_INFESTED_MOSSY_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_INFESTED_CRACKED_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_INFESTED_CHISELED_BRICK = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_OVERGROWN = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_OVERGROWN_SNOWED = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_SMOOTH = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_SMOOTH_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_POLISHED = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_POLISHED_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_POLISHED_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<WallBlock>> METAMORPHIC_POLISHED_WALL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_TILE = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<Block>> METAMORPHIC_CRACKED_TILE = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<StairBlock>> METAMORPHIC_TILE_STAIRS = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<SlabBlock>> METAMORPHIC_TILE_SLAB = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<WallBlock>> METAMORPHIC_TILE_WALL = new EnumMap<>(MetamorphicVariant.class);
    public static final Map<MetamorphicVariant, RegistryObject<RotatedPillarBlock>> METAMORPHIC_PILLAR = new EnumMap<>(MetamorphicVariant.class);

    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_STONE = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_COBBLE = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_MOSSY_COBBLE = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_COBBLE_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_COBBLE_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<WallBlock>> SEDIMENTARY_COBBLE_WALL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_MOSSY_COBBLE_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_MOSSY_COBBLE_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<WallBlock>> SEDIMENTARY_MOSSY_COBBLE_WALL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_MOSSY_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_CRACKED_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_CHISELED_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_BRICK_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_BRICK_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<WallBlock>> SEDIMENTARY_BRICK_WALL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_MOSSY_BRICK_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_MOSSY_BRICK_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<WallBlock>> SEDIMENTARY_MOSSY_BRICK_WALL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_INFESTED = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_INFESTED_COBBLE = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_INFESTED_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_INFESTED_MOSSY_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_INFESTED_CRACKED_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_INFESTED_CHISELED_BRICK = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SandBlock>> SEDIMENTARY_SAND = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_SANDSTONE = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_SANDSTONE_CHISELED = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_SANDSTONE_CUT = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_SANDSTONE_SMOOTH = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_SANDSTONE_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_SANDSTONE_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<WallBlock>> SEDIMENTARY_SANDSTONE_WALL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_SANDSTONE_SMOOTH_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_SANDSTONE_SMOOTH_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_SANDSTONE_CUT_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<GravelBlock>> SEDIMENTARY_GRAVEL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_CLAY = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<ButtonBlock>> SEDIMENTARY_BUTTON = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_SPELEOTHEM = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_SANDSTONE_SPELEOTHEM = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_OVERGROWN = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_OVERGROWN_SNOWED = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_SMOOTH = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_SMOOTH_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_POLISHED = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_POLISHED_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_POLISHED_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<WallBlock>> SEDIMENTARY_POLISHED_WALL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_TILE = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<Block>> SEDIMENTARY_CRACKED_TILE = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<StairBlock>> SEDIMENTARY_TILE_STAIRS = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<SlabBlock>> SEDIMENTARY_TILE_SLAB = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<WallBlock>> SEDIMENTARY_TILE_WALL = new EnumMap<>(SedimentaryVariant.class);
    public static final Map<SedimentaryVariant, RegistryObject<RotatedPillarBlock>> SEDIMENTARY_PILLAR = new EnumMap<>(SedimentaryVariant.class);

    public static final Map<VanillaSpeleothemVariant, RegistryObject<Block>> VANILLA_SPELEOTHEM = new EnumMap<>(VanillaSpeleothemVariant.class);
    public static final Map<QuarkSpeleothemVariant, RegistryObject<Block>> QUARK_SPELEOTHEM = new EnumMap<>(QuarkSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant, RegistryObject<Block>> UNDERGARDEN_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.CreateSpeleothemVariant, RegistryObject<Block>> CREATE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant, RegistryObject<Block>> BETTEREND_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant, RegistryObject<Block>> GALOSPHERE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BwgSpeleothemVariant, RegistryObject<Block>> BWG_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant, RegistryObject<Block>> TWILIGHTFOREST_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AetherSpeleothemVariant, RegistryObject<Block>> AETHER_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant, RegistryObject<Block>> BLUE_SKIES_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant, RegistryObject<Block>> SPELUNKERY_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant, RegistryObject<Block>> ICEANDFIRE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant, RegistryObject<Block>> MYSTICALAGRICULTURE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant, RegistryObject<Block>> BIOMESOPLENTY_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant, RegistryObject<Block>> FORBIDDEN_ARCANUS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant, RegistryObject<Block>> ALEXSCAVES_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant, RegistryObject<Block>> ARS_NOUVEAU_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant, RegistryObject<Block>> CATACLYSM_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant, RegistryObject<Block>> TWIGS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant, RegistryObject<Block>> ARCHITECTS_PALETTE_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant, RegistryObject<Block>> OUTER_END_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant, RegistryObject<Block>> BOTANIA_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant, RegistryObject<Block>> AD_ASTRA_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant, RegistryObject<Block>> DEEP_AETHER_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant, RegistryObject<Block>> CAVERNS_AND_CHASMS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant, RegistryObject<Block>> ATMOSPHERIC_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant, RegistryObject<Block>> ENDERGETIC_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant, RegistryObject<Block>> WILDER_WILDS_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant, RegistryObject<Block>> REGIONS_UNEXPLORED_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant, RegistryObject<Block>> BORN_IN_CHAOS_V1_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant, RegistryObject<Block>> NATURALIST_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant, RegistryObject<Block>> YUNGSCAVEBIOMES_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant, RegistryObject<Block>> NATURES_SPIRIT_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant, RegistryObject<Block>> NETHEREXP_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant, RegistryObject<Block>> DEEPERDARKER_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant, RegistryObject<Block>> THE_DEEP_VOID_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.class);
    public static final Map<sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant, RegistryObject<Block>> DEFILED_LANDS_PREBORN_SPELEOTHEM = new EnumMap<>(sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.class);
    public static final Map<TerracottaSpeleothemVariant, RegistryObject<Block>> TERRACOTTA_SPELEOTHEM = new EnumMap<>(TerracottaSpeleothemVariant.class);
    public static final Map<DecorativeSpeleothemVariant, RegistryObject<Block>> DECORATIVE_SPELEOTHEM = new EnumMap<>(DecorativeSpeleothemVariant.class);

    public static final Map<String, RegistryObject<Block>> ORES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> VANILLA_HOST_OVERGROWN = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> VANILLA_HOST_SNOWED = new HashMap<>();

    public static final SoundType DEV_SPELEOTHEM_SOUNDS = new SoundType(
            1.0F, 1.0F,
            SoundEvents.CAT_DEATH,
            SoundEvents.CAT_AMBIENT,
            SoundEvents.CAT_STRAY_AMBIENT,
            SoundEvents.CAT_AMBIENT,
            SoundEvents.CAT_AMBIENT
    );

    public static final RegistryObject<Block> DEV_SPELEOTHEM = BLOCKS.register("dev_speleothem", () -> new WosDevSpeleothemBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .sound(DEV_SPELEOTHEM_SOUNDS)
                    .strength(50.0F, 1200.0F)
                    .noOcclusion()
                    .dynamicShape()
                    .requiresCorrectToolForDrops()
    ));
    public static final RegistryObject<Item> DEV_SPELEOTHEM_ITEM = WosItems.ITEMS.register("dev_speleothem",
            () -> new BlockItem(DEV_SPELEOTHEM.get(), new Item.Properties()));

    public static final RegistryObject<Block> MIMIC_SPELEOTHEM = BLOCKS.register("mimic_speleothem",
            () -> new sxilverr.worldofstone.common.block.WosMimicSpeleothemBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .sound(SoundType.STONE)
                            .strength(1.5F, 6.0F)
                            .noOcclusion()
                            .dynamicShape()
                            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> MIMIC_SPELEOTHEM_ITEM = WosItems.ITEMS.register("mimic_speleothem",
            () -> new BlockItem(MIMIC_SPELEOTHEM.get(), new Item.Properties()));

    private static final java.util.Set<DecorativeSpeleothemVariant> NO_TOOL_VARIANTS = java.util.EnumSet.of(
            DecorativeSpeleothemVariant.TNT,
            DecorativeSpeleothemVariant.GLASS,
            DecorativeSpeleothemVariant.TINTED_GLASS,
            DecorativeSpeleothemVariant.WHITE_STAINED_GLASS,
            DecorativeSpeleothemVariant.ORANGE_STAINED_GLASS,
            DecorativeSpeleothemVariant.MAGENTA_STAINED_GLASS,
            DecorativeSpeleothemVariant.LIGHT_BLUE_STAINED_GLASS,
            DecorativeSpeleothemVariant.YELLOW_STAINED_GLASS,
            DecorativeSpeleothemVariant.LIME_STAINED_GLASS,
            DecorativeSpeleothemVariant.PINK_STAINED_GLASS,
            DecorativeSpeleothemVariant.GRAY_STAINED_GLASS,
            DecorativeSpeleothemVariant.LIGHT_GRAY_STAINED_GLASS,
            DecorativeSpeleothemVariant.CYAN_STAINED_GLASS,
            DecorativeSpeleothemVariant.PURPLE_STAINED_GLASS,
            DecorativeSpeleothemVariant.BLUE_STAINED_GLASS,
            DecorativeSpeleothemVariant.BROWN_STAINED_GLASS,
            DecorativeSpeleothemVariant.GREEN_STAINED_GLASS,
            DecorativeSpeleothemVariant.RED_STAINED_GLASS,
            DecorativeSpeleothemVariant.BLACK_STAINED_GLASS,
            DecorativeSpeleothemVariant.SPONGE,
            DecorativeSpeleothemVariant.SLIME,
            DecorativeSpeleothemVariant.HONEY,
            DecorativeSpeleothemVariant.MOSS,
            DecorativeSpeleothemVariant.SEA_LANTERN,
            DecorativeSpeleothemVariant.WHITE_WOOL,
            DecorativeSpeleothemVariant.ORANGE_WOOL,
            DecorativeSpeleothemVariant.MAGENTA_WOOL,
            DecorativeSpeleothemVariant.LIGHT_BLUE_WOOL,
            DecorativeSpeleothemVariant.YELLOW_WOOL,
            DecorativeSpeleothemVariant.LIME_WOOL,
            DecorativeSpeleothemVariant.PINK_WOOL,
            DecorativeSpeleothemVariant.GRAY_WOOL,
            DecorativeSpeleothemVariant.LIGHT_GRAY_WOOL,
            DecorativeSpeleothemVariant.CYAN_WOOL,
            DecorativeSpeleothemVariant.PURPLE_WOOL,
            DecorativeSpeleothemVariant.BLUE_WOOL,
            DecorativeSpeleothemVariant.BROWN_WOOL,
            DecorativeSpeleothemVariant.GREEN_WOOL,
            DecorativeSpeleothemVariant.RED_WOOL,
            DecorativeSpeleothemVariant.BLACK_WOOL,
            DecorativeSpeleothemVariant.HONEYCOMB,
            DecorativeSpeleothemVariant.HAY_BALE,
            DecorativeSpeleothemVariant.SCULK,
            DecorativeSpeleothemVariant.SCULK_CATALYST,
            DecorativeSpeleothemVariant.CLAY,
            DecorativeSpeleothemVariant.PODZOL,
            DecorativeSpeleothemVariant.MYCELIUM,
            DecorativeSpeleothemVariant.COARSE_DIRT,
            DecorativeSpeleothemVariant.ROOTED_DIRT,
            DecorativeSpeleothemVariant.SOUL_SAND,
            DecorativeSpeleothemVariant.SOUL_SOIL,
            DecorativeSpeleothemVariant.GRASS,
            DecorativeSpeleothemVariant.BROWN_MUSHROOM,
            DecorativeSpeleothemVariant.RED_MUSHROOM,
            DecorativeSpeleothemVariant.NETHER_WART,
            DecorativeSpeleothemVariant.WARPED_WART,
            DecorativeSpeleothemVariant.SHROOMLIGHT,
            DecorativeSpeleothemVariant.OCHRE_FROGLIGHT,
            DecorativeSpeleothemVariant.VERDANT_FROGLIGHT,
            DecorativeSpeleothemVariant.PEARLESCENT_FROGLIGHT,
            DecorativeSpeleothemVariant.MELON,
            DecorativeSpeleothemVariant.PUMPKIN,
            DecorativeSpeleothemVariant.WARPED_NYLIUM,
            DecorativeSpeleothemVariant.CRIMSON_NYLIUM,
            DecorativeSpeleothemVariant.OAK_LOG,
            DecorativeSpeleothemVariant.STRIPPED_OAK_LOG,
            DecorativeSpeleothemVariant.OAK_PLANKS,
            DecorativeSpeleothemVariant.SPRUCE_LOG,
            DecorativeSpeleothemVariant.STRIPPED_SPRUCE_LOG,
            DecorativeSpeleothemVariant.SPRUCE_PLANKS,
            DecorativeSpeleothemVariant.BIRCH_LOG,
            DecorativeSpeleothemVariant.STRIPPED_BIRCH_LOG,
            DecorativeSpeleothemVariant.BIRCH_PLANKS,
            DecorativeSpeleothemVariant.JUNGLE_LOG,
            DecorativeSpeleothemVariant.STRIPPED_JUNGLE_LOG,
            DecorativeSpeleothemVariant.JUNGLE_PLANKS,
            DecorativeSpeleothemVariant.ACACIA_LOG,
            DecorativeSpeleothemVariant.STRIPPED_ACACIA_LOG,
            DecorativeSpeleothemVariant.ACACIA_PLANKS,
            DecorativeSpeleothemVariant.DARK_OAK_LOG,
            DecorativeSpeleothemVariant.STRIPPED_DARK_OAK_LOG,
            DecorativeSpeleothemVariant.DARK_OAK_PLANKS,
            DecorativeSpeleothemVariant.MANGROVE_LOG,
            DecorativeSpeleothemVariant.STRIPPED_MANGROVE_LOG,
            DecorativeSpeleothemVariant.MANGROVE_PLANKS,
            DecorativeSpeleothemVariant.CHERRY_LOG,
            DecorativeSpeleothemVariant.STRIPPED_CHERRY_LOG,
            DecorativeSpeleothemVariant.CHERRY_PLANKS,
            DecorativeSpeleothemVariant.CRIMSON_STEM,
            DecorativeSpeleothemVariant.STRIPPED_CRIMSON_STEM,
            DecorativeSpeleothemVariant.CRIMSON_PLANKS,
            DecorativeSpeleothemVariant.WARPED_STEM,
            DecorativeSpeleothemVariant.STRIPPED_WARPED_STEM,
            DecorativeSpeleothemVariant.WARPED_PLANKS,
            DecorativeSpeleothemVariant.BAMBOO_BLOCK,
            DecorativeSpeleothemVariant.STRIPPED_BAMBOO_BLOCK,
            DecorativeSpeleothemVariant.BAMBOO_PLANKS,
            DecorativeSpeleothemVariant.BAMBOO_MOSAIC
    );

    static {
        for (IgneousVariant v : IgneousVariant.VALUES) {
            float h = v.getHardness();
            float r = v.getResistance();
            String n = v.toString();
            final IgneousVariant fv = v;
            IGNEOUS_STONE.put(v, registerStrataStone(n, 1.5F * h, 6.0F * r, () -> IGNEOUS_OVERGROWN_SNOWED.get(fv).get(), () -> IGNEOUS_COBBLE.get(fv).get(), false));
            IGNEOUS_COBBLE.put(v, registerBlock(n + "_cobblestone", 2.0F * h, 6.0F * r));
            IGNEOUS_MOSSY_COBBLE.put(v, registerBlock("mossy_" + n + "_cobblestone", 2.0F * h, 6.0F * r));
            IGNEOUS_MOSSY_COBBLE_STAIRS.put(v, registerStairs("mossy_" + n + "_cobblestone_stairs", IGNEOUS_MOSSY_COBBLE.get(v), 2.0F * h, 6.0F * r));
            IGNEOUS_MOSSY_COBBLE_SLAB.put(v, registerSlab("mossy_" + n + "_cobblestone_slab", 2.0F * h, 6.0F * r));
            IGNEOUS_MOSSY_COBBLE_WALL.put(v, registerWall("mossy_" + n + "_cobblestone_wall", 2.0F * h, 6.0F * r));
            IGNEOUS_BRICK.put(v, registerBlock(n + "_bricks", 1.5F * h, 6.0F * r));
            IGNEOUS_MOSSY_BRICK.put(v, registerBlock("mossy_" + n + "_bricks", 1.5F * h, 6.0F * r));
            IGNEOUS_CRACKED_BRICK.put(v, registerBlock("cracked_" + n + "_bricks", 1.5F * h, 6.0F * r));
            IGNEOUS_CHISELED_BRICK.put(v, registerBlock("chiseled_" + n + "_bricks", 1.5F * h, 6.0F * r));
            IGNEOUS_MOSSY_BRICK_STAIRS.put(v, registerStairs("mossy_" + n + "_brick_stairs", IGNEOUS_MOSSY_BRICK.get(v), 1.5F * h, 6.0F * r));
            IGNEOUS_MOSSY_BRICK_SLAB.put(v, registerSlab("mossy_" + n + "_brick_slab", 1.5F * h, 6.0F * r));
            IGNEOUS_MOSSY_BRICK_WALL.put(v, registerWall("mossy_" + n + "_brick_wall", 1.5F * h, 6.0F * r));
            IGNEOUS_COBBLE_STAIRS.put(v, registerStairs(n + "_cobblestone_stairs", IGNEOUS_COBBLE.get(v), 2.0F * h, 6.0F * r));
            IGNEOUS_COBBLE_SLAB.put(v, registerSlab(n + "_cobblestone_slab", 2.0F * h, 6.0F * r));
            IGNEOUS_COBBLE_WALL.put(v, registerWall(n + "_cobblestone_wall", 2.0F * h, 6.0F * r));
            IGNEOUS_BRICK_STAIRS.put(v, registerStairs(n + "_brick_stairs", IGNEOUS_BRICK.get(v), 1.5F * h, 6.0F * r));
            IGNEOUS_BRICK_SLAB.put(v, registerSlab(n + "_brick_slab", 1.5F * h, 6.0F * r));
            IGNEOUS_BRICK_WALL.put(v, registerWall(n + "_brick_wall", 1.5F * h, 6.0F * r));
            IGNEOUS_SAND.put(v, registerSand(n + "_sand"));
            IGNEOUS_SANDSTONE.put(v, registerBlock(n + "_sandstone", 0.8F, 0.8F));
            IGNEOUS_SANDSTONE_CHISELED.put(v, registerBlock("chiseled_" + n + "_sandstone", 0.8F, 0.8F));
            IGNEOUS_SANDSTONE_CUT.put(v, registerBlock("cut_" + n + "_sandstone", 0.8F, 0.8F));
            IGNEOUS_SANDSTONE_SMOOTH.put(v, registerBlock("smooth_" + n + "_sandstone", 2.0F, 6.0F));
            IGNEOUS_SANDSTONE_STAIRS.put(v, registerStairs(n + "_sandstone_stairs", IGNEOUS_SANDSTONE.get(v), 0.8F, 0.8F));
            IGNEOUS_SANDSTONE_SLAB.put(v, registerSlab(n + "_sandstone_slab", 0.8F, 0.8F));
            IGNEOUS_SANDSTONE_WALL.put(v, registerWall(n + "_sandstone_wall", 0.8F, 0.8F));
            IGNEOUS_SANDSTONE_SMOOTH_STAIRS.put(v, registerStairs("smooth_" + n + "_sandstone_stairs", IGNEOUS_SANDSTONE_SMOOTH.get(v), 2.0F, 6.0F));
            IGNEOUS_SANDSTONE_SMOOTH_SLAB.put(v, registerSlab("smooth_" + n + "_sandstone_slab", 2.0F, 6.0F));
            IGNEOUS_SANDSTONE_CUT_SLAB.put(v, registerSlab("cut_" + n + "_sandstone_slab", 0.8F, 0.8F));
            IGNEOUS_GRAVEL.put(v, registerGravel(n + "_gravel"));
            IGNEOUS_CLAY.put(v, registerClay(n + "_clay"));
            IGNEOUS_BUTTON.put(v, registerButton(n + "_button"));
            IGNEOUS_SPELEOTHEM.put(v, registerSpeleothemFromHost(n + "_speleothem", "worldofstone:" + n, 1.5F * h, 6.0F * r));
            IGNEOUS_SANDSTONE_SPELEOTHEM.put(v, registerSpeleothemFromHost(n + "_sandstone_speleothem", "worldofstone:" + n + "_sandstone", 0.8F, 0.8F));
            IGNEOUS_OVERGROWN.put(v, registerOvergrown("overgrown_" + n, 1.5F * h, 6.0F * r));
            IGNEOUS_OVERGROWN_SNOWED.put(v, registerOvergrown("snowed_" + n, 1.5F * h, 6.0F * r));
            IGNEOUS_SMOOTH.put(v, registerBlock("smooth_" + n, 2.0F, 6.0F));
            IGNEOUS_SMOOTH_SLAB.put(v, registerSlab("smooth_" + n + "_slab", 2.0F, 6.0F));
            IGNEOUS_POLISHED.put(v, registerBlock("polished_" + n, 1.5F * h, 6.0F * r));
            IGNEOUS_POLISHED_STAIRS.put(v, registerStairs("polished_" + n + "_stairs", IGNEOUS_POLISHED.get(v), 1.5F * h, 6.0F * r));
            IGNEOUS_POLISHED_SLAB.put(v, registerSlab("polished_" + n + "_slab", 1.5F * h, 6.0F * r));
            IGNEOUS_POLISHED_WALL.put(v, registerWall("polished_" + n + "_wall", 1.5F * h, 6.0F * r));
            IGNEOUS_TILE.put(v, registerBlock(n + "_tiles", 1.5F * h, 6.0F * r));
            IGNEOUS_CRACKED_TILE.put(v, registerBlock("cracked_" + n + "_tiles", 1.5F * h, 6.0F * r));
            IGNEOUS_TILE_STAIRS.put(v, registerStairs(n + "_tile_stairs", IGNEOUS_TILE.get(v), 1.5F * h, 6.0F * r));
            IGNEOUS_TILE_SLAB.put(v, registerSlab(n + "_tile_slab", 1.5F * h, 6.0F * r));
            IGNEOUS_TILE_WALL.put(v, registerWall(n + "_tile_wall", 1.5F * h, 6.0F * r));
            IGNEOUS_PILLAR.put(v, registerPillar(n + "_pillar", 1.5F * h, 6.0F * r));
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            float h = v.getHardness();
            float r = v.getResistance();
            String n = v.toString();
            final MetamorphicVariant fv = v;
            METAMORPHIC_STONE.put(v, registerStrataStone(n, 1.5F * h, 6.0F * r, () -> METAMORPHIC_OVERGROWN_SNOWED.get(fv).get(), () -> METAMORPHIC_COBBLE.get(fv).get(), false));
            METAMORPHIC_COBBLE.put(v, registerBlock(n + "_cobblestone", 2.0F * h, 6.0F * r));
            METAMORPHIC_MOSSY_COBBLE.put(v, registerBlock("mossy_" + n + "_cobblestone", 2.0F * h, 6.0F * r));
            METAMORPHIC_MOSSY_COBBLE_STAIRS.put(v, registerStairs("mossy_" + n + "_cobblestone_stairs", METAMORPHIC_MOSSY_COBBLE.get(v), 2.0F * h, 6.0F * r));
            METAMORPHIC_MOSSY_COBBLE_SLAB.put(v, registerSlab("mossy_" + n + "_cobblestone_slab", 2.0F * h, 6.0F * r));
            METAMORPHIC_MOSSY_COBBLE_WALL.put(v, registerWall("mossy_" + n + "_cobblestone_wall", 2.0F * h, 6.0F * r));
            METAMORPHIC_BRICK.put(v, registerBlock(n + "_bricks", 1.5F * h, 6.0F * r));
            METAMORPHIC_MOSSY_BRICK.put(v, registerBlock("mossy_" + n + "_bricks", 1.5F * h, 6.0F * r));
            METAMORPHIC_CRACKED_BRICK.put(v, registerBlock("cracked_" + n + "_bricks", 1.5F * h, 6.0F * r));
            METAMORPHIC_CHISELED_BRICK.put(v, registerBlock("chiseled_" + n + "_bricks", 1.5F * h, 6.0F * r));
            METAMORPHIC_MOSSY_BRICK_STAIRS.put(v, registerStairs("mossy_" + n + "_brick_stairs", METAMORPHIC_MOSSY_BRICK.get(v), 1.5F * h, 6.0F * r));
            METAMORPHIC_MOSSY_BRICK_SLAB.put(v, registerSlab("mossy_" + n + "_brick_slab", 1.5F * h, 6.0F * r));
            METAMORPHIC_MOSSY_BRICK_WALL.put(v, registerWall("mossy_" + n + "_brick_wall", 1.5F * h, 6.0F * r));
            METAMORPHIC_COBBLE_STAIRS.put(v, registerStairs(n + "_cobblestone_stairs", METAMORPHIC_COBBLE.get(v), 2.0F * h, 6.0F * r));
            METAMORPHIC_COBBLE_SLAB.put(v, registerSlab(n + "_cobblestone_slab", 2.0F * h, 6.0F * r));
            METAMORPHIC_COBBLE_WALL.put(v, registerWall(n + "_cobblestone_wall", 2.0F * h, 6.0F * r));
            METAMORPHIC_BRICK_STAIRS.put(v, registerStairs(n + "_brick_stairs", METAMORPHIC_BRICK.get(v), 1.5F * h, 6.0F * r));
            METAMORPHIC_BRICK_SLAB.put(v, registerSlab(n + "_brick_slab", 1.5F * h, 6.0F * r));
            METAMORPHIC_BRICK_WALL.put(v, registerWall(n + "_brick_wall", 1.5F * h, 6.0F * r));
            METAMORPHIC_SAND.put(v, registerSand(n + "_sand"));
            METAMORPHIC_SANDSTONE.put(v, registerBlock(n + "_sandstone", 0.8F, 0.8F));
            METAMORPHIC_SANDSTONE_CHISELED.put(v, registerBlock("chiseled_" + n + "_sandstone", 0.8F, 0.8F));
            METAMORPHIC_SANDSTONE_CUT.put(v, registerBlock("cut_" + n + "_sandstone", 0.8F, 0.8F));
            METAMORPHIC_SANDSTONE_SMOOTH.put(v, registerBlock("smooth_" + n + "_sandstone", 2.0F, 6.0F));
            METAMORPHIC_SANDSTONE_STAIRS.put(v, registerStairs(n + "_sandstone_stairs", METAMORPHIC_SANDSTONE.get(v), 0.8F, 0.8F));
            METAMORPHIC_SANDSTONE_SLAB.put(v, registerSlab(n + "_sandstone_slab", 0.8F, 0.8F));
            METAMORPHIC_SANDSTONE_WALL.put(v, registerWall(n + "_sandstone_wall", 0.8F, 0.8F));
            METAMORPHIC_SANDSTONE_SMOOTH_STAIRS.put(v, registerStairs("smooth_" + n + "_sandstone_stairs", METAMORPHIC_SANDSTONE_SMOOTH.get(v), 2.0F, 6.0F));
            METAMORPHIC_SANDSTONE_SMOOTH_SLAB.put(v, registerSlab("smooth_" + n + "_sandstone_slab", 2.0F, 6.0F));
            METAMORPHIC_SANDSTONE_CUT_SLAB.put(v, registerSlab("cut_" + n + "_sandstone_slab", 0.8F, 0.8F));
            METAMORPHIC_GRAVEL.put(v, registerGravel(n + "_gravel"));
            METAMORPHIC_CLAY.put(v, registerClay(n + "_clay"));
            METAMORPHIC_BUTTON.put(v, registerButton(n + "_button"));
            METAMORPHIC_SPELEOTHEM.put(v, registerSpeleothemFromHost(n + "_speleothem", "worldofstone:" + n, 1.5F * h, 6.0F * r));
            METAMORPHIC_SANDSTONE_SPELEOTHEM.put(v, registerSpeleothemFromHost(n + "_sandstone_speleothem", "worldofstone:" + n + "_sandstone", 0.8F, 0.8F));
            METAMORPHIC_OVERGROWN.put(v, registerOvergrown("overgrown_" + n, 1.5F * h, 6.0F * r));
            METAMORPHIC_OVERGROWN_SNOWED.put(v, registerOvergrown("snowed_" + n, 1.5F * h, 6.0F * r));
            METAMORPHIC_SMOOTH.put(v, registerBlock("smooth_" + n, 2.0F, 6.0F));
            METAMORPHIC_SMOOTH_SLAB.put(v, registerSlab("smooth_" + n + "_slab", 2.0F, 6.0F));
            METAMORPHIC_POLISHED.put(v, registerBlock("polished_" + n, 1.5F * h, 6.0F * r));
            METAMORPHIC_POLISHED_STAIRS.put(v, registerStairs("polished_" + n + "_stairs", METAMORPHIC_POLISHED.get(v), 1.5F * h, 6.0F * r));
            METAMORPHIC_POLISHED_SLAB.put(v, registerSlab("polished_" + n + "_slab", 1.5F * h, 6.0F * r));
            METAMORPHIC_POLISHED_WALL.put(v, registerWall("polished_" + n + "_wall", 1.5F * h, 6.0F * r));
            METAMORPHIC_TILE.put(v, registerBlock(n + "_tiles", 1.5F * h, 6.0F * r));
            METAMORPHIC_CRACKED_TILE.put(v, registerBlock("cracked_" + n + "_tiles", 1.5F * h, 6.0F * r));
            METAMORPHIC_TILE_STAIRS.put(v, registerStairs(n + "_tile_stairs", METAMORPHIC_TILE.get(v), 1.5F * h, 6.0F * r));
            METAMORPHIC_TILE_SLAB.put(v, registerSlab(n + "_tile_slab", 1.5F * h, 6.0F * r));
            METAMORPHIC_TILE_WALL.put(v, registerWall(n + "_tile_wall", 1.5F * h, 6.0F * r));
            METAMORPHIC_PILLAR.put(v, registerPillar(n + "_pillar", 1.5F * h, 6.0F * r));
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            float h = v.getHardness();
            float r = v.getResistance();
            String n = v.toString();
            final SedimentaryVariant fv = v;
            SEDIMENTARY_STONE.put(v, registerStrataStone(n, 1.5F * h, 6.0F * r, () -> SEDIMENTARY_OVERGROWN_SNOWED.get(fv).get(), () -> SEDIMENTARY_COBBLE.get(fv).get(), v == SedimentaryVariant.LIGNITE));
            SEDIMENTARY_COBBLE.put(v, registerBlock(n + "_cobblestone", 2.0F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_COBBLE.put(v, registerBlock("mossy_" + n + "_cobblestone", 2.0F * h, 6.0F * r));
            SEDIMENTARY_COBBLE_STAIRS.put(v, registerStairs(n + "_cobblestone_stairs", SEDIMENTARY_COBBLE.get(v), 2.0F * h, 6.0F * r));
            SEDIMENTARY_COBBLE_SLAB.put(v, registerSlab(n + "_cobblestone_slab", 2.0F * h, 6.0F * r));
            SEDIMENTARY_COBBLE_WALL.put(v, registerWall(n + "_cobblestone_wall", 2.0F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_COBBLE_STAIRS.put(v, registerStairs("mossy_" + n + "_cobblestone_stairs", SEDIMENTARY_MOSSY_COBBLE.get(v), 2.0F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_COBBLE_SLAB.put(v, registerSlab("mossy_" + n + "_cobblestone_slab", 2.0F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_COBBLE_WALL.put(v, registerWall("mossy_" + n + "_cobblestone_wall", 2.0F * h, 6.0F * r));
            SEDIMENTARY_BRICK.put(v, registerBlock(n + "_bricks", 1.5F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_BRICK.put(v, registerBlock("mossy_" + n + "_bricks", 1.5F * h, 6.0F * r));
            SEDIMENTARY_CRACKED_BRICK.put(v, registerBlock("cracked_" + n + "_bricks", 1.5F * h, 6.0F * r));
            SEDIMENTARY_CHISELED_BRICK.put(v, registerBlock("chiseled_" + n + "_bricks", 1.5F * h, 6.0F * r));
            SEDIMENTARY_BRICK_STAIRS.put(v, registerStairs(n + "_brick_stairs", SEDIMENTARY_BRICK.get(v), 1.5F * h, 6.0F * r));
            SEDIMENTARY_BRICK_SLAB.put(v, registerSlab(n + "_brick_slab", 1.5F * h, 6.0F * r));
            SEDIMENTARY_BRICK_WALL.put(v, registerWall(n + "_brick_wall", 1.5F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_BRICK_STAIRS.put(v, registerStairs("mossy_" + n + "_brick_stairs", SEDIMENTARY_MOSSY_BRICK.get(v), 1.5F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_BRICK_SLAB.put(v, registerSlab("mossy_" + n + "_brick_slab", 1.5F * h, 6.0F * r));
            SEDIMENTARY_MOSSY_BRICK_WALL.put(v, registerWall("mossy_" + n + "_brick_wall", 1.5F * h, 6.0F * r));
            SEDIMENTARY_SAND.put(v, registerSand(n + "_sand"));
            SEDIMENTARY_SANDSTONE.put(v, registerBlock(n + "_sandstone", 0.8F, 0.8F));
            SEDIMENTARY_SANDSTONE_CHISELED.put(v, registerBlock("chiseled_" + n + "_sandstone", 0.8F, 0.8F));
            SEDIMENTARY_SANDSTONE_CUT.put(v, registerBlock("cut_" + n + "_sandstone", 0.8F, 0.8F));
            SEDIMENTARY_SANDSTONE_SMOOTH.put(v, registerBlock("smooth_" + n + "_sandstone", 2.0F, 6.0F));
            SEDIMENTARY_SANDSTONE_STAIRS.put(v, registerStairs(n + "_sandstone_stairs", SEDIMENTARY_SANDSTONE.get(v), 0.8F, 0.8F));
            SEDIMENTARY_SANDSTONE_SLAB.put(v, registerSlab(n + "_sandstone_slab", 0.8F, 0.8F));
            SEDIMENTARY_SANDSTONE_WALL.put(v, registerWall(n + "_sandstone_wall", 0.8F, 0.8F));
            SEDIMENTARY_SANDSTONE_SMOOTH_STAIRS.put(v, registerStairs("smooth_" + n + "_sandstone_stairs", SEDIMENTARY_SANDSTONE_SMOOTH.get(v), 2.0F, 6.0F));
            SEDIMENTARY_SANDSTONE_SMOOTH_SLAB.put(v, registerSlab("smooth_" + n + "_sandstone_slab", 2.0F, 6.0F));
            SEDIMENTARY_SANDSTONE_CUT_SLAB.put(v, registerSlab("cut_" + n + "_sandstone_slab", 0.8F, 0.8F));
            SEDIMENTARY_GRAVEL.put(v, registerGravel(n + "_gravel"));
            SEDIMENTARY_CLAY.put(v, registerClay(n + "_clay"));
            SEDIMENTARY_BUTTON.put(v, registerButton(n + "_button"));
            SEDIMENTARY_SPELEOTHEM.put(v, registerSpeleothemFromHost(n + "_speleothem", "worldofstone:" + n, 1.5F * h, 6.0F * r));
            SEDIMENTARY_SANDSTONE_SPELEOTHEM.put(v, registerSpeleothemFromHost(n + "_sandstone_speleothem", "worldofstone:" + n + "_sandstone", 0.8F, 0.8F));
            SEDIMENTARY_OVERGROWN.put(v, registerOvergrown("overgrown_" + n, 1.5F * h, 6.0F * r));
            SEDIMENTARY_OVERGROWN_SNOWED.put(v, registerOvergrown("snowed_" + n, 1.5F * h, 6.0F * r));
            SEDIMENTARY_SMOOTH.put(v, registerBlock("smooth_" + n, 2.0F, 6.0F));
            SEDIMENTARY_SMOOTH_SLAB.put(v, registerSlab("smooth_" + n + "_slab", 2.0F, 6.0F));
            SEDIMENTARY_POLISHED.put(v, registerBlock("polished_" + n, 1.5F * h, 6.0F * r));
            SEDIMENTARY_POLISHED_STAIRS.put(v, registerStairs("polished_" + n + "_stairs", SEDIMENTARY_POLISHED.get(v), 1.5F * h, 6.0F * r));
            SEDIMENTARY_POLISHED_SLAB.put(v, registerSlab("polished_" + n + "_slab", 1.5F * h, 6.0F * r));
            SEDIMENTARY_POLISHED_WALL.put(v, registerWall("polished_" + n + "_wall", 1.5F * h, 6.0F * r));
            SEDIMENTARY_TILE.put(v, registerBlock(n + "_tiles", 1.5F * h, 6.0F * r));
            SEDIMENTARY_CRACKED_TILE.put(v, registerBlock("cracked_" + n + "_tiles", 1.5F * h, 6.0F * r));
            SEDIMENTARY_TILE_STAIRS.put(v, registerStairs(n + "_tile_stairs", SEDIMENTARY_TILE.get(v), 1.5F * h, 6.0F * r));
            SEDIMENTARY_TILE_SLAB.put(v, registerSlab(n + "_tile_slab", 1.5F * h, 6.0F * r));
            SEDIMENTARY_TILE_WALL.put(v, registerWall(n + "_tile_wall", 1.5F * h, 6.0F * r));
            SEDIMENTARY_PILLAR.put(v, registerPillar(n + "_pillar", 1.5F * h, 6.0F * r));
        }
        for (VanillaSpeleothemVariant v : VanillaSpeleothemVariant.VALUES) {
            VANILLA_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), "minecraft:" + v.toString(), 1.5F, 6.0F));
        }
        if (ModList.get().isLoaded("quark")) {
            for (QuarkSpeleothemVariant v : QuarkSpeleothemVariant.VALUES) {
                QUARK_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("undergarden")) {
            for (sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant v : sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.VALUES) {
                UNDERGARDEN_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("create")) {
            for (sxilverr.worldofstone.api.enums.CreateSpeleothemVariant v : sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.VALUES) {
                CREATE_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("betterend")) {
            for (sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant v : sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.VALUES) {
                BETTEREND_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("galosphere")) {
            for (sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant v : sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.VALUES) {
                GALOSPHERE_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("biomeswevegone")) {
            for (sxilverr.worldofstone.api.enums.BwgSpeleothemVariant v : sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.VALUES) {
                BWG_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("twilightforest")) {
            for (sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant v : sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.VALUES) {
                TWILIGHTFOREST_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("aether")) {
            for (sxilverr.worldofstone.api.enums.AetherSpeleothemVariant v : sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.VALUES) {
                AETHER_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), aetherHostBlockId(v), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("blue_skies")) {
            for (sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant v : sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.VALUES) {
                BLUE_SKIES_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), blueSkiesHostBlockId(v), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("spelunkery")) {
            for (sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant v : sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.VALUES) {
                SPELUNKERY_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("iceandfire")) {
            for (sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v : sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.VALUES) {
                ICEANDFIRE_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("mysticalagriculture")) {
            for (sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v : sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.VALUES) {
                MYSTICALAGRICULTURE_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("biomesoplenty")) {
            for (sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant v : sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.VALUES) {
                BIOMESOPLENTY_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("forbidden_arcanus")) {
            for (sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant v : sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.VALUES) {
                FORBIDDEN_ARCANUS_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("alexscaves")) {
            for (sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant v : sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.VALUES) {
                ALEXSCAVES_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("ars_nouveau")) {
            for (sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v : sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.VALUES) {
                ARS_NOUVEAU_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("cataclysm")) {
            for (sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v : sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.VALUES) {
                CATACLYSM_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("twigs")) {
            for (sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant v : sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.VALUES) {
                TWIGS_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("architects_palette")) {
            for (sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant v : sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.VALUES) {
                ARCHITECTS_PALETTE_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("outer_end")) {
            for (sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant v : sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.VALUES) {
                OUTER_END_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("botania")) {
            for (sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant v : sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.VALUES) {
                BOTANIA_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("ad_astra")) {
            for (sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant v : sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.VALUES) {
                AD_ASTRA_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("deep_aether")) {
            for (sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant v : sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.VALUES) {
                DEEP_AETHER_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("caverns_and_chasms")) {
            for (sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant v : sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.VALUES) {
                CAVERNS_AND_CHASMS_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("atmospheric")) {
            for (sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant v : sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.VALUES) {
                ATMOSPHERIC_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("endergetic")) {
            for (sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v : sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.VALUES) {
                ENDERGETIC_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("wilder_wilds")) {
            for (sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant v : sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.VALUES) {
                WILDER_WILDS_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("regions_unexplored")) {
            for (sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant v : sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.VALUES) {
                REGIONS_UNEXPLORED_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("born_in_chaos_v1")) {
            for (sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v : sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.VALUES) {
                BORN_IN_CHAOS_V1_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("naturalist")) {
            for (sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v : sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.VALUES) {
                NATURALIST_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("yungscavebiomes")) {
            for (sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v : sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.VALUES) {
                YUNGSCAVEBIOMES_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("natures_spirit")) {
            for (sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant v : sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.VALUES) {
                NATURES_SPIRIT_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("netherexp")) {
            for (sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant v : sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.VALUES) {
                NETHEREXP_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("deeperdarker")) {
            for (sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant v : sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.VALUES) {
                DEEPERDARKER_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("the_deep_void")) {
            for (sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant v : sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.VALUES) {
                THE_DEEP_VOID_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        if (ModList.get().isLoaded("defiled_lands_preborn")) {
            for (sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v : sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.VALUES) {
                DEFILED_LANDS_PREBORN_SPELEOTHEM.put(v, registerSpeleothemFromHost(v.getRegistryName(), v.getTextureRef().replace("block/", ""), 1.5F, 6.0F));
            }
        }
        for (TerracottaSpeleothemVariant v : TerracottaSpeleothemVariant.VALUES) {
            TERRACOTTA_SPELEOTHEM.put(v, registerSpeleothem(v.getRegistryName(), 1.25F, 4.2F));
        }
        for (DecorativeSpeleothemVariant v : DecorativeSpeleothemVariant.VALUES) {
            DECORATIVE_SPELEOTHEM.put(v, registerDecorativeSpeleothem(v));
        }

        for (IgneousVariant v : IgneousVariant.VALUES) {
            String n = v.toString();
            IGNEOUS_INFESTED.put(v, registerInfestedBlock("infested_" + n, IGNEOUS_STONE.get(v)));
            IGNEOUS_INFESTED_COBBLE.put(v, registerInfestedBlock("infested_" + n + "_cobblestone", IGNEOUS_COBBLE.get(v)));
            IGNEOUS_INFESTED_BRICK.put(v, registerInfestedBlock("infested_" + n + "_bricks", IGNEOUS_BRICK.get(v)));
            IGNEOUS_INFESTED_MOSSY_BRICK.put(v, registerInfestedBlock("infested_mossy_" + n + "_bricks", IGNEOUS_MOSSY_BRICK.get(v)));
            IGNEOUS_INFESTED_CRACKED_BRICK.put(v, registerInfestedBlock("infested_cracked_" + n + "_bricks", IGNEOUS_CRACKED_BRICK.get(v)));
            IGNEOUS_INFESTED_CHISELED_BRICK.put(v, registerInfestedBlock("infested_chiseled_" + n + "_bricks", IGNEOUS_CHISELED_BRICK.get(v)));
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            String n = v.toString();
            METAMORPHIC_INFESTED.put(v, registerInfestedBlock("infested_" + n, METAMORPHIC_STONE.get(v)));
            METAMORPHIC_INFESTED_COBBLE.put(v, registerInfestedBlock("infested_" + n + "_cobblestone", METAMORPHIC_COBBLE.get(v)));
            METAMORPHIC_INFESTED_BRICK.put(v, registerInfestedBlock("infested_" + n + "_bricks", METAMORPHIC_BRICK.get(v)));
            METAMORPHIC_INFESTED_MOSSY_BRICK.put(v, registerInfestedBlock("infested_mossy_" + n + "_bricks", METAMORPHIC_MOSSY_BRICK.get(v)));
            METAMORPHIC_INFESTED_CRACKED_BRICK.put(v, registerInfestedBlock("infested_cracked_" + n + "_bricks", METAMORPHIC_CRACKED_BRICK.get(v)));
            METAMORPHIC_INFESTED_CHISELED_BRICK.put(v, registerInfestedBlock("infested_chiseled_" + n + "_bricks", METAMORPHIC_CHISELED_BRICK.get(v)));
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            String n = v.toString();
            SEDIMENTARY_INFESTED.put(v, registerInfestedBlock("infested_" + n, SEDIMENTARY_STONE.get(v)));
            SEDIMENTARY_INFESTED_COBBLE.put(v, registerInfestedBlock("infested_" + n + "_cobblestone", SEDIMENTARY_COBBLE.get(v)));
            SEDIMENTARY_INFESTED_BRICK.put(v, registerInfestedBlock("infested_" + n + "_bricks", SEDIMENTARY_BRICK.get(v)));
            SEDIMENTARY_INFESTED_MOSSY_BRICK.put(v, registerInfestedBlock("infested_mossy_" + n + "_bricks", SEDIMENTARY_MOSSY_BRICK.get(v)));
            SEDIMENTARY_INFESTED_CRACKED_BRICK.put(v, registerInfestedBlock("infested_cracked_" + n + "_bricks", SEDIMENTARY_CRACKED_BRICK.get(v)));
            SEDIMENTARY_INFESTED_CHISELED_BRICK.put(v, registerInfestedBlock("infested_chiseled_" + n + "_bricks", SEDIMENTARY_CHISELED_BRICK.get(v)));
        }

        for (IgneousVariant v : IgneousVariant.VALUES) {
            registerOres(v.toString());
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            registerOres(v.toString());
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            registerOres(v.toString());
        }
        for (VanillaOreHost host : VanillaOreHost.VALUES) {
            registerOres(host.getRegistryName());
        }
        for (String host : new String[]{"stone", "granite", "diorite", "andesite", "tuff"}) {
            VANILLA_HOST_OVERGROWN.put(host, registerOvergrown("overgrown_" + host, 1.5F, 6.0F));
            VANILLA_HOST_SNOWED.put(host, registerOvergrown("snowed_" + host, 1.5F, 6.0F));
        }
    }

    private static void registerOres(String variantName) {
        boolean isObsidian = variantName.equals("obsidian");
        float hardness = isObsidian ? 50.0F : 3.0F;
        float resistance = isObsidian ? 1200.0F : 3.0F;
        for (OreVariant ore : OreVariant.VALUES) {
            String name = variantName + "_" + ore.suffix;
            RegistryObject<Block> block = BLOCKS.register(name, () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .sound(SoundType.STONE)
                            .strength(hardness, resistance)
                            .requiresCorrectToolForDrops(),
                    ore.getXpProvider()));
            WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
            ORES.put(name, block);
        }
    }

    private static RegistryObject<Block> registerInfestedBlock(String name, RegistryObject<Block> hostRo) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new sxilverr.worldofstone.common.block.WosInfestedBlock(hostRo.get(),
                BlockBehaviour.Properties.of()
                        .mapColor(hostRo.get().defaultMapColor())
                        .strength(0.0F, 0.75F)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<Block> registerBlock(String name, float hardness, float resistance) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new Block(stoneProps(hardness, resistance)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<Block> registerStrataStone(String name, float hardness, float resistance, Supplier<Block> snowedVariant, Supplier<Block> cobbleVariant, boolean isLignite) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new StrataStoneBlock(stoneProps(hardness, resistance).randomTicks(), snowedVariant, cobbleVariant, isLignite));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<StairBlock> registerStairs(String name, Supplier<? extends Block> base, float hardness, float resistance) {
        RegistryObject<StairBlock> block = BLOCKS.register(name, () -> new StairBlock(() -> base.get().defaultBlockState(), stoneProps(hardness, resistance)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<SlabBlock> registerSlab(String name, float hardness, float resistance) {
        RegistryObject<SlabBlock> block = BLOCKS.register(name, () -> new SlabBlock(stoneProps(hardness, resistance)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<WallBlock> registerWall(String name, float hardness, float resistance) {
        RegistryObject<WallBlock> block = BLOCKS.register(name, () -> new WallBlock(stoneProps(hardness, resistance)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<SandBlock> registerSand(String name) {
        RegistryObject<SandBlock> block = BLOCKS.register(name, () -> new SandBlock(0xDDD2A3,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.SAND)
                        .sound(SoundType.SAND)
                        .strength(0.5F, 0.5F)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<GravelBlock> registerGravel(String name) {
        RegistryObject<GravelBlock> block = BLOCKS.register(name, () -> new GravelBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.STONE)
                        .sound(SoundType.GRAVEL)
                        .strength(0.6F, 0.6F)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<Block> registerClay(String name) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new Block(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.CLAY)
                        .sound(SoundType.GRAVEL)
                        .strength(0.6F, 0.6F)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<ButtonBlock> registerButton(String name) {
        RegistryObject<ButtonBlock> block = BLOCKS.register(name, () -> new ButtonBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.STONE)
                        .noCollission()
                        .strength(0.5F)
                        .pushReaction(net.minecraft.world.level.material.PushReaction.DESTROY),
                BlockSetType.STONE, 20, false));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<Block> registerSpeleothem(String name, float hardness, float resistance) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new WosSpeleothemBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.STONE)
                        .sound(SoundType.STONE)
                        .strength(hardness, resistance)
                        .noOcclusion()
                        .dynamicShape()
                        .requiresCorrectToolForDrops()));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<Block> registerSpeleothemFromHost(String name, String hostBlockId, float fallbackHardness, float fallbackResistance) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> {
            BlockBehaviour.Properties props = null;
            if (hostBlockId != null) {
                net.minecraft.resources.ResourceLocation rl = net.minecraft.resources.ResourceLocation.tryParse(hostBlockId);
                if (rl != null) {
                    Block host = ForgeRegistries.BLOCKS.getValue(rl);
                    if (host != null && host != net.minecraft.world.level.block.Blocks.AIR) {
                        final int hostLight = host.defaultBlockState().getLightEmission();
                        final SoundType hostSound = host.getSoundType(host.defaultBlockState());
                        props = BlockBehaviour.Properties.copy(host)
                                .mapColor(host.defaultMapColor())
                                .sound(hostSound)
                                .lightLevel(state -> hostLight)
                                .emissiveRendering((s, l, p) -> false)
                                .hasPostProcess((s, l, p) -> false)
                                .isRedstoneConductor((s, l, p) -> false)
                                .isSuffocating((s, l, p) -> false)
                                .isViewBlocking((s, l, p) -> false)
                                .noOcclusion()
                                .dynamicShape();
                    }
                }
            }
            if (props == null) {
                props = BlockBehaviour.Properties.of()
                        .mapColor(MapColor.STONE)
                        .sound(SoundType.STONE)
                        .strength(fallbackHardness, fallbackResistance)
                        .noOcclusion()
                        .dynamicShape()
                        .requiresCorrectToolForDrops();
            }
            return new WosSpeleothemBlock(props);
        });
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<Block> registerDecorativeSpeleothem(DecorativeSpeleothemVariant v) {
        String name = v.getRegistryName();
        int light = v.getLightLevel();
        boolean unbreakable = v == DecorativeSpeleothemVariant.BEDROCK || v == DecorativeSpeleothemVariant.END_PORTAL_FRAME;
        boolean noTool = NO_TOOL_VARIANTS.contains(v);
        boolean isMagma = v == DecorativeSpeleothemVariant.MAGMA;
        boolean isSlime = v == DecorativeSpeleothemVariant.SLIME;
        boolean isHoney = v == DecorativeSpeleothemVariant.HONEY;
        boolean isSoulSand = v == DecorativeSpeleothemVariant.SOUL_SAND;
        String hostId = decorativeHostBlockId(v);
        RegistryObject<Block> block = BLOCKS.register(name, () -> {
            BlockBehaviour.Properties props = null;
            if (!unbreakable && hostId != null) {
                net.minecraft.resources.ResourceLocation rl = net.minecraft.resources.ResourceLocation.tryParse(hostId);
                if (rl != null) {
                    Block host = ForgeRegistries.BLOCKS.getValue(rl);
                    if (host != null && host != net.minecraft.world.level.block.Blocks.AIR) {
                        final SoundType hostSound = host.getSoundType(host.defaultBlockState());
                        props = BlockBehaviour.Properties.copy(host)
                                .mapColor(host.defaultMapColor())
                                .sound(hostSound)
                                .emissiveRendering((s, l, p) -> false)
                                .hasPostProcess((s, l, p) -> false)
                                .isRedstoneConductor((s, l, p) -> false)
                                .isSuffocating((s, l, p) -> false)
                                .isViewBlocking((s, l, p) -> false)
                                .noOcclusion()
                                .dynamicShape();
                    }
                }
            }
            if (props == null) {
                float hardness = unbreakable ? -1.0F : v.getHardness();
                float resistance = unbreakable ? 3600000.0F : v.getResistance();
                props = BlockBehaviour.Properties.of()
                        .mapColor(v.getMapColor())
                        .sound(v.getSoundType())
                        .strength(hardness, resistance)
                        .noOcclusion()
                        .dynamicShape();
                if (!noTool) {
                    props = props.requiresCorrectToolForDrops();
                }
            }
            final int effectiveLight = light;
            props = props.lightLevel(state -> effectiveLight);
            if (isMagma) {
                return new sxilverr.worldofstone.common.block.WosMagmaSpeleothemBlock(props);
            }
            if (isSlime) {
                return new sxilverr.worldofstone.common.block.WosSlimeSpeleothemBlock(props);
            }
            if (isHoney) {
                return new sxilverr.worldofstone.common.block.WosHoneySpeleothemBlock(props);
            }
            if (isSoulSand) {
                return new sxilverr.worldofstone.common.block.WosSoulSandSpeleothemBlock(props);
            }
            return new WosSpeleothemBlock(props);
        });
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static String decorativeHostBlockId(DecorativeSpeleothemVariant v) {
        String reg = v.toString();
        if (reg.equals("hay_bale")) return "minecraft:hay_block";
        java.util.Set<String> needBlockSuffix = java.util.Set.of(
                "bone", "raw_iron", "raw_copper", "raw_gold",
                "magma", "amethyst", "purpur", "quartz", "coal", "copper",
                "iron", "gold", "redstone", "emerald", "lapis", "diamond", "netherite",
                "snow", "slime", "honey", "moss",
                "honeycomb", "grass",
                "brown_mushroom", "red_mushroom", "nether_wart", "warped_wart",
                "tube_coral", "brain_coral", "bubble_coral", "fire_coral", "horn_coral",
                "dead_tube_coral", "dead_brain_coral", "dead_bubble_coral", "dead_fire_coral", "dead_horn_coral",
                "bamboo", "stripped_bamboo"
        );
        if (needBlockSuffix.contains(reg)) return "minecraft:" + reg + "_block";
        return "minecraft:" + reg;
    }

    private static String aetherHostBlockId(sxilverr.worldofstone.api.enums.AetherSpeleothemVariant v) {
        switch (v) {
            case AETHER_HOLYSTONE: return "aether:holystone";
            case AETHER_AEROGEL:   return "aether:aerogel";
            default: return null;
        }
    }

    private static String blueSkiesHostBlockId(sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant v) {
        switch (v) {
            case BLUE_SKIES_LUNAR_STONE:        return "blue_skies:lunar_stone";
            case BLUE_SKIES_TURQUOISE_STONE:    return "blue_skies:turquoise_stone";
            case BLUE_SKIES_MIDNIGHT_SANDSTONE: return "blue_skies:midnight_sandstone";
            case BLUE_SKIES_CRYSTAL_SANDSTONE:  return "blue_skies:crystal_sandstone";
            default: return null;
        }
    }

    private static RegistryObject<RotatedPillarBlock> registerPillar(String name, float hardness, float resistance) {
        RegistryObject<RotatedPillarBlock> block = BLOCKS.register(name, () -> new RotatedPillarBlock(stoneProps(hardness, resistance)));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<Block> registerOvergrown(String name, float hardness, float resistance) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new Block(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.STONE)
                        .sound(SoundType.STONE)
                        .strength(hardness, resistance)
                        .requiresCorrectToolForDrops()));
        WosItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static BlockBehaviour.Properties stoneProps(float hardness, float resistance) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .sound(SoundType.STONE)
                .strength(hardness, resistance)
                .requiresCorrectToolForDrops();
    }

    public static void init() {
    }

    private WosBlocks() {
    }
}
