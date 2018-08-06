package shadows.plants2.block.end;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.Placebo;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.block.BushBase;
import shadows.plants2.init.ModRegistry;
import shadows.plants2.util.PlantUtil;

public abstract class BlockEndBush extends BushBase {

	public BlockEndBush(String name) {
		super(name, ModRegistry.END);
		setTickRandomly(true);
	}

	@Override
	public boolean isValidSoil(World world, BlockPos pos, IBlockState state, IBlockState soil) {
		return soil.getBlock() instanceof BlockObsidian || soil.getBlock() == Blocks.END_STONE;
	}

	@Override
	protected void addStatesToList() {
		PlantUtil.END.add(this.getDefaultState());
	}

	@Override
	public void initModels(ModelRegistryEvent e) {
		String name = getRegistryName().getPath();
		PlaceboUtil.sMRL("plants", this, 0, "inventory=true,type=" + name);
		Placebo.PROXY.useRenamedMapper(this, "plants", "", "inventory=false,type=" + name);
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextFloat() < 0.03F) world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, MathHelper.nextFloat(rand, -0.09F, 0.09F), MathHelper.nextFloat(rand, -0.09F, 0.09F), MathHelper.nextFloat(rand, -0.09F, 0.09F));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
		for (int i = 0; i < RANDOM.nextInt(10); i++) {
			ParticleEndRod p = new ParticleEndRod(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, get(), get(), get());
			p.setColor(getColor());
			p.setColorFade(getColorFade());
			manager.addEffect(p);
		}
		return super.addDestroyEffects(world, pos, manager);
	}

	static double get() {
		return MathHelper.nextDouble(RANDOM, -0.09, 0.09);
	}

	abstract int getColor();

	abstract int getColorFade();

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(Placebo.PROXY.translate("plants2." + getRegistryName().getPath() + ".tooltip"));
	}

}
