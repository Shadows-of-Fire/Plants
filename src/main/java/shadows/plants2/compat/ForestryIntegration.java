package shadows.plants2.compat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import forestry.api.apiculture.FlowerManager;
import forestry.arboriculture.PluginArboriculture;
import forestry.arboriculture.genetics.TreeGenome;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import shadows.plants2.block.BlockEnumDoubleBush;
import shadows.plants2.block.base.IEnumBlock;
import shadows.plants2.data.Constants;
import shadows.plants2.data.enums.IFlowerEnum;
import shadows.plants2.data.enums.IPropertyEnum;
import shadows.plants2.util.PlantUtil;

public class ForestryIntegration {

	public static void registerFlowersToForestry() {
		List<IBlockState> list = new ArrayList<IBlockState>();
		List<IBlockState> desertList = new ArrayList<IBlockState>();

		for (IBlockState state : PlantUtil.DEFAULT) {

			if (state.getBlock() instanceof BlockEnumDoubleBush)
				continue;

			IPropertyEnum k;
			if (state.getBlock() instanceof IEnumBlock && (k = ((IEnumBlock<?>) state.getBlock()).getValue(state)) instanceof IFlowerEnum && ((IFlowerEnum) k).hasFlowers()) {
				list.add(state);
			}
		}

		for (IBlockState state : PlantUtil.DESERT) {

			if (state.getBlock() instanceof BlockEnumDoubleBush)
				continue;

			IPropertyEnum k;
			if (state.getBlock() instanceof IEnumBlock && (k = ((IEnumBlock<?>) state.getBlock()).getValue(state)) instanceof IFlowerEnum && ((IFlowerEnum) k).hasFlowers()) {
				desertList.add(state);
			}
		}

		for (IBlockState state : list) {
			FlowerManager.flowerRegistry.registerAcceptableFlower(state, FlowerManager.FlowerTypeVanilla);
			FlowerManager.flowerRegistry.registerPlantableFlower(state, 0.85D, FlowerManager.FlowerTypeVanilla);
		}

		for (IBlockState state : desertList) {
			FlowerManager.flowerRegistry.registerAcceptableFlower(state, FlowerManager.FlowerTypeCacti);
			FlowerManager.flowerRegistry.registerPlantableFlower(state, 0.85D, FlowerManager.FlowerTypeCacti);
		}
	}

	public static class ForestryFlowerpot implements IFlowerpotHandler {

		@Override
		public String handleFlowerpot(IBlockState state, ItemStack stack) {
			String[] names;
			if (stack.getItem() == PluginArboriculture.getItems().sapling) {
				names = TreeGenome.getSpecies(stack).getAlleleName().toLowerCase(Locale.ROOT).split(" ");
				return "sapling_" + names[names.length - 1];
			}

			return "none";
		}

		@Override
		public String getModId() {
			return Constants.FORESTRY_ID;
		}

		@Override
		public String getStatePrefix() {
			return "f_";
		}

	}

}
