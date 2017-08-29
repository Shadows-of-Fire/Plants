package shadows.plants2.block;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnumBush;
import shadows.plants2.block.base.IEnumBlock;
import shadows.plants2.client.IHasModel;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.compat.BotaniaFlowerpot;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.TheBigBookOfEnums;
import shadows.plants2.data.enums.TheBigBookOfEnums.AllPlants;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.state.FlowerpotBlockState.FlowerpotStateContainer;
import shadows.plants2.tile.TileFlowerpot;
import shadows.plants2.util.PlantUtil;

public class BlockFlowerpot extends BlockFlowerPot implements IEnumBlock<AllPlants>, IHasModel {

	public static PropertyEnum<AllPlants> PROP = TileFlowerpot.PROPERTY;
	private final BlockStateContainer container;

	public BlockFlowerpot() {
		setRegistryName("minecraft", "flower_pot");
		setUnlocalizedName(Constants.MODID + ".flowerpot");
		setCreativeTab(CreativeTabs.DECORATIONS);
		container = createStateContainer();
		setDefaultState(container.getBaseState().withProperty(PROP, AllPlants.NONE));
		ModRegistry.BLOCKS.add(this);
		ModRegistry.ITEMS.add(createItemBlock().setRegistryName(getRegistryName()));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		return state.withProperty(PROP, ((TileFlowerpot) tile).getFlower());
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		super.onBlockHarvested(world, pos, state, player);
		if (player.capabilities.isCreativeMode) {
			TileFlowerpot pot = this.getTileEntity(world, pos);
			if (pot != null) {
				pot.setFlower(AllPlants.NONE);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlantUtil.sMRL("flowerpot", this, 0, "inventory");
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("flowerpot"));
	}

	@Override
	public int getLightValue(IBlockState state) {
		return state.getValue(PROP).hasLight() ? 15 : 0;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(hand);
		TileFlowerpot pot = this.getTileEntity(world, pos);

		if (pot == null)
			return false;

		ItemStack stack = pot.getFlowerItemStack();

		if (stack.isEmpty()) {
			if (!canTryPot(held))
				return false;
			else {
				Block block = Block.getBlockFromItem(held.getItem());
				IBlockState toUse = block.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, held.getMetadata(), player, hand);

				AllPlants plant;

				String name = "none";
				if (block instanceof BlockEnumBush<?>) {
					name = ((IEnumBlock<?>) block).getValue(toUse).getName();
				} else if (block == Blocks.RED_FLOWER || block == Blocks.YELLOW_FLOWER)
					name = toUse.getValue(((BlockFlower) block).getTypeProperty()).getName();
				else if (block == Blocks.RED_MUSHROOM)
					name = "red_mushroom";
				else if (block == Blocks.BROWN_MUSHROOM)
					name = "brown_mushroom";
				else if (block == Blocks.SAPLING)
					name = toUse.getValue(BlockSapling.TYPE).getName() + "_sapling";
				else if (block == Blocks.DEADBUSH)
					name = "dead_bush";
				else if (block == Blocks.CACTUS)
					name = "cactus";
				else if (block == Blocks.TALLGRASS && toUse.getValue(BlockTallGrass.TYPE) == BlockTallGrass.EnumType.FERN)
					name = "fern";
				else if (Loader.isModLoaded(Constants.BOTANIA_ID))
					name = BotaniaFlowerpot.handleFlowerpot(toUse);

				plant = TheBigBookOfEnums.NAME_TO_ENUM.get(name);

				if (plant == AllPlants.NONE || plant == null)
					return false;
				pot.setFlower(plant);
				pot.setItemStack(new ItemStack(held.getItem(), 1, held.getMetadata()));
				world.setBlockState(pos, getDefaultState().withProperty(PROP, pot.getFlower()));
				if (!player.capabilities.isCreativeMode)
					held.shrink(1);
			}
		} else {
			if (player.addItemStackToInventory(pot.getFlowerItemStack())) {
				pot.setFlower(AllPlants.NONE);
				pot.setItemStack(ItemStack.EMPTY);
				world.setBlockState(pos, getDefaultState().withProperty(PROP, pot.getFlower()));
			}
		}
		pot.markDirty();
		world.notifyBlockUpdate(pos, state, state, 3);
		return true;
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
		if (Loader.isModLoaded(Constants.BOTANIA_ID) && state.getValue(PROP).getName().startsWith("b_"))
			BotaniaFlowerpot.randomDisplayTick(state, world, pos, rand);
		if (rand.nextFloat() < 0.1F) {
			AllPlants p = state.getValue(PROP);
			if (p == AllPlants.ASH || p == AllPlants.BLAZE)
				world.spawnParticle(p == AllPlants.ASH ? EnumParticleTypes.SMOKE_LARGE : EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.4, pos.getZ() + 0.5, getDouble(rand), 0.05, getDouble(rand));
		}
	}

	public static double getDouble(Random rand) {
		return MathHelper.nextDouble(rand, -0.05, 0.05);
	}

	public boolean canTryPot(ItemStack toPlant) {
		return toPlant.getItem() instanceof ItemBlock;
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
	public List<AllPlants> getTypes() {
		return Arrays.asList(AllPlants.values());
	}

	@Override
	public Item createItemBlock() {
		return new ItemBlock(this);
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
	public PropertyEnum<AllPlants> getProperty() {
		return PROP;
	}

	@Nullable
	private TileFlowerpot getTileEntity(IBlockAccess world, BlockPos pos) {
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity instanceof TileFlowerpot ? (TileFlowerpot) tileentity : null;
	}

	@Override
	public IBlockState getStateFor(AllPlants e) {
		return this.getDefaultState().withProperty(PROP, e);
	}

	@Override
	public boolean placeStateAt(IBlockState state, World world, BlockPos pos) {
		return world.setBlockState(pos, state);
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
