package com.ToMe.ToMeTinkers.modifiers;

import com.ToMe.ToMeTinkers.Config;
import com.ToMe.ToMeTinkers.ToMeTinkers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.tools.modifiers.ModSoulbound;
import slimeknights.tconstruct.tools.modifiers.ToolModifier;

public class ModBound extends ToolModifier {

	public ModBound() {
		super("bound", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
		addAspects(new ModifierAspect.DataAspect(this), new ModifierAspect.SingleAspect(this), ModifierAspect.freeModifier);
	}

	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		
	}
	
	@SubscribeEvent
	//@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onItemDrop(ItemTossEvent e) {
		ItemStack stack = e.getEntityItem().getItem();
		if(TinkerUtil.getModifiers(stack).contains(this)) {
			if(e.getPlayer().isCreative()) {
				if(Config.debug) {
					//ToMeTinkers.logger.warn("Canceled Bound Modifier because player is in Creative Mode!(if not canceling at this position the Item would get deleted!!!)");
					//ToMeTinkers.logger.warn("Canceled Bound Modifier because the player is in Creative Mode! (if not canceling at this position the Item would get deleted!!!)");
					ToMeTinkers.logger.warn("Canceled Bound Modifier because the player " + e.getPlayer().getName() + " is in Creative Mode! (if not canceling at this position the Item would get deleted!!!)");
				}
				return;
			}
			//e.setCanceled(true);
			//e.getPlayer().addItemStackToInventory(stack);
			if(e.isCancelable()) {
				//e.setCanceled(true);
				//e.getPlayer().addItemStackToInventory(stack);
				if(e.getPlayer().addItemStackToInventory(stack)) {
					e.setCanceled(true);
					return;
				}
				//else {
					//if(Config.debug) {
						//ToMeTinkers.logger.warn("Bound Modifier Failed!");
					//}
				//}
			}
			if(Config.debug) {
				ToMeTinkers.logger.warn("Bound Modifier Failed!");
			}
		}
		
	}
	
}