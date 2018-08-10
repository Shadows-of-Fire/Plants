package shadows.plants2.compat;

import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;

public class AAIntegration {

	public static void registerFarmerBehavior() {
		ActuallyAdditionsAPI.addFarmerBehavior(new HarvestBushFarmerBehavior());
	}

}
