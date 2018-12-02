package com.ToMe.ToMeTinkers.traits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.ToMe.ToMeTinkers.Util;
import com.google.common.collect.Iterables;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitHealthy extends AbstractTrait {
	
	private static final UUID HEALTHY_UUID = UUID.fromString("30303030-e9ca-44a9-b5f7-544d32355443");//30303030 = 0000, 544d32355443 = TM25TC, everything else is random.
	private static final float HEALTH_AMOUNT = 4.0f;
	
	public TraitHealthy() {
		super("healthy", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		if (e.phase == TickEvent.Phase.END && e.side == Side.SERVER) {
			EntityPlayer player = e.player;
			IAttributeInstance healthAtt = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			AttributeModifier modifier = healthAtt.getModifier(HEALTHY_UUID);
			double oldMaxHealth = healthAtt.getAttributeValue();
			//boolean healthy = hasHealthy(player);
			boolean healthy = Util.hasTrait(player, this);
			if (modifier != null && !healthy) {
				healthAtt.removeModifier(modifier);
				float health = player.getHealth() - HEALTH_AMOUNT;
				if(health > 0) {
					player.setHealth(health);
				}
				else {
					player.attackEntityFrom(DamageSource.STARVE, Integer.MAX_VALUE);
				}
			}
			else if (modifier == null && healthy) {
				healthAtt.applyModifier(new AttributeModifier(HEALTHY_UUID, "Healthy trait modifier", HEALTH_AMOUNT, 0));
				player.setHealth(player.getHealth() + HEALTH_AMOUNT);
			}
		}
	}
	
	/*private boolean hasHealthy(EntityPlayer player) {
		//if(ToolHelper.getTraits(player.getHeldItemMainhand()).contains(this)) {
			//return true;
		//}
		//if(ToolHelper.getTraits(player.getHeldItemOffhand()).contains(this)) {
			//return true;
		//}
		//for(ItemStack stack:player.getEquipmentAndArmor()) {//armory expansion compat.
		//for(ItemStack stack:Iterables.<ItemStack>concat(player.getEquipmentAndArmor(), Arrays.asList(new ItemStack[] {player.getHeldItemOffhand()}))) {
		//for(ItemStack stack:getEquipmentAndArmor(player)) {
		for(ItemStack stack:Util.getEquipmentAndArmor(player)) {
			//if(ToolHelper.getTraits(stack).contains(this)) {
				//return true;
			//}
			return isToolWithTrait(stack);
		}
		return false;
	}*/
	
	/*private List<ItemStack> getEquipmentAndArmor(EntityPlayer player) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		//list.addAll(player.getEquipmentAndArmor());
		for(ItemStack stack:player.getEquipmentAndArmor()) {
			list.add(stack);
		}
		list.add(player.getHeldItemOffhand());
		return list;
	}*/
	
}