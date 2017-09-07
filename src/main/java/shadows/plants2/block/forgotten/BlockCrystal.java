package shadows.plants2.block.forgotten;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnum;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.IHasRecipe;
import shadows.plants2.data.enums.TheBigBookOfEnums.Crystals;
import shadows.plants2.util.PlantUtil;

public class BlockCrystal extends BlockEnum<Crystals> implements IHasRecipe {

	public BlockCrystal() {
		super("crystal", Material.GLASS, SoundType.GLASS, 1.4F, 20F, Crystals.class);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("blocks", this, i, "type=" + types.get(i).getName() + (types.get(i).isShard() ? "_inv" : ""));
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("blocks"));
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return !state.getValue(property).isShard();
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return isFullCube(state);
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return state.getValue(property).isShard() ? null : "pickaxe";
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (world.getBlockState(pos.offset(side)) != state)
			return true;
		if (world.isAirBlock(pos.offset(side)))
			return true;
		return false;
	}

	@Override
	@Deprecated
	public float getBlockHardness(IBlockState state, World world, BlockPos pos) {
		return state.getValue(property).isShard() ? 0.3F : 1.4F;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		Crystals c = state.getValue(property);
		if (c.isShard()) {
			System.out.println(c.get());
			drops.add(c.get());
		} else
			drops.add(new ItemStack(this, 1, c.ordinal()));
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return state.getValue(property).isShard();
	}

	@Override
	public ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this, 1, state.getValue(property).ordinal());
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		//TODO: add recipes for crystal tools etc here.
	}

}
