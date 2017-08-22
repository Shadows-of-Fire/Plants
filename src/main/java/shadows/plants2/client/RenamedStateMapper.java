package shadows.plants2.client;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.data.Constants;

@SideOnly(Side.CLIENT)
public class RenamedStateMapper implements IStateMapper {

	final String path;
	String append = "";

	public RenamedStateMapper(String path) {
		this.path = path;
	}
	
	public RenamedStateMapper(String path, String append) {
		this.path = path;
		this.append = append;
	}

	@Override
	public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block block) {
		Map<IBlockState, ModelResourceLocation> map = new DefaultStateMapper().putStateModelLocations(block);
		for (IBlockState state : map.keySet()) {
			ModelResourceLocation loc = map.get(state);
			map.put(state, new ModelResourceLocation(Constants.MODID + ":" + path, loc.getVariant() + append));
		}
		return map;
	}

}
