package com.ToMe.ToMeTinkers.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TraitXPBoostArmor extends AbstractArmorTrait {
	
	private final static int MULTIPLAYER = 4;
	
	public TraitXPBoostArmor() {
		super("xu_xp_boost", 0xFFC4FF8F);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onXPPickUp(PlayerPickupXpEvent e) {
		int xp = e.getOrb().getXpValue();
		if (xp > 0) {
			int addXP = xp * (MULTIPLAYER - 1);
			if (addXP > 0) {
				e.getEntityPlayer().addExperience(addXP);
			}
		}
	}
	
}