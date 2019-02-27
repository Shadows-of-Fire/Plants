package shadows.plants2.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shadows.placebo.interfaces.IHasRecipe;
import shadows.placebo.item.ItemBase;
import shadows.plants2.Plants2;
import shadows.plants2.data.PlantConfig;
import shadows.plants2.util.PlantUtil;

public final class ItemPlantball extends ItemBase implements IHasRecipe {

	public ItemPlantball() {
		super("plantball", Plants2.INFO);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty() && player.canPlayerEdit(pos, facing, stack)) {
			IBlockState worldState = world.getBlockState(pos);
			SoundType soundtype = SoundType.PLANT;
			IBlockState flower = PlantUtil.getFlowerState(world.rand);
			IBlockState desert = PlantUtil.getDesertFlowerState(world.rand);

			if (worldState.getBlock() == Blocks.MOSSY_COBBLESTONE && facing.getAxis().isHorizontal()) {
				if (!world.isRemote) PlantUtil.placeVine(world, PlantUtil.getRandomVine(world.rand), pos, facing);

				world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, 1, 1);

				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}

			else if (desert.getBlock().canPlaceBlockAt(world, pos.up())) {
				if (isBlacklisted(desert)) return EnumActionResult.FAIL;

				if (!world.isRemote) {
					genFlowers(world, pos, desert);
					if (world.rand.nextInt(10) == 0) genFlowers(world, pos, desert);
				}

				world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, 1, 1);

				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}

			else if (flower.getBlock().canPlaceBlockAt(world, pos.up())) {
				if (isBlacklisted(flower)) return EnumActionResult.FAIL;

				if (!world.isRemote) {
					genFlowers(world, pos, flower);
					if (world.rand.nextInt(10) == 0) genFlowers(world, pos, flower);
				}

				world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, 1, 1);

				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}

			else if (worldState.getBlock() instanceof BlockBush && !worldState.getBlock().hasTileEntity(worldState)) {
				if (isBlacklisted(worldState)) return EnumActionResult.FAIL;

				if (PlantConfig.allBushes || worldState.getBlock().getRegistryName().getNamespace().equals(Plants2.MODID) || worldState.getBlock().getRegistryName().getNamespace().equals("minecraft")) {

					if (!world.isRemote) genFlowers(world, pos, worldState);

					world.playSound(player, pos.up(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, 1, 1);

					stack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
			}
		}

		return EnumActionResult.FAIL;
	}

	private static void genFlowers(World world, BlockPos pos, IBlockState state) {
		if (world.provider instanceof WorldProviderHell) PlantUtil.genFlowerPatchForNether(world, pos, world.rand, state);
		else PlantUtil.genSmallFlowerPatchNearby(world, pos.up(), world.rand, state);
	}

	private static Map<IBlockState, Boolean> CACHE = new HashMap<>();

	private static boolean isBlacklisted(IBlockState state) {
		if (CACHE.getOrDefault(state, false)) return false;
		if (PlantConfig.REGNAME_BL.contains(state.getBlock().getRegistryName())) return true;
		if (PlantConfig.MODID_BL.contains(state.getBlock().getRegistryName().getNamespace())) return true;
		CACHE.put(state, true);
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add("Use on grass for plants");
		tooltip.add("Use on moss stone for vines");
		tooltip.add("Use on a plant to copy it");
	}

	@Override
	public void initRecipes(Register<IRecipe> e) {
		Plants2.HELPER.addShapeless(new ItemStack(this, 5), "plant", "plant", "plant", "plant", "plant", "plant", "plant", "plant", "plant");
		Plants2.HELPER.addShapeless(new ItemStack(this, 2), "plant", "plant", "plant", "plant");
	}

}
