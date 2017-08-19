package shadows.plants2.data.enums;

import shadows.plants2.data.StackPrimer;
import shadows.plants2.init.ModRegistry;

public class HarvestEnums {

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
		public StackPrimer[] getDrops() {
			return drops;
		}
	}

}