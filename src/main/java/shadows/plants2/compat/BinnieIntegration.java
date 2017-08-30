package shadows.plants2.compat;

import java.util.Locale;

import binnie.botany.genetics.Flower;
import binnie.botany.genetics.FlowerGenome;
import binnie.botany.modules.ModuleFlowers;
import binnie.botany.tile.FlowerRenderInfo;
import binnie.botany.tile.TileEntityFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import shadows.plants2.data.Constants;
import shadows.plants2.tile.TileFlowerpot;

public class BinnieIntegration {
	
	private static final TileEntityFlower F = new TileEntityFlower();
	
	public static int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tint) {
		
		TileFlowerpot pot = (TileFlowerpot) world.getTileEntity(pos);

		FlowerRenderInfo render = new FlowerRenderInfo(new Flower(pot.getFlowerItemStack().getTagCompound()), F);
		if (tint == 10)
			return render.getStem().getColor(render.isWilted());
		if (tint == 11)
			return render.getPrimary().getColor(render.isWilted());
		if (tint == 12)
			return render.getSecondary().getColor(render.isWilted());
		
		return -1;
	}
	

	public static class BotanyFlowerpot implements IFlowerpotHandler{

		@Override
		public String handleFlowerpot(IBlockState state, ItemStack stack) {
			if(stack.getItem() == ModuleFlowers.flowerItem) { 
				if(!stack.getTagCompound().getBoolean("Flowered")) return "none";
				if(stack.getTagCompound().getBoolean("Wilt")) return "none";
				return FlowerGenome.getSpecies(stack).getAlleleName().toLowerCase(Locale.ROOT);
			
			}
			
			return "none";
		}

		@Override
		public String getModId() {
			return Constants.BOTANY_ID;
		}

		@Override
		public String getStatePrefix() {
			return "bb_";
		}
		
	}
	
	
}
