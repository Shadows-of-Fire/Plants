package shadows.plants2.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import shadows.placebo.Placebo;
import shadows.placebo.client.IHasModel;
import shadows.placebo.itemblock.ItemBlockBase;
import shadows.placebo.util.PlaceboUtil;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.Plants2;
import shadows.plants2.data.PlantConstants;
import shadows.plants2.data.enums.TheBigBookOfEnums.Vines;
import shadows.plants2.util.PlantUtil;

public class BlockCustomVine extends BlockVine implements IHasModel {

	private final StackPrimer[] drops;
	private final Vines vine;
	public static final PropertyEnum<Vines> VINE = PropertyEnum.create("zvine", Vines.class);

	public BlockCustomVine(String name, Vines vine, StackPrimer... drops) {
		setRegistryName(name);
		setTranslationKey(Plants2.MODID + "." + name);
		setCreativeTab(PlantConstants.TAB);
		setSoundType(SoundType.PLANT);
		setHardness(0.2F);
		this.drops = drops;
		this.vine = vine;
		setDefaultState(getDefaultState().withProperty(VINE, vine));
		Plants2.INFO.getBlockList().add(this);
		Plants2.INFO.getItemList().add(new ItemBlockBase(this));
		PlantUtil.VINES.add(this);
		vine.set(this);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("vines", this, 0, "zvine=" + vine.getName());
		Placebo.PROXY.useRenamedMapper(this, "vines");
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
		for (StackPrimer p : drops)
			if (world.rand.nextInt(4) == 0) spawnAsEntity(world, pos, p.genStack());
		super.harvestBlock(world, player, pos, state, te, stack);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { UP, NORTH, EAST, SOUTH, WEST, VINE });
	}

	@Override
	public boolean canAttachTo(World world, BlockPos pos, EnumFacing facing) {
		Block block = world.getBlockState(pos.up()).getBlock();
		return this.isAcceptableNeighbor(world, pos.offset(facing.getOpposite()), facing) && (block == Blocks.AIR || block == this || this.isAcceptableNeighbor(world, pos.up(), EnumFacing.UP));
	}

	protected boolean isAcceptableNeighbor(World world, BlockPos pos, EnumFacing facing) {
		IBlockState iblockstate = world.getBlockState(pos);
		return iblockstate.getBlockFaceShape(world, pos, facing) == BlockFaceShape.SOLID && !isExceptBlockForAttaching(iblockstate.getBlock());
	}

}
