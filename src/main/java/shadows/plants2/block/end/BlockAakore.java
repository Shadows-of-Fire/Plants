package shadows.plants2.block.end;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAakore extends BlockEndBush {

	public BlockAakore() {
		super("aakore");
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// TODO Auto-generated method stub
		super.updateTick(worldIn, pos, state, rand);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (RANDOM.nextInt(13) == 0 && entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200, 1));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
		ParticleEndRod p = new ParticleEndRod(world, pos.getX() + get(), pos.getY() + get(), pos.getZ() + get(), get(), get(), get());
		p.setColor(0x00FFAA);
		p.setColorFade(0xAA00FF);
		manager.addEffect(p);
		return super.addDestroyEffects(world, pos, manager);
	}

	static double get() {
		return MathHelper.nextDouble(RANDOM, -0.4, 0.4);
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(RANDOM.nextInt(50) == 0 ? Items.DIAMOND : Item.getItemFromBlock(this));
	}

}
