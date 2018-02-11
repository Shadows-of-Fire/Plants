package shadows.plants2.block.forgotten;

import java.util.Random;

import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.placebo.Placebo;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.block.BlockEnumBush;
import shadows.plants2.data.PlantConstants;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.gen.forgotten.BushGen;

public class BlockBushling extends BlockEnumBush<BushSet> implements IGrowable, IPlantable, IHasRecipe {

	public static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(.25, 0, .25, .75, 0.5625, .75);
	public static final AxisAlignedBB BIG_BUSH_AABB = new AxisAlignedBB(.125, 0, .125, .875, 0.625, .875);

	public BlockBushling() {
		super("bushling", EnumPlantType.Plains, BushSet.class, 0);
		setSoundType(SoundType.PLANT);
		setTickRandomly(true);
		setDefaultState(getDefaultState().withProperty(getProperty(), BushSet.BLACKBERRY).withProperty(PlantConstants.INV, false));
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlaceboUtil.sMRL("plants", this, i, "inventory=true," + property.getName() + "=" + types.get(i).getName());
		}
		Placebo.PROXY.useRenamedMapper(this, "plants");
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, PlantConstants.INV);
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
		int k = state.getValue(getProperty()).ordinal();
		if (k == 2 | k == 3) { return BIG_BUSH_AABB; }
		return BUSH_AABB;
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		boolean[] bools = { false, false, false, false };
		for (int i = 0; i < 4; i++) {
			if (BushGen.BUSHGENS[state.getValue(getProperty()).ordinal()].isReplaceable(world, pos.offset(EnumFacing.HORIZONTALS[i]))) bools[i] = true;
		}
		return bools[0] && bools[1] && bools[2] && bools[3];
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		BushGen.BUSHGENS[state.getValue(getProperty()).ordinal()].generate(world, rand, pos);

	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(getProperty()).ordinal();
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(15) == 0 && canGrow(world, pos, state, false)) grow(world, rand, pos, state);
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

	@Override
	protected void addStatesToList() {
	}

	@Override
	protected int getMaxEnumValues() {
		return 8;
	}

	@Override
	public void initRecipes(Register<IRecipe> event) {
	}

}
