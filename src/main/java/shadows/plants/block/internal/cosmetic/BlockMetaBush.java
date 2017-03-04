package shadows.plants.block.internal.cosmetic;

import java.util.List;
import java.util.Map;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.BushBase;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.common.IMetaPlant;
import shadows.plants.item.internal.cosmetic.ItemBlockMetaBush;

public class BlockMetaBush extends BushBase implements IMetaPlant {

	public static final PropertyInteger BASICMETA = PropertyInteger.create("basicmeta", 0, 15);
	public Map<Integer, EnumTempZone> tempmap;
	private int max;

	public BlockMetaBush(String name, Map<Integer, EnumTempZone> map, int maxmeta) {
		super(name, EnumModule.COSMETIC, null);
		setDefaultState(this.blockState.getBaseState().withProperty(BASICMETA, 0));
		tempmap = map;
		GameRegistry.register(new ItemBlockMetaBush(this));
		max = maxmeta;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (int i = 0; i < 16; ++i) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BASICMETA, meta);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BASICMETA);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { BASICMETA });
	}

	@Override
	public float getTempMax(IBlockState state) {
		int k = state.getValue(BASICMETA);
		return tempmap.get(k).getMax();
	}

	@Override
	public float getTempMin(IBlockState state) {
		int k = state.getValue(BASICMETA);
		return tempmap.get(k).getMin();
	}

	@Override
	public int getMaxData() {
		return max;
	}

	@Override
	public int getMetaPropValue(IBlockState state) {
		return state.getValue(BASICMETA);
	}

}
