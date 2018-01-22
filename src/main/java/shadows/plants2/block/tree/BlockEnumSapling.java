package shadows.plants2.block.tree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import shadows.placebo.Placebo;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.block.BlockEnumBush;
import shadows.plants2.data.Constants;

public class BlockEnumSapling<E extends Enum<E> & ITreeEnum<E>> extends BlockEnumBush<E> implements IGrowable {

	public static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
	protected final Collection<Block> soils;

	public BlockEnumSapling(EnumPlantType plantType, SoundType s, float hard, float res, E type, Block... otherSoils) {
		super(type.getName() + "_sapling", plantType, type);
		setDefaultState(getBlockState().getBaseState().withProperty(Constants.INV, false));
		setTickRandomly(true);
		setSoundType(s);
		setHardness(hard);
		setResistance(res);
		this.soils = Arrays.asList(otherSoils);
	}

	public BlockEnumSapling(EnumPlantType plantType, E type) {
		this(plantType, SoundType.PLANT, 0, 0, type);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState soil = world.getBlockState(pos.down());
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && (soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || soils.contains(soil.getBlock()));
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || soils.contains(soil.getBlock());
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SAPLING_AABB;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && world.getLightFromNeighbors(pos.up()) >= 6 && rand.nextInt(7) == 0) {
			this.grow(world, rand, pos, state);
		}
	}

	@Override
	public String getUnlocalizedName() {
		return "tile.plants2.sapling";
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("saplings", this, 0, "inventory=true,type=" + type.getName());
		Placebo.PROXY.useRenamedMapper(this, "saplings", ",type=" + type.getName());
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return world.rand.nextFloat() < 0.45F;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockToAir(pos);
		if (!type.getTree().getTreeGen().generate(world, rand, pos)) world.setBlockState(pos, state);
	}

	@Override
	protected void addStatesToList() {

	}
}
