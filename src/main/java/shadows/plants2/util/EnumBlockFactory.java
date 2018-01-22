package shadows.plants2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import net.minecraft.block.Block;
import shadows.placebo.block.base.IEnumBlock;
import shadows.placebo.interfaces.IPropertyEnum;

public class EnumBlockFactory<E extends Enum<E> & IPropertyEnum, B extends Block & IEnumBlock<E>> {

	Map<E, B> map = new HashMap<>();

	public EnumBlockFactory(Function<E, B> func, E[] vals) {
		for (E e : vals)
			map.put(e, func.apply(e));
	}

	public B getBlock(E e) {
		return map.get(e);
	}

}
