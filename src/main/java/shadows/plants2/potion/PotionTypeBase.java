package shadows.plants2.potion;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import shadows.plants2.Plants2;
import shadows.plants2.data.Constants;

public class PotionTypeBase extends PotionType {

	public PotionTypeBase(String name, PotionEffect... types) {
		super(Constants.MODID + "." + name, types);
		setRegistryName(Constants.MODID, name);
		Plants2.INFO.getPotionTypeList().add(this);
	}

}
