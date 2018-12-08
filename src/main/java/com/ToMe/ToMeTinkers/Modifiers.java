package com.ToMe.ToMeTinkers;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import com.ToMe.ToMeTinkers.modifiers.ModBound;
import com.ToMe.ToMeTinkers.modifiers.ModExtraModifier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.client.book.BookHelper;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.mantle.util.RecipeMatch.Match;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.book.TinkerBook;
import slimeknights.tconstruct.library.book.content.ContentModifier;
import slimeknights.tconstruct.library.book.sectiontransformer.ModifierSectionTransformer;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.tools.AbstractToolPulse;

public class Modifiers {
	
	//public static Modifier avaritiafree = new ModExtraModifier("AvaritiaFree", 5);
	//public static Modifier extramods = new ModExtraModifier("ExtraModifier", 1);
	//public static Modifier avaritiafree = new ModExtraModifier("avaritiafree", 5);
	//public static Modifier extramods = new ModExtraModifier("extramodifier", 1);
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
	
	//private OreDictItemCombination oreDictItemCombination(ItemStack stack, String oreDict, int amountMatched) {
	private static OreDictItemCombination oreDictItemCombination(ItemStack stack, String oreDict, int amountMatched) {
		//return new OreDictItemCombination(stack, oreDict, amountMatched);
		return new Modifiers().new OreDictItemCombination(stack, oreDict, amountMatched);
	}
	
	//private static void register(Modifier mod) {
		//try {
			//Field modifiers = AbstractToolPulse.class.getDeclaredField("modifiers");
			//modifiers.setAccessible(true);
			//List<IModifier> mods = (List<IModifier>) modifiers.get(null);
			//mods.add(mod);
			//TinkerBook.INSTANCE.addRepository(new FileRepository("tometinkers:" + "book"));
			//TinkerBook.INSTANCE.addRepository(new FileRepository(ToMeTinkers.MODID + ":book"));
		//} catch (Exception e) {
			// TODO: handle exception
			//ToMeTinkers.logger.catching(e);
		//}
		//TinkerBook.INSTANCE.addRepository(new FileRepository(ToMeTinkers.MODID + ":book"));
	//}
	
	private enum ModifierRegister {
		avaritiafree("avaritiafree"/*, Modifiers.avaritiafree*/) {
			
			@Override
			protected void register() {
				Modifiers.avaritiafree = new ModExtraModifier("avaritiafree", 5);
				Modifiers.avaritiafree.addItem(new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("avaritia", "resource")), 1, 5), 1, 1);
				super.register();
			}
			
			@Override
			protected String modDependency() {
				return "avaritia";
			}
			
		},
		extramods("extramods"/*, Modifiers.extramods*/) {
			
			@Override
			protected void register() {
				Modifiers.extramods = new ModExtraModifier("extramodifier", 1);
				//Modifiers.extramods.addItem(new ItemStack(Items.GOLDEN_APPLE, 1, 1), 1, 1);
				//Modifiers.extramods.addItem("blockDiamond", 1, 1);
				//Modifiers.extramods.addRecipeMatch(new RecipeMatch.ItemCombination(1, new ItemStack(Items.GOLDEN_APPLE, 1, 1), new ItemStack(Blocks.DIAMOND_BLOCK)));
				Modifiers.extramods.addRecipeMatch(oreDictItemCombination(new ItemStack(Items.GOLDEN_APPLE, 1, 1), "blockDiamond", 1));
				//for(ItemStack stack:oreDictItemCombination(new ItemStack(Items.GOLDEN_APPLE, 1, 1), "blockDiamond", 1).getInputs()) {
					//ToMeTinkers.logger.info("Modifiers: " + stack.getUnlocalizedName());
				//}
				super.register();
			}
			
		},
		bound("bound") {
			
			@Override
			protected void register() {
				Modifiers.bound = new ModBound();
				Modifiers.bound.addItem("string");
				super.register();
			}
			
		};
		
		private String id;
		private Modifier mod;
		
		//private ModifierRegister(Modifier mod) {
			//this(mod.identifier.toLowerCase(), mod);
			//this(mod.identifier, mod);
		//}
		
		//private ModifierRegister() {
		private ModifierRegister(String id) {
		//private ModifierRegister(String id, Modifier mod) {
			this.id = id;
			//this.mod = mod;
		}
		
		protected void register() {
			//Modifiers.register(this.mod);
			//TinkerRegistry.registerModifier(mod);
			if(Config.debug) {
				ToMeTinkers.logger.info("Registered Modifier " + id);
			}
		}
		
		protected String modDependency() {
			return "tconstruct";
		}
	}
	
	/*protected*/private class OreDictItemCombination extends RecipeMatch {
		
		protected final ItemStack stack;
		protected final List<ItemStack> oredictEntry;
		
		public OreDictItemCombination(ItemStack stack, String oreDict, int amountMatched) {
			super(amountMatched, 1);
			this.stack = stack;
			this.oredictEntry = OreDictionary.getOres(oreDict);
		}
		
		@Override
		public List<ItemStack> getInputs() {
			//return ImmutableList.copyOf(itemStacks);
			ImmutableList.Builder<ItemStack> builder = ImmutableList.builder();
			//oredictEntry.forEach(stack -> IntStream.range(0, amountNeeded).forEach(i -> builder.add(stack)));
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