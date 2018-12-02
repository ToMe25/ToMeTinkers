package com.ToMe.ToMeTinkers;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
//import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
//import slimeknights.mantle.util.RecipeMatch;
//import slimeknights.tconstruct.TConstruct;
//import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
//import slimeknights.tconstruct.library.materials.BowMaterialStats;
//import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
//import slimeknights.tconstruct.library.materials.HandleMaterialStats;
//import slimeknights.tconstruct.library.materials.HeadMaterialStats;
//import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
//import slimeknights.tconstruct.library.materials.MaterialTypes;
//import slimeknights.tconstruct.library.materials.ProjectileMaterialStats;
//import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
//import slimeknights.tconstruct.shared.TinkerFluids;
//import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockMolten;
//import slimeknights.tconstruct.tools.TinkerMaterials;
//import slimeknights.tconstruct.tools.TinkerTraits;
//import slimeknights.tconstruct.tools.traits.TraitStiff;

public class Materials {
	
	//public static Material glass;
	//public static MaterialIntegration glassIntegration;
	//public static Fluid glassFluid;
	//public static IMaterialStats glassHead;
	//public static IMaterialStats glassHandle;
	//public static IMaterialStats glassExtra;
	//public static IMaterialStats glassProjectile;//USELESS
	//public static IMaterialStats glassBow;
	
	//private static void initMaterials() {
		//String name = "glass";
		//glassFluid = TinkerFluids.glass;
		//glass.setFluid(glassFluid);
		//if(glass != null) {
			//glass.setFluid(glassFluid);
			//glassIntegration.fluid = glassFluid;
			//ToMeTinkers.logger.info("" + glassFluid);
		//}
		//if(TinkerRegistry.getMaterial(name) == Material.UNKNOWN) {
		//if(TinkerRegistry.getMaterial(name) == Material.UNKNOWN && TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
		//if(TinkerRegistry.getMaterial(name) == Material.UNKNOWN && isSmelteryEnabled()) {
			//glassFluid = FluidRegistry.getFluid("glass");
			//glassFluid = TinkerFluids.glass;
			//glassFluid = new FluidMolten("glass", 0x80c0f5fa);
			//glassFluid = new FluidMolten("glasstmt", 0x80c0f5fa);
			//FluidRegistry.registerFluid(glassFluid);
			//glass = new Material("glass", 123);
			//glass = new Material("glass", 0x80ffffff);
			//glass = new Material("glass", 0x80c0f5fa);
			//glass.addItem(Blocks.GLASS, 3);
			//glass.addItem("blockGlass", 1, 3);
			//glass.addItem("blockGlass", 1, Material.VALUE_Block);
			//glass.addItem(Items.GLASS_BOTTLE, 1, 3);
			//glass.addItem(Items.GLASS_BOTTLE, 1, Material.VALUE_Block);
			//ToMeTinkers.logger.info("" + FluidRegistry.getFluid("tconstruct:molten_glass"));
			//ToMeTinkers.logger.info("" + FluidRegistry.getFluid("tconstruct:glass"));
			//ToMeTinkers.logger.info("" + FluidRegistry.getFluid("glass"));
			//ToMeTinkers.logger.info(glassFluid.toString());
			//ToMeTinkers.logger.info("" + glassFluid);
			//glass.setFluid(FluidRegistry.getFluid("tconstruct:molten_glass"));
			//glass.setFluid(FluidRegistry.getFluid("tconstruct:glass"));
			//glass.setFluid(FluidRegistry.getFluid("glass"));
			//glass.setFluid(glassFluid);
			//glass.setRepresentativeItem(new ItemStack(Blocks.GLASS));
			//glass.setCastable(true);
			//glass.setCraftable(true);
			//glass.addTrait(TinkerTraits.freezing);
			//glass.addTrait(TinkerTraits.fractured);
			//glass.addTrait(TinkerTraits.splintering, MaterialTypes.HEAD);
			//glassIntegration = new MaterialIntegration(glass, FluidRegistry.getFluid("tconstruct:molten_glass"), "Glass");
			//glassIntegration = new MaterialIntegration(glass, FluidRegistry.getFluid("tconstruct:glass"), "Glass");
			//glassIntegration = new MaterialIntegration(glass, FluidRegistry.getFluid("glass"), "Glass");
			//glassIntegration = new MaterialIntegration(glass, glassFluid, "Glass");
			//glassIntegration = new MaterialIntegration(glass, glassFluid);
			//ToMeTinkers.logger.info(glassIntegration.fluid.toString());
			//glassIntegration.setRepresentativeItem("glass");
			//glassIntegration.toolforge();
			//glass.setCastable(true);
			//ToMeTinkers.logger.info("Finished Glass Initializing!");
		//}
	//}
	
	//private static void initStats() {
		//if(glass != null) {
			//glassHead = new HeadMaterialStats(10, 3, 4, 0);
			//glassHandle = new HandleMaterialStats(1.2f, 3);
			//glassExtra = new ExtraMaterialStats(3);
			//glassProjectile = new ProjectileMaterialStats();
			//glassBow = new BowMaterialStats(0.2f, 0.4f, -1);
		//}
	//}
	
	//public static void preInit() {
		//initMaterials();
		//initStats();
		//if(isSmelteryEnabled()) {
			//TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, glassFluid, 1000);
			//TinkerRegistry.addMaterial(glass);
			//TinkerRegistry.addMaterialStats(glass, glassHead);
			//TinkerRegistry.addMaterialStats(glass, glassHandle);
			//TinkerRegistry.addMaterialStats(glass, glassExtra);
			//TinkerRegistry.addMaterialStats(glass, glassHead, glassHandle, glassExtra, glassBow);
			//TinkerRegistry.integrate(glassIntegration);
			//glassIntegration.preInit();
		//}
	//}
	
	public static void registerMaterials() {
		//initMaterials();
		//initStats();
		//TinkerRegistry.addMaterial(glass);
		//TinkerRegistry.addMaterialStats(glass, glassHead);
		//TinkerRegistry.addMaterialStats(glass, glassHandle);
		//TinkerRegistry.addMaterialStats(glass, glassExtra);
		//glass.setCastable(true);
		//if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
		//if(isSmelteryEnabled()) {
		//if(isSmelteryEnabled() && glass != null) {
			//TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(Items.GLASS_BOTTLE, 1000), FluidRegistry.getFluid("glass")));
			//TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, FluidRegistry.getFluid("glass"), 1000);
			//TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, glassFluid, 1000);
			//TinkerRegistry.addMaterialStats(glass, glassHead, glassHandle, glassExtra);
			//TinkerRegistry.addMaterialStats(glass, glassHead, glassHandle, glassExtra, glassBow);
			//TinkerRegistry.addMaterialTrait(glass, TinkerTraits.splintering, "???");
			//TinkerRegistry.addMaterialTrait(glass, TinkerTraits.splinters, "???");
			//TinkerRegistry.addMaterial(glass);
			//TinkerRegistry.integrate(glassIntegration);
			//glassIntegration.preInit();
			//glassIntegration.integrate();
			//ToMeTinkers.logger.info("" + glassIntegration.isIntegrated());
			//TinkerSmeltery.registerToolpartMeltingCasting(glass);
			//TinkerRegistry.addMaterialStats(glass, glassHead);
			//TinkerRegistry.addMaterialStats(glass, glassHandle);
			//TinkerRegistry.addMaterialStats(glass, glassExtra);
			//TinkerRegistry.integrate(glassIntegration);
			//glass.setCastable(true);
		//}
		for(MaterialStats stats:MaterialStats.values()) {
			//if(TinkerRegistry.getMaterial(stats.id) == Material.UNKNOWN) {
			//if(TinkerRegistry.getMaterial(stats.id) == Material.UNKNOWN && ToMeTinkers.cfg.getMaterialEnable(stats.id)) {
				//stats.addItems();
				//stats.addTraits();
				//stats.register();
			//}
			//if(Loader.isModLoaded(stats.modDependency())) {
			if(Loader.isModLoaded(stats.modDependency()) && stats.id != null && !stats.id.isEmpty()) {
				if(TinkerRegistry.getMaterial(stats.id) == Material.UNKNOWN && ToMeTinkers.cfg.getMaterialEnable(stats.id)) {
					stats.addItems();
					stats.addTraits();
					stats.register();
				}
			}
		}
	}
	
	//private static boolean isSmelteryEnabled() {
		//return TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId);
	//}
	
	//public static void registerFluids() {
	public static void registerFluids(RegistryEvent.Register<Block> e) {
		final IForgeRegistry<Block> registry = e.getRegistry();
		for(FluidMolten fm:MaterialStats.fluids) {
			FluidRegistry.registerFluid(fm);
			//registry.register(new BlockMolten(fm).setUnlocalizedName(ToMeTinkers.MODID + ":molten_" + fm.getName()).setRegistryName(ToMeTinkers.MODID + ":molten_" + fm.getName()/*new ResourceLocation(ToMeTinkers.MODID, fm.getName())*/));
			//registry.register(new BlockMolten(fm).setUnlocalizedName(ToMeTinkers.MODID + ".molten_" + fm.getName()).setRegistryName(ToMeTinkers.MODID + ".molten_" + fm.getName()));
			//registry.register(new BlockMolten(fm).setUnlocalizedName(ToMeTinkers.MODID + ".molten_" + fm.getName()).setRegistryName("molten_" + fm.getName()));
			registry.register(new BlockMolten(fm).setUnlocalizedName(ToMeTinkers.MODID + ".molten_" + fm.getName()).setRegistryName(new ResourceLocation(ToMeTinkers.MODID, "molten_" + fm.getName())));
			//registry.register(new BlockMolten(fm).setUnlocalizedName(ToMeTinkers.MODID + ".molten_" + fm.getName()).setRegistryName(new ResourceLocation("tconstruct", "molten_" + fm.getName())));
			if(Config.debug) {
				ToMeTinkers.logger.info("Registered Fluid " + fm.getName());
			}
		}
	}
	
	public static void postInit() {
		for(MaterialStats stats:MaterialStats.values()) {
			stats.postInit();
		}
	}
	
}