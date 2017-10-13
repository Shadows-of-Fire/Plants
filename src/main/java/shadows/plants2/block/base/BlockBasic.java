package shadows.plants2.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.client.IHasModel;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.util.PlantUtil;

public abstract class BlockBasic extends Block implements IHasModel {

	public BlockBasic(String name, Material material, float hardness, float resist, boolean customItemBlock, boolean addToList) {
		super(material);
		setRegistryName(Constants.MODID, name);
		setUnlocalizedName(Constants.MODID + "." + name);
		setHardness(hardness);
		setResistance(resist);
		setCreativeTab(ModRegistry.TAB);
		if (addToList) ModRegistry.BLOCKS.add(this);
		if (!customItemBlock) ModRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	public BlockBasic(String name, Material material, float hardness, float resist, boolean customItemBlock) {
		this(name, material, hardness, resist, customItemBlock, true);
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlantUtil.sMRL("blocks", this, 0, "type=" + getRegistryName().getResourceDomain());
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("blocks"));
	}

}
