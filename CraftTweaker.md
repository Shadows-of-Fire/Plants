As of Plants 2.8.0, the Magical Brewing Cauldron has recieved CraftTweaker support.

This allows pack devs to customize which potions a specific color can give.  Each EnumDyeColor now has a weighted list associated with it.  This list is what the cauldron uses when generating the potion stack.  Potions are pulled randomly with more favor towards those with a higher weight.  An example script is below.

```
import mods.plants.BrewingCauldron;

BrewingCauldron.addWeightedPotion("red", <potion:botania:bloodthirst>, 5);
BrewingCauldron.addWeightedPotion("black", <potion:botania:emptiness>, 90);
BrewingCauldron.addWeightedPotion("red", <potion:minecraft:glowing>, 5);

BrewingCauldron.clearWeightedList("black");

BrewingCauldron.removeWeightedPotion("red", <potion:minecraft:instant_health>);
```

There are three methods in total.  `addWeightedPotion(String, IPotion, int)`, `clearWeightedList(String)`, and `removeWeightedPotion(String, IPotion)`.  In each case, the string must represent an EnumDyeColor, it is not case sensitive.  
`addWeightedPotion` will add the provided potion to the specific color list, with the given weight.
`clearWeightedList` will clear the entire list for that color.
`removeWeightedPotion` will try to remove only a single potion from a color list.  Not exactly useful currently since each list only contains one color by default, and you could just use `clearWeightedList` to the same effect.

All scripts are run during the FMLInitializationEvent.  Clears are processed first, then removals, then finally additions.

The default color -> potion map may be found [here](https://github.com/Shadows-of-Fire/Plants/blob/master/src/main/java/shadows/plants2/util/ColorToPotionWeightedMap.java#L60).  All default entries have a weight of 10.
