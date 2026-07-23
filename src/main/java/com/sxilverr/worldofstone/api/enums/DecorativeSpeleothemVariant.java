package com.sxilverr.worldofstone.api.enums;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public enum DecorativeSpeleothemVariant {
    ICE("ice", "Ice", SoundType.GLASS, MapColor.ICE, 0.5F, 0.5F, 0),
    PACKED_ICE("packed_ice", "Packed Ice", SoundType.GLASS, MapColor.ICE, 0.5F, 0.5F, 0),
    BLUE_ICE("blue_ice", "Blue Ice", SoundType.GLASS, MapColor.ICE, 2.8F, 2.8F, 0),
    OBSIDIAN("obsidian", "Obsidian", SoundType.STONE, MapColor.COLOR_BLACK, 50.0F, 1200.0F, 0),
    CRYING_OBSIDIAN("crying_obsidian", "Crying Obsidian", SoundType.STONE, MapColor.COLOR_BLACK, 50.0F, 1200.0F, 10),
    BONE("bone", "Bone", SoundType.BONE_BLOCK, MapColor.SAND, 2.0F, 2.0F, 0),
    RAW_IRON("raw_iron", "Raw Iron", SoundType.STONE, MapColor.RAW_IRON, 5.0F, 6.0F, 0),
    RAW_COPPER("raw_copper", "Raw Copper", SoundType.STONE, MapColor.COLOR_ORANGE, 5.0F, 6.0F, 0),
    RAW_GOLD("raw_gold", "Raw Gold", SoundType.STONE, MapColor.GOLD, 5.0F, 6.0F, 0),
    GLOWSTONE("glowstone", "Glowstone", SoundType.GLASS, MapColor.SAND, 0.3F, 0.3F, 15),
    MAGMA("magma", "Magma", SoundType.STONE, MapColor.COLOR_RED, 0.5F, 0.5F, 3),
    AMETHYST("amethyst", "Amethyst", SoundType.AMETHYST, MapColor.COLOR_PURPLE, 1.5F, 1.5F, 0),
    PURPUR("purpur", "Purpur", SoundType.STONE, MapColor.COLOR_MAGENTA, 1.5F, 6.0F, 0),
    PRISMARINE("prismarine", "Prismarine", SoundType.STONE, MapColor.DIAMOND, 1.5F, 6.0F, 0),
    PRISMARINE_BRICKS("prismarine_bricks", "Prismarine Bricks", SoundType.STONE, MapColor.DIAMOND, 1.5F, 6.0F, 0),
    DARK_PRISMARINE("dark_prismarine", "Dark Prismarine", SoundType.STONE, MapColor.DIAMOND, 1.5F, 6.0F, 0),
    QUARTZ("quartz", "Quartz", SoundType.STONE, MapColor.QUARTZ, 0.8F, 0.8F, 0),
    COAL("coal", "Coal", SoundType.STONE, MapColor.COLOR_BLACK, 5.0F, 6.0F, 0),
    COPPER("copper", "Copper", SoundType.COPPER, MapColor.COLOR_ORANGE, 3.0F, 6.0F, 0),
    EXPOSED_COPPER("exposed_copper", "Exposed Copper", SoundType.COPPER, MapColor.TERRACOTTA_LIGHT_GRAY, 3.0F, 6.0F, 0),
    WEATHERED_COPPER("weathered_copper", "Weathered Copper", SoundType.COPPER, MapColor.WARPED_STEM, 3.0F, 6.0F, 0),
    OXIDIZED_COPPER("oxidized_copper", "Oxidized Copper", SoundType.COPPER, MapColor.WARPED_NYLIUM, 3.0F, 6.0F, 0),
    IRON("iron", "Iron", SoundType.METAL, MapColor.METAL, 5.0F, 6.0F, 0),
    GOLD("gold", "Gold", SoundType.METAL, MapColor.GOLD, 3.0F, 6.0F, 0),
    REDSTONE("redstone", "Redstone", SoundType.METAL, MapColor.FIRE, 5.0F, 6.0F, 0),
    EMERALD("emerald", "Emerald", SoundType.METAL, MapColor.EMERALD, 5.0F, 6.0F, 0),
    LAPIS("lapis", "Lapis Lazuli", SoundType.STONE, MapColor.LAPIS, 3.0F, 3.0F, 0),
    DIAMOND("diamond", "Diamond", SoundType.METAL, MapColor.DIAMOND, 5.0F, 6.0F, 0),
    NETHERITE("netherite", "Netherite", SoundType.NETHERITE_BLOCK, MapColor.COLOR_BLACK, 50.0F, 1200.0F, 0),
    DIRT("dirt", "Dirt", SoundType.GRAVEL, MapColor.DIRT, 0.5F, 0.5F, 0),
    SNOW("snow", "Snow", SoundType.SNOW, MapColor.SNOW, 0.2F, 0.2F, 0),
    MUD("mud", "Mud", SoundType.MUD, MapColor.TERRACOTTA_CYAN, 0.5F, 0.5F, 0),
    PACKED_MUD("packed_mud", "Packed Mud", SoundType.PACKED_MUD, MapColor.DIRT, 1.0F, 3.0F, 0),
    GLASS("glass", "Glass", SoundType.GLASS, MapColor.NONE, 0.3F, 0.3F, 0),
    TINTED_GLASS("tinted_glass", "Tinted Glass", SoundType.GLASS, MapColor.COLOR_GRAY, 0.3F, 0.3F, 0),
    WHITE_STAINED_GLASS("white_stained_glass", "White Stained Glass", SoundType.GLASS, MapColor.SNOW, 0.3F, 0.3F, 0),
    ORANGE_STAINED_GLASS("orange_stained_glass", "Orange Stained Glass", SoundType.GLASS, MapColor.COLOR_ORANGE, 0.3F, 0.3F, 0),
    MAGENTA_STAINED_GLASS("magenta_stained_glass", "Magenta Stained Glass", SoundType.GLASS, MapColor.COLOR_MAGENTA, 0.3F, 0.3F, 0),
    LIGHT_BLUE_STAINED_GLASS("light_blue_stained_glass", "Light Blue Stained Glass", SoundType.GLASS, MapColor.COLOR_LIGHT_BLUE, 0.3F, 0.3F, 0),
    YELLOW_STAINED_GLASS("yellow_stained_glass", "Yellow Stained Glass", SoundType.GLASS, MapColor.COLOR_YELLOW, 0.3F, 0.3F, 0),
    LIME_STAINED_GLASS("lime_stained_glass", "Lime Stained Glass", SoundType.GLASS, MapColor.COLOR_LIGHT_GREEN, 0.3F, 0.3F, 0),
    PINK_STAINED_GLASS("pink_stained_glass", "Pink Stained Glass", SoundType.GLASS, MapColor.COLOR_PINK, 0.3F, 0.3F, 0),
    GRAY_STAINED_GLASS("gray_stained_glass", "Gray Stained Glass", SoundType.GLASS, MapColor.COLOR_GRAY, 0.3F, 0.3F, 0),
    LIGHT_GRAY_STAINED_GLASS("light_gray_stained_glass", "Light Gray Stained Glass", SoundType.GLASS, MapColor.COLOR_LIGHT_GRAY, 0.3F, 0.3F, 0),
    CYAN_STAINED_GLASS("cyan_stained_glass", "Cyan Stained Glass", SoundType.GLASS, MapColor.COLOR_CYAN, 0.3F, 0.3F, 0),
    PURPLE_STAINED_GLASS("purple_stained_glass", "Purple Stained Glass", SoundType.GLASS, MapColor.COLOR_PURPLE, 0.3F, 0.3F, 0),
    BLUE_STAINED_GLASS("blue_stained_glass", "Blue Stained Glass", SoundType.GLASS, MapColor.COLOR_BLUE, 0.3F, 0.3F, 0),
    BROWN_STAINED_GLASS("brown_stained_glass", "Brown Stained Glass", SoundType.GLASS, MapColor.COLOR_BROWN, 0.3F, 0.3F, 0),
    GREEN_STAINED_GLASS("green_stained_glass", "Green Stained Glass", SoundType.GLASS, MapColor.COLOR_GREEN, 0.3F, 0.3F, 0),
    RED_STAINED_GLASS("red_stained_glass", "Red Stained Glass", SoundType.GLASS, MapColor.COLOR_RED, 0.3F, 0.3F, 0),
    BLACK_STAINED_GLASS("black_stained_glass", "Black Stained Glass", SoundType.GLASS, MapColor.COLOR_BLACK, 0.3F, 0.3F, 0),
    TUBE_CORAL("tube_coral", "Tube Coral", SoundType.CORAL_BLOCK, MapColor.COLOR_BLUE, 1.5F, 6.0F, 0),
    BRAIN_CORAL("brain_coral", "Brain Coral", SoundType.CORAL_BLOCK, MapColor.COLOR_PINK, 1.5F, 6.0F, 0),
    BUBBLE_CORAL("bubble_coral", "Bubble Coral", SoundType.CORAL_BLOCK, MapColor.COLOR_PURPLE, 1.5F, 6.0F, 0),
    FIRE_CORAL("fire_coral", "Fire Coral", SoundType.CORAL_BLOCK, MapColor.COLOR_RED, 1.5F, 6.0F, 0),
    HORN_CORAL("horn_coral", "Horn Coral", SoundType.CORAL_BLOCK, MapColor.COLOR_YELLOW, 1.5F, 6.0F, 0),
    SPONGE("sponge", "Sponge", SoundType.GRASS, MapColor.COLOR_YELLOW, 0.6F, 0.6F, 0),
    SLIME("slime", "Slime", SoundType.SLIME_BLOCK, MapColor.GRASS, 0.0F, 0.0F, 0),
    HONEY("honey", "Honey", SoundType.HONEY_BLOCK, MapColor.COLOR_ORANGE, 0.0F, 0.0F, 0),
    MOSS("moss", "Moss", SoundType.MOSS, MapColor.COLOR_GREEN, 0.1F, 0.1F, 0),
    SEA_LANTERN("sea_lantern", "Sea Lantern", SoundType.GLASS, MapColor.QUARTZ, 0.3F, 0.3F, 15),
    WHITE_WOOL("white_wool", "White Wool", SoundType.WOOL, MapColor.SNOW, 0.8F, 0.8F, 0),
    ORANGE_WOOL("orange_wool", "Orange Wool", SoundType.WOOL, MapColor.COLOR_ORANGE, 0.8F, 0.8F, 0),
    MAGENTA_WOOL("magenta_wool", "Magenta Wool", SoundType.WOOL, MapColor.COLOR_MAGENTA, 0.8F, 0.8F, 0),
    LIGHT_BLUE_WOOL("light_blue_wool", "Light Blue Wool", SoundType.WOOL, MapColor.COLOR_LIGHT_BLUE, 0.8F, 0.8F, 0),
    YELLOW_WOOL("yellow_wool", "Yellow Wool", SoundType.WOOL, MapColor.COLOR_YELLOW, 0.8F, 0.8F, 0),
    LIME_WOOL("lime_wool", "Lime Wool", SoundType.WOOL, MapColor.COLOR_LIGHT_GREEN, 0.8F, 0.8F, 0),
    PINK_WOOL("pink_wool", "Pink Wool", SoundType.WOOL, MapColor.COLOR_PINK, 0.8F, 0.8F, 0),
    GRAY_WOOL("gray_wool", "Gray Wool", SoundType.WOOL, MapColor.COLOR_GRAY, 0.8F, 0.8F, 0),
    LIGHT_GRAY_WOOL("light_gray_wool", "Light Gray Wool", SoundType.WOOL, MapColor.COLOR_LIGHT_GRAY, 0.8F, 0.8F, 0),
    CYAN_WOOL("cyan_wool", "Cyan Wool", SoundType.WOOL, MapColor.COLOR_CYAN, 0.8F, 0.8F, 0),
    PURPLE_WOOL("purple_wool", "Purple Wool", SoundType.WOOL, MapColor.COLOR_PURPLE, 0.8F, 0.8F, 0),
    BLUE_WOOL("blue_wool", "Blue Wool", SoundType.WOOL, MapColor.COLOR_BLUE, 0.8F, 0.8F, 0),
    BROWN_WOOL("brown_wool", "Brown Wool", SoundType.WOOL, MapColor.COLOR_BROWN, 0.8F, 0.8F, 0),
    GREEN_WOOL("green_wool", "Green Wool", SoundType.WOOL, MapColor.COLOR_GREEN, 0.8F, 0.8F, 0),
    RED_WOOL("red_wool", "Red Wool", SoundType.WOOL, MapColor.COLOR_RED, 0.8F, 0.8F, 0),
    BLACK_WOOL("black_wool", "Black Wool", SoundType.WOOL, MapColor.COLOR_BLACK, 0.8F, 0.8F, 0),
    WHITE_CONCRETE("white_concrete", "White Concrete", SoundType.STONE, MapColor.SNOW, 1.8F, 1.8F, 0),
    ORANGE_CONCRETE("orange_concrete", "Orange Concrete", SoundType.STONE, MapColor.COLOR_ORANGE, 1.8F, 1.8F, 0),
    MAGENTA_CONCRETE("magenta_concrete", "Magenta Concrete", SoundType.STONE, MapColor.COLOR_MAGENTA, 1.8F, 1.8F, 0),
    LIGHT_BLUE_CONCRETE("light_blue_concrete", "Light Blue Concrete", SoundType.STONE, MapColor.COLOR_LIGHT_BLUE, 1.8F, 1.8F, 0),
    YELLOW_CONCRETE("yellow_concrete", "Yellow Concrete", SoundType.STONE, MapColor.COLOR_YELLOW, 1.8F, 1.8F, 0),
    LIME_CONCRETE("lime_concrete", "Lime Concrete", SoundType.STONE, MapColor.COLOR_LIGHT_GREEN, 1.8F, 1.8F, 0),
    PINK_CONCRETE("pink_concrete", "Pink Concrete", SoundType.STONE, MapColor.COLOR_PINK, 1.8F, 1.8F, 0),
    GRAY_CONCRETE("gray_concrete", "Gray Concrete", SoundType.STONE, MapColor.COLOR_GRAY, 1.8F, 1.8F, 0),
    LIGHT_GRAY_CONCRETE("light_gray_concrete", "Light Gray Concrete", SoundType.STONE, MapColor.COLOR_LIGHT_GRAY, 1.8F, 1.8F, 0),
    CYAN_CONCRETE("cyan_concrete", "Cyan Concrete", SoundType.STONE, MapColor.COLOR_CYAN, 1.8F, 1.8F, 0),
    PURPLE_CONCRETE("purple_concrete", "Purple Concrete", SoundType.STONE, MapColor.COLOR_PURPLE, 1.8F, 1.8F, 0),
    BLUE_CONCRETE("blue_concrete", "Blue Concrete", SoundType.STONE, MapColor.COLOR_BLUE, 1.8F, 1.8F, 0),
    BROWN_CONCRETE("brown_concrete", "Brown Concrete", SoundType.STONE, MapColor.COLOR_BROWN, 1.8F, 1.8F, 0),
    GREEN_CONCRETE("green_concrete", "Green Concrete", SoundType.STONE, MapColor.COLOR_GREEN, 1.8F, 1.8F, 0),
    RED_CONCRETE("red_concrete", "Red Concrete", SoundType.STONE, MapColor.COLOR_RED, 1.8F, 1.8F, 0),
    BLACK_CONCRETE("black_concrete", "Black Concrete", SoundType.STONE, MapColor.COLOR_BLACK, 1.8F, 1.8F, 0),
    WHITE_GLAZED_TERRACOTTA("white_glazed_terracotta", "White Glazed Terracotta", SoundType.STONE, MapColor.SNOW, 1.4F, 1.4F, 0),
    ORANGE_GLAZED_TERRACOTTA("orange_glazed_terracotta", "Orange Glazed Terracotta", SoundType.STONE, MapColor.COLOR_ORANGE, 1.4F, 1.4F, 0),
    MAGENTA_GLAZED_TERRACOTTA("magenta_glazed_terracotta", "Magenta Glazed Terracotta", SoundType.STONE, MapColor.COLOR_MAGENTA, 1.4F, 1.4F, 0),
    LIGHT_BLUE_GLAZED_TERRACOTTA("light_blue_glazed_terracotta", "Light Blue Glazed Terracotta", SoundType.STONE, MapColor.COLOR_LIGHT_BLUE, 1.4F, 1.4F, 0),
    YELLOW_GLAZED_TERRACOTTA("yellow_glazed_terracotta", "Yellow Glazed Terracotta", SoundType.STONE, MapColor.COLOR_YELLOW, 1.4F, 1.4F, 0),
    LIME_GLAZED_TERRACOTTA("lime_glazed_terracotta", "Lime Glazed Terracotta", SoundType.STONE, MapColor.COLOR_LIGHT_GREEN, 1.4F, 1.4F, 0),
    PINK_GLAZED_TERRACOTTA("pink_glazed_terracotta", "Pink Glazed Terracotta", SoundType.STONE, MapColor.COLOR_PINK, 1.4F, 1.4F, 0),
    GRAY_GLAZED_TERRACOTTA("gray_glazed_terracotta", "Gray Glazed Terracotta", SoundType.STONE, MapColor.COLOR_GRAY, 1.4F, 1.4F, 0),
    LIGHT_GRAY_GLAZED_TERRACOTTA("light_gray_glazed_terracotta", "Light Gray Glazed Terracotta", SoundType.STONE, MapColor.COLOR_LIGHT_GRAY, 1.4F, 1.4F, 0),
    CYAN_GLAZED_TERRACOTTA("cyan_glazed_terracotta", "Cyan Glazed Terracotta", SoundType.STONE, MapColor.COLOR_CYAN, 1.4F, 1.4F, 0),
    PURPLE_GLAZED_TERRACOTTA("purple_glazed_terracotta", "Purple Glazed Terracotta", SoundType.STONE, MapColor.COLOR_PURPLE, 1.4F, 1.4F, 0),
    BLUE_GLAZED_TERRACOTTA("blue_glazed_terracotta", "Blue Glazed Terracotta", SoundType.STONE, MapColor.COLOR_BLUE, 1.4F, 1.4F, 0),
    BROWN_GLAZED_TERRACOTTA("brown_glazed_terracotta", "Brown Glazed Terracotta", SoundType.STONE, MapColor.COLOR_BROWN, 1.4F, 1.4F, 0),
    GREEN_GLAZED_TERRACOTTA("green_glazed_terracotta", "Green Glazed Terracotta", SoundType.STONE, MapColor.COLOR_GREEN, 1.4F, 1.4F, 0),
    RED_GLAZED_TERRACOTTA("red_glazed_terracotta", "Red Glazed Terracotta", SoundType.STONE, MapColor.COLOR_RED, 1.4F, 1.4F, 0),
    BLACK_GLAZED_TERRACOTTA("black_glazed_terracotta", "Black Glazed Terracotta", SoundType.STONE, MapColor.COLOR_BLACK, 1.4F, 1.4F, 0),
    HONEYCOMB("honeycomb", "Honeycomb", SoundType.CORAL_BLOCK, MapColor.COLOR_ORANGE, 0.6F, 0.6F, 0),
    BRICKS("bricks", "Bricks", SoundType.STONE, MapColor.COLOR_RED, 2.0F, 6.0F, 0),
    HAY_BALE("hay_bale", "Hay Bale", SoundType.GRASS, MapColor.COLOR_YELLOW, 0.5F, 0.5F, 0),
    SCULK("sculk", "Sculk", SoundType.SCULK, MapColor.COLOR_BLACK, 0.2F, 0.2F, 0),
    SCULK_CATALYST("sculk_catalyst", "Sculk Catalyst", SoundType.SCULK_CATALYST, MapColor.COLOR_BLACK, 3.0F, 3.0F, 6),
    CLAY("clay", "Clay", SoundType.GRAVEL, MapColor.CLAY, 0.6F, 0.6F, 0),
    PODZOL("podzol", "Podzol", SoundType.GRAVEL, MapColor.PODZOL, 0.5F, 0.5F, 0),
    MYCELIUM("mycelium", "Mycelium", SoundType.GRASS, MapColor.COLOR_PURPLE, 0.6F, 0.6F, 0),
    COARSE_DIRT("coarse_dirt", "Coarse Dirt", SoundType.GRAVEL, MapColor.DIRT, 0.5F, 0.5F, 0),
    ROOTED_DIRT("rooted_dirt", "Rooted Dirt", SoundType.ROOTED_DIRT, MapColor.DIRT, 0.5F, 0.5F, 0),
    SOUL_SAND("soul_sand", "Soul Sand", SoundType.SOUL_SAND, MapColor.COLOR_BROWN, 0.5F, 0.5F, 0),
    SOUL_SOIL("soul_soil", "Soul Soil", SoundType.SOUL_SOIL, MapColor.COLOR_BROWN, 0.5F, 0.5F, 0),
    GRASS("grass", "Grass", SoundType.GRASS, MapColor.GRASS, 0.6F, 0.6F, 0),
    BROWN_MUSHROOM("brown_mushroom", "Brown Mushroom", SoundType.WOOD, MapColor.COLOR_BROWN, 0.2F, 0.2F, 0),
    RED_MUSHROOM("red_mushroom", "Red Mushroom", SoundType.WOOD, MapColor.COLOR_RED, 0.2F, 0.2F, 0),
    NETHER_WART("nether_wart", "Nether Wart", SoundType.WART_BLOCK, MapColor.COLOR_RED, 1.0F, 1.0F, 0),
    WARPED_WART("warped_wart", "Warped Wart", SoundType.WART_BLOCK, MapColor.WARPED_WART_BLOCK, 1.0F, 1.0F, 0),
    SHROOMLIGHT("shroomlight", "Shroomlight", SoundType.SHROOMLIGHT, MapColor.COLOR_ORANGE, 1.0F, 1.0F, 15),
    OCHRE_FROGLIGHT("ochre_froglight", "Ochre Froglight", SoundType.FROGLIGHT, MapColor.SAND, 0.3F, 0.3F, 15),
    VERDANT_FROGLIGHT("verdant_froglight", "Verdant Froglight", SoundType.FROGLIGHT, MapColor.COLOR_GREEN, 0.3F, 0.3F, 15),
    PEARLESCENT_FROGLIGHT("pearlescent_froglight", "Pearlescent Froglight", SoundType.FROGLIGHT, MapColor.COLOR_PINK, 0.3F, 0.3F, 15),
    DEAD_TUBE_CORAL("dead_tube_coral", "Dead Tube Coral", SoundType.STONE, MapColor.COLOR_GRAY, 1.5F, 6.0F, 0),
    DEAD_BRAIN_CORAL("dead_brain_coral", "Dead Brain Coral", SoundType.STONE, MapColor.COLOR_GRAY, 1.5F, 6.0F, 0),
    DEAD_BUBBLE_CORAL("dead_bubble_coral", "Dead Bubble Coral", SoundType.STONE, MapColor.COLOR_GRAY, 1.5F, 6.0F, 0),
    DEAD_FIRE_CORAL("dead_fire_coral", "Dead Fire Coral", SoundType.STONE, MapColor.COLOR_GRAY, 1.5F, 6.0F, 0),
    DEAD_HORN_CORAL("dead_horn_coral", "Dead Horn Coral", SoundType.STONE, MapColor.COLOR_GRAY, 1.5F, 6.0F, 0),
    MELON("melon", "Melon", SoundType.WOOD, MapColor.COLOR_LIGHT_GREEN, 1.0F, 1.0F, 0),
    PUMPKIN("pumpkin", "Pumpkin", SoundType.WOOD, MapColor.COLOR_ORANGE, 1.0F, 1.0F, 0),
    WARPED_NYLIUM("warped_nylium", "Warped Nylium", SoundType.NYLIUM, MapColor.WARPED_NYLIUM, 0.4F, 0.4F, 0),
    CRIMSON_NYLIUM("crimson_nylium", "Crimson Nylium", SoundType.NYLIUM, MapColor.CRIMSON_NYLIUM, 0.4F, 0.4F, 0),
    OAK_LOG("oak_log", "Oak Log", SoundType.WOOD, MapColor.WOOD, 2.0F, 2.0F, 0),
    STRIPPED_OAK_LOG("stripped_oak_log", "Stripped Oak Log", SoundType.WOOD, MapColor.WOOD, 2.0F, 2.0F, 0),
    OAK_PLANKS("oak_planks", "Oak Planks", SoundType.WOOD, MapColor.WOOD, 2.0F, 3.0F, 0),
    SPRUCE_LOG("spruce_log", "Spruce Log", SoundType.WOOD, MapColor.PODZOL, 2.0F, 2.0F, 0),
    STRIPPED_SPRUCE_LOG("stripped_spruce_log", "Stripped Spruce Log", SoundType.WOOD, MapColor.PODZOL, 2.0F, 2.0F, 0),
    SPRUCE_PLANKS("spruce_planks", "Spruce Planks", SoundType.WOOD, MapColor.PODZOL, 2.0F, 3.0F, 0),
    BIRCH_LOG("birch_log", "Birch Log", SoundType.WOOD, MapColor.SAND, 2.0F, 2.0F, 0),
    STRIPPED_BIRCH_LOG("stripped_birch_log", "Stripped Birch Log", SoundType.WOOD, MapColor.SAND, 2.0F, 2.0F, 0),
    BIRCH_PLANKS("birch_planks", "Birch Planks", SoundType.WOOD, MapColor.SAND, 2.0F, 3.0F, 0),
    JUNGLE_LOG("jungle_log", "Jungle Log", SoundType.WOOD, MapColor.DIRT, 2.0F, 2.0F, 0),
    STRIPPED_JUNGLE_LOG("stripped_jungle_log", "Stripped Jungle Log", SoundType.WOOD, MapColor.DIRT, 2.0F, 2.0F, 0),
    JUNGLE_PLANKS("jungle_planks", "Jungle Planks", SoundType.WOOD, MapColor.DIRT, 2.0F, 3.0F, 0),
    ACACIA_LOG("acacia_log", "Acacia Log", SoundType.WOOD, MapColor.COLOR_GRAY, 2.0F, 2.0F, 0),
    STRIPPED_ACACIA_LOG("stripped_acacia_log", "Stripped Acacia Log", SoundType.WOOD, MapColor.COLOR_ORANGE, 2.0F, 2.0F, 0),
    ACACIA_PLANKS("acacia_planks", "Acacia Planks", SoundType.WOOD, MapColor.COLOR_ORANGE, 2.0F, 3.0F, 0),
    DARK_OAK_LOG("dark_oak_log", "Dark Oak Log", SoundType.WOOD, MapColor.COLOR_BROWN, 2.0F, 2.0F, 0),
    STRIPPED_DARK_OAK_LOG("stripped_dark_oak_log", "Stripped Dark Oak Log", SoundType.WOOD, MapColor.COLOR_BROWN, 2.0F, 2.0F, 0),
    DARK_OAK_PLANKS("dark_oak_planks", "Dark Oak Planks", SoundType.WOOD, MapColor.COLOR_BROWN, 2.0F, 3.0F, 0),
    MANGROVE_LOG("mangrove_log", "Mangrove Log", SoundType.WOOD, MapColor.COLOR_RED, 2.0F, 2.0F, 0),
    STRIPPED_MANGROVE_LOG("stripped_mangrove_log", "Stripped Mangrove Log", SoundType.WOOD, MapColor.COLOR_RED, 2.0F, 2.0F, 0),
    MANGROVE_PLANKS("mangrove_planks", "Mangrove Planks", SoundType.WOOD, MapColor.COLOR_RED, 2.0F, 3.0F, 0),
    CHERRY_LOG("cherry_log", "Cherry Log", SoundType.CHERRY_WOOD, MapColor.TERRACOTTA_WHITE, 2.0F, 2.0F, 0),
    STRIPPED_CHERRY_LOG("stripped_cherry_log", "Stripped Cherry Log", SoundType.CHERRY_WOOD, MapColor.TERRACOTTA_PINK, 2.0F, 2.0F, 0),
    CHERRY_PLANKS("cherry_planks", "Cherry Planks", SoundType.CHERRY_WOOD, MapColor.TERRACOTTA_PINK, 2.0F, 3.0F, 0),
    CRIMSON_STEM("crimson_stem", "Crimson Stem", SoundType.STEM, MapColor.CRIMSON_STEM, 2.0F, 2.0F, 0),
    STRIPPED_CRIMSON_STEM("stripped_crimson_stem", "Stripped Crimson Stem", SoundType.STEM, MapColor.CRIMSON_STEM, 2.0F, 2.0F, 0),
    CRIMSON_PLANKS("crimson_planks", "Crimson Planks", SoundType.STEM, MapColor.CRIMSON_STEM, 2.0F, 3.0F, 0),
    WARPED_STEM("warped_stem", "Warped Stem", SoundType.STEM, MapColor.WARPED_STEM, 2.0F, 2.0F, 0),
    STRIPPED_WARPED_STEM("stripped_warped_stem", "Stripped Warped Stem", SoundType.STEM, MapColor.WARPED_STEM, 2.0F, 2.0F, 0),
    WARPED_PLANKS("warped_planks", "Warped Planks", SoundType.STEM, MapColor.WARPED_STEM, 2.0F, 3.0F, 0),
    BAMBOO_BLOCK("bamboo", "Bamboo", SoundType.BAMBOO_WOOD, MapColor.PLANT, 2.0F, 2.0F, 0),
    STRIPPED_BAMBOO_BLOCK("stripped_bamboo", "Stripped Bamboo", SoundType.BAMBOO_WOOD, MapColor.PLANT, 2.0F, 2.0F, 0),
    BAMBOO_PLANKS("bamboo_planks", "Bamboo Planks", SoundType.BAMBOO_WOOD, MapColor.PLANT, 2.0F, 3.0F, 0),
    BAMBOO_MOSAIC("bamboo_mosaic", "Bamboo Mosaic", SoundType.BAMBOO_WOOD, MapColor.PLANT, 2.0F, 3.0F, 0),
    TNT("tnt", "TNT", SoundType.GRASS, MapColor.FIRE, 0.0F, 0.0F, 0),
    END_PORTAL_FRAME("end_portal_frame", "End Portal Frame", SoundType.STONE, MapColor.COLOR_BLACK, 1.5F, 6.0F, 11),
    BEDROCK("bedrock", "Bedrock", SoundType.STONE, MapColor.STONE, -1.0F, 3600000.0F, 0),
    OAK_LEAVES("oak_leaves", "Oak Leaves", SoundType.GRASS, MapColor.PLANT, 0.2F, 0.2F, 0),
    SPRUCE_LEAVES("spruce_leaves", "Spruce Leaves", SoundType.GRASS, MapColor.PLANT, 0.2F, 0.2F, 0),
    BIRCH_LEAVES("birch_leaves", "Birch Leaves", SoundType.GRASS, MapColor.PLANT, 0.2F, 0.2F, 0),
    JUNGLE_LEAVES("jungle_leaves", "Jungle Leaves", SoundType.GRASS, MapColor.PLANT, 0.2F, 0.2F, 0),
    ACACIA_LEAVES("acacia_leaves", "Acacia Leaves", SoundType.GRASS, MapColor.PLANT, 0.2F, 0.2F, 0),
    DARK_OAK_LEAVES("dark_oak_leaves", "Dark Oak Leaves", SoundType.GRASS, MapColor.PLANT, 0.2F, 0.2F, 0),
    MANGROVE_LEAVES("mangrove_leaves", "Mangrove Leaves", SoundType.GRASS, MapColor.PLANT, 0.2F, 0.2F, 0),
    CHERRY_LEAVES("cherry_leaves", "Cherry Leaves", SoundType.CHERRY_LEAVES, MapColor.PLANT, 0.2F, 0.2F, 0),
    AZALEA_LEAVES("azalea_leaves", "Azalea Leaves", SoundType.AZALEA_LEAVES, MapColor.PLANT, 0.2F, 0.2F, 0),
    FLOWERING_AZALEA_LEAVES("flowering_azalea_leaves", "Flowering Azalea Leaves", SoundType.AZALEA_LEAVES, MapColor.PLANT, 0.2F, 0.2F, 0);

    public static final DecorativeSpeleothemVariant[] VALUES = values();

    private final String registryBase;
    private final String displayBase;
    private final SoundType soundType;
    private final MapColor mapColor;
    private final float hardness;
    private final float resistance;
    private final int lightLevel;

    DecorativeSpeleothemVariant(String registryBase, String displayBase, SoundType soundType, MapColor mapColor,
                                float hardness, float resistance, int lightLevel) {
        this.registryBase = registryBase;
        this.displayBase = displayBase;
        this.soundType = soundType;
        this.mapColor = mapColor;
        this.hardness = hardness;
        this.resistance = resistance;
        this.lightLevel = lightLevel;
    }

    public String getRegistryName() {
        return registryBase + "_speleothem";
    }

    public String getDisplayName() {
        return displayBase + " Speleothem";
    }

    public SoundType getSoundType() {
        return soundType;
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public float getHardness() {
        return hardness;
    }

    public float getResistance() {
        return resistance;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    @Override
    public String toString() {
        return registryBase;
    }
}
