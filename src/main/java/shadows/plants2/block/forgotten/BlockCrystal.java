package shadows.plants2.block.forgotten;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.Placebo;
import shadows.placebo.block.base.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;
import shadows.plants2.block.tree.BlockEnumLeaves;
import shadows.plants2.block.tree.BlockEnumLog;
import shadows.plants2.block.tree.BlockEnumSapling;
import shadows.plants2.block.tree.Tree;
import shadows.plants2.data.enums.TheBigBookOfEnums.CrystalLogs;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;
import shadows.plants2.data.enums.TheBigBookOfEnums.Generic;
import shadows.plants2.init.ModRegistry;

public class BlockCrystal extends BlockEnum<Crystals> implements IHasRecipe {

	public BlockCrystal(Crystals type) {
		super(type.getName() + (type.isShard() ? "_block" : ""), Material.GLASS, SoundType.GLASS, 1.4F, 20F, type, Plants2.INFO);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("blocks", this, 0, "type=" + type.getName() + (type.isShard() ? "_inv" : ""));
		Placebo.PROXY.useRenamedMapper(this, "blocks", "", "type=" + type.getName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile.plants2.crystal";
	}

	@Override
	public int getLightValue(IBlockState state) {
		return type.isShard() ? 5 : 8;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return type.isShard() ? NULL_AABB : FULL_BLOCK_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return !type.isShard();
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return isFullCube(state);
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return type.isShard() ? null : "pickaxe";
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (world.getBlockState(pos.offset(side)) != state) return true;
		if (world.isAirBlock(pos.offset(side))) return true;
		return false;
	}

	@Override
	public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
		return type.isShard() ? 0.3F : 1.4F;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (type.isShard()) drops.add(type.getDrops());
		else drops.add(new ItemStack(this));
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return type.isShard();
	}

	@Override
	public ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addSimpleShapeless(Generic.CRYSTAL_CHUNK.getAsStack(), Generic.CRYSTAL_SHARD.getAsStack(), 4);
		Plants2.HELPER.addSimpleShapeless(Generic.CRYSTAL_SHARD.getAsStack(4), Generic.CRYSTAL_CHUNK.getAsStack(), 1);
		Plants2.HELPER.addSimpleShapeless(Generic.DARK_CRYSTAL_CHUNK.getAsStack(), Generic.DARK_CRYSTAL_SHARD.getAsStack(), 4);
		Plants2.HELPER.addSimpleShapeless(Generic.DARK_CRYSTAL_SHARD.getAsStack(4), Generic.DARK_CRYSTAL_CHUNK.getAsStack(), 1);
		Plants2.HELPER.addShaped(Crystals.CRYSTAL_BRICK.getAsStack(4), 2, 2, Crystals.CRYSTAL_BLOCK.getAsStack(), Crystals.CRYSTAL_BLOCK.getAsStack(), Crystals.CRYSTAL_BLOCK.getAsStack(), Crystals.CRYSTAL_BLOCK.getAsStack());
		Plants2.HELPER.addSimpleShapeless(Crystals.CRYSTAL_BLOCK.getAsStack(), Generic.CRYSTAL_CHUNK.getAsStack(), 4);
		Plants2.HELPER.addShaped(Crystals.DARK_CRYSTAL_BRICK.getAsStack(), 2, 2, Crystals.DARK_CRYSTAL_BLOCK.getAsStack(), Crystals.DARK_CRYSTAL_BLOCK.getAsStack(), Crystals.DARK_CRYSTAL_BLOCK.getAsStack(), Crystals.DARK_CRYSTAL_BLOCK.getAsStack());
		Plants2.HELPER.addSimpleShapeless(Crystals.DARK_CRYSTAL_BLOCK.getAsStack(), Generic.DARK_CRYSTAL_CHUNK.getAsStack(), 4);
		Plants2.HELPER.addShaped(Generic.CRYSTAL_STICK.getAsStack(), 1, 2, Generic.CRYSTAL_SHARD.getAsStack(), Generic.CRYSTAL_SHARD.getAsStack());
		ItemStack cc = Generic.CRYSTAL_CHUNK.getAsStack();
		ItemStack st = Generic.CRYSTAL_STICK.getAsStack();
		Plants2.HELPER.addShaped(ModRegistry.CRYSTAL_PICKAXE, 3, 3, cc, cc, cc, null, st, null, null, st, null);
		Plants2.HELPER.addShaped(ModRegistry.CRYSTAL_SHOVEL, 1, 3, cc, st, st);
		Plants2.HELPER.addShaped(ModRegistry.CRYSTAL_AXE, 2, 3, cc, cc, cc, st, null, st);
		Plants2.HELPER.addShaped(ModRegistry.CRYSTAL_HOE, 2, 3, cc, cc, null, st, null, st);
		Plants2.HELPER.addShaped(ModRegistry.CRYSTAL_SWORD, 1, 3, cc, cc, st);
		cc = Generic.DARK_CRYSTAL_CHUNK.getAsStack();
		Plants2.HELPER.addShaped(ModRegistry.DARK_CRYSTAL_PICKAXE, 3, 3, cc, cc, cc, null, st, null, null, st, null);
		Plants2.HELPER.addShaped(ModRegistry.DARK_CRYSTAL_SHOVEL, 1, 3, cc, st, st);
		Plants2.HELPER.addShaped(ModRegistry.DARK_CRYSTAL_AXE, 2, 3, cc, cc, cc, st, null, st);
		Plants2.HELPER.addShaped(ModRegistry.DARK_CRYSTAL_HOE, 2, 3, cc, cc, null, st, null, st);
		Plants2.HELPER.addShaped(ModRegistry.DARK_CRYSTAL_SWORD, 1, 3, cc, cc, st);
		cc = Generic.CRYSTAL_SHARD.getAsStack();
		Plants2.HELPER.addShaped(ModRegistry.CRYSTAL_SAP, 3, 3, cc, null, cc, null, cc, null, null, Generic.DARK_CRYSTAL_SHARD.getAsStack(), null);
	}

	private static class Logs extends BlockEnumLog<CrystalLogs> {

		private Logs(CrystalLogs type) {
			super(SoundType.GLASS, 1.5F, 5F, type);
		}

		@Override
		public int getLightValue(IBlockState state) {
			return 15;
		}
	}

	private static class Leaves extends BlockEnumLeaves<CrystalLogs> {

		private Leaves(CrystalLogs type) {
			super(SoundType.GLASS, 0.4F, 0, ModRegistry.CRYSTAL_SAP, type);
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(type == CrystalLogs.CRYSTAL ? Generic.CRYSTAL_SHARD.getAsStack(quantityDropped(RANDOM)) : Generic.DARK_CRYSTAL_SHARD.getAsStack(quantityDropped(RANDOM)));
		}

		@Override
		public int quantityDropped(Random rand) {
			return rand.nextInt(4) == 0 ? 1 : 0;
		}
	}

	public static class Sapling extends BlockEnumSapling<CrystalLogs> {

		public Sapling() {
			super(EnumPlantType.Plains, SoundType.GLASS, 0, 0, CrystalLogs.CRYSTAL, ModRegistry.GROUNDCOVER);
		}

		@Override
		public int getLightValue(IBlockState state) {
			return 5;
		}

		@Override
		public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
			world.setBlockToAir(pos);
			WorldGenerator gen = rand.nextFloat() > 0.8F ? ModRegistry.DARK_CRYSTAL_TREE_GEN : ModRegistry.CRYSTAL_TREE_GEN;
			if (!gen.generate(world, rand, pos)) world.setBlockState(pos, state);
		}
	}

	public static class CrystalTree extends Tree<CrystalLogs> {

		public CrystalTree(CrystalLogs type) {
			super(type);
			setLog(new Logs(type));
			setLeaf(new Leaves(type));
			setSapling(ModRegistry.CRYSTAL_SAP);
			makePlanks(Material.GLASS, SoundType.GLASS, 1.4F, 20F);
		}

	}

}
