package shadows.plants2.data.enums;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public interface ILogBasedPropertyEnum extends IPropertyEnum {

	public WorldGenAbstractTree getTreeGen();

	public void setTreeGen(WorldGenAbstractTree k);

	@Override
	default public int getPredicateIndex() {
		return ((Enum<?>) this).ordinal() / 4;
	}

	@Override
	default public int getMetadata() {
		return ((Enum<?>) this).ordinal() % 4;
	}

}
