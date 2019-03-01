package shadows.naturalis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shadows.naturalis.gen.GenHandler;
import shadows.naturalis.registry.RegHandler;
import shadows.naturalis.util.EnumGenType;
import shadows.placebo.util.CreativeTab;
import shadows.placebo.util.StackPrimer;

@Mod(modid = Naturalis.MODID, name = Naturalis.MODNAME, version = Naturalis.VERSION, dependencies = Naturalis.DEPS)
public class Naturalis {

	public static final String MODID = "naturalis";
	public static final String MODNAME = "Naturalis";
	public static final String VERSION = "1.0.0";
	public static final String DEPS = "required-after:placebo@[2.0.0,)";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final CreativeTab TAB = new CreativeTab(Naturalis.MODID, new StackPrimer(Naturalis.MODID + ":flowering_onion"));

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new RegHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent e) throws Throwable {
		MinecraftForge.TERRAIN_GEN_BUS.register(new GenHandler());
		RegHandler.ITEMS.clear();
		RegHandler.BLOCKS.clear();
		EnumGenType.calcBiomeMap();
	}

	static void jsons() throws Throwable {
		File f = new File("A:/Github/Plants/src/main/resources/assets/naturalis/blockstates/default.json");
		BufferedReader read = new BufferedReader(new FileReader(f));
		List<String> lines = new ArrayList<>(19);
		while (read.ready())
			lines.add(read.readLine());
		read.close();
		for (Block b : RegHandler.BLOCKS) {
			File out = new File(f.getParent(), String.format("%s.json", b.getRegistryName().getPath()));
			out.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(out));
			for (String s : lines) {
				writer.write(s.replace("<X>", b.getRegistryName().getPath()) + "\n");
			}
			writer.close();
		}
	}
}
