package shadows.naturalis.biome;
/*
import java.util.Random;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.registries.IForgeRegistry;
import shadows.plants2.ModRegistry;
import shadows.plants2.Naturalis;
import shadows.plants2.gen.WorldGenCrystals;

public class BiomeCrystalForest extends Biome {

	static final WorldGenerator CRYSTALS = new WorldGenCrystals();

	public BiomeCrystalForest() {
		super(new BiomeProperties("Crystal Forest").setTemperature(0.8F).setRainfall(0.2F).setHeightVariation(0.1F).setBaseHeight(0.3F));
		spawnableCreatureList.clear();
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 10, 1, 2));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCreeper.class, 3, 1, 1));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySpider.class, 7, 1, 2));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 5, 2, 5));
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySilverfish.class, 1, 1, 1));
		fillerBlock = ModRegistry.GROUNDCOVER.getDefaultState();
		topBlock = ModRegistry.GROUNDCOVER.getDefaultState();
		decorator.treesPerChunk = 4;
		decorator.flowersPerChunk = 0;
		decorator.grassPerChunk = 15;
		flowers.clear();
		flowers.add(new FlowerEntry(ModRegistry.CRYSTAL.getStateFor(Crystals.CRYSTAL_SHARD), 60));
		flowers.add(new FlowerEntry(ModRegistry.CRYSTAL.getStateFor(Crystals.DARK_CRYSTAL_SHARD), 40));
		Naturalis.INFO.getBiomeList().add(this);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return rand.nextFloat() >= 0.73F ? ModRegistry.DARK_CRYSTAL_TREE : ModRegistry.CRYSTAL_TREE;
	}

	@Override
	public void plantFlower(World world, Random rand, BlockPos pos) {
		FlowerEntry flower = WeightedRandom.getRandomItem(rand, flowers);
		if (flower != null) world.setBlockState(pos, flower.state, 3);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return CRYSTALS;
	}

	@Override
	public void register(IForgeRegistry<Biome> reg) {
		if (PlantConfig.crystalForest) {
			reg.register(this);
			if (PlantConfig.crystalForestWeight > 0) {
				BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(this, PlantConfig.crystalForestWeight));
				BiomeManager.addSpawnBiome(this);
			}
		}
	}

}
*/