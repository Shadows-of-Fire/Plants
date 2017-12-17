package shadows.plants2.biome;

import net.minecraft.world.biome.Biome;
import shadows.plants2.data.Constants;

public abstract class AbstractBiomeBase extends Biome {

	public AbstractBiomeBase(String regname, BiomeProperties properties) {
		super(properties);
		setRegistryName(Constants.MODID, regname);
		register();
	}

	protected abstract void register();

}