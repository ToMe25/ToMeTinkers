package com.ToMe.ToMeTinkers.traits;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.traits.AbstractTrait;

//@Deprecated
public class TraitUnbreakable extends AbstractTrait {

	public TraitUnbreakable() {
		super("unbreakable", 0xffffff);
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		super.applyEffect(rootCompound, modifierTag);
		rootCompound.setBoolean("Unbreakable", true);
	}
	
}