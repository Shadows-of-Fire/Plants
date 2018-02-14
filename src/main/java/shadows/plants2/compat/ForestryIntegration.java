package shadows.plants2.compat;

import java.util.ArrayList;
import java.util.List;

import forestry.api.apiculture.FlowerManager;
import net.minecraft.block.state.IBlockState;
import shadows.placebo.block.IEnumBlock;
import shadows.placebo.interfaces.IFlowerEnum;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.plants2.block.BlockEnumDoubleFlower;
import shadows.plants2.util.PlantUtil;

public class ForestryIntegration {

	public static void registerFlowersToForestry() {

		if (FlowerManager.flowerRegistry == null) return;

		List<IBlockState> list = new ArrayList<IBlockState>();
		List<IBlockState> desertList = new ArrayList<IBlockState>();

		for (IBlockState state : PlantUtil.DEFAULT) {

			if (state.getBlock() instanceof BlockEnumDoubleFlower) continue;

			IPropertyEnum k;
			if (state.getBlock() instanceof IEnumBlock && (k = ((IEnumBlock<?>) state.getBlock()).getValue(state)) instanceof IFlowerEnum && ((IFlowerEnum) k).hasFlowers()) {
				list.add(state);
			}
		}

		for (IBlockState state : PlantUtil.DESERT) {

			if (state.getBlock() instanceof BlockEnumDoubleFlower) continue;

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

}
