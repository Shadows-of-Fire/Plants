package shadows.plants2.compat;

import java.util.ArrayList;
import java.util.List;

import forestry.api.apiculture.FlowerManager;
import net.minecraft.block.state.IBlockState;
import shadows.plants2.block.BlockEnumDoubleBush;
import shadows.plants2.block.base.IEnumBlock;
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

}
