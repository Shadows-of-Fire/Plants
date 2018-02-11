package shadows.plants2.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.Placebo;
import shadows.placebo.interfaces.IHarvestableEnum;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.data.PlantConstants;

public class BlockEnumDoubleHarvestBush<E extends Enum<E> & IHarvestableEnum> extends BlockEnumHarvestBush<E> {

	public static final PropertyBool UPPER = BlockEnumDoubleFlower.UPPER;

	public BlockEnumDoubleHarvestBush(String name, EnumPlantType type, Class<E> enumClass, int predicate) {
		super(name, type, enumClass, predicate);
		setDefaultState(getDefaultState().withProperty(UPPER, false));
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
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlaceboUtil.sMRL("double_harvest", this, i, "fruit=true," + property.getName() + "=" + types.get(i).getName() + ",upper=true,zinventory=true");
		}
		Placebo.PROXY.useRenamedMapper(this, "double_harvest");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!PlantConfig.harvests) return false;
		if (!world.isRemote) {
			StackPrimer[] drops = types.get(state.getValue(property).ordinal() % 4).getDrops();
			if (state.getValue(FRUIT) && state.getValue(UPPER)) {
				for (StackPrimer s : drops) {
					ItemStack i = s.genStack();
					if (!player.addItemStackToInventory(i)) if (!world.isRemote) Block.spawnAsEntity(world, pos, i);
				}
				world.setBlockState(pos, state.withProperty(UPPER, true).withProperty(FRUIT, false));
				world.setBlockState(pos.down(), state.withProperty(UPPER, false).withProperty(FRUIT, false));
				return true;
			} else if (state.getValue(FRUIT) && !state.getValue(UPPER)) {
				for (StackPrimer s : drops) {
					ItemStack i = s.genStack();
					if (!player.addItemStackToInventory(i)) if (!world.isRemote) Block.spawnAsEntity(world, pos, i);
				}
				world.setBlockState(pos.up(), getDefaultState().withProperty(UPPER, true).withProperty(FRUIT, false));
				world.setBlockState(pos, getDefaultState().withProperty(UPPER, false).withProperty(FRUIT, false));
				return true;
			}
		}
		return state.getValue(FRUIT);
	}

	@Override
	protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(world, pos, state)) {
			boolean flag = state.getValue(UPPER);
			BlockPos otherPos = flag ? pos.down() : pos.up();
			world.destroyBlock(pos, !flag);
			world.destroyBlock(otherPos, flag);
		}
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		if (state.getBlock() != this) return super.canBlockStay(world, pos, state);
		if (state.getValue(UPPER)) {
			return world.getBlockState(pos.down()).getBlock() == this;
		} else {
			IBlockState iblockstate = world.getBlockState(pos.up());
			return iblockstate.getBlock() == this && super.canBlockStay(world, pos, iblockstate);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (state.getValue(UPPER)) {
			return null;
		} else {
			return Item.getItemFromBlock(this);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos.up(), state.withProperty(UPPER, true), 2);
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
			world.setBlockToAir(pos.up());
		}
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if (state.getBlock() == this && !state.getValue(UPPER) && world.getBlockState(pos.up()).getBlock() == this) world.setBlockToAir(pos.up());
		return world.setBlockToAir(pos);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = state.getValue(property).ordinal() % 4;
		i *= 4;
		if (state.getValue(UPPER)) i++;
		if (state.getValue(FRUIT)) i += 2;
		return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		float i = meta;
		i /= 4;
		state = state.withProperty(property, types.get((int) i));
		if (i - 0.5F >= 0) {
			state = state.withProperty(FRUIT, true);
			i -= 0.5F;
		}
		if (i - 0.25F >= 0) state = state.withProperty(UPPER, true);

		return state;
	}

	@Override
	public PropertyBool getInvProperty() {
		return PlantConstants.ZINV;
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, FRUIT, UPPER, getInvProperty());
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);

		if (!world.isRemote && state.getValue(UPPER) && !state.getValue(FRUIT)) {
			if (rand.nextInt(PlantConfig.harvestGrowthChance) == 0) {
				world.setBlockState(pos, state.withProperty(FRUIT, true).withProperty(UPPER, true), 3);
				world.setBlockState(pos.down(), state.withProperty(FRUIT, true).withProperty(UPPER, false), 3);
			}
		}
	}

	@Override
	public boolean placeStateAt(IBlockState state, World world, BlockPos pos) {
		if (world.isAirBlock(pos.up()) && world.setBlockState(pos, state.withProperty(UPPER, false))) {
			this.onBlockPlacedBy(world, pos, state, null, null);
			return true;
		}
		return false;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state.withProperty(FRUIT, true));
		if (!state.getValue(UPPER)) world.setBlockState(pos.up(), state.withProperty(UPPER, true).withProperty(FRUIT, true));
		else world.setBlockState(pos.down(), state.withProperty(UPPER, false).withProperty(FRUIT, true));
	}

	@Override
	protected int getMaxEnumValues() {
		return 4;
	}

}
