package shadows.plants2.block;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.client.RenamedStateMapper;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.itemblock.ItemBlockBase;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class BlockJar extends BlockFlowerpot implements IHasRecipe {

	public static final AxisAlignedBB AABB_MAIN = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
	public static final AxisAlignedBB AABB_KNOB = new AxisAlignedBB(0.3125D, 0.875D, 0.3125D, 0.5625D, 1.0D, 0.5625D);

	public BlockJar() {
		super("jar");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL(Plants2.MODID, "jar", this, 0, "inventory");
		PlaceboUtil.sMRL(Plants2.MODID, "jar", this, 5444, "glass");
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper(Plants2.MODID, "jar", "", "normal"));
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.TRANSLUCENT || layer == BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public ItemBlock createItemBlock() {
		return new ItemBlockBase(this);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_MAIN;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_MAIN);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_KNOB);
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addShaped(this, 3, 3, null, "plankWood", null, "blockGlass", null, "blockGlass", "blockGlass", "blockGlass", "blockGlass");
	}

}
