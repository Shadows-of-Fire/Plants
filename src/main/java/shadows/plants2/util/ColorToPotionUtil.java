package shadows.plants2.util;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.tile.TileBrewingCauldron;

public class ColorToPotionUtil {

	public static final Map<EnumDyeColor, Potion> MAP = new HashMap<>();

	public static final Random RAND = new Random(ThreadLocalRandom.current().nextLong());

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

	public static int[] colorsToIntArray(EnumDyeColor[] colors) {
		int[] ret = new int[6];
		for (int i = 0; i < 6; i++)
			ret[i] = colors[i] == null ? -1 : colors[i].getMetadata();
		return ret;
	}

	public static EnumDyeColor[] intsToColorArray(int[] input) {
		if (input.length < 6) return new EnumDyeColor[6];
		EnumDyeColor[] colors = new EnumDyeColor[6];
		for (int i = 0; i < 6; i++)
			colors[i] = input[i] == -1 ? null : EnumDyeColor.byMetadata(input[i]);
		return colors;
	}

	public static boolean isDyeArrayValid(EnumDyeColor[] colors) {
		for (int i = 0; i < 6; i++)
			if (colors[i] == null) return false;
		return true;
	}

	public static Potion[] colorsToPotionArray(EnumDyeColor[] colors) {
		Potion[] pots = new Potion[6];
		for (int i = 0; i < 6; i++)
			pots[i] = MAP.get(colors[i]);
		return pots;
	}

	public static ItemStack genPotionStack(TileBrewingCauldron cauldron) {
		ItemStack stack = new ItemStack(cauldron.getPotionItem());
		PotionUtils.addPotionToItemStack(stack, ModRegistry.CAULDRON_BREW);
		Map<Potion, Integer> numPots = new HashMap<>();
		for (Potion pot : colorsToPotionArray(cauldron.getColors())) {
			numPots.put(pot, numPots.getOrDefault(pot, 0) + 1);
		}

		PotionEffect[] potEffs = new PotionEffect[3];
		int i = 0;

		for (Entry<Potion, Integer> ent : numPots.entrySet()) {
			if (i >= 3) break;
			int duration = (ent.getValue() % 3 + 1) * (720 * (RAND.nextInt(3) + 1));
			int multiplier = (ent.getValue() % 2) * ((RAND.nextInt(2) + 1));
			if (ent.getKey().isInstant()) duration = 0;
			if(cauldron.getPotionItem() == Items.LINGERING_POTION) duration /= 6;
			potEffs[i++] = new PotionEffect(ent.getKey(), duration, multiplier);
		}

		if (potEffs[2] == null) potEffs = new PotionEffect[] { potEffs[0], potEffs[1] };
		if (potEffs[1] == null) potEffs = new PotionEffect[] { potEffs[0] };

		PotionUtils.appendEffects(stack, Arrays.asList(potEffs));
		return stack;
	}

	public static int getColorMultiplier(EnumDyeColor[] colors, boolean wart) {
		int j = 6;
		for (int i = 0; i < 6; i++) {
			if (colors[i] == null) {
				j = i;
				break;
			}
		}

		if (j == 0 && wart) return Color.RED.getRGB();
		else if (j == 0 && !wart) return -1;

		int r = 0;
		int g = 0;
		int b = 0;

		for (int i = 0; i < j; i++) {
			Color col = new Color(colors[i].getColorValue());
			r += col.getRed();
			g += col.getGreen();
			b += col.getBlue();
		}
		r /= j;
		b /= j;
		g /= j;

		Color newC = new Color(r, g, b);
		return newC.getRGB();
	}

}
