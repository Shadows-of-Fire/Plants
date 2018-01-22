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
import net.minecraftforge.common.BiomeDictionary;
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
import shadows.placebo.block.base.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.item.base.ItemAxeBase;
import shadows.placebo.item.base.ItemHoeBase;
import shadows.placebo.item.base.ItemPickaxeBase;
import shadows.placebo.item.base.ItemShovelBase;
import shadows.placebo.item.base.ItemSwordBase;
import shadows.placebo.util.RecipeHelper;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.Plants2;
import shadows.plants2.biome.BiomeCrystalForest;
import shadows.plants2.block.BlockBrewingCauldron;
import shadows.plants2.block.BlockCataplant;
import shadows.plants2.block.BlockCustomVine;
import shadows.plants2.block.BlockEnumCrop;
import shadows.plants2.block.BlockEnumDoubleFlower;
import shadows.plants2.block.BlockEnumDoubleHarvestBush;
import shadows.plants2.block.BlockEnumFlower;
import shadows.plants2.block.BlockEnumHarvestBush;
import shadows.plants2.block.BlockEnumNetherHarvest;
import shadows.plants2.block.forgotten.BlockBushLeaves;
import shadows.plants2.block.forgotten.BlockBushling;
import shadows.plants2.block.forgotten.BlockCrystal;
import shadows.plants2.block.forgotten.BlockCrystalGround;
import shadows.plants2.block.forgotten.BlockNetherSapling;
import shadows.plants2.block.tree.BlockEnumLeaves;
import shadows.plants2.block.tree.BlockEnumLog;
import shadows.plants2.block.tree.BlockEnumPlanks;
import shadows.plants2.block.tree.BlockEnumSapling;
import shadows.plants2.block.tree.Tree;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.LaterEnums.DoubleHarvestable;
import shadows.plants2.data.enums.LaterEnums.Harvestable;
import shadows.plants2.data.enums.LaterEnums.NetherHarvests;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crops;
import shadows.plants2.data.enums.TheBigBookOfEnums.CrystalLogs;
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
import shadows.plants2.util.EnumBlockFactory;
import shadows.plants2.util.EnumItemFactory;
import shadows.plants2.util.EnumTreeFactory;

public class ModRegistry {

	public static final ToolMaterial MAT_CRYSTAL = EnumHelper.addToolMaterial("crystal", 1, 128, 7.0F, 2, 22);
	public static final ToolMaterial MAT_DARK_CRYSTAL = EnumHelper.addToolMaterial("dark_crystal", 3, 1000, 7.0F, 3, 8);

	public static final EnumBlockFactory<Plants, BlockEnumFlower<Plants>> PLANTS = new EnumBlockFactory<>(e -> new BlockEnumFlower<>(EnumPlantType.Plains, e), Plants.values());
	public static final EnumBlockFactory<Desert, BlockEnumFlower<Desert>> DESERT = new EnumBlockFactory<>(e -> new BlockEnumFlower<>(EnumPlantType.Desert, e), Desert.values());
	public static final EnumBlockFactory<Double, BlockEnumDoubleFlower<Double>> DOUBLE = new EnumBlockFactory<>(e -> new BlockEnumDoubleFlower<>(EnumPlantType.Plains, e), Double.values());

	public static final EnumItemFactory<Generic, ItemBigEnum<Generic>> GENERIC = new EnumItemFactory<>(e -> new ItemBigEnum<>(e), Generic.values());
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

	public static final EnumBlockFactory<Harvestable, BlockEnumHarvestBush<Harvestable>> HARVEST = new EnumBlockFactory<>(e -> new BlockEnumHarvestBush<>(EnumPlantType.Plains, e), Harvestable.values());
	public static final EnumBlockFactory<NetherHarvests, BlockEnumNetherHarvest<NetherHarvests>> NETHER_HARVEST = new EnumBlockFactory<>(e -> new BlockEnumNetherHarvest<>(e), NetherHarvests.values());
	public static final EnumBlockFactory<DoubleHarvestable, BlockEnumDoubleHarvestBush<DoubleHarvestable>> DOUBLE_HARVEST = new EnumBlockFactory<>(e -> new BlockEnumDoubleHarvestBush<>(EnumPlantType.Plains, e), DoubleHarvestable.values());

	public static final BlockEnumCrop<Crops> AMARANTHUS_H_CROP = new BlockEnumCrop<>(Crops.AMARANTHUS_H, AMARANTHUS_H, AMARANTHUS_H_SEEDS);
	public static final BlockEnumCrop<Crops> PINEAPPLE_CROP = new BlockEnumCrop<>(Crops.PINEAPPLE, PINEAPPLE, PINEAPPLE_SEEDS);
	public static final BlockEnumCrop<Crops> OKRA_CROP = new BlockEnumCrop<>(Crops.OKRA, OKRA, OKRA_SEEDS);

	public static final Block ADLUMIA_F = new BlockCustomVine("adlumia_f", Vines.ADLUMIA_F);
	public static final Block AFGEKIA_M = new BlockCustomVine("afgekia_m", Vines.AFGEKIA_M);
	public static final Block ANDROSACE_A = new BlockCustomVine("androsace_a", Vines.ANDROSACE_A);
	public static final Block AKEBIA_Q_VINE = new BlockCustomVine("akebia_q_vine", Vines.AKEBIA_Q, new StackPrimer(AKEBIA_Q));
	public static final Block AMPELOPSIS_A_VINE = new BlockCustomVine("ampelopsis_a_vine", Vines.AMPELOPSIS_A, new StackPrimer(AMPELOPSIS_A));

	public static final Tree<NetherLogs> NETHER_ASH_TREE = new Tree<>(NetherLogs.ASH).makeLog().setSapling(new BlockNetherSapling<>(NetherLogs.ASH)).makeLeaf().makePlanks();
	public static final Tree<NetherLogs> NETHER_BLAZE_TREE = new Tree<>(NetherLogs.BLAZE).makeLog().setSapling(new BlockNetherSapling<>(NetherLogs.BLAZE)).makeLeaf().makePlanks();

	public static final EnumTreeFactory<Logs, Tree<Logs>> BASIC_TREES = new EnumTreeFactory<>(e -> Tree.defaultTree(e), Logs.values());

	public static final EnumBlockFactory<Crystals, BlockEnum<Crystals>> CRYSTAL = new EnumBlockFactory<>(e -> new BlockCrystal(e), Crystals.values());
	public static final Block GROUNDCOVER = new BlockCrystalGround();
	public static final BlockEnumSapling<CrystalLogs> CRYSTAL_SAP = new BlockCrystal.Sapling();
	public static final Tree<CrystalLogs> CRYSTAL_TREE = new BlockCrystal.CrystalTree(CrystalLogs.CRYSTAL);
	public static final Tree<CrystalLogs> DARK_CRYSTAL_TREE = new BlockCrystal.CrystalTree(CrystalLogs.DARK_CRYSTAL);

	public static final EnumBlockFactory<BushSet, BlockBushLeaves> BUSH = new EnumBlockFactory<>(e -> new BlockBushLeaves(e), BushSet.values());
	public static final EnumBlockFactory<BushSet, BlockBushling> BUSHLING = new EnumBlockFactory<>(e -> new BlockBushling(e), BushSet.values());

	public static final BlockCataplant CATAPLANT = new BlockCataplant();

	public static final WorldGenerator ASH_TREE_GEN = new NetherTreeGen<>(NETHER_ASH_TREE);
	public static final WorldGenerator BLAZE_TREE_GEN = new NetherTreeGen<>(NETHER_BLAZE_TREE);
	public static final WorldGenAbstractTree KAURI_TREE_GEN = new StructureGen<Logs>(new BlockPos(-4, 0, -4), Logs.BLACK_KAURI, Type.HOT, Type.SAVANNA, Type.DRY);
	public static final WorldGenAbstractTree PINE_TREE_GEN = new StructureGen<Logs>(new BlockPos(-7, 0, -7), Logs.BRAZILLIAN_PINE, Type.JUNGLE, Type.SAVANNA);
	public static final WorldGenAbstractTree INCENSE_TREE_GEN = new StructureGen<Logs>(new BlockPos(-2, 0, -2), Logs.INCENSE_CEDAR, Type.SNOWY, Type.COLD, Type.CONIFEROUS, Type.FOREST);
	public static final WorldGenAbstractTree MURRAY_TREE_GEN = new StructureGen<Logs>(new BlockPos(-3, 0, -3), Logs.MURRAY_PINE, Type.SNOWY, Type.COLD, Type.CONIFEROUS, Type.FOREST);
	public static final WorldGenAbstractTree CRYSTAL_TREE_GEN = new EnumTreeGen<>(false, false, 4, CRYSTAL_TREE);
	public static final WorldGenAbstractTree DARK_CRYSTAL_TREE_GEN = new EnumTreeGen<>(false, false, 4, DARK_CRYSTAL_TREE);

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
		if (Config.excalibur && Loader.isModLoaded(Constants.BOTANIA_ID)) event.getRegistry().register(new ItemExcalibur());
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
		BiomeDictionary.addTypes(CRYSTAL_FOREST, Type.FOREST, Type.COLD, Type.MAGICAL, Type.RARE);
	}

	public static void oreDict(FMLInitializationEvent e) {
		OreDictionary.registerOre("dyeBlue", Generic.DYE_BLUE.getAsStack());
		OreDictionary.registerOre("dye", Generic.DYE_BLUE.getAsStack());
		OreDictionary.registerOre("dyeBlack", Generic.DYE_BLACK.getAsStack());
		OreDictionary.registerOre("dye", Generic.DYE_BLACK.getAsStack());
		OreDictionary.registerOre("dyeBrown", Generic.DYE_BROWN.getAsStack());
		OreDictionary.registerOre("dye", Generic.DYE_BROWN.getAsStack());
		OreDictionary.registerOre("dyeWhite", Generic.DYE_WHITE.getAsStack());
		OreDictionary.registerOre("dye", Generic.DYE_WHITE.getAsStack());

		for (Block b : Plants2.INFO.getBlockList()) {
			if (b instanceof BlockEnumLog<?>) OreDictionary.registerOre("logWood", new ItemStack(b));
			else if (b instanceof BlockEnumSapling<?>) OreDictionary.registerOre("treeSapling", new ItemStack(b));
			else if (b instanceof BlockEnumLeaves<?>) OreDictionary.registerOre("treeLeaves", new ItemStack(b));
			else if (b instanceof BlockEnumPlanks<?>) OreDictionary.registerOre("plankWood", new ItemStack(b));
		}
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
		Plants2.HELPER.addSimpleShapeless(new ItemStack(Items.STRING, 2), Generic.COTTON.getAsStack(), 5);
		RecipeHelper.addPotionRecipe(PotionTypes.AWKWARD, Generic.SMOLDERBERRY.getAsStack(), PotionTypes.FIRE_RESISTANCE);
		RecipeHelper.addPotionRecipe(PotionTypes.AWKWARD, Generic.EMBERROOT.getAsStack(), PotionTypes.STRENGTH);
		RecipeHelper.addPotionRecipe(PotionTypes.AWKWARD, PHYTOLACCA_A, WITHER);
		RecipeHelper.addPotionRecipe(PotionTypes.HEALING, AMBROSIA_A, REGEN_HEAL);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), BLUEBERRY);
		Plants2.HELPER.addShapeless(Generic.DYE_BLACK.getAsStack(), BLACKBERRY);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage()), SAFFRON);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()), RASPBERRY);
		Plants2.HELPER.addShapeless(new ItemStack(Items.DYE, 1, EnumDyeColor.RED.getDyeDamage()), HUCKLEBERRY);
	}

	public static void generators(FMLPostInitializationEvent e) {
		if (!Config.all_generation) return;
		if (Config.bush_gen) GameRegistry.registerWorldGenerator(new BushGen(), 25);
		if (Config.nether_tree_gen) GameRegistry.registerWorldGenerator(new NetherTreeGen.TreeGenerator(), 20);
		if (Config.tree_gen) GameRegistry.registerWorldGenerator(new EnumTreeGen.TreeGenerator(), 15);
		if (Config.nether_flower_gen) GameRegistry.registerWorldGenerator(new NetherGen(), 30);
	}

	public static void tiles(FMLPreInitializationEvent e) {
		GameRegistry.registerTileEntity(TileBrewingCauldron.class, Plants2.MODID + ":brewing_cauldron");
	}
}
