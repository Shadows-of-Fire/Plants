package shadows.plants.registry.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import shadows.plants.block.botania.BFarmland;
import shadows.plants.block.botania.BSoil;

public class BotaniaModule {

	/*
	 * The control module for the Botania Module of Plants.
	 * This will handle all registration which is then passed to the respective registry classes.
	 */
	public static List<Block> BLIST = new ArrayList<Block>();
	public static BSoil b_soil = new BSoil();
	public static BFarmland b_farmland = new BFarmland();

	
	public static List<Block> getB(){
		BLIST.clear();
		BLIST.add(b_soil);
		BLIST.add(b_farmland);
		return BLIST;
	}
}
