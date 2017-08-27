package shadows.plants2.tile;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import shadows.plants2.data.enums.TheBigBookOfEnums.AllPlants;

public class TileFlowerpot extends TileEntityFlowerPot {

	public static final PropertyEnum<AllPlants> PROPERTY = PropertyEnum.create("type", AllPlants.class);
	
	private AllPlants flower = AllPlants.NONE;
	private ItemStack stack = ItemStack.EMPTY;
	

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		flower = AllPlants.values()[tag.getInteger("flower")];
		stack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("item"))), 1, tag.getInteger("meta"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("flower", this.flower.ordinal());
		tag.setString("item", stack.getItem().getRegistryName().toString());
		tag.setInteger("meta", stack.getMetadata());
		return tag;
	}

	public AllPlants getFlower() {
		return flower;
	}
	
	public void setFlower(AllPlants flower) {
		this.flower = flower;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
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
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 150, this.getUpdateTag());
	}

}
