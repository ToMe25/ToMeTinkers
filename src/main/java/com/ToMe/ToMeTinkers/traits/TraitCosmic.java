package com.ToMe.ToMeTinkers.traits;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
//import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;
import java.util.Random;

import com.ToMe.ToMeTinkers.Config;
import com.ToMe.ToMeTinkers.ToMeTinkers;
//import com.ToMe.ToMeTinkers.Traits;
import com.ToMe.ToMeTinkers.Util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
//import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.oredict.OreDictionary;
//import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolCraftingEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolModifyEvent;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
//import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.traits.AbstractTrait;
//import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.TinkerUtil;
//import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.modifiers.ModExtraTrait;
//import slimeknights.tconstruct.tools.traits.TraitWritable;

public class TraitCosmic extends AbstractTrait {
//public class TraitCosmic extends ModExtraTrait {
//public class TraitCosmic extends AbstractTraitLeveled {
	
	//private static ItemStack helperStack = new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("tconstruct", "broadsword")));
	private static Random rand = new Random();
	
	public TraitCosmic() {
	//public TraitCosmic(int levels) {
		//super("cosmic", 0xffffff);
		super("avaritia_cosmic", 0xffffff);
		//super("cosmic", 0xffffff, 1, Integer.MAX_VALUE);
		//super("cosmic", 0xffffff, Integer.MAX_VALUE, 1);
		//super("avaritia_cosmic", 0xffffff, Integer.MAX_VALUE, 1);
		//super("cosmic", String.valueOf(Integer.MAX_VALUE), 0xffffff, Integer.MAX_VALUE, 1);
		//super("cosmic", String.valueOf(levels), 0xffffff, Integer.MAX_VALUE, 1);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	/*@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		super.applyEffect(rootCompound, modifierTag);
		NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
		int index = TinkerUtil.getIndexInCompoundList(tagList, getModifierIdentifier());
		NBTTagCompound tag = new NBTTagCompound();
		if(index > -1) {
			tag = tagList.getCompoundTagAt(index);
		}
		else {
			index = tagList.tagCount();
			tagList.appendTag(tag);
		}
		//ModifierNBT data = ModifierNBT.readTag(tag);
		//data.write(tag);
		//tag.setBoolean(identifier, true);
		//tagList.set(index, tag);
		//TagUtil.setModifiersTagList(rootCompound, tagList);
		//NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		//int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS);
		//modifiers += 5;
		//toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		//TagUtil.setToolTag(rootCompound, toolTag);
		if(!tag.getBoolean(identifier)) {
			ModifierNBT data = ModifierNBT.readTag(tag);
			data.level += 1;
			data.write(tag);
			tag.setBoolean(identifier, true);
			tagList.set(index, tag);
			TagUtil.setModifiersTagList(rootCompound, tagList);
			NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
			int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS);
			modifiers += 5;
			toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
			TagUtil.setToolTag(rootCompound, toolTag);
		}
	}*/
	
	/*@Override
	public void apply(ItemStack stack) {
		//super.apply(stack);
		//NBTTagCompound tags = this.getModifierTag(stack);
		NBTTagList tagList = TagUtil.getModifiersTagList(stack.getTagCompound());
		NBTTagCompound tags = tagList.getCompoundTagAt(TinkerUtil.getIndexInCompoundList(tagList, getModifierIdentifier()));
		//tags.setBoolean(key, true);
		tags.setBoolean(identifier, true);
		int modifiers = tags.getInteger("Modifiers");
		modifiers += 5;
		tags.setInteger("Modifiers", modifiers);
	}*/
	
	@Override
	public void updateNBTforTrait(NBTTagCompound modifierTag, int newColor) {
		super.updateNBTforTrait(modifierTag, newColor);
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		data.level = 0;
		data.write(modifierTag);
	}
	
	@Override
	//public void applyModifierEffect(NBTTagCompound rootCompound) {
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
	//public void applyBonusModifiers(NBTTagCompound rootCompound) {
		//super.applyModifierEffect(rootCompound);
		super.applyEffect(rootCompound, modifierTag);
		//ToMeTinkers.logger.info(rootCompound.toString());
		//ToMeTinkers.logger.info(modifierTag.toString());
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + 5;
		data.level++;
		NBTTagList parts = TagUtil.getBaseMaterialsTagList(rootCompound);
		if(data.level == 1 && parts.tagCount() < 3) {
			//if(Util.getHeadMaterial(rootCompound).getAllTraits().contains(this)) {
				//data.level++;
				//modifiers += 5;
			//}
			try {
				if(Util.getHeadMaterial(rootCompound).getAllTraits().contains(this)) {
					data.level++;
					modifiers += 5;
				}
			} catch (Exception e) {
				ToMeTinkers.logger.catching(e);
			}
		}
		data.write(modifierTag);
		//NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		//int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + 5;
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		TagUtil.setToolTag(rootCompound, toolTag);
		//ModifierNBT data = ModifierNBT.readTag(modifierTag);
		//data.level++;
		//data.write(modifierTag);
		/*NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS);
		//System.out.println("Tool has " + modifiers + " Modifiers");
		//NBTTagList parts = toolTag.getTagList(Tags.PART_MATERIAL, 0);
		//NBTTagList parts = TagUtil.getBaseMaterialsTagList(rootCompound);
		//int i = 0;
		//while(i < parts.tagCount()) {
			//ToMeTinkers.logger.info("TraitCosmic: " + parts.getCompoundTagAt(i));
			//i++;
		//}
		//int lvl = 0;
		//for(Material mat:TinkerUtil.getMaterialsFromTagList(parts)) {
		//for(IModifier mod:TinkerUtil.getModifiers(new ItemStack(Items.APPLE, 1, 0, rootCompound))) {
			//if(mat.identifier.equalsIgnoreCase("avaritia_infinitymetal")) {
				//lvl ++;
			//}
			//if(mat.getAllTraits().contains(this)) {
				//lvl++;
			//}
			//if(mod.getIdentifier().equals(identifier)) {
				//lvl++;
			//}
		//}
		//boolean embossment = false;
		//ItemStack stack = new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("tconstruct", "broadsword")), 1, 0, rootCompound);
		//stack.setTagCompound(rootCompound);
		//for(IModifier mod:TinkerUtil.getModifiers(new ItemStack(Items.APPLE, 1, 0, rootCompound))) {
		//for(IModifier mod:TinkerUtil.getModifiers(new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("tconstruct", "broadsword")), 1, 0, rootCompound))) {
		//for(IModifier mod:TinkerUtil.getModifiers(stack)) {
			//if(mod instanceof TraitCosmic) {
				//if(embossment) {
					//modifiers += 5;
					//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Embossment!");
				//}
				//else {
					//embossment = true;
					//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Modifier!");
				//}
			//}
			//if(mod instanceof ModExtraTrait) {
				//ModExtraTrait embossment = (ModExtraTrait) mod;
				//try {
					//Field traits = ModExtraTrait.class.getDeclaredField("traits");
					//traits.setAccessible(true);
					//Collection<ITrait> trait = (Collection<ITrait>) traits.get(embossment);
					//for(ITrait t:trait) {
						//if(t instanceof TraitCosmic) {
							//modifiers += 5;
							//lvl++;
							//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Embossment!");
						//}
					//}
				//} catch (Exception e) {
					// TODO: handle exception
					//ToMeTinkers.logger.catching(e);
				//}
				//ToMeTinkers.logger.info("TraitCosmic: " + "has a Embossment!");
			//}
			//ToMeTinkers.logger.info("TraitCosmic: " + "has " + mod.getIdentifier() + " Modifier!");
			//ToMeTinkers.logger.info("TraitCosmic: " + "has " + mod.getClass() + " Modifier!");
		//}
		//if(TinkerUtil.hasModifier(rootCompound, "extratrait" + identifier)) {
		//if(TinkerUtil.hasModifier(rootCompound, "extratrait")) {
			//NBTTagCompound nbttc = TinkerUtil.getModifierTag(rootCompound, "extratrait");
			//Modifier embossment = TinkerUtil.getModifierTag(rootCompound, "extratrait");
			//for(String s:nbttc.getKeySet()) {
				//ToMeTinkers.logger.info("TraitCosmic: " + "has " + s + " Embossment!");
			//}
			//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Embossment!");
			//modifiers += 5;
		//}
		//for(toolTag.getTagList(Tags.PART_MATERIAL, 0)) {
			
		//}
		NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
		int index = TinkerUtil.getIndexInCompoundList(tagList, getModifierIdentifier());
		NBTTagCompound tag = new NBTTagCompound();
		if(index > -1) {
			tag = tagList.getCompoundTagAt(index);
		}
		else {
			//this.updateNBTforTrait(tag, color);
			index = tagList.tagCount();
			tagList.appendTag(tag);
		}
		ModifierNBT data = ModifierNBT.readTag(tag);
		//ModifierNBT data = ModifierNBT.readTag(modifierTag);
		int lvl = getCosmicLevel(rootCompound);
		//ToMeTinkers.logger.info("" + data.level);
		//ToMeTinkers.logger.info("TraitCosmic: " + "data.level = " + data.level + ", lvl = " + lvl);
		int lvlChange = lvl - data.level;
		//int lvlChange = lvl - (data.level < 2 ? data.level - 1 : data.level);
		//int lvlChange = lvl - (data.level == 1 ? data.level - 1 : (data.level == 0 ? data.level + 1 : data.level));//prevent bugs with data.level == 1
		//int lvlChange = lvl - (data.level == 0 ? data.level + 1 : data.level);//prevent bugs with data.level == 1
		//int lvlChange = lvl - (data.level - 1);//data.level is default 1, lvl is default 0.
		//ToMeTinkers.logger.info("TraitCosmic: " + "data.level = " + data.level + ", lvl = " + lvl + ", lvlChange = " + lvlChange);
		//ToMeTinkers.logger.info("TraitCosmic: " + (lvlChange < 0 ? "removed " + ((lvlChange * 5) * -1) : "added " + (lvlChange * 5)) + "modifiers.");
		modifiers += 5 * lvlChange;
		//modifiers += 5 * lvl;
		//modifiers += 5;
		//if(lvlChange > 0 || lvlChange < 0) {
		//if(lvlChange > 1) {
		//if(lvlChange > 0) {
			//modifiers += 5 * lvlChange;
			//modifiers += 5 * (lvlChange - 1);
		//}
		//ToMeTinkers.logger.info("TraitCosmic: " + "current tool has " + modifiers + " modifiers.");
		data.level = lvl;
		//data.level = lvl == 1 ? 0 : lvl;//prevent bugs with data.level == 1
		//ToMeTinkers.logger.info("TraitCosmic: " + "saving data.level as " + (lvl == 1 ? 0 : lvl));
		//if(data.level == 1) {
			//data.level = getCosmicLevel(rootCompound);
			//NBTTagList materials = TagUtil.getBaseMaterialsTagList(rootCompound);
			//if(materials.tagCount() < 3) {
				//if(Util.getHeadMaterial(rootCompound).getAllTraits().contains(this)) {
					//data.level = getCosmicLevel(rootCompound);
					//data.level++;
					//modifiers += 5;
					//ToMeTinkers.logger.info("TraitCosmic: " + "Found a two Part Tool with a Cosmic Head.");
				//}
			//}
		//}
		//data.level++;
		//data.level = getCosmicLevel(rootCompound);
		//modifiers += 5;
		//data.level = lvl + 1;//data.level is default 1, lvl is default 0.
		//data.write(tag);
		//modifiers += 5;
		//levels = lvl;
		//modifiers += 5 * levels;
		//modifiers += 5 * lvl;
		//modifiers += 5 * (lvl - 1);
		//if(lvl > 1) {
			//modifiers += 5 * (lvl - 1);
		//}
		//System.out.println("Changed Modifiers to " + modifiers);
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		data.write(tag);
		//data.write(modifierTag);
		tagList.set(index, tag);
		TagUtil.setModifiersTagList(rootCompound, tagList);
		TagUtil.setToolTag(rootCompound, toolTag);
		//NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
		//int index = TinkerUtil.getIndexInCompoundList(tagList, getModifierIdentifier());
		//NBTTagCompound tag = new NBTTagCompound();
		//if(index > -1) {
			//tag = tagList.getCompoundTagAt(index);
		//}
		//else {
			//index = tagList.tagCount();
			//tagList.appendTag(tag);
		//}
		//ModifierNBT data = ModifierNBT.readTag(tag);
		//data.level = lvl;
		//if(lvl > data.level) {
			//data.level = lvl;
		//}
		//data.write(tag);
		//TagUtil.setToolTag(rootCompound, toolTag);
		//super.applyEffect(rootCompound, modifierTag);*/
	}
	
	/*@Override
	public void applyModifierEffect(NBTTagCompound rootCompound) {
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + (levels * 5);
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		TagUtil.setToolTag(rootCompound, toolTag);
	}*/
	
	@SubscribeEvent
    public void onHarvestDrops(HarvestDropsEvent event) {
		if(event.getHarvester() == null)
			return;
		if(event.getHarvester().getHeldItemMainhand() == null && event.getHarvester().getHeldItemOffhand() == null)
			return;
		for(ItemStack stack:event.getHarvester().getEquipmentAndArmor()) {
		//for(ItemStack stack:Util.getEquipmentAndArmor(event.getHarvester())) {
			//ToMeTinkers.logger.info("TraitCosmic: " + stack.getDisplayName());
			if (stack != null && stack.hasTagCompound() && stack.getItem() instanceof ToolCore) {
			//if (stack != null && stack.hasTagCompound()) {
				//ToolCore tool = (ToolCore)stack.getItem();
				//NBTTagCompound toolTag = stack.getTagCompound().getCompoundTag("InfiTool");
				//ToMeTinkers.logger.info("TraitCosmic: " + TinkerUtil.getMaterialFromStack(stack));
				//ToMeTinkers.logger.info("TraitCosmic: " + TinkerUtil.getMaterialFromStack(stack).getIdentifier());
				//if(TinkerUtil.getMaterialFromStack(stack).getAllTraits().contains(this)) {
				//if (toolTag.getInteger("Head") == Tonkers.infinityMetalId) {
				//if(getHeadMaterial(stack.getTagCompound()).getAllTraits().contains(this)) {
				//if(Util.getHeadMaterial(stack.getTagCompound()).getAllTraits().contains(this)) {
				if(Util.getHeadMaterial(TagUtil.getTagSafe(stack)).getAllTraits().contains(this)) {
					//ToMeTinkers.logger.info("TraitCosmic: " + stack.getDisplayName() + " has Cosmic.");
					//int parts = 1;
					//if (toolTag.getInteger("Handle") == Tonkers.infinityMetalId) {
						//parts++;
					//}
					//if (toolTag.getInteger("Accessory") == Tonkers.infinityMetalId) {
						//parts++;
					//}
					//if (toolTag.getInteger("Extra") == Tonkers.infinityMetalId) {
						//parts++;
					//}
					//int lvl = getCosmicLevel(stack.getTagCompound());
					int lvl = getCosmicLevel(TagUtil.getTagSafe(stack));
					int luck = Math.min(3, lvl);
					//NBTTagList tagList = TagUtil.getBaseMaterialsTagList(stack.getTagCompound());
					NBTTagList tagList = TagUtil.getBaseMaterialsTagList(TagUtil.getTagSafe(stack));
					//if (lvl == TinkerUtil.getMaterialsFromTagList(tagList).size()) {
					if (lvl == tagList.tagCount()) {
						luck++;
						//if (tagList.tagCount() == 2) {//Moved to getCosmicLevel
							//luck++;
						//}
					}
					try {
						Class EventHandler = Class.forName("morph.avaritia.handler.AvaritiaEventHandler");
						//for(Method m:EventHandler.getDeclaredMethods()) {
							//String Parameters = "";
							//for(Class<?> c:m.getParameterTypes()) {
								//Parameters +=", ";
								//Parameters += c.getName();
							//}
							//Parameters = Parameters.substring(2);
							//ToMeTinkers.logger.info("TraitCosmic: " + m.getName() + "(" + Parameters + ")");
						//}
						//Method applyLuck = EventHandler.getDeclaredMethod("applyLuck", HarvestDropsEvent.class, Integer.class);
						Method applyLuck = EventHandler.getDeclaredMethod("applyLuck", HarvestDropsEvent.class, int.class);
						//Method applyLuck = EventHandler.getMethod("applyLuck", HarvestDropsEvent.class, Integer.class);
						applyLuck.invoke(null, event, luck);
					} catch (Exception e) {
						// TODO: handle exception
						ToMeTinkers.logger.catching(e);
					}
					/*if(event.getState().getMaterial() == net.minecraft.block.material.Material.ROCK) {
						List<ItemStack> adds = new ArrayList<ItemStack>();
						List<ItemStack> removals = new ArrayList<ItemStack>();
						for(ItemStack drop:event.getDrops()) {
							if(drop.getItem() != Item.getItemFromBlock(event.getState().getBlock()) && !(drop.getItem() instanceof ItemBlock)){
								drop.setCount(Math.min(drop.getCount() * luck, drop.getMaxStackSize()));
							}
							else if(Config.fractured && drop.getItem() == Item.getItemFromBlock(event.getState().getBlock())) {
								ItemFracturedOre ifo = (ItemFracturedOre)LudicrousItems.fractured_ore;
								int[] oreids = OreDictionary.getOreIDs(drop);
								for (int i=0; i<oreids.length; i++) {
									String orename = OreDictionary.getOreName(oreids[i]);
									if (orename.startsWith("ore")) {
										adds.add(ifo.getStackForOre(drop, Math.min(drop.getCount() * (luck + 1), drop.getMaxStackSize())));
										removals.add(drop);
										break;
									}
								}
							}
						}
						event.getDrops().addAll(adds);
						event.getDrops().removeAll(removals);
					}*/
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerMine(PlayerInteractEvent event) {
		if(!Config.cosmicBedrockMiner || /*event.getFace() == EnumFacing.getFront(-1) || */event.getWorld().isRemote || /*event.getResult() instanceof PlayerInteractEvent.LeftClickBlock.class ||*/ (event.getEntityPlayer().getHeldItemMainhand().isEmpty() && event.getEntityPlayer().getHeldItemOffhand().isEmpty())/*(event.getEntityPlayer().getHeldItemMainhand() == null && event.getEntityPlayer().getHeldItemOffhand() == null)*//*event.getEntityPlayer().getActiveItemStack() == null*/ || event.getEntityPlayer().capabilities.isCreativeMode)
			//ToMeTinkers.logger.info("onPlayerMine Exit");
			//ToMeTinkers.logger.info("Exitet with properties" + (" " + !Config.cosmicBedrockMiner) + (" " + (event.getFace() == EnumFacing.getFront(-1))) + (" " + event.getWorld().isRemote) + (" " + (event.getEntityPlayer().getActiveItemStack() == null)) + (" " + event.getEntityPlayer().capabilities.isCreativeMode));
			return;
		IBlockState state = event.getWorld().getBlockState(event.getPos());
		Block block = state.getBlock();
		//Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
		int meta = block.getMetaFromState(state);
		//ItemStack held = event.getEntityPlayer().getActiveItemStack();
		ItemStack held = event.getEntityPlayer().getHeldItemMainhand();
		if(state.getBlockHardness(event.getEntityPlayer().world, event.getPos()) <= -1 && held.hasTagCompound() && held.getItem() instanceof ToolCore && (state.getMaterial() == net.minecraft.block.material.Material.ROCK || state.getMaterial() == net.minecraft.block.material.Material.IRON)) {
			//ToMeTinkers.logger.info("TraitCosmic: " + "Should be mined!");
			//NBTTagCompound toolTag = held.getTagCompound().getCompoundTag("InfiTool");
			ToolCore tool = (ToolCore)held.getItem();
			BlockPos pos = event.getPos();
			//if (toolTag != null && toolTag.getInteger("Head") == Tonkers.infinityMetalId && tool.canHarvestBlock(Blocks.STONE.getDefaultState(), held)) {
			//if(getHeadMaterial(held.getTagCompound()).getAllTraits().contains(this) && tool.canHarvestBlock(Blocks.STONE.getDefaultState(), held)) {
			//if(Util.getHeadMaterial(held.getTagCompound()).getAllTraits().contains(this) && tool.canHarvestBlock(Blocks.STONE.getDefaultState(), held)) {
			if(Util.getHeadMaterial(TagUtil.getTagSafe(held)).getAllTraits().contains(this) && tool.canHarvestBlock(Blocks.STONE.getDefaultState(), held)) {
				if(block.quantityDropped(rand) == 0) {
					ItemStack drop = block.getPickBlock(state, raytraceFromEntity(event.getWorld(), event.getEntityPlayer(), true, 10), event.getWorld(), event.getPos(), event.getEntityPlayer());
					if(drop == null)
						drop = new ItemStack(block, 1, meta);
					float f = 0.7F;
					double d0 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					//BlockPos pos = event.getPos();
					EntityItem entityitem = new EntityItem(event.getWorld(), (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, drop);
					entityitem.setPickupDelay(10);
					event.getWorld().spawnEntity(entityitem);
					//ToMeTinkers.logger.info("TraitCosmic: " + "dropped Item.");
				}
				else {
					block.harvestBlock(event.getWorld(), event.getEntityPlayer(), event.getPos(), state, null, event.getEntityPlayer().getActiveItemStack());
					//ToMeTinkers.logger.info("TraitCosmic: " + "dropped Item.");
				}
				event.getEntityPlayer().world.setBlockToAir(event.getPos());
				//event.getWorld().playAuxSFX(2001, event.x, event.y, event.z, Block.getIdFromBlock(block) + (meta << 12));
				//event.getWorld().playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 0.25f, 0.5f);
				//event.getWorld().playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.NEUTRAL, 0.25f, 0.5f);
				event.getWorld().playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.NEUTRAL, 1.0f, 0.8f);
			}
		}
		//else {
			//ToMeTinkers.logger.info("TraitCosmic: " + Blocks.BEDROCK.getDefaultState().getMaterial());
			//ToMeTinkers.logger.info("TraitCosmic: " + (state.getBlockHardness(event.getEntityPlayer().world, event.getPos()) <= -1) + " " + (held.getItem() instanceof ToolCore) + " " + (state.getMaterial() == net.minecraft.block.material.Material.ROCK || state.getMaterial() == net.minecraft.block.material.Material.IRON));
			//ToMeTinkers.logger.info("TraitCosmic: " + held.getItem().getClass());
		//}
	}
	
	/*@SubscribeEvent
	public void onToolCrafting(ToolCraftingEvent event) {
	//public static void onToolCrafting(ToolCraftingEvent event) {
		//ToMeTinkers.logger.info("onToolCrafting");
		NBTTagCompound rootCompound = event.getItemStack().getTagCompound();
		NBTTagCompound modifierTag = new NBTTagCompound();
		NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
		int index = TinkerUtil.getIndexInList(tagList, identifier);
		if(index >= 0) {
			modifierTag = tagList.getCompoundTagAt(index);
		}
		applyEffect(rootCompound, modifierTag);
		//apply(rootCompound);//Just to make sure all Cosmic levels where detected
		//applyBonusModifiers(rootCompound);
		//((TraitCosmic)Traits.cosmic).applyBonusModifiers(rootCompound);
	}*/
	
	/*@SubscribeEvent
	public void onToolModify(ToolModifyEvent event) {
		//for(IModifier mod:event.getModifiers()) {
			//if(mod instanceof ModExtraTrait || mod instanceof TraitCosmic) {
				//ToMeTinkers.logger.info("TraitCosmic: " + "Changing Modifiers Count.");
			//}
			//else {
				//return;
			//}
		//}
		//NBTTagCompound rootCompound = event.getItemStack().getTagCompound();
		NBTTagCompound rootCompound = TagUtil.getTagSafe(event.getItemStack());
		//if(TinkerUtil.hasTrait(event.getItemStack().getTagCompound(), identifier)) {
		//if(TinkerUtil.hasTrait(rootCompound, identifier)) {
		if(TinkerUtil.getModifiers(event.getItemStack()).contains(this)) {
		//if(event.getModifiers().contains(this)) {
			//NBTTagCompound rootCompound = event.getItemStack().getTagCompound();
			NBTTagCompound modifierTag = new NBTTagCompound();
			NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
			int index = TinkerUtil.getIndexInList(tagList, identifier);
			if(index >= 0) {
				modifierTag = tagList.getCompoundTagAt(index);
			}
			//else {
				//this.updateNBTforTrait(modifierTag, color);
				//tagList.appendTag(modifierTag);
				//TagUtil.setModifiersTagList(rootCompound, tagList);
			//}
			applyEffect(rootCompound, modifierTag);
		}
		*//*for(IModifier mod:TinkerUtil.getModifiers(event.getItemStack())) {
			if(mod instanceof ModExtraTrait) {
				ModExtraTrait embossment = (ModExtraTrait) mod;
				try {
					Field traits = ModExtraTrait.class.getDeclaredField("traits");
					traits.setAccessible(true);
					Collection<ITrait> trait = (Collection<ITrait>) traits.get(embossment);
					if(trait.contains(this)) {
						NBTTagCompound modifierTag = new NBTTagCompound();
						NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
						int index = TinkerUtil.getIndexInList(tagList, identifier);
						if(index >= 0) {
							modifierTag = tagList.getCompoundTagAt(index);
						}
						applyEffect(rootCompound, modifierTag);
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
					ToMeTinkers.logger.catching(e);
				}
			}
		}*//*
		//if(Util.hasEmbossment(rootCompound, this)) {
		if(Util.hasEmbossment(rootCompound, this, false)) {
			NBTTagCompound modifierTag = new NBTTagCompound();
			NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
			int index = TinkerUtil.getIndexInList(tagList, identifier);
			if(index >= 0) {
				modifierTag = tagList.getCompoundTagAt(index);
			}
			//else {
				//this.updateNBTforTrait(modifierTag, color);
				//tagList.appendTag(modifierTag);
				//TagUtil.setModifiersTagList(rootCompound, tagList);
			//}
			applyEffect(rootCompound, modifierTag);
		}
		//NBTTagCompound rootCompound = event.getItemStack().getTagCompound();
		//NBTTagCompound modifierTag = new NBTTagCompound();
		//NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
		//int index = TinkerUtil.getIndexInList(tagList, identifier);
		//if(index >= 0) {
			//modifierTag = tagList.getCompoundTagAt(index);
		//}
		//applyEffect(rootCompound, modifierTag);
		//applyBonusModifiers(rootCompound);
	}*/
	
	private int getCosmicLevel(NBTTagCompound rootTag) {
		int lvl = 0;
		NBTTagList parts = TagUtil.getBaseMaterialsTagList(rootTag);
		for(Material mat:TinkerUtil.getMaterialsFromTagList(parts)) {
			if(mat.getAllTraits().contains(this)) {
				lvl++;
			}
		}
		if(parts.tagCount() < 3) {
			//if(getHeadMaterial(rootTag).getAllTraits().contains(this)) {
			if(Util.getHeadMaterial(rootTag).getAllTraits().contains(this)) {
				lvl++;
			}
		}
		//helperStack.setTagCompound(rootTag);
		//for(IModifier mod:TinkerUtil.getModifiers(helperStack)) {
		/*for(IModifier mod:Util.getModifiers(rootTag)) {
			if(mod instanceof ModExtraTrait) {
				ModExtraTrait embossment = (ModExtraTrait) mod;
				try {
					Field traits = ModExtraTrait.class.getDeclaredField("traits");
					traits.setAccessible(true);
					Collection<ITrait> trait = (Collection<ITrait>) traits.get(embossment);
					if(trait.contains(this)) {
						lvl++;
						//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Embossment!");
					}
					//for(ITrait t:trait) {
						//if(t instanceof TraitCosmic) {
							//lvl++;
							//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Embossment!");
						//}
					//}
					//Field mat = ModExtraTrait.class.getDeclaredField("material");
					//mat.setAccessible(true);
					//Material material = (Material) mat.get(embossment);
					//if(material.getAllTraits().contains(this)) {
						//lvl++;
						//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Embossment!");
					//}
					//ToMeTinkers.logger.info("TraitCosmic: " + "has " + material.getIdentifier() + " Embossment!");
					//for(ITrait t:material.getAllTraits()) {
						//ToMeTinkers.logger.info("TraitCosmic: " + "has " + t.getIdentifier() + " Embossment!");
					//}
				} catch (Exception e) {
					// TODO: handle exception
					ToMeTinkers.logger.catching(e);
				}
				//ToMeTinkers.logger.info("TraitCosmic: " + "has a Embossment!");
			}
			//ToMeTinkers.logger.info("TraitCosmic: " + "has " + mod.getClass() + " Modifier!");
			//ToMeTinkers.logger.info("TraitCosmic: " + "has " + mod.getIdentifier() + " Modifier!");
		}*/
		//if(Util.hasEmbossment(rootTag, this)) {
		if(Util.hasEmbossment(rootTag, this, false)) {
			lvl++;
			//ToMeTinkers.logger.info("TraitCosmic: " + "has Cosmic Embossment!");
		}
		//else {
			//ToMeTinkers.logger.info("TraitCosmic: " + "has no Cosmic Embossment!");
		//}
		//ToMeTinkers.logger.info("TraitCosmic: " + "lvl = " + lvl);
		return lvl;
	}
	
	public static RayTraceResult raytraceFromEntity(World world, Entity player, boolean wut, double range) {
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f;
		if (!world.isRemote && player instanceof EntityPlayer)
			d1 += 1.62D;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		Vec3d vec3 = new Vec3d(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		Vec3d vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, wut);
	}
	
	/*public static Material getHeadMaterial(NBTTagCompound rootTag) {
		NBTTagList tagList = TagUtil.getBaseMaterialsTagList(rootTag);
		return TinkerUtil.getMaterialsFromTagList(tagList).get(1);//1 should be Head.
	}*/
	
}