package shadows.plants2.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IPostInitUpdate;
import shadows.plants2.data.enums.ILogBasedPropertyEnum;

public class EnumBasedTreeGen extends WorldGenTrees implements IPostInitUpdate {

	final ILogBasedPropertyEnum k;

	public EnumBasedTreeGen(boolean notify, int minHeight, IBlockState log, IBlockState leaf, boolean vines, ILogBasedPropertyEnum enumToAssignTo) {
		super(notify, minHeight, log, leaf, vines);
		Constants.UPDATES.add(this);
		k = enumToAssignTo;
	}

	public void postInit(FMLPostInitializationEvent e) {
		k.setTreeGen(this);
	}

}
