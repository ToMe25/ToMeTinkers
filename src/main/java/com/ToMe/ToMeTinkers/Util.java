package com.ToMe.ToMeTinkers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class Util {
	
	/*//public static List<ItemStack> getEquipmentAndArmor(EntityPlayer player) {
	public static List<ItemStack> getEquipmentAndArmor(Entity player) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		//list.addAll(player.getEquipmentAndArmor());
		for(ItemStack stack:player.getEquipmentAndArmor()) {
			list.add(stack);
			//ToMeTinkers.logger.info("Util: " + stack.getDisplayName());
		}
		//list.add(player.getHeldItemMainhand());
		//list.add(player.getHeldItemOffhand());
		return list;
	}*/
	
	public static boolean hasTrait(EntityPlayer player, AbstractTrait trait) {
		boolean hasTrait = false;
		//for(ItemStack stack:getEquipmentAndArmor(player)) {//armory expansion compat.
		for(ItemStack stack:player.getEquipmentAndArmor()) {//armory expansion compat.
			//return TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), trait.getIdentifier());//isToolWithTrait
			//boolean ht = TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), trait.getIdentifier());
			//if(ht) {
				//hasTrait = true;
			//}
			//hasTrait = TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), trait.getIdentifier()) ? true : hasTrait;
			if(TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), trait.getIdentifier())) {
				return true;
			}
		}
		return false;
		//return hasTrait;
	}
	
	public static Material getHeadMaterial(NBTTagCompound rootTag) {
		NBTTagList tagList = TagUtil.getBaseMaterialsTagList(rootTag);
		return TinkerUtil.getMaterialsFromTagList(tagList).get(1);//1 should be Head.
	}
	
	public static NonNullList<IModifier> getModifiers(NBTTagCompound rootCompound) {
		NonNullList<IModifier> result = NonNullList.create();
		NBTTagList modifierList = TagUtil.getModifiersTagList(rootCompound);
		//ToMeTinkers.logger.info(rootCompound.toString());
		//ToMeTinkers.logger.info(rootCompound.getTag("Modifiers").toString());
		//ToMeTinkers.logger.info(modifierList.toString());
		for(int i = 0; i < modifierList.tagCount(); i++) {
		//for(NBTBase tag:modifierList) {
			NBTTagCompound tag = modifierList.getCompoundTagAt(i);
			//ToMeTinkers.logger.info(tag.toString());
			//ModifierNBT data = ModifierNBT.readTag(tag);
			ModifierNBT data = ModifierNBT.readTag((NBTTagCompound)tag);
			IModifier modifier = TinkerRegistry.getModifier(data.identifier);
			if(modifier != null) {
				result.add(modifier);
				//if(data.identifier.contains("extratrait")) {
					//ToMeTinkers.logger.info(data.identifier);
				//}
			}
			//else {
				//if(data.identifier.contains("extratrait")) {
					//ToMeTinkers.logger.info(data.identifier);
				//}
				//ToMeTinkers.logger.warn("Util: " + "Couldn't find a Modifier named " + data.identifier + ".");
			//}
		}
		return result;
	}
	
}