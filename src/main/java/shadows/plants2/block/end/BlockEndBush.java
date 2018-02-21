package shadows.plants2.block.end;

import java.util.Random;

import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.EnumPlantType;
import shadows.placebo.Placebo;
import shadows.placebo.util.PlaceboUtil;
import shadows.plants2.block.BushBase;
import shadows.plants2.util.PlantUtil;

public abstract class BlockEndBush extends BushBase {

	public BlockEndBush(String name) {
		super(name, EnumPlantType.Plains);
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
		String name = getRegistryName().getResourcePath();
		PlaceboUtil.sMRL("plants", this, 0, "inventory=true,type=" + name);
		Placebo.PROXY.useRenamedMapper(this, "plants", "", "inventory=false,type=" + name);
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextFloat() < 0.03F) world.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, MathHelper.nextFloat(rand, -0.09F, 0.09F), MathHelper.nextFloat(rand, -0.09F, 0.09F), MathHelper.nextFloat(rand, -0.09F, 0.09F));
	}

}
