/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 29, 2015, 10:12:50 PM (GMT)]
 */
package shadows.plants.item.addon.botania;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Optional.Method;
import shadows.plants.util.Data;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.sound.BotaniaSoundEvents;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemExcalibur extends ItemSword implements ILensEffect, IManaUsingItem {

	private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
	private static final String TAG_HOME_ID = "homeID";

	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("EXCALIBUR", 3, -1, 6.2F, 6F, 40);

	Achievement achievement;

	public ItemExcalibur() {
		super(toolMaterial);
		setCreativeTab(Data.TAB_I);
		setUnlocalizedName(Data.MODID + ".excalibur");
		setRegistryName("excalibur");
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if (par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			PotionEffect haste = player.getActivePotionEffect(Potion.getPotionById(3));
			float check = haste == null ? 0.16666667F : haste.getAmplifier() == 1 ? 0.5F : 0.4F;

			if (player.getHeldItemMainhand() == par1ItemStack && player.swingProgress == check && !par2World.isRemote) {
				EntityManaBurst burst = getBurst(player, par1ItemStack);
				par2World.spawnEntityInWorld(burst);
				par2World.playSound(null, player.posX, player.posY, player.posZ, BotaniaSoundEvents.terraBlade,
						SoundCategory.PLAYERS, 0.4F, 1.4F);
			}
		}
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public boolean usesMana(ItemStack stack) {
		return false;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public boolean isItemTool(ItemStack p_77616_1_) {
		return true;
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return Integer.MAX_VALUE;
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		if (slot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(),
					new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 10, 0));
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getAttributeUnlocalizedName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 0.3, 1));
		}
		return multimap;
	}

	@Method(modid = Data.BOTANIA)
	public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
		EntityManaBurst burst = new EntityManaBurst(player);

		float motionModifier = 7F;

		burst.setColor(0xFFFF20);
		burst.setMana(1);
		burst.setStartingMana(1);
		burst.setMinManaLoss(200);
		burst.setManaLossPerTick(1F);
		burst.setGravity(0F);
		burst.setMotion(burst.motionX * motionModifier, burst.motionY * motionModifier, burst.motionZ * motionModifier);

		ItemStack lens = stack.copy();
		ItemNBTHelper.setString(lens, TAG_ATTACKER_USERNAME, player.getCommandSenderEntity().getName());
		burst.setSourceLens(lens);
		return burst;
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public void apply(ItemStack stack, BurstProperties props) {
		// NO-OP
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public boolean collideBurst(IManaBurst burst, RayTraceResult pos, boolean isManaBlock, boolean dead,
			ItemStack stack) {
		return dead;
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public void updateBurst(IManaBurst burst, ItemStack stack) {
		EntityThrowable entity = (EntityThrowable) burst;
		AxisAlignedBB axis = new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX,
				entity.lastTickPosY, entity.lastTickPosZ).expand(1, 1, 1);

		String attacker = ItemNBTHelper.getString(burst.getSourceLens(), TAG_ATTACKER_USERNAME, "");
		int homeID = ItemNBTHelper.getInt(stack, TAG_HOME_ID, -1);
		if (homeID == -1) {
			AxisAlignedBB axis1 = new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX,
					entity.lastTickPosY, entity.lastTickPosZ).expand(5, 5, 5);
			List<EntityLivingBase> entities = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis1);
			for (EntityLivingBase living : entities) {
				if (living instanceof EntityPlayer || living instanceof EntityDragon || living instanceof EntityWither
						|| !(living instanceof IMob) || living.hurtTime != 0)
					continue;

				homeID = living.getEntityId();
				ItemNBTHelper.setInt(stack, TAG_HOME_ID, homeID);
				break;
			}
		}

		List<EntityLivingBase> entities = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);

		if (homeID != -1) {
			Entity home = entity.worldObj.getEntityByID(homeID);
			if (home != null) {
				Vector3 vecEntity = Vector3.fromEntityCenter(home);
				Vector3 vecThis = Vector3.fromEntityCenter(entity);
				Vector3 vecMotion = vecEntity.subtract(vecThis);
				Vector3 vecCurrentMotion = new Vector3(entity.motionX, entity.motionY, entity.motionZ);

				vecMotion.normalize().multiply(vecCurrentMotion.mag());
				burst.setMotion(vecMotion.x, vecMotion.y, vecMotion.z);
			}
		}

		for (EntityLivingBase living : entities) {
			if (living instanceof EntityPlayer
					&& (((EntityPlayer) living).getCommandSenderEntity().getName().equals(attacker)
							|| living.worldObj.getMinecraftServer() != null
									&& living.worldObj.getMinecraftServer().isPVPEnabled()))
				continue;

			if (living.hurtTime == 0) {
				int cost = 1;
				int mana = burst.getMana();
				if (mana >= cost) {
					burst.setMana(mana - cost);
					float damage = 4F + toolMaterial.getDamageVsEntity();
					if (!burst.isFake() && !entity.worldObj.isRemote) {
						EntityPlayer player = living.worldObj.getPlayerEntityByName(attacker);
						living.attackEntityFrom(
								player == null ? DamageSource.magic : DamageSource.causePlayerDamage(player), damage);
						entity.setDead();
						break;
					}
				}
			}
		}
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public boolean doParticles(IManaBurst burst, ItemStack stack) {
		return true;
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase,
			@Nonnull EntityLivingBase par3EntityLivingBase) {
		if (usesMana(par1ItemStack))
			ToolCommons.damageItem(par1ItemStack, 1, par3EntityLivingBase, getManaPerDamage());
		return true;
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World world, IBlockState state,
			@Nonnull BlockPos pos, @Nonnull EntityLivingBase entity) {
		if (usesMana(stack) && state.getBlockHardness(world, pos) != 0F)
			ToolCommons.damageItem(stack, 1, entity, getManaPerDamage());

		return true;
	}

	public int getManaPerDamage() {
		return 60;
	}

	@Override
	@Method(modid = Data.BOTANIA)
	public boolean getIsRepairable(ItemStack par1ItemStack, @Nonnull ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == ModItems.manaResource && par2ItemStack.getItemDamage() == 0 ? true
				: super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Nonnull
	@Override
	@Method(modid = Data.BOTANIA)
	public EnumRarity getRarity(ItemStack stack) {
		return BotaniaAPI.rarityRelic;
	}
}
