package shadows.plants2.data.enums;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nullable;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenerator;
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
