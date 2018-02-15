package shadows.plants2.client;

import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class JarItemModel implements IBakedModel {

	ImmutableList<BakedQuad> bois = null;

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		if (side == null) {
			if (bois == null) {
				ImmutableList.Builder<BakedQuad> bob = ImmutableList.builder();
				bob.addAll(JarModel.jarSolid.getQuads(state, side, rand));
				bob.addAll(JarModel.jarGlass.getQuads(state, side, rand));
				bois = bob.build();
			}
			return bois;
		}
		return ImmutableList.of();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return JarModel.jarSolid.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return JarModel.jarSolid.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return JarModel.jarSolid.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return JarModel.jarSolid.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return JarModel.jarSolid.getOverrides();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		return Pair.of(this, JarModel.jarSolid.handlePerspective(cameraTransformType).getRight());
	}

}
