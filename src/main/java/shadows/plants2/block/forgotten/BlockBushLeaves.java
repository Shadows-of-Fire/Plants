package shadows.plants2.block.forgotten;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.oredict.OreDictionary;
import shadows.placebo.block.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.Plants2;
import shadows.plants2.block.BlockEnumHarvestBush;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.init.ModRegistry;

public class BlockBushLeaves extends BlockEnum<BushSet> implements IGrowable, IShearable, IHasRecipe {

	public BlockBushLeaves() {
		super("bush", Material.LEAVES, SoundType.PLANT, 0.2F, 0.0F, BushSet.class, Plants2.INFO);
		setTickRandomly(true);
		setLightOpacity(1);
		setDefaultState(getDefaultState().withProperty(getProperty(), BushSet.BLACKBERRY).withProperty(BlockEnumHarvestBush.FRUIT, false));
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		OreDictionary.registerOre("treeLeaves", new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < getProperty().getAllowedValues().size(); i++) {
			PlaceboUtil.sMRL(this, i, "fruit=true," + "type=" + BushSet.values()[i].getName());
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND || state.getValue(getProperty()).getHarvest().isEmpty()) return false;
		else if (state.getValue(BlockEnumHarvestBush.FRUIT)) {
			StackPrimer s = state.getValue(getProperty()).getHarvest();
			if (!player.addItemStackToInventory(s.genStack())) if (!world.isRemote) Block.spawnAsEntity(world, pos, s.genStack());
			world.setBlockState(pos, state.withProperty(BlockEnumHarvestBush.FRUIT, false));
			return true;
		}
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(getProperty()).ordinal();
	}

	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (canGrow(world, pos, state, false)) 
		{
			boolean couldGrow = (rand.nextFloat() < 0.35F);
			if (ForgeHooks.onCropsGrowPre(world, pos, state, couldGrow))
			{
				grow(world, rand, pos, state);
				ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
			}
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int i = 0; i < BushSet.values().length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(getProperty()).ordinal() + (state.getValue(BlockEnumHarvestBush.FRUIT) ? 6 : 0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(getProperty(), BushSet.values()[meta % 6]).withProperty(BlockEnumHarvestBush.FRUIT, meta >= 6);
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, getProperty(), BlockEnumHarvestBush.FRUIT);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> k = new ArrayList<ItemStack>();
		if (state.getValue(BlockEnumHarvestBush.FRUIT)) k.add(state.getValue(getProperty()).getHarvest().genStack());
		if (ThreadLocalRandom.current().nextInt(5) == 0) k.add(new ItemStack(ModRegistry.BUSHLING, 1, damageDropped(state)));
		return k;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		List<ItemStack> k = getDrops(world, pos, state, fortune);
		k.add(new ItemStack(state.getBlock(), 1, damageDropped(state)));
		if (ThreadLocalRandom.current().nextInt(15) == 0) {
			k.clear();
			k.add(new ItemStack(ModRegistry.BUSHLING, 1, damageDropped(state)));
		}
		return k;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return !state.getValue(getProperty()).getHarvest().isEmpty() && !state.getValue(BlockEnumHarvestBush.FRUIT);
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state.withProperty(BlockEnumHarvestBush.FRUIT, true));
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 30;
	}

}
