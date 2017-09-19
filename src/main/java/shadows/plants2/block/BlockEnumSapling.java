package shadows.plants2.block;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnumBush;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.ITreeEnum;
import shadows.plants2.util.PlantUtil;

public class BlockEnumSapling<E extends Enum<E> & ITreeEnum> extends BlockEnumBush<E> implements IGrowable {

	public static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
	protected final Collection<Block> soils;

	public BlockEnumSapling(String name, EnumPlantType type, SoundType s, float hard, float res, Class<E> clazz, int predicate, Block... otherSoils) {
		super(name, type, clazz, predicate);
		setDefaultState(getBlockState().getBaseState().withProperty(property, types.get(0)).withProperty(Constants.INV, false));
		setTickRandomly(true);
		setSoundType(s);
		setHardness(hard);
		setResistance(res);
		this.soils = Arrays.asList(otherSoils);
	}

	public BlockEnumSapling(String name, SoundType s, float hard, float res, Class<E> clazz, int predicate, Block... otherSoils) {
		this(name, EnumPlantType.Plains, s, hard, res, clazz, predicate, otherSoils);
	}

	public BlockEnumSapling(String name, EnumPlantType type, Class<E> clazz, int predicate) {
		this(name, type, SoundType.PLANT, 0, 0, clazz, predicate);
	}

	public BlockEnumSapling(String name, Class<E> clazz, int predicate) {
		this(name, EnumPlantType.Plains, clazz, predicate);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState soil = world.getBlockState(pos.down());
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && (soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || soils.contains(soil.getBlock()));
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
	public int damageDropped(IBlockState state) {
		return state.getValue(property).ordinal();
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
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("saplings", this, i, "inventory=true," + property.getName() + "=" + types.get(i).getName());
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("saplings"));
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return world.rand.nextFloat() < 0.45F;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockToAir(pos);
		if (!state.getValue(property).getTreeGen().generate(world, rand, pos)) world.setBlockState(pos, state);
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, Constants.INV);
	}

	@Override
	protected void addStatesToList() {

	}
}
