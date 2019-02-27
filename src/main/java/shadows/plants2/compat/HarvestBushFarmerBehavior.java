package shadows.plants2.compat;

import de.ellpeck.actuallyadditions.api.farmer.FarmerResult;
import de.ellpeck.actuallyadditions.api.farmer.IFarmerBehavior;
import de.ellpeck.actuallyadditions.api.internal.IFarmer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.placebo.interfaces.ISpecialPlacement;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.block.BlockEnumDoubleHarvestBush;
import shadows.plants2.block.BlockEnumHarvestBush;

public class HarvestBushFarmerBehavior implements IFarmerBehavior {

	boolean plant(World world, BlockPos pos, IBlockState state, IFarmer farmer) {
		if (tryPlant(state, world, pos)) {
			farmer.extractEnergy(700);
			return true;
		}
		return false;
	}

	boolean tryPlant(IBlockState state, World world, BlockPos pos) {
		if (state.getBlock().canPlaceBlockAt(world, pos)) return ((ISpecialPlacement) state.getBlock()).placeStateAt(state, world, pos);
		return false;
	}

	@Override
	public FarmerResult tryPlantSeed(ItemStack seed, World world, BlockPos pos, IFarmer farmer) {
		Block b;
		if (farmer.getEnergy() >= 700 && (b = Block.getBlockFromItem(seed.getItem())) instanceof BlockEnumHarvestBush) {
			if (plant(world, pos, b.getStateForPlacement(world, pos, null, 0, 0, 0, seed.getMetadata(), null, null), farmer)) return FarmerResult.SUCCESS;
		}
		return FarmerResult.FAIL;
	}

	@Override
	public FarmerResult tryHarvestPlant(World world, BlockPos pos, IFarmer farmer) {
		int use = 250;
		if (farmer.getEnergy() >= use) {
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			if (block instanceof BlockEnumHarvestBush && state.getValue(BlockEnumHarvestBush.FRUIT)) return harvest(world, pos, farmer, (BlockEnumHarvestBush<?>) block, state);
		}
		return FarmerResult.FAIL;
	}

	private FarmerResult harvest(World world, BlockPos pos, IFarmer farmer, BlockEnumHarvestBush<?> bush, IBlockState state) {
		NonNullList<ItemStack> drops = NonNullList.create();
		StackPrimer[] primes = bush.getFruit(state);
		for (StackPrimer p : primes)
			drops.add(p.genStack());

		if (farmer.canAddToOutput(drops)) {
			farmer.addToOutput(drops);
			world.setBlockState(pos, state.withProperty(BlockEnumHarvestBush.FRUIT, false));
			if (isDouble(bush)) world.setBlockState(pos.up(), world.getBlockState(pos.up()).withProperty(BlockEnumHarvestBush.FRUIT, false));
			farmer.extractEnergy(250);
			return FarmerResult.SUCCESS;
		}
		return FarmerResult.FAIL;
	}

	@Override
	public int getPriority() {
		return 3;
	}

	boolean isDouble(Block b) {
		return b instanceof BlockEnumDoubleHarvestBush;
	}

}