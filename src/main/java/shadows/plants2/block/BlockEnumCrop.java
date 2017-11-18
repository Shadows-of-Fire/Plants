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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnumBush;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.Config;
import shadows.plants2.data.enums.IPropertyEnum;
import shadows.plants2.init.ModRegistry;

public class BlockEnumCrop<E extends Enum<E> & IPropertyEnum> extends BlockEnumBush<E> implements IGrowable {

	public static final PropertyInteger AGE = BlockCrops.AGE;
	public static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
	private final Item[] crops;
	private final Item[] seeds;

	public BlockEnumCrop(String name, Class<E> enumClass, int predicate, Item[] crops, Item[] seeds) {
		super(name, EnumPlantType.Crop, enumClass, predicate);
		this.crops = crops;
		this.seeds = seeds;
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
		return new ItemStack(seeds[state.getValue(property).ordinal() % 2]);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return crops[state.getValue(property).ordinal() % 2];
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
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, getInvProperty(), AGE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("crops"));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState().withProperty(property, types.get(meta / 8));
		if (meta >= 8) meta -= 8;
		state = state.withProperty(AGE, meta);
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = state.getValue(property).ordinal() % 2;
		i *= 8;
		i += state.getValue(AGE);
		return i;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(property).ordinal() % 2;
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
	}

	@Override
	public List<ItemStack> getActualDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> k = new ArrayList<ItemStack>();
		int i = state.getValue(property).ordinal() % 2;
		if (fortune != 1594) k.add(new ItemStack(seeds[i]));
		if (state.getValue(AGE) == 7) k.add(new ItemStack(crops[i]));
		if (state.getValue(AGE) == 7 && RANDOM.nextInt(4) == 0) k.add(new ItemStack(seeds[i]));
		return k;
	}

	@Override
	public boolean placeStateAt(IBlockState state, World world, BlockPos pos) {
		return world.setBlockState(pos, state.withProperty(AGE, 7));
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState soil = world.getBlockState(pos.down());
		return super.canPlaceBlockAt(world, pos) && soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, ModRegistry.PLANT_0);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, ModRegistry.PLANT_0);
	}

	@Override
	protected void addStatesToList() {
		if (Config.harvests) super.addStatesToList();
	}

}
