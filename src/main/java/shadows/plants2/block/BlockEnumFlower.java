package shadows.plants2.block;

import net.minecraftforge.common.EnumPlantType;
import shadows.plants2.block.base.BlockEnumBush;
import shadows.plants2.data.enums.IFlowerEnum;

public class BlockEnumFlower<E extends Enum<E> & IFlowerEnum> extends BlockEnumBush<E> {

	public BlockEnumFlower(String name, EnumPlantType type, Class<E> enumClass, int predicate) {
		super(name, type, enumClass, predicate);
	}

}
