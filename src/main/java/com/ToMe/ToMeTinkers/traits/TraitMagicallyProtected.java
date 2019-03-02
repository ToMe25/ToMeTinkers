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
	private static final float MAX_REDUCTION = 80.0f;//80%
	
	public TraitMagicallyProtected() {
		//super("magicallyprotected", 0xffffff);
		super("tometinkers_magically_protected", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onPlayerHit(LivingHurtEvent e) {
		if(e.getEntityLiving() instanceof EntityPlayer && !e.getEntityLiving().getEntityWorld().isRemote) {
		//if(!e.getEntityLiving().getEntityWorld().isRemote) {
			if(e.getSource().isMagicDamage()) {
				EntityPlayer player = (EntityPlayer) e.getEntity();
				//double reduction = Util.getTraitItems(player, this) * AMOUNT_REDUCED > MAX_REDUCTION ? MAX_REDUCTION : Util.getTraitItems(player, this) * AMOUNT_REDUCED;
				//double reduction = Util.getTraitItems(player, this) * AMOUNT_REDUCED;
				//float reduction = Util.getTraitItems(player, this) * AMOUNT_REDUCED;
				//if(reduction > MAX_REDUCTION) {
					//reduction = MAX_REDUCTION;
				//}
				//float dmg = e.getAmount() * (100 - AMOUNT_REDUCED) / 100;
				//float dmg = e.getAmount() * (100 - reduction) / 100;
				//ToMeTinkers.logger.info("TraitMagicallyProtected: " + "Reduced Damage from " + e.getAmount() + " to " + dmg + ".");
				//e.setAmount(dmg);
				//if(hasTrait(player)) {
				if(Util.hasTrait(player, this)) {
					//float dmg = e.getAmount() / 100 * (100 - AMOUNT_REDUCED);
					//float dmg = e.getAmount() * (100 - AMOUNT_REDUCED) / 100;
					//ToMeTinkers.logger.info("TraitMagicallyProtected: " + dmg);
					//e.setAmount(dmg);
					float reduction = Util.getTraitItems(player, this) * AMOUNT_REDUCED;
					if(reduction > MAX_REDUCTION) {
						reduction = MAX_REDUCTION;
					}
					float dmg = e.getAmount() * (100 - reduction) / 100;
					//ToMeTinkers.logger.info("TraitMagicallyProtected: " + "Reduced Damage from " + e.getAmount() + " to " + dmg + ".");
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