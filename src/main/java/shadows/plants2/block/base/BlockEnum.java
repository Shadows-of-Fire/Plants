package shadows.plants2.block.base;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.enums.IPropertyEnum;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.itemblock.ItemBlockEnum;
import shadows.plants2.util.PlantUtil;

//I stole this from nut
public abstract class BlockEnum<E extends Enum<E> & IPropertyEnum> extends BlockBasic implements IEnumBlock<E> {

	protected final List<E> types = new ArrayList<E>();
	protected final Predicate<E> valueFilter;
	protected final PropertyEnum<E> property;
	protected final BlockStateContainer realStateContainer;

	public BlockEnum(String name, Material material, SoundType sound, float hardness, float resistance, Class<E> enumClass, String propName, Predicate<E> valueFilter) {
		super(name, material, hardness, resistance, true, false);
		this.setSoundType(sound);
		this.valueFilter = valueFilter;
		this.property = PropertyEnum.create(propName, enumClass, valueFilter);
		types.addAll(property.getAllowedValues());
		this.realStateContainer = createStateContainer();
		this.setDefaultState(getBlockState().getBaseState());
		ModRegistry.BLOCKS.add(this);
		ModRegistry.ITEMS.add(createItemBlock());
	}

	public BlockEnum(String name, Material material, SoundType sound, float hardness, float resistance, Class<E> enumClass, String propName) {
		this(name, material, sound, hardness, resistance, enumClass, propName, Predicates.<E>alwaysTrue());
	}

	public BlockEnum(String name, Material material, SoundType sound, float hardness, float resistance, Class<E> enumClass) {
		this(name, material, sound, hardness, resistance, enumClass, "type");
	}

	public ItemBlock createItemBlock() {
		return new ItemBlockEnum<E>(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("blocks", this, i, "type=" + types.get(i).getName());
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("blocks"));
	}

	@Override
	public final BlockStateContainer createBlockState() {
		return new BlockStateContainer.Builder(this).build(); // Blank to avoid crashes
	}

	@Override
	public final BlockStateContainer getBlockState() {
		return realStateContainer;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(property, types.get(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(property).ordinal();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int i = 0; i < this.types.size(); i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property);
	}

	@Override
	public List<E> getTypes() {
		return types;
	}

	@Override
	public PropertyEnum<E> getProperty() {
		return property;
	}

	@Override
	public BlockStateContainer getRealStateContainer() {
		return realStateContainer;
	}

	@Override
	public IBlockState getStateFor(E e) {
		return this.getDefaultState().withProperty(property, e);
	}

	@Override
	public boolean placeStateAt(IBlockState state, World world, BlockPos pos) {
		return world.setBlockState(pos, state);
	}

}
