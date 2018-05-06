package shadows.plants2.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import shadows.placebo.Placebo;
import shadows.placebo.block.BlockBasic;
import shadows.placebo.block.IEnumBlockAccess;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.interfaces.IPlankEnum;
import shadows.placebo.interfaces.IPropertyEnum;
import shadows.placebo.itemblock.ItemBlockBase;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.Plants2;

public class BlockEnumSlab extends BlockBasic implements IHasRecipe {

	public static final PropertyEnum<SlabVariant> VARIANT = PropertyEnum.create("slab_variant", SlabVariant.class);

	private final IPlankEnum e;

	public <T extends IPlankEnum, B extends Block & IEnumBlockAccess<T>> BlockEnumSlab(T e, B block) {
		super(e.getName() + "_slab", block.getStateFor(e).getMaterial(), block.blockHardness, block.blockResistance, Plants2.INFO);
		this.setSoundType(block.blockSoundType);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, SlabVariant.LOWER));
		this.e = e;
	}

	@Override
	public void initModels(ModelRegistryEvent ev) {
		Placebo.PROXY.useRenamedMapper(this, "slabs", ",type=" + e.getName());
		PlaceboUtil.sMRL("slabs", this, 0, "slab_variant=lower,type=" + e.getName());
	}

	@Override
	public void initRecipes(Register<IRecipe> ev) {
		OreDictionary.registerOre("slabWood", this);
		Ingredient i = Ingredient.fromStacks(e.get());
		Plants2.HELPER.addShaped(new ItemStack(this, 6), 3, 1, i, i, i);
		Plants2.HELPER.addShaped(e.get(), 1, 2, this, this);
	}

	@Override
	public ItemBlock createItemBlock() {
		return new ItemBlockSlab(this);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return state == getDouble();
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return isFullCube(state) || state == getUpper();
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return isFullCube(state) ? 2 : 1;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return state.getValue(VARIANT).getAABB();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.getBoundingBox(world, pos);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public String getUnlocalizedName() {
		return "tile.plants2.slab." + e.getName();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing) {
		IBlockState state2 = blockAccess.getBlockState(pos.offset(facing));
		if (!facing.getAxis().isVertical()) return !(state2.getBlock() == this && (state2 == state || getDouble() == state2));
		else if (facing == EnumFacing.DOWN && state2 == getDouble()) return !(state == getLower() || state == state2);
		else if (facing == EnumFacing.UP && state2 == getDouble()) return !(state.getBlock() == this);
		else if (facing == EnumFacing.UP && state == getDouble()) return !(state2 == getLower());
		return !(state2.getBlock() == this && state2 == getOpposite(state));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return state == getDouble();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, SlabVariant.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		if (face == EnumFacing.UP) return state == getUpper();
		else if (face == EnumFacing.DOWN) return state == getLower();
		else return state == getDouble();
	}

	public IBlockState getUpper() {
		return this.getDefaultState().withProperty(VARIANT, SlabVariant.UPPER);
	}

	public IBlockState getLower() {
		return this.getDefaultState();
	}

	public IBlockState getDouble() {
		return this.getDefaultState().withProperty(VARIANT, SlabVariant.DOUBLE);
	}

	public IBlockState getOpposite(IBlockState state) {
		if (state == getUpper()) return getLower();
		return state;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (hitY >= 0.5) return this.getDefaultState().withProperty(VARIANT, SlabVariant.UPPER);
		return this.getDefaultState();
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return e.isNether() ? 0 : 20;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return e.isNether() ? 0 : 5;
	}

	public static enum SlabVariant implements IPropertyEnum {
		LOWER(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)),
		UPPER(new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D)),
		DOUBLE(FULL_BLOCK_AABB);

		private AxisAlignedBB aabb;

		private SlabVariant(AxisAlignedBB AABB) {
			this.aabb = AABB;
		}

		public AxisAlignedBB getAABB() {
			return aabb;
		}

		@Override
		public ItemStack get() {
			return ItemStack.EMPTY;
		}
	}

	private static class ItemBlockSlab extends ItemBlockBase {

		private final BlockEnumSlab slab;

		public ItemBlockSlab(BlockEnumSlab slab) {
			super(slab);
			this.slab = slab;
		}

		@Override
		public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			if (!canPlaceOnSide(player, world, pos, hand, facing, hitX, hitY, hitZ)) return EnumActionResult.FAIL;
			ItemStack stack = player.getHeldItem(hand);
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			if (!block.isReplaceable(world, pos) && !(block == this.block && checkOppositeByFacing(state, facing))) pos = pos.offset(facing);
			state = world.getBlockState(pos);
			IBlockState placeState = getByHitY(hitY);

			if (player.canPlayerEdit(pos, facing, stack) && facing.getAxis().isVertical() && state.getBlock() == this.block) {
				placeState = slab.getDouble();
				if (doubleSlab(world, pos)) return shrinkAndSucceed(world, pos, player, stack);

			} else if (validEditable(state, world, pos, facing, stack, player) && facing.getAxis().isHorizontal()) {
				if (world.setBlockState(pos, placeState)) return shrinkAndSucceed(world, pos, player, stack);

			} else if (validEditable(state, world, pos, facing, stack, player) && facing.getAxis().isVertical()) {
				placeState = slab.getOpposite(placeState);
				if (world.setBlockState(pos, placeState)) return shrinkAndSucceed(world, pos, player, stack);

			} else if (player.canPlayerEdit(pos, facing, stack) && facing.getAxis().isHorizontal() && state.getBlock() == this.block && canBeMerged(state, placeState)) {
				if (doubleSlab(world, pos)) return shrinkAndSucceed(world, pos, player, stack);
			}
			return EnumActionResult.PASS;
		}

		private boolean validEditable(IBlockState state, World world, BlockPos pos, EnumFacing facing, ItemStack stack, EntityPlayer player) {
			return state.getBlock().isReplaceable(world, pos) && player.canPlayerEdit(pos, facing, stack);
		}

		private boolean canBeMerged(IBlockState state, IBlockState state2) {
			return state != state2 && state != slab.getDouble();
		}

		private boolean checkOppositeByFacing(IBlockState state, EnumFacing facing) {
			if (facing == EnumFacing.DOWN) return state.getValue(VARIANT) == SlabVariant.UPPER;
			else if (facing == EnumFacing.UP) return state.getValue(VARIANT) == SlabVariant.LOWER;
			else return false;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
			IBlockState state = getByHitY(Minecraft.getMinecraft().objectMouseOver.hitVec.y % 1F);
			IBlockState current = world.getBlockState(pos);
			if (current == slab.getDouble()) pos = pos.offset(side);
			current = world.getBlockState(pos);
			if (state != slab.getDouble() && state != current) return true;
			return super.canPlaceBlockOnSide(world, pos, side, player, stack) && world.checkNoEntityCollision(state.getValue(VARIANT).getAABB().offset(pos), null);
		}

		private boolean canPlaceOnSide(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
			IBlockState state = this.block.getStateForPlacement(world, pos, side, hitX, hitY, hitZ, player.getHeldItem(hand).getMetadata(), player, hand);
			IBlockState current = world.getBlockState(pos);
			if (current.getBlock() == this.block && current != this.slab.getDouble()) return world.checkNoEntityCollision(state.getValue(VARIANT).getAABB().offset(pos), null);
			return world.checkNoEntityCollision(state.getValue(VARIANT).getAABB().offset(pos.offset(side)), null);
		}

		private EnumActionResult shrinkAndSucceed(World world, BlockPos pos, EntityPlayer player, ItemStack stack) {
			SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
			world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}

		private IBlockState getByHitY(double hitY) {
			if (hitY >= 0.5) return this.block.getDefaultState().withProperty(VARIANT, SlabVariant.UPPER);
			return this.block.getDefaultState();
		}

		private boolean doubleSlab(World world, BlockPos pos) {
			return world.setBlockState(pos, slab.getDouble());
		}

		@Override
		public int getMetadata(int damage) {
			return damage;
		}

	}
}