package shadows.naturalis.registry;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import shadows.naturalis.Naturalis;
import shadows.naturalis.block.BlockDesertBush;
import shadows.naturalis.block.BlockNaturalBush;
import shadows.naturalis.util.Color;

@ObjectHolder(Naturalis.MODID)
public class NaturalBlocks {

	public static final BlockNaturalBush FLOWERING_ONION = new BlockNaturalBush("flowering_onion", Color.BLUE);
	public static final BlockNaturalBush CRY_VIOLET = new BlockNaturalBush("cry_violet", Color.PURPLE);
	public static final BlockNaturalBush GIANT_RAGWEED = new BlockNaturalBush("giant_ragweed", Color.YELLOW);
	public static final BlockNaturalBush YELLOW_ROCKETCRESS = new BlockNaturalBush("yellow_rocketcress", Color.YELLOW);

	public static final BlockDesertBush APACHE_PLUME = new BlockDesertBush("apache_plume", Color.BLUE);
	public static final BlockDesertBush ARIZONA_POPPY = new BlockDesertBush("arizona_poppy", Color.PURPLE);
	public static final BlockDesertBush ASIAN_MUSTARD = new BlockDesertBush("asian_mustard", Color.YELLOW);
	public static final BlockDesertBush BIGELOWS_MONKEYFLOWER = new BlockDesertBush("bigelows_monkeyflower", Color.YELLOW);
	public static final BlockDesertBush BLACKTACK_PHACELIA = new BlockDesertBush("blacktack_phacelia", Color.BLUE);
	public static final BlockDesertBush BLAZING_STAR = new BlockDesertBush("blazing_star", Color.PURPLE);
	public static final BlockDesertBush BRISTLY_FIDDLENECK = new BlockDesertBush("bristly_fiddleneck", Color.YELLOW);
	public static final BlockDesertBush BROWN_EYES = new BlockDesertBush("brown_eyes", Color.YELLOW);
	public static final BlockDesertBush DISTANT_SCORPIONWEED = new BlockDesertBush("distant_scorpionweed", Color.BLUE);
	public static final BlockDesertBush SAGEBRUSH = new BlockDesertBush("sagebrush", Color.PURPLE);

	static void load() {
	}
}