package shadows.plants2.block.forgotten;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockBasic;
import shadows.plants2.client.IHasModel;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.util.PlantUtil;

public class BlockCrystalGround extends BlockBasic implements IHasModel {

	public BlockCrystalGround() {
		super("groundcover", Material.GROUND, 0.4F, 20F, false);
		setSoundType(SoundType.GLASS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlantUtil.sMRL("blocks", this, 0, "type=groundcover");
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("blocks", "", "type=groundcover"));
	}

	@Override
	public int getLightValue(IBlockState state) {
		return 8;
	}

}
//TODO: delete this
