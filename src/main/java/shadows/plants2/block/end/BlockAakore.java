package shadows.plants2.block.end;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAakore extends BlockEndBush {

	public BlockAakore() {
		super("aakore");
	}

	static final AxisAlignedBB largeboi = new AxisAlignedBB(-5, -5, -5, 5, 5, 5);

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		for (Entity e : world.getEntitiesWithinAABB(Entity.class, largeboi.offset(pos))) {
			double x = (pos.getX() + 0.5 - e.posX) / 3.2;
			double y = (pos.getY() + 0.5 - e.posY) * 1.23;
			double z = (pos.getZ() + 0.5 - e.posZ) / 3.2;
			e.addVelocity(x, y, z);
			e.velocityChanged = true;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (RANDOM.nextInt(13) == 0 && !world.isRemote && entity instanceof EntityLivingBase && !((EntityLivingBase) entity).isPotionActive(MobEffects.LEVITATION)) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 40, 1));
		}
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(RANDOM.nextInt(50) == 0 ? Items.DIAMOND : Item.getItemFromBlock(this));
	}

	@Override
	int getColor() {
		return 0x00FFAA;
	}

	@Override
	int getColorFade() {
		return 0xAA00FF;
	}

}
