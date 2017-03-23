package shadows.plants.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.common.EnumModule;
import shadows.plants.common.IModularThing;
import shadows.plants.common.ITemperaturePlant;
import shadows.plants.util.Config;
import shadows.plants.util.Data;

public abstract class BushBase extends BlockBush implements IModularThing, ITemperaturePlant, IShearable {

	private EnumModule module;
	public List<Block> soil = new ArrayList<Block>();
	public static final AxisAlignedBB BUSHBOX = new AxisAlignedBB(0.125D, 0D, 0.125D, 0.875D, 0.75D, 0.875D);
	private final EnumPlantType plantType;

	public BushBase(String name, EnumModule type, @Nullable Block[] blocks) {
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		disableStats();
		setResistance(150F);
		module = type;
		if (blocks != null) {
			for (Block soils : blocks) {
				soil.add(soils);
			}
		} else if(blocks == null) {
			soil.add(Blocks.GRASS_PATH);
			soil.add(Blocks.GRASS);
			soil.add(Blocks.DIRT);
		}
		plantType = EnumPlantType.Plains;
	}
	
	public BushBase(String name, EnumModule type, @Nullable Block[] blocks, EnumPlantType plantType) {
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		disableStats();
		setResistance(150F);
		module = type;
		if (blocks != null) {
			for (Block soils : blocks) {
				soil.add(soils);
			}
		} else if(blocks == null) {
			soil.add(Blocks.GRASS_PATH);
			soil.add(Blocks.GRASS);
			soil.add(Blocks.DIRT);
		}
		this.plantType = plantType;
	}

	public BushBase(EnumModule type, String name, @Nonnull Block soilIn) {
		setRegistryName(name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		disableStats();
		module = type;
		soil.add(soilIn);
		plantType = EnumPlantType.Plains;
	}

	@Override
	public EnumModule getType() {
		return module;
	}
	
    @Override
    public EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
    	return this.plantType;
    }

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		return soil.contains(state.getBlock());
	}

	@Override
	protected boolean canSustainBush(IBlockState state) {
		return soil.contains(state.getBlock());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.NONE;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BUSHBOX;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return Config.needShears;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return getActualDrops(world, pos, world.getBlockState(pos), fortune);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (!Config.needShears)
			return getActualDrops(world, pos, state, fortune);
		else
			return new ArrayList<ItemStack>();
	}

	public List<ItemStack> getActualDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new ArrayList<ItemStack>();

		Random rand = world instanceof World ? ((World) world).rand : RANDOM;

		int count = quantityDropped(state, fortune, rand);
		for (int i = 0; i < count; i++) {
			Item item = this.getItemDropped(state, rand, fortune);
			if (item != null) {
				ret.add(new ItemStack(item, 1, this.damageDropped(state)));
			}
		}
		return ret;
	}

}
