package shadows.plants2.biome;

import java.util.Random;

import net.minecraft.entity.monster.EntityCreeper;
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
import shadows.plants2.Plants2;
import shadows.plants2.data.Config;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;
import shadows.plants2.gen.WorldGenCrystals;
import shadows.plants2.init.ModRegistry;

public class BiomeCrystalForest extends AbstractBiomeBase {

	static final WorldGenerator CRYSTALS = new WorldGenCrystals();

	public BiomeCrystalForest() {
		super("crystal_forest", new BiomeProperties("Crystal Forest").setTemperature(0.8F).setRainfall(0.2F).setHeightVariation(0.1F).setBaseHeight(0.3F));

		spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 10, 1, 1));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCreeper.class, 10, 1, 1));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySpider.class, 10, 1, 1));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		this.fillerBlock = ModRegistry.GROUNDCOVER.getDefaultState();
		this.topBlock = ModRegistry.GROUNDCOVER.getDefaultState();
		this.decorator.treesPerChunk = 4;
		this.decorator.flowersPerChunk = 0;
		this.decorator.grassPerChunk = 15;
		this.flowers.clear();
		this.flowers.add(new FlowerEntry(ModRegistry.CRYSTAL.getStateFor(Crystals.CRYSTAL_SHARD), 80));
		this.flowers.add(new FlowerEntry(ModRegistry.CRYSTAL.getStateFor(Crystals.DARK_CRYSTAL_SHARD), 20));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return rand.nextFloat() >= 0.9F ? ModRegistry.DARK_CRYSTAL_TREE : ModRegistry.CRYSTAL_TREE;
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
	protected void register() {
		if (!Config.crystalForest) return;
		Plants2.INFO.getBiomeList().add(this);
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(this, 50));
	}

}
