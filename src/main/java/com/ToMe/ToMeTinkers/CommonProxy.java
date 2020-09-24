package com.ToMe.ToMeTinkers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
//import slimeknights.mantle.client.book.BookHelper;
//import slimeknights.tconstruct.library.events.TinkerRegisterEvent.MeltingRegisterEvent;
import slimeknights.tconstruct.library.materials.Material;

public class CommonProxy {
	
	//public void setRenderInfo(Material material, int low, int mid, int high) {
	//public void setRenderInfo(Material material, Block block) {
	public void setRenderInfo(Material material, String resourcelocation) {
		
	}
	
	public void registerBookPages() {
		
	}
	
	public void registerFluidModels() {
		
	}
	
	public void registerItems(RegistryEvent.Register<Item> e) {
		Materials.registerMaterials();
		Modifiers.registerModifiers();
		//ModifiersArmor.registerModifiers();
		if(Loader.isModLoaded("conarm")) {
			ModifiersArmor.registerModifiers();
		}
		ToMeTinkers.proxy.registerFluidModels();
		if(Config.neutroniumNuggetOreDict) {
			Item neutroniumNugget = Item.REGISTRY.getObject(new ResourceLocation("avaritia", "resource"));
			if(neutroniumNugget != null) {
				OreDictionary.registerOre("nuggetCosmicNeutronium", new ItemStack(neutroniumNugget, 1, 3));
			}
			//OreDictionary.registerOre("nuggetCosmicNeutronium", Item.REGISTRY.getObject(new ResourceLocation("avaritia", "resource")));
		}
		if(Config.gemDilithiumOreDict) {
			Item dilithiumCrystal = Item.REGISTRY.getObject(new ResourceLocation("libvulpes", "productcrystal"));
			if(dilithiumCrystal != null) {
				OreDictionary.registerOre("gemDilithium", new ItemStack(dilithiumCrystal, 1, 0));
			}
		}
		//BookHelper.writeSavedPage(new ItemStack(neutroniumNugget, 1, 3), "Test");
		ToMeTinkers.proxy.registerBookPages();
	}
	
	//public void registerModifiers(RegistryEvent.Register<Item> e) {
		//Modifiers.registerModifiers();
	//}
	
	public /*static*/ void registerBlocks(RegistryEvent.Register<Block> e) {
		Materials.registerFluids(e);
	}
	
	/*public static void onMeltingRegister(MeltingRegisterEvent e) {
		//MaterialStats.onMeltingRegister(e);
	}*/
	
	public void preInit(FMLPreInitializationEvent e) {
		if(ToMeTinkers.cfg == null) {
			ToMeTinkers.cfg = new Config(e.getSuggestedConfigurationFile());
		}
		//ToMeTinkers.cfg = new Config(e.getSuggestedConfigurationFile());
		//Materials.preInit();
		//ToMeTinkers.proxy.registerFluidModels();
		//ToMeTinkers.proxy.registerBookPages();
	}
	
	public void Init(FMLInitializationEvent e) {
		//ToMeTinkers.proxy.registerFluidModels();
		registerFixes();
		//ToMeTinkers.proxy.registerBookPages();
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		MaterialStats.remove();
		Materials.postInit();
	}
	
	public void registerFixes() {
		ModFixs fixes = FMLCommonHandler.instance().getDataFixer().init(ToMeTinkers.MODID, 1);
		fixes.registerFix(FixTypes.ITEM_INSTANCE, new TraitFixer());
	}
	
}