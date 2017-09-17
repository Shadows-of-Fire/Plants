package shadows.plants2.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants2.block.base.BlockEnum;
import shadows.plants2.client.RenamedStateMapper;
import shadows.plants2.data.enums.ITreeEnum;
import shadows.plants2.util.PlantUtil;

public class BlockEnumLeaves<E extends Enum<E> & ITreeEnum> extends BlockEnum<E> implements IShearable {

	private final BlockEnumSapling<E> sapling;
	private int[] surroundings = new int[32768];

	public BlockEnumLeaves(String name, SoundType s, float hard, float res, BlockEnumSapling<E> sapling, Class<E> clazz, int predicate) {
		super(name, Material.LEAVES, s, hard, res, clazz, "type", (e) -> (e.getPredicateIndex() == predicate));
		this.setDefaultState(getBlockState().getBaseState().withProperty(property, types.get(0)).withProperty(BlockLeaves.DECAYABLE, false).withProperty(BlockLeaves.CHECK_DECAY, false));
		this.sapling = sapling;
		setTickRandomly(true);
		if (this.types.size() > 4) throw new IllegalArgumentException("Attempting to create a BlockEnumLeaves with more than 4 values is invalid.");
	}

	public BlockEnumLeaves(String name, BlockEnumSapling<E> sapling, Class<E> clazz, int predicate) {
		this(name, SoundType.PLANT, 0.2F, 0F, sapling, clazz, predicate);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return getBlockLayer() == BlockRenderLayer.SOLID;
	}

	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public String getUnlocalizedName() {
		return "tile.plants2.leaves";
	}

	@Override
	public IBlockState getStateFor(E e) {
		return this.getDefaultState().withProperty(BlockLeaves.DECAYABLE, true).withProperty(property, e);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModels(ModelRegistryEvent e) {
		for (int i = 0; i < types.size(); i++) {
			PlantUtil.sMRL("leaves", this, i, "check_decay=false,decayable=false," + property.getName() + "=" + types.get(i).getName());
		}
		ModelLoader.setCustomStateMapper(this, new RenamedStateMapper("leaves"));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(property, types.get(meta));
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(sapling, quantityDropped(RANDOM), damageDropped(state)));
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		List<ItemStack> k = new ArrayList<ItemStack>();
		k.add(new ItemStack(this, 1, damageDropped(state)));
		return k;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		Blocks.LEAVES.breakBlock(world, pos, state);
	}

	@Override //God this came from BlockLeaves dont even talk to me about it.
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			if (state.getValue(BlockLeaves.CHECK_DECAY) && state.getValue(BlockLeaves.DECAYABLE)) {
				int k = pos.getX();
				int l = pos.getY();
				int i1 = pos.getZ();

				if (world.isAreaLoaded(new BlockPos(k - 5, l - 5, i1 - 5), new BlockPos(k + 5, l + 5, i1 + 5))) {
					BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

					for (int i2 = -4; i2 <= 4; ++i2) {
						for (int j2 = -4; j2 <= 4; ++j2) {
							for (int k2 = -4; k2 <= 4; ++k2) {
								IBlockState iblockstate = world.getBlockState(blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2));
								Block block = iblockstate.getBlock();

								if (!block.canSustainLeaves(iblockstate, world, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
									if (block.isLeaves(iblockstate, world, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2))) {
										this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -2;
									} else {
										this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -1;
									}
								} else {
									this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = 0;
								}
							}
						}
					}

					for (int i3 = 1; i3 <= 4; ++i3) {
						for (int j3 = -4; j3 <= 4; ++j3) {
							for (int k3 = -4; k3 <= 4; ++k3) {
								for (int l3 = -4; l3 <= 4; ++l3) {
									if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16] == i3 - 1) {
										if (this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] = i3;
										}

										if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] == -2) {
											this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] = i3;
										}
									}
								}
							}
						}
					}
				}

				int l2 = this.surroundings[16912];

				if (l2 >= 0) {
					world.setBlockState(pos, state.withProperty(BlockLeaves.CHECK_DECAY, false), 4);
				} else {
					this.destroy(world, pos);
				}
			}
		}
	}

	private void destroy(World world, BlockPos pos) {
		this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
		world.setBlockToAir(pos);
	}

	@Override
	public void beginLeavesDecay(IBlockState state, World world, BlockPos pos) {
		world.setBlockState(pos, state.withProperty(BlockLeaves.CHECK_DECAY, true), 4);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(property).ordinal() % 4;
	}

	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = state.getValue(property).ordinal() % 4;
		i *= 4;
		if (state.getValue(BlockLeaves.CHECK_DECAY)) i++;
		if (state.getValue(BlockLeaves.DECAYABLE)) i += 2;
		return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		float i = meta;
		i /= 4;
		state = state.withProperty(property, types.get((int) i));
		if (i - 0.5F >= 0) {
			state = state.withProperty(BlockLeaves.DECAYABLE, true);
			i -= 0.5F;
		}
		if (i - 0.25F >= 0) state = state.withProperty(BlockLeaves.CHECK_DECAY, true);

		return state;
	}

	@Override
	public BlockStateContainer createStateContainer() {
		return new BlockStateContainer(this, property, BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE);
	}

	public BlockEnumSapling<E> getSapling() {
		return sapling;
	}
}
