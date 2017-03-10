package shadows.plants.block.internal.cosmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shadows.plants.common.EnumTempZone;
import shadows.plants.item.internal.cosmetic.ItemBlockDoubleHarvestable;
import shadows.plants.util.Config;
import shadows.plants.util.Data;

public class BlockDoubleHarvestable extends BlockHarvestable {

	public static final PropertyBool UPPER = PropertyBool.create("upper");

	public BlockDoubleHarvestable(String name, ItemStack crop, EnumTempZone zone) {
		super(name, crop, zone);
		setDefaultState(this.blockState.getBaseState().withProperty(UPPER, true).withProperty(FRUIT, false));
		GameRegistry.register(new ItemBlockDoubleHarvestable(this));
	}

	public BlockDoubleHarvestable(String name, Item crop, EnumTempZone zone) {
		this(name, new ItemStack(crop), zone);
	}

	public BlockDoubleHarvestable(String name, Item crop, Item crop2, EnumTempZone zone) {
		this(name, new ItemStack(crop), zone);
		cropItem2 = crop2;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			 EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player.getHeldItemMainhand() == Data.EMPTYSTACK) {
			if (state.getValue(FRUIT) && state.getValue(UPPER)) {
				Block.spawnAsEntity(world, pos, new ItemStack(cropItem, 1, meta));
				if (cropItem2 != null)
					Block.spawnAsEntity(world, pos, new ItemStack(cropItem2, 1, meta));
				world.setBlockState(pos, getDefaultState().withProperty(UPPER, true).withProperty(FRUIT, false));
				world.setBlockState(pos.down(),
						getDefaultState().withProperty(UPPER, false).withProperty(FRUIT, false));
				return true;
			} else if (state.getValue(FRUIT) && !state.getValue(UPPER)) {
				Block.spawnAsEntity(world, pos, new ItemStack(cropItem, 1, meta));
				if (cropItem2 != null)
					Block.spawnAsEntity(world, pos, new ItemStack(cropItem2, 1, meta));
				world.setBlockState(pos.up(), getDefaultState().withProperty(UPPER, true).withProperty(FRUIT, false));
				world.setBlockState(pos, getDefaultState().withProperty(UPPER, false).withProperty(FRUIT, false));
				return true;
			}
		}
		return false;
	}

	@Override
	protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(world, pos, state)) {
			boolean flag = state.getValue(UPPER);
			if (flag && !(world.getBlockState(pos.down()) == this)) {
				BlockPos blockpos = flag ? pos : pos.up();
				BlockPos blockpos1 = flag ? pos.down() : pos;
				Block block = (Block) (flag ? this : world.getBlockState(blockpos).getBlock());
				Block block1 = (Block) (flag ? world.getBlockState(blockpos1).getBlock() : this);

				if (!flag)
					this.dropBlockAsItemWithChance(world, pos, state, 1.0F, 0);
				;

				if (block == this) {
					world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
				}

				if (block1 == this) {
					world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
				}
			}
			if (!flag && !(world.getBlockState(pos.up()) == this)) {
				BlockPos blockpos = flag ? pos : pos.up();
				BlockPos blockpos1 = flag ? pos.down() : pos;
				Block block = (Block) (flag ? this : world.getBlockState(blockpos).getBlock());
				Block block1 = (Block) (flag ? world.getBlockState(blockpos1).getBlock() : this);

				if (!flag)
					this.dropBlockAsItemWithChance(world, pos, state, 1.0F, 0);
				;

				if (block == this) {
					world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
				}

				if (block1 == this) {
					world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
				}
			}

		}
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		if (state.getBlock() != this) {
			System.out.println("Case one being evaluated");
			return super.canBlockStay(world, pos, state);
		}
		if (state.getValue(UPPER)) {
			if (Config.debug)
				System.out.println("Case two being evaluated");
			return world.getBlockState(pos.down()).getBlock() == this;
		} else if (!state.getValue(UPPER)) {
			if (Config.debug)
				System.out.println("Case three being evaluated");
			return soil.contains(world.getBlockState(pos.down()).getBlock())
					&& world.getBlockState(pos.up()).getBlock() == this;
		} else {
			if (Config.debug)
				System.out.println("Case four being evaluated");
			return false;
		}
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Nullable
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (state.getValue(UPPER)) {
			return null;
		} else {
			return Item.getItemFromBlock(this);
		}
	}

	public void placeAt(World world, BlockPos lowerPos, int meta, int flags) {
		world.setBlockState(lowerPos, this.getDefaultState().withProperty(UPPER, false).withProperty(FRUIT, false),
				flags);
		world.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(UPPER, true).withProperty(FRUIT, false),
				flags);
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow
	 * post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos.up(), state.withProperty(UPPER, true), 2);
	}

	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state,
			@Nullable TileEntity te, @Nullable ItemStack stack) {

		super.harvestBlock(worldIn, player, pos, state, te, stack);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (state.getValue(UPPER)) {
			if (world.getBlockState(pos.down()).getBlock() == this) {
				if (player.capabilities.isCreativeMode) {
					world.setBlockToAir(pos.down());
				} else {
					world.destroyBlock(pos.down(), true);

					if (world.isRemote) {
						world.setBlockToAir(pos.down());
					}
				}
			}
		} else if (world.getBlockState(pos.up()).getBlock() == this) {
			world.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
		}

		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,
			boolean willHarvest) {
		// Forge: Break both parts on the client to prevent the top part
		// flickering as default type for a few frames.
		if (state.getBlock() == this && !state.getValue(UPPER) && world.getBlockState(pos.up()).getBlock() == this)
			world.setBlockToAir(pos.up());
		return world.setBlockToAir(pos);
	}

	@Override
	public IBlockState getStateFromMeta(int meta2) {
		if (meta2 == 0)
			return getDefaultState();
		if (meta2 == 1)
			return blockState.getBaseState().withProperty(UPPER, false).withProperty(FRUIT, false);
		if (meta2 == 2)
			return blockState.getBaseState().withProperty(UPPER, true).withProperty(FRUIT, true);
		if (meta2 == 3)
			return blockState.getBaseState().withProperty(UPPER, false).withProperty(FRUIT, true);
		else
			return null;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int k = 0;
		if (!state.getValue(UPPER))
			++k;
		if (state.getValue(FRUIT)) {
			++k;
			++k;
		}
		return k;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FRUIT, UPPER });
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);

		if (world.getLightFromNeighbors(pos.up()) > 5 && !state.getValue(FRUIT) && state.getValue(UPPER)) {
			if (rand.nextInt((15)) <= 2) {
				world.setBlockState(pos, state.withProperty(FRUIT, true).withProperty(UPPER, true), 3);
				world.setBlockState(pos.down(), state.withProperty(FRUIT, true).withProperty(UPPER, false), 3);
			}
		}
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		if (world.getBlockState(pos).getBlock() == this) {
			if (world.getBlockState(pos).getValue(UPPER))
				return getActualDrops(world, pos, world.getBlockState(pos).withProperty(UPPER, false), fortune);
			if (!world.getBlockState(pos).getValue(UPPER))
				return getActualDrops(world, pos, world.getBlockState(pos), fortune);
		}
		System.out.println("Oh something is very very wrong");
		return getDrops(world, pos, getDefaultState(), fortune);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = getActualDrops(world, pos, state, fortune);
		if (!Config.needShears && state.getValue(FRUIT) && !state.getValue(UPPER))
			list.add(new ItemStack(cropItem, 1, meta));
		if (!Config.needShears)
			return list;
		else if (Config.needShears && state.getValue(FRUIT) && !state.getValue(UPPER))
			return Arrays.asList(new ItemStack(cropItem, 1, meta));
		else
			return new ArrayList<ItemStack>();
	}

}
