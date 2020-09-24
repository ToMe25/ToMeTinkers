package com.ToMe.ToMeTinkers;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
//import slimeknights.tconstruct.tools.TinkerMaterials;

public enum MaterialArmorStats {
	
	Glass(MaterialStats.Glass.mat, new CoreMaterialStats(0.6f, 8.0f), new PlatesMaterialStats(1.2f, 0.1f, 0), new TrimMaterialStats(2.9f)) {
		
		@Override
		protected void addTraits() {
			//mat.addTrait(ArmorTraits.skeletal, ArmorMaterialType.CORE);
			mat.addTrait(ArmorTraits.spiny, ArmorMaterialType.CORE);
			//addTrait(mat, ArmorTraits.skeletal);
			addSecTrait(mat, ArmorTraits.rough);
			super.addTraits();
		}
		
	},
	Gold(MaterialStats.Gold.mat, new CoreMaterialStats(5.0f, 12.0f), new PlatesMaterialStats(0.75f, -3.5f, 0), new TrimMaterialStats(3.5f)),
	Topaz(MaterialStats.Topaz.mat, new CoreMaterialStats(13.8f, 18.5f), new PlatesMaterialStats(1.0f, 5.8f, 1.0f), new TrimMaterialStats(3.8f)) {
		
		@Override
		protected void addTraits() {
			addTrait(mat, ArmorTraits.ambitious);
			super.addTraits();
		}
		
	},
	Ruby(MaterialStats.Ruby.mat, new CoreMaterialStats(14.6f, 19.0f), new PlatesMaterialStats(1.1f, -5.5f, 1.5f), new TrimMaterialStats(6.0f)),
	Diamond(MaterialStats.Diamond.mat, new CoreMaterialStats(16.5f, 17.5f), new PlatesMaterialStats(1.2f, 6.5f, 2.0f), new TrimMaterialStats(8.0f)) {
		
		@Override
		protected void addTraits() {
			//addTrait(mat, TraitsArmor.strengthening);
			addSecTrait(mat, TraitsArmor.strengthening);
			mat.addTrait(TraitsArmor.optimized, ArmorMaterialType.CORE);
			super.addTraits();
		}
		
	},
	Sapphire(MaterialStats.Sapphire.mat, new CoreMaterialStats(15.25f, 17.0f), new PlatesMaterialStats(1.12f, -5.5f, 2.5f), new TrimMaterialStats(7.9f)),
	Emerald(MaterialStats.Emerald.mat, new CoreMaterialStats(16.2f, 17.2f), new PlatesMaterialStats(1.2f, 6.7f, 3.0f), new TrimMaterialStats(7.5f)) {
		
		@Override
		protected void addTraits() {
			addTrait(mat, ArmorTraits.ambitious);
			//addSecTrait(mat, ArmorTraits.ambitious);
			super.addTraits();
		}
		
	},
	MagicalWood(MaterialStats.MagicalWood.mat, new CoreMaterialStats(2.5f, 3.0f), new PlatesMaterialStats(1.0f, 1.0f, 0.0f), new TrimMaterialStats(0.5f)),
	EnchantedMetal(MaterialStats.EnchantedMetal.mat, new CoreMaterialStats(14.6f, 14.5f), new PlatesMaterialStats(1.1f, 0.75f, 0.0f), new TrimMaterialStats(6.0f)) {
		
		@Override
		protected void addTraits() {
			addTrait(mat, TraitsArmor.xpboost);
			super.addTraits();
		}
		
	},
	DemonicMetal(MaterialStats.DemonicMetal.mat, new CoreMaterialStats(5.5f, 12.5f), new PlatesMaterialStats(0.25f, 8.0f, 0.0f), new TrimMaterialStats(0.75f)) {
		
		@Override
		protected void addTraits() {
			for(ITrait t:mat.getAllTraits()) {
				mat.addTrait(t, ArmorMaterialType.CORE);
			}
			super.addTraits();
		}
		
	},
	EvilInfusedIron(MaterialStats.EvilInfusedIron.mat, new CoreMaterialStats(19.98f, 28.0f), new PlatesMaterialStats(1.0f, 0.0f, 5.0f), new TrimMaterialStats(5.35f)) {
		
		@Override
		protected void addTraits() {
			//addTrait(mat, TraitsArmor.withering);
			mat.addTrait(TraitsArmor.withering, ArmorMaterialType.CORE);
			super.addTraits();
		}
		
	},
	AvaritiaInfinity(MaterialStats.AvaritiaInfinity.mat, new CoreMaterialStats(/*470.5f*/676.0f, /*9845.0f*/984.5f), new PlatesMaterialStats(10.0f, /*9845.0f*//*984.5f*/676.0f, 200.0f), new TrimMaterialStats(/*9845.0f*//*984.5f*//*1120.0f*/676.0f)) {
		
		@Override
		protected void addTraits() {
			addTrait(mat, TraitsArmor.cosmic);
			addTrait(mat, Traits.unbreakable);
			super.addTraits();
		}
		
	},
	AvaritiaNeutronium(MaterialStats.AvaritiaNeutronium.mat, new CoreMaterialStats(113.5f, /*46.5f*/160.0f), new PlatesMaterialStats(1.0f, 160.0f, 6.0f), new TrimMaterialStats(/*268.5f*/160.0f)) {
		
		@Override
		protected void addTraits() {
			addTrait(mat, TraitsArmor.supermassive);
			super.addTraits();
		}
		
	},
	AvaritiaCrystalmatrix(MaterialStats.AvaritiaCrystalmatrix.mat, new CoreMaterialStats(/*58.5f*/83.0f, 42.0f), new PlatesMaterialStats(0.9f, 83.0f, 4.0f), new TrimMaterialStats(/*140.0f*/83.0f)) {
		
		@Override
		protected void addTraits() {
			addTrait(mat, TraitsArmor.crystallized);
			super.addTraits();
		}
		
	},
	Tritanium(MaterialStats.Tritanium.mat, new CoreMaterialStats(/*20.8f*//*61.75f*/120.5f, 2.75f), new PlatesMaterialStats(1.0f, 85.3f, 2.0f), new TrimMaterialStats(143.0f)),
	Dilithium(MaterialStats.Dilithium.mat, new CoreMaterialStats(30.0f, 6.0f), new PlatesMaterialStats(1.0f, 21.3f, 0.0f), new TrimMaterialStats(35.8f));
	
	protected Material mat;
	
	private MaterialArmorStats(Material mat, CoreMaterialStats coreStats, PlatesMaterialStats plateStats, TrimMaterialStats trimStats) {
		if(mat != null) {
			this.mat = mat;
			//if(coreStats != null && plateStats != null && trimStats != null) {
			if(coreStats != null && !mat.hasStats(ArmorMaterialType.CORE) && plateStats != null && !mat.hasStats(ArmorMaterialType.PLATES) && trimStats != null && !mat.hasStats(ArmorMaterialType.TRIM)) {
				TinkerRegistry.addMaterialStats(mat, coreStats, plateStats, trimStats);
				//try {
					//if(!mat.hasStats("core") && !mat.hasStats("plates") && ! mat.hasStats("trim")) {
						//TinkerRegistry.addMaterialStats(mat, coreStats, plateStats, trimStats);
					//}
				//} catch (Exception e) {
					//ToMeTinkers.logger.catching(e);
				//}
			}
			if(Config.debug) {
				ToMeTinkers.logger.info("Finished Conarm Material Integration for Material " + mat.identifier + ".");
			}
		}
	}
	
	protected void addTraits() {
		
	}
	
	/**
	 * Registers a trait to a materials CORE, PLATES, and TRIM stats;
	 * 
	 * @param mat   the material to add the trait to.
	 * @param trait the trait to add.
	 */
	protected void addTrait(Material mat, AbstractTrait trait) {
		if(!mat.hasTrait(trait.getIdentifier(), ArmorMaterialType.CORE)) {
			mat.addTrait(trait, ArmorMaterialType.CORE);
		}
		if(!mat.hasTrait(trait.getIdentifier(), ArmorMaterialType.PLATES)) {
			mat.addTrait(trait, ArmorMaterialType.PLATES);
		}
		if(!mat.hasTrait(trait.getIdentifier(), ArmorMaterialType.TRIM)) {
			mat.addTrait(trait, ArmorMaterialType.TRIM);
		}
	}
	
	/**
	 * Registers a trait to a materials PLATES and TRIM stats.
	 * 
	 * @param mat   the material to add the trait to.
	 * @param trait the trait to add.
	 */
	protected void addSecTrait(Material mat, AbstractTrait trait) {
		if(!mat.hasTrait(trait.getIdentifier(), ArmorMaterialType.PLATES)) {
			mat.addTrait(trait, ArmorMaterialType.PLATES);
		}
		if(!mat.hasTrait(trait.getIdentifier(), ArmorMaterialType.TRIM)) {
			mat.addTrait(trait, ArmorMaterialType.TRIM);
		}
	}
	
}