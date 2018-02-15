package shadows.plants2.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import shadows.placebo.block.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.item.ItemAxeBase;
import shadows.placebo.item.ItemHoeBase;
import shadows.placebo.item.ItemPickaxeBase;
import shadows.placebo.item.ItemShovelBase;
import shadows.placebo.item.ItemSwordBase;
import shadows.placebo.util.EnumBlockFactory;
import shadows.placebo.util.RecipeHelper;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.Plants2;
import shadows.plants2.biome.BiomeCrystalForest;
import shadows.plants2.block.BlockBrewingCauldron;
import shadows.plants2.block.BlockCataplant;
import shadows.plants2.block.BlockCustomVine;
import shadows.plants2.block.BlockEnumBush;
import shadows.plants2.block.BlockEnumCrop;
import shadows.plants2.block.BlockEnumDoubleFlower;
import shadows.plants2.block.BlockEnumDoubleHarvestBush;
import shadows.plants2.block.BlockEnumFlower;
import shadows.plants2.block.BlockEnumHarvestBush;
import shadows.plants2.block.BlockEnumLeaves;
import shadows.plants2.block.BlockEnumLog;
import shadows.plants2.block.BlockEnumNetherHarvest;
import shadows.plants2.block.BlockEnumParticleLeaves;
import shadows.plants2.block.BlockEnumPlanks;
import shadows.plants2.block.BlockEnumSapling;
import shadows.plants2.block.BlockEnumSlab;
import shadows.plants2.block.BlockEnumStairs;
import shadows.plants2.block.BlockFlowerpot;
import shadows.plants2.block.BlockJar;
import shadows.plants2.block.forgotten.BlockBushLeaves;
import shadows.plants2.block.forgotten.BlockBushling;
import shadows.plants2.block.forgotten.BlockCrystal;
import shadows.plants2.block.forgotten.BlockCrystalGround;
import shadows.plants2.block.forgotten.BlockNetherSapling;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.data.PlantConstants;
import shadows.plants2.data.enums.LaterEnums.DoubleHarvestable;
import shadows.plants2.data.enums.LaterEnums.Harvestable;
import shadows.plants2.data.enums.LaterEnums.NetherHarvests;
import shadows.plants2.data.enums.LaterEnums.Planks;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crops;
import shadows.plants2.data.enums.TheBigBookOfEnums.CrystalLogs;
import shadows.plants2.data.enums.TheBigBookOfEnums.CrystalPlanks;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;
import shadows.plants2.data.enums.TheBigBookOfEnums.Desert;
import shadows.plants2.data.enums.TheBigBookOfEnums.Double;
import shadows.plants2.data.enums.TheBigBookOfEnums.Generic;
import shadows.plants2.data.enums.TheBigBookOfEnums.Logs;
import shadows.plants2.data.enums.TheBigBookOfEnums.NetherLogs;
import shadows.plants2.data.enums.TheBigBookOfEnums.Plants;
import shadows.plants2.data.enums.TheBigBookOfEnums.Vines;
import shadows.plants2.gen.EnumTreeGen;
import shadows.plants2.gen.NetherGen;
import shadows.plants2.gen.StructureGen;
import shadows.plants2.gen.forgotten.BushGen;
import shadows.plants2.gen.forgotten.NetherTreeGen;
import shadows.plants2.item.ItemBigEnum;
import shadows.plants2.item.ItemExcalibur;
import shadows.plants2.item.ItemFireFruit;
import shadows.plants2.item.ItemFoodBase;
import shadows.plants2.item.ItemPlantball;
import shadows.plants2.item.ItemSeed;
import shadows.plants2.potion.PotionTypeBase;
import shadows.plants2.tile.TileBrewingCauldron;
import shadows.plants2.tile.TileFlowerpot;

public class ModRegistry {

	public static final ToolMaterial MAT_CRYSTAL = EnumHelper.addToolMaterial("crystal", 1, 128, 7.0F, 2, 22);
	public static final ToolMaterial MAT_DARK_CRYSTAL = EnumHelper.addToolMaterial("dark_crystal", 3, 1000, 7.0F, 3, 8);

	public static final BlockEnumBush<Plants> PLANT_0 = new BlockEnumFlower<>("cosmetic_0", EnumPlantType.Plains, Plants.class, 0);
	public static final BlockEnumBush<Plants> PLANT_1 = new BlockEnumFlower<>("cosmetic_1", EnumPlantType.Plains, Plants.class, 1);
	public static final BlockEnumBush<Plants> PLANT_2 = new BlockEnumFlower<>("cosmetic_2", EnumPlantType.Plains, Plants.class, 2);
	public static final BlockEnumBush<Plants> PLANT_3 = new BlockEnumFlower<>("cosmetic_3", EnumPlantType.Plains, Plants.class, 3);
	public static final BlockEnumBush<Plants> PLANT_4 = new BlockEnumFlower<>("cosmetic_4", EnumPlantType.Plains, Plants.class, 4);
	public static final BlockEnumBush<Plants> PLANT_5 = new BlockEnumFlower<>("cosmetic_5", EnumPlantType.Plains, Plants.class, 5);
	public static final BlockEnumBush<Plants> PLANT_6 = new BlockEnumFlower<>("cosmetic_6", EnumPlantType.Plains, Plants.class, 6);

	public static final BlockEnumBush<Desert> DESERT_0 = new BlockEnumFlower<>("desert_0", EnumPlantType.Desert, Desert.class, 0);
	public static final BlockEnumBush<Desert> DESERT_1 = new BlockEnumFlower<>("desert_1", EnumPlantType.Desert, Desert.class, 1);

	public static final BlockEnumBush<Double> DOUBLE_0 = new BlockEnumDoubleFlower<>("double_0", EnumPlantType.Plains, Double.class, 0);

	public static final ItemBigEnum<Generic> GENERIC = new ItemBigEnum<>("generic", Generic.values());
	public static final Item PLANTBALL = new ItemPlantball();

	public static final Item OKRA = new ItemFoodBase("okra", 3, 1.3f);
	public static final Item PINEAPPLE = new ItemFoodBase("pineapple", 9, 0.6f, new PotionEffect(MobEffects.RESISTANCE, 400, 5), 0.01F); //u just ate an entire pineapple maybe u have some luck too and be invulnerable
	public static final Item AMARANTHUS_H = new ItemFoodBase("amaranthus_h", 5, 0.3f);
	public static final Item AMBROSIA_A = new ItemFoodBase("ambrosia_a", 3, 0.5f, new PotionEffect(MobEffects.REGENERATION, 200, 3), 0.06F); //Medicinal to certain native american tribes
	public static final Item APOCYNUM_C = new ItemFoodBase("apocynum_c", 1, 2.0f);
	public static final Item DAUCUS_C = new ItemFoodBase("daucus_c", 4, 1.1f, new PotionEffect(MobEffects.POISON, 200, 1), 0.24F);
	public static final Item PHYTOLACCA_A = new ItemFoodBase("phytolacca_a", 5, 1.0f, new PotionEffect(MobEffects.WITHER, 160, 1), 0.89F); //Poisonous when uncooked, maybe get a cooked variant?
	public static final Item PLANTAGO_M = new ItemFoodBase("plantago_m", 3, 0.4f, new PotionEffect(MobEffects.ABSORPTION, 160), 0.45F); //These things are like *really* healthy
	public static final Item RUBUS_O = new ItemFoodBase("rubus_o", 6, 0.5f);
	public static final Item RUBUS_P = new ItemFoodBase("rubus_p", 3, 0.5f);
	public static final Item SAFFRON = new ItemFoodBase("saffron", 2, 0.2f);
	public static final Item SOLANUM_C = new ItemFoodBase("solanum_c", 2, 0.5f);
	public static final Item SOLANUM_D = new ItemFoodBase("solanum_d", 3, 0.6f);
	public static final Item SOLANUM_N = new ItemFoodBase("solanum_n", 5, 1.1f);
	public static final Item ALYXIA_B = new ItemFoodBase("alyxia_b", 2, 1.4f);
	public static final Item ACTAEA_P = new ItemFoodBase("actaea_p", 4, 1.2f);
	public static final Item ALTERNANTHERA_F = new ItemFoodBase("alternanthera_f", 7, 0.3f);
	public static final Item AMPELOPSIS_A = new ItemFoodBase("ampelopsis_a", 1, 3.0f);
	public static final Item AKEBIA_Q = new ItemFoodBase("akebia_q", 4, 1.3f);
	public static final Item BLACKBERRY = new ItemFoodBase("blackberry", 2, 1.1F);
	public static final Item BLUEBERRY = new ItemFoodBase("blueberry", 2, 0.9F);
	public static final Item RASPBERRY = new ItemFoodBase("raspberry", 2, 1.4F);
	public static final Item HUCKLEBERRY = new ItemFoodBase("huckleberry", 3, 0.5F);
	public static final Item FIRE_FRUIT = new ItemFireFruit();
	public static final Item TAHITIAN_SPINACH = new ItemFoodBase("tahitian_spinach", 4, 0.3F, new PotionEffect(MobEffects.STRENGTH, 590, 2), 0.2F);

	public static final Item AMARANTHUS_H_SEEDS = new ItemSeed<>("amaranthus_h_seeds", EnumPlantType.Crop, "plants2:crop_0", Crops.AMARANTHUS_H);
	public static final Item OKRA_SEEDS = new ItemSeed<>("okra_seeds", EnumPlantType.Crop, "plants2:crop_0", Crops.OKRA);
	public static final Item PINEAPPLE_SEEDS = new ItemSeed<>("pineapple_seeds", EnumPlantType.Crop, "plants2:crop_1", Crops.PINEAPPLE);

	public static final BlockEnumBush<Harvestable> HARVEST_0 = new BlockEnumHarvestBush<>("harvest_0", EnumPlantType.Plains, Harvestable.class, 0);
	public static final BlockEnumBush<Harvestable> HARVEST_1 = new BlockEnumHarvestBush<>("harvest_1", EnumPlantType.Plains, Harvestable.class, 1);
	public static final BlockEnumBush<NetherHarvests> NETHER_HARVEST = new BlockEnumNetherHarvest<>("nether_harvest", NetherHarvests.class, 0);

	public static final BlockEnumBush<Crops> CROP_0 = new BlockEnumCrop<>("crop_0", Crops.class, 0, AMARANTHUS_H, OKRA, AMARANTHUS_H_SEEDS, OKRA_SEEDS);
	public static final BlockEnumBush<Crops> CROP_1 = new BlockEnumCrop<>("crop_1", Crops.class, 1, PINEAPPLE, null, PINEAPPLE_SEEDS, null);

	public static final BlockEnumBush<DoubleHarvestable> DOUBLE_HARVEST_0 = new BlockEnumDoubleHarvestBush<>("double_harvest_0", EnumPlantType.Plains, DoubleHarvestable.class, 0);

	public static final Block ADLUMIA_F = new BlockCustomVine("adlumia_f", Vines.ADLUMIA_F);
	public static final Block AFGEKIA_M = new BlockCustomVine("afgekia_m", Vines.AFGEKIA_M);
	public static final Block ANDROSACE_A = new BlockCustomVine("androsace_a", Vines.ANDROSACE_A);
	public static final Block AKEBIA_Q_VINE = new BlockCustomVine("akebia_q_vine", Vines.AKEBIA_Q, new StackPrimer(AKEBIA_Q));
	public static final Block AMPELOPSIS_A_VINE = new BlockCustomVine("ampelopsis_a_vine", Vines.AMPELOPSIS_A, new StackPrimer(AMPELOPSIS_A));

	public static final BlockEnum<NetherLogs> NETHER_LOG = new BlockEnumLog<>("nether_log", NetherLogs.class, 0);
	public static final BlockEnumSapling<NetherLogs> NETHER_SAP = new BlockNetherSapling<>("nether_sapling", NetherLogs.class, 0);
	public static final BlockEnum<NetherLogs> NETHER_LEAF = new BlockEnumParticleLeaves<>("nether_leaves", NETHER_SAP, NetherLogs.class, 0);

	public static final BlockEnum<Logs> LOG_0 = new BlockEnumLog<>("log_0", Logs.class, 0);
	public static final BlockEnumSapling<Logs> SAP_0 = new BlockEnumSapling<>("sapling_0", Logs.class, 0);
	public static final BlockEnum<Logs> LEAF_0 = new BlockEnumLeaves<>("leaves_0", SAP_0, Logs.class, 0);

	public static final BlockEnum<Crystals> CRYSTAL = new BlockCrystal();
	public static final Block GROUNDCOVER = new BlockCrystalGround();
	public static final BlockEnum<CrystalLogs> CRYSTAL_LOG = new BlockCrystal.Logs();
	public static final BlockEnumSapling<CrystalLogs> CRYSTAL_SAP = new BlockCrystal.Sapling();
	public static final BlockEnum<CrystalLogs> CRYSTAL_LEAF = new BlockCrystal.Leaves();
	public static final BlockEnum<CrystalPlanks> CRYSTAL_PLANKS = new BlockCrystal.Planks();
	public static final EnumBlockFactory<CrystalPlanks, BlockEnumStairs> CRYSTAL_STAIRS = new EnumBlockFactory<>(e -> new BlockCrystal.Stairs(e), CrystalPlanks.values());
	public static final EnumBlockFactory<CrystalPlanks, BlockEnumSlab> CRYSTAL_SLABS = new EnumBlockFactory<>(e -> new BlockCrystal.Slabs(e), CrystalPlanks.values());

	public static final BlockEnum<Planks> PLANKS = new BlockEnumPlanks<>("planks", Planks.class, 0);
	public static final EnumBlockFactory<Planks, BlockEnumStairs> STAIRS = new EnumBlockFactory<>(e -> new BlockEnumStairs(e, PLANKS), Planks.values());
	public static final EnumBlockFactory<Planks, BlockEnumSlab> SLABS = new EnumBlockFactory<>(e -> new BlockEnumSlab(e, PLANKS), Planks.values());

	public static final BlockEnum<BushSet> BUSH = new BlockBushLeaves();
	public static final BlockEnumBush<BushSet> BUSHLING = new BlockBushling();

	public static final BlockFlowerpot FLOWERPOT = new BlockFlowerpot();
	public static final BlockJar JAR = new BlockJar();

	public static final BlockCataplant CATAPLANT = new BlockCataplant();

	public static final WorldGenerator ASH_TREE = new NetherTreeGen<>(NETHER_LOG, NETHER_LEAF, NetherLogs.ASH);
	public static final WorldGenerator BLAZE_TREE = new NetherTreeGen<>(NETHER_LOG, NETHER_LEAF, NetherLogs.BLAZE);

	public static final WorldGenAbstractTree KAURI_TREE = new StructureGen(new BlockPos(-4, 0, -4), Logs.BLACK_KAURI, Type.HOT, Type.SAVANNA, Type.DRY);
	public static final WorldGenAbstractTree PINE_TREE = new StructureGen(new BlockPos(-7, 0, -7), Logs.BRAZILLIAN_PINE, Type.JUNGLE, Type.SAVANNA);
	public static final WorldGenAbstractTree INCENSE_TREE = new StructureGen(new BlockPos(-2, 0, -2), Logs.INCENSE_CEDAR, Type.SNOWY, Type.COLD, Type.CONIFEROUS, Type.FOREST);
	public static final WorldGenAbstractTree MURRAY_TREE = new StructureGen(new BlockPos(-3, 0, -3), Logs.MURRAY_PINE, Type.SNOWY, Type.COLD, Type.CONIFEROUS, Type.FOREST);

	public static final WorldGenAbstractTree CRYSTAL_TREE = new EnumTreeGen<>(false, 4, CRYSTAL_LOG, CRYSTAL_LEAF, CrystalLogs.CRYSTAL, false);
	public static final WorldGenAbstractTree DARK_CRYSTAL_TREE = new EnumTreeGen<>(false, 4, CRYSTAL_LOG, CRYSTAL_LEAF, CrystalLogs.DARK_CRYSTAL, false);

	public static final Biome CRYSTAL_FOREST = new BiomeCrystalForest();

	public static final PotionType WITHER = new PotionTypeBase("wither", new PotionEffect(MobEffects.WITHER, 3600));
	public static final PotionType REGEN_HEAL = new PotionTypeBase("regen_heal", new PotionEffect(MobEffects.REGENERATION, 1600), new PotionEffect(MobEffects.INSTANT_HEALTH));
	public static final PotionType CAULDRON_BREW = new PotionTypeBase("cauldron_brew");

	public static final Block BREWING_CAULDRON = new BlockBrewingCauldron();

	public static final Item CRYSTAL_PICKAXE = new ItemPickaxeBase("crystal_pickaxe", Plants2.INFO, MAT_CRYSTAL);
	public static final Item CRYSTAL_AXE = new ItemAxeBase("crystal_axe", Plants2.INFO, MAT_CRYSTAL);
	public static final Item CRYSTAL_SHOVEL = new ItemShovelBase("crystal_shovel", Plants2.INFO, MAT_CRYSTAL);
	public static final Item CRYSTAL_HOE = new ItemHoeBase("crystal_hoe", Plants2.INFO, MAT_CRYSTAL);
	public static final Item CRYSTAL_SWORD = new ItemSwordBase("crystal_sword", Plants2.INFO, MAT_CRYSTAL);

	public static final Item DARK_CRYSTAL_PICKAXE = new ItemPickaxeBase("dark_crystal_pickaxe", Plants2.INFO, MAT_DARK_CRYSTAL);
	public static final Item DARK_CRYSTAL_AXE = new ItemAxeBase("dark_crystal_axe", Plants2.INFO, MAT_DARK_CRYSTAL);
	public static final Item DARK_CRYSTAL_SHOVEL = new ItemShovelBase("dark_crystal_shovel", Plants2.INFO, MAT_DARK_CRYSTAL);
	public static final Item DARK_CRYSTAL_HOE = new ItemHoeBase("dark_crystal_hoe", Plants2.INFO, MAT_DARK_CRYSTAL);
	public static final Item DARK_CRYSTAL_SWORD = new ItemSwordBase("dark_crystal_sword", Plants2.INFO, MAT_DARK_CRYSTAL);

	@SubscribeEvent
	public void onBlockRegister(Register<Block> event) {
		event.getRegistry().registerAll(Plants2.INFO.getBlockList().toArray(new Block[0]));
	}

	@SubscribeEvent
	public void onItemRegister(Register<Item> event) {
		event.getRegistry().registerAll(Plants2.INFO.getItemList().toArray(new Item[0]));
		if (PlantConfig.excalibur && Loader.isModLoaded(PlantConstants.BOTANIA_ID)) event.getRegistry().register(new ItemExcalibur());
	}

	@SubscribeEvent
	public void onRecipeRegister(Register<IRecipe> event) {
		recipes(event);
		event.getRegistry().registerAll(Plants2.INFO.getRecipeList().toArray(new IRecipe[0]));
	}

	@SubscribeEvent
	public void onPotionRegister(Register<PotionType> event) {
		event.getRegistry().registerAll(Plants2.INFO.getPotionTypeList().toArray(new PotionType[0]));
	}

	@SubscribeEvent
	public void onBiomeRegister(Register<Biome> event) {
		event.getRegistry().registerAll(Plants2.INFO.getBiomeList().toArray(new Biome[0]));
	}

	public static void oreDict(FMLInitializationEvent e) {
		OreDictionary.registerOre("dyeBlue", Generic.DYE_BLUE.get());
		OreDictionary.registerOre("dye", Generic.DYE_BLUE.get());
		OreDictionary.registerOre("dyeBlack", Generic.DYE_BLACK.get());
		OreDictionary.registerOre("dye", Generic.DYE_BLACK.get());
		OreDictionary.registerOre("dyeBrown", Generic.DYE_BROWN.get());
		OreDictionary.registerOre("dye", Generic.DYE_BROWN.get());
		OreDictionary.registerOre("dyeWhite", Generic.DYE_WHITE.get());
		OreDictionary.registerOre("dye", Generic.DYE_WHITE.get());
		OreDictionary.registerOre("logWood", new ItemStack(NETHER_LOG, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(NETHER_SAP, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(NETHER_LEAF, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(LOG_0, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(SAP_0, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(LEAF_0, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(PLANKS, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("logWood", new ItemStack(CRYSTAL_LOG, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeSapling", new ItemStack(CRYSTAL_SAP, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("treeLeaves", new ItemStack(CRYSTAL_LEAF, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("plankWood", new ItemStack(CRYSTAL_PLANKS, 1, OreDictionary.WILDCARD_VALUE));

		for (Block block : ForgeRegistries.BLOCKS) {
			if (block instanceof BlockBush && Item.getItemFromBlock(block) != Items.AIR) {
				OreDictionary.registerOre("plant", new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE));
			}
		}
	}

	public static void recipes(Register<IRecipe> e) {
		for (Block block : Plants2.INFO.getBlockList()) {
			if (block instanceof IHasRecipe) ((IHasRecipe) block).initRecipes(e);
		}
		for (Item item : Plants2.INFO.getItemList()) {
			if (item instanceof IHasRecipe) ((IHasRecipe) item).initRecipes(e);
		}
		Plants2.HELPER.addSimpleShapeless(new ItemStack(Items.STRING, 2), Generic.COTTON.get(), 5);
		RecipeHelper.addPotionRecipe(PotionTypes.AWKWARD, Generic.SMOLDERBERRY.get(), PotionTypes.FIRE_RESISTANCE);
		RecipeHelper.addPotionRecipe(PotionTypes.AWKWARD, Generic.EMBERROOT.get(), PotionTypes.STRENGTH);
		RecipeHelper.addPotionRecipe(PotionTypes.AWKWARD, PHYTOLACCA_A, WITHER);
		RecipeHelper.addPotionRecipe(PotionTypes.HEALING, AMBROSIA_A, REGEN_HEAL);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), BLUEBERRY);
		Plants2.HELPER.addShapeless(Generic.DYE_BLACK.get(), BLACKBERRY);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage()), SAFFRON);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()), RASPBERRY);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage()), HUCKLEBERRY);
	}

	public static void generators(FMLPostInitializationEvent e) {
		if (!PlantConfig.all_generation) return;
		if (PlantConfig.bush_gen) GameRegistry.registerWorldGenerator(new BushGen(), 25);
		if (PlantConfig.nether_tree_gen) GameRegistry.registerWorldGenerator(new NetherTreeGen.TreeGenerator(), 20);
		if (PlantConfig.tree_gen) GameRegistry.registerWorldGenerator(new EnumTreeGen.TreeGenerator(), 15);
		if (PlantConfig.nether_flower_gen) GameRegistry.registerWorldGenerator(new NetherGen(), 30);
	}

	public static void tiles(FMLPreInitializationEvent e) {
		GameRegistry.registerTileEntity(TileFlowerpot.class, Plants2.MODID + ":flowerpot");
		GameRegistry.registerTileEntity(TileBrewingCauldron.class, Plants2.MODID + ":brewing_cauldron");
	}
}
