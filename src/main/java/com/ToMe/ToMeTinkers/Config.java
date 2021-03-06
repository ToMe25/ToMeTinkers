package com.ToMe.ToMeTinkers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;

public class Config extends Configuration {
	
	public static final String CATEGORY_MATERIALS = "materials";
	public static final String CATEGORY_MODIFIERS = "modifiers";
	public static Map<String, Boolean> materials = new HashMap<String, Boolean>();
	public static Map<String, Boolean> modifiers = new HashMap<String, Boolean>();
	public static boolean meltGems = false;
	public static boolean debug = false;
	public static boolean cosmicBedrockMiner = true;
	public static boolean supermassiveResistant = true;
	public static boolean neutroniumNuggetOreDict = true;
	public static boolean gemDilithiumOreDict = true;
	public static boolean enchGoldenAppleRecipe = true;
	public static boolean avaritiaArrowShafts = false;
	public static boolean cosmicFullArmorFlight = true;
	public static boolean shaftCastRecipe = true;
	public static boolean boundTweak = true;
	
	public Config(File cfg) {
		super(cfg);
		try {
			load();
			readConfig();
		} finally {
			if(hasChanged()) {
				save();
			}
		}
	}
	
	public void readConfig() {
		addCustomCategoryComment(CATEGORY_GENERAL, "The General Configuration for this Mod.");
		addCustomCategoryComment(CATEGORY_MATERIALS, "The Materials Configuration for this Mod.(used to Enable / Disable Materials)");
		addCustomCategoryComment(CATEGORY_MODIFIERS, "The Modifiers Configuration for this Mod.(used to Enable / Disable Modifiers)");
		meltGems = getBoolean("meltGems", CATEGORY_GENERAL, meltGems, "Whether gems should be used in the Smeltery / Casting Table. (if false gems will be used in the Part Builder)");
		debug = getBoolean("debug", CATEGORY_GENERAL, debug, "Enables / Disables some debug output.");
		cosmicBedrockMiner = getBoolean("cosmicBedrockMiner", CATEGORY_GENERAL, cosmicBedrockMiner, "Should tools with the Cosmic Trait be able to mine Bedrock?");
		supermassiveResistant = getBoolean("supermassiveResistant", CATEGORY_GENERAL, supermassiveResistant, "Should supermassive(Neutronium Trait) work as Resistant 3?");
		neutroniumNuggetOreDict = getBoolean("neutroniumNuggetOreDict", CATEGORY_GENERAL, neutroniumNuggetOreDict, "Registers Avaritias Neutronium Nugget as OreDictionary \"nuggetCosmicNeutronium\".");
		gemDilithiumOreDict = getBoolean("gemDilithiumOreDict", CATEGORY_GENERAL, gemDilithiumOreDict, "Registers Advanced Rocketrys Dilithium Crystal as OreDictionary \"gemDilithium\".");
		enchGoldenAppleRecipe = getBoolean("enchantedGoldenAppleRecipe", CATEGORY_GENERAL, enchGoldenAppleRecipe, "Should ToMeTinkers re-add the old Enchanted Golden Apple Recipe?");
		avaritiaArrowShafts = getBoolean("avaritiaArrowShafts", CATEGORY_GENERAL, avaritiaArrowShafts, "Should Players be able to make Arrowshafts out of Avaritia Materials?");
		cosmicFullArmorFlight = getBoolean("cosmicFullArmorFlight", CATEGORY_GENERAL, cosmicFullArmorFlight, "Should Players with a full Armor with Infinity parts be able to fly?");
		shaftCastRecipe = getBoolean("shaftCastRecipe", CATEGORY_GENERAL, shaftCastRecipe, "Should Players be able to craft Arrowshaft Casts?(they can't be casted because that creates gold bolt cores, and i cant change that.)");
		boundTweak = getBoolean("boundTweak", CATEGORY_GENERAL, boundTweak, "Tweak the EntityPlayer class to make the bound modifier more reliable");
	}
	
	//public static void addMaterial(String name) {
		//materials.put(name, true);
	//}
	
	public boolean getMaterialEnable(String mat) {
		if(!materials.containsKey(mat)) {
			load();
			materials.put(mat, true);
			materials.put(mat, getBoolean(mat, CATEGORY_MATERIALS, materials.get(mat), "Enables / Disables the Material " + mat + "."));
			if(hasChanged()) {
				save();
			}
		}
		return materials.get(mat);
	}
	
	public boolean getModifierEnable(String mod) {
		if(!modifiers.containsKey(mod)) {
			load();
			modifiers.put(mod, true);
			modifiers.put(mod, getBoolean(mod, CATEGORY_MODIFIERS, modifiers.get(mod), "Enables / Disables the Material " + mod + "."));
			if(hasChanged()) {
				save();
			}
		}
		return modifiers.get(mod);
	}
	
}