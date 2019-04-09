package shadows.plants2.block;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.oredict.OreDictionary;
import shadows.placebo.Placebo;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.interfaces.ITreeEnum;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.data.PlantConstants;
import shadows.plants2.data.enums.TheBigBookOfEnums.Logs;

public class BlockEnumSapling<E extends Enum<E> & ITreeEnum> extends BlockEnumBush<E> implements IGrowable, IHasRecipe {

	public static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
	protected final Collection<Block> soils;

	public BlockEnumSapling(String name, EnumPlantType type, SoundType s, float hard, float res, Class<E> clazz, int predicate, Block... otherSoils) {
		super(name, type, clazz, predicate);
		setDefaultState(getBlockState().getBaseState().withProperty(property, types.get(0)).withProperty(PlantConstants.INV, false));
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

	//1.13
	public BlockEnumSapling(String name, EnumPlantType type, SoundType s, float hard, float res, E e, Block... otherSoils) {
		super(name, type, e);
		setTickRandomly(true);
		setSoundType(s);
		setHardness(hard);
		setResistance(res);
		this.soils = Arrays.asList(otherSoils);
	}

	public BlockEnumSapling(String name, SoundType s, float hard, float res, E e, Block... otherSoils) {
		this(name, EnumPlantType.Plains, s, hard, res, e, otherSoils);
	}

	public BlockEnumSapling(String name, EnumPlantType type, E e) {
		this(name, type, SoundType.PLANT, 0, 0, e);
	}

	public BlockEnumSapling(String name, E e) {
		this(name, EnumPlantType.Plains, e);
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		OreDictionary.registerOre("treeSapling", new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));
	}

	@Override
	@Deprecated
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState soil = world.getBlockState(pos.down());
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && (soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || isValidSoil(world, pos, getDefaultState(), soil));
	}

	@Override
	public boolean canPlaceBlockAt(IBlockState state, World world, BlockPos pos, EnumFacing side) {
		IBlockState soil = world.getBlockState(pos.down());
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && (soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || isValidSoil(world, pos, state, soil));
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || isValidSoil(world, pos, state, soil);
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
	public String getTranslationKey() {
		return "tile.plants2.sapling";
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
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
		if (property == null) PlaceboUtil.sMRL("saplings", this, 0, "inventory=true,type=" + value.getName());
		else for (int i = 0; i < types.size(); i++) {
			PlaceboUtil.sMRL("saplings", this, i, "inventory=true," + property.getName() + "=" + types.get(i).getName());
		}
		Placebo.PROXY.useRenamedMapper(this, "saplings", property == null ? ",type=" + value.getName() : "");
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return world.rand.nextFloat() < 0.45F;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		if (!TerrainGen.saplingGrowTree(world, rand, pos)) return;
		world.setBlockToAir(pos);
		WorldGenerator gen = property == null ? value.getTreeGen() : state.getValue(property).getTreeGen();
		if (!gen.generate(world, rand, pos)) world.setBlockState(pos, state);
	}

	@Override
	protected void addStatesToList() {

	}

	@Override
	public boolean isValidSoil(World world, BlockPos pos, IBlockState state, IBlockState soil) {
		if (property != null && state.getValue(property) == Logs.BLACK_KAURI) return soil.getBlock().canSustainPlant(soil, world, pos, EnumFacing.UP, Blocks.DEADBUSH);
		return soils.contains(soil.getBlock());
	}
}
