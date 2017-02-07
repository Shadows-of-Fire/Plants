package shadows.plants.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import shadows.plants.common.EnumModule;
import shadows.plants.common.IModularThing;
import shadows.plants.util.Data;

public class FoodBase extends ItemFood implements IModularThing{

	private EnumModule module;
	private boolean isPoisoned;
	
	public FoodBase(String name, EnumModule module_, int amount, float saturation, boolean poison) {
		super(amount, saturation, false);
		setUnlocalizedName(Data.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(Data.TAB_I);
		module = module_;
		isPoisoned = poison;
	}

	@Override
	public EnumModule getType() {
		return module;
	}
	
	@Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote && isPoisoned)
        {
        	int dur = (int) (this.getHealAmount(stack) + this.getSaturationModifier(stack));
        	boolean amped = (dur >= 5);
        	int amp = 0;
        	if (amped) amp = 2;
        	if (dur > 20) amp = 4;
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(20), (20 * (dur * 2)), amp));
        }
    }

}
