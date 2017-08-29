package shadows.plants2.gen;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import shadows.plants2.block.BlockEnumSapling;
import shadows.plants2.data.Constants;
import shadows.plants2.util.PlantUtil;

public class TreeTemplate extends Template {

	protected final List<Template.BlockInfo> blocks;

	public TreeTemplate(Template template) {
		blocks = ReflectionHelper.getPrivateValue(Template.class, template, "blocks", "field_186270_a");
	}

	public boolean isReplaceable(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		boolean flag = state.getBlock().isReplaceable(world, pos) || (state.getBlock().isLeaves(state, world, pos) && PlantUtil.isOwnedBy(state.getBlock(), Constants.MODID));
		if (flag && state.getBlock() instanceof BlockDoublePlant)
			world.setBlockToAir(pos.up());
		return flag;
	}

	@Override
	public void addBlocksToWorld(World world, BlockPos pos, @Nullable ITemplateProcessor processor, PlacementSettings placement, int flags) {
		addBlocksToWorld(world, pos, processor, placement, flags, true);
	}

	public boolean addBlocksToWorld(World world, BlockPos pos, @Nullable ITemplateProcessor processor, PlacementSettings placement, int flags, boolean unused) {
		Block block = placement.getReplacedBlock();
		StructureBoundingBox structureboundingbox = placement.getBoundingBox();
		for (BlockInfo info : this.blocks) {
			BlockPos toCheck = transformedBlockPos(placement, info.pos).add(pos);
			if (!info.blockState.getBlock().isLeaves(info.blockState, world, toCheck))
				if (!isReplaceable(world, toCheck) && !(world.getBlockState(toCheck) instanceof BlockEnumSapling))
					return false;
		}
		for (Template.BlockInfo template$blockinfo : this.blocks) {
			BlockPos blockpos = transformedBlockPos(placement, template$blockinfo.pos).add(pos);
			Template.BlockInfo template$blockinfo1 = processor != null ? processor.processBlock(world, blockpos, template$blockinfo) : template$blockinfo;

			if (template$blockinfo1 != null) {
				Block block1 = template$blockinfo1.blockState.getBlock();

				if ((block == null || block != block1) && (!placement.getIgnoreStructureBlock() || block1 != Blocks.STRUCTURE_BLOCK) && (structureboundingbox == null || structureboundingbox.isVecInside(blockpos))) {
					IBlockState iblockstate = template$blockinfo1.blockState.withMirror(placement.getMirror());
					IBlockState iblockstate1 = iblockstate.withRotation(placement.getRotation());

					if (template$blockinfo1.tileentityData != null) {
						TileEntity tileentity = world.getTileEntity(blockpos);

						if (tileentity != null) {
							if (tileentity instanceof IInventory) {
								((IInventory) tileentity).clear();
							}

							world.setBlockState(blockpos, Blocks.BARRIER.getDefaultState(), 4);
						}
					}

					if (isReplaceable(world, blockpos) && world.setBlockState(blockpos, iblockstate1, flags) && template$blockinfo1.tileentityData != null) {
						TileEntity tileentity2 = world.getTileEntity(blockpos);

						if (tileentity2 != null) {
							template$blockinfo1.tileentityData.setInteger("x", blockpos.getX());
							template$blockinfo1.tileentityData.setInteger("y", blockpos.getY());
							template$blockinfo1.tileentityData.setInteger("z", blockpos.getZ());
							tileentity2.readFromNBT(template$blockinfo1.tileentityData);
							tileentity2.mirror(placement.getMirror());
							tileentity2.rotate(placement.getRotation());
						}
					}
				}
			}
		}

		for (Template.BlockInfo template$blockinfo2 : this.blocks) {
			if (block == null || block != template$blockinfo2.blockState.getBlock()) {
				BlockPos blockpos1 = transformedBlockPos(placement, template$blockinfo2.pos).add(pos);

				if (structureboundingbox == null || structureboundingbox.isVecInside(blockpos1)) {
					world.notifyNeighborsRespectDebug(blockpos1, template$blockinfo2.blockState.getBlock(), false);

					if (template$blockinfo2.tileentityData != null) {
						TileEntity tileentity1 = world.getTileEntity(blockpos1);

						if (tileentity1 != null) {
							tileentity1.markDirty();
						}
					}
				}
			}
		}
		return true;
	}
}
