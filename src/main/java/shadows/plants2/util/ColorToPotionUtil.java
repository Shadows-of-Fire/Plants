package shadows.plants2.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.Potion;

public class ColorToPotionUtil {

	public static final Map<EnumDyeColor, Potion> MAP = new HashMap<>();

	static {
		MAP.put(EnumDyeColor.WHITE, MobEffects.GLOWING);
		MAP.put(EnumDyeColor.ORANGE, MobEffects.FIRE_RESISTANCE);
		MAP.put(EnumDyeColor.MAGENTA, MobEffects.SLOWNESS);
		MAP.put(EnumDyeColor.LIGHT_BLUE, MobEffects.JUMP_BOOST);
		MAP.put(EnumDyeColor.YELLOW, MobEffects.NAUSEA);
		MAP.put(EnumDyeColor.LIME, MobEffects.MINING_FATIGUE);
		MAP.put(EnumDyeColor.PINK, MobEffects.REGENERATION);
		MAP.put(EnumDyeColor.GRAY, MobEffects.SPEED);
		MAP.put(EnumDyeColor.SILVER, MobEffects.RESISTANCE);
		MAP.put(EnumDyeColor.CYAN, MobEffects.UNLUCK);
		MAP.put(EnumDyeColor.PURPLE, MobEffects.NIGHT_VISION);
		MAP.put(EnumDyeColor.BLUE, MobEffects.WATER_BREATHING);
		MAP.put(EnumDyeColor.BROWN, MobEffects.SATURATION);
		MAP.put(EnumDyeColor.GREEN, MobEffects.LUCK);
		MAP.put(EnumDyeColor.RED, MobEffects.INSTANT_HEALTH);
		MAP.put(EnumDyeColor.BLACK, MobEffects.INSTANT_DAMAGE);
	}
	
}
