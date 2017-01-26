package shadows.plants.block.internal.cosmetic;

import java.util.List;

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
import shadows.plants.item.internal.cosmetic.ItemBlockMetaBush;



public class BlockMetaBush extends BushBase{

	public static final PropertyInteger BASICMETA = PropertyInteger.create("basicmeta", 0, 15);
	public static String name2;
	
	public BlockMetaBush(String name) {
		super(name, EnumModule.COSMETIC, null);
		setDefaultState(this.blockState.getBaseState().withProperty(BASICMETA, 0));
		GameRegistry.register(new ItemBlockMetaBush(this));
		name2 = name;
	}
	
	@Override
	public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for (int i = 0; i < 16; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BASICMETA, meta);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(BASICMETA);
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BASICMETA});
    }
    
    public static String getName(BlockMetaBush bush){
    	return BlockMetaBush.name2;
    }
    
    public static int getBlockNumber(BlockMetaBush block){
    	return Integer.parseInt(block.getRegistryName().getResourcePath().substring(9));
    }
	
	

}
