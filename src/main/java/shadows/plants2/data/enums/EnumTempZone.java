package shadows.plants2.data.enums;

import net.minecraft.world.biome.Biome;

public enum EnumTempZone {
	TROPICAL(0.8f, 1.2f),
	TEMPERATE(0.5f, 1.5f),
	COLD(-0.5f, 0.3f),
	COOL(0.0f, 0.8f),
	FROZEN(-5.0f, 0.0f),
	HOT(1.5f, 2.0f),
	BURNING(2.0f, 5.0f),
	ALL(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY),;

	private float tempmax;
	private float tempmin;

	EnumTempZone(float min, float max) {
		tempmax = max;
		tempmin = min;

	}

	public float getMax() {
		return tempmax;
	}

	public float getMin() {
		return tempmin;
	}

	public boolean isBiomeValid(Biome b) {
		float k = b.getTemperature();
		return k <= tempmax && k >= tempmin;
	}
}

/*
 * BiomePlains Temperature(0.8F) 
 * BiomeDesert Temperature(2.0F); 
 * BiomeHills Temperature(0.2F) 
 * BiomeForest Temperature(0.7F) 
 * BiomeTaiga Temperature(0.25F)
 * BiomeSwamp Temperature(0.8F) 
 * BiomeHell Temperature(2.0F) 
 * BiomeSnow Temperature(0.0F) 
 * BiomeMushroomIsland Temperature(0.9F) 
 * BiomeBeach Temperature(0.8F) 
 * "forest_hills" Temperature(0.7F) 
 * "taiga_hills" Temperature(0.25F) 
 * BiomeJungle Temperature(0.95F) 
 * BiomeStoneBeach Temperature(0.2F) 
 * "cold_beach" Temperature(0.05F) 
 * "birch_forest" Temperature(0.6F) 
 * "roofed_forest" Temperature(0.7F) 
 * "taiga_cold" Temperature(-0.5F) 
 * "redwood_taiga" Temperature(0.3F) 
 * BiomeSavanna Temperature(1.2F) 
 * "savanna_rock" Temperature(1.0F) 
 * BiomeMesa Temperature(2.0F)
 */
