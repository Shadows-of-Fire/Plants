package shadows.plants2.block.forgotten;

import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import shadows.placebo.Placebo;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.block.BlockEnumBush;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.gen.forgotten.BushGen;

public class BlockBushling extends BlockEnumBush<BushSet> implements IGrowable, IPlantable {

	public static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(.25, 0, .25, .75, 0.5625, .75);
	public static final AxisAlignedBB BIG_BUSH_AABB = new AxisAlignedBB(.125, 0, .125, .875, 0.625, .875);

	public BlockBushling(BushSet bush) {
		super(bush.getName() + "_bushling", EnumPlantType.Plains, bush);
		setSoundType(SoundType.PLANT);
		setTickRandomly(true);
		setDefaultState(getDefaultState().withProperty(Constants.INV, false));
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("plants", this, 0, "inventory=true,type=" + type.getName());
		Placebo.PROXY.useRenamedMapper(this, "plants");
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, Constants.INV);
	}

	@Override
	public String getUnlocalizedName() {
		return "tile.plants2.bushling";
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
		int k = type.ordinal();
		if (k == 2 | k == 3) return BIG_BUSH_AABB;
		return BUSH_AABB;
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		boolean[] bools = { false, false, false, false };
		for (int i = 0; i < 4; i++) {
			if (BushGen.BUSHGENS[type.ordinal()].isReplaceable(world, pos.offset(EnumFacing.HORIZONTALS[i]))) bools[i] = true;
		}
		return bools[0] && bools[1] && bools[2] && bools[3];
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		BushGen.BUSHGENS[type.ordinal()].generate(world, rand, pos);

	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(15) == 0 && canGrow(world, pos, state, false)) grow(world, rand, pos, state);
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

	@Override
	protected void addStatesToList() {
	}

}
