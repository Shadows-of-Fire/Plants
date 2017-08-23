package shadows.plants2.block.base;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.Constants;
import shadows.plants2.data.IHasRecipe;
import shadows.plants2.data.enums.IPropertyEnum;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.itemblock.ItemBlockEnum;
import shadows.plants2.util.PlantUtil;
import shadows.plants2.util.RecipeHelper;

public abstract class BlockEnumBush<E extends Enum<E> & IPropertyEnum> extends BushBase implements IEnumBlock<E>, IHasRecipe {

	protected final List<E> types = new ArrayList<E>();
	protected final Predicate<E> valueFilter;
	protected final PropertyEnum<E> property;
	protected final BlockStateContainer realStateContainer;

	public BlockEnumBush(String name, EnumPlantType type, Class<E> enumClass, int predicate) {
		super(name, true, type);
		this.valueFilter = (e) -> (e.getPredicateIndex() == predicate);
		this.property = PropertyEnum.create("type", enumClass, valueFilter);
		types.addAll(property.getAllowedValues());
		if (types.size() > getMaxEnumValues())
			throw new IllegalArgumentException("Trying to create a " + this.getClass().getSimpleName() + " with " + types.size() + " enum constants is invalid");
		this.realStateContainer = createStateContainer();
		this.setDefaultState(getBlockState().getBaseState().withProperty(getInvProperty(), false));
		Item k = createItemBlock();
		if (k != null)
			ModRegistry.ITEMS.add(k);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(property, types.get(meta));
	}

	@Override
	public IBlockState getStateFor(E e) {
		return this.getDefaultState().withProperty(property, e);
	}

	@Override
	public ItemBlock createItemBlock() {
		return new ItemBlockEnum<E>(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("plants", this, i, "inventory=true," + property.getName() + "=" + types.get(i).getName());
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("plants"));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(property, types.get(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(property).ordinal() % 16;
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
	protected final BlockStateContainer createBlockState() {
		return new BlockStateContainer.Builder(this).build(); // Blank to avoid crashes
	}

	@Override
	public final BlockStateContainer getBlockState() {
		return realStateContainer;
	}

	@Override
	public List<E> getTypes() {
		return types;
	}

	public PropertyBool getInvProperty() {
		return Constants.INV;
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, getInvProperty());
	}

	@Override
	public BlockStateContainer getRealStateContainer() {
		return realStateContainer;
	}

	@Override
	public PropertyEnum<E> getProperty() {
		return property;
	}

	@Override
	public String getUnlocalizedName() {
		return "tile.plants2";
	}

	@Override
	protected void addStatesToList() {
		List<IBlockState> list = PlantUtil.TYPE_TO_STATES.get(type);
		for (E e : types) {
			list.add(getStateFor(e));
		}
	}

	protected int getMaxEnumValues() {
		return 16;
	}

	@Override
	public void initRecipes(Register<IRecipe> event) {
		for (E e : getTypes()) {
			if (e.useForRecipes())
				RecipeHelper.addShapeless(PlantUtil.getDyeForEnum(e.getColor(), 1), new ItemStack(this, 1, e.getMetadata()));
		}
	}

}
