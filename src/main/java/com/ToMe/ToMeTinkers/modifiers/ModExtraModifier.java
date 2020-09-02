package com.ToMe.ToMeTinkers.modifiers;

import net.minecraft.nbt.NBTTagCompound;
//import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
//import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
//import slimeknights.tconstruct.tools.modifiers.ModCreative;
import slimeknights.tconstruct.tools.modifiers.ToolModifier;

public class ModExtraModifier extends /*Modifier*/ToolModifier {
	
	private final int extraMods;
	
	public ModExtraModifier(String identifier, int Modifiers) {
		//super(identifier);
		//super(identifier, 0xffffff);
		super(identifier, Modifiers > 3 ? 0xff0000 : 0xffd700);
		extraMods = Modifiers;
		addAspects(new ModifierAspect.SingleAspect(this));
		//addAspects(new ModifierAspect.DataAspect(this), new ModifierAspect.SingleAspect(this));
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		//ModifierNBT data = ModifierNBT.readTag(modifierTag);
		//int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + data.level * extraMods;
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + extraMods;
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		//TagUtil.setToolTag(rootCompound, toolTag);
	}
	
}