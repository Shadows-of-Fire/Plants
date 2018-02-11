package shadows.plants2.potion;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import shadows.plants2.Plants2;

public class PotionTypeBase extends PotionType {

	public PotionTypeBase(String name, PotionEffect... types) {
		super(Plants2.MODID + "." + name, types);
		setRegistryName(Plants2.MODID, name);
		Plants2.INFO.getPotionTypeList().add(this);
	}

}
