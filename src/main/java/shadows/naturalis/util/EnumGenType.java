package shadows.naturalis.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public enum EnumGenType {

	DESERT(Type.SANDY, Type.SAVANNA),
	SNOW(Type.SNOWY, Type.CONIFEROUS, Type.COLD),
	HELL(Type.NETHER),
	END(Type.END),
	JUNGLE(Type.JUNGLE),
	OCEAN(Type.OCEAN),
	RIVER(Type.RIVER),
	BEACH(Type.BEACH),
	SWAMP(Type.SWAMP, Type.MUSHROOM),
	GRASS(Type.PLAINS, Type.FOREST, Type.LUSH);

	public static final EnumGenType[] VALUES = values();
	public static final Map<Biome, EnumGenType> BIOME_TO_TYPE = new HashMap<>();

	final Set<Type> types = new HashSet<>();
	final List<IBlockState> states = new ArrayList<>();

	EnumGenType(Type... types) {
		for (Type t : types)
			this.types.add(t);
	}

	public List<IBlockState> getStates() {
		return states;
	}

	public Set<Type> getTypes() {
		return types;
	}

	public static EnumGenType byBiome(Biome biome) {
		return BIOME_TO_TYPE.get(biome);
	}

	public static void calcBiomeMap() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			for (EnumGenType t : VALUES) {
				Set<Type> bTypes = BiomeDictionary.getTypes(biome);
				if (bTypes.stream().anyMatch(t.types::contains)) {
					BIOME_TO_TYPE.put(biome, t);
					break;
				}
			}
		}
	}

}
