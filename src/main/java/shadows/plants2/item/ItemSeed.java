package shadows.plants2.item;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import shadows.placebo.client.IHasModel;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.plants2.Plants2;
import shadows.plants2.block.BlockEnumCrop;
import shadows.plants2.data.Constants;

public class ItemSeed<E extends Enum<E> & IPropertyEnum> extends ItemSeeds implements IHasModel {

	private EnumPlantType type;
	private IBlockState crop = null;
	private String cropName;
	private E cropVariant;

	public ItemSeed(String name, EnumPlantType type, String blockName, E variant) {
		super(null, null);
		this.type = type;
		cropName = blockName;
		cropVariant = variant;
		setRegistryName(name);
		setUnlocalizedName(Constants.MODID + "." + name);
		setCreativeTab(Constants.TAB);
		Plants2.INFO.getItemList().add(this);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		IBlockState state = world.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, this) && world.isAirBlock(pos.up())) {
			world.setBlockState(pos.up(), getPlant(world, pos));
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		} else return EnumActionResult.FAIL;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return type;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return crop == null ? crop = defineCropState() : crop;
	}

	@SuppressWarnings("unchecked") //Java u suck
	private IBlockState defineCropState() {
		BlockEnumCrop<E> b = (BlockEnumCrop<E>) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(cropName));
		return b.getStateFor(cropVariant).withProperty(BlockCrops.AGE, 0);
	}

}
