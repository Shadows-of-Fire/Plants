package shadows.plants2.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import shadows.plants2.block.tree.ITreeEnum;
import shadows.plants2.block.tree.Tree;

public class EnumTreeFactory<E extends Enum<E> & ITreeEnum<E>, T extends Tree<E>> {

	Map<E, T> map = new HashMap<>();

	public EnumTreeFactory(Function<E, T> func, E[] vals) {
		for (E e : vals)
			map.put(e, func.apply(e));
	}

	public T getItem(E e) {
		return map.get(e);
	}

}
