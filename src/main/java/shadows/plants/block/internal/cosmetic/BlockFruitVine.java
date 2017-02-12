package shadows.plants.block.internal.cosmetic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shadows.plants.block.VineBase;
import shadows.plants.common.EnumModule;
import shadows.plants.common.EnumTempZone;
import shadows.plants.util.Config;
import shadows.plants.util.Data;

public class BlockFruitVine extends VineBase{

	ItemStack fruit;
	
	public BlockFruitVine(String name, ItemStack fruit_, EnumTempZone zone){
		super(name, EnumModule.COSMETIC, zone);
		fruit = fruit_;
	}

	@Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune){
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(this));
		return list;
    }
	
	@Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack){
		if(fruit != Data.EMPTYSTACK && world.rand.nextInt(Config.vineFruitChance) == 0) spawnAsEntity(world, pos, fruit);
		super.harvestBlock(world, player, pos, state, te, stack);
    }
	
}