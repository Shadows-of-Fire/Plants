package shadows.naturalis.registry;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import shadows.naturalis.Naturalis;
import shadows.naturalis.block.BlockNaturalBush;

@ObjectHolder(Naturalis.MODID)
public class NaturalBlocks {

	public static final BlockNaturalBush FLOWERING_ONION = new BlockNaturalBush("flowering_onion");
	public static final BlockNaturalBush CRY_VIOLET = new BlockNaturalBush("cry_violet");
	public static final BlockNaturalBush GIANT_RAGWEED = new BlockNaturalBush("giant_ragweed");
	public static final BlockNaturalBush BANDED_TRINITY = new BlockNaturalBush("banded_trinity");
	public static final BlockNaturalBush YELLOW_ROCKETCRESS = new BlockNaturalBush("yellow_rocketcress");

	static void load() {
	}
}
