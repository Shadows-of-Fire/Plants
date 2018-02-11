package shadows.plants2.data.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import shadows.placebo.Placebo;
import shadows.placebo.interfaces.IFlowerEnum;
import shadows.placebo.interfaces.IParticleProvider;
import shadows.placebo.interfaces.IPlankEnum;
import shadows.placebo.interfaces.IPostInitUpdate;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.placebo.interfaces.ITreeEnum;
import shadows.placebo.util.RecipeHelper;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.Plants2;
import shadows.plants2.data.PlantConstants;
import shadows.plants2.init.ModRegistry;

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

		IForgeRegistryEntry<?> thing = null;

		@Override
		public ItemStack get() {
			return RecipeHelper.makeStack(thing, 1, getMetadata());
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			thing = ifre;
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

		@Override
		public ItemStack get() {
			return new ItemStack(ModRegistry.NETHER_LOG, 1, ordinal());
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
		STREBLORRHIZA_S(false, EnumDyeColor.GRAY),
		SUMATRA_D(false, EnumDyeColor.BROWN),
		THISMIA_A(false, EnumDyeColor.ORANGE),
		TRILLIUM(true, EnumDyeColor.WHITE),
		VERATRUM_V(false, EnumDyeColor.GREEN),
		VERONICA_A(true, EnumDyeColor.LIGHT_BLUE),
		VIOLA_C(true, EnumDyeColor.PURPLE),
		ALLIUM_DRUMSTICK(true, EnumDyeColor.PINK),
		BACHELORS_BUTTON(true, EnumDyeColor.BLUE),
		BILLY_BUTTONS(true, EnumDyeColor.YELLOW),
		DELPHINIUM_BELLADONNA(true, EnumDyeColor.BLUE),
		FERNFLOWER_YARROW(true, EnumDyeColor.YELLOW),
		GERBERA_DAISY(true, EnumDyeColor.PINK),
		HYDRANGEA(true, EnumDyeColor.PURPLE),
		RED_ROVER(true, EnumDyeColor.RED),
		SNAPDRAGON(true, EnumDyeColor.RED),
		STAR_OF_BETHLEHEM(true, EnumDyeColor.WHITE),
		BARREN_STRAWBERRY(true, EnumDyeColor.LIME),
		BEARDTONGUE(true, EnumDyeColor.MAGENTA),
		FAIRY_LILY(true, EnumDyeColor.WHITE),
		ROYAL_BLUEBELL(true, EnumDyeColor.PURPLE),
		SANDBOG_DEATH_CAMAS(true, EnumDyeColor.SILVER),
		SNOWY_RIVER_WESTRINGIA(true, EnumDyeColor.GRAY),
		WELDENIA_CANDIDA(true, EnumDyeColor.WHITE),
		WILD_RICE(false, EnumDyeColor.LIME),
		YELLOWROOT(true, EnumDyeColor.MAGENTA),
		ZENOBIA(true, EnumDyeColor.SILVER),
		CAMBRIDGE_BLUE(true, EnumDyeColor.CYAN),;

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

		IForgeRegistryEntry<?> thing = null;

		@Override
		public ItemStack get() {
			return RecipeHelper.makeStack(thing, 1, getMetadata());
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			thing = ifre;
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
		SAN_ANGELO_YUCCA(false, EnumDyeColor.GREEN),;

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

		IForgeRegistryEntry<?> thing = null;

		@Override
		public ItemStack get() {
			return RecipeHelper.makeStack(thing, 1, getMetadata());
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			thing = ifre;
		}

	}

	public static enum Double implements IFlowerEnum {
		ABELIA_C(true, EnumDyeColor.WHITE),
		ABRONIA_A(true, EnumDyeColor.PURPLE),
		ACANTHUS_B(true, EnumDyeColor.PINK),
		ADENOCARPUS_F(true, EnumDyeColor.YELLOW),
		AGONIS_T(true, EnumDyeColor.WHITE),
		ALONSOA_M(true, EnumDyeColor.RED),
		BULBIL_BUGLELILY(true, EnumDyeColor.ORANGE),;

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

		IForgeRegistryEntry<?> thing = null;

		@Override
		public ItemStack get() {
			return RecipeHelper.makeStack(thing, 1, getMetadata());
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			thing = ifre;
		}

	}

	public static enum Generic implements IPropertyEnum {
		COTTON,
		DYE_BLACK,
		DYE_BLUE,
		DYE_BROWN,
		DYE_WHITE,
		CRYSTAL_SHARD,
		CRYSTAL_CHUNK,
		DARK_CRYSTAL_SHARD,
		DARK_CRYSTAL_CHUNK,
		CRYSTAL_STICK,
		BLAZE_PETAL,
		MAGMA_JELLY,
		SMOLDERBERRY,
		EMBERROOT,;

		@Override
		public ItemStack get() {
			return new ItemStack(ModRegistry.GENERIC, 1, ordinal());
		}
	}

	public static enum Vines implements IPropertyEnum {
		ADLUMIA_F,
		AFGEKIA_M,
		ANDROSACE_A,
		AKEBIA_Q,
		AMPELOPSIS_A;

		IForgeRegistryEntry<?> thing = null;

		@Override
		public ItemStack get() {
			return RecipeHelper.makeStack(thing);
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			thing = ifre;
		}
	}

	public static enum Crops implements IPropertyEnum {
		AMARANTHUS_H,
		OKRA,
		PINEAPPLE,;

		@Override
		public int getPredicateIndex() {
			return this.ordinal() / 2;
		}

		@Override
		public ItemStack get() {
			return ItemStack.EMPTY;
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
			Placebo.UPDATES.add(this);
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

		@Override
		public ItemStack get() {
			return new ItemStack(ModRegistry.BUSH, 1, ordinal());
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
		B_FLOWER_WHITE(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.WHITE),
		B_FLOWER_ORANGE(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.ORANGE),
		B_FLOWER_MAGENTA(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.MAGENTA),
		B_FLOWER_LIGHT_BLUE(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.LIGHT_BLUE),
		B_FLOWER_YELLOW(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.YELLOW),
		B_FLOWER_LIME(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.LIME),
		B_FLOWER_PINK(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.PINK),
		B_FLOWER_GRAY(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.GRAY),
		B_FLOWER_SILVER(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.SILVER),
		B_FLOWER_CYAN(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.CYAN),
		B_FLOWER_PURPLE(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.PURPLE),
		B_FLOWER_BLUE(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.BLUE),
		B_FLOWER_BROWN(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.BROWN),
		B_FLOWER_GREEN(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.GREEN),
		B_FLOWER_RED(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.RED),
		B_FLOWER_BLACK(PlantConstants.BOTANIA_ID, 0, EnumDyeColor.BLACK),
		B_MUSHROOM_WHITE(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.WHITE),
		B_MUSHROOM_ORANGE(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.ORANGE),
		B_MUSHROOM_MAGENTA(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.MAGENTA),
		B_MUSHROOM_LIGHT_BLUE(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.LIGHT_BLUE),
		B_MUSHROOM_YELLOW(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.YELLOW),
		B_MUSHROOM_LIME(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.LIME),
		B_MUSHROOM_PINK(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.PINK),
		B_MUSHROOM_GRAY(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.GRAY),
		B_MUSHROOM_SILVER(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.SILVER),
		B_MUSHROOM_CYAN(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.CYAN),
		B_MUSHROOM_PURPLE(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.PURPLE),
		B_MUSHROOM_BLUE(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.BLUE),
		B_MUSHROOM_BROWN(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.BROWN),
		B_MUSHROOM_GREEN(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.GREEN),
		B_MUSHROOM_RED(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.RED),
		B_MUSHROOM_BLACK(PlantConstants.BOTANIA_ID, 3, EnumDyeColor.BLACK),
		B_SFLOWER_WHITE(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.WHITE),
		B_SFLOWER_ORANGE(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.ORANGE),
		B_SFLOWER_MAGENTA(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.MAGENTA),
		B_SFLOWER_LIGHT_BLUE(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.LIGHT_BLUE),
		B_SFLOWER_YELLOW(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.YELLOW),
		B_SFLOWER_LIME(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.LIME),
		B_SFLOWER_PINK(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.PINK),
		B_SFLOWER_GRAY(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.GRAY),
		B_SFLOWER_SILVER(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.SILVER),
		B_SFLOWER_CYAN(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.CYAN),
		B_SFLOWER_PURPLE(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.PURPLE),
		B_SFLOWER_BLUE(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.BLUE),
		B_SFLOWER_BROWN(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.BROWN),
		B_SFLOWER_GREEN(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.GREEN),
		B_SFLOWER_RED(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.RED),
		B_SFLOWER_BLACK(PlantConstants.BOTANIA_ID, 15, EnumDyeColor.BLACK),
		TF_MAYAPPLE(PlantConstants.TF_ID),
		TF_OAK_SAPLING(PlantConstants.TF_ID),
		TF_CANOPY_SAPLING(PlantConstants.TF_ID),
		TF_MANGROVE_SAPLING(PlantConstants.TF_ID),
		TF_DARKWOOD_SAPLING(PlantConstants.TF_ID),
		TF_HOLLOW_OAK_SAPLING(PlantConstants.TF_ID),
		TF_TIME_SAPLING(PlantConstants.TF_ID),
		TF_TRANSFORMATION_SAPLING(PlantConstants.TF_ID),
		TF_MINING_SAPLING(PlantConstants.TF_ID),
		TF_SORTING_SAPLING(PlantConstants.TF_ID),
		TF_RAINBOW_SAPLING(PlantConstants.TF_ID),
		TF_MUSHGLOOM(PlantConstants.TF_ID, 3),
		TF_FIDDLEHEAD(PlantConstants.TF_ID),
		TF_WATERLILY(PlantConstants.TF_ID),
		F_SAPLING_ACACIA(PlantConstants.FORESTRY_ID),
		F_SAPLING_DESERT_ACACIA(PlantConstants.FORESTRY_ID),
		F_SAPLING_BALSA(PlantConstants.FORESTRY_ID),
		F_SAPLING_GRANDIDIERS_BAOBAB(PlantConstants.FORESTRY_ID),
		F_SAPLING_HILL_CHERRY(PlantConstants.FORESTRY_ID),
		F_SAPLING_SWEET_CHESTNUT(PlantConstants.FORESTRY_ID),
		F_SAPLING_COCOBOLO(PlantConstants.FORESTRY_ID),
		F_SAPLING_DATE(PlantConstants.FORESTRY_ID),
		F_SAPLING_MYRTLE_EBONY(PlantConstants.FORESTRY_ID),
		F_SAPLING_GIGANT(PlantConstants.FORESTRY_ID),
		F_SAPLING_IPE(PlantConstants.FORESTRY_ID),
		F_SAPLING_KAPOK(PlantConstants.FORESTRY_ID),
		F_SAPLING_MUNDANE_LARCH(PlantConstants.FORESTRY_ID),
		F_SAPLING_LEMON(PlantConstants.FORESTRY_ID),
		F_SAPLING_SILVER_LIME(PlantConstants.FORESTRY_ID),
		F_SAPLING_BLUE_MAHOE(PlantConstants.FORESTRY_ID),
		F_SAPLING_SUGAR_MAPLE(PlantConstants.FORESTRY_ID),
		F_SAPLING_PADAUK(PlantConstants.FORESTRY_ID),
		F_SAPLING_PAPAYA(PlantConstants.FORESTRY_ID),
		F_SAPLING_BULL_PINE(PlantConstants.FORESTRY_ID),
		F_SAPLING_PLUM(PlantConstants.FORESTRY_ID),
		F_SAPLING_WHITE_POPLAR(PlantConstants.FORESTRY_ID),
		F_SAPLING_COAST_SEQUOIA(PlantConstants.FORESTRY_ID),
		F_SAPLING_SIPIRI(PlantConstants.FORESTRY_ID),
		F_SAPLING_TEAK(PlantConstants.FORESTRY_ID),
		F_SAPLING_COMMON_WALNUT(PlantConstants.FORESTRY_ID),
		F_SAPLING_WENGE(PlantConstants.FORESTRY_ID),
		F_SAPLING_WHITE_WILLOW(PlantConstants.FORESTRY_ID),
		F_SAPLING_ZEBRAWOOD(PlantConstants.FORESTRY_ID),
		F_SAPLING_APPLE_OAK(PlantConstants.FORESTRY_ID),
		F_SAPLING_RED_SPRUCE(PlantConstants.FORESTRY_ID),
		F_SAPLING_SILVER_BIRCH(PlantConstants.FORESTRY_ID),
		F_SAPLING_JUNGLE(PlantConstants.FORESTRY_ID),
		F_SAPLING_DARK_OAK(PlantConstants.FORESTRY_ID),
		AA_BLACK_LOTUS(PlantConstants.AA_ID),
		BB_AGAPANTHUS(PlantConstants.BOTANY_ID),
		BB_ALLIUM(PlantConstants.BOTANY_ID),
		BB_ANEMONE(PlantConstants.BOTANY_ID),
		BB_AQUILEGIA(PlantConstants.BOTANY_ID),
		BB_ASTER(PlantConstants.BOTANY_ID),
		BB_AURICULA(PlantConstants.BOTANY_ID),
		BB_AZALEA(PlantConstants.BOTANY_ID),
		BB_BLUET(PlantConstants.BOTANY_ID),
		BB_CARNATION(PlantConstants.BOTANY_ID),
		BB_CONEFLOWER(PlantConstants.BOTANY_ID),
		BB_CORNFLOWER(PlantConstants.BOTANY_ID),
		BB_DAFFODIL(PlantConstants.BOTANY_ID),
		BB_DAHLIA(PlantConstants.BOTANY_ID),
		BB_DAISY(PlantConstants.BOTANY_ID),
		BB_DANDELION(PlantConstants.BOTANY_ID),
		BB_DIANTHUS(PlantConstants.BOTANY_ID),
		BB_EDELWEISS(PlantConstants.BOTANY_ID),
		BB_FORGET(PlantConstants.BOTANY_ID),
		BB_FUCHSIA(PlantConstants.BOTANY_ID),
		BB_GAILLARDIA(PlantConstants.BOTANY_ID),
		BB_GERANIUM(PlantConstants.BOTANY_ID),
		BB_IRIS(PlantConstants.BOTANY_ID),
		BB_LILY(PlantConstants.BOTANY_ID),
		BB_MARIGOLD(PlantConstants.BOTANY_ID),
		BB_MUMS(PlantConstants.BOTANY_ID),
		BB_ORCHID(PlantConstants.BOTANY_ID),
		BB_PANSY(PlantConstants.BOTANY_ID),
		BB_PETUNIA(PlantConstants.BOTANY_ID),
		BB_POPPY(PlantConstants.BOTANY_ID),
		BB_PRIMROSE(PlantConstants.BOTANY_ID),
		BB_SCABIOUS(PlantConstants.BOTANY_ID),
		BB_TULIP(PlantConstants.BOTANY_ID),
		BB_VIOLA(PlantConstants.BOTANY_ID),
		BB_YARROW(PlantConstants.BOTANY_ID),
		BB_ZINNIA(PlantConstants.BOTANY_ID);

		final int light;
		final EnumDyeColor color;
		final String modid;

		FlowerpotPlants(String modid, int light, EnumDyeColor color) {
			this.modid = modid;
			this.light = light;
			this.color = color;
			NAME_TO_ENUM.put(getName(), this);
		}

		FlowerpotPlants(int light, EnumDyeColor color) {
			this(Plants2.MODID, light, color);
		}

		FlowerpotPlants(EnumDyeColor color) {
			this(0, color);
		}

		FlowerpotPlants(int light) {
			this(light, EnumDyeColor.WHITE);
		}

		FlowerpotPlants(String modid, EnumDyeColor color) {
			this(modid, 0, color);
		}

		FlowerpotPlants(String modid, int light) {
			this(modid, light, EnumDyeColor.WHITE);
		}

		FlowerpotPlants(String modid) {
			this(modid, 0);
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

		@Override
		public ItemStack get() {
			return ItemStack.EMPTY;
		}

		public boolean isLoaded() {
			return Loader.isModLoaded(modid);
		}

		public ModelResourceLocation genMRL() {
			if (isLoaded()) return new ModelResourceLocation(new ResourceLocation(Plants2.MODID, "flowerpot/" + modid), "type=" + this.getName());
			return new ModelResourceLocation(new ResourceLocation(Plants2.MODID, "flowerpot/" + Plants2.MODID), "type=none");
		}

	}

	public static enum CrystalLogs implements ITreeEnum {
		CRYSTAL,
		DARK_CRYSTAL;

		private WorldGenerator gen;

		@Override
		public WorldGenerator getTreeGen() {
			return gen;
		}

		@Override
		public void setTreeGen(WorldGenerator k) {
			gen = k;
		}

		@Override
		public ItemStack get() {
			return new ItemStack(ModRegistry.CRYSTAL_LOG, 1, ordinal());
		}

	}

	public static enum CrystalPlanks implements IPlankEnum {
		CRYSTAL,
		DARK_CRYSTAL;

		@Override
		public ItemStack get() {
			return new ItemStack(ModRegistry.CRYSTAL_PLANKS, 1, ordinal());
		}

		@Override
		public ItemStack genLogStack() {
			return new ItemStack(ModRegistry.CRYSTAL_LOG, 1, ordinal());
		}

	}

	public static enum Crystals implements IPropertyEnum {
		CRYSTAL_SHARD(true, Generic.CRYSTAL_SHARD),
		CRYSTAL_BLOCK(false),
		CRYSTAL_BRICK(false),
		DARK_CRYSTAL_SHARD(true, Generic.DARK_CRYSTAL_SHARD),
		DARK_CRYSTAL_BLOCK(false),
		DARK_CRYSTAL_BRICK(false),
		TITAN_STONE(false);

		private boolean isShard;
		private Generic drops;

		Crystals(boolean isShard, @Nullable Generic drops) {
			this.isShard = isShard;
			this.drops = drops;
		}

		Crystals(boolean isShard) {
			this(isShard, null);
		}

		public boolean isShard() {
			return isShard;
		}

		public ItemStack getDrops() {
			return drops != null ? drops.get(ThreadLocalRandom.current().nextInt(3) + 1).copy() : ItemStack.EMPTY;
		}

		@Override
		public ItemStack get() {
			return new ItemStack(ModRegistry.CRYSTAL, 1, ordinal());
		}

	}

}
