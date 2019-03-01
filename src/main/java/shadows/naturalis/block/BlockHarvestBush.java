package shadows.naturalis.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import shadows.placebo.util.RecipeHelper;
import shadows.placebo.util.StackPrimer;

public class BlockHarvestBush extends BlockNaturalBush implements IGrowable {

	public static final PropertyBool FRUIT = PropertyBool.create("fruit");
	protected final StackPrimer[] drops;

	public BlockHarvestBush(String name, StackPrimer... drops) {
		super(name, null);
		setTickRandomly(true);
		setDefaultState(getDefaultState().withProperty(FRUIT, false));
		this.drops = drops;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && canGrow(world, pos, state, false)) {
			boolean couldGrow = rand.nextInt(10) == 0;
			if (ForgeHooks.onCropsGrowPre(world, pos, state, couldGrow)) {
				grow(world, rand, pos, state);
				ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!state.getValue(FRUIT)) return false;
		for (StackPrimer s : drops) {
			ItemStack iS = s.get();
			if (!player.addItemStackToInventory(iS)) {
				if (!world.isRemote) Block.spawnAsEntity(world, pos, iS);
			}
		}
		world.setBlockState(pos, state.withProperty(FRUIT, false));
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FRUIT, meta == 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FRUIT) ? 0 : 1;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FRUIT);
	}

	@Override
	public void getActualDrops(NonNullList<ItemStack> list, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		super.getActualDrops(list, world, pos, state, fortune);
		if (state.getValue(FRUIT)) for (StackPrimer s : drops)
			list.add(s.get());
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return !state.getValue(FRUIT);
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state.withProperty(FRUIT, true));
	}

	@Override
	public void addRecipes(RecipeHelper helper) {
	}

}
