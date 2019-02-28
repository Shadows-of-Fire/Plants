package shadows.plants2.block;

import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import shadows.placebo.Placebo;
import shadows.placebo.block.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.interfaces.ITreeEnum;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class BlockEnumLog<E extends Enum<E> & ITreeEnum> extends BlockEnum<E> implements IHasRecipe {

	public static final PropertyEnum<Axis> AXIS = PropertyEnum.create("axis", Axis.class);

	public BlockEnumLog(String name, SoundType s, float hard, float res, Class<E> enumClass, int predicate) {
		super(name, Material.WOOD, s, hard, res, enumClass, "type", (e) -> e.getPredicateIndex() == predicate, Plants2.INFO);
		setDefaultState(getBlockState().getBaseState().withProperty(property, types.get(0)).withProperty(AXIS, Axis.Y));
	}

	public BlockEnumLog(String name, Class<E> enumClass, int predicate) {
		this(name, SoundType.WOOD, 2F, 1F, enumClass, predicate);
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, AXIS);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5))) {
			for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
				IBlockState iblockstate = worldIn.getBlockState(blockpos);

				if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
					iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
				}
			}
		}
	}

	@Override
	public String getTranslationKey() {
		return "tile.plants2.log";
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(property, types.get(meta % 5)).withProperty(AXIS, Axis.values()[meta / 5]);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(property, types.get(MathHelper.clamp(meta, 0, types.size()))).withProperty(AXIS, facing.getAxis());
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(property).getMetadata();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = state.getValue(property).ordinal() % 5; // 0-4
		i += state.getValue(AXIS).ordinal() * 5; // 0-14
		return i;
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlaceboUtil.sMRL("logs", this, i, AXIS.getName() + "=" + EnumAxis.Y.getName() + "," + property.getName() + "=" + types.get(i).getName());
		}
		Placebo.PROXY.useRenamedMapper(this, "logs");
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		OreDictionary.registerOre("logWood", new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));
		GameRegistry.addSmelting(new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.COAL, 1, 1), 2);
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 5;
	}

}
