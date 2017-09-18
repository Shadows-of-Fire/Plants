package shadows.plants2.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnumBush;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.data.StackPrimer;
import shadows.plants2.data.enums.IHarvestableEnum;
import shadows.plants2.util.PlantUtil;

public class BlockEnumHarvestBush<E extends Enum<E> & IHarvestableEnum> extends BlockEnumBush<E> implements IGrowable {

	public static final PropertyBool FRUIT = PropertyBool.create("fruit");

	public BlockEnumHarvestBush(String name, EnumPlantType type, Class<E> enumClass, int predicate) {
		super(name, type, enumClass, predicate);
		setTickRandomly(true);
		setDefaultState(getDefaultState().withProperty(FRUIT, false));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && canGrow(world, pos, state, false) && rand.nextInt(Config.harvestGrowthChance) == 0) grow(world, rand, pos, state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("harvestables", this, i, "fruit=true,inventory=true," + property.getName() + "=" + types.get(i).getName());
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("harvestables"));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FRUIT, false);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!Config.harvests || !state.getValue(FRUIT)) return false;
		for (StackPrimer s : state.getValue(property).getDrops()) {
			ItemStack iS = s.genStack();
			if (!player.addItemStackToInventory(iS)) {
				if (!world.isRemote) Block.spawnAsEntity(world, pos, iS);
			}
		}
		world.setBlockState(pos, state.withProperty(FRUIT, false));
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		int k = meta % 2;
		if (k == 0) return this.getDefaultState().withProperty(FRUIT, true).withProperty(property, types.get(getActualMeta(meta)));
		if (k == 1) return this.getDefaultState().withProperty(FRUIT, false).withProperty(property, types.get(getActualMeta(meta)));
		else return null;
	}

	public static int getActualMeta(int meta) {
		int k = meta % 2;
		float j = ((float) meta) / 2F;
		if (k == 0) return (int) j;
		if (k == 1) return (int) (j - .5);
		else return 0;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		boolean k = state.getValue(FRUIT);
		int j = state.getValue(property).ordinal() % 8;

		if (k) return (j * 2);
		if (!k) return (1 + j * 2);
		else return 0;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(property).ordinal() % 8;
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, Constants.INV, property, FRUIT);
	}

	@Override
	public List<ItemStack> getActualDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = super.getActualDrops(world, pos, state, fortune);
		if (Config.harvests && state.getValue(FRUIT)) for (StackPrimer s : state.getValue(property).getDrops())
			drops.add(s.genStack());
		return drops;
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return !state.getValue(FRUIT);
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(world, pos, state, false);
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state.withProperty(FRUIT, true));
	}

	@Override
	protected int getMaxEnumValues() {
		return 8;
	}

	@Override
	protected void addStatesToList() {
		List<IBlockState> list = PlantUtil.TYPE_TO_STATES.get(type);
		for (E e : types) {
			list.add(getStateFor(e).withProperty(FRUIT, true));
		}
	}

}
