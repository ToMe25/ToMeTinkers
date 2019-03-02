package com.ToMe.ToMeTinkers.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.shared.client.ParticleEffect;
import slimeknights.tconstruct.tools.TinkerTools;

public class TraitCrystallizedArmor extends AbstractArmorTrait {

	public TraitCrystallizedArmor() {
		super("tometinkers_crystallized", 0xffffff);
	}
	
	@Override
	public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
		if(entity.getEntityWorld().isRemote) {
			return 0;
		}
		NBTTagCompound tag = TinkerUtil.getModifierTag(tool, identifier);
		float chance = 0.4f;//Reinforced 2
		if(chance >= random.nextFloat()) {
			newDamage -= damage;
		}
		return Math.max(0, newDamage);
	}
	
	@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		Entity attacker = source.getImmediateSource();
		float dmg = 0.5f;
		if (attacker instanceof EntityLivingBase) {
			EntityDamageSource damageSource = new EntityDamageSource(DamageSource.CACTUS.damageType, player);
			damageSource.setDamageBypassesArmor();
			damageSource.setDamageIsAbsolute(); 
			//if(attackEntitySecondary(damageSource, damage, attacker, true, false)) {
			if(attackEntitySecondary(damageSource, dmg, attacker, true, false)) {
				TinkerTools.proxy.spawnEffectParticle(ParticleEffect.Type.HEART_BLOOD, attacker, 1);
			}
		}
		return super.onDamaged(armor, player, source, damage, newDamage, evt);
	}
	
}