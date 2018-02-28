package shadows.plants2.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;
import shadows.plants2.Plants2;

public abstract class AbstractBiomeBase extends Biome {

	public AbstractBiomeBase(String regname, BiomeProperties properties) {
		super(properties);
		setRegistryName(Plants2.MODID, regname);
	}

	public abstract void register(IForgeRegistry<Biome> reg);

}