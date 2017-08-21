package shadows.plants2.block.forgotten;

import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnum;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.gen.forgotten.BushGenerator;
import shadows.plants2.util.PlantUtil;

public class BlockBushling extends BlockEnum<BushSet> implements IGrowable, IPlantable {

	public static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(.25, 0, .25, .75, 0.5625, .75);
	public static final AxisAlignedBB BIG_BUSH_AABB = new AxisAlignedBB(.125, 0, .125, .875, 0.625, .875);

	public BlockBushling() {
		super("bushling", Material.PLANTS, SoundType.PLANT, 0, 0, BushSet.class);
		setSoundType(SoundType.PLANT);
		setTickRandomly(true);
		setDefaultState(getDefaultState().withProperty(getProperty(), BushSet.BLACKBERRY).withProperty(Constants.INV, false));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("plants", this, i, "inventory=true," + property.getName() + "=" + types.get(i).getName());
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("plants"));
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, Constants.INV);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		int k = state.getValue(getProperty()).ordinal();
		if (k == 2 | k == 3) {
			return BIG_BUSH_AABB;
		}
		return BUSH_AABB;
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		boolean[] bools = { false, false, false, false };
		for (int i = 0; i < 4; i++) {
			if (BushGenerator.BUSHGENS[state.getValue(getProperty()).ordinal()].isReplaceable(world, pos.offset(EnumFacing.HORIZONTALS[i])))
				bools[i] = true;
		}
		return bools[0] && bools[1] && bools[2] && bools[3];
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		BushGenerator.BUSHGENS[state.getValue(getProperty()).ordinal()].generate(world, rand, pos);

	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(getProperty()).ordinal();
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(15) == 0 && canGrow(world, pos, state, false))
			grow(world, rand, pos, state);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int i = 0; i < BushSet.values().length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(getProperty()).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(getProperty(), BushSet.values()[meta]);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getDefaultState();
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return super.canPlaceBlockAt(world, pos) && state.getBlock().canSustainPlant(state, world, pos.down(), EnumFacing.UP, this);

	}

}
