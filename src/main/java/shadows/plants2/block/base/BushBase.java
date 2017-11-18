package shadows.plants2.block.base;

import java.util.ArrayList;
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
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import shadows.plants2.client.IHasModel;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IPostInitUpdate;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.util.ISpecialPlacement;
import shadows.plants2.util.PlantUtil;

public abstract class BushBase extends BlockBush implements IHasModel, IShearable, IPostInitUpdate, ISpecialPlacement {

	protected final EnumPlantType type;
	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.75D, 0.8D);

	public BushBase(String name, boolean hasCustomItemBlock, EnumPlantType type) {
		setRegistryName(name);
		setUnlocalizedName(Constants.MODID + "." + name);
		setCreativeTab(ModRegistry.TAB);
		this.type = type;
		setTickRandomly(false);
		setSoundType(SoundType.PLANT);
		ModRegistry.BLOCKS.add(this);
		if (!hasCustomItemBlock) ModRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
		Constants.UPDATES.add(this);
	}

	public BushBase(String name, EnumPlantType type) {
		this(name, false, type);
	}

	public BushBase(String name) {
		this(name, false, EnumPlantType.Plains);
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return false; //nah
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return type;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return Config.needShears;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return getActualDrops(world, pos, world.getBlockState(pos), fortune);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> list, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (!Config.needShears) list.addAll(getActualDrops(world, pos, state, fortune));
	}

	public List<ItemStack> getActualDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> k = new ArrayList<ItemStack>();
		k.add(new ItemStack(getItemDropped(state, RANDOM, fortune), quantityDropped(RANDOM), damageDropped(state)));
		return k;
	}

	@Override
	public boolean placeStateAt(IBlockState state, World world, BlockPos pos) {
		return world.setBlockState(pos, state);
	}

	protected void addStatesToList() {
		PlantUtil.TYPE_TO_STATES.get(type).add(this.getDefaultState());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		addStatesToList();
	}

}
