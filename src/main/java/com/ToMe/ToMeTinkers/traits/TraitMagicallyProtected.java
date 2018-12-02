package com.ToMe.ToMeTinkers.traits;

import com.ToMe.ToMeTinkers.ToMeTinkers;
import com.ToMe.ToMeTinkers.Util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitMagicallyProtected extends AbstractTrait {
	
	private static final float AMOUNT_REDUCED = 20.0f;//20%
	
	public TraitMagicallyProtected() {
		super("magicallyprotected", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onPlayerHit(LivingHurtEvent e) {
		if(e.getEntityLiving() instanceof EntityPlayer && !e.getEntityLiving().getEntityWorld().isRemote) {
		//if(!e.getEntityLiving().getEntityWorld().isRemote) {
			if(e.getSource().isMagicDamage()) {
				EntityPlayer player = (EntityPlayer) e.getEntity();
				//if(hasTrait(player)) {
				if(Util.hasTrait(player, this)) {
					//float dmg = e.getAmount() / 100 * (100 - AMOUNT_REDUCED);
					float dmg = e.getAmount() * (100 - AMOUNT_REDUCED) / 100;
					ToMeTinkers.logger.info("TraitMagicallyProtected: " + dmg);
					e.setAmount(dmg);
				}
			}
		 }
	}
	
	/*private boolean hasTrait(EntityPlayer player) {
		//if(ToolHelper.getTraits(player.getHeldItemMainhand()).contains(this)) {
			//return true;
		//}
		//if(ToolHelper.getTraits(player.getHeldItemOffhand()).contains(this)) {
			//return true;
		//}
		for(ItemStack stack:player.getEquipmentAndArmor()) {//armory expansion compat.
			//if(ToolHelper.getTraits(stack).contains(this)) {
				//return true;
			//}
			return isToolWithTrait(stack);
		}
		return false;
	}*/
	
}