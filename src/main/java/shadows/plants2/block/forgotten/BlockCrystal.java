package shadows.plants2.block.forgotten;

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
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.Placebo;
import shadows.placebo.block.base.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;
import shadows.plants2.block.BlockEnumLeaves;
import shadows.plants2.block.BlockEnumLog;
import shadows.plants2.block.BlockEnumSapling;
import shadows.plants2.data.enums.TheBigBookOfEnums.CrystalLogs;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;
import shadows.plants2.data.enums.TheBigBookOfEnums.Generic;
import shadows.plants2.init.ModRegistry;

public class BlockCrystal extends BlockEnum<Crystals> implements IHasRecipe {

	public BlockCrystal() {
		super("crystal", Material.GLASS, SoundType.GLASS, 1.4F, 20F, Crystals.class, Plants2.INFO);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlaceboUtil.sMRL("blocks", this, i, "type=" + types.get(i).getName() + (types.get(i).isShard() ? "_inv" : ""));
		}
		Placebo.PROXY.useRenamedMapper(this, "blocks");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int getLightValue(IBlockState state) {
		return state.getValue(property).isShard() ? 5 : 8;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getValue(property).isShard() ? NULL_AABB : FULL_BLOCK_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return !state.getValue(property).isShard();
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return isFullCube(state);
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return state.getValue(property).isShard() ? null : "pickaxe";
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (world.getBlockState(pos.offset(side)) != state) return true;
		if (world.isAirBlock(pos.offset(side))) return true;
		return false;
	}

	@Override
	public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
		return state.getValue(property).isShard() ? 0.3F : 1.4F;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		Crystals c = state.getValue(property);
		if (c.isShard()) drops.add(c.getDrops());
		else drops.add(new ItemStack(this, 1, c.ordinal()));
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return state.getValue(property).isShard();
	}

	@Override
	public ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this, 1, state.getValue(property).ordinal());
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addSimpleShapeless(Generic.CRYSTAL_CHUNK.get(), Generic.CRYSTAL_SHARD.get(), 4);
		Plants2.HELPER.addSimpleShapeless(Generic.CRYSTAL_SHARD.get(4), Generic.CRYSTAL_CHUNK.get(), 1);
		Plants2.HELPER.addSimpleShapeless(Generic.DARK_CRYSTAL_CHUNK.get(), Generic.DARK_CRYSTAL_SHARD.get(), 4);
		Plants2.HELPER.addSimpleShapeless(Generic.DARK_CRYSTAL_SHARD.get(4), Generic.DARK_CRYSTAL_CHUNK.get(), 1);
		Plants2.HELPER.addShaped(Crystals.CRYSTAL_BRICK.get(4), 2, 2, Crystals.CRYSTAL_BLOCK.get(), Crystals.CRYSTAL_BLOCK.get(), Crystals.CRYSTAL_BLOCK.get(), Crystals.CRYSTAL_BLOCK.get());
		Plants2.HELPER.addSimpleShapeless(Crystals.CRYSTAL_BLOCK.get(), Generic.CRYSTAL_CHUNK.get(), 4);
		Plants2.HELPER.addShaped(Crystals.DARK_CRYSTAL_BRICK.get(), 2, 2, Crystals.DARK_CRYSTAL_BLOCK.get(), Crystals.DARK_CRYSTAL_BLOCK.get(), Crystals.DARK_CRYSTAL_BLOCK.get(), Crystals.DARK_CRYSTAL_BLOCK.get());
		Plants2.HELPER.addSimpleShapeless(Crystals.DARK_CRYSTAL_BLOCK.get(), Generic.DARK_CRYSTAL_CHUNK.get(), 4);
		Plants2.HELPER.addShaped(Generic.CRYSTAL_STICK.get(), 1, 2, Generic.CRYSTAL_CHUNK.get(), Generic.CRYSTAL_CHUNK.get());
	}

	public static class Logs extends BlockEnumLog<CrystalLogs> {

		public Logs() {
			super("crystal_log", SoundType.GLASS, 1.5F, 5F, CrystalLogs.class, 0);
		}

		@Override
		public int getLightValue(IBlockState state) {
			return 15;
		}
	}

	public static class Leaves extends BlockEnumLeaves<CrystalLogs> {

		public Leaves() {
			super("crystal_leaves", SoundType.GLASS, 0.4F, 0, ModRegistry.CRYSTAL_SAP, CrystalLogs.class, 0);
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(state.getValue(property) == CrystalLogs.CRYSTAL ? Crystals.CRYSTAL_SHARD.get(quantityDropped(RANDOM)) : Crystals.DARK_CRYSTAL_SHARD.get(quantityDropped(RANDOM)));
		}

		@Override
		public int getLightValue(IBlockState state) {
			return 15;
		}
	}

	public static class Sapling extends BlockEnumSapling<CrystalLogs> {

		public Sapling() {
			super("crystal_sapling", SoundType.GLASS, 0, 0, CrystalLogs.class, 0, ModRegistry.GROUNDCOVER);
		}

		@Override
		public int getLightValue(IBlockState state) {
			return 7;
		}
	}

}
