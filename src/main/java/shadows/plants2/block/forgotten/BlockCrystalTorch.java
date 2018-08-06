package shadows.plants2.block.forgotten;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.client.IHasModel;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.itemblock.ItemBlockBase;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;
import shadows.plants2.client.ParticleWhiteFlame;
import shadows.plants2.data.enums.TheBigBookOfEnums.Generic;

public class BlockCrystalTorch extends BlockTorch implements IHasModel, IHasRecipe {

	public BlockCrystalTorch() {
		setRegistryName("crystal_torch");
		setTranslationKey(Plants2.MODID + ".crystal_torch");
		Plants2.INFO.getBlockList().add(this);
		Plants2.INFO.getItemList().add(new ItemBlockBase(this));
		setHardness(0);
		setLightLevel(.9375F);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("blocks", this, 0, "type=" + getRegistryName().getPath());
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addShaped(this, 1, 2, Generic.CRYSTAL_CHUNK.get(), Generic.CRYSTAL_STICK.get());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		EnumFacing enumfacing = state.getValue(FACING);
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;

		ParticleWhiteFlame p;

		if (enumfacing.getAxis().isHorizontal()) {
			EnumFacing enumfacing1 = enumfacing.getOpposite();
			p = new ParticleWhiteFlame(world, d0 + 0.27D * enumfacing1.getXOffset(), d1 + 0.22D, d2 + 0.27D * enumfacing1.getZOffset(), 0.0D, 0.0D, 0.0D);
		} else p = new ParticleWhiteFlame(world, d0, d1, d2, 0.0D, 0.0D, 0.0D);

		Minecraft.getMinecraft().effectRenderer.addEffect(p);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
