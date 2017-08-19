package shadows.plants2.data.enums;

import java.util.Locale;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;

public interface IPropertyEnum extends IStringSerializable {

	default public String getName() {
		return ((Enum<?>) this).name().toLowerCase(Locale.ENGLISH);
	}

	default public EnumDyeColor getColor() {
		return EnumDyeColor.WHITE;
	};

	default public int getPredicateIndex() {
		return 0;
	};

}
