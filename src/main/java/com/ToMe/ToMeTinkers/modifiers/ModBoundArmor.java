package com.ToMe.ToMeTinkers.modifiers;

import com.ToMe.ToMeTinkers.Config;
import com.ToMe.ToMeTinkers.ToMeTinkers;

import c4.conarm.lib.modifiers.ArmorModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class ModBoundArmor extends ArmorModifier {
	
	public ModBoundArmor() {
		super("bound", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
		addAspects(new ModifierAspect.DataAspect(this), new ModifierAspect.SingleAspect(this), ModifierAspect.freeModifier);
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		
	}
	
	@SubscribeEvent
	public void onItemDrop(ItemTossEvent e) {
		ItemStack stack = e.getEntityItem().getItem();
		if(TinkerUtil.getModifiers(stack).contains(this)) {
			if(e.getPlayer().isCreative()) {
				if(Config.debug) {
					ToMeTinkers.logger.warn("Canceled Bound Modifier because the player " + e.getPlayer().getName() + " is in Creative Mode! (if not canceling at this position the Item would get deleted!!!)");
				}
				return;
			}
			if(e.isCancelable()) {
				if(e.getPlayer().addItemStackToInventory(stack)) {
					e.setCanceled(true);
					return;
				}
			}
			if(Config.debug) {
				ToMeTinkers.logger.warn("Bound Modifier Failed!");
			}
		}
		
	}
	
}