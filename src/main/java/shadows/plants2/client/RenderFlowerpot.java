package shadows.plants2.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.tile.TileFlowerpot;

public class RenderFlowerpot extends FastTESR<TileFlowerpot> {

	public static final Map<IBlockState, IBakedModel> MODELS = new HashMap<>();
	public static final IBlockState AIR = Blocks.AIR.getDefaultState();

	@Override
	public void renderTileEntityFast(TileFlowerpot te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		if (te == null) return;
		BlockRendererDispatcher brd = Minecraft.getMinecraft().getBlockRendererDispatcher();

		IBakedModel model = null;
		if (te.getState() != AIR) {
			model = MODELS.get(te.getState());
			if (model == null) {
				model = new FlowerpotModel(te.getState(), brd.getModelForState(te.getState()));
				MODELS.put(te.getState(), model);
			}
			buffer.setTranslation(x, y, z);
			brd.getBlockModelRenderer().renderModel(te.getWorld(), model, ModRegistry.FLOWERPOT.getDefaultState(), BlockPos.ORIGIN, buffer, true);
		}
	}

}
