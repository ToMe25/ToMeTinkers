package com.ToMe.ToMeTinkers;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class ConfigCondition implements IConditionFactory {
	
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		//if(json.get("option").toString()./*replaceAll("\"", "").*/equalsIgnoreCase("gapple")) {
		if(JsonUtils.getString(json, "option").equalsIgnoreCase("gapple")) {
			return () -> Config.enchGoldenAppleRecipe;
		}
		else {
			ToMeTinkers.logger.warn("ConfigCondition: " + "Couldn't find Condition " + JsonUtils.getString(json, "option") + ".");
			return () -> false;
		}
	}
	
}