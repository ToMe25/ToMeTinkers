package com.ToMe.ToMeTinkers.traits;

import c4.conarm.common.armor.utils.ArmorTagUtil;
import c4.conarm.lib.armor.ArmorNBT;
import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.nbt.NBTTagCompound;
//import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitStrengtheningArmor extends AbstractArmorTrait {
	
	private static final float BONUS_DEFENSE = 1.5f;
	private static final float BONUS_THOUGHNESS = 1.0f;
	
	public TraitStrengtheningArmor() {
		//super("strengthening", 0xffffff);
		super("tometinkers_strengthening", 0xffffff);
	}
	
	@Override
	public void applyEffect(NBTTagCompound root, NBTTagCompound modifier) {
		if(!TinkerUtil.hasTrait(root, identifier)) {
			ArmorNBT data = ArmorTagUtil.getArmorStats(root);
			data.defense += BONUS_DEFENSE;
			data.toughness += BONUS_THOUGHNESS;
			TagUtil.setToolTag(root, data.get());
		}
		super.applyEffect(root, modifier);
	}
	
}