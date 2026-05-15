package sxilverr.worldofstone.world;

import sxilverr.worldofstone.api.enums.DecorativeSpeleothemVariant;
import sxilverr.worldofstone.api.enums.IgneousVariant;
import sxilverr.worldofstone.api.enums.MetamorphicVariant;
import sxilverr.worldofstone.api.enums.QuarkSpeleothemVariant;
import sxilverr.worldofstone.api.enums.SedimentaryVariant;
import sxilverr.worldofstone.api.enums.TerracottaSpeleothemVariant;
import sxilverr.worldofstone.api.enums.VanillaSpeleothemVariant;
import sxilverr.worldofstone.common.block.WosSpeleothemBlock;
import sxilverr.worldofstone.config.WosConfig;
import sxilverr.worldofstone.registry.WosBlocks;
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
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
                if (speleothem == WosBlocks.MIMIC_SPELEOTHEM.get()) {
                    net.minecraft.world.level.block.entity.BlockEntity be = level.getBlockEntity(pos);
                    if (be instanceof sxilverr.worldofstone.common.block.WosMimicSpeleothemBlockEntity mimic) {
                        mimic.setSource(hostBlock.defaultBlockState());
                    }
                }
            }
        }
        return placedAny;
    }

    private static void mapIceHost(Map<Block, Block> map, String hostId, DecorativeSpeleothemVariant variant) {
        Block host = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(hostId));
        RegistryObject<Block> spRo = WosBlocks.DECORATIVE_SPELEOTHEM.get(variant);
        if (host != null && spRo != null && spRo.isPresent()) {
            map.put(host, spRo.get());
        }
    }

    private static Map<Block, Block> buildStoneToSpeleothemMap() {
        Map<Block, Block> map = new HashMap<>();
        for (IgneousVariant v : IgneousVariant.VALUES) {
            if (WosConfig.isSpeleothemEnabled(v)) {
                RegistryObject<Block> stoneRo = WosBlocks.IGNEOUS_STONE.get(v);
                RegistryObject<Block> spRo = WosBlocks.IGNEOUS_SPELEOTHEM.get(v);
                if (stoneRo != null && stoneRo.isPresent() && spRo != null && spRo.isPresent()) {
                    map.put(stoneRo.get(), spRo.get());
                }
            }
            if (WosConfig.isSandstoneSpeleothemEnabled(v)) {
                RegistryObject<Block> sandRo = WosBlocks.IGNEOUS_SANDSTONE.get(v);
                RegistryObject<Block> sandSpRo = WosBlocks.IGNEOUS_SANDSTONE_SPELEOTHEM.get(v);
                if (sandRo != null && sandRo.isPresent() && sandSpRo != null && sandSpRo.isPresent()) {
                    map.put(sandRo.get(), sandSpRo.get());
                }
            }
        }
        for (MetamorphicVariant v : MetamorphicVariant.VALUES) {
            if (WosConfig.isSpeleothemEnabled(v)) {
                RegistryObject<Block> stoneRo = WosBlocks.METAMORPHIC_STONE.get(v);
                RegistryObject<Block> spRo = WosBlocks.METAMORPHIC_SPELEOTHEM.get(v);
                if (stoneRo != null && stoneRo.isPresent() && spRo != null && spRo.isPresent()) {
                    map.put(stoneRo.get(), spRo.get());
                }
            }
            if (WosConfig.isSandstoneSpeleothemEnabled(v)) {
                RegistryObject<Block> sandRo = WosBlocks.METAMORPHIC_SANDSTONE.get(v);
                RegistryObject<Block> sandSpRo = WosBlocks.METAMORPHIC_SANDSTONE_SPELEOTHEM.get(v);
                if (sandRo != null && sandRo.isPresent() && sandSpRo != null && sandSpRo.isPresent()) {
                    map.put(sandRo.get(), sandSpRo.get());
                }
            }
        }
        for (SedimentaryVariant v : SedimentaryVariant.VALUES) {
            if (WosConfig.isSpeleothemEnabled(v)) {
                RegistryObject<Block> stoneRo = WosBlocks.SEDIMENTARY_STONE.get(v);
                RegistryObject<Block> spRo = WosBlocks.SEDIMENTARY_SPELEOTHEM.get(v);
                if (stoneRo != null && stoneRo.isPresent() && spRo != null && spRo.isPresent()) {
                    map.put(stoneRo.get(), spRo.get());
                }
            }
            if (WosConfig.isSandstoneSpeleothemEnabled(v)) {
                RegistryObject<Block> sandRo = WosBlocks.SEDIMENTARY_SANDSTONE.get(v);
                RegistryObject<Block> sandSpRo = WosBlocks.SEDIMENTARY_SANDSTONE_SPELEOTHEM.get(v);
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
            Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
            RegistryObject<Block> spRo = WosBlocks.VANILLA_SPELEOTHEM.get(e.getValue());
            if (stone != null && spRo != null && spRo.isPresent()) {
                map.put(stone, spRo.get());
            }
        }

        if (WosConfig.terracottaSpeleothemsEnabled) {
            for (TerracottaSpeleothemVariant v : TerracottaSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(v.getHostBlock()));
                RegistryObject<Block> spRo = WosBlocks.TERRACOTTA_SPELEOTHEM.get(v);
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

        if (ModList.get().isLoaded("quark")) {
            Map<String, QuarkSpeleothemVariant> quarkMap = new HashMap<>();
            quarkMap.put("quark:limestone", QuarkSpeleothemVariant.QUARK_LIMESTONE);
            quarkMap.put("quark:shale", QuarkSpeleothemVariant.QUARK_SHALE);
            quarkMap.put("quark:myalite", QuarkSpeleothemVariant.QUARK_MYALITE);
            quarkMap.put("quark:permafrost", QuarkSpeleothemVariant.QUARK_PERMAFROST);
            quarkMap.put("quark:jasper", QuarkSpeleothemVariant.QUARK_JASPER);
            quarkMap.put("quark:dusky_myalite", QuarkSpeleothemVariant.QUARK_DUSKY_MYALITE);
            for (Map.Entry<String, QuarkSpeleothemVariant> e : quarkMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.QUARK_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) {
                    map.put(stone, spRo.get());
                }
            }
        }

        if (ModList.get().isLoaded("undergarden")) {
            Map<String, sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant> ugMap = new HashMap<>();
            ugMap.put("undergarden:depthrock", sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.UNDERGARDEN_DEPTHROCK);
            ugMap.put("undergarden:shiverstone", sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant.UNDERGARDEN_SHIVERSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.UndergardenSpeleothemVariant> e : ugMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.UNDERGARDEN_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("create")) {
            Map<String, sxilverr.worldofstone.api.enums.CreateSpeleothemVariant> cMap = new HashMap<>();
            cMap.put("create:limestone", sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_LIMESTONE);
            cMap.put("create:scoria", sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_SCORIA);
            cMap.put("create:scorchia", sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_SCORCHIA);
            cMap.put("create:asurine", sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_ASURINE);
            cMap.put("create:ochrum", sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_OCHRUM);
            cMap.put("create:veridium", sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_VERIDIUM);
            cMap.put("create:crimsite", sxilverr.worldofstone.api.enums.CreateSpeleothemVariant.CREATE_CRIMSITE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.CreateSpeleothemVariant> e : cMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.CREATE_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("betterend")) {
            Map<String, sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant> beMap = new HashMap<>();
            beMap.put("betterend:flavolite", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_FLAVOLITE);
            beMap.put("betterend:violecite", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_VIOLECITE);
            beMap.put("betterend:virid_jadestone", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_VIRID_JADESTONE);
            beMap.put("betterend:azure_jadestone", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_AZURE_JADESTONE);
            beMap.put("betterend:sandy_jadestone", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_SANDY_JADESTONE);
            beMap.put("betterend:sulphuric_rock", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_SULPHURIC_ROCK);
            beMap.put("betterend:umbralith", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_UMBRALITH);
            beMap.put("betterend:brimstone", sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant.BETTEREND_BRIMSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.BetterEndSpeleothemVariant> e : beMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.BETTEREND_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("galosphere")) {
            Map<String, sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant> gMap = new HashMap<>();
            gMap.put("galosphere:allurite_block", sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.GALOSPHERE_ALLURITE);
            gMap.put("galosphere:lumiere_block", sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant.GALOSPHERE_LUMIERE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.GalosphereSpeleothemVariant> e : gMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.GALOSPHERE_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("biomeswevegone")) {
            Map<String, sxilverr.worldofstone.api.enums.BwgSpeleothemVariant> bwgMap = new HashMap<>();
            bwgMap.put("biomeswevegone:dacite", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_DACITE);
            bwgMap.put("biomeswevegone:white_dacite", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_WHITE_DACITE);
            bwgMap.put("biomeswevegone:red_rock", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_RED_ROCK);
            bwgMap.put("biomeswevegone:black_sandstone", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_BLACK_SANDSTONE);
            bwgMap.put("biomeswevegone:white_sandstone", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_WHITE_SANDSTONE);
            bwgMap.put("biomeswevegone:blue_sandstone", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_BLUE_SANDSTONE);
            bwgMap.put("biomeswevegone:purple_sandstone", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_PURPLE_SANDSTONE);
            bwgMap.put("biomeswevegone:pink_sandstone", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_PINK_SANDSTONE);
            bwgMap.put("biomeswevegone:windswept_sandstone", sxilverr.worldofstone.api.enums.BwgSpeleothemVariant.BWG_WINDSWEPT_SANDSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.BwgSpeleothemVariant> e : bwgMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.BWG_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("twilightforest")) {
            Map<String, sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant> tfMap = new HashMap<>();
            tfMap.put("twilightforest:mazestone", sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.TWILIGHTFOREST_MAZESTONE);
            tfMap.put("twilightforest:deadrock", sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.TWILIGHTFOREST_DEADROCK);
            tfMap.put("twilightforest:trollsteinn", sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant.TWILIGHTFOREST_TROLLSTEINN);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.TwilightForestSpeleothemVariant> e : tfMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.TWILIGHTFOREST_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("aether")) {
            Map<String, sxilverr.worldofstone.api.enums.AetherSpeleothemVariant> aMap = new HashMap<>();
            aMap.put("aether:holystone", sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.AETHER_HOLYSTONE);
            aMap.put("aether:aerogel", sxilverr.worldofstone.api.enums.AetherSpeleothemVariant.AETHER_AEROGEL);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.AetherSpeleothemVariant> e : aMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.AETHER_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("blue_skies")) {
            Map<String, sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant> bsMap = new HashMap<>();
            bsMap.put("blue_skies:lunar_stone", sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_LUNAR_STONE);
            bsMap.put("blue_skies:turquoise_stone", sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_TURQUOISE_STONE);
            bsMap.put("blue_skies:midnight_sandstone", sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_MIDNIGHT_SANDSTONE);
            bsMap.put("blue_skies:crystal_sandstone", sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant.BLUE_SKIES_CRYSTAL_SANDSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.BlueSkiesSpeleothemVariant> e : bsMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.BLUE_SKIES_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("spelunkery")) {
            Map<String, sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant> spMap = new HashMap<>();
            spMap.put("spelunkery:rock_salt_block", sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.SPELUNKERY_ROCK_SALT_BLOCK);
            spMap.put("spelunkery:nephrite", sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant.SPELUNKERY_NEPHRITE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.SpelunkerySpeleothemVariant> e : spMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.SPELUNKERY_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("iceandfire")) {
            for (sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant v : sxilverr.worldofstone.api.enums.IceAndFireSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("iceandfire:dread_stone"));
                RegistryObject<Block> spRo = WosBlocks.ICEANDFIRE_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("mysticalagriculture")) {
            for (sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant v : sxilverr.worldofstone.api.enums.MysticalAgricultureSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("mysticalagriculture:soulstone"));
                RegistryObject<Block> spRo = WosBlocks.MYSTICALAGRICULTURE_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("biomesoplenty")) {
            Map<String, sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant> bopMap = new HashMap<>();
            bopMap.put("biomesoplenty:white_sandstone", sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_WHITE_SANDSTONE);
            bopMap.put("biomesoplenty:orange_sandstone", sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_ORANGE_SANDSTONE);
            bopMap.put("biomesoplenty:black_sandstone", sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_BLACK_SANDSTONE);
            bopMap.put("biomesoplenty:brimstone", sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant.BIOMESOPLENTY_BRIMSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.BiomesOPlentySpeleothemVariant> e : bopMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.BIOMESOPLENTY_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("forbidden_arcanus")) {
            Map<String, sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant> faMap = new HashMap<>();
            faMap.put("forbidden_arcanus:darkstone", sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.FORBIDDEN_ARCANUS_DARKSTONE);
            faMap.put("forbidden_arcanus:soulless_sandstone", sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant.FORBIDDEN_ARCANUS_SOULLESS_SANDSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.ForbiddenArcanusSpeleothemVariant> e : faMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.FORBIDDEN_ARCANUS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("alexscaves")) {
            Map<String, sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant> acMap = new HashMap<>();
            acMap.put("alexscaves:galena", sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_GALENA);
            acMap.put("alexscaves:limestone", sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_LIMESTONE);
            acMap.put("alexscaves:radrock", sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_RADROCK);
            acMap.put("alexscaves:abyssmarine", sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_ABYSSMARINE);
            acMap.put("alexscaves:guanostone", sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_GUANOSTONE);
            acMap.put("alexscaves:coprolith", sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_COPROLITH);
            acMap.put("alexscaves:gingerbread_block", sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant.ALEXSCAVES_GINGERBREAD_BLOCK);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.AlexsCavesSpeleothemVariant> e : acMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.ALEXSCAVES_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("ars_nouveau")) {
            for (sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant v : sxilverr.worldofstone.api.enums.ArsNouveauSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ars_nouveau:sourcestone"));
                RegistryObject<Block> spRo = WosBlocks.ARS_NOUVEAU_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("cataclysm")) {
            for (sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant v : sxilverr.worldofstone.api.enums.CataclysmSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("cataclysm:azure_seastone"));
                RegistryObject<Block> spRo = WosBlocks.CATACLYSM_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("twigs")) {
            Map<String, sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant> twMap = new HashMap<>();
            twMap.put("twigs:schist", sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.TWIGS_SCHIST);
            twMap.put("twigs:rhyolite", sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.TWIGS_RHYOLITE);
            twMap.put("twigs:bloodstone", sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant.TWIGS_BLOODSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.TwigsSpeleothemVariant> e : twMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.TWIGS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("architects_palette")) {
            Map<String, sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant> apMap = new HashMap<>();
            apMap.put("architects_palette:abyssaline", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_ABYSSALINE);
            apMap.put("architects_palette:myonite", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_MYONITE);
            apMap.put("architects_palette:hadaline", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_HADALINE);
            apMap.put("architects_palette:esoterrack", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_ESOTERRACK);
            apMap.put("architects_palette:onyx", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_ONYX);
            apMap.put("architects_palette:wardstone", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_WARDSTONE);
            apMap.put("architects_palette:moonshale", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_MOONSHALE);
            apMap.put("architects_palette:nebulite", sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant.ARCHITECTS_PALETTE_NEBULITE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.ArchitectsPaletteSpeleothemVariant> e : apMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.ARCHITECTS_PALETTE_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("outer_end")) {
            Map<String, sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant> oeMap = new HashMap<>();
            oeMap.put("outer_end:violite", sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_VIOLITE);
            oeMap.put("outer_end:stromatolite", sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_STROMATOLITE);
            oeMap.put("outer_end:halite", sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_HALITE);
            oeMap.put("outer_end:ancient_stone", sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant.OUTER_END_ANCIENT_STONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.OuterEndSpeleothemVariant> e : oeMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.OUTER_END_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("botania")) {
            Map<String, sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant> boMap = new HashMap<>();
            boMap.put("botania:livingrock", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_LIVINGROCK);
            boMap.put("botania:shimmerrock", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_SHIMMERROCK);
            boMap.put("botania:metamorphic_forest_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_FOREST_STONE);
            boMap.put("botania:metamorphic_plains_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_PLAINS_STONE);
            boMap.put("botania:metamorphic_mountain_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_MOUNTAIN_STONE);
            boMap.put("botania:metamorphic_fungal_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_FUNGAL_STONE);
            boMap.put("botania:metamorphic_swamp_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_SWAMP_STONE);
            boMap.put("botania:metamorphic_desert_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_DESERT_STONE);
            boMap.put("botania:metamorphic_taiga_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_TAIGA_STONE);
            boMap.put("botania:metamorphic_mesa_stone", sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant.BOTANIA_METAMORPHIC_MESA_STONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.BotaniaSpeleothemVariant> e : boMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.BOTANIA_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("ad_astra")) {
            Map<String, sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant> aaMap = new HashMap<>();
            aaMap.put("ad_astra:sky_stone", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_SKY_STONE);
            aaMap.put("ad_astra:moon_stone", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MOON_STONE);
            aaMap.put("ad_astra:moon_deepslate", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MOON_DEEPSLATE);
            aaMap.put("ad_astra:mars_stone", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MARS_STONE);
            aaMap.put("ad_astra:venus_stone", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_VENUS_STONE);
            aaMap.put("ad_astra:venus_sandstone", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_VENUS_SANDSTONE);
            aaMap.put("ad_astra:mercury_stone", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_MERCURY_STONE);
            aaMap.put("ad_astra:glacio_stone", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_GLACIO_STONE);
            aaMap.put("ad_astra:permafrost", sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant.AD_ASTRA_PERMAFROST);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.AdAstraSpeleothemVariant> e : aaMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.AD_ASTRA_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("deep_aether")) {
            Map<String, sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant> daMap = new HashMap<>();
            daMap.put("deep_aether:aseterite", sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.DEEP_AETHER_ASETERITE);
            daMap.put("deep_aether:raw_clorite", sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant.DEEP_AETHER_RAW_CLORITE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.DeepAetherSpeleothemVariant> e : daMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.DEEP_AETHER_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("caverns_and_chasms")) {
            Map<String, sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant> ccMap = new HashMap<>();
            ccMap.put("caverns_and_chasms:sugilite", sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.CAVERNS_AND_CHASMS_SUGILITE);
            ccMap.put("caverns_and_chasms:cylindrite", sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.CAVERNS_AND_CHASMS_CYLINDRITE);
            ccMap.put("caverns_and_chasms:rhyolite", sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant.CAVERNS_AND_CHASMS_RHYOLITE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.CavernsAndChasmsSpeleothemVariant> e : ccMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.CAVERNS_AND_CHASMS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("atmospheric")) {
            Map<String, sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant> atMap = new HashMap<>();
            atMap.put("atmospheric:ivory_travertine", sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_IVORY_TRAVERTINE);
            atMap.put("atmospheric:peach_travertine", sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_PEACH_TRAVERTINE);
            atMap.put("atmospheric:persimmon_travertine", sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_PERSIMMON_TRAVERTINE);
            atMap.put("atmospheric:saffron_travertine", sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_SAFFRON_TRAVERTINE);
            atMap.put("atmospheric:dolerite", sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_DOLERITE);
            atMap.put("atmospheric:arid_sandstone", sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_ARID_SANDSTONE);
            atMap.put("atmospheric:red_arid_sandstone", sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant.ATMOSPHERIC_RED_ARID_SANDSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.AtmosphericSpeleothemVariant> e : atMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.ATMOSPHERIC_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("endergetic")) {
            for (sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant v : sxilverr.worldofstone.api.enums.EndergeticSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("endergetic:eumus"));
                RegistryObject<Block> spRo = WosBlocks.ENDERGETIC_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("wilder_wilds")) {
            Map<String, sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant> wwMap = new HashMap<>();
            wwMap.put("wilder_wilds:lavenderhardenedclay", sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.WILDER_WILDS_LAVENDERHARDENEDCLAY);
            wwMap.put("wilder_wilds:coral_hardened_clay", sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.WILDER_WILDS_CORAL_HARDENED_CLAY);
            wwMap.put("wilder_wilds:cream_hardened_clay", sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant.WILDER_WILDS_CREAM_HARDENED_CLAY);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.WilderWildsSpeleothemVariant> e : wwMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.WILDER_WILDS_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("regions_unexplored")) {
            Map<String, sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant> ruMap = new HashMap<>();
            ruMap.put("regions_unexplored:chalk", sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.REGIONS_UNEXPLORED_CHALK);
            ruMap.put("regions_unexplored:argillite", sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.REGIONS_UNEXPLORED_ARGILLITE);
            ruMap.put("regions_unexplored:mossy_stone", sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant.REGIONS_UNEXPLORED_MOSSY_STONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.RegionsUnexploredSpeleothemVariant> e : ruMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.REGIONS_UNEXPLORED_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("born_in_chaos_v1")) {
            for (sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant v : sxilverr.worldofstone.api.enums.BornInChaosSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("born_in_chaos_v1:black_argillite"));
                RegistryObject<Block> spRo = WosBlocks.BORN_IN_CHAOS_V1_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("naturalist")) {
            for (sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant v : sxilverr.worldofstone.api.enums.NaturalistSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("naturalist:shellstone"));
                RegistryObject<Block> spRo = WosBlocks.NATURALIST_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("yungscavebiomes")) {
            for (sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant v : sxilverr.worldofstone.api.enums.YungsCaveBiomesSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("yungscavebiomes:ancient_sandstone"));
                RegistryObject<Block> spRo = WosBlocks.YUNGSCAVEBIOMES_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("natures_spirit")) {
            Map<String, sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant> nsMap = new HashMap<>();
            nsMap.put("natures_spirit:travertine", sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.NATURES_SPIRIT_TRAVERTINE);
            nsMap.put("natures_spirit:chert", sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.NATURES_SPIRIT_CHERT);
            nsMap.put("natures_spirit:pink_sandstone", sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant.NATURES_SPIRIT_PINK_SANDSTONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.NaturesSpiritSpeleothemVariant> e : nsMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.NATURES_SPIRIT_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("netherexp")) {
            Map<String, sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant> neMap = new HashMap<>();
            neMap.put("netherexp:soul_slate", sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.NETHEREXP_SOUL_SLATE);
            neMap.put("netherexp:pale_soul_slate", sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.NETHEREXP_PALE_SOUL_SLATE);
            neMap.put("netherexp:black_ice", sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant.NETHEREXP_BLACK_ICE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.NetherExpSpeleothemVariant> e : neMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.NETHEREXP_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("deeperdarker")) {
            Map<String, sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant> ddMap = new HashMap<>();
            ddMap.put("deeperdarker:sculk_stone", sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.DEEPERDARKER_SCULK_STONE);
            ddMap.put("deeperdarker:gloomslate", sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant.DEEPERDARKER_GLOOMSLATE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.DeeperDarkerSpeleothemVariant> e : ddMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.DEEPERDARKER_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("the_deep_void")) {
            Map<String, sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant> tdvMap = new HashMap<>();
            tdvMap.put("the_deep_void:ancient_deepslate", sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_ANCIENT_DEEPSLATE);
            tdvMap.put("the_deep_void:primordial_stone", sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_PRIMORDIAL_STONE);
            tdvMap.put("the_deep_void:solid_void_block", sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_SOLID_VOID_BLOCK);
            tdvMap.put("the_deep_void:monolithic_stone", sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant.THE_DEEP_VOID_MONOLITHIC_STONE);
            for (Map.Entry<String, sxilverr.worldofstone.api.enums.TheDeepVoidSpeleothemVariant> e : tdvMap.entrySet()) {
                if (!WosConfig.isSpeleothemEnabled(e.getValue())) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(e.getKey()));
                RegistryObject<Block> spRo = WosBlocks.THE_DEEP_VOID_SPELEOTHEM.get(e.getValue());
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        if (ModList.get().isLoaded("defiled_lands_preborn")) {
            for (sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant v : sxilverr.worldofstone.api.enums.DefiledLandsSpeleothemVariant.VALUES) {
                if (!WosConfig.isSpeleothemEnabled(v)) continue;
                Block stone = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("defiled_lands_preborn:defiled_stone"));
                RegistryObject<Block> spRo = WosBlocks.DEFILED_LANDS_PREBORN_SPELEOTHEM.get(v);
                if (stone != null && spRo != null && spRo.isPresent()) map.put(stone, spRo.get());
            }
        }

        for (String blockId : WosConfig.mimicSpeleothemWorldgenBlocks) {
            ResourceLocation rl = ResourceLocation.tryParse(blockId);
            if (rl == null) continue;
            Block source = ForgeRegistries.BLOCKS.getValue(rl);
            if (source != null && source != net.minecraft.world.level.block.Blocks.AIR) {
                map.put(source, WosBlocks.MIMIC_SPELEOTHEM.get());
            }
        }

        return map;
    }
}
