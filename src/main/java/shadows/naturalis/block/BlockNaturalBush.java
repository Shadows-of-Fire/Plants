package shadows.naturalis.block;

import java.util.List;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;
import shadows.naturalis.Naturalis;
import shadows.naturalis.registry.NaturalConfig;
import shadows.naturalis.registry.RegHandler;
import shadows.naturalis.util.Color;
import shadows.naturalis.util.EnumGenType;
import shadows.placebo.client.IHasModel;
import shadows.placebo.data.IHasRecipe;
import shadows.placebo.item.ItemBlockBase;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.RecipeHelper;

public class BlockNaturalBush extends BlockBush implements IHasModel, IShearable, IHasRecipe {

	public static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.75D, 0.8D);

	protected Color color;

	public BlockNaturalBush(String name, Color color) {
		setTickRandomly(false);
		setSoundType(SoundType.PLANT);
		this.color = color;
		init(Naturalis.MODID, name);
	}

	public void init(String modid, String name) {
		PlaceboUtil.initBlock(this, modid, name, 0, 0);
		RegHandler.BLOCKS.add(this);
		RegHandler.ITEMS.add(createItemBlock());
		getGenType().getStates().add(getGenState());
		setCreativeTab(Naturalis.TAB);
	}

	public ItemBlock createItemBlock() {
		return new ItemBlockBase(this);
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && canBlockStay(world, pos, getDefaultState());
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || isValidSoil(world, pos.down(), state, soil);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BUSH_AABB;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return NaturalConfig.reqShears;
	}

	@Override
	public final List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		NonNullList<ItemStack> list = NonNullList.create();
		getActualDrops(list, world, pos, world.getBlockState(pos), fortune);
		return list;
	}

	@Override
	public final void getDrops(NonNullList<ItemStack> list, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (!NaturalConfig.reqShears) getActualDrops(list, world, pos, state, fortune);
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	/**
	 * Adds the actual drops of this block to the passed list.  This is called by getDrops and onSheared, and both of those are final.
	 */
	public void getActualDrops(NonNullList<ItemStack> list, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		super.getDrops(list, world, pos, state, fortune);
	}

	/**
	 * Method used for checking additional soils.  If this returns false, placement falls back to EnumPlantType checks.
	 * @return If this soil state is acceptable.
	 */
	public boolean isValidSoil(World world, BlockPos pos, IBlockState state, IBlockState soil) {
		return false;
	}

	/**
	 * This controls where this block will be generated in the world. See {@link EnumGenType} for types.
	 */
	public EnumGenType getGenType() {
		return EnumGenType.GRASS;
	}

	/**
	 * This controls what state from this block is considered to be the "natural" state that will be placed during worldgen.
	 */
	public IBlockState getGenState() {
		return getDefaultState();
	}

	@Override
	public void addRecipes(RecipeHelper helper) {
		helper.addShapeless(color.get(), this);
	}

}
