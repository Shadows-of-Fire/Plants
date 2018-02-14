package shadows.plants2.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.property.IExtendedBlockState;
import shadows.plants2.block.BlockFlowerpot.UnlistedStateProperty;

public class ActualFlowerpotModel implements IBakedModel {

	public static final Map<IBlockState, IBakedModel> MODELS = new HashMap<>();

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		if (state instanceof IExtendedBlockState) {
			IBlockState flower = ((IExtendedBlockState) state).getValue(UnlistedStateProperty.UNLISTED_STATE);
			if (flower == null || flower == Blocks.AIR.getDefaultState()) return FlowerpotModel.flowerpot.getQuads(state, side, rand);
			IBakedModel model = MODELS.get(flower);
			if (model == null) {
				model = new FlowerpotModel(flower);
				MODELS.put(flower, model);
			}
			return model.getQuads(state, side, rand);
		}
		return FlowerpotModel.flowerpot.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return FlowerpotModel.flowerpot.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return FlowerpotModel.flowerpot.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}

}
