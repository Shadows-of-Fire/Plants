package shadows.plants2.data.enums;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IPostInitUpdate;
import shadows.plants2.data.StackPrimer;

public class TheBigBookOfEnums {

	public static enum Logs implements ITreeEnum {
		BLACK_KAURI,
		BRAZILLIAN_PINE,
		INCENSE_CEDAR,
		MURRAY_PINE;

		private WorldGenerator treeGen;

		@Override
		public void setTreeGen(WorldGenerator gen) {
			treeGen = gen;
		}

		@Override
		public WorldGenerator getTreeGen() {
			return treeGen;
		}

	}

	public static enum NetherLogs implements ITreeEnum, IParticleProvider {
		ASH(EnumParticleTypes.SMOKE_LARGE),
		BLAZE(EnumParticleTypes.FLAME);

		private WorldGenerator treeGen;
		private EnumParticleTypes particle;

		NetherLogs(EnumParticleTypes particle) {
			this.particle = particle;
		}

		@Override
		public void setTreeGen(WorldGenerator gen) {
			treeGen = gen;
		}

		@Override
		public WorldGenerator getTreeGen() {
			return treeGen;
		}

		@Override
		public EnumParticleTypes getParticle() {
			return particle;
		}

	}

	public static enum Plants implements IFlowerEnum {
		ABELIOPHYLLUM_D(false, EnumDyeColor.WHITE),
		ABROMA_A(true, EnumDyeColor.RED),
		ABUTILON_I(true, EnumDyeColor.WHITE),
		ACAENA_S(true, EnumDyeColor.ORANGE),
		ACALYPHA_A(true, EnumDyeColor.PINK),
		ACALYPHA_R(false, EnumDyeColor.RED),
		ACANTHOLIMON_G(true, EnumDyeColor.MAGENTA),
		ACHILLEA(true, EnumDyeColor.RED),
		ACHIMENES_E(true, EnumDyeColor.RED),
		ACINOS_A(true, EnumDyeColor.PURPLE),
		ACIPHYLLA_G(true, EnumDyeColor.SILVER),
		ADENOPHORA_T(true, EnumDyeColor.MAGENTA),
		ADONIS_A(true, EnumDyeColor.ORANGE),
		AECHMEA_A(true, EnumDyeColor.RED),
		AESCHYNANTHUS_S(true, EnumDyeColor.RED),
		AETHIONEMA_S(true, EnumDyeColor.PINK),
		AGAPANTHUS_A(true, EnumDyeColor.BLUE),
		AGERATUM_C(true, EnumDyeColor.SILVER),
		AGROSTEMMA_G(true, EnumDyeColor.MAGENTA),
		AIRA_C(true, EnumDyeColor.SILVER),
		AJUGA_A(true, EnumDyeColor.PURPLE),
		ALBUCA_F(true, EnumDyeColor.GREEN),
		ALCEA_B(true, EnumDyeColor.BROWN),
		ALKANNA_T(true, EnumDyeColor.BLUE),
		ALLIARIA_P(true, EnumDyeColor.WHITE),
		ALLIUM_C(true, EnumDyeColor.PURPLE),
		ALOE_B(false, EnumDyeColor.ORANGE),
		ALOPECURUS_G(false, EnumDyeColor.YELLOW),
		ALSOBIA_D(false, EnumDyeColor.WHITE),
		ALSTROEMERIA_P(true, EnumDyeColor.RED),
		ALYOGYNE_H(true, EnumDyeColor.RED),
		AMARYLLIS_B(true, EnumDyeColor.PINK),
		AMBROSIA_T(true, EnumDyeColor.YELLOW),
		AMESIELLA_P(true, EnumDyeColor.WHITE),
		AMMOBIUM_A(true, EnumDyeColor.WHITE),
		AMPHIPAPPUS(true, EnumDyeColor.YELLOW),
		ANACYCLUS_V(true, EnumDyeColor.YELLOW),
		ANAGALLIS_A(true, EnumDyeColor.ORANGE),
		ANCHUSA_A(true, EnumDyeColor.BLUE),
		ARGOCOFFEEOPSIS_L(true, EnumDyeColor.PINK),
		ASCLEPIAS_S(true, EnumDyeColor.MAGENTA),
		ASCLEPIAS_T(true, EnumDyeColor.ORANGE),
		ASPALATHUS_V(true, EnumDyeColor.YELLOW),
		ASTILBE_C(false, EnumDyeColor.PINK),
		BARBAREA_V(true, EnumDyeColor.YELLOW),
		BEGONIA_E(true, EnumDyeColor.PINK),
		BRACHYSTELMA_S(true, EnumDyeColor.BROWN),
		CARDAMINE_C(true, EnumDyeColor.WHITE),
		CEPHALOPHYLLUM_P(true, EnumDyeColor.RED),
		CHRYSANTHEMUM_M(true, EnumDyeColor.PINK),
		CLOVE(false, EnumDyeColor.RED),
		CORYDALIS(true, EnumDyeColor.MAGENTA),
		CORYDALIS_F(true, EnumDyeColor.YELLOW),
		CRASSULA_S(true, EnumDyeColor.SILVER),
		DISA_F(true, EnumDyeColor.RED),
		EPIPOGIUM_A(true, EnumDyeColor.PINK),
		FABACEAE(true, EnumDyeColor.PURPLE),
		GALANTHUS(true, EnumDyeColor.WHITE),
		HELIOTROPIUM_P(true, EnumDyeColor.YELLOW),
		HELLEBORE(true, EnumDyeColor.PURPLE),
		HESPERIS_M(true, EnumDyeColor.PINK),
		IMPATIENS_C(false, EnumDyeColor.ORANGE),
		ISOLEPIS_B(false, EnumDyeColor.GREEN),
		NARTHECIUM_A(true, EnumDyeColor.YELLOW),
		NEMESIA_M(false, EnumDyeColor.YELLOW),
		PEONY(true, EnumDyeColor.PINK),
		PLUCHEA_G(true, EnumDyeColor.PINK),
		PSORALEA_C(true, EnumDyeColor.PURPLE),
		PSORALEA_M(false, EnumDyeColor.BLUE),
		PUERARIA_M(true, EnumDyeColor.MAGENTA),
		RHANTERIUM_E(true, EnumDyeColor.YELLOW),
		RORIPPA_S(false, EnumDyeColor.YELLOW),
		RUDBECKIA_H(true, EnumDyeColor.YELLOW),
		STREBLORRHIZA_S(false, EnumDyeColor.RED),
		SUMATRA_D(false, EnumDyeColor.BROWN),
		THISMIA_A(false, EnumDyeColor.ORANGE),
		TRILLIUM(true, EnumDyeColor.WHITE),
		VERATRUM_V(false, EnumDyeColor.GREEN),
		VERONICA_A(true, EnumDyeColor.LIGHT_BLUE),
		VIOLA_C(true, EnumDyeColor.PURPLE),;

		EnumDyeColor color;
		boolean flowers;

		Plants(boolean flowers, EnumDyeColor c) {
			color = c;
			this.flowers = flowers;
		}

		@Override
		public EnumDyeColor getColor() {
			return color;
		}

		@Override
		public boolean useForRecipes() {
			return true;
		}

		@Override
		public boolean hasFlowers() {
			return flowers;
		}

	}

	public static enum Desert implements IFlowerEnum {
		APACHE_PLUME(true, EnumDyeColor.PURPLE),
		ARIZONA_POPPY(true, EnumDyeColor.YELLOW),
		ASIAN_MUSTARD(true, EnumDyeColor.LIME),
		BIGELOWS_MONKEY_FLOWER(true, EnumDyeColor.MAGENTA),
		BLACKTACK_PHACELIA(true, EnumDyeColor.PURPLE),
		BLAZING_STAR(true, EnumDyeColor.YELLOW),
		BLUE_FLAX(true, EnumDyeColor.LIGHT_BLUE),
		BRISTLY_FIDDLENECK(true, EnumDyeColor.YELLOW),
		BROWN_EYES(true, EnumDyeColor.BROWN),
		BUCKBRUSH(true, EnumDyeColor.SILVER),
		BUTTON_BRITTLEBUSH(true, EnumDyeColor.LIME),
		CANAIGRE(true, EnumDyeColor.ORANGE),
		CHOCOLATE_DROPS(false, EnumDyeColor.BLUE),
		CHUPAROSA(false, EnumDyeColor.ORANGE),
		CLUSTERED_BROOMRAPE(true, EnumDyeColor.YELLOW),
		COULTERS_JEWELFLOWER(true, EnumDyeColor.BROWN),
		DISTANT_SCORPIONWEED(true, EnumDyeColor.PURPLE),
		NEGLECTED_SCORPIONWEED(true, EnumDyeColor.BROWN),
		SAGEBRUSH(false, EnumDyeColor.GREEN),;

		EnumDyeColor color;
		boolean flowers;

		Desert(boolean flowers, EnumDyeColor c) {
			color = c;
			this.flowers = flowers;
		}

		@Override
		public EnumDyeColor getColor() {
			return color;
		}

		@Override
		public boolean useForRecipes() {
			return true;
		}

		@Override
		public boolean hasFlowers() {
			return flowers;
		}

	}

	public static enum Double implements IFlowerEnum {
		ABELIA_C(true, EnumDyeColor.WHITE),
		ABRONIA_A(true, EnumDyeColor.PURPLE),
		ACANTHUS_B(true, EnumDyeColor.PINK),
		ADENOCARPUS_F(true, EnumDyeColor.YELLOW),
		AGONIS_T(true, EnumDyeColor.WHITE),
		ALONSOA_M(true, EnumDyeColor.RED),;

		EnumDyeColor color;
		boolean flowers;

		Double(boolean flowers, EnumDyeColor c) {
			color = c;
			this.flowers = flowers;
		}

		@Override
		public EnumDyeColor getColor() {
			return color;
		}

		@Override
		public int getPredicateIndex() {
			return this.ordinal() / 8;
		}

		@Override
		public boolean useForRecipes() {
			return true;
		}

		@Override
		public int getMetadata() {
			return this.ordinal() % 8;
		}

		@Override
		public boolean hasFlowers() {
			return flowers;
		}

	}

	public static enum Generic implements IPropertyEnum {
		COTTON,
		DYE_BLACK,
		DYE_BLUE,
		DYE_BROWN,
		DYE_WHITE
	}

	public static enum Vines implements IPropertyEnum {
		ADLUMIA_F,
		AFGEKIA_M,
		ANDROSACE_A,
		AKEBIA_Q,
		AMPELOPSIS_A
	}

	public static enum Crops implements IPropertyEnum {
		AMARANTHUS_H,
		OKRA,
		PINEAPPLE,;

		@Override
		public int getPredicateIndex() {
			return this.ordinal() / 2;
		}

	}

	public static enum BushSet implements IPropertyEnum, IPostInitUpdate {
		BLACKBERRY("plants2:blackberry"),
		BLUEBERRY("plants2:blueberry"),
		DECIDUOUS(""),
		EVERGREEN(""),
		HUCKLEBERRY("plants2:huckleberry"),
		RASPBERRY("plants2:raspberry");

		private StackPrimer primer;
		private String dropName;

		BushSet(String dropName) {
			this.dropName = dropName;
			Constants.UPDATES.add(this);
		}

		public StackPrimer getHarvest() {
			return primer;
		}

		private void setItem(Item item) {
			primer = new StackPrimer(item);
		}

		@Override
		public void postInit(FMLPostInitializationEvent e) {
			this.setItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(dropName)));
		}

	}

	public static final Map<String, FlowerpotPlants> NAME_TO_ENUM = new HashMap<>();

	public static enum FlowerpotPlants implements IFlowerEnum {
		NONE,
		ABELIOPHYLLUM_D,
		ABROMA_A,
		ABUTILON_I,
		ACAENA_S,
		ACALYPHA_A,
		ACALYPHA_R,
		ACANTHOLIMON_G,
		ACHILLEA,
		ACHIMENES_E,
		ACINOS_A,
		ACIPHYLLA_G,
		ADENOPHORA_T,
		ADONIS_A,
		AECHMEA_A,
		AESCHYNANTHUS_S,
		AETHIONEMA_S,
		AGAPANTHUS_A,
		AGERATUM_C,
		AGROSTEMMA_G,
		AIRA_C,
		AJUGA_A,
		ALBUCA_F,
		ALCEA_B,
		ALKANNA_T,
		ALLIARIA_P,
		ALLIUM_C,
		ALOE_B,
		ALOPECURUS_G,
		ALSOBIA_D,
		ALSTROEMERIA_P,
		ALYOGYNE_H,
		AMARYLLIS_B,
		AMBROSIA_T,
		AMESIELLA_P,
		AMMOBIUM_A,
		AMPHIPAPPUS,
		ANACYCLUS_V,
		ANAGALLIS_A,
		ANCHUSA_A,
		ARGOCOFFEEOPSIS_L,
		ASCLEPIAS_S,
		ASCLEPIAS_T,
		ASPALATHUS_V,
		ASTILBE_C,
		BARBAREA_V,
		BEGONIA_E,
		BRACHYSTELMA_S,
		CARDAMINE_C,
		CEPHALOPHYLLUM_P,
		CHRYSANTHEMUM_M,
		CLOVE,
		CORYDALIS,
		CORYDALIS_F,
		CRASSULA_S,
		DISA_F,
		EPIPOGIUM_A,
		FABACEAE,
		GALANTHUS,
		HELIOTROPIUM_P,
		HELLEBORE,
		HESPERIS_M,
		IMPATIENS_C,
		ISOLEPIS_B,
		NARTHECIUM_A,
		NEMESIA_M,
		PEONY,
		PLUCHEA_G,
		PSORALEA_C,
		PSORALEA_M,
		PUERARIA_M,
		RHANTERIUM_E,
		RORIPPA_S,
		RUDBECKIA_H,
		STREBLORRHIZA_S,
		SUMATRA_D,
		THISMIA_A,
		TRILLIUM,
		VERATRUM_V,
		VERONICA_A,
		VIOLA_C,
		APACHE_PLUME,
		ARIZONA_POPPY,
		ASIAN_MUSTARD,
		BIGELOWS_MONKEY_FLOWER,
		BLACKTACK_PHACELIA,
		BLAZING_STAR,
		BLUE_FLAX,
		BRISTLY_FIDDLENECK,
		BROWN_EYES,
		BUCKBRUSH,
		BUTTON_BRITTLEBUSH,
		CANAIGRE,
		CHOCOLATE_DROPS,
		CHUPAROSA,
		CLUSTERED_BROOMRAPE,
		COULTERS_JEWELFLOWER,
		DISTANT_SCORPIONWEED,
		NEGLECTED_SCORPIONWEED,
		SAGEBRUSH,
		DANDELION,
		POPPY,
		BLUE_ORCHID,
		ALLIUM,
		HOUSTONIA,
		RED_TULIP,
		ORANGE_TULIP,
		WHITE_TULIP,
		PINK_TULIP,
		OXEYE_DAISY,
		OAK_SAPLING,
		SPRUCE_SAPLING,
		BIRCH_SAPLING,
		JUNGLE_SAPLING,
		ACACIA_SAPLING,
		DARK_OAK_SAPLING,
		MUSHROOM_RED,
		MUSHROOM_BROWN,
		DEAD_BUSH,
		FERN,
		CACTUS,
		BLACKBERRY,
		BLUEBERRY,
		DECIDUOUS,
		EVERGREEN,
		HUCKLEBERRY,
		RASPBERRY,
		ACTAEA_P,
		ALTERNANTHERA_F,
		AMBROSIA_A,
		APOCYNUM_C,
		DAUCUS_C,
		PHYTOLACCA_A,
		PLANTAGO_M,
		RUBUS_O,
		RUBUS_P,
		SAFFRON,
		SOLANUM_C,
		SOLANUM_D,
		SOLANUM_N,
		ASH,
		BLAZE,
		BLACK_KAURI,
		BRAZILLIAN_PINE,
		INCENSE_CEDAR,
		MURRAY_PINE,
		B_FLOWER_WHITE(0, EnumDyeColor.WHITE),
		B_FLOWER_ORANGE(0, EnumDyeColor.ORANGE),
		B_FLOWER_MAGENTA(0, EnumDyeColor.MAGENTA),
		B_FLOWER_LIGHT_BLUE(0, EnumDyeColor.LIGHT_BLUE),
		B_FLOWER_YELLOW(0, EnumDyeColor.YELLOW),
		B_FLOWER_LIME(0, EnumDyeColor.LIME),
		B_FLOWER_PINK(0, EnumDyeColor.PINK),
		B_FLOWER_GRAY(0, EnumDyeColor.GRAY),
		B_FLOWER_SILVER(0, EnumDyeColor.SILVER),
		B_FLOWER_CYAN(0, EnumDyeColor.CYAN),
		B_FLOWER_PURPLE(0, EnumDyeColor.PURPLE),
		B_FLOWER_BLUE(0, EnumDyeColor.BLUE),
		B_FLOWER_BROWN(0, EnumDyeColor.BROWN),
		B_FLOWER_GREEN(0, EnumDyeColor.GREEN),
		B_FLOWER_RED(0, EnumDyeColor.RED),
		B_FLOWER_BLACK(0, EnumDyeColor.BLACK),
		B_MUSHROOM_WHITE(15, EnumDyeColor.WHITE),
		B_MUSHROOM_ORANGE(15, EnumDyeColor.ORANGE),
		B_MUSHROOM_MAGENTA(15, EnumDyeColor.MAGENTA),
		B_MUSHROOM_LIGHT_BLUE(15, EnumDyeColor.LIGHT_BLUE),
		B_MUSHROOM_YELLOW(15, EnumDyeColor.YELLOW),
		B_MUSHROOM_LIME(15, EnumDyeColor.LIME),
		B_MUSHROOM_PINK(15, EnumDyeColor.PINK),
		B_MUSHROOM_GRAY(15, EnumDyeColor.GRAY),
		B_MUSHROOM_SILVER(15, EnumDyeColor.SILVER),
		B_MUSHROOM_CYAN(15, EnumDyeColor.CYAN),
		B_MUSHROOM_PURPLE(15, EnumDyeColor.PURPLE),
		B_MUSHROOM_BLUE(15, EnumDyeColor.BLUE),
		B_MUSHROOM_BROWN(15, EnumDyeColor.BROWN),
		B_MUSHROOM_GREEN(15, EnumDyeColor.GREEN),
		B_MUSHROOM_RED(15, EnumDyeColor.RED),
		B_MUSHROOM_BLACK(15, EnumDyeColor.BLACK),
		B_SFLOWER_WHITE(15, EnumDyeColor.WHITE),
		B_SFLOWER_ORANGE(15, EnumDyeColor.ORANGE),
		B_SFLOWER_MAGENTA(15, EnumDyeColor.MAGENTA),
		B_SFLOWER_LIGHT_BLUE(15, EnumDyeColor.LIGHT_BLUE),
		B_SFLOWER_YELLOW(15, EnumDyeColor.YELLOW),
		B_SFLOWER_LIME(15, EnumDyeColor.LIME),
		B_SFLOWER_PINK(15, EnumDyeColor.PINK),
		B_SFLOWER_GRAY(15, EnumDyeColor.GRAY),
		B_SFLOWER_SILVER(15, EnumDyeColor.SILVER),
		B_SFLOWER_CYAN(15, EnumDyeColor.CYAN),
		B_SFLOWER_PURPLE(15, EnumDyeColor.PURPLE),
		B_SFLOWER_BLUE(15, EnumDyeColor.BLUE),
		B_SFLOWER_BROWN(15, EnumDyeColor.BROWN),
		B_SFLOWER_GREEN(15, EnumDyeColor.GREEN),
		B_SFLOWER_RED(15, EnumDyeColor.RED),
		B_SFLOWER_BLACK(15, EnumDyeColor.BLACK),
		TF_MAYAPPLE,
		TF_OAK_SAPLING,
		TF_CANOPY_SAPLING,
		TF_MANGROVE_SAPLING,
		TF_DARKWOOD_SAPLING,
		TF_HOLLOW_OAK_SAPLING,
		TF_TIME_SAPLING,
		TF_TRANSFORMATION_SAPLING,
		TF_MINING_SAPLING,
		TF_SORTING_SAPLING,
		TF_RAINBOW_SAPLING,
		TF_MUSHGLOOM(3),
		TF_FIDDLEHEAD,
		TF_WATERLILY,
		F_SAPLING_ACACIA,
		F_SAPLING_DESERT_ACACIA,
		F_SAPLING_BALSA,
		F_SAPLING_GRANDIDIERS_BAOBAB,
		F_SAPLING_HILL_CHERRY,
		F_SAPLING_SWEET_CHESTNUT,
		F_SAPLING_COCOBOLO,
		F_SAPLING_DATE,
		F_SAPLING_MYRTLE_EBONY,
		F_SAPLING_GIGANT,
		F_SAPLING_IPE,
		F_SAPLING_KAPOK,
		F_SAPLING_MUNDANE_LARCH,
		F_SAPLING_LEMON,
		F_SAPLING_SILVER_LIME,
		F_SAPLING_BLUE_MAHOE,
		F_SAPLING_MAHOGANY,
		F_SAPLING_SUGAR_MAPLE,
		F_SAPLING_PADAUK,
		F_SAPLING_PAPAYA,
		F_SAPLING_BULL_PINE,
		F_SAPLING_PLUM,
		F_SAPLING_WHITE_POPLAR,
		F_SAPLING_COAST_SEQUOIA,
		F_SAPLING_SIPIRI,
		F_SAPLING_TEAK,
		F_SAPLING_COMMON_WALNUT,
		F_SAPLING_WENGE,
		F_SAPLING_WHITE_WILLOW,
		F_SAPLING_ZEBRAWOOD,
		F_SAPLING_APPLE_OAK,
		F_SAPLING_RED_SPRUCE,
		F_SAPLING_SILVER_BIRCH,
		F_SAPLING_JUNGLE,
		F_SAPLING_DARK_OAK,
		AA_BLACK_LOTUS,;

		final int light;
		final EnumDyeColor color;

		FlowerpotPlants(int light, EnumDyeColor color) {
			this.light = light;
			this.color = color;
			NAME_TO_ENUM.put(getName(), this);
		}

		FlowerpotPlants(EnumDyeColor color) {
			this(0, color);
		}

		FlowerpotPlants(int light) {
			this(light, EnumDyeColor.WHITE);
		}

		FlowerpotPlants() {
			this(0);
		}

		@Override
		public EnumDyeColor getColor() {
			return color;
		}

		public int getLightLevel() {
			return light;
		}

		@Override // Unused
		public boolean hasFlowers() {
			return false;
		}

	}

}
