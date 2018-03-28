package shadows.plants2.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.client.IHasModel;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.plants2.Plants2;
import shadows.plants2.data.IColorProvider;
import shadows.plants2.data.PlantConstants;
import shadows.plants2.network.ParticleMessage;
import shadows.plants2.tile.TileBrewingCauldron;
import shadows.plants2.util.ColorToPotionUtil;

public class BlockBrewingCauldron extends Block implements IHasRecipe, IHasModel {

	protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

	public static final PropertyInteger LEVEL = BlockCauldron.LEVEL;

	public BlockBrewingCauldron() {
		super(Material.IRON, MapColor.STONE);
		setRegistryName("brewing_cauldron");
		setUnlocalizedName(Plants2.MODID + ".brewing_cauldron");
		setCreativeTab(PlantConstants.TAB);
		setHardness(3F);
		setResistance(10F);
		setSoundType(SoundType.METAL);
		setDefaultState(blockState.getBaseState().withProperty(LEVEL, 0));
		Plants2.INFO.getBlockList().add(this);
		Plants2.INFO.getItemList().add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		TileEntity t = world.getTileEntity(pos);
		if (!(t instanceof TileBrewingCauldron)) return false;
		TileBrewingCauldron cauldron = (TileBrewingCauldron) t;
		if (stack.getItem() == Items.WATER_BUCKET) {
			if (cauldron.isBeingExtracted() || cauldron.hasMaxWater()) return false;
			if (!world.isRemote) {
				if (!player.capabilities.isCreativeMode) {
					stack.shrink(1);
					player.setHeldItem(hand, new ItemStack(Items.BUCKET));
				}
				cauldron.setWaterLevel(cauldron.getWaterLevel() + 1);
			}
			world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
			cauldron.markDirty();
			world.notifyBlockUpdate(pos, state, state.withProperty(LEVEL, cauldron.getWaterLevel()), 3);
			return true;
		} else if (ColorToPotionUtil.isDyeArrayValid(cauldron.getColors()) && stack.getItem() == Items.GLASS_BOTTLE) {
			if (!world.isRemote) {
				if (!player.capabilities.isCreativeMode) stack.shrink(1);
				if (cauldron.getPotion().isEmpty()) cauldron.setPotion(ColorToPotionUtil.genPotionStack(cauldron));
				ItemStack potion = cauldron.getPotion();
				if (!player.addItemStackToInventory(potion)) Block.spawnAsEntity(world, pos.up(), potion);
				if (cauldron.getWaterLevel() - 1 == 0) cauldron.reset();
				else cauldron.setWaterLevel(cauldron.getWaterLevel() - 1);
			}
			world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1, 1);
			cauldron.markDirty();
			world.notifyBlockUpdate(pos, state, state, 3);
			return true;
		}
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (world.isRemote) return;
		TileEntity t = world.getTileEntity(pos);
		if (!(t instanceof TileBrewingCauldron)) return;
		if (entity instanceof EntityItem) {
			TileBrewingCauldron cauldron = (TileBrewingCauldron) t;
			if (cauldron.isBeingExtracted() || !cauldron.hasMaxWater()) return;

			EntityItem item = (EntityItem) entity;
			ItemStack stack = item.getItem();

			if (!cauldron.hasNetherWart() && stack.getItem() == Items.NETHER_WART) {
				if (!cauldron.hasFirstWart()) {
					cauldron.setFirstWart(true);
					world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
					stack.shrink(1);
					world.notifyBlockUpdate(pos, state, state.withProperty(LEVEL, cauldron.getWaterLevel()), 3);
					Plants2.NETWORK.sendToAllAround(new ParticleMessage(pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 150));
					return;
				} else {
					cauldron.setSecondWart(true);
					world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
					stack.shrink(1);
					world.notifyBlockUpdate(pos, state, state.withProperty(LEVEL, cauldron.getWaterLevel()), 3);
					Plants2.NETWORK.sendToAllAround(new ParticleMessage(pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 150));
					return;
				}

			} else if (cauldron.hasNetherWart() && !ColorToPotionUtil.isDyeArrayValid(cauldron.getColors())) {
				boolean doubled = false;
				EnumDyeColor color = null;
				if (stack.getItem() instanceof IColorProvider) {
					color = ((IColorProvider) stack.getItem()).getColor(null);
				}
				if (stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock() instanceof IColorProvider) {
					Block b = ((ItemBlock) stack.getItem()).getBlock();
					IBlockState flower = b.getStateForPlacement(world, pos, EnumFacing.UP, 0, 0, 0, stack.getMetadata(), FakePlayerFactory.getMinecraft((WorldServer) world), EnumHand.MAIN_HAND);
					color = ((IColorProvider) b).getColor(flower);
					doubled = b instanceof BlockEnumDoubleFlower;
				}
				if (color != null) for (int i = 0; i < 6; i++) {
					if (ColorToPotionUtil.isDyeArrayValid(cauldron.getColors())) break;
					if (cauldron.getColors()[i] == null) {
						if (doubled) {
							if (cauldron.getColors()[MathHelper.clamp(i + 1, 0, 5)] != null) break;
							cauldron.setColor(i, color);
							cauldron.setColor(MathHelper.clamp(i + 1, 0, 5), color);
						} else cauldron.setColor(i, color);
						world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
						stack.shrink(1);
						world.notifyBlockUpdate(pos, state, state.withProperty(LEVEL, cauldron.getWaterLevel()), 3);
						Plants2.NETWORK.sendToAllAround(new ParticleMessage(pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 150));
						return;
					}
				}
			} else if (cauldron.hasNetherWart() && ColorToPotionUtil.isDyeArrayValid(cauldron.getColors()) && cauldron.getPotionItem() == Items.POTIONITEM) {
				if (stack.getItem() != Items.GUNPOWDER && stack.getItem() != Items.DRAGON_BREATH) return;
				if (stack.getItem() == Items.GUNPOWDER) cauldron.setGunpowder(true);
				else if (stack.getItem() == Items.DRAGON_BREATH) cauldron.setDragBreath(true);
				world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
				stack.shrink(1);
				world.notifyBlockUpdate(pos, state, state.withProperty(LEVEL, cauldron.getWaterLevel()), 3);
				Plants2.NETWORK.sendToAllAround(new ParticleMessage(pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 150));
				return;
			}
		}

	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LEVEL);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity t = world.getTileEntity(pos);
		if (t instanceof TileBrewingCauldron) {
			state = state.withProperty(LEVEL, ((TileBrewingCauldron) t).getWaterLevel());
		}
		return state;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileBrewingCauldron();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void initModels(ModelRegistryEvent e) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Items.CAULDRON.getRegistryName(), "inventory"));
		ModelLoader.setCustomStateMapper(this, new IStateMapper() {

			@Override
			public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block block) {
				Map<IBlockState, ModelResourceLocation> map = new HashMap<>();
				for (int i = 0; i < 4; i++)
					map.put(block.getDefaultState().withProperty(BlockCauldron.LEVEL, i), new ModelResourceLocation(Plants2.MODID + ":blocks", "type=cauldron_" + i));
				return map;
			}

		});
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addShaped(this, 3, 3, "plant", "plant", "plant", Items.BLAZE_POWDER, Items.CAULDRON, Items.BLAZE_POWDER, Blocks.STONE_SLAB, Blocks.STONE_SLAB, Blocks.STONE_SLAB);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		Plants2.PROXY.doCauldronParticles(state, world, pos, rand);

	}

}
