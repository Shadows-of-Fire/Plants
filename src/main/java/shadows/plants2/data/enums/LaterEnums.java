package shadows.plants2.data.enums;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import shadows.placebo.interfaces.IHarvestableEnum;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.placebo.util.RecipeHelper;
import shadows.placebo.util.StackPrimer;
import shadows.plants2.data.enums.TheBigBookOfEnums.CrystalLogs;
import shadows.plants2.data.enums.TheBigBookOfEnums.Generic;
import shadows.plants2.data.enums.TheBigBookOfEnums.Logs;
import shadows.plants2.data.enums.TheBigBookOfEnums.NetherLogs;
import shadows.plants2.init.ModRegistry;

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
		SOLANUM_N(new StackPrimer(ModRegistry.SOLANUM_N)),
		TAHITIAN_SPINACH(new StackPrimer(ModRegistry.TAHITIAN_SPINACH)),
		;

		StackPrimer[] drops;
		Block block;

		Harvestable(StackPrimer... input) {
			drops = input;
		}

		@Override
		public StackPrimer[] getDrops() {
			return drops;
		}

		@Override
		public ItemStack getAsStack() {
			return RecipeHelper.makeStack(block);
		}
		
		@Override
		public IBlockState getAsState() {
			return block.getDefaultState();
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			block = (Block) ifre;
		}
	}

	public static enum NetherHarvests implements IHarvestableEnum {
		BLAZE(new StackPrimer(ModRegistry.GENERIC, 1, Generic.BLAZE_PETAL.ordinal())),
		MAGMA(new StackPrimer(ModRegistry.GENERIC, 1, Generic.MAGMA_JELLY.ordinal())),
		MELON(new StackPrimer(Items.SPECKLED_MELON)),
		FIRE_FRUIT(new StackPrimer(ModRegistry.FIRE_FRUIT)),
		EMBERROOT(new StackPrimer(ModRegistry.GENERIC, 1, Generic.EMBERROOT.ordinal())),
		SMOLDERBERRY(new StackPrimer(ModRegistry.GENERIC, 1, Generic.SMOLDERBERRY.ordinal())),;

		StackPrimer[] drops;
		Block block;

		NetherHarvests(StackPrimer... inputs) {
			drops = inputs;
		}

		@Override
		public StackPrimer[] getDrops() {
			return drops;
		}

		@Override
		public ItemStack getAsStack() {
			return RecipeHelper.makeStack(block);
		}
		
		@Override
		public IBlockState getAsState() {
			return block.getDefaultState();
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			block = (Block) ifre;
		}

	}

	public static enum DoubleHarvestable implements IHarvestableEnum {
		ALYXIA_B(new StackPrimer(ModRegistry.ALYXIA_B)),;

		StackPrimer[] drops;
		Block block;

		DoubleHarvestable(StackPrimer... input) {
			drops = input;
		}

		@Override
		public StackPrimer[] getDrops() {
			return drops;
		}

		@Override
		public ItemStack getAsStack() {
			return RecipeHelper.makeStack(block);
		}
		
		@Override
		public IBlockState getAsState() {
			return block.getDefaultState();
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			block = (Block) ifre;
		}
	}

	public static enum Planks implements IPropertyEnum {
		ASH(new StackPrimer(NetherLogs.ASH)),
		BLAZE(new StackPrimer(NetherLogs.BLAZE)),
		BLACK_KAURI(new StackPrimer(Logs.BLACK_KAURI)),
		BRAZILLIAN_PINE(new StackPrimer(Logs.BRAZILLIAN_PINE)),
		INCENSE_CEDAR(new StackPrimer(Logs.INCENSE_CEDAR)),
		MURRAY_PINE(new StackPrimer(Logs.MURRAY_PINE)),
		CRYSTAL(new StackPrimer(CrystalLogs.CRYSTAL)),
		DARK_CRYSTAL(new StackPrimer(CrystalLogs.DARK_CRYSTAL));

		StackPrimer primer;
		Block block;

		Planks(StackPrimer primer) {
			this.primer = primer;
		}

		public ItemStack genLogStack() {
			return primer.genStack();
		}

		@Override
		public ItemStack getAsStack() {
			return RecipeHelper.makeStack(block);
		}
		
		@Override
		public IBlockState getAsState() {
			return block.getDefaultState();
		}

		@Override
		public void set(IForgeRegistryEntry<?> ifre) {
			block = (Block) ifre;
		}
	}

}