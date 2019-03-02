package com.ToMe.ToMeTinkers.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class TraitOptimizedArmor extends AbstractArmorTrait {
	
	private static final float maxReduction = 15.0f;
	
	public TraitOptimizedArmor() {
		super("tometinkers_optimized", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		//float reduction = maxReduction - (player.getHealth() - damage) / (player.getMaxHealth() / 100);
		//float reduction = maxReduction - (player.getHealth() - damage) / (player.getMaxHealth() / 50);
		float reduction = maxReduction - (player.getHealth() - damage) / (player.getMaxHealth() / 25);
		if(reduction > maxReduction)
			reduction = maxReduction;
		if(reduction < 0)
			reduction = 0;
		newDamage = newDamage * (100 - reduction) / 100;
		return super.onDamaged(armor, player, source, damage, newDamage, evt);
	}
	
}