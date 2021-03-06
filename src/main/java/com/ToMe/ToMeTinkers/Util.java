package com.ToMe.ToMeTinkers;

import java.lang.reflect.Field;
//import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
//import scala.tools.nsc.doc.model.Trait;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
//import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.tools.modifiers.ModExtraTrait;

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
		//boolean hasTrait = false;
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
		List<Material> materials = TinkerUtil.getMaterialsFromTagList(tagList);
		return materials.size() > 1 ? materials.get(1) : materials.get(0);
		//return TinkerUtil.getMaterialsFromTagList(tagList).get(1);//1 should be Head.
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
	
	//public static boolean hasEmbossment(NBTTagCompound rootCompound, Trait trait) {
	//public static boolean hasEmbossment(NBTTagCompound rootCompound, ITrait trait) {
	public static boolean hasEmbossment(NBTTagCompound rootCompound, ITrait trait, boolean debug) {
		for(IModifier mod:getModifiers(rootCompound)) {
			if(mod instanceof ModExtraTrait) {
				ModExtraTrait embossment = (ModExtraTrait) mod;
				try {
					Field traits = ModExtraTrait.class.getDeclaredField("traits");
					traits.setAccessible(true);
					@SuppressWarnings("unchecked")
					Collection<ITrait> traitList = (Collection<ITrait>) traits.get(embossment);
					if(debug) {
						String traitsStr = "";
						for(ITrait tr:traitList) {
							//traitsStr += tr + ", ";
							traitsStr += tr.getIdentifier() + ", ";
							//ToMeTinkers.logger.info("Util: " + "found Trait " + tr);
						}
						traitsStr = traitsStr.substring(0, traitsStr.length() - 2);
						ToMeTinkers.logger.info("Util: " + "found Traits: " + traitsStr);
					}
					if(traitList.contains(trait)) {
						//NBTTagCompound modifierTag = new NBTTagCompound();
						//NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
						//int index = TinkerUtil.getIndexInList(tagList, trait.getIdentifier());
						//if(index >= 0) {
							//modifierTag = tagList.getCompoundTagAt(index);
						//}
						if(debug) {
							ToMeTinkers.logger.info("Util: " + "found that Trait!");
						}
						return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
					ToMeTinkers.logger.catching(e);
				}
			}
		}
		if(debug) {
			//ToMeTinkers.logger.info("Util: " + "cant find that Trait!");
			ToMeTinkers.logger.info("Util: " + "cant find Trait " + trait.getIdentifier());
			//ToMeTinkers.logger.info("given NBT: " + rootCompound.toString());
			ToMeTinkers.logger.info("modifiers NBT: " + TagUtil.getModifiersTagList(rootCompound).toString());
		}
		return false;
	}
	
	/**
	 * gets the Count of Items the Player currently has equipped with that Trait.
	 * @param player the Player
	 * @param trait the Trait to find
	 * @return
	 */
	public static int getTraitItems(EntityPlayer player, AbstractTrait trait) {
		int count = 0;
		for(ItemStack stack:player.getEquipmentAndArmor()) {
			if(TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), trait.getIdentifier())) {
				count++;
			}
		}
		return count;
	}
	
}