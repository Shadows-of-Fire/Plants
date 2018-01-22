package shadows.plants2.block;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.Placebo;
import shadows.placebo.block.base.IEnumBlock;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.placebo.itemblock.ItemBlockEnum;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.data.Constants;

public abstract class BlockEnumBush<E extends Enum<E> & IPropertyEnum> extends BushBase implements IEnumBlock<E> {

	protected final E type;

	public BlockEnumBush(String name, EnumPlantType plantType, E type) {
		super(name, plantType);
		this.setDefaultState(getBlockState().getBaseState().withProperty(getInvProperty(), false));
		this.type = type;
		type.set(this);
	}

	@Override
	public ItemBlock createItemBlock() {
		return new ItemBlockEnum<>(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		PlaceboUtil.sMRL("plants", this, 0, "inventory=true,type=" + type.getName());
		Placebo.PROXY.useRenamedMapper(this, "plants", ",type=" + type.getName());
	}

	@Override
	public E getType() {
		return type;
	}

	@Override
	public IBlockState getStateFor(E e) {
		return getDefaultState();
	}

	public PropertyBool getInvProperty() {
		return Constants.INV;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, getInvProperty());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public String getUnlocalizedName() {
		return "tile.plants2";
	}

}
