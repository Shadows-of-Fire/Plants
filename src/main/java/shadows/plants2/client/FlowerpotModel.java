package shadows.plants2.client;

import java.util.EnumMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.client.model.pipeline.VertexTransformer;
import net.minecraftforge.common.model.TRSRTransformation;

public class FlowerpotModel implements IBakedModel {

	public static final TRSRTransformation TRANSFORM = TRSRTransformation.blockCenterToCorner(new TRSRTransformation(new Vector3f(0, 0.2F, 0), null, new Vector3f(0.8F, 0.8F, 0.8F), null));
	public static IBakedModel flowerpot;
	private final IBakedModel flower;
	private final ImmutableList<BakedQuad> general;
	private final ImmutableMap<EnumFacing, ImmutableList<BakedQuad>> faces;

	public FlowerpotModel(IBlockState flowerState) {
		this.flower = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(flowerState);

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
				builder.addAll(flowerpot.getQuads(null, face, 0));
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
			builder.addAll(flowerpot.getQuads(null, null, 0));
			this.general = builder.build();
		} else general = ImmutableList.of();

		this.faces = Maps.immutableEnumMap(faces);
	}

	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		if (side == null) return general;
		return faces.get(side);
	}

	public boolean isAmbientOcclusion() {
		return flowerpot.isAmbientOcclusion() && flower.isAmbientOcclusion();
	}

	public boolean isGui3d() {
		return false;
	}

	public boolean isBuiltInRenderer() {
		return false;
	}

	public TextureAtlasSprite getParticleTexture() {
		return flowerpot.getParticleTexture();
	}

	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}

	public static class Transformer extends VertexTransformer {

		protected final Matrix4f transformation;
		protected final Matrix3f normalTransformation;

		public Transformer(TRSRTransformation transformation, VertexFormat format) {
			super(new UnpackedBakedQuad.Builder(format));
			// position transform
			this.transformation = transformation.getMatrix();
			// normal transform
			this.normalTransformation = new Matrix3f();
			this.transformation.getRotationScale(this.normalTransformation);
			this.normalTransformation.invert();
			this.normalTransformation.transpose();
		}

		@Override
		public void put(int element, float... data) {
			VertexFormatElement.EnumUsage usage = parent.getVertexFormat().getElement(element).getUsage();

			// transform normals and position
			if (usage == VertexFormatElement.EnumUsage.POSITION && data.length >= 3) {
				Vector4f vec = new Vector4f(data);
				vec.setW(1.0f);
				transformation.transform(vec);
				data = new float[4];
				vec.get(data);
			} else if (usage == VertexFormatElement.EnumUsage.NORMAL && data.length >= 3) {
				Vector3f vec = new Vector3f(data);
				normalTransformation.transform(vec);
				vec.normalize();
				data = new float[4];
				vec.get(data);
			}
			super.put(element, data);
		}

		public UnpackedBakedQuad build() {
			return ((UnpackedBakedQuad.Builder) parent).build();
		}
	}

}
