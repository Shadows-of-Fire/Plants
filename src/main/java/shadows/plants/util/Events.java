package shadows.plants.util;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.plants.block.SoilBase;


@Mod.EventBusSubscriber
public class Events {

	
	@SubscribeEvent
	public static void till(UseHoeEvent event){
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		if (world.getBlockState(pos).getBlock() instanceof SoilBase){
		Block soil = world.getBlockState(pos).getBlock();
		Block farmland = Util.getFarmlandFromModule(((IModularThing) soil).getType());
		world.setBlockState(pos, farmland.getDefaultState(), 3);
		event.getEntityPlayer().playSound(SoundEvents.ITEM_HOE_TILL, 1, 1);
		event.setResult(Result.ALLOW);
		}
	}
}
