package shadows.plants2.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import shadows.placebo.Placebo;
import shadows.placebo.interfaces.IHarvestableEnum;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.util.PlantUtil;

public class BlockEnumHarvestBush<E extends Enum<E> & IHarvestableEnum> extends BlockEnumBush<E> implements IGrowable {

	public static final PropertyBool FRUIT = PropertyBool.create("fruit");

	public BlockEnumHarvestBush(EnumPlantType plantType, E type) {
		super(type.getName() + "_bush", plantType, type);
		setTickRandomly(true);
		setDefaultState(getDefaultState().withProperty(FRUIT, false));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && canGrow(world, pos, state, false) && rand.nextInt(Config.harvestGrowthChance) == 0) grow(world, rand, pos, state);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("harvestables", this, 0, "fruit=true,inventory=true,type=" + type.getName());
		Placebo.PROXY.useRenamedMapper(this, "harvestables");
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FRUIT, false);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!Config.harvests || !state.getValue(FRUIT)) return false;
		for (StackPrimer s : type.getDrops()) {
			ItemStack iS = s.genStack();
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
		if (state.getValue(FRUIT)) return 0;
		return 1;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, Constants.INV, FRUIT);
	}

	@Override
	public List<ItemStack> getActualDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = super.getActualDrops(world, pos, state, fortune);
		if (Config.harvests && state.getValue(FRUIT)) for (StackPrimer s : type.getDrops())
			drops.add(s.genStack());
		return drops;
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
	protected void addStatesToList() {
		PlantUtil.TYPE_TO_STATES.get(plantType).add(getStateFor(type).withProperty(FRUIT, true));
	}

}
