package shadows.plants2.data.enums;

import shadows.plants2.data.StackPrimer;

public interface IHarvestableEnum extends IPropertyEnum {

	@Override
	default public int getPredicateIndex() {
		return ((Enum<?>) this).ordinal() / 8;
	}

	public StackPrimer[] getDrops();

}
