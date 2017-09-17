package shadows.plants2.gen;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.BlockRotationProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.ITreeEnum;

public class StructureGen extends EnumTreeGen<ITreeEnum> {

	protected TreeTemplate structure = null;
	protected final ResourceLocation structurePath;
	protected BlockPos offset;
	protected Type[] allowedBiomes;

	public StructureGen(ResourceLocation structurePath, BlockPos offset, ITreeEnum assign, Type... allowedBiomes) {
		super(assign);
		this.structurePath = structurePath;
		this.offset = offset;
		this.allowedBiomes = allowedBiomes;
	}

	public StructureGen(BlockPos offset, ITreeEnum assign, Type... allowedBiomes) {
		this(new ResourceLocation(Constants.MODID, assign.getName() + "_tree"), offset, assign, allowedBiomes);
	}

	public StructureGen(BlockPos offset, String name, ITreeEnum assign, Type... allowedBiomes) {
		this(new ResourceLocation(Constants.MODID, name), offset, assign, allowedBiomes);
	}

	private final PlacementSettings settings = new PlacementSettings();

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if (!world.isRemote) {
			if (structure == null) structure = new TreeTemplate(((WorldServer) world).getStructureTemplateManager().get(null, structurePath));
			return structure.addBlocksToWorld(world, pos.add(offset), new BlockRotationProcessor(pos, settings), settings, 3, false);
		}
		return false;
	}

	@Override
	public boolean canGen(World world, BlockPos pos) {
		if (super.canGen(world, pos)) {
			Biome b = world.getBiome(pos);
			for (Type t : allowedBiomes)
				if ((BiomeDictionary.hasType(b, t))) return true;
			return allowedBiomes.length == 0;
		}
		return false;
	}

}
