package shadows.plants2.block;

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
import shadows.placebo.Placebo;
import shadows.placebo.client.IHasModel;
import shadows.placebo.interfaces.IPostInitUpdate;
import shadows.placebo.interfaces.ISpecialPlacement;
import shadows.placebo.itemblock.ItemBlockBase;
import shadows.plants2.Plants2;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.util.PlantUtil;

public abstract class BushBase extends BlockBush implements IHasModel, IShearable, IPostInitUpdate, ISpecialPlacement {

	protected final EnumPlantType type;
	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.75D, 0.8D);

	public BushBase(String name, EnumPlantType type) {
		setRegistryName(name);
		setUnlocalizedName(Plants2.INFO.getID() + "." + name);
		setCreativeTab(Plants2.INFO.getDefaultTab());
		this.type = type;
		setTickRandomly(false);
		setSoundType(SoundType.PLANT);
		Plants2.INFO.getBlockList().add(this);
		ItemBlock ib = createItemBlock();
		if (ib != null) Plants2.INFO.getItemList().add(ib);
		Placebo.UPDATES.add(this);
	}

	public BushBase(String name) {
		this(name, EnumPlantType.Plains);
	}

	public ItemBlock createItemBlock() {
		return new ItemBlockBase(this);
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return false; //nah
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && canBlockStay(world, pos, getDefaultState());
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState soil = world.getBlockState(pos.down());
		return soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this) || isValidSoil(world, pos, state, soil);
	}

	/**
	 * Method used for checking additional soils.  If this returns false, placement falls back to EnumPlantType checks.
	 * @return If this soil state is acceptable.
	 */
	public boolean isValidSoil(World world, BlockPos pos, IBlockState state, IBlockState soil) {
		return false;
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
		return PlantConfig.needShears;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return getActualDrops(world, pos, world.getBlockState(pos), fortune);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> list, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (!PlantConfig.needShears) list.addAll(getActualDrops(world, pos, state, fortune));
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

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

}
