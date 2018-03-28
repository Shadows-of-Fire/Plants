package shadows.plants2.compat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.potions.IPotion;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.Potion;
import shadows.plants2.util.ColorToPotionUtil;
import shadows.plants2.util.ColorToPotionWeightedMap.WeightedPotion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.plants.BrewingCauldron")
public class CrafttweakerIntegration {

	private static List<Triple<EnumDyeColor, Potion, Integer>> additions = new ArrayList<>();
	private static List<Pair<EnumDyeColor, Potion>> removals = new ArrayList<>();
	private static List<EnumDyeColor> clears = new ArrayList<>();

	@ZenMethod
	public static void addWeightedPotion(String dyeColor, IPotion potion, int weight) {
		EnumDyeColor color = EnumDyeColor.valueOf(dyeColor.toUpperCase(Locale.ENGLISH));
		Validate.notNull(color, "Invalid EnumDyeColor %s!", dyeColor);
		Validate.isTrue(potion.getInternal() != null && potion.getInternal() instanceof Potion, "Invalid potion %s!", potion);
		Validate.isTrue(weight > 0, "Invalid weight %s, must be positive!", weight);
		additions.add(Triple.of(color, (Potion) potion.getInternal(), weight));
	}

	@ZenMethod
	public static void removeWeightedPotion(String dyeColor, IPotion potion) {
		EnumDyeColor color = EnumDyeColor.valueOf(dyeColor.toUpperCase(Locale.ENGLISH));
		Validate.notNull(color, "Invalid EnumDyeColor %s!", dyeColor);
		Validate.isTrue(potion.getInternal() != null && potion.getInternal() instanceof Potion, "Invalid potion %s!", potion);
		removals.add(Pair.of(color, (Potion) potion.getInternal()));
	}

	@ZenMethod
	public static void clearWeightedList(String dyeColor) {
		EnumDyeColor color = EnumDyeColor.valueOf(dyeColor.toUpperCase(Locale.ENGLISH));
		Validate.notNull(color, "Invalid EnumDyeColor %s!", dyeColor);
		clears.add(color);
	}

	public static void processCauldronChanges() {
		for (EnumDyeColor color : clears) {
			ColorToPotionUtil.MAP.getPotionList(color).clear();
		}
		for (Pair<EnumDyeColor, Potion> p : removals) {
			WeightedPotion pot = null;
			List<WeightedPotion> list = ColorToPotionUtil.MAP.getPotionList(p.getLeft());
			for (WeightedPotion wp : list)
				if (wp.getPotion().delegate.get() == p.getRight().delegate.get()) {
					pot = wp;
					break;
				}
			if (pot != null) list.remove(pot);
			else CraftTweakerAPI.logError("[Plants] Tried to remove a weighted potion entry, but no entry was found. Color: " + p.getLeft() + ", Potion: " + p.getRight().getRegistryName());

		}
		for (Triple<EnumDyeColor, Potion, Integer> t : additions) {
			ColorToPotionUtil.MAP.getPotionList(t.getLeft()).add(new WeightedPotion(t.getMiddle(), t.getRight()));
		}

		for (Map.Entry<EnumDyeColor, List<WeightedPotion>> ent : ColorToPotionUtil.MAP.entrySet())
			if (ent.getValue().isEmpty()) throw new RuntimeException("[Plants] All WeightedPotions have been removed from the " + ent.getKey() + " list, this is not allowed!");

		additions = null;
		removals = null;
		clears = null;
	}

}
