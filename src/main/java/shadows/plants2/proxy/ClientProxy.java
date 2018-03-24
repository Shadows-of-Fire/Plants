package shadows.plants2.proxy;

import java.awt.Color;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.client.particle.ParticleSimpleAnimated;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.registries.IRegistryDelegate;
import shadows.placebo.client.IHasModel;
import shadows.plants2.Plants2;
import shadows.plants2.client.ActualFlowerpotModel;
import shadows.plants2.client.ActualJarModel;
import shadows.plants2.client.FlowerpotModel;
import shadows.plants2.client.JarItemModel;
import shadows.plants2.client.JarModel;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.tile.TileBrewingCauldron;
import shadows.plants2.tile.TileFlowerpot;
import shadows.plants2.util.ColorToPotionUtil;

public class ClientProxy implements IProxy {

	public static boolean aoConstant = true;

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		aoConstant = ForgeModContainer.forgeLightPipelineEnabled && !Loader.isModLoaded("optifine");
	}

	public static final int GROUND_COLOR = 0xA3CBF7;
	public static final int BLUE = 0x4749FF;

	@Override
	public void init(FMLInitializationEvent e) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> {
			if (tint != 1) return -1;

			TileEntity t = world.getTileEntity(pos);
			if (!(t instanceof TileBrewingCauldron)) return -1;
			TileBrewingCauldron caul = (TileBrewingCauldron) t;
			int color = ColorToPotionUtil.getColorMultiplier(caul.getColors(), caul.hasFirstWart());
			return color == -1 ? BLUE : color;
		}, ModRegistry.BREWING_CAULDRON);

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tint) -> {
			return GROUND_COLOR;
		}, ModRegistry.GROUNDCOVER);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tint) -> {
			return GROUND_COLOR;
		}, ModRegistry.GROUNDCOVER);

		//Vanilla Copy :: Added line to avoid crash with nested pot
		IBlockColor color = (state, world, pos, tint) -> {
			if (world != null && pos != null) {
				TileEntity tileentity = world.getTileEntity(pos);
				if (tileentity instanceof TileFlowerpot && ((TileFlowerpot) tileentity).getState().getBlock() != state.getBlock()) {
					Item item = ((TileFlowerpot) tileentity).getFlowerPotItem();
					IBlockState iblockstate = Block.getBlockFromItem(item).getDefaultState();
					return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, world, pos, tint);
				}
			}
			return -1;
		};

		Map<IRegistryDelegate<Block>, IBlockColor> blockBois = ReflectionHelper.getPrivateValue(BlockColors.class, Minecraft.getMinecraft().getBlockColors(), "blockColorMap");
		blockBois.put(Blocks.FLOWER_POT.delegate, color);
		blockBois.put(ModRegistry.JAR.delegate, color);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		ModelResourceLocation mrl2 = new ModelResourceLocation(new ResourceLocation(Plants2.MODID, "flowerpot"), "cactus");
		ActualFlowerpotModel.MODELS.put(Blocks.CACTUS.getDefaultState(), Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getModel(mrl2));
	}

	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent e) {
		for (Block b : Plants2.INFO.getBlockList())
			if (b instanceof IHasModel) ((IHasModel) b).initModels(e);
		for (Item i : Plants2.INFO.getItemList())
			if (i instanceof IHasModel) ((IHasModel) i).initModels(e);
	}

	@SubscribeEvent
	public void onModelBake(ModelBakeEvent e) {
		ModelResourceLocation mrl = new ModelResourceLocation(new ResourceLocation(Plants2.MODID, "flowerpot"), "normal");
		FlowerpotModel.flowerpot = e.getModelRegistry().getObject(mrl);
		e.getModelRegistry().putObject(mrl, new ActualFlowerpotModel());

		ModelResourceLocation normal = new ModelResourceLocation(new ResourceLocation(Plants2.MODID, "jar"), "normal");
		ModelResourceLocation glass = new ModelResourceLocation(new ResourceLocation(Plants2.MODID, "jar"), "glass");
		ModelResourceLocation inv = new ModelResourceLocation(new ResourceLocation(Plants2.MODID, "jar"), "inventory");
		JarModel.jarSolid = e.getModelRegistry().getObject(normal);
		JarModel.jarGlass = e.getModelRegistry().getObject(glass);
		e.getModelRegistry().putObject(normal, new ActualJarModel());
		e.getModelRegistry().putObject(inv, new JarItemModel());
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
}
