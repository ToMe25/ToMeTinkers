package com.ToMe.ToMeTinkers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.TinkerIntegration;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent.MeltingRegisterEvent;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.ArrowShaftMaterialStats;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.BowStringMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.FletchingMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.materials.ProjectileMaterialStats;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.library.smeltery.ICastingRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.library.smeltery.PreferenceCastingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTraits;

public enum MaterialStats {
	
	Glass("glass", new HeadMaterialStats(10, 3.0f, 4.0f, 0), new HandleMaterialStats(1.2f, 3), new ExtraMaterialStats(3), null, null, null, null, true, 0x80c0f5fa, new ItemStack(Blocks.GLASS), TinkerFluids.glass, /*null*/"Glass", /*null*/"blockGlass") {
		
		@Override
		protected void addItems() {
			//mat.addItem("blockGlass", 1, Material.VALUE_Block);
			mat.addItem("blockGlass", 1, 1000);
			//mat.addItem(Items.GLASS_BOTTLE, 1, Material.VALUE_Block);
			mat.addItem(Items.GLASS_BOTTLE, 1, 1000);
			//mat.addItem("paneGlass", 1, (Material.VALUE_Block * 6) / 16);
			mat.addItem("paneGlass", 1, (1000 * 6) / 16);
			super.addItems();
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(TinkerTraits.fractured);
			//mat.addTrait(TinkerTraits.splintering);
			//mat.addTrait(TinkerTraits.fractured, MaterialTypes.HEAD);
			mat.addTrait(TinkerTraits.splintering, MaterialTypes.HEAD);
			mat.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
			super.addTraits();
		}
		
		@Override
		protected void register() {
			//TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, fluid, 1000);
			if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
				TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, fluid, 1000);
			}
			//removeMelting(TinkerFluids.glass, 1296);
			//removeBasinCasting(new ItemStack(Blocks.GLASS), TinkerFluids.glass);
			removeMelting(fluid, 1296);
			removeBasinCasting(new ItemStack(Blocks.GLASS), fluid);
			super.register();
			//try {
				//remove(TinkerFluids.glass, 1296);
			//} catch (Exception e) {
				// TODO: handle exception
				//ToMeTinkers.logger.catching(e);
			//}
			//MaterialStats.Glass.remove();
			//removeMelting(TinkerFluids.glass, 1296);
			//removeBasinCasting(new ItemStack(Blocks.GLASS), TinkerFluids.glass);
		}
		
	},
	Gold("gold", new HeadMaterialStats(160, 10.0f, 3.5f, 1), new HandleMaterialStats(0.75f, -30), new ExtraMaterialStats(50), new BowMaterialStats(0.75f, 1.1f, 5.5f), null, null, null, true, 0xf6d609, new ItemStack(Items.GOLD_INGOT), TinkerFluids.gold, "Gold", null) {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("Gold");
			mat.addItem("oreGold", 1, Material.VALUE_Ore());
			super.addItems();
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(Traits.magical);
			super.addTraits();
		}
		
	},
	Topaz("topaz", new HeadMaterialStats(275, 6.5f, 6.5f, 2), new HandleMaterialStats(1.0f, 75), new ExtraMaterialStats(55), null, null, null, null, Config.meltGems, 0xffce43, null, /*null*/ Config.meltGems ? createFluid("topaz", 0xffce43, 999) : null, "Topaz", "gemTopaz") {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("Topaz");
			mat.addItem("oreTopaz", 1, Material.VALUE_Ore());
			mat.addItem("gemTopaz", 1, Material.VALUE_Gem);
			matInt.setRepresentativeItem("gemTopaz");
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					TinkerRegistry.registerTableCasting(new PreferenceCastingRecipe("gemTopaz", RecipeMatch.ofNBT(TinkerSmeltery.castGem), fluid, Material.VALUE_Ingot));//Just because this will be also used as default for all Gems by Tinkers Construct.
				}
			}
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(TinkerTraits.established);
			super.addTraits();
		}
		
		@Override
		protected void postInit() {
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					for(FluidStack fs :TinkerSmeltery.castCreationFluids) {
						TinkerRegistry.registerTableCasting(new CastingRecipe(TinkerSmeltery.castGem, RecipeMatch.of("gemTopaz"), fs, true, true));
					}
				}
			}
			super.postInit();
		}
		
	},
	Ruby("ruby", new HeadMaterialStats(350, 6.5f, 7.5f, 2), new HandleMaterialStats(1.1f, -55), new ExtraMaterialStats(80), null, null, null, null, Config.meltGems, 0xca0000, null, /*null*/ Config.meltGems ? createFluid("ruby", 0xcf0000, 999) : null, "Ruby", "gemRuby") {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("Ruby");
			mat.addItem("oreRuby", 1, Material.VALUE_Ore());
			mat.addItem("gemRuby", 1, Material.VALUE_Gem);
			matInt.setRepresentativeItem("gemRuby");
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					TinkerRegistry.registerTableCasting(new PreferenceCastingRecipe("gemRuby", RecipeMatch.ofNBT(TinkerSmeltery.castGem), fluid, Material.VALUE_Ingot));//Just because this will be also used as default for all Gems by Tinkers Construct.
				}
			}
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(Traits.healthy);
			//mat.addTrait(Traits.healthy, MaterialTypes.HEAD);
			super.addTraits();
		}
		
		@Override
		protected void postInit() {
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					for(FluidStack fs :TinkerSmeltery.castCreationFluids) {
						TinkerRegistry.registerTableCasting(new CastingRecipe(TinkerSmeltery.castGem, RecipeMatch.of("gemRuby"), fs, true, true));
					}
				}
			}
			super.postInit();
		}
		
	},
	Diamond("diamond", new HeadMaterialStats(500, 9.0f, 5.0f, 3), new HandleMaterialStats(1.2f, 80), new ExtraMaterialStats(100), null, null, null, null, Config.meltGems, 0x00ffcc, new ItemStack(Items.DIAMOND), /*null*/ Config.meltGems ? createFluid("diamond", 0x00ffdc, 999) : null, "Diamond", /*null*/"gemDiamond") {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("Diamond");
			mat.addItem("oreDiamond", 1, Material.VALUE_Ore());
			mat.addItem("gemDiamond", 1, Material.VALUE_Gem);
			super.addItems();
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					TinkerRegistry.registerTableCasting(new PreferenceCastingRecipe("gemDiamond", RecipeMatch.ofNBT(TinkerSmeltery.castGem), fluid, Material.VALUE_Ingot));//Just because this will be also used as default for all Gems by Tinkers Construct.
				}
			}
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(Traits.strengthening);
			mat.addTrait(Traits.optimized, MaterialTypes.HEAD);
			super.addTraits();
		}
		
		@Override
		protected void postInit() {
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					for(FluidStack fs :TinkerSmeltery.castCreationFluids) {
						TinkerRegistry.registerTableCasting(new CastingRecipe(TinkerSmeltery.castGem, RecipeMatch.of("gemDiamond"), fs, true, true));
					}
				}
			}
			super.postInit();
		}
		
	},
	Sapphire("sapphire", new HeadMaterialStats(400, 6.0f, 7.75f, 2), new HandleMaterialStats(1.12f, -55), new ExtraMaterialStats(85), null, null, null, null, Config.meltGems, 0x2b4fe7, null, /*null*/ Config.meltGems ? createFluid("sapphire", 0x2b4fe7, 999) : null, "Sapphire", "gemSapphire") {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("Sapphire");
			mat.addItem("oreSapphire", 1, Material.VALUE_Ore());
			mat.addItem("gemSapphire", 1, Material.VALUE_Gem);
			matInt.setRepresentativeItem("gemSapphire");
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					//TinkerRegistry.registerBasinCasting(new PreferenceCastingRecipe("blockSapphire", null, fluid, Material.VALUE_Block));
					TinkerRegistry.registerTableCasting(new PreferenceCastingRecipe("gemSapphire", RecipeMatch.ofNBT(TinkerSmeltery.castGem), fluid, Material.VALUE_Ingot));//Just because this will be also used as default for all Gems by Tinkers Construct.
					//for(FluidStack fs :TinkerSmeltery.castCreationFluids) {
						//TinkerRegistry.registerTableCasting(new CastingRecipe(TinkerSmeltery.castGem, RecipeMatch.of("gemSapphire"), fs, true, true));
					//}
				}
			}
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(Traits.magicallyprotected);
			super.addTraits();
		}
		
		@Override
		protected void postInit() {
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					for(FluidStack fs :TinkerSmeltery.castCreationFluids) {
						TinkerRegistry.registerTableCasting(new CastingRecipe(TinkerSmeltery.castGem, RecipeMatch.of("gemSapphire"), fs, true, true));
					}
				}
			}
			super.postInit();
		}
		
	},
	Emerald("emerald", new HeadMaterialStats(450, 9.5f, 5.1f, 3), new HandleMaterialStats(1.2f, 85), new ExtraMaterialStats(95), null, null, null, null, Config.meltGems, 0x49e07e, new ItemStack(Items.EMERALD), Config.meltGems ? TinkerFluids.emerald : null, "Emerald", /*null*/"gemEmerald") {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("Emerald");
			mat.addItem("oreEmerald", 1, Material.VALUE_Ore());
			mat.addItem("gemEmerald", 1, Material.VALUE_Gem);
			super.addItems();
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(TinkerTraits.established);
			super.addTraits();
		}
		
		@Override
		protected void register() {
			//removeMelting(TinkerFluids.emerald, 666);
			//removeMelting(TinkerFluids.emerald, 1332);
			//removeMelting(TinkerFluids.emerald, 5994);
			if(Config.meltGems) {
				//TinkerRegistry.registerMelting(Items.EMERALD, fluid, 144);
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					//TinkerRegistry.registerMelting(Items.EMERALD, fluid, 144);
					TinkerRegistry.registerMelting("gemEmerald", fluid, 144);
				}
				//removeMelting(TinkerFluids.emerald, 666);
				//removeMelting(TinkerFluids.emerald, 1332);
				//removeMelting(TinkerFluids.emerald, 5994);
				//removeBasinCasting(new ItemStack(Blocks.EMERALD_BLOCK), TinkerFluids.emerald);
				//removeTableCasting(new ItemStack(Items.EMERALD), TinkerFluids.emerald);
				removeMelting(fluid, 666);
				//removeMelting(fluid, 576);
				removeMelting(fluid, 1332);
				removeMelting(fluid, 5994);
				removeBasinCasting(new ItemStack(Blocks.EMERALD_BLOCK), fluid);
				removeTableCasting(new ItemStack(Items.EMERALD), fluid);
			}
			super.register();
		}
		
		@Override
		protected void postInit() {
			if(Config.meltGems) {
				if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
					//TinkerSmeltery.registerOredictMeltingCasting(fluid, "Emerald");
					TinkerRegistry.registerBasinCasting(new PreferenceCastingRecipe("blockEmerald", null, fluid, Material.VALUE_Block));
					TinkerRegistry.registerTableCasting(new PreferenceCastingRecipe("gemEmerald", RecipeMatch.ofNBT(TinkerSmeltery.castGem), fluid, Material.VALUE_Ingot));//Just because this will be also used as default for all Gems by Tinkers Construct.
					//TinkerRegistry.registerTableCasting(new PreferenceCastingRecipe("gemEmerald", RecipeMatch.ofNBT(TinkerSmeltery.castGem), fluid, Material.VALUE_Gem));
					//for(FluidStack fs :TinkerSmeltery.castCreationFluids) {
						//TinkerRegistry.registerTableCasting(new CastingRecipe(TinkerSmeltery.castGem, RecipeMatch.of("gemEmerald"), fs, true, true));
					//}
				}
			}
			super.postInit();
		}
		
	},
	MagicalWood(TinkerRegistry.getMaterial("xu_magical_wood"), null, null, null, null, null, new ArrowShaftMaterialStats(1.1f, 5), null, false, null, null, null, null) {//Adds Arrow Shaft Stats to Magical Wood.
		
		@Override
		protected String modDependency() {
			return "extrautils2";
		}
		
	},
	EnchantedMetal(TinkerRegistry.getMaterial("xu_enchanted_metal"), null, null, null, null, null, null, null, false, null, null, null, null) {//Adds dummy Bow Stats to Enchanted Iron because every Vanilla Tinkers Construct Material has Bow Stats.
		
		@Override
		protected String modDependency() {
			return "extrautils2";
		}
		
	},
	EvilInfusedIron(TinkerRegistry.getMaterial("xu_evil_metal"), null, null, null, null, null, null, null, false, null, null, null, null) {//Adds dummy Bow Stats to Evil Infused Iron because every Vanilla Tinkers Construct Material has Bow Stats.
		
		@Override
		protected String modDependency() {
			return "extrautils2";
		}
		
	},
	AvaritiaInfinity("avaritia_infinitymetal", new HeadMaterialStats(8000, 6000.0f, /*50.0f*//*100.0f*//*150.0f*/175.0f, 5), new HandleMaterialStats(10.0f, 8000), new ExtraMaterialStats(8000), new BowMaterialStats(1.25f/*10.0f*/, 60.0f, 60.0f), null, /*new ArrowShaftMaterialStats(1.0f, 1000)*/null, null, true, /*0xffffff*/ 0xff0000, null /*new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("avaritia:resource")*//*new ResourceLocation("avaritia", "resource")*//*), 6)*/, createFluid("infinity", 0xffffff, 1024), "infinity", "ingotInfinity") {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("Infinity");
			//super.addItems();
			matInt.setRepresentativeItem("ingotInfinity");
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(Traits.cosmic);
			mat.addTrait(Traits.unbreakable);
			super.addTraits();
		}
		
		@Override
		protected String modDependency() {
			return "avaritia";
		}
		
		@Override
		protected void register() {
			//ToMeTinkers.proxy.setRenderInfo(mat, 0x82ff96, 0x7c92ff, 0xea84ff);
			ToMeTinkers.proxy.setRenderInfo(mat, "avaritia:blocks/resource/infinity");
			//ToMeTinkers.proxy.setRenderInfo(mat, "avaritia:blocks/textures/resource/infinity");
			//ToMeTinkers.proxy.setRenderInfo(mat, "avaritia:textures/blocks/resource/infinity");
			//TinkerRegistry.registerMelting("ingotInfinity", fluid, Material.VALUE_Ingot);
			//TinkerRegistry.registerMelting("blockInfinity", fluid, Material.VALUE_Block);
			//TinkerSmeltery.registerOredictMeltingCasting(fluid, "Infinity");
			if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
				TinkerSmeltery.registerOredictMeltingCasting(fluid, "Infinity");
			}
			super.register();
		}
		
	},
	AvaritiaNeutronium(/*new Material("avaritia_neutronium", TextFormatting.DARK_GRAY)*/"avaritia_neutronium", new HeadMaterialStats(1920, 900.0f, /*8.0f*/8.25f, 5), new HandleMaterialStats(1.0f, 1920), new ExtraMaterialStats(1920), new BowMaterialStats(/*109.0f*//*0.9f*//*0.35f*/0.325f/*0.3f*/, 10.0f, 10.0f), null, /*new ArrowShaftMaterialStats(1.1f, 1000)*/null, null, true, 0x303030, null, createFluid("neutronium", 0x303030, 1024), "neutronium", "ingotCosmicNeutronium") {
		
		@Override
		protected void addItems() {
			mat.addCommonItems("CosmicNeutronium");
			matInt.setRepresentativeItem("ingotCosmicNeutronium");
		}
		
		@Override
		protected void addTraits() {
			mat.addTrait(Traits.supermassive);
			super.addTraits();
		}
		
		@Override
		protected String modDependency() {
			return "avaritia";
		}
		
		@Override
		protected void register() {
			//ToMeTinkers.proxy.setRenderInfo(mat, "avaritia:blocks/resource/neutronium");
			if(TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
				TinkerSmeltery.registerOredictMeltingCasting(fluid, "CosmicNeutronium");
			}
			super.register();
		}
		
	};
	
	protected Material mat;
	protected MaterialIntegration matInt;
	protected Fluid fluid;
	protected boolean smelteable;
	protected String id;
	//protected String id = "error";
	protected ItemStack representativeItemstack;
	protected final BowMaterialStats whyNot = new BowMaterialStats(0.2f, 0.4f, -1);
	//protected Map<Fluid, Integer> remove = new HashMap<Fluid, Integer>();
	//protected static Map<Fluid, Integer> removeMelting = new HashMap<Fluid, Integer>();
	protected static Map<Fluid, List<Integer>> removeMelting = new HashMap<Fluid, List<Integer>>();
	protected static Map<ItemStack, Fluid> removeBasinCasting = new HashMap<ItemStack, Fluid>();
	protected static Map<ItemStack, Fluid> removeTableCasting = new HashMap<ItemStack, Fluid>();
	//protected static List<FluidMolten> fluids = new ArrayList<FluidMolten>();
	protected static List<FluidMolten> fluids;
	
	static {
		//fluids = new ArrayList<FluidMolten>();
		if(fluids == null) {//Needed because createFluid() sometimes being invoked first.
			fluids = new ArrayList<FluidMolten>();
		}
	}
	
	private MaterialStats(String identifier, HeadMaterialStats headStats, HandleMaterialStats handleStats, ExtraMaterialStats extraStats, @Nullable BowMaterialStats bowStats, @Nullable BowStringMaterialStats bowStringStats, @Nullable ArrowShaftMaterialStats arrowStats, @Nullable FletchingMaterialStats fletchingStats, boolean casting, int color, @Nullable ItemStack representativeItem, @Nullable Fluid fluid, @Nullable String oreSuffix, @Nullable String oreDictRequirement) {
		//this(new Material(identifier, color), headStats, handleStats, extraStats, bowStats, bowStringStats, arrowStats, fletchingStats, casting, representativeItem, fluid, oreSuffix, oreDictRequirement);
		this(new Material(identifier, color, true), headStats, handleStats, extraStats, bowStats, bowStringStats, arrowStats, fletchingStats, casting, representativeItem, fluid, oreSuffix, oreDictRequirement);
		//this.fluid = fluid;
		//id = identifier;
		//casting = TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId) ? casting : false;
		//smelteable = casting;
		//mat = new Material(identifier, color);
		//mat.setFluid(fluid);
		//mat.setCraftable(!casting);
		//mat.setCastable(casting);
		//if(oreSuffix == null || oreSuffix == "") {
			//matInt = new MaterialIntegration(mat, fluid);
		//}
		//else {
			//matInt = new MaterialIntegration(mat, fluid, oreSuffix);
		//}
		//representativeItemstack = representativeItem;
		//mat.setRepresentativeItem(representativeItem);
		//TinkerRegistry.addMaterialStats(mat, headStats, handleStats, extraStats, bowStats, bowStringStats, arrowStats, fletchingStats, new ProjectileMaterialStats());
		//if(headStats != null) {
			//TinkerRegistry.addMaterialStats(mat, headStats);
		//}
		//if(handleStats != null) {
			//TinkerRegistry.addMaterialStats(mat, handleStats);
		//}
		//if(extraStats != null) {
			//TinkerRegistry.addMaterialStats(mat, extraStats);
		//}
		//if(bowStats != null) {
			//TinkerRegistry.addMaterialStats(mat, bowStats);
		//}
		//TinkerRegistry.addMaterialStats(mat, headStats, handleStats, extraStats, bowStats == null ? whyNot : bowStats);
		//if(bowStringStats != null) {
			//TinkerRegistry.addMaterialStats(mat, bowStringStats);
		//}
		//if(arrowStats != null) {
			//TinkerRegistry.addMaterialStats(mat, arrowStats);
		//}
		//if(fletchingStats != null) {
			//TinkerRegistry.addMaterialStats(mat, fletchingStats);
		//}
	}
	
	//@SuppressWarnings("null")
	private MaterialStats(Material mat, HeadMaterialStats headStats, HandleMaterialStats handleStats, ExtraMaterialStats extraStats, @Nullable BowMaterialStats bowStats, @Nullable BowStringMaterialStats bowStringStats, @Nullable ArrowShaftMaterialStats arrowStats, @Nullable FletchingMaterialStats fletchingStats, boolean casting, @Nullable ItemStack representativeItem, @Nullable Fluid fluid, @Nullable String oreSuffix, @Nullable String oreDictRequirement) {
		//this.fluid = fluid;
		//id = mat.identifier;
		//casting = TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId) ? casting : false;
		//smelteable = casting;
		//this.mat = mat;
		//mat.setFluid(fluid);
		//mat.setCraftable(!casting);
		//mat.setCastable(casting);
		//if(oreSuffix == null || oreSuffix == "") {
			//matInt = new MaterialIntegration(mat, fluid);
		//}
		//else {
			//matInt = new MaterialIntegration(mat, fluid, oreSuffix);
		//}
		//representativeItemstack = representativeItem;
		//if(headStats != null) {
			//TinkerRegistry.addMaterialStats(mat, headStats, handleStats, extraStats, bowStats == null ? whyNot : bowStats);
		//}
		//if(bowStringStats != null) {
			//TinkerRegistry.addMaterialStats(mat, bowStringStats);
		//}
		//if(arrowStats != null) {
			//TinkerRegistry.addMaterialStats(mat, arrowStats);
		//}
		//if(fletchingStats != null) {
			//TinkerRegistry.addMaterialStats(mat, fletchingStats);
		//}
		if(Loader.isModLoaded(modDependency())) {
			//if(mat != Material.UNKNOWN) {
			//if(TinkerRegistry.getMaterial(id) == Material.UNKNOWN && ToMeTinkers.cfg.getMaterialEnable(id)) {
			if(mat != null && mat != Material.UNKNOWN && ToMeTinkers.cfg.getMaterialEnable(mat.identifier)) {
			//if(mat != null && mat != Material.UNKNOWN && TinkerRegistry.getMaterial(mat.identifier) == Material.UNKNOWN && ToMeTinkers.cfg.getMaterialEnable(mat.identifier)) {
				this.fluid = fluid;
				id = mat.identifier;
				casting = TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId) ? casting : false;
				smelteable = casting;
				this.mat = mat;
				mat.setFluid(fluid);
				mat.setCraftable(!casting);
				mat.setCastable(casting);
				if(oreDictRequirement != null && !oreDictRequirement.isEmpty()) {
					matInt = new MaterialIntegration(mat, fluid, oreSuffix, oreDictRequirement);
					//matInt = new MaterialIntegration(mat, fluid, "Copper");
					//matInt = TinkerIntegration.integrate(TinkerMaterials.copper, TinkerFluids.copper, "Copper");
					//matInt = TinkerRegistry.integrate(new MaterialIntegration(TinkerMaterials.copper, TinkerFluids.copper, "Copper"));
					//matInt = TinkerRegistry.integrate(new MaterialIntegration(TinkerMaterials.copper, TinkerFluids.copper, "CopperA"));
					//Random r = new Random();
					//int i = r.nextInt();
					//Material copper = new Material(TinkerMaterials.copper.identifier + i, TinkerMaterials.copper.materialTextColor, TinkerMaterials.copper.isHidden());
					//Material copper = new Material(TinkerMaterials.copper.identifier, TinkerMaterials.copper.materialTextColor, TinkerMaterials.copper.isHidden());
					//Material copper = new Material(TinkerMaterials.copper.identifier + "tmt", TinkerMaterials.copper.materialTextColor, TinkerMaterials.copper.isHidden());
					//matInt = TinkerRegistry.integrate(new MaterialIntegration(copper, TinkerFluids.copper, "CopperA"));
					//matInt = TinkerRegistry.integrate(new MaterialIntegration(copper, TinkerFluids.copper, "Copper" + i));
					//matInt = TinkerRegistry.integrate(new MaterialIntegration(mat, fluid, oreSuffix));
					//matInt = new MaterialIntegration(copper, TinkerFluids.copper, "Copper" + i);
					//ToMeTinkers.logger.info("Material " + mat.identifier + " has a oreRequirement.");
					//ToMeTinkers.logger.info("Adding Material " + id + " with Fluid " + fluid + ", oreSuffix " + oreSuffix + " and oreRequirement " + oreDictRequirement + ".");
					//ToMeTinkers.logger.info("Ore Dictionary " + oreDictRequirement + (OreDictionary.getOres(oreDictRequirement, false).isEmpty() ? " is " : " isn't ") + "Empty!");
					//for(String ore:matInt.oreRequirement) {
						//ToMeTinkers.logger.info("oreRequirement contains " + ore + ".");
					//}
				}
				//if(oreSuffix != null && !oreSuffix.isEmpty()) {
				//else if(oreSuffix != null || !oreSuffix.isEmpty()) {
				else if(oreSuffix != null && !oreSuffix.isEmpty()) {
				//if(oreSuffix == null || oreSuffix == "") {
				//if(oreSuffix == null || oreSuffix.isEmpty()) {
					//matInt = new MaterialIntegration(mat, fluid);
					matInt = new MaterialIntegration(mat, fluid, oreSuffix);
					//ToMeTinkers.logger.info("Material " + mat.identifier + " has a oreSuffix.");
					//ToMeTinkers.logger.info("Adding Material " + id + " with Fluid " + fluid + " and oreSuffix " + oreSuffix + ".");
				}
				else {
					//matInt = new MaterialIntegration(mat, fluid, oreSuffix);
					matInt = new MaterialIntegration(mat, fluid);
					//ToMeTinkers.logger.info("Material " + mat.identifier + " has no oreSuffix.");
					//ToMeTinkers.logger.info("Adding Material " + id + " with Fluid " + fluid + ".");
				}
				representativeItemstack = representativeItem;
				//if(headStats != null) {
				if(headStats != null && handleStats != null && extraStats != null) {
					//TinkerRegistry.addMaterialStats(mat, headStats, handleStats, extraStats, bowStats == null ? whyNot : bowStats);
					TinkerRegistry.addMaterialStats(mat, headStats, handleStats, extraStats);
				}
				if(!mat.hasStats(MaterialTypes.BOW)) {
					TinkerRegistry.addMaterialStats(mat, bowStats == null ? whyNot : bowStats);
				}
				//TinkerRegistry.addMaterialStats(mat, new ProjectileMaterialStats());
				if(!mat.hasStats(MaterialTypes.PROJECTILE)) {
					TinkerRegistry.addMaterialStats(mat, new ProjectileMaterialStats());
				}
				//if(bowStringStats != null) {
				if(bowStringStats != null && !mat.hasStats(MaterialTypes.BOWSTRING)) {
					TinkerRegistry.addMaterialStats(mat, bowStringStats);
				}
				//if(arrowStats != null) {
				if(arrowStats != null && !mat.hasStats(MaterialTypes.SHAFT)) {
					TinkerRegistry.addMaterialStats(mat, arrowStats);
				}
				//if(fletchingStats != null) {
				if(fletchingStats != null && !mat.hasStats(MaterialTypes.FLETCHING)) {
					TinkerRegistry.addMaterialStats(mat, fletchingStats);
				}
			}
		}
	}
	
	protected void addItems() {
		//mat.setRepresentativeItem(representativeItemstack);
		if(representativeItemstack != null && ! representativeItemstack.isEmpty()) {
			mat.setRepresentativeItem(representativeItemstack);
		}
	}
	
	protected void addTraits() {
		
	}
	
	protected String modDependency() {
		return "tconstruct";
	}
	
	protected void register() {
		TinkerRegistry.integrate(matInt);
		matInt.preInit();
		if(Config.debug) {
			ToMeTinkers.logger.info("Registered Material " + id);
		}
	}
	
	/**
	 * Do things while postInit(after Recipe removal).
	 */
	protected void postInit() {
		
	}
	
	//protected void remove(Fluid output, int amount) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	protected void removeMelting(Fluid output, int amount) {
		//List<MeltingRecipe> remove = new ArrayList<MeltingRecipe>();
		//Field f = TinkerRegistry.class.getDeclaredField("meltingRegistry");
		//f.setAccessible(true);
		//List<MeltingRecipe> list = (List<MeltingRecipe>)f.get(null);
		//for(MeltingRecipe r:(List<MeltingRecipe>)f.get(null)) {
		//for(MeltingRecipe r:list) {
			//if(r.getResult().getFluid().equals(output) && r.getResult().amount == amount) {
				//remove.add(r);
			//}
		//}
		//for(MeltingRecipe r:remove) {
			//list.remove(r);
		//}
		//ToMeTinkers.logger.info("Removed all Smeltery Melting Recipes producing " + amount + "mb of " + output.getUnlocalizedName() + ".");
		//remove.put(output, amount);
		//removeMelting.put(output, amount);
		List<Integer> values = new ArrayList<Integer>();
		if(removeMelting.containsKey(output)) {
			values = removeMelting.get(output);
		}
		values.add(amount);
		removeMelting.put(output, values);
		//ToMeTinkers.logger.info("Added " + output.getUnlocalizedName() + " with amount " + amount + " to the remove Map.");
		if(Config.debug) {
			ToMeTinkers.logger.info("Added " + output.getUnlocalizedName() + " with amount " + amount + " to the remove Map.");
		}
	}
	
	protected void removeBasinCasting(ItemStack output, Fluid input) {
		removeBasinCasting.put(output, input);
		//ToMeTinkers.logger.info("Added " + output.getUnlocalizedName() + " with from Fluid " + input + " to the remove Map.");
		if(Config.debug) {
			ToMeTinkers.logger.info("Added " + output.getUnlocalizedName() + " from Fluid " + input.getUnlocalizedName() + " to the remove Map.");
		}
	}
	
	protected void removeTableCasting(ItemStack output, Fluid input) {
		removeTableCasting.put(output, input);
		if(Config.debug) {
			ToMeTinkers.logger.info("Added " + output.getUnlocalizedName() + " from Fluid " + input.getUnlocalizedName() + " to the remove Map.");
		}
	}
	
	/**
	 * removes all Recipes from the remove Maps.
	 */
	//public void remove() {
	public static void remove() {
		try {
			List<MeltingRecipe> remove = new ArrayList<MeltingRecipe>();
			Field f = TinkerRegistry.class.getDeclaredField("meltingRegistry");
			f.setAccessible(true);
			List<MeltingRecipe> list = (List<MeltingRecipe>)f.get(null);
			for(MeltingRecipe r:list) {
				//for(Fluid output:this.remove.keySet()) {
				for(Fluid output:removeMelting.keySet()) {
					//if(r.getResult().getFluid().equals(output) && r.getResult().amount == this.remove.get(output)) {
					//if(r.getResult().getFluid().equals(output) && r.getResult().amount == removeMelting.get(output)) {
						//remove.add(r);
					//}
					for(int amount:removeMelting.get(output)) {
						if(r.getResult().getFluid().equals(output) && r.getResult().amount == amount) {
							remove.add(r);
						}
					}
					//else {
						//ToMeTinkers.logger.info("Found Melting Result " + r.getResult().getUnlocalizedName() + "X" + r.getResult().amount);
					//}
				}
				//ToMeTinkers.logger.info("Found Melting Result " + r.getResult().getUnlocalizedName() + "X" + r.getResult().amount);
			}
			for(MeltingRecipe r:remove) {
				//ToMeTinkers.logger.info("Removed recipe " + r);
				list.remove(r);
				if(Config.debug) {
					ToMeTinkers.logger.info("Removed recipe " + r);
				}
			}
			//ToMeTinkers.logger.info("Removed all Smeltery Melting Recipes saved in the remove Map.");
			if(Config.debug) {
				ToMeTinkers.logger.info("Removed all Smeltery Melting Recipes saved in the remove Map.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			//ToMeTinkers.logger.catching(e);
			if(Config.debug) {
				ToMeTinkers.logger.catching(e);
			}
		}
		try {
			List<ICastingRecipe> remove = new ArrayList<ICastingRecipe>();
			Field f = TinkerRegistry.class.getDeclaredField("basinCastRegistry");
			f.setAccessible(true);
			List<ICastingRecipe> list = (List<ICastingRecipe>)f.get(null);
			for(ICastingRecipe r:list) {
				for(ItemStack output:removeBasinCasting.keySet()) {
					if(ItemStack.areItemStacksEqual(r.getResult(ItemStack.EMPTY, removeBasinCasting.get(output)), output)) {
						remove.add(r);
					}
				}
			}
			for(ICastingRecipe r:remove) {
				//ToMeTinkers.logger.info("Removed recipe " + r);
				list.remove(r);
				if(Config.debug) {
					ToMeTinkers.logger.info("Removed recipe " + r);
				}
			}
			//ToMeTinkers.logger.info("Removed all Smeltery Casting Recipes saved in the remove Map.");
			if(Config.debug) {
				ToMeTinkers.logger.info("Removed all Smeltery Casting Recipes saved in the remove Map.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			//ToMeTinkers.logger.catching(e);
			if(Config.debug) {
				ToMeTinkers.logger.catching(e);
			}
		}
		try {
			List<ICastingRecipe> remove = new ArrayList<ICastingRecipe>();
			Field f = TinkerRegistry.class.getDeclaredField("tableCastRegistry");
			f.setAccessible(true);
			List<ICastingRecipe> list = (List<ICastingRecipe>)f.get(null);
			for(ICastingRecipe r:list) {
				for(ItemStack output:removeTableCasting.keySet()) {
					if(ItemStack.areItemStacksEqual(r.getResult(ItemStack.EMPTY, removeTableCasting.get(output)), output)) {
						remove.add(r);
					}
				}
			}
			for(ICastingRecipe r:remove) {
				//ToMeTinkers.logger.info("Removed recipe " + r);
				list.remove(r);
				if(Config.debug) {
					ToMeTinkers.logger.info("Removed recipe " + r);
				}
			}
			//ToMeTinkers.logger.info("Removed all Smeltery Casting Recipes saved in the remove Map.");
			if(Config.debug) {
				ToMeTinkers.logger.info("Removed all Smeltery Casting Recipes saved in the remove Map.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			//ToMeTinkers.logger.catching(e);
			if(Config.debug) {
				ToMeTinkers.logger.catching(e);
			}
		}
		//ToMeTinkers.logger.info("Removed all Smeltery Recipes saved in the remove Maps.");
		if(Config.debug) {
			ToMeTinkers.logger.info("Removed all Smeltery Recipes saved in the remove Maps.");
		}
	}
	
	/*public static void onMeltingRegister(MeltingRegisterEvent e) {
		for(Fluid f:removeMelting.keySet()) {
			if(e.getRecipe().getResult().getFluid().equals(f)) {
				if(e.getRecipe().getResult().amount == removeMelting.get(f)) {
					e.setCanceled(true);
					if(Config.debug) {
						ToMeTinkers.logger.info("Removed recipe " + e.getRecipe());
					}
				}
			}
			//else {
				//ToMeTinkers.logger.info(e.getRecipe().getResult().getFluid() + " != " + f);
				//ToMeTinkers.logger.info("Found " + e.getRecipe().getResult().getFluid().getName() + ": " + e.getRecipe().getResult().getFluid() + " != " + f);
			//}
		}
		//ToMeTinkers.logger.info("Found Melting Result " + e.getRecipe().getResult().getUnlocalizedName() + "X" + e.getRecipe().getResult().amount);
	}*/
	
	protected static FluidMolten createFluid(String name, int color, int heat) {
		FluidMolten fluid = new FluidMolten(name, color);
		fluid.setTemperature(heat);
		//fluid.setUnlocalizedName(ToMeTinkers.MODID + ":" + name);
		fluid.setUnlocalizedName(ToMeTinkers.MODID + "." + name);
		if(fluids == null) {//why ever this can occure
			fluids = new ArrayList<FluidMolten>();
		}
		fluids.add(fluid);
		return fluid;
	}
	
}