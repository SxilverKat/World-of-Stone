package com.sxilverr.worldofstone.world;

import com.sxilverr.worldofstone.api.enums.DecorativeSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.IgneousVariant;
import com.sxilverr.worldofstone.api.enums.MetamorphicVariant;
import com.sxilverr.worldofstone.api.enums.QuarkSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.SedimentaryVariant;
import com.sxilverr.worldofstone.api.enums.TerracottaSpeleothemVariant;
import com.sxilverr.worldofstone.api.enums.VanillaSpeleothemVariant;
import com.sxilverr.worldofstone.api.ModInfo;
import com.sxilverr.worldofstone.common.block.WosSpeleothemBlock;
import com.sxilverr.worldofstone.config.WosConfig;
import com.sxilverr.worldofstone.registry.WosBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.core.registries.BuiltInRegistries;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;

import java.util.HashMap;
import java.util.Map;

public class SpeleothemFeature extends Feature<NoneFeatureConfiguration> {

    public SpeleothemFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        if (!WosConfig.speleothemsEnabled || !WosConfig.speleothemsGenerate) return false;
        WorldGenLevel level = ctx.level();
        BlockPos origin = ctx.origin();
        RandomSource rand = ctx.random();

        Map<Block, Block> stoneToSpeleothem = buildStoneToSpeleothemMap();
        if (stoneToSpeleothem.isEmpty()) return false;

        ChunkAccess chunk = level.getChunk(origin);
        int chunkX = chunk.getPos().getMinBlockX();
        int chunkZ = chunk.getPos().getMinBlockZ();
        int minY = level.getMinBuildHeight() + 4;
        ResourceKey<Level> dim = level.getLevel().dimension();
        int seaLevel = level.getSeaLevel();
        int dimensionCap;
        if (dim == Level.NETHER) dimensionCap = 120;
        else if (dim == Level.END) dimensionCap = 200;
        else dimensionCap = WosConfig.speleothemsAllowAboveGround ? level.getMaxBuildHeight() - 4 : 80;
        int maxY = Math.min(level.getMaxBuildHeight() - 4, dimensionCap);
        int yRange = Math.max(1, maxY - minY);
        boolean isOverworld = dim == Level.OVERWORLD;
        boolean isEnd = dim == Level.END;

        int attempts = Math.max(1, WosConfig.speleothemAttemptsPerChunk) * 32;
        int rarity = Math.max(1, WosConfig.speleothemRarity);

        int placedClusters = 0;
        for (int i = 0; i < attempts; i++) {
            if (rarity > 1 && rand.nextInt(rarity) != 0) continue;

            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            int y = minY + rand.nextInt(yRange);
            BlockPos pos = new BlockPos(x, y, z);

            BlockState atPos = level.getBlockState(pos);
            FluidState atFluid = atPos.getFluidState();
            boolean isAir = atPos.isAir();
            boolean isWater = !isAir && atFluid.is(FluidTags.WATER);
            boolean isLava = !isAir && atFluid.is(FluidTags.LAVA);
            boolean canPlaceHere = isAir
                    || (isWater && WosConfig.speleothemsGenerateInWater)
                    || (isLava && WosConfig.speleothemsGenerateInLava);
            if (!canPlaceHere) continue;

            int heightmapY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
            boolean exposed = pos.getY() >= heightmapY;
            if (exposed) {
                boolean allowSky = isEnd ? WosConfig.speleothemsAllowSkyViewEnd : WosConfig.speleothemsAllowSkyView;
                if (!allowSky) continue;
            }

            boolean aboveGround = isOverworld && y > seaLevel;
            if (aboveGround && !WosConfig.speleothemsAllowAboveGround) continue;

            BlockPos above = pos.above();
            BlockPos below = pos.below();
            BlockState aboveState = level.getBlockState(above);
            BlockState belowState = level.getBlockState(below);
            boolean ceilingSturdy = aboveState.isFaceSturdy(level, above, Direction.DOWN);
            boolean floorSturdy = belowState.isFaceSturdy(level, below, Direction.UP);
            if (!ceilingSturdy && !floorSturdy) continue;

            boolean blockPointingUp = aboveGround && !WosConfig.speleothemsAllowPointingUpAboveGround;

            Direction tip;
            Block surrounding;
            if (ceilingSturdy && floorSturdy) {
                if (blockPointingUp) {
                    tip = Direction.DOWN;
                    surrounding = aboveState.getBlock();
                } else if (rand.nextBoolean()) {
                    tip = Direction.DOWN;
                    surrounding = aboveState.getBlock();
                } else {
                    tip = Direction.UP;
                    surrounding = belowState.getBlock();
                }
            } else if (ceilingSturdy) {
                tip = Direction.DOWN;
                surrounding = aboveState.getBlock();
            } else {
                if (blockPointingUp) continue;
                tip = Direction.UP;
                surrounding = belowState.getBlock();
            }

            Block speleothem = stoneToSpeleothem.get(surrounding);
            if (speleothem == null) continue;

            int length = (rand.nextInt(10) < 7) ? 3 : 2;

            if (placeCluster(level, pos, tip, speleothem, length, surrounding)) {
                placedClusters++;
            }
        }
        return placedClusters > 0;
    }

    private boolean placeCluster(WorldGenLevel level, BlockPos start, Direction tip, Block speleothem, int length, Block hostBlock) {
        WosSpeleothemBlock.Size[] order = {
                WosSpeleothemBlock.Size.LARGE,
                WosSpeleothemBlock.Size.MEDIUM,
                WosSpeleothemBlock.Size.SMALL
        };
        boolean placedAny = false;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < length; i++) {
            int yOffset = (tip == Direction.DOWN) ? -i : i;
            pos.set(start.getX(), start.getY() + yOffset, start.getZ());
            BlockState atPos = level.getBlockState(pos);
            FluidState atFluid = atPos.getFluidState();
            boolean atAir = atPos.isAir();
            boolean atWater = !atAir && atFluid.is(FluidTags.WATER);
            boolean atLava = !atAir && atFluid.is(FluidTags.LAVA);
            if (i > 0) {
                boolean canPlace = atAir
                        || (atWater && WosConfig.speleothemsGenerateInWater)
                        || (atLava && WosConfig.speleothemsGenerateInLava);
                if (!canPlace) break;
            }
            boolean waterlogged = atWater && WosConfig.speleothemsWaterloggable;
            WosSpeleothemBlock.Size size = order[3 - length + i];
            BlockState state = speleothem.defaultBlockState()
                    .setValue(WosSpeleothemBlock.TIP_DIRECTION, tip)
                    .setValue(WosSpeleothemBlock.SIZE, size)
                    .setValue(WosSpeleothemBlock.WATERLOGGED, waterlogged);
            if (level.setBlock(pos, state, 3)) {
                placedAny = true;
                if (WosBlocks.MIMIC_SPELEOTHEM != null && speleothem == WosBlocks.MIMIC_SPELEOTHEM.get()) {
                    MimicHook.INSTANCE.setSource(level, pos, hostBlock.defaultBlockState());
                }
            }
        }
        return placedAny;
    }

    private static Block blockOrNull(net.minecraft.resources.ResourceLocation rl) {
        return BuiltInRegistries.BLOCK.containsKey(rl) ? BuiltInRegistries.BLOCK.get(rl) : null;
    }

    private static void mapIceHost(Map<Block, Block> map, String hostId, DecorativeSpeleothemVariant variant) {
        Block host = blockOrNull(ModInfo.id(hostId));
        RegistrySupplier<Block> spRo = WosBlocks.DECORATIVE_SPELEOTHEM.get(variant);
        if (host != null && spRo != null && spRo.isPresent()) {
            map.put(host, spRo.get());
        }
    }

    private static Map<Block, Block> buildStoneToSpeleothemMap() {
        Map<Block, Block> map = new HashMap<>();
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (WosConfig.isSpeleothemEnabled(v)) {
                RegistrySupplier<Block> stoneRo = WosBlocks.IGNEOUS_STONE.get(v);
                RegistrySupplier<Block> spRo = WosBlocks.IGNEOUS_SPELEOTHEM.get(v);
                if (stoneRo != null && stoneRo.isPresent() && spRo != null && spRo.isPresent()) {
                    map.put(stoneRo.get(), spRo.get());
                }
            }
            if (WosConfig.isSandstoneSpeleothemEnabled(v)) {
                RegistrySupplier<Block> sandRo = WosBlocks.IGNEOUS_SANDSTONE.get(v);
                RegistrySupplier<Block> sandSpRo = WosBlocks.IGNEOUS_SANDSTONE_SPELEOTHEM.get(v);
                if (sandRo != null && sandRo.isPresent() && sandSpRo != null && sandSpRo.isPresent()) {
                    map.put(sandRo.get(), sandSpRo.get());
                }
            }
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (WosConfig.isSpeleothemEnabled(v)) {
                RegistrySupplier<Block> stoneRo = WosBlocks.METAMORPHIC_STONE.get(v);
                RegistrySupplier<Block> spRo = WosBlocks.METAMORPHIC_SPELEOTHEM.get(v);
                if (stoneRo != null && stoneRo.isPresent() && spRo != null && spRo.isPresent()) {
                    map.put(stoneRo.get(), spRo.get());
                }
            }
            if (WosConfig.isSandstoneSpeleothemEnabled(v)) {
                RegistrySupplier<Block> sandRo = WosBlocks.METAMORPHIC_SANDSTONE.get(v);
                RegistrySupplier<Block> sandSpRo = WosBlocks.METAMORPHIC_SANDSTONE_SPELEOTHEM.get(v);
                if (sandRo != null && sandRo.isPresent() && sandSpRo != null && sandSpRo.isPresent()) {
                    map.put(sandRo.get(), sandSpRo.get());
                }
            }
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (WosConfig.isSpeleothemEnabled(v)) {
                RegistrySupplier<Block> stoneRo = WosBlocks.SEDIMENTARY_STONE.get(v);
                RegistrySupplier<Block> spRo = WosBlocks.SEDIMENTARY_SPELEOTHEM.get(v);
                if (stoneRo != null && stoneRo.isPresent() && spRo != null && spRo.isPresent()) {
                    map.put(stoneRo.get(), spRo.get());
                }
            }
            if (WosConfig.isSandstoneSpeleothemEnabled(v)) {
                RegistrySupplier<Block> sandRo = WosBlocks.SEDIMENTARY_SANDSTONE.get(v);
                RegistrySupplier<Block> sandSpRo = WosBlocks.SEDIMENTARY_SANDSTONE_SPELEOTHEM.get(v);
                if (sandRo != null && sandRo.isPresent() && sandSpRo != null && sandSpRo.isPresent()) {
                    map.put(sandRo.get(), sandSpRo.get());
                }
            }
        }

        Map<String, VanillaSpeleothemVariant> vanillaMap = new HashMap<>();
        vanillaMap.put("minecraft:stone", VanillaSpeleothemVariant.STONE);
        vanillaMap.put("minecraft:granite", VanillaSpeleothemVariant.GRANITE);
        vanillaMap.put("minecraft:diorite", VanillaSpeleothemVariant.DIORITE);
        vanillaMap.put("minecraft:andesite", VanillaSpeleothemVariant.ANDESITE);
        vanillaMap.put("minecraft:deepslate", VanillaSpeleothemVariant.DEEPSLATE);
        vanillaMap.put("minecraft:tuff", VanillaSpeleothemVariant.TUFF);
        vanillaMap.put("minecraft:calcite", VanillaSpeleothemVariant.CALCITE);
        vanillaMap.put("minecraft:dripstone_block", VanillaSpeleothemVariant.DRIPSTONE);
        vanillaMap.put("minecraft:netherrack", VanillaSpeleothemVariant.NETHERRACK);
        vanillaMap.put("minecraft:basalt", VanillaSpeleothemVariant.BASALT);
        vanillaMap.put("minecraft:smooth_basalt", VanillaSpeleothemVariant.SMOOTH_BASALT);
        vanillaMap.put("minecraft:blackstone", VanillaSpeleothemVariant.BLACKSTONE);
        vanillaMap.put("minecraft:end_stone", VanillaSpeleothemVariant.END_STONE);
        vanillaMap.put("minecraft:sandstone", VanillaSpeleothemVariant.SANDSTONE);
        vanillaMap.put("minecraft:red_sandstone", VanillaSpeleothemVariant.RED_SANDSTONE);
        for (Map.Entry<String, VanillaSpeleothemVariant> e : vanillaMap.entrySet()) {
            if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
            Block stone = blockOrNull(ModInfo.id(e.getKey()));
            RegistrySupplier<Block> spRo = WosBlocks.VANILLA_SPELEOTHEM.get(e.getValue());
            if (stone != null && spRo != null && spRo.isPresent()) {
                map.put(stone, spRo.get());
            }
        }

        if (WosConfig.terracottaSpeleothemsEnabled) {
            for (TerracottaSpeleothemVariant v : TerracottaSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id(v.getHostBlock()));
                RegistrySupplier<Block> spRo = WosBlocks.TERRACOTTA_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) {
                    map.put(stone, spRo.get());
                }
            }
        }

        if (WosConfig.iceSpeleothemEnabled) {
            mapIceHost(map, "minecraft:ice", DecorativeSpeleothemVariant.ICE);
            mapIceHost(map, "minecraft:packed_ice", DecorativeSpeleothemVariant.PACKED_ICE);
            mapIceHost(map, "minecraft:blue_ice", DecorativeSpeleothemVariant.BLUE_ICE);
        }

        if (Platform.isModLoaded("quark")) {
            Map<String, QuarkSpeleothemVariant> quarkMap = new HashMap<>();
            quarkMap.put("quark:limestone", QuarkSpeleothemVariant.QUARK_LIMESTONE);
            quarkMap.put("quark:shale", QuarkSpeleothemVariant.QUARK_SHALE);
            quarkMap.put("quark:myalite", QuarkSpeleothemVariant.QUARK_MYALITE);
            quarkMap.put("quark:permafrost", QuarkSpeleothemVariant.QUARK_PERMAFROST);
            quarkMap.put("quark:jasper", QuarkSpeleothemVariant.QUARK_JASPER);
            quarkMap.put("quark:dusky_myalite", QuarkSpeleothemVariant.QUARK_DUSKY_MYALITE);
            for (Map.Entry<String, QuarkSpeleothemVariant> e : quarkMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.QUARK_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) {
                    map.put(stone, spRo.get());
                }
            }
        }

        if (Platform.isModLoaded("undergarden")) {
            Map<String, com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant> ugMap = new HashMap<>();
            ugMap.put("undergarden:depthrock", com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.UNDERGARDEN_DEPTHROCK);
            ugMap.put("undergarden:shiverstone", com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.UNDERGARDEN_SHIVERSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant> e : ugMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.UNDERGARDEN_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("create")) {
            Map<String, com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant> cMap = new HashMap<>();
            cMap.put("create:limestone", com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_LIMESTONE);
            cMap.put("create:scoria", com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_SCORIA);
            cMap.put("create:scorchia", com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_SCORCHIA);
            cMap.put("create:asurine", com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_ASURINE);
            cMap.put("create:ochrum", com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_OCHRUM);
            cMap.put("create:veridium", com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_VERIDIUM);
            cMap.put("create:crimsite", com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_CRIMSITE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.CreateSpeleothemVariant> e : cMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.CREATE_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("betterend")) {
            Map<String, com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant> beMap = new HashMap<>();
            beMap.put("betterend:flavolite", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_FLAVOLITE);
            beMap.put("betterend:violecite", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_VIOLECITE);
            beMap.put("betterend:virid_jadestone", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_VIRID_JADESTONE);
            beMap.put("betterend:azure_jadestone", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_AZURE_JADESTONE);
            beMap.put("betterend:sandy_jadestone", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_SANDY_JADESTONE);
            beMap.put("betterend:sulphuric_rock", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_SULPHURIC_ROCK);
            beMap.put("betterend:umbralith", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_UMBRALITH);
            beMap.put("betterend:brimstone", com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_BRIMSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant> e : beMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.BETTEREND_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("galosphere")) {
            Map<String, com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant> gMap = new HashMap<>();
            gMap.put("galosphere:allurite_block", com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.GALOSPHERE_ALLURITE);
            gMap.put("galosphere:lumiere_block", com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.GALOSPHERE_LUMIERE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant> e : gMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.GALOSPHERE_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("biomeswevegone")) {
            Map<String, com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant> bwgMap = new HashMap<>();
            bwgMap.put("biomeswevegone:dacite", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_DACITE);
            bwgMap.put("biomeswevegone:white_dacite", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_WHITE_DACITE);
            bwgMap.put("biomeswevegone:red_rock", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_RED_ROCK);
            bwgMap.put("biomeswevegone:black_sandstone", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_BLACK_SANDSTONE);
            bwgMap.put("biomeswevegone:white_sandstone", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_WHITE_SANDSTONE);
            bwgMap.put("biomeswevegone:blue_sandstone", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_BLUE_SANDSTONE);
            bwgMap.put("biomeswevegone:purple_sandstone", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_PURPLE_SANDSTONE);
            bwgMap.put("biomeswevegone:pink_sandstone", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_PINK_SANDSTONE);
            bwgMap.put("biomeswevegone:windswept_sandstone", com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_WINDSWEPT_SANDSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.BwgSpeleothemVariant> e : bwgMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.BWG_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("twilightforest")) {
            Map<String, com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant> tfMap = new HashMap<>();
            tfMap.put("twilightforest:mazestone", com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.TWILIGHTFOREST_MAZESTONE);
            tfMap.put("twilightforest:deadrock", com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.TWILIGHTFOREST_DEADROCK);
            tfMap.put("twilightforest:trollsteinn", com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.TWILIGHTFOREST_TROLLSTEINN);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant> e : tfMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.TWILIGHTFOREST_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("aether")) {
            Map<String, com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant> aMap = new HashMap<>();
            aMap.put("aether:holystone", com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.AETHER_HOLYSTONE);
            aMap.put("aether:aerogel", com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.AETHER_AEROGEL);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.AetherSpeleothemVariant> e : aMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.AETHER_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("blue_skies")) {
            Map<String, com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant> bsMap = new HashMap<>();
            bsMap.put("blue_skies:lunar_stone", com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_LUNAR_STONE);
            bsMap.put("blue_skies:turquoise_stone", com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_TURQUOISE_STONE);
            bsMap.put("blue_skies:midnight_sandstone", com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_MIDNIGHT_SANDSTONE);
            bsMap.put("blue_skies:crystal_sandstone", com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_CRYSTAL_SANDSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant> e : bsMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.BLUE_SKIES_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("spelunkery")) {
            Map<String, com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant> spMap = new HashMap<>();
            spMap.put("spelunkery:rock_salt_block", com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.SPELUNKERY_ROCK_SALT_BLOCK);
            spMap.put("spelunkery:nephrite", com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.SPELUNKERY_NEPHRITE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant> e : spMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.SPELUNKERY_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("iceandfire")) {
            for (com.sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("iceandfire:dread_stone"));
                RegistrySupplier<Block> spRo = WosBlocks.ICEANDFIRE_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("mysticalagriculture")) {
            for (com.sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("mysticalagriculture:soulstone"));
                RegistrySupplier<Block> spRo = WosBlocks.MYSTICALAGRICULTURE_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("biomesoplenty")) {
            Map<String, com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant> bopMap = new HashMap<>();
            bopMap.put("biomesoplenty:white_sandstone", com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_WHITE_SANDSTONE);
            bopMap.put("biomesoplenty:orange_sandstone", com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_ORANGE_SANDSTONE);
            bopMap.put("biomesoplenty:black_sandstone", com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_BLACK_SANDSTONE);
            bopMap.put("biomesoplenty:brimstone", com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_BRIMSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant> e : bopMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.BIOMESOPLENTY_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("forbidden_arcanus")) {
            Map<String, com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant> faMap = new HashMap<>();
            faMap.put("forbidden_arcanus:darkstone", com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.FORBIDDEN_ARCANUS_DARKSTONE);
            faMap.put("forbidden_arcanus:soulless_sandstone", com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.FORBIDDEN_ARCANUS_SOULLESS_SANDSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant> e : faMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.FORBIDDEN_ARCANUS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("alexscaves")) {
            Map<String, com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant> acMap = new HashMap<>();
            acMap.put("alexscaves:galena", com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_GALENA);
            acMap.put("alexscaves:limestone", com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_LIMESTONE);
            acMap.put("alexscaves:radrock", com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_RADROCK);
            acMap.put("alexscaves:abyssmarine", com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_ABYSSMARINE);
            acMap.put("alexscaves:guanostone", com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_GUANOSTONE);
            acMap.put("alexscaves:coprolith", com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_COPROLITH);
            acMap.put("alexscaves:gingerbread_block", com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_GINGERBREAD_BLOCK);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant> e : acMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.ALEXSCAVES_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("ars_nouveau")) {
            for (com.sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("ars_nouveau:sourcestone"));
                RegistrySupplier<Block> spRo = WosBlocks.ARS_NOUVEAU_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("cataclysm")) {
            for (com.sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("cataclysm:azure_seastone"));
                RegistrySupplier<Block> spRo = WosBlocks.CATACLYSM_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("twigs")) {
            Map<String, com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant> twMap = new HashMap<>();
            twMap.put("twigs:schist", com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.TWIGS_SCHIST);
            twMap.put("twigs:rhyolite", com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.TWIGS_RHYOLITE);
            twMap.put("twigs:bloodstone", com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.TWIGS_BLOODSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant> e : twMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.TWIGS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("architects_palette")) {
            Map<String, com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant> apMap = new HashMap<>();
            apMap.put("architects_palette:abyssaline", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_ABYSSALINE);
            apMap.put("architects_palette:myonite", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_MYONITE);
            apMap.put("architects_palette:hadaline", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_HADALINE);
            apMap.put("architects_palette:esoterrack", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_ESOTERRACK);
            apMap.put("architects_palette:onyx", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_ONYX);
            apMap.put("architects_palette:wardstone", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_WARDSTONE);
            apMap.put("architects_palette:moonshale", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_MOONSHALE);
            apMap.put("architects_palette:nebulite", com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_NEBULITE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant> e : apMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.ARCHITECTS_PALETTE_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("outer_end")) {
            Map<String, com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant> oeMap = new HashMap<>();
            oeMap.put("outer_end:violite", com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_VIOLITE);
            oeMap.put("outer_end:stromatolite", com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_STROMATOLITE);
            oeMap.put("outer_end:halite", com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_HALITE);
            oeMap.put("outer_end:ancient_stone", com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_ANCIENT_STONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant> e : oeMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.OUTER_END_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("botania")) {
            Map<String, com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant> boMap = new HashMap<>();
            boMap.put("botania:livingrock", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_LIVINGROCK);
            boMap.put("botania:shimmerrock", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_SHIMMERROCK);
            boMap.put("botania:metamorphic_forest_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_FOREST_STONE);
            boMap.put("botania:metamorphic_plains_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_PLAINS_STONE);
            boMap.put("botania:metamorphic_mountain_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_MOUNTAIN_STONE);
            boMap.put("botania:metamorphic_fungal_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_FUNGAL_STONE);
            boMap.put("botania:metamorphic_swamp_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_SWAMP_STONE);
            boMap.put("botania:metamorphic_desert_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_DESERT_STONE);
            boMap.put("botania:metamorphic_taiga_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_TAIGA_STONE);
            boMap.put("botania:metamorphic_mesa_stone", com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_MESA_STONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant> e : boMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.BOTANIA_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("ad_astra")) {
            Map<String, com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant> aaMap = new HashMap<>();
            aaMap.put("ad_astra:sky_stone", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_SKY_STONE);
            aaMap.put("ad_astra:moon_stone", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MOON_STONE);
            aaMap.put("ad_astra:moon_deepslate", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MOON_DEEPSLATE);
            aaMap.put("ad_astra:mars_stone", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MARS_STONE);
            aaMap.put("ad_astra:venus_stone", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_VENUS_STONE);
            aaMap.put("ad_astra:venus_sandstone", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_VENUS_SANDSTONE);
            aaMap.put("ad_astra:mercury_stone", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MERCURY_STONE);
            aaMap.put("ad_astra:glacio_stone", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_GLACIO_STONE);
            aaMap.put("ad_astra:permafrost", com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_PERMAFROST);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant> e : aaMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.AD_ASTRA_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("deep_aether")) {
            Map<String, com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant> daMap = new HashMap<>();
            daMap.put("deep_aether:aseterite", com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.DEEP_AETHER_ASETERITE);
            daMap.put("deep_aether:raw_clorite", com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.DEEP_AETHER_RAW_CLORITE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant> e : daMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.DEEP_AETHER_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("caverns_and_chasms")) {
            Map<String, com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant> ccMap = new HashMap<>();
            ccMap.put("caverns_and_chasms:sugilite", com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.CAVERNS_AND_CHASMS_SUGILITE);
            ccMap.put("caverns_and_chasms:cylindrite", com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.CAVERNS_AND_CHASMS_CYLINDRITE);
            ccMap.put("caverns_and_chasms:rhyolite", com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.CAVERNS_AND_CHASMS_RHYOLITE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant> e : ccMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.CAVERNS_AND_CHASMS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("atmospheric")) {
            Map<String, com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant> atMap = new HashMap<>();
            atMap.put("atmospheric:ivory_travertine", com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_IVORY_TRAVERTINE);
            atMap.put("atmospheric:peach_travertine", com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_PEACH_TRAVERTINE);
            atMap.put("atmospheric:persimmon_travertine", com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_PERSIMMON_TRAVERTINE);
            atMap.put("atmospheric:saffron_travertine", com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_SAFFRON_TRAVERTINE);
            atMap.put("atmospheric:dolerite", com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_DOLERITE);
            atMap.put("atmospheric:arid_sandstone", com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_ARID_SANDSTONE);
            atMap.put("atmospheric:red_arid_sandstone", com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_RED_ARID_SANDSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant> e : atMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.ATMOSPHERIC_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("endergetic")) {
            for (com.sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("endergetic:eumus"));
                RegistrySupplier<Block> spRo = WosBlocks.ENDERGETIC_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("wilder_wilds")) {
            Map<String, com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant> wwMap = new HashMap<>();
            wwMap.put("wilder_wilds:lavenderhardenedclay", com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.WILDER_WILDS_LAVENDERHARDENEDCLAY);
            wwMap.put("wilder_wilds:coral_hardened_clay", com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.WILDER_WILDS_CORAL_HARDENED_CLAY);
            wwMap.put("wilder_wilds:cream_hardened_clay", com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.WILDER_WILDS_CREAM_HARDENED_CLAY);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant> e : wwMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.WILDER_WILDS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("regions_unexplored")) {
            Map<String, com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant> ruMap = new HashMap<>();
            ruMap.put("regions_unexplored:chalk", com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.REGIONS_UNEXPLORED_CHALK);
            ruMap.put("regions_unexplored:argillite", com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.REGIONS_UNEXPLORED_ARGILLITE);
            ruMap.put("regions_unexplored:mossy_stone", com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.REGIONS_UNEXPLORED_MOSSY_STONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant> e : ruMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.REGIONS_UNEXPLORED_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("born_in_chaos_v1")) {
            for (com.sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("born_in_chaos_v1:black_argillite"));
                RegistrySupplier<Block> spRo = WosBlocks.BORN_IN_CHAOS_V1_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("naturalist")) {
            for (com.sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("naturalist:shellstone"));
                RegistrySupplier<Block> spRo = WosBlocks.NATURALIST_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("yungscavebiomes")) {
            for (com.sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("yungscavebiomes:ancient_sandstone"));
                RegistrySupplier<Block> spRo = WosBlocks.YUNGSCAVEBIOMES_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("natures_spirit")) {
            Map<String, com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant> nsMap = new HashMap<>();
            nsMap.put("natures_spirit:travertine", com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.NATURES_SPIRIT_TRAVERTINE);
            nsMap.put("natures_spirit:chert", com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.NATURES_SPIRIT_CHERT);
            nsMap.put("natures_spirit:pink_sandstone", com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.NATURES_SPIRIT_PINK_SANDSTONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant> e : nsMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.NATURES_SPIRIT_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("netherexp")) {
            Map<String, com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant> neMap = new HashMap<>();
            neMap.put("netherexp:soul_slate", com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.NETHEREXP_SOUL_SLATE);
            neMap.put("netherexp:pale_soul_slate", com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.NETHEREXP_PALE_SOUL_SLATE);
            neMap.put("netherexp:black_ice", com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.NETHEREXP_BLACK_ICE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant> e : neMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.NETHEREXP_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("deeperdarker")) {
            Map<String, com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant> ddMap = new HashMap<>();
            ddMap.put("deeperdarker:sculk_stone", com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.DEEPERDARKER_SCULK_STONE);
            ddMap.put("deeperdarker:gloomslate", com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.DEEPERDARKER_GLOOMSLATE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant> e : ddMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.DEEPERDARKER_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("the_deep_void")) {
            Map<String, com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant> tdvMap = new HashMap<>();
            tdvMap.put("the_deep_void:ancient_deepslate", com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_ANCIENT_DEEPSLATE);
            tdvMap.put("the_deep_void:primordial_stone", com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_PRIMORDIAL_STONE);
            tdvMap.put("the_deep_void:solid_void_block", com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_SOLID_VOID_BLOCK);
            tdvMap.put("the_deep_void:monolithic_stone", com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_MONOLITHIC_STONE);
            for (Map.Entry<String, com.sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant> e : tdvMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = blockOrNull(ModInfo.id(e.getKey()));
                RegistrySupplier<Block> spRo = WosBlocks.THE_DEEP_VOID_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (Platform.isModLoaded("defiled_lands_preborn")) {
            for (com.sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v : com.sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = blockOrNull(ModInfo.id("defiled_lands_preborn:defiled_stone"));
                RegistrySupplier<Block> spRo = WosBlocks.DEFILED_LANDS_PREBORN_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        for (String blockId : WosConfig.mimicSpeleothemWorldgenBlocks) {
            ResourceLocation rl = ResourceLocation.tryParse(blockId);
            if (rl == null) continue;
            Block source = blockOrNull(rl);
            if (source != null && source != net.minecraft.world.level.block.Blocks.AIR && WosBlocks.MIMIC_SPELEOTHEM != null) {
                map.put(source, WosBlocks.MIMIC_SPELEOTHEM.get());
            }
        }

        return map;
    }
}
