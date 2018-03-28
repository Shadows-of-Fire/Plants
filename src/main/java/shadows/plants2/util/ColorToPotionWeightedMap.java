package shadows.plants2.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandom;

public class ColorToPotionWeightedMap {

	final ImmutableMap<EnumDyeColor, List<WeightedPotion>> internal;

	public ColorToPotionWeightedMap() {
		ImmutableMap.Builder<EnumDyeColor, List<WeightedPotion>> builder = ImmutableMap.builder();
		for (Pair<EnumDyeColor, Potion> pair : defaultColors) {
			ArrayList<WeightedPotion> list = new ArrayList<>();
			list.add(new WeightedPotion(pair.getRight(), 10));
			builder.put(pair.getKey(), list);
		}
		internal = builder.build();
	}

	public Potion getRandomPotion(EnumDyeColor color) {
		return WeightedRandom.getRandomItem(ColorToPotionUtil.RAND, internal.get(color)).getPotion();
	}

	public List<WeightedPotion> getPotionList(EnumDyeColor color) {
		return internal.get(color);
	}

	public ImmutableSet<Entry<EnumDyeColor, List<WeightedPotion>>> entrySet() {
		return internal.entrySet();
	}

	public static class WeightedPotion extends WeightedRandom.Item {

		final Potion potion;

		public WeightedPotion(Potion potion, int weight) {
			super(weight);
			this.potion = potion;
		}

		public Potion getPotion() {
			return potion;
		}

	}

	//Formatter::off
	private static List<Pair<EnumDyeColor, Potion>> defaultColors = ImmutableList.of(
			Pair.of(EnumDyeColor.WHITE, MobEffects.GLOWING),
			Pair.of(EnumDyeColor.ORANGE, MobEffects.FIRE_RESISTANCE),
			Pair.of(EnumDyeColor.MAGENTA, MobEffects.SLOWNESS),
			Pair.of(EnumDyeColor.LIGHT_BLUE, MobEffects.JUMP_BOOST),
			Pair.of(EnumDyeColor.YELLOW, MobEffects.NAUSEA),
			Pair.of(EnumDyeColor.LIME, MobEffects.MINING_FATIGUE),
			Pair.of(EnumDyeColor.PINK, MobEffects.REGENERATION),
			Pair.of(EnumDyeColor.GRAY, MobEffects.SPEED),
			Pair.of(EnumDyeColor.SILVER, MobEffects.RESISTANCE),
			Pair.of(EnumDyeColor.CYAN, MobEffects.STRENGTH),
			Pair.of(EnumDyeColor.PURPLE, MobEffects.NIGHT_VISION),
			Pair.of(EnumDyeColor.BLUE, MobEffects.WATER_BREATHING),
			Pair.of(EnumDyeColor.BROWN, MobEffects.SATURATION),
			Pair.of(EnumDyeColor.GREEN, MobEffects.LUCK),
			Pair.of(EnumDyeColor.RED, MobEffects.INSTANT_HEALTH),
			Pair.of(EnumDyeColor.BLACK, MobEffects.INSTANT_DAMAGE));

}
