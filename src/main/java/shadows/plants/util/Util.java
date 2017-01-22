package shadows.plants.util;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.EnumModule;
import shadows.plants.registry.modules.AE2Module;
import shadows.plants.registry.modules.BotaniaModule;

public class Util {
	//normal methods
	@SideOnly(Side.CLIENT)
	public static void initModel(Block block){
		if (Config.debug) System.out.println("Registered Model " + block.toString());
		if (!(block instanceof BlockMetaBush)) ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModel(Item item){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void register(Block block){
		if (Config.debug) System.out.println("Registered " + block.toString());
		GameRegistry.register(block);
		if (!(block instanceof BlockMetaBush)) GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}
	
	public static void register(Item item){
		GameRegistry.register(item);
	}
	
	@Nullable
	public static Block getFarmlandFromModule(EnumModule module){
		switch(module){
		case APPLIED : return AE2Module.ae_farmland;
		case BOTANICAL : return BotaniaModule.b_farmland;
		case HOSTILE : 
		case COSMETIC :
		case MEME : return Blocks.FARMLAND;
		
		default : return null;
		}
	}
	
	public static void spawnParticlesAtBlock(World world, BlockPos pos, EnumParticleTypes particle){
		Random rand = world.rand;
		float f = rand.nextFloat();
		world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double)pos.getX() + f, (double)pos.getY() + f*2, (double)pos.getZ() + f, (double)f, (double)((float)f - rand.nextFloat() - 1.0F), (double)((float)f + rand.nextFloat()) - 0.5D, new int[0]);
	}
	
	
	//Spawns an EntityLiving in world at position.
    public static void spawnCreature(World world, Entity entity, double x, double y, double z)
    {
    if (entity instanceof EntityDragon){
    	
        world.getChunkFromBlockCoords(new BlockPos(0, 128, 0));
        EntityDragon entitydragon = new EntityDragon(world);
        entitydragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
        entitydragon.setPosition(x, y + 50, z);
        world.spawnEntityInWorld(entitydragon);
    }
        
    else if (!(entity instanceof EntityDragon) && entity instanceof EntityLivingBase)
    {
        EntityLiving entityliving = (EntityLiving)entity;
        entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
        entityliving.rotationYawHead = entityliving.rotationYaw;
        entityliving.renderYawOffset = entityliving.rotationYaw;
        entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
        world.spawnEntityInWorld(entity);
        entityliving.playLivingSound();
    
	
    }
    }
	
    //Returns true if an item is found in a mob drop list.
    public static boolean dropSearchFinder(List<EntityItem> list, ItemStack stack){
    	Iterator<EntityItem> iterator = list.iterator();
    	while (iterator.hasNext()){
    		EntityItem item = iterator.next();
    		if (item.getEntityItem().getItem() == stack.getItem()) {
        		//System.out.println("item is " + item.getEntityItem().toString() + " while target = " + stack.toString() + " result: true");
    			return true;

    		}
    		//System.out.println("item is " + item.getEntityItem().toString() + " while target = " + stack.toString() + " result: false");
    	}
    	
    	return false;
    	
    }
	
	
}
