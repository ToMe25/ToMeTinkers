package com.ToMe.ToMeTinkers.traits;

//import net.minecraft.block.Block;
//import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
//import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitStrengthening extends AbstractTrait {
	
	private static final float BONUS_DAMAGE = 1.0f;
	private static final float BONUS_SPEED/*MINING_SPEED_BONUS*/ = 1.5f;
	
	public TraitStrengthening() {
		//super("strengthening", 0xffffff);
		super("tometinkers_strengthening", 0xffffff);
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		if(!TinkerUtil.hasTrait(rootCompound, identifier)) {
			ToolNBT data = TagUtil.getToolStats(rootCompound);
			data.attack += BONUS_DAMAGE;
			//data.speed += MINING_SPEED_BONUS;
			data.speed += BONUS_SPEED;
			TagUtil.setToolTag(rootCompound, data.get());
		}
		super.applyEffect(rootCompound, modifierTag);
	}
	
	//@Override
	//public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		//event.setNewSpeed(event.getNewSpeed() + MINING_SPEED_BONUS);
	//}
	
}