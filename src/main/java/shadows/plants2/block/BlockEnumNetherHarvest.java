package shadows.plants2.block;

import java.util.Random;

import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent.Register;
import shadows.plants2.data.Config;
import shadows.plants2.data.enums.IHarvestableEnum;
import shadows.plants2.data.enums.TheBigBookOfEnums.Generic;
import shadows.plants2.gen.NetherGen;
import shadows.plants2.util.RecipeHelper;

public class BlockEnumNetherHarvest<E extends Enum<E> & IHarvestableEnum> extends BlockEnumHarvestBush<E> {

	public BlockEnumNetherHarvest(String name, Class<E> enumClass, int predicate) {
		super(name, EnumPlantType.Nether, enumClass, predicate);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return (world.getBlockState(pos).getBlock().isReplaceable(world, pos) && state.getBlock() instanceof BlockNetherrack) || super.canPlaceBlockAt(world, pos);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		return NetherGen.isValidSoil(world.getBlockState(pos.down()), world, pos.down(), this);
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if (player.getHeldItemMainhand().getItem() instanceof ItemShears) return super.removedByPlayer(state, world, pos, player, willHarvest);
		else {
			player.setFire(10);
			return super.removedByPlayer(state, world, pos, player, willHarvest);
		}
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return super.canGrow(world, pos, state, isClient) && Config.hardNether ? world.getBlockState(pos.down()).getBlock().canSustainPlant(world.getBlockState(pos.down()), world, pos.down(), EnumFacing.UP, this) : true;
	}

	@Override
	public void initRecipes(Register<IRecipe> event) {
		RecipeHelper.addSimpleShapeless(Items.BLAZE_POWDER, Generic.BLAZE_PETAL.get(), 3);
		RecipeHelper.addSimpleShapeless(Items.MAGMA_CREAM, Generic.MAGMA_JELLY.get(), 3);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float chance, int fortune) {
		if (harvesters.get() == null) return;
		super.dropBlockAsItemWithChance(world, pos, state, chance, fortune);
	}
	
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextFloat() < 0.03F) world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, MathHelper.nextDouble(rand, -0.05, 0.05), 0.06, MathHelper.nextDouble(rand, -0.05, 0.05));
	}

}
