package shadows.plants.block.internal.cosmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shadows.plants.block.BushBase;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.util.Config;
import shadows.plants.util.Data;

public class BlockHarvestable extends BushBase {

	public static final PropertyBool FRUIT = PropertyBool.create("fruit");
	protected Item cropItem;
	protected Item cropItem2 = null;
	protected int meta;
	protected float tempmax;
	protected float tempmin;

	public BlockHarvestable(String name, ItemStack crop, EnumTempZone zone) {
		super(name, EnumModule.COSMETIC, null);
		setDefaultState(this.blockState.getBaseState().withProperty(FRUIT, false));
		setTickRandomly(true);
		cropItem = crop.getItem();
		meta = crop.getMetadata();
		tempmax = zone.getMax();
		tempmin = zone.getMin();
	}

	public BlockHarvestable(String name, Item crop, EnumTempZone zone) {
		this(name, new ItemStack(crop), zone);
	}

	public BlockHarvestable(String name, Item crop, Item crop2, EnumTempZone zone) {
		this(name, new ItemStack(crop), zone);
		cropItem2 = crop2;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player.getHeldItemMainhand() == Data.EMPTYSTACK) {
			if (state.getValue(FRUIT)) {
				Block.spawnAsEntity(world, pos, new ItemStack(cropItem, 1, meta));
				if (cropItem2 != null)
					Block.spawnAsEntity(world, pos, new ItemStack(cropItem2, 1, meta));
				world.setBlockState(pos, this.getDefaultState());
				return true;
			}
		}
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta == 0)
			return this.blockState.getBaseState().withProperty(FRUIT, false);
		return this.blockState.getBaseState().withProperty(FRUIT, true);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if (state.getValue(FRUIT))
			return 1;
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FRUIT });
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);

		if (world.getLightFromNeighbors(pos.up()) > 5) {
			if (!state.getValue(FRUIT)) {

				if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt((15)) <= 2)) {
					world.setBlockState(pos, state.withProperty(FRUIT, true), 2);
					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
				}
			}
		}
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = getActualDrops(world, pos, state, fortune);
		if (!Config.needShears && state.getValue(FRUIT))
			list.add(new ItemStack(cropItem, 1, meta));
		if (!Config.needShears)
			return list;
		else if (Config.needShears && state.getValue(FRUIT))
			return Arrays.asList(new ItemStack(cropItem, 1, meta));
		else
			return new ArrayList<ItemStack>();
	}

	@Override
	public float getTempMax(IBlockState state) {
		return tempmax;
	}

	@Override
	public float getTempMin(IBlockState state) {
		return tempmin;
	}

}
