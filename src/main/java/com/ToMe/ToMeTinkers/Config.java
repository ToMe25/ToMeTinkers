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
	public static boolean enchGoldenAppleRecipe = true;
	
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
		enchGoldenAppleRecipe = getBoolean("enchantedGoldenAppleRecipe", CATEGORY_GENERAL, enchGoldenAppleRecipe, "Should ToMeTinkers re-add the old Enchanted Golden Apple Recipe?");
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