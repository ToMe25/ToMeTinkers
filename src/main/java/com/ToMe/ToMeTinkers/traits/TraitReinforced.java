package com.ToMe.ToMeTinkers.traits;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.tools.modifiers.ModReinforced;
import slimeknights.tconstruct.tools.traits.TraitWritable;

//public class TraitReinforced extends ModReinforced implements ITrait {
//public class TraitReinforced extends AbstractTrait {
public class TraitReinforced extends AbstractTraitLeveled {
	
	private static final float chancePerLevel = 0.20f;
	public static final String TAG_UNBREAKABLE = "Unbreakable";
	private final int lvl;
	
	public TraitReinforced(int lvl) {
		super("reinforced", 0xffffff, 10, lvl);
		//super("tometinkers_reinforced", 0xffffff, 10, lvl);
		//super(lvl);
		//super("reinforced", 0xffffff);
		//super("reinforced" + lvl, 0xffffff);
		//super("traitreinforced", 0xffffff);
		this.lvl = lvl;
	}
	
	private float getReinforcedChance(NBTTagCompound modifierTag) {
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		return (float) lvl * chancePerLevel;
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		super.applyEffect(rootCompound, modifierTag);
		if (getReinforcedChance(modifierTag) >= 1f) {
			rootCompound.setBoolean(TAG_UNBREAKABLE, true);
		}
	}
	
	@Override
	public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
		if (entity.getEntityWorld().isRemote) {
			return 0;
		}
		NBTTagCompound tag = TinkerUtil.getModifierTag(tool, identifier);
		float chance = getReinforcedChance(tag);
		if (chance >= random.nextFloat()) {
			newDamage -= damage;
		}
		return Math.max(0, newDamage);
	}
	
	@Override
	public String getLocalizedDesc() {
		return String.format(super.getLocalizedDesc(), Util.dfPercent.format(chancePerLevel));
	}
	
	@Override
	public String getTooltip(NBTTagCompound modifierTag, boolean detailed) {
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		if (lvl == 10) {
			//return Util.translate("modifier.%s.unbreakable", getIdentifier());
			return Util.translate("modifier.reinforced.unbreakable");
		}
		return super.getTooltip(modifierTag, detailed);
	}
	
	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		String loc = String.format(LOC_Extra, getIdentifier());
		if (I18n.canTranslate(loc)) {
			float chance = getReinforcedChance(modifierTag);
			String chanceStr = Util.dfPercent.format(chance);
			if (chance >= 1f) {
				//chanceStr = Util.translate("modifier.%s.unbreakable", getIdentifier());
				chanceStr = Util.translate("modifier.reinforced.unbreakable");
			}
			return ImmutableList.of(Util.translateFormatted(loc, chanceStr));
		}
		return super.getExtraInfo(tool, modifierTag);
	}
	
}