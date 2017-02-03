package shadows.plants.util;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.plants.block.BushBase;
import shadows.plants.block.internal.cosmetic.BlockDoubleMetaBush;
import shadows.plants.block.internal.cosmetic.BlockMetaBush;
import shadows.plants.common.EnumModule;

public class Util {

	public static boolean isException(Block block){
		return (block instanceof BlockMetaBush || block instanceof BlockDoubleMetaBush);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModel(Block block){
		if (Config.debug) System.out.println("Registered Model " + block.toString());
		if (!isException(block)) ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		else if (isException(block)) handleExceptionRenders(block);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModel(Item item){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void register(Block block){
		if (Config.debug) System.out.println("Registered " + block.toString());
		GameRegistry.register(block);
		if (!isException(block)) GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}
	
	public static void register(Item item){
		GameRegistry.register(item);
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
    
    public static int getMaxMetadata(String name){//This should return the max used value of a block's PropertyInteger(META)
    	int ij = 0;
    	
    	
    	switch(name){
    	case("cosmetic_1"): ij = 15; break;
    	case("cosmetic_2"): ij = 15; break;
    	case("cosmetic_3"): ij = 15; break;
    	case("cosmetic_4"): ij = 5; break;
    	case("cosmetic_5"): ij = 2; break; //this one is 0-7 bc anywhere I need it, it will be assigning the PropertyInteger
    	}
    	
    	return ij;
    }
	
    private static int getBlockNumber(BushBase block){
    	
    	if (block.getType() == EnumModule.COSMETIC) return Integer.parseInt(block.getRegistryName().getResourcePath().substring(9));
    	else return 0;
    }
    
	@SideOnly(Side.CLIENT)
	public static void handleExceptionRenders(Block block){
		if (block instanceof BlockMetaBush){
		for (int i = 0; i < 16; i++){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(new ResourceLocation("plants:cosmetic/" + Util.getBlockNumber((BushBase) block) + "/" + "cosmetic" + "." + i), "inventory"));
		}}
		if (block instanceof BlockDoubleMetaBush){
		for (int i = 0; i < 8; i++){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(new ResourceLocation("plants:cosmetic/" + Util.getBlockNumber((BushBase) block) + "/" + "cosmetic" + "." + i), "inventory"));
		}}}
	
		public static void addSimpleShapeless(Item result, int resultquantity, int resultmeta, Item reagent, int reagentmeta, Item reagent2, Item reagent3){
			GameRegistry.addShapelessRecipe(new ItemStack(result, resultquantity, resultmeta), new ItemStack(reagent, 1, reagentmeta), new ItemStack(reagent2, 1, reagentmeta), new ItemStack(reagent3, 1, reagentmeta));
	}
		public static void addSimpleShapeless(Item result, int resultquantity, int resultmeta, Item reagent, int reagentmeta){
			GameRegistry.addShapelessRecipe(new ItemStack(result, resultquantity, resultmeta), new ItemStack(reagent, 1, reagentmeta));
	}
		public static void addSimpleShapeless(Item result, int resultmeta, Item reagent, int reagentmeta){
			addSimpleShapeless(result, 1, resultmeta, reagent, reagentmeta);
	}
		public static void addSimpleShapeless(Item result, Item reagent, int reagentmeta){
			addSimpleShapeless(result, 1, 0, reagent, reagentmeta);
	}
		public static void addSimpleShapeless(Item result, int resultmeta, Item reagent){
			addSimpleShapeless(result, 1, resultmeta, reagent, 0);
	}
		public static void addSimpleShapeless(Item result, int resultmeta, Block reagent){
			addSimpleShapeless(result, 1, resultmeta, Item.getItemFromBlock(reagent), 0);
	}
		public static void addSimpleShapeless(Item result, int resultmeta, Block reagent, int reagentmeta){
			addSimpleShapeless(result, 1, resultmeta, Item.getItemFromBlock(reagent), reagentmeta);
	}
		public static void addSimpleShapeless(Item result, int resultquantity, int resultmeta, Block reagent, int reagentmeta){
			GameRegistry.addShapelessRecipe(new ItemStack(result, resultquantity, resultmeta), new ItemStack(reagent, 1, reagentmeta));
	}
		public static void addSimpleShapeless(Item result, Item reagent){
			addSimpleShapeless(result, 1, 0, reagent, 0);
	}
	
	
	
}
