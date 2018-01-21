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
import shadows.plants2.data.Config;
import shadows.plants2.util.PlantUtil;

public abstract class BushBase extends BlockBush implements IHasModel, IShearable, IPostInitUpdate, ISpecialPlacement {

	protected final EnumPlantType plantType;
	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.75D, 0.8D);

	public BushBase(String name, EnumPlantType plantType) {
		setRegistryName(name);
		setUnlocalizedName(Plants2.INFO.getID() + "." + name);
		setCreativeTab(Plants2.INFO.getDefaultTab());
		this.plantType = plantType;
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
		return plantType;
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
		PlantUtil.TYPE_TO_STATES.get(plantType).add(this.getDefaultState());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		addStatesToList();
	}

}
