package com.ToMe.ToMeTinkers.modifiers;

import c4.conarm.lib.modifiers.ArmorModifier;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
//import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public class ModExtraModifierArmor extends ArmorModifier {
	
	private final int extraMods;
	
	public ModExtraModifierArmor(String identifier, int Modifiers) {
		super(identifier, Modifiers > 3 ? 0xff0000 : 0xffd700);
		extraMods = Modifiers;
		addAspects(new ModifierAspect.SingleAspect(this));
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		//ModifierNBT data = ModifierNBT.readTag(modifierTag);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + extraMods;
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
	}
	
}