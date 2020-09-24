package com.ToMe.ToMeTinkers.traits;

//import java.lang.reflect.Field;
//import java.util.Collection;

import com.ToMe.ToMeTinkers.Config;
//import com.ToMe.ToMeTinkers.ToMeTinkers;
import com.ToMe.ToMeTinkers.Util;

import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.MathHelper;
import slimeknights.tconstruct.library.materials.Material;
//import slimeknights.tconstruct.library.modifiers.IModifier;
//import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.traits.AbstractTrait;
//import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
//import slimeknights.tconstruct.tools.modifiers.ModExtraTrait;
//import slimeknights.tconstruct.tools.modifiers.ModKnockback;
//import slimeknights.tconstruct.tools.modifiers.ModReinforced;

public class TraitSupermassive extends AbstractTrait {
	
	//private static ItemStack helperStack = new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("tconstruct", "broadsword")));
	
	public TraitSupermassive() {
		//super("supermassive", 0xffffff);
		//super("tometinkers_supermassive", 0xffffff);
		super("avaritia_supermassive", 0xffffff);
	}
	
	/*@Override
	public void afterHit(ItemStack stack, EntityLivingBase attacker, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (attacker != null && attacker instanceof EntityLivingBase && target instanceof EntityLivingBase) {
			for(ItemStack stk:Util.getEquipmentAndArmor(attacker)) {
				if (stk != null && stk.hasTagCompound() && stk.getItem() instanceof ToolCore) {
					ToolCore tool = (ToolCore)stk.getItem();
					float puntpower = 0.0f;
					float puntboost = 1.5f;
					puntpower += puntboost * getSupermassiveLevel(stk.getTagCompound());
					float knockback = (AbilityHelper.calcKnockback(attacker, target, stk, tool, stk.getTagCompound(), 0) + 1) * puntpower;
					target.addVelocity((double) (-MathHelper.sin(attacker.rotationYaw * (float) Math.PI / 180.0F) * (float) knockback * 0.5F), 0.1D, (double) (MathHelper.cos(attacker.rotationYaw * (float) Math.PI / 180.0F) * (float) knockback * 0.5F));
				}
			}
			//ItemStack held = attacker.getHeldItem();
			//if (held != null && held.hasTagCompound() && held.getItem() instanceof ToolCore) {
				//ToolCore tool = (ToolCore)held.getItem();
				//NBTTagCompound toolTag = held.getTagCompound().getCompoundTag("InfiTool");
				//float puntpower = 0.0f;
				//float puntboost = 1.5f;
				//puntpower += puntboost * getSupermassiveLevel(stack.getTagCompound());
				//float knockback = (AbilityHelper.calcKnockback(attacker, attacked, held, tool, toolTag, 0) + 1) * puntpower;
				//attacked.addVelocity((double) (-MathHelper.sin(attacker.rotationYaw * (float) Math.PI / 180.0F) * (float) knockback * 0.5F), 0.1D, (double) (MathHelper.cos(attacker.rotationYaw * (float) Math.PI / 180.0F) * (float) knockback * 0.5F));
			//}
		}
		super.afterHit(stack, attacker, target, damageDealt, wasCritical, wasHit);
	}*/
	
	@Override
	public float knockBack(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float knockback, float newKnockback, boolean isCritical) {
		//float puntboost = 1.5f * getSupermassiveLevel(tool.getTagCompound());
		float puntboost = 1.5f * getSupermassiveLevel(TagUtil.getTagSafe(tool));
		//return super.knockBack(tool, player, target, damage, knockback, newKnockback * puntboost, isCritical);
		//return newKnockback * puntboost;
		//return newKnockback + (newKnockback * puntboost);
		//return newKnockback + (newKnockback + 1) * puntboost;
		return (newKnockback + 1) * puntboost;
	}
	
	@Override
	public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
		if(Config.supermassiveResistant) {
			if(entity.getEntityWorld().isRemote) {
				return 0;
			}
			float chance = 0.6f;//Reinforced 3
			if(chance >= random.nextFloat()) {
				newDamage -= damage;
			}
			return Math.max(0, newDamage);
		}
		else {
			return super.onToolDamage(tool, damage, newDamage, entity);
		}
	}
	
	private int getSupermassiveLevel(NBTTagCompound rootTag) {
		int lvl = 0;
		NBTTagList parts = TagUtil.getBaseMaterialsTagList(rootTag);
		for(Material mat:TinkerUtil.getMaterialsFromTagList(parts)) {
			if(mat.getAllTraits().contains(this)) {
				lvl++;
			}
		}
		//helperStack.setTagCompound(rootTag);
		//for(IModifier mod:TinkerUtil.getModifiers(helperStack)) {
		/*for(IModifier mod:Util.getModifiers(rootTag)) {
			if(mod instanceof ModExtraTrait) {
				ModExtraTrait embossment = (ModExtraTrait) mod;
				try {
					Field traits = ModExtraTrait.class.getDeclaredField("traits");
					traits.setAccessible(true);
					Collection<ITrait> trait = (Collection<ITrait>) traits.get(embossment);
					if(trait.contains(this)) {
						lvl++;
					}
					//for(ITrait t:trait) {
						//if(t instanceof TraitCosmic) {
							//lvl++;
							//ToMeTinkers.logger.info("TraitSupermassive: " + "has Supermassive Embossment!");
						//}
					//}
					//Field mat = ModExtraTrait.class.getDeclaredField("material");
					//mat.setAccessible(true);
					//Material material = (Material) mat.get(embossment);
					//if(material.getAllTraits().contains(this)) {
						//lvl++;
						//ToMeTinkers.logger.info("TraitCosmic: " + "has Supermassive Embossment!");
					//}
				} catch (Exception e) {
					ToMeTinkers.logger.catching(e);
				}
			}
		}*/
		if(Util.hasEmbossment(rootTag, this, false)) {
			lvl++;
		}
		return lvl;
	}
	
}