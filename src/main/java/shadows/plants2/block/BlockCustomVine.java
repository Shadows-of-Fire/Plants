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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.client.IHasModel;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.Constants;
import shadows.plants2.data.StackPrimer;
import shadows.plants2.data.enums.TheBigBookOfEnums.Vines;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.itemblock.ItemBlockBase;
import shadows.plants2.util.PlantUtil;

public class BlockCustomVine extends BlockVine implements IHasModel {

	private final StackPrimer[] drops;
	private final Vines vine;
	public static final PropertyEnum<Vines> VINE = PropertyEnum.create("zvine", Vines.class);

	public BlockCustomVine(String name, Vines vine, StackPrimer... drops) {
		setRegistryName(name);
		setUnlocalizedName(Constants.MODID + "." + name);
		setCreativeTab(ModRegistry.TAB);
		setSoundType(SoundType.PLANT);
		setHardness(0.2F);
		this.drops = drops;
		this.vine = vine;
		setDefaultState(getDefaultState().withProperty(VINE, vine));
		ModRegistry.BLOCKS.add(this);
		ModRegistry.ITEMS.add(new ItemBlockBase(this));
		PlantUtil.VINES.add(this);
	}

	@Override @SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlantUtil.sMRL("vines", this, 0, "zvine=" + vine.getName());
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("vines"));
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
		for (StackPrimer p : drops)
			if (world.rand.nextInt(4) == 0)
				spawnAsEntity(world, pos, p.genStack());
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
