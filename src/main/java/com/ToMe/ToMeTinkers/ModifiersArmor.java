package com.ToMe.ToMeTinkers;

import java.util.List;
import java.util.Optional;

import com.ToMe.ToMeTinkers.modifiers.ModBoundArmor;
import com.ToMe.ToMeTinkers.modifiers.ModExtraModifierArmor;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import c4.conarm.lib.book.ArmoryBook;
import c4.conarm.lib.utils.RecipeMatchHolder;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class ModifiersArmor {
	
	public static Modifier avaritiafree;
	public static Modifier extramods;
	public static Modifier bound;
	
	public static void registerModifiers() {
		for(ModifierRegister mod:ModifierRegister.values()) {
			if(Loader.isModLoaded(mod.modDependency())) {
				if(ToMeTinkers.cfg.getModifierEnable(mod.id)) {
					mod.register();
				}
			}
		}
	}
	
	public static void registerBookPages() {
		ArmoryBook.INSTANCE.addRepository(new FileRepository(ToMeTinkers.MODID + ":bookarmor"));
	}
	
	private static OreDictItemCombination oreDictItemCombination(ItemStack stack, String oreDict, int amountMatched) {
		return new ModifiersArmor().new OreDictItemCombination(stack, oreDict, amountMatched);
	}
	
	private enum ModifierRegister {
		avaritiafree("avaritiafree") {
			
			@Override
			protected void register() {
				ModifiersArmor.avaritiafree = new ModExtraModifierArmor("avaritiafree", 5);
				//ModifiersArmor.avaritiafree.addItem(new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("avaritia", "resource")), 1, 5), 1, 1);
				RecipeMatchHolder.addItem(ModifiersArmor.avaritiafree, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("avaritia", "resource")), 1, 5), 1, 1);
				super.register();
			}
			
			@Override
			protected String modDependency() {
				return "avaritia";
			}
			
		},
		extramods("extramods") {
			
			@Override
			protected void register() {
				ModifiersArmor.extramods = new ModExtraModifierArmor("extramodifier", 1);
				//ModifiersArmor.extramods.addRecipeMatch(oreDictItemCombination(new ItemStack(Items.GOLDEN_APPLE, 1, 1), "blockDiamond", 1));
				RecipeMatchHolder.addRecipeMatch(ModifiersArmor.extramods, oreDictItemCombination(new ItemStack(Items.GOLDEN_APPLE, 1, 1), "blockDiamond", 1));
				super.register();
			}
			
		},
		bound("bound") {
			
			@Override
			protected void register() {
				ModifiersArmor.bound = new ModBoundArmor();
				//ModifiersArmor.bound.addItem("string");
				RecipeMatchHolder.addItem(ModifiersArmor.bound, "string");
				super.register();
			}
			
		};
		
		private String id;
		private Modifier mod;
		
		private ModifierRegister(String id) {
			this.id = id;
		}
		
		protected void register() {
			if(Config.debug) {
				ToMeTinkers.logger.info("Registered Modifier " + id);
			}
		}
		
		protected String modDependency() {
			return "tconstruct";
		}
	}
	
	private class OreDictItemCombination extends RecipeMatch {
		
		protected final ItemStack stack;
		protected final List<ItemStack> oredictEntry;
		
		public OreDictItemCombination(ItemStack stack, String oreDict, int amountMatched) {
			super(amountMatched, 1);
			this.stack = stack;
			this.oredictEntry = OreDictionary.getOres(oreDict);
		}
		
		@Override
		public List<ItemStack> getInputs() {
			ImmutableList.Builder<ItemStack> builder = ImmutableList.builder();
			for(ItemStack stack:oredictEntry) {
				builder.add(stack);
			}
			builder.add(stack);
			return builder.build();
		}
		
		@Override
		public Optional<Match> matches(NonNullList<ItemStack> stacks) {
			List<ItemStack> found = Lists.newLinkedList();
			int stillNeeded = amountNeeded;
			boolean foundStack = false;
			
			for(ItemStack stack:stacks) {
				if(ItemStack.areItemsEqual(this.stack, stack) && ItemStack.areItemStackTagsEqual(this.stack, stack)) {
					ItemStack copy = stack.copy();
					copy.setCount(1);
					found.add(copy);
					foundStack = true;
					break;
				}
			}
			
			for(ItemStack ore : oredictEntry) {
				for(ItemStack stack : stacks) {
					if(OreDictionary.itemMatches(ore, stack, false)) {
						ItemStack copy = stack.copy();
						copy.setCount(Math.min(copy.getCount(), stillNeeded));
						found.add(copy);
						stillNeeded -= copy.getCount();
						if(stillNeeded <= 0 && foundStack) {
							return Optional.of(new Match(found, amountMatched));
						}
					}
				}
			}
			return Optional.empty();
		}
	}
	
}