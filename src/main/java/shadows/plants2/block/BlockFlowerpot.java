package shadows.plants2.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.client.IHasModel;
import shadows.placebo.client.RenamedStateMapper;
import shadows.placebo.interfaces.IItemBlock;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.VanillaPacketDispatcher;
import shadows.plants2.Plants2;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.state.FlowerpotBlockState;
import shadows.plants2.tile.TileFlowerpot;

public class BlockFlowerpot extends BlockFlowerPot implements IHasModel, IItemBlock {

	public BlockFlowerpot() {
		PlaceboUtil.setRegNameIllegally(this, "flower_pot");
		setTranslationKey(Plants2.MODID + ".flowerpot");
		setCreativeTab(CreativeTabs.DECORATIONS);
		if (PlantConfig.flowerpot) {
			Plants2.INFO.getBlockList().add(this);
			Plants2.INFO.getItemList().add(createItemBlock());
		}
	}

	BlockFlowerpot(String name) {
		setRegistryName(Plants2.MODID, name);
		setTranslationKey(Plants2.MODID + "." + name);
		setCreativeTab(Plants2.INFO.getDefaultTab());
		Plants2.INFO.getBlockList().add(this);
		Plants2.INFO.getItemList().add(createItemBlock());
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		super.onBlockHarvested(world, pos, state, player);
		if (player.capabilities.isCreativeMode) {
			TileFlowerpot pot = getTileEntity(world, pos);
			if (pot != null) pot.setState(Blocks.AIR.getDefaultState());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL(Plants2.MODID, "flowerpot", this, 0, "inventory");
		PlaceboUtil.sMRL(Plants2.MODID, "flowerpot", this, 5444, "cactus");
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper(Plants2.MODID, "flowerpot", "", "normal"));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState flowerpotState, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(hand);
		TileFlowerpot pot = getTileEntity(world, pos);

		if (pot == null) return false;

		ItemStack flowerpot = pot.getFlowerItemStack();

		if (flowerpot.isEmpty()) {
			IBlockState toUse = Block.getBlockFromItem(held.getItem()).getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, held.getMetadata(), player, hand);
			if (toUse.getBlock() == Blocks.AIR && held.getItem() instanceof IPlantable) toUse = ((IPlantable) held.getItem()).getPlant(world, pos);
			if (toUse.getBlock() != this && (toUse.getBlock() == Blocks.AIR || toUse.getBlock().hasTileEntity(toUse))) return false;
			if (BlockEnumHarvestBush.FRUIT.equals(toUse.getBlock().getBlockState().getProperty("fruit"))) toUse = toUse.withProperty(BlockEnumHarvestBush.FRUIT, true);
			pot.setState(toUse);
			ItemStack copy = held.copy();
			copy.setCount(1);
			pot.setItemStack(copy);
			if (!player.capabilities.isCreativeMode) held.shrink(1);
		} else {
			if (player.addItemStackToInventory(pot.getFlowerItemStack())) {
				pot.setState(Blocks.AIR.getDefaultState());
				pot.setItemStack(ItemStack.EMPTY);
			}
		}

		pot.markDirty();
		if (!world.isRemote) VanillaPacketDispatcher.dispatchTEToNearbyPlayers(pot);
		return true;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(this));
		if (getTileEntity(world, pos) != null) {
			drops.add(getTileEntity(world, pos).getFlowerItemStack());
		}
	}

	public static double getDouble(Random rand) {
		return MathHelper.nextDouble(rand, -0.05, 0.05);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileFlowerpot();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return createTileEntity(world, getDefaultState());
	}

	@Override
	public ItemBlock createItemBlock() {
		ItemBlock k = new ItemBlock(this);
		PlaceboUtil.setRegNameIllegally(k, "flower_pot");
		return k;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new FlowerpotBlockState(this, new IProperty[0], new IUnlistedProperty[] { UnlistedStateProperty.UNLISTED_STATE });
	}

	@Nullable
	private static TileFlowerpot getTileEntity(IBlockAccess world, BlockPos pos) {
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity instanceof TileFlowerpot ? (TileFlowerpot) tileentity : null;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileFlowerpot t = getTileEntity(world, pos);
		if (t != null && state instanceof IExtendedBlockState) return ((IExtendedBlockState) state).withProperty(UnlistedStateProperty.UNLISTED_STATE, t.getState());
		return state;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		TileFlowerpot t = getTileEntity(world, pos);
		if (t != null && !t.getStack().isEmpty()) return t.getStack();
		return new ItemStack(this);
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		TileFlowerpot t = getTileEntity(world, pos);
		if (t == null || t.getState().getBlock() == this) return;
		t.getState().getBlock().randomDisplayTick(t.getState(), world, pos, rand);
	}

	public static class UnlistedStateProperty implements IUnlistedProperty<IBlockState> {

		public static final UnlistedStateProperty UNLISTED_STATE = new UnlistedStateProperty();

		@Override
		public String getName() {
			return "pot_state";
		}

		@Override
		public boolean isValid(IBlockState value) {
			return true;
		}

		@Override
		public Class<IBlockState> getType() {
			return IBlockState.class;
		}

		@Override
		public String valueToString(IBlockState value) {
			return value.toString();
		}

	}

}
