package shadows.plants2.compat;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.BlockFlowerpot;
import shadows.plants2.data.Constants;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;

public class BotaniaFlowerpot implements IFlowerpotHandler {

	@Override
	public String handleFlowerpot(IBlockState state, ItemStack stack) {
		if (state.getBlock() == ModBlocks.mushroom)
			return "mushroom_" + state.getValue(BotaniaStateProps.COLOR).getName();
		if (state.getBlock() == ModBlocks.flower) return "flower_" + state.getValue(BotaniaStateProps.COLOR).getName();
		if (state.getBlock() == ModBlocks.shinyFlower)
			return "sflower_" + state.getValue(BotaniaStateProps.COLOR).getName();
		return "none";
	}

	@Override
	public String getModId() {
		return Constants.BOTANIA_ID;
	}

	@Override
	public String getStatePrefix() {
		return "b_";
	}

	@SideOnly(Side.CLIENT)
	public static void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		int hex = state.getValue(BlockFlowerpot.PROP).getColor().getColorValue();
		int r = (hex & 0xFF0000) >> 16;
		int g = (hex & 0xFF00) >> 8;
		int b = hex & 0xFF;

		if (rand.nextDouble() < ConfigHandler.flowerParticleFrequency)
			Botania.proxy.sparkleFX(pos.getX() + 0.3 + rand.nextFloat() * 0.5, pos.getY() + 0.5 + rand.nextFloat() * 0.5, pos.getZ() + 0.3 + rand.nextFloat() * 0.5, r / 255F, g / 255F, b / 255F, rand.nextFloat(), 5);
	}
}
