package shadows.plants2.tile;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileFlowerpot extends TileEntityFlowerPot {

	protected IBlockState state = Blocks.AIR.getDefaultState();
	protected ItemStack stack = ItemStack.EMPTY;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		state = NBTUtil.readBlockState(tag.getCompoundTag("state"));
		stack = new ItemStack(tag.getCompoundTag("stack"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setTag("state", NBTUtil.writeBlockState(new NBTTagCompound(), state));
		tag.setTag("stack", stack.writeToNBT(new NBTTagCompound()));
		return tag;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		state = Block.getStateById(pkt.getNbtCompound().getInteger("stateid"));
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public ItemStack getFlowerItemStack() {
		return stack.copy();
	}

	@Override
	public Item getFlowerPotItem() {
		return stack.getItem();
	}

	@Override
	public int getFlowerPotData() {
		return stack.getMetadata();
	}

	@Override
	public void setItemStack(ItemStack stack) {
		this.stack = stack.copy();
	}
	
	public void setState(IBlockState state) {
		this.state = state;
	}
	
	public IBlockState getState() {
		return state;
	}
	
	public ItemStack getStack() {
		return stack;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("stateid", Block.getStateId(state));
		return new SPacketUpdateTileEntity(this.pos, 150, tag);
	}
	
	@Override
	public boolean hasFastRenderer() {
		return true;
	}

}
