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
		SAGEBRUSH(false, EnumDyeColor.GREEN),
		;

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

	public static final Map<String, AllPlants> NAME_TO_ENUM = new HashMap<>();

	public static enum AllPlants implements IFlowerEnum {
		NONE(false),
		ABELIOPHYLLUM_D(false),
		ABROMA_A(true),
		ABUTILON_I(true),
		ACAENA_S(true),
		ACALYPHA_A(true),
		ACALYPHA_R(false),
		ACANTHOLIMON_G(true),
		ACHILLEA(true),
		ACHIMENES_E(true),
		ACINOS_A(true),
		ACIPHYLLA_G(true),
		ADENOPHORA_T(true),
		ADONIS_A(true),
		AECHMEA_A(true),
		AESCHYNANTHUS_S(true),
		AETHIONEMA_S(true),
		AGAPANTHUS_A(true),
		AGERATUM_C(true),
		AGROSTEMMA_G(true),
		AIRA_C(true),
		AJUGA_A(true),
		ALBUCA_F(true),
		ALCEA_B(true),
		ALKANNA_T(true),
		ALLIARIA_P(true),
		ALLIUM_C(true),
		ALOE_B(false),
		ALOPECURUS_G(false),
		ALSOBIA_D(false),
		ALSTROEMERIA_P(true),
		ALYOGYNE_H(true),
		AMARYLLIS_B(true),
		AMBROSIA_T(true),
		AMESIELLA_P(true),
		AMMOBIUM_A(true),
		AMPHIPAPPUS(true),
		ANACYCLUS_V(true),
		ANAGALLIS_A(true),
		ANCHUSA_A(true),
		ARGOCOFFEEOPSIS_L(true),
		ASCLEPIAS_S(true),
		ASCLEPIAS_T(true),
		ASPALATHUS_V(true),
		ASTILBE_C(false),
		BARBAREA_V(true),
		BEGONIA_E(true),
		BRACHYSTELMA_S(true),
		CARDAMINE_C(true),
		CEPHALOPHYLLUM_P(true),
		CHRYSANTHEMUM_M(true),
		CLOVE(false),
		CORYDALIS(true),
		CORYDALIS_F(true),
		CRASSULA_S(true),
		DISA_F(true),
		EPIPOGIUM_A(true),
		FABACEAE(true),
		GALANTHUS(true),
		HELIOTROPIUM_P(true),
		HELLEBORE(true),
		HESPERIS_M(true),
		IMPATIENS_C(false),
		ISOLEPIS_B(false),
		NARTHECIUM_A(true),
		NEMESIA_M(false),
		PEONY(true),
		PLUCHEA_G(true),
		PSORALEA_C(true),
		PSORALEA_M(false),
		PUERARIA_M(true),
		RHANTERIUM_E(true),
		RORIPPA_S(false),
		RUDBECKIA_H(true),
		STREBLORRHIZA_S(false),
		SUMATRA_D(false),
		THISMIA_A(false),
		TRILLIUM(true),
		VERATRUM_V(false),
		VERONICA_A(true),
		VIOLA_C(true),
		APACHE_PLUME(true),
		ARIZONA_POPPY(true),
		ASIAN_MUSTARD(true),
		BIGELOWS_MONKEY_FLOWER(true),
		BLACKTACK_PHACELIA(true),
		BLAZING_STAR(true),
		BLUE_FLAX(true),
		BRISTLY_FIDDLENECK(true),
		BROWN_EYES(true),
		BUCKBRUSH(true),
		BUTTON_BRITTLEBUSH(true),
		CANAIGRE(true),
		CHOCOLATE_DROPS(false),
		CHUPAROSA(false),
		CLUSTERED_BROOMRAPE(true),
		COULTERS_JEWELFLOWER(true),
		DISTANT_SCORPIONWEED(true),
		NEGLECTED_SCORPIONWEED(true),
		SAGEBRUSH(false),
		DANDELION(true),
		POPPY(true),
		BLUE_ORCHID(true),
		ALLIUM(true),
		HOUSTONIA(true),
		RED_TULIP(true),
		ORANGE_TULIP(true),
		WHITE_TULIP(true),
		PINK_TULIP(true),
		OXEYE_DAISY(true),
		OAK_SAPLING(false),
		SPRUCE_SAPLING(false),
		BIRCH_SAPLING(false),
		JUNGLE_SAPLING(false),
		ACACIA_SAPLING(false),
		DARK_OAK_SAPLING(false),
		RED_MUSHROOM(false),
		BROWN_MUSHROOM(false),
		DEAD_BUSH(false),
		FERN(false),
		CACTUS(false),
		BLACKBERRY(false),
		BLUEBERRY(false),
		DECIDUOUS(false),
		EVERGREEN(false),
		HUCKLEBERRY(false),
		RASPBERRY(false),
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
		BRAZILLIAN_PINE,;

		EnumDyeColor color;
		boolean flowers;

		AllPlants(boolean flowers, EnumDyeColor c) {
			color = c;
			this.flowers = flowers;
			NAME_TO_ENUM.put(getName(), this);
		}

		AllPlants(boolean flowers) {
			this(flowers, EnumDyeColor.WHITE);
		}

		AllPlants() {
			this(false);
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

}
