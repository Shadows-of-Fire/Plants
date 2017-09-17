package shadows.plants2.data.enums;

import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import shadows.plants2.data.StackPrimer;
import shadows.plants2.data.enums.TheBigBookOfEnums.Logs;
import shadows.plants2.data.enums.TheBigBookOfEnums.NetherLogs;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.util.RecipeHelper;

public class LaterEnums {

	public static enum Harvestable implements IHarvestableEnum {

		ACTAEA_P(new StackPrimer(ModRegistry.ACTAEA_P)),
		ALTERNANTHERA_F(new StackPrimer(ModRegistry.ALTERNANTHERA_F)),
		AMBROSIA_A(new StackPrimer(ModRegistry.AMBROSIA_A)),
		APOCYNUM_C(new StackPrimer(ModRegistry.APOCYNUM_C), new StackPrimer(ModRegistry.GENERIC)),
		DAUCUS_C(new StackPrimer(ModRegistry.DAUCUS_C)),
		PHYTOLACCA_A(new StackPrimer(ModRegistry.PHYTOLACCA_A)),
		PLANTAGO_M(new StackPrimer(ModRegistry.PLANTAGO_M)),
		RUBUS_O(new StackPrimer(ModRegistry.RUBUS_O)),
		RUBUS_P(new StackPrimer(ModRegistry.RUBUS_P)),
		SAFFRON(new StackPrimer(ModRegistry.SAFFRON)),
		SOLANUM_C(new StackPrimer(ModRegistry.SOLANUM_C)),
		SOLANUM_D(new StackPrimer(ModRegistry.SOLANUM_D)),
		SOLANUM_N(new StackPrimer(ModRegistry.SOLANUM_N)),;

		private StackPrimer[] drops;

		Harvestable(StackPrimer... input) {
			drops = input;
		}

		@Override
		public StackPrimer[] getDrops() {
			return drops;
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

	public static enum DoubleHarvestable implements IHarvestableEnum {
		ALYXIA_B(new StackPrimer(ModRegistry.ALYXIA_B)),;

		private StackPrimer[] drops;

		DoubleHarvestable(StackPrimer... input) {
			drops = input;
		}

		@Override
		public int getPredicateIndex() {
			return this.ordinal() / 4;
		}

		@Override
		public int getMetadata() {
			return this.ordinal() % 4;
		}

		@Override
		public StackPrimer[] getDrops() {
			return drops;
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

	public static enum Planks implements IPlankEnum {
		ASH(new StackPrimer(ModRegistry.NETHER_LOG, 1, NetherLogs.ASH.getMetadata())),
		BLAZE(new StackPrimer(ModRegistry.NETHER_LOG, 1, NetherLogs.BLAZE.getMetadata())),
		BLACK_KAURI(new StackPrimer(ModRegistry.LOG_0, 1, Logs.BLACK_KAURI.getMetadata())),
		BRAZILLIAN_PINE(new StackPrimer(ModRegistry.LOG_0, 1, Logs.BRAZILLIAN_PINE.getMetadata())),
		INCENSE_CEDAR(new StackPrimer(ModRegistry.LOG_0, 1, Logs.INCENSE_CEDAR.getMetadata())),
		MURRAY_PINE(new StackPrimer(ModRegistry.LOG_0, 1, Logs.MURRAY_PINE.getMetadata())),;

		private StackPrimer primer;

		Planks(StackPrimer primer) {
			this.primer = primer;
		}

		@Override
		public ItemStack genLogStack() {
			return primer.genStack();
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

}