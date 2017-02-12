package shadows.plants.item.internal.cosmetic;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shadows.plants.block.internal.cosmetic.BlockFruitVine;
import shadows.plants.common.EnumModule;
import shadows.plants.item.common.DummyItem;
import shadows.plants.util.Config;
import shadows.plants.util.Util;

public class ItemCompost extends DummyItem{

	public ItemCompost(String name) {
		super(name, EnumModule.COSMETIC);
	}
	
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
    	
        if (stack.stackSize != 0 && player.canPlayerEdit(pos, facing, stack)){
    	if(Config.debug) System.out.println("Using Compost Item");
    	IBlockState state = world.getBlockState(pos);
		SoundType soundtype = SoundType.PLANT;
    	if(state.getBlock() == Blocks.MOSSY_COBBLESTONE){
    		BlockFruitVine block = (BlockFruitVine) Util.getRandomVine(world.rand);
    		Util.placeVine(world, block, pos, facing);
            world.playSound(player, pos.offset(facing), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
    		--stack.stackSize;
            return EnumActionResult.SUCCESS;
        }
    		findAndGenFlowers(world, pos);
    		if(world.rand.nextInt(10) == 0) findAndGenFlowers(world, pos);
            world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

    		--stack.stackSize;
            return EnumActionResult.SUCCESS;
        }
     return EnumActionResult.FAIL;
    }
    
    private static void findAndGenFlowers(World world, BlockPos pos){
    	Block flower = Util.getFlowerByChance(world.rand);
    	if(Config.debug) System.out.println(flower.toString());
    	if(flower.canPlaceBlockAt(world, pos.up())){
    		if(Config.debug) System.out.println("Attempting to place " + flower.toString());
    		Util.genSmallFlowerPatchNearby(world, pos.up(), world.rand, Util.getStateByChance(world.rand, flower), flower);
    }}

}
