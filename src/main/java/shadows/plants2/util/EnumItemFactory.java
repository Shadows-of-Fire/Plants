package shadows.plants2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import shadows.placebo.interfaces.IPropertyEnum;
import shadows.plants2.item.ItemBigEnum;

public class EnumItemFactory<E extends Enum<E> & IPropertyEnum, I extends ItemBigEnum<E>> {

	Map<E, I> map = new HashMap<>();

	public EnumItemFactory(Function<E, I> func, E[] vals) {
		for (E e : vals)
			map.put(e, func.apply(e));
	}

	public I getItem(E e) {
		return map.get(e);
	}

}
