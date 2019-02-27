package shadows.plants2.client;

import java.util.EnumMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.model.TRSRTransformation;

public class JarModel implements IBakedModel {

	public static final TRSRTransformation TRANSFORM = TRSRTransformation.blockCenterToCorner(new TRSRTransformation(new Vector3f(0, -0.17F, 0), null, new Vector3f(0.4F, 0.4F, 0.4F), null));
	public static IBakedModel jarSolid;
	public static IBakedModel jarGlass;
	private final IBakedModel flower;
	private final ImmutableList<BakedQuad> general;
	private final ImmutableMap<EnumFacing, ImmutableList<BakedQuad>> faces;

	public JarModel(IBlockState flowerState) {
		flower = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(flowerState);

		ImmutableList.Builder<BakedQuad> builder;
		EnumMap<EnumFacing, ImmutableList<BakedQuad>> faces = new EnumMap<>(EnumFacing.class);

		for (EnumFacing face : EnumFacing.values()) {
			builder = ImmutableList.builder();
			if (!flower.isBuiltInRenderer()) {
				for (BakedQuad quad : flower.getQuads(flowerState, face, 0)) {
					Transformer transformer = new Transformer(TRANSFORM, quad.getFormat());
					quad.pipe(transformer);
					builder.add(transformer.build());
				}
				builder.addAll(jarSolid.getQuads(null, face, 0));
			}
			faces.put(face, builder.build());
		}

		if (!flower.isBuiltInRenderer()) {
			builder = ImmutableList.builder();
			for (BakedQuad quad : flower.getQuads(flowerState, null, 0)) {
				Transformer transformer = new Transformer(TRANSFORM, quad.getFormat());
				quad.pipe(transformer);
				builder.add(transformer.build());
			}
			builder.addAll(jarSolid.getQuads(null, null, 0));
			general = builder.build();
		} else general = ImmutableList.of();

		this.faces = Maps.immutableEnumMap(faces);
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		if (MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.TRANSLUCENT) { return jarGlass.getQuads(state, side, rand); }
		if (MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.CUTOUT) {
			if (side == null) return general;
			return faces.get(side);
		}
		return ImmutableList.of();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return jarSolid.isAmbientOcclusion() && flower.isAmbientOcclusion();
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
		return jarSolid.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}

}
