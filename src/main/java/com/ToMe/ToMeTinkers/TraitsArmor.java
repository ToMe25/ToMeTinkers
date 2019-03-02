package com.ToMe.ToMeTinkers;

import com.ToMe.ToMeTinkers.traits.TraitCosmicArmor;
import com.ToMe.ToMeTinkers.traits.TraitCrystallizedArmor;
import com.ToMe.ToMeTinkers.traits.TraitOptimizedArmor;
import com.ToMe.ToMeTinkers.traits.TraitStrengtheningArmor;
import com.ToMe.ToMeTinkers.traits.TraitSupermassiveArmor;
import com.ToMe.ToMeTinkers.traits.TraitWitheringArmor;
import com.ToMe.ToMeTinkers.traits.TraitXPBoostArmor;

import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitsArmor {
	
	public static AbstractTrait strengthening = new TraitStrengtheningArmor();
	public static AbstractTrait xpboost = new TraitXPBoostArmor();
	public static AbstractTrait withering = new TraitWitheringArmor();
	public static AbstractTrait optimized = new TraitOptimizedArmor();
	public static AbstractTrait cosmic = new TraitCosmicArmor();
	public static AbstractTrait supermassive = new TraitSupermassiveArmor();
	public static AbstractTrait crystallized = new TraitCrystallizedArmor();
	
}