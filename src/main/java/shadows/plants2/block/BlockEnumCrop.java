package shadows.plants2.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import shadows.placebo.Placebo;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.data.Config;

public class BlockEnumCrop<E extends Enum<E> & IPropertyEnum> extends BlockEnumBush<E> implements IGrowable {

	public static final PropertyInteger AGE = BlockCrops.AGE;
	public static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
	private final StackPrimer crop;
	private final StackPrimer seed;

	public BlockEnumCrop(E type, Item crop, Item seed) {
		super(type.getName() + "_crop", EnumPlantType.Crop, type);
		this.crop = new StackPrimer(crop);
		this.seed = new StackPrimer(seed);
		setDefaultState(getDefaultState().withProperty(AGE, 0));
		setTickRandomly(true);
	}

	@Override
	public IBlockState getStateFor(E e) {
		return super.getStateFor(e).withProperty(AGE, 7);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CROPS_AABB[state.getValue(AGE)];
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!(state.getValue(AGE) == 7) || !(world.getBlockState(pos.down()) instanceof BlockFarmland)) return false;
		for (ItemStack s : getActualDrops(world, pos, state, 1594)) {
			if (!player.addItemStackToInventory(s)) {
				if (!world.isRemote) Block.spawnAsEntity(world, pos, s);
			}
		}
		world.setBlockState(pos, state.withProperty(AGE, 0));
		return true;
	}

	@Override
	public ItemBlock createItemBlock() {
		return null;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return seed.genStack();
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return state.getValue(AGE) < 7;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		int age = state.getValue(AGE);
		int grow = MathHelper.getInt(rand, 1, 3);
		if (age < 7) world.setBlockState(pos, state.withProperty(AGE, age + grow <= 7 ? age + grow : 7));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && canGrow(world, pos, state, false) && rand.nextInt(Config.cropGrowthChance) == 0) grow(world, rand, pos, state);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, getInvProperty(), AGE);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		Placebo.PROXY.useRenamedMapper(this, "crops", "", "type=" + type.getName());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(AGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AGE);
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
	}

	@Override
	public List<ItemStack> getActualDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> k = new ArrayList<ItemStack>();
		if (fortune != 1594) k.add(seed.genStack());
		if (state.getValue(AGE) == 7) {
			k.add(crop.genStack());
			if (RANDOM.nextInt(4) == 0) k.add(seed.genStack());
		}
		return k;
	}

	@Override
	public boolean placeStateAt(IBlockState state, World world, BlockPos pos) {
		return world.setBlockState(pos, state.withProperty(AGE, 7));
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState soil = world.getBlockState(pos.down());
		return super.canPlaceBlockAt(world, pos) && soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, (IPlantable) Blocks.WHEAT);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, (IPlantable) Blocks.WHEAT);
	}

	@Override
	protected void addStatesToList() {
		if (Config.harvests) super.addStatesToList();
	}

}
