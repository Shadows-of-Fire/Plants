package shadows.plants2.proxy;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.client.particle.ParticleSimpleAnimated;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.plants2.client.IHasModel;
import shadows.plants2.compat.BinnieIntegration;
import shadows.plants2.data.Config;
import shadows.plants2.data.Constants;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.tile.TileBrewingCauldron;
import shadows.plants2.tile.TileFlowerpot;
import shadows.plants2.util.ColorToPotionUtil;

public class ClientProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		if (Config.flowerpot) Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> {
			if (tint == 1) return world.getBiome(pos).getGrassColorAtPos(pos);

			TileEntity t = world.getTileEntity(pos);

			if (Loader.isModLoaded(Constants.BOTANY_ID) && tint >= 10 && t instanceof TileFlowerpot) { return BinnieIntegration.colorMultiplier(state, world, pos, tint); }
			return -1;
		}, ModRegistry.FLOWERPOT);

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> {
			TileEntity t = world.getTileEntity(pos);
			if (!(t instanceof TileBrewingCauldron) || tint != 1) return -1;
			TileBrewingCauldron caul = (TileBrewingCauldron) t;
			int color = ColorToPotionUtil.getColorMultiplier(caul.getColors(), caul.hasFirstWart());
			return color == -1 ? Color.BLUE.getRGB() : color;
		}, ModRegistry.BREWING_CAULDRON);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {

	}

	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent e) {
		for (Block b : ModRegistry.BLOCKS)
			if (b instanceof IHasModel) ((IHasModel) b).initModels(e);
		for (Item i : ModRegistry.ITEMS)
			if (i instanceof IHasModel) ((IHasModel) i).initModels(e);
	}

	public static final Color WATER = new Color(48, 69, 244);

	@Override
	public void doCauldronParticles(IBlockState state, World world, BlockPos pos, Random rand) {
		TileEntity t = world.getTileEntity(pos);
		if (t instanceof TileBrewingCauldron) {
			TileBrewingCauldron caul = (TileBrewingCauldron) t;
			if (!caul.hasMaxWater() && !caul.isBeingExtracted()) return;
			int color = ColorToPotionUtil.getColorMultiplier(caul.getColors(), caul.hasFirstWart());
			if (color == -1) color = WATER.getRGB();
			for (int i = 0; i < rand.nextInt(getLastNonNull(caul.getColors()) * 5 + 4); i++) {
				ParticleSimpleAnimated p = new ParticleEndRod(world, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, MathHelper.nextDouble(rand, -0.05, 0.05), getLastNonNull(caul.getColors()) == 6 ? 0.3 : 0.08, MathHelper.nextDouble(rand, -0.05, 0.05));
				p.setColor(color);
				p.setColorFade(color);
				Minecraft.getMinecraft().effectRenderer.addEffect(p);
			}
		}
	}

	@Override
	public void doCauldronInputParticles(BlockPos pos) {
		World world = Minecraft.getMinecraft().world;
		Random rand = world.rand;
		TileEntity t = world.getTileEntity(pos);
		if (t instanceof TileBrewingCauldron) {
			TileBrewingCauldron caul = (TileBrewingCauldron) t;
			int color = ColorToPotionUtil.getColorMultiplier(caul.getColors(), caul.hasFirstWart());
			if (color == -1) color = WATER.getRGB();
			for (int i = 0; i < 30; i++) {
				ParticleSimpleAnimated p = new ParticleEndRod(world, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, MathHelper.nextDouble(rand, -0.1, 0.1), 0.12, MathHelper.nextDouble(rand, -0.1, 0.1));
				p.setColor(color);
				p.setColorFade(color);
				Minecraft.getMinecraft().effectRenderer.addEffect(p);
			}
		}
	}

	private static int getLastNonNull(Object[] array) {
		int j = array.length;
		for (int i = 0; i < j; i++) {
			if (array[i] == null) {
				j = i;
				break;
			}
		}
		return j;
	}
	
	@Override
	public String translate(String lang, Object... args) {
		return I18n.format(lang, args);
	}

}
