package shadows.plants2.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;

public abstract class AbstractBiomeBase extends Biome {

	public AbstractBiomeBase(String regname, BiomeProperties properties) {
		super(properties);
		setRegistryName(Constants.MODID, regname);
		ModRegistry.BIOMES.add(this);
		register();
	}

	protected void register() {
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(this, 50));
	}

}