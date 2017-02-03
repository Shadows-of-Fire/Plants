package shadows.plants.common;

import java.util.Collections;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeEnd;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeForestMutated;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSavannaMutated;
import net.minecraft.world.biome.BiomeSnow;
import net.minecraft.world.biome.BiomeStoneBeach;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.biome.BiomeVoid;

public enum EnumTempZone {
	TROPICAL(1.2f, 1.6f),
	TEMPERATE(0.5f, 1.5f),
	COOL(0.0f, 0.8f),
	HOT(1.5f, 2.0f),
	;
	
	private float tempmax;
	private float tempmin;
	
	
	EnumTempZone(float min, float max){
		tempmax = max;
		tempmin = min;
		
	}
	
	
	public float getMax(){
		return tempmax;
	}
	
	public float getMin(){
		return tempmin;
	}
}

/*
        BiomePlains Temperature(0.8F)
        BiomeDesert Temperature(2.0F);
        BiomeHills Temperature(0.2F)
        BiomeForest Temperature(0.7F)
        BiomeTaiga Temperature(0.25F)
        BiomeSwamp Temperature(0.8F)
        BiomeHell Temperature(2.0F)
        BiomeSnow Temperature(0.0F)
        BiomeMushroomIsland Temperature(0.9F)
        BiomeBeach Temperature(0.8F)
        "forest_hills" Temperature(0.7F)
        "taiga_hills" Temperature(0.25F)
        BiomeJungle Temperature(0.95F)
        BiomeStoneBeach Temperature(0.2F)
        "cold_beach" Temperature(0.05F)
        "birch_forest" Temperature(0.6F)
        "roofed_forest" Temperature(0.7F)
        "taiga_cold" Temperature(-0.5F)
        "redwood_taiga" Temperature(0.3F)
        BiomeSavanna Temperature(1.2F)
        "savanna_rock" Temperature(1.0F)
        BiomeMesa Temperature(2.0F)
*/
