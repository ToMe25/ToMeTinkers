package com.ToMe.ToMeTinkers.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.shared.client.ParticleEffect;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.modifiers.ModReinforced;
import slimeknights.tconstruct.tools.traits.TraitPrickly;

public class TraitCrystallized extends AbstractTrait {

	public TraitCrystallized() {
		//super("crystallized", 0xffffff);
		super("tometinkers_crystallized", 0xffffff);
	}
	
	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if(target.isEntityAlive() && wasHit) {
			//float damage = 0.5f + Math.max(-0.5f, (float) random.nextGaussian() * 0.75f);
			//float damage = 0.5f + (damageDealt / 4);
			float damage = 0.5f + TagUtil.getToolStats(tool).attack / 4;
			if(damage > 0) {
				EntityDamageSource damageSource = new EntityDamageSource(DamageSource.CACTUS.damageType, player);
				damageSource.setDamageBypassesArmor();
				damageSource.setDamageIsAbsolute(); 
				if(attackEntitySecondary(damageSource, damage, target, true, false)) {
					TinkerTools.proxy.spawnEffectParticle(ParticleEffect.Type.HEART_BLOOD, target, 1);
				}
			}
		}
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
	
}