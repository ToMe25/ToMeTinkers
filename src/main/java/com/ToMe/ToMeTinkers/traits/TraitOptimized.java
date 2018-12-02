package com.ToMe.ToMeTinkers.traits;

import com.ToMe.ToMeTinkers.ToMeTinkers;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitOptimized extends AbstractTrait {
	
	private static final int maxSpeedBonus = 3;
	
	public TraitOptimized() {
		super("optimized", 0xffffff);
	}
	
	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		Block block = event.getState().getBlock();
		int hlvl = tool.getItem().getHarvestLevel(tool, block.getHarvestTool(event.getState()), event.getEntityPlayer(), event.getState());
		int dif = hlvl - block.getHarvestLevel(event.getState());
		if(dif < 0) {
			dif = -dif;
		}
		event.setNewSpeed(event.getNewSpeed() + (maxSpeedBonus - dif));
		//ToMeTinkers.logger.info("" + event.getNewSpeed());
	}
	
}