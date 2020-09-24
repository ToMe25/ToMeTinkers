package com.ToMe.ToMeTinkers.traits;

//import java.lang.reflect.Field;
//import java.util.Collection;

import com.ToMe.ToMeTinkers.Config;
//import com.ToMe.ToMeTinkers.ToMeTinkers;
import com.ToMe.ToMeTinkers.Util;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import slimeknights.tconstruct.library.materials.Material;
//import slimeknights.tconstruct.library.modifiers.IModifier;
//import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
//import slimeknights.tconstruct.tools.modifiers.ModExtraTrait;

public class TraitSupermassiveArmor extends AbstractArmorTrait {

	public TraitSupermassiveArmor() {
		//super("tometinkers_supermassive", 0xffffff);
		super("avaritia_supermassive", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void onKnockback(ItemStack armor, EntityPlayer player, LivingKnockBackEvent evt) {
		//int level = getSupermassiveLevel(player);
		int level = getSupermassiveLevel(TagUtil.getTagSafe(armor));
		//System.out.println("old knockback: " + evt.getStrength() + " new knockback: " + evt.getStrength() / (0.55f * level));
		evt.setStrength(evt.getStrength() / (0.55f * level));
		super.onKnockback(armor, player, evt);
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
	
	//private int getSupermassiveLevel(EntityPlayer player) {
		//int lvl = 0;
		//for(ItemStack stack:player.getArmorInventoryList()) {
			//lvl += getSupermassiveLevel(TagUtil.getTagSafe(stack));
		//}
		//return lvl;
	//}
	
	private int getSupermassiveLevel(NBTTagCompound rootTag) {
		int lvl = 0;
		NBTTagList parts = TagUtil.getBaseMaterialsTagList(rootTag);
		for(Material mat:TinkerUtil.getMaterialsFromTagList(parts)) {
			if(mat.getAllTraits().contains(this)) {
				lvl++;
			}
		}
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