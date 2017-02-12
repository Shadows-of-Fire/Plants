package shadows.plants.item.internal.cosmetic;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
       	Block flower = Util.getFlowerByChance(world.rand);
       	
    	if(state.getBlock() == Blocks.MOSSY_COBBLESTONE){
    		BlockFruitVine block = (BlockFruitVine) Util.getRandomVine(world.rand);
    		Util.placeVine(world, block, pos, facing);
            world.playSound(player, pos.offset(facing), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
    		--stack.stackSize;
            return EnumActionResult.SUCCESS;
        }
    	

    	else if(flower.canPlaceBlockAt(world, pos.up())){
    		genFlowers(world, pos, flower, Util.getStateByChance(world.rand, flower));
    		if(world.rand.nextInt(10) == 0) genFlowers(world, pos, flower, Util.getStateByChance(world.rand, flower));
            world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

    		--stack.stackSize;
            return EnumActionResult.SUCCESS;
        }
        
    	else if(state.getBlock() instanceof BlockBush && !(state.getBlock().hasTileEntity(state))){
    		if(state.getBlock().getRegistryName().getResourceDomain().equals("plants") || state.getBlock().getRegistryName().getResourceDomain().equals("minecraft")){
    		genFlowers(world, pos, state.getBlock(), state);
            world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

    		--stack.stackSize;
            return EnumActionResult.SUCCESS;
            }
    		else if(Config.allBushes){
    		genFlowers(world, pos, state.getBlock(), state);
            world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
            
    		--stack.stackSize;
            return EnumActionResult.SUCCESS;
            }
        }}
        
        
     return EnumActionResult.FAIL;
    }
    
    private static void genFlowers(World world, BlockPos pos, Block flower, IBlockState state){
    	if(Config.debug) System.out.println(flower.toString());
    		if(Config.debug) System.out.println("Attempting to place " + flower.toString());
    		Util.genSmallFlowerPatchNearby(world, pos.up(), world.rand, state, flower);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){
    	tooltip.add("Use on grass for plants");
    	tooltip.add("Use on moss stone for vines");
    	tooltip.add("Use on plants for copies");
    }

}
