package shadows.plants2.data.enums;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IPostInitUpdate;
import shadows.plants2.data.StackPrimer;

public class TheBigBookOfEnums {

	public static enum Logs implements ILogBasedPropertyEnum {
		,;

		private WorldGenAbstractTree treeGen;

		public void setTreeGen(WorldGenAbstractTree gen) {
			treeGen = gen;
		}

		@Override
		public WorldGenAbstractTree getTreeGen() {
			return treeGen;
		}

	}

	public static enum Plants implements IPropertyEnum {
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
		VIOLA_C,;

		EnumDyeColor color;

		Plants(EnumDyeColor c) {
			color = c;
		}

		Plants() {
			this(EnumDyeColor.WHITE);
		}

		@Override
		public EnumDyeColor getColor() {
			return color;
		}

		@Override
		public int getPredicateIndex() {
			return this.ordinal() / 16;
		}

	}

	public static enum Desert implements IPropertyEnum {
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
		SAGEBRUSH;

		EnumDyeColor color;

		Desert(EnumDyeColor c) {
			color = c;
		}

		Desert() {
			this(EnumDyeColor.WHITE);
		}

		@Override
		public EnumDyeColor getColor() {
			return color;
		}

		@Override
		public int getPredicateIndex() {
			return this.ordinal() / 16;
		}

	}

	public static enum Double implements IPropertyEnum {
		ABELIA_C,
		ABRONIA_A,
		ACANTHUS_B,
		ADENOCARPUS_F,
		AGONIS_T,
		ALONSOA_M;

		EnumDyeColor color;

		Double(EnumDyeColor c) {
			color = c;
		}

		Double() {
			this(EnumDyeColor.WHITE);
		}

		@Override
		public EnumDyeColor getColor() {
			return color;
		}

		@Override
		public int getPredicateIndex() {
			return this.ordinal() / 8;
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
			if (dropName.equals(""))
				this.dropName = "minecraft:air";
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

}
