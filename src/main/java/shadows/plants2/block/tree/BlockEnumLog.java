package shadows.plants2.block.tree;

import net.minecraft.block.BlockLog;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import shadows.placebo.Placebo;
import shadows.placebo.block.base.BlockEnum;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class BlockEnumLog<E extends Enum<E> & ITreeEnum<E>> extends BlockEnum<E> implements IHasRecipe {

	public static final PropertyEnum<EnumAxis> AXIS = BlockLog.LOG_AXIS;

	public BlockEnumLog(SoundType s, float hard, float res, E type) {
		super(type.getName() + "_log", Material.WOOD, s, hard, res, type, Plants2.INFO);
		this.setDefaultState(getBlockState().getBaseState().withProperty(AXIS, EnumAxis.Y));
	}

	public BlockEnumLog(E type) {
		this(SoundType.WOOD, 2F, 1F, type);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AXIS);
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
	public String getUnlocalizedName() {
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
		return getDefaultState().withProperty(AXIS, EnumAxis.values()[meta]);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AXIS).ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("logs", this, 0, AXIS.getName() + "=" + EnumAxis.Y.getName() + ",type=" + type.getName());
		Placebo.PROXY.useRenamedMapper(this, "logs");
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		GameRegistry.addSmelting(new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.COAL, 1, 1), 2);
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

}
