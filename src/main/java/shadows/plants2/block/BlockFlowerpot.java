package shadows.plants2.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.properties.PropertyEnum;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.Loader;
import shadows.placebo.block.base.IEnumBlock;
import shadows.placebo.client.IHasModel;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;
import shadows.plants2.compat.AAFlowerpot;
import shadows.plants2.compat.BinnieIntegration.BotanyFlowerpot;
import shadows.plants2.compat.BotaniaFlowerpot;
import shadows.plants2.compat.DefaultFlowerpot;
import shadows.plants2.compat.ForestryIntegration.ForestryFlowerpot;
import shadows.plants2.compat.IFlowerpotHandler;
import shadows.plants2.compat.TFFlowerpot;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.TheBigBookOfEnums;
import shadows.plants2.data.enums.TheBigBookOfEnums.FlowerpotPlants;
import shadows.plants2.state.FlowerpotBlockState.FlowerpotStateContainer;
import shadows.plants2.tile.TileFlowerpot;

public class BlockFlowerpot extends BlockFlowerPot implements IEnumBlock<FlowerpotPlants>, IHasModel {

	public static final PropertyEnum<FlowerpotPlants> PROP = TileFlowerpot.PROPERTY;
	public static final List<IFlowerpotHandler> HANDLERS = new ArrayList<>();

	private final BlockStateContainer container;

	public BlockFlowerpot() {
		PlaceboUtil.setRegNameIllegally(this, "flower_pot");
		setUnlocalizedName(Constants.MODID + ".flowerpot");
		setCreativeTab(CreativeTabs.DECORATIONS);
		container = createStateContainer();
		setDefaultState(container.getBaseState().withProperty(PROP, FlowerpotPlants.NONE));
		if (Config.flowerpot) {
			Plants2.INFO.getBlockList().add(this);
			Plants2.INFO.getItemList().add(createItemBlock());
			initModHandlers();
		}
	}

	private void initModHandlers() {
		HANDLERS.add(new DefaultFlowerpot());
		if (Loader.isModLoaded(Constants.TF_ID)) HANDLERS.add(new TFFlowerpot());
		if (Loader.isModLoaded(Constants.BOTANIA_ID)) HANDLERS.add(new BotaniaFlowerpot());
		if (Loader.isModLoaded(Constants.FORESTRY_ID)) HANDLERS.add(new ForestryFlowerpot());
		if (Loader.isModLoaded(Constants.AA_ID)) HANDLERS.add(new AAFlowerpot());
		if (Loader.isModLoaded(Constants.BOTANY_ID)) HANDLERS.add(new BotanyFlowerpot());
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (!(tile instanceof TileFlowerpot)) return state;
		return state.withProperty(PROP, ((TileFlowerpot) tile).getFlower());
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		super.onBlockHarvested(world, pos, state, player);
		if (player.capabilities.isCreativeMode) {
			TileFlowerpot pot = getTileEntity(world, pos);
			if (pot != null) {
				pot.setFlower(FlowerpotPlants.NONE);
			}
		}
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL(Constants.MODID, "flowerpot/plants2", this, 0, "inventory");
		Plants2.PROXY.potStateMap(this);
	}

	@Override
	public int getLightValue(IBlockState state) {
		return state.getValue(PROP).getLightLevel();
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
			String name = "none";

			for (IFlowerpotHandler handler : HANDLERS) {
				if (canHandle(handler, toUse, held)) {
					name = handler.getFinalName(toUse, held);
					break;
				}
			}

			FlowerpotPlants plant = TheBigBookOfEnums.NAME_TO_ENUM.get(name);

			if (plant == FlowerpotPlants.NONE || plant == null) return false;

			pot.setFlower(plant);
			ItemStack toSet = new ItemStack(held.getItem(), 1, held.getMetadata());
			if (held.getTagCompound() != null) toSet.setTagCompound(held.getTagCompound());
			pot.setItemStack(toSet);
			world.setBlockState(pos, getDefaultState().withProperty(PROP, pot.getFlower()));
			if (!player.capabilities.isCreativeMode) held.shrink(1);
		} else {
			if (player.addItemStackToInventory(pot.getFlowerItemStack())) {
				pot.setFlower(FlowerpotPlants.NONE);
				pot.setItemStack(ItemStack.EMPTY);
				world.setBlockState(pos, getDefaultState().withProperty(PROP, pot.getFlower()));
			}
		}
		pot.markDirty();
		world.notifyBlockUpdate(pos, flowerpotState, flowerpotState, 3);
		return true;
	}

	private static boolean canHandle(IFlowerpotHandler handler, IBlockState state, ItemStack stack) {
		return (state.getBlock() != Blocks.AIR && handler.owns(state.getBlock())) || (!stack.isEmpty() && handler.owns(stack.getItem()));
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(this));
		if (getTileEntity(world, pos) != null) {
			drops.add(getTileEntity(world, pos).getFlowerItemStack());
		}
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		state = getActualState(state, world, pos);
		if (Loader.isModLoaded(Constants.BOTANIA_ID) && state.getValue(PROP).getName().startsWith("b_")) BotaniaFlowerpot.randomDisplayTick(state, world, pos, rand);
		if (rand.nextFloat() < 0.1F) {
			FlowerpotPlants p = state.getValue(PROP);
			if (p == FlowerpotPlants.ASH || p == FlowerpotPlants.BLAZE) world.spawnParticle(p == FlowerpotPlants.ASH ? EnumParticleTypes.SMOKE_LARGE : EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.4, pos.getZ() + 0.5, getDouble(rand), 0.05, getDouble(rand));
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
	public List<FlowerpotPlants> getTypes() {
		return Arrays.asList(FlowerpotPlants.values());
	}

	@Override
	public ItemBlock createItemBlock() {
		ItemBlock k = new ItemBlock(this);
		PlaceboUtil.setRegNameIllegally(k, "flower_pot");
		return k;
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new FlowerpotStateContainer(this, PROP);
	}

	@Override
	public BlockStateContainer getRealStateContainer() {
		return container;
	}

	@Override
	public BlockStateContainer getBlockState() {
		return getRealStateContainer();
	}

	@Override
	public PropertyEnum<FlowerpotPlants> getProperty() {
		return PROP;
	}

	@Nullable
	private static TileFlowerpot getTileEntity(IBlockAccess world, BlockPos pos) {
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity instanceof TileFlowerpot ? (TileFlowerpot) tileentity : null;
	}

	@Override
	public IBlockState getStateFor(FlowerpotPlants e) {
		return this.getDefaultState().withProperty(PROP, e);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

}
