package shadows.plants2.data.enums;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public interface ILogBasedPropertyEnum extends IPropertyEnum {

	@Override
	default public EnumDyeColor getColor() {
		return EnumDyeColor.WHITE;
	}

	public WorldGenAbstractTree getTreeGen();

	public void setTreeGen(WorldGenAbstractTree k);

	@Override
	default public int getPredicateIndex() {
		return ((Enum<?>) this).ordinal() / 4;
	}

}
