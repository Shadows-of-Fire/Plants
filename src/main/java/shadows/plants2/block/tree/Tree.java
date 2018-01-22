package shadows.plants2.block.tree;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import shadows.placebo.interfaces.IParticleProvider;
import shadows.plants2.block.BlockEnumParticleLeaves;

public class Tree<E extends Enum<E> & ITreeEnum<E>> {

	E type;
	WorldGenerator gen;
	BlockEnumLog<E> log;
	BlockEnumLeaves<E> leaf;
	BlockEnumSapling<E> sapling;
	BlockEnumPlanks<E> planks;

	public Tree(E type) {
		this.type = type;
		type.setTree(this);
	}

	public WorldGenerator getTreeGen() {
		return gen;
	}

	public BlockEnumLog<E> getLog() {
		return log;
	}

	public BlockEnumLeaves<E> getLeaf() {
		return leaf;
	}

	public BlockEnumSapling<E> getSap() {
		return sapling;
	}

	public BlockEnumPlanks<E> getPlanks() {
		return planks;
	}

	public E getType() {
		return type;
	}

	public Tree<E> setTreeGen(WorldGenerator gen) {
		if (this.gen != null) throw new UnsupportedOperationException("Tree gen already set!");
		this.gen = gen;
		return this;
	}

	public Tree<E> setLog(BlockEnumLog<E> log) {
		if (this.log != null) throw new UnsupportedOperationException("Log already created!");
		this.log = log;
		return this;
	}

	public Tree<E> setLeaf(BlockEnumLeaves<E> leaf) {
		if (this.leaf != null) throw new UnsupportedOperationException("Leaves already created!");
		this.leaf = leaf;
		return this;
	}

	public Tree<E> setSapling(BlockEnumSapling<E> sapling) {
		if (this.sapling != null) throw new UnsupportedOperationException("Sapling already created!");
		this.sapling = sapling;
		return this;
	}

	public Tree<E> setPlanks(BlockEnumPlanks<E> planks) {
		if (this.planks != null) throw new UnsupportedOperationException("Planks already created!");
		this.planks = planks;
		return this;
	}

	public Tree<E> makeLog() {
		return setLog(new BlockEnumLog<>(type));
	}

	public Tree<E> makeLog(SoundType s, float hard, float res) {
		return setLog(new BlockEnumLog<>(s, hard, res, type));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Tree<E> makeLeaf() {
		if (this.sapling == null) throw new UnsupportedOperationException("Leaves require a sapling!");
		if (type instanceof IParticleProvider) return setLeaf(new BlockEnumParticleLeaves(sapling, (Enum<?>) type));
		return setLeaf(new BlockEnumLeaves<>(sapling, type));
	}

	public Tree<E> makeLeaf(SoundType s, float hard, float res) {
		if (this.sapling == null) throw new UnsupportedOperationException("Leaves require a sapling!");
		return setLeaf(new BlockEnumLeaves<>(s, hard, res, sapling, type));
	}

	public Tree<E> makeSap(EnumPlantType plantType) {
		return setSapling(new BlockEnumSapling<>(plantType, type));
	}

	public Tree<E> makeSap(EnumPlantType plantType, SoundType s, float hard, float res) {
		return setSapling(new BlockEnumSapling<>(plantType, s, hard, res, type));
	}

	public Tree<E> makePlanks() {
		return setPlanks(new BlockEnumPlanks<>(type));
	}

	public Tree<E> makePlanks(Material mat, SoundType sound, float hard, float res) {
		return setPlanks(new BlockEnumPlanks<>(mat, sound, hard, res, type));
	}

	public static <E extends Enum<E> & ITreeEnum<E>> Tree<E> defaultTree(E e) {
		return new Tree<>(e).makeLog().makeSap(EnumPlantType.Plains).makeLeaf().makePlanks();
	}

}
