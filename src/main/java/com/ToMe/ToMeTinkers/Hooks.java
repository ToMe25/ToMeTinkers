package com.ToMe.ToMeTinkers;

import javax.annotation.Nonnull;
//import javax.annotation.Nullable;

//import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
//import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.Loader;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class Hooks {
	
	/*@Nullable
	public static EntityItem dropItem(@Nonnull EntityPlayer player, @Nonnull ItemStack item, boolean includeName) {
		//NBTTagCompound nbt = item.getTagCompound();
		//if(nbt != null) {
			//if(TinkerUtil.hasModifier(nbt, "bound")) {
			//if(TinkerUtil.hasModifier(nbt, "bound") || TinkerUtil.hasModifier(nbt, "bound_armor")) {
				//return null;
			//}
		//}
		for(IModifier mod:TinkerUtil.getModifiers(item)) {
			if(mod.equals(Modifiers.bound)) {
				//System.out.println("cancel");
				return null;
			}
			else if(Loader.isModLoaded("conarm")) {
				if(mod.equals(ModifiersArmor.bound)) {
					//System.out.println("cancel");
					return null;
				}
			}
			//else {
				//System.out.println(mod);
			//}
		}
		for(StackTraceElement trace:Thread.currentThread().getStackTrace()) {
			System.out.println(trace);
		}
		//System.out.println("dont cancel");
		return ForgeHooks.onPlayerTossEvent(player, item, includeName);
	}*/
	
	public static boolean dropItem(@Nonnull EntityPlayer player, @Nonnull ItemStack item) {
		for(IModifier mod:TinkerUtil.getModifiers(item)) {
			//if(mod.equals(Modifiers.bound)) {
			if(mod.equals(Modifiers.bound)) {
				//System.out.println("cancel");
				return false;
			}
			else if(Loader.isModLoaded("conarm")) {
				if(mod.equals(ModifiersArmor.bound)) {
					//System.out.println("cancel");
					return false;
				}
			}
		}
		//System.out.println("dont cancel");
		return true;
	}
	
	public static void dropItem(Slot slot, EntityPlayer player, int dragType) {
		for(IModifier mod:TinkerUtil.getModifiers(slot.getStack())) {
			if(mod.equals(Modifiers.bound)) {
				//System.out.println("cancel");
				return;
			}
			else if(Loader.isModLoaded("conarm")) {
				if(mod.equals(ModifiersArmor.bound)) {
					//System.out.println("cancel");
					return;
				}
			}
		}
		//System.out.println("dont cancel");
		ItemStack itemstack = slot.decrStackSize(dragType == 0 ? 1 : slot.getStack().getCount());
        slot.onTake(player, itemstack);
        player.dropItem(itemstack, true);
	}
	
}