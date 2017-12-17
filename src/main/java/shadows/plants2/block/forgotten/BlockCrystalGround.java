package shadows.plants2.block.forgotten;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.client.event.ModelRegistryEvent;
import shadows.placebo.Placebo;
import shadows.placebo.block.base.BlockBasic;
import shadows.placebo.client.IHasModel;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class BlockCrystalGround extends BlockBasic implements IHasModel {

	public BlockCrystalGround() {
		super("groundcover", Material.GROUND, 0.4F, 20F, Plants2.INFO);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("blocks", this, 0, "type=groundcover");
		Placebo.PROXY.useRenamedMapper(this, "blocks", "", "type=groundcover");
	}

	@Override
	public int getLightValue(IBlockState state) {
		return 0;
	}

}
