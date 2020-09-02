package com.ToMe.ToMeTinkers;

import com.ToMe.ToMeTinkers.traits.TraitCosmic;
import com.ToMe.ToMeTinkers.traits.TraitCrystallized;
import com.ToMe.ToMeTinkers.traits.TraitHealthy;
import com.ToMe.ToMeTinkers.traits.TraitMagical;
import com.ToMe.ToMeTinkers.traits.TraitMagicallyProtected;
import com.ToMe.ToMeTinkers.traits.TraitOptimized;
import com.ToMe.ToMeTinkers.traits.TraitReinforced;
import com.ToMe.ToMeTinkers.traits.TraitStrengthening;
import com.ToMe.ToMeTinkers.traits.TraitSupermassive;
import com.ToMe.ToMeTinkers.traits.TraitUnbreakable;

//import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.AbstractTrait;
//import slimeknights.tconstruct.tools.modifiers.ModReinforced;
//import slimeknights.tconstruct.tools.traits.TraitBonusDamage;

public class Traits {
	
	public static AbstractTrait magical = new TraitMagical();
	public static AbstractTrait optimized = new TraitOptimized();
	public static AbstractTrait healthy = new TraitHealthy();
	public static AbstractTrait magicallyprotected = new TraitMagicallyProtected();
	public static AbstractTrait strengthening = new TraitStrengthening();
	//public static AbstractTrait strengthening = new TraitBonusDamage("strengthening", 1.0f);
	public static AbstractTrait unbreakable = new TraitUnbreakable();
	//public static AbstractTrait unbreakable = new ModReinforced();
	//public static AbstractTrait unbreakable = new TraitReinforced(10);
	public static AbstractTrait cosmic = new TraitCosmic();
	//public static AbstractTrait cosmic1 = new TraitCosmic(1);
	//public static AbstractTrait cosmic2 = new TraitCosmic(2);
	//public static AbstractTrait cosmic3 = new TraitCosmic(3);
	//public static AbstractTrait cosmic4 = new TraitCosmic(4);
	public static AbstractTrait supermassive = new TraitSupermassive();
	public static AbstractTrait crystallized = new TraitCrystallized();
	public static AbstractTrait reinforced2 = new TraitReinforced(2);
	public static AbstractTrait reinforced3 = new TraitReinforced(3);
	
}