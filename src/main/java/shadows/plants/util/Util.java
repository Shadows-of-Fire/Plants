package shadows.plants.util;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.BushBase;
import shadows.plants.block.VineBase;
import shadows.plants.block.internal.cosmetic.BlockCrop;
import shadows.plants.block.internal.cosmetic.BlockDoubleHarvestable;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockFruitVine;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.EnumModule;
import shadows.plants.common.IMetaPlant;
import shadows.plants.registry.modules.CosmeticModule;

public class Util {

	public static boolean isException(Block block) {
		return (block instanceof BlockMetaBush || block instanceof BlockDoubleMetaBush);
	}

	@SideOnly(Side.CLIENT)
	public static void initModel(Block block) {
		if (Config.debug)
			System.out.println("Registered model for " + block.toString());
		if (!isException(block))
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
					new ModelResourceLocation(block.getRegistryName(), "inventory"));
		else if (isException(block))
			handleExceptionRenders(block);
	}

	@SideOnly(Side.CLIENT)
	public static void initModel(Item item) {
		if (Config.debug)
			System.out.println("Registered model for " + item.toString());
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void register(Block block) {
		if (Config.debug)
			System.out.println("Registered " + block.toString());
		GameRegistry.register(block);
		if (!isException(block) && !(block instanceof BlockDoubleHarvestable))
			GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}

	public static void register(Item item) {
		if (Config.debug)
			System.out.println("Registered " + item.toString());
		GameRegistry.register(item);
	}

	public static void spawnParticlesAtBlock(World world, BlockPos pos, EnumParticleTypes particle) {
		Random rand = world.rand;
		float f = rand.nextFloat();
		world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double) pos.getX() + f, (double) pos.getY() + f * 2,
				(double) pos.getZ() + f, (double) f, (double) ((float) f - rand.nextFloat() - 1.0F),
				(double) ((float) f + rand.nextFloat()) - 0.5D, new int[0]);
	}

	// Spawns an EntityLiving in world at position.
	public static void spawnCreature(World world, Entity entity, double x, double y, double z) {
		if (entity instanceof EntityDragon) {

			world.getChunkFromBlockCoords(new BlockPos(0, 128, 0));
			EntityDragon entitydragon = new EntityDragon(world);
			entitydragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
			entitydragon.setPosition(x, y + 50, z);
			world.spawnEntityInWorld(entitydragon);
		}

		else if (!(entity instanceof EntityDragon) && entity instanceof EntityLivingBase) {
			EntityLiving entityliving = (EntityLiving) entity;
			entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
			entityliving.rotationYawHead = entityliving.rotationYaw;
			entityliving.renderYawOffset = entityliving.rotationYaw;
			entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)),
					(IEntityLivingData) null);
			world.spawnEntityInWorld(entity);
			entityliving.playLivingSound();

		}
	}

	// Returns true if an item is found in a mob drop list.
	public static boolean dropSearchFinder(List<EntityItem> list, ItemStack stack) {
		Iterator<EntityItem> iterator = list.iterator();
		while (iterator.hasNext()) {
			EntityItem item = iterator.next();
			if (item.getEntityItem().getItem() == stack.getItem()) {
				if (Config.debug)
					System.out.println("item is " + item.getEntityItem().toString() + " while target = "
							+ stack.toString() + " result: true");
				return true;

			}
			if (Config.debug)
				System.out.println("item is " + item.getEntityItem().toString() + " while target = " + stack.toString()
						+ " result: false");
		}

		return false;

	}

	private static int getBlockNumber(BushBase block) {

		if (block.getType() == EnumModule.COSMETIC)
			return Integer.parseInt(block.getRegistryName().getResourcePath().substring(9));
		else
			return 0;
	}

	@SideOnly(Side.CLIENT)
	public static void handleExceptionRenders(Block block) {
		if (block instanceof BlockMetaBush) {
			for (int i = 0; i < 16; i++) {
				ModelLoader
						.setCustomModelResourceLocation(Item.getItemFromBlock(block), i,
								new ModelResourceLocation(new ResourceLocation("plants:cosmetic/"
										+ Util.getBlockNumber((BushBase) block) + "/" + "cosmetic" + "." + i),
										"inventory"));
			}
		} else if (block instanceof BlockDoubleMetaBush) {
			for (int i = 0; i < 8; i++) {
				ModelLoader
						.setCustomModelResourceLocation(Item.getItemFromBlock(block), i,
								new ModelResourceLocation(new ResourceLocation("plants:cosmetic/"
										+ Util.getBlockNumber((BushBase) block) + "/" + "cosmetic" + "." + i),
										"inventory"));
			}
		}
	}

	public static void addSimpleShapeless(Item result, int resultquantity, int resultmeta, Item reagent,
			int reagentmeta, Item reagent2, Item reagent3) {
		GameRegistry.addShapelessRecipe(new ItemStack(result, resultquantity, resultmeta),
				new ItemStack(reagent, 1, reagentmeta), new ItemStack(reagent2, 1, reagentmeta),
				new ItemStack(reagent3, 1, reagentmeta));
	}

	public static void addSimpleShapeless(Item result, int resultquantity, int resultmeta, Item reagent,
			int reagentmeta) {
		GameRegistry.addShapelessRecipe(new ItemStack(result, resultquantity, resultmeta),
				new ItemStack(reagent, 1, reagentmeta));
	}

	public static void addSimpleShapeless(Item result, int resultmeta, Item reagent, int reagentmeta) {
		addSimpleShapeless(result, 1, resultmeta, reagent, reagentmeta);
	}

	public static void addSimpleShapeless(Item result, Item reagent, int reagentmeta) {
		addSimpleShapeless(result, 1, 0, reagent, reagentmeta);
	}

	public static void addSimpleShapeless(Item result, Block reagent, int reagentmeta) {
		addSimpleShapeless(result, 1, 0, reagent, reagentmeta);
	}

	public static void addSimpleShapeless(Item result, int resultmeta, Item reagent) {
		addSimpleShapeless(result, 1, resultmeta, reagent, 0);
	}

	public static void addSimpleShapeless(Item result, int resultmeta, Block reagent) {
		addSimpleShapeless(result, 1, resultmeta, Item.getItemFromBlock(reagent), 0);
	}

	public static void addSimpleShapeless(Item result, int resultmeta, Block reagent, int reagentmeta) {
		addSimpleShapeless(result, 1, resultmeta, Item.getItemFromBlock(reagent), reagentmeta);
	}

	public static void addSimpleShapeless(Item result, int resultquantity, int resultmeta, Block reagent,
			int reagentmeta) {
		GameRegistry.addShapelessRecipe(new ItemStack(result, resultquantity, resultmeta),
				new ItemStack(reagent, 1, reagentmeta));
	}

	public static void addSimpleShapeless(Item result, Item reagent) {
		addSimpleShapeless(result, 1, 0, reagent, 0);
	}

	public static Block getFlowerByChance(Random rand) {
		int x = rand.nextInt(86);
		if (x >= 0 && x < 16)
			return CosmeticModule.cosmetic_1;
		if (x >= 16 && x < 32)
			return CosmeticModule.cosmetic_2;
		if (x >= 32 && x < 48)
			return CosmeticModule.cosmetic_3;
		if (x >= 48 && x < 64)
			return CosmeticModule.cosmetic_4;
		if (x >= 64 && x < 69)
			return CosmeticModule.cosmetic_5;
		if (x == 69)
			return CosmeticModule.ambrosia_a_crop;
		if (x == 70)
			return CosmeticModule.apocynum_c_crop;
		if (x == 71)
			return CosmeticModule.daucus_c_crop;
		if (x == 72)
			return CosmeticModule.okra_crop;
		if (x == 73)
			return CosmeticModule.phytolacca_a_crop;
		if (x == 74)
			return CosmeticModule.pineapple_crop;
		if (x == 75)
			return CosmeticModule.plantago_m_crop;
		if (x == 76)
			return CosmeticModule.rubus_o_crop;
		if (x == 77)
			return CosmeticModule.saffron_crop;
		if (x == 78)
			return CosmeticModule.solanum_c_crop;
		if (x == 79)
			return CosmeticModule.solanum_d_crop;
		if (x == 80)
			return CosmeticModule.solanum_n_crop;
		if (x == 81)
			return CosmeticModule.cosmetic_6;
		if (x == 82)
			return CosmeticModule.alyxia_b_crop;
		if (x == 83)
			return CosmeticModule.amaranthus_h_crop;
		if (x == 84)
			return CosmeticModule.actaea_p_crop;
		if (x == 85)
			return CosmeticModule.alternanthera_f_crop;
		else {
			System.out.println(
					"PLANTS HAS RETURNED A NULL VALUE (X = " + x + ") FOR getFlowerByChance. THIS IS VERY BAD!");
			return null;
		}
	}

	public static Block getRandomVine(Random rand) {
		int x = rand.nextInt(5);
		if (x == 0)
			return CosmeticModule.adlumia_f;
		if (x == 1)
			return CosmeticModule.afgekia_m;
		if (x == 2)
			return CosmeticModule.androsace_a;
		if (x == 3)
			return CosmeticModule.akebia_q_crop;
		if (x == 4)
			return CosmeticModule.ampelopsis_a_crop;
		else
			return null;
	}

	public static IBlockState getStateByChance(Random rand, Block flower) {
		int xk = 0;
		IBlockState state = flower.getDefaultState();
		int maxdata = 0;
		if (flower instanceof IMetaPlant)
			maxdata = ((IMetaPlant) flower).getMaxData();
		if (flower instanceof BlockMetaBush) {
			xk = rand.nextInt(maxdata + 1);
			if (xk == 2 && flower == CosmeticModule.cosmetic_1)
				++xk;
			if (xk == 3 && flower == CosmeticModule.cosmetic_1)
				++xk;
			if (xk == 13 && flower == CosmeticModule.cosmetic_2)
				++xk;
			state = flower.getDefaultState().withProperty(BlockMetaBush.BASICMETA, xk);
		} else if (flower instanceof BlockDoubleMetaBush) {
			xk = rand.nextInt(maxdata + 1);
			state = flower.getDefaultState().withProperty(BlockDoubleMetaBush.META, (xk));
		} else if (flower instanceof BlockCrop)
			state = flower.getDefaultState().withProperty(BlockCrop.AGE, 7);
		else if (flower instanceof BlockDoubleHarvestable)
			state = flower.getDefaultState().withProperty(BlockDoubleHarvestable.UPPER, false);
		return state;
	}

	public static void placeFlower(World world, IBlockState state, BlockPos pos, Block flower) {
		if (state != null && flower != null) {
			if (!(flower instanceof BlockDoubleMetaBush || flower instanceof BlockDoubleHarvestable
					|| flower instanceof BlockFruitVine)) {
				if (world.isAirBlock(pos) && !world.provider.getHasNoSky() && flower.canPlaceBlockAt(world, pos)) {
					world.setBlockState(pos, state, 2);
				}
			} else if (flower instanceof BlockDoubleMetaBush) {
				if (world.isAirBlock(pos) && world.isAirBlock(pos.up()) && !world.provider.getHasNoSky()
						&& flower.canPlaceBlockAt(world, pos)) {
					world.setBlockState(pos, state.withProperty(BlockDoubleMetaBush.UPPER, false), 2);
					world.setBlockState(pos.up(), state.withProperty(BlockDoubleMetaBush.UPPER, true), 2);
				}
			} else if (flower instanceof BlockDoubleHarvestable) {
				if (world.isAirBlock(pos) && world.isAirBlock(pos.up()) && !world.provider.getHasNoSky()
						&& flower.canPlaceBlockAt(world, pos)) {
					world.setBlockState(pos, state.withProperty(BlockDoubleHarvestable.UPPER, false), 2);
					world.setBlockState(pos.up(), state.withProperty(BlockDoubleHarvestable.UPPER, true), 2);
				}
			}
		}
	}

	public static void genFlowerPatch(World world, BlockPos pos, Random rand, IBlockState state, Block flower) {
		int dist = Config.patchsize;// Spread of the flowers, a radius of sorts.
		for (int i = 0; i < Config.quantity; i++) {// number of positions
													// selected per event.
			int x = pos.getX() + MathHelper.getRandomIntegerInRange(rand, -5, 5);
			int z = pos.getZ() + MathHelper.getRandomIntegerInRange(rand, -5, 5);
			int y = world.getTopSolidOrLiquidBlock(pos).getY();
			for (int j = 0; j < Config.density; j++) { // number of placements
														// that occur.
				int x1 = x + MathHelper.getRandomIntegerInRange(rand, -1 * dist, dist);
				int y1 = y + rand.nextInt(4) - rand.nextInt(4);
				int z1 = z + MathHelper.getRandomIntegerInRange(rand, -1 * dist, dist);
				BlockPos pos2 = new BlockPos(x1, y1, z1);
				Util.placeFlower(world, state, pos2, flower);
			}
		}
	}

	public static void genMegaPatch(World world, BlockPos pos, Random rand, IBlockState state, Block flower) {
		int dist = 14;// Spread of the flowers, a radius of sorts.
		for (int i = 0; i < 5; i++) {// number of positions selected per event.
			int x = pos.getX() + rand.nextInt(16) + 8;
			int z = pos.getZ() + rand.nextInt(16) + 8;
			int y = world.getTopSolidOrLiquidBlock(pos).getY();
			for (int j = 0; j < 48; j++) { // number of placements that occur.
				int x1 = x + rand.nextInt(dist * 2) - dist;
				int y1 = y + rand.nextInt(4) - rand.nextInt(4);
				int z1 = z + rand.nextInt(dist * 2) - dist;
				BlockPos pos2 = new BlockPos(x1, y1, z1);
				Util.placeFlower(world, state, pos2, flower);
			}
		}
	}

	public static void genSmallFlowerPatchNearby(World world, BlockPos pos, Random rand, IBlockState state,
			Block flower) {
		int dist = 2;// Spread of the flowers, a radius of sorts.
		for (int i = 0; i < 2; i++) {// number of positions selected per event.
			int x = pos.getX() + MathHelper.getRandomIntegerInRange(rand, -2, 2);
			int z = pos.getZ() + MathHelper.getRandomIntegerInRange(rand, -2, 2);
			int y = world.getTopSolidOrLiquidBlock(pos).getY();
			for (int j = 0; j < 3; j++) { // number of placements that occur.
				int x1 = x + MathHelper.getRandomIntegerInRange(rand, -1 * dist, dist);
				int y1 = y;
				int z1 = z + MathHelper.getRandomIntegerInRange(rand, -1 * dist, dist);
				BlockPos pos2 = new BlockPos(x1, y1, z1);
				Util.placeFlower(world, state, pos2, flower);
			}
		}
	}

	public static boolean isEventTypeAcceptable(EventType type) {
		return (type == EventType.CACTUS || type == EventType.DEAD_BUSH || type == EventType.LILYPAD
				|| type == EventType.FLOWERS || type == EventType.GRASS || type == EventType.PUMPKIN
				|| type == EventType.REED || type == EventType.TREE);

	}

	public static void placeVine(World world, VineBase vine, BlockPos pos, EnumFacing facing) {
		if (!world.isRemote && vine.canPlaceBlockOnSide(world, pos.offset(facing), facing)) {
			if (Config.debug)
				System.out.println(vine.toString());
			world.setBlockState(pos.offset(facing),
					vine.onBlockPlaced(world, pos.offset(facing), facing, 0, 0, 0, 0, null));
		}
	}

}
