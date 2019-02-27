package shadows.plants2.tile;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import shadows.plants2.util.ColorToPotionUtil;

public class TileBrewingCauldron extends TileEntity {

	public static final String C = "colors";
	public static final String W1 = "wart1";
	public static final String W2 = "wart2";
	public static final String WL = "water_level";
	public static final String BE = "being_extracted";
	public static final String G = "gunpowder";
	public static final String DB = "dragon_breath";

	public static final ItemStack EMPTYNBT = new ItemStack((Item) null);
	static {
		EMPTYNBT.setTagCompound(new NBTTagCompound());
	}

	protected EnumDyeColor[] colors = new EnumDyeColor[6];
	protected boolean hasFirstWart = false;
	protected boolean hasSecondWart = false;
	protected int waterLevel = 0;
	protected boolean beingExtracted = false;
	protected boolean gunpowder = false;
	protected boolean dragBreath = false;

	protected ItemStack generatedPotion = EMPTYNBT;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		colors = ColorToPotionUtil.intsToColorArray(tag.getIntArray(C));
		hasFirstWart = tag.getBoolean(W1);
		hasSecondWart = tag.getBoolean(W2);
		waterLevel = tag.getInteger(WL);
		beingExtracted = tag.getBoolean(BE);
		gunpowder = tag.getBoolean(G);
		dragBreath = tag.getBoolean(DB);
		generatedPotion = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("item"))), 1, tag.getInteger("meta"));
		generatedPotion.setTagCompound(tag.getCompoundTag("stack_nbt"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setIntArray(C, ColorToPotionUtil.colorsToIntArray(colors));
		tag.setBoolean(W1, hasFirstWart);
		tag.setBoolean(W2, hasSecondWart);
		tag.setInteger(WL, waterLevel);
		tag.setBoolean(BE, beingExtracted);
		tag.setString("item", generatedPotion.getItem().getRegistryName().toString());
		tag.setInteger("meta", generatedPotion.getMetadata());
		tag.setTag("stack_nbt", generatedPotion.getTagCompound());
		tag.setBoolean(G, gunpowder);
		tag.setBoolean(DB, dragBreath);
		return tag;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos).withProperty(BlockCauldron.LEVEL, waterLevel), 3);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 150, getUpdateTag());
	}

	public void setColor(int index, EnumDyeColor color) {
		if (index < 0 || index > 5) throw new ArrayIndexOutOfBoundsException("The colors array has a size of 6");
		colors[index] = color;
		markDirty();
	}

	public void setFirstWart(boolean set) {
		hasFirstWart = set;
		markDirty();
	}

	public void setSecondWart(boolean set) {
		hasSecondWart = set;
		markDirty();
	}

	public void setWaterLevel(int level) {
		waterLevel = level;
		markDirty();
	}

	public void setExtracting(boolean set) {
		beingExtracted = set;
		markDirty();
	}

	public void setDragBreath(boolean set) {
		dragBreath = set;
		markDirty();
	}

	public void setGunpowder(boolean set) {
		gunpowder = set;
		markDirty();
	}

	public void setPotion(ItemStack stack) {
		generatedPotion = stack;
		markDirty();
	}

	public boolean isBeingExtracted() {
		return beingExtracted;
	}

	public EnumDyeColor[] getColors() {
		return colors;
	}

	public boolean hasMaxWater() {
		return waterLevel == 3;
	}

	public int getWaterLevel() {
		return waterLevel;
	}

	public boolean hasFirstWart() {
		return hasFirstWart;
	}

	public boolean hasNetherWart() {
		return hasFirstWart && hasSecondWart;
	}

	public Item getPotionItem() {
		if (gunpowder) return Items.SPLASH_POTION;
		if (dragBreath) return Items.LINGERING_POTION;
		else return Items.POTIONITEM;
	}

	public ItemStack getPotion() {
		return generatedPotion.copy();
	}

	public void reset() {
		colors = new EnumDyeColor[6];
		hasFirstWart = false;
		hasSecondWart = false;
		waterLevel = 0;
		beingExtracted = false;
		generatedPotion = EMPTYNBT;
		gunpowder = false;
		dragBreath = false;
		markDirty();
	}

}
