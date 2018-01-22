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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.IShearable;
import shadows.placebo.Placebo;
import shadows.placebo.block.base.BlockEnum;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.Plants2;
import shadows.plants2.block.BlockEnumHarvestBush;
import shadows.plants2.data.enums.TheBigBookOfEnums.BushSet;
import shadows.plants2.init.ModRegistry;

public class BlockBushLeaves extends BlockEnum<BushSet> implements IGrowable, IShearable {

	public BlockBushLeaves(BushSet type) {
		super(type.getName() + "_bush", Material.LEAVES, SoundType.PLANT, 0.2F, 0.0F, type, Plants2.INFO);
		setTickRandomly(true);
		setLightOpacity(1);
		setDefaultState(getDefaultState().withProperty(BlockEnumHarvestBush.FRUIT, false));
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("bush", this, 0, "fruit=true,type=" + type.getName());
		Placebo.PROXY.useRenamedMapper(this, "bush", ",type=" + type.getName());
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile.plants2.bush";
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND || type.getHarvest().isEmpty()) return false;
		else if (state.getValue(BlockEnumHarvestBush.FRUIT)) {
			StackPrimer s = type.getHarvest();
			if (!player.addItemStackToInventory(s.genStack())) if (!world.isRemote) Block.spawnAsEntity(world, pos, s.genStack());
			world.setBlockState(pos, state.withProperty(BlockEnumHarvestBush.FRUIT, false));
			return true;
		}
		return false;
	}

	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextFloat() < 0.35F && canGrow(world, pos, state, false)) grow(world, rand, pos, state);
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
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockEnumHarvestBush.FRUIT) ? 0 : 1;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(BlockEnumHarvestBush.FRUIT, meta == 0);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockEnumHarvestBush.FRUIT);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> k = new ArrayList<ItemStack>();
		if (state.getValue(BlockEnumHarvestBush.FRUIT)) k.add(type.getHarvest().genStack());
		if (ThreadLocalRandom.current().nextInt(5) == 0) k.add(new ItemStack(ModRegistry.BUSHLING.getBlock(type)));
		return k;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		List<ItemStack> k = getDrops(world, pos, state, fortune);
		k.add(new ItemStack(state.getBlock(), 1, damageDropped(state)));
		if (ThreadLocalRandom.current().nextInt(15) == 0) {
			k.clear();
			k.add(new ItemStack(ModRegistry.BUSHLING.getBlock(type)));
		}
		return k;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return !type.getHarvest().isEmpty() && !state.getValue(BlockEnumHarvestBush.FRUIT);
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state.withProperty(BlockEnumHarvestBush.FRUIT, true));
	}

}
