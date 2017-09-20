package shadows.plants2.tile;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileBrewingCauldron extends TileEntity {

	protected static final EnumDyeColor[] COLORS = new EnumDyeColor[6];

	public int[] colorsToIntArray() {
		int[] ret = new int[6];
		for(int i = 0; i < 6; i++) ret[i] = COLORS[i] == null ? -1 : COLORS[i].getMetadata();	
		return ret;
	}
	
	public void deserializeFromIntArray(int[] input) {
		for(int i = 0; i < 6; i++) COLORS[i] = input[i] == -1 ? null : EnumDyeColor.byMetadata(input[i]);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	}
	
}
