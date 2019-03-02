package com.ToMe.ToMeTinkers.traits;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.tools.traits.TraitWritable;

public class TraitMagical extends AbstractTrait {

	public TraitMagical() {
		//super("magical", 0xffffff);
		super("tometinkers_magical", 0xffffff);
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		super.applyEffect(rootCompound, modifierTag);
		//NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		//int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + 1;
		//toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		//TagUtil.setToolTag(rootCompound, toolTag);
		NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
		int index = TinkerUtil.getIndexInCompoundList(tagList, getModifierIdentifier());
		NBTTagCompound tag = new NBTTagCompound();
		if(index > -1) {
			tag = tagList.getCompoundTagAt(index);
		}
		else {
			index = tagList.tagCount();
			tagList.appendTag(tag);
		}
		if(!tag.getBoolean(identifier)) {
			//ModifierNBT data = ModifierNBT.readTag(tag);
			//data.level += 1;
			//data.write(tag);
			tag.setBoolean(identifier, true);
			tagList.set(index, tag);
			TagUtil.setModifiersTagList(rootCompound, tagList);
			NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
			int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + 1;
			toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
			TagUtil.setToolTag(rootCompound, toolTag);
		}
	}
	
}