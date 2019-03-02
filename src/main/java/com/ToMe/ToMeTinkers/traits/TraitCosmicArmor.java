package com.ToMe.ToMeTinkers.traits;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

import com.ToMe.ToMeTinkers.Config;
import com.ToMe.ToMeTinkers.ToMeTinkers;
import com.ToMe.ToMeTinkers.Util;

import c4.conarm.lib.armor.ArmorCore;
import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolModifyEvent;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitCosmicArmor extends AbstractArmorTrait {
	
	//private static Random rand = new Random();
	private static List<String> fullInfinityPlayers = new ArrayList<String>();
	private static List<String> playersWithHat = new ArrayList<String>();
	private static List<String> playersWithChest = new ArrayList<String>();
	private static List<String> playersWithLeg = new ArrayList<String>();
	private static List<String> playersWithFoot = new ArrayList<String>();
	private static int errorMessageDelay = 0;
	
	public TraitCosmicArmor() {
		super("avaritia_cosmic", 0xffffff);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void updateNBTforTrait(NBTTagCompound modifierTag, int newColor) {
		super.updateNBTforTrait(modifierTag, newColor);
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		data.level = 0;
		data.write(modifierTag);
	}
	
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		super.applyEffect(rootCompound, modifierTag);
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + 5;
		data.level++;
		NBTTagList parts = TagUtil.getBaseMaterialsTagList(rootCompound);
		if(data.level == 1 && parts.tagCount() < 3) {
			if(Util.getHeadMaterial(rootCompound).getAllTraits().contains(this)) {
				data.level++;
				modifiers += 5;
			}
		}
		data.write(modifierTag);
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		TagUtil.setToolTag(rootCompound, toolTag);
		/*NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS);
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
		ModifierNBT data = ModifierNBT.readTag(tag);
		int lvl = getCosmicLevel(rootCompound);
		int lvlChange = lvl - (data.level == 1 ? data.level - 1 : (data.level == 0 ? data.level + 1 : data.level));//prevent bugs with data.level == 1
		modifiers += 5 * lvlChange;
		data.level = lvl == 1 ? 0 : lvl;//prevent bugs with data.level == 1
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
		data.write(tag);
		TagUtil.setToolTag(rootCompound, toolTag);
		//super.applyEffect(rootCompound, modifierTag);*/
	}
	
	/*@SubscribeEvent
	public void onToolModify(ToolModifyEvent event) {
		//NBTTagCompound rootCompound = event.getItemStack().getTagCompound();
		NBTTagCompound rootCompound = TagUtil.getTagSafe(event.getItemStack());
		if(TinkerUtil.getModifiers(event.getItemStack()).contains(this)) {
			NBTTagCompound modifierTag = new NBTTagCompound();
			NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
			int index = TinkerUtil.getIndexInList(tagList, identifier);
			if(index >= 0) {
				modifierTag = tagList.getCompoundTagAt(index);
			}
			applyEffect(rootCompound, modifierTag);
		}
		//if(Util.hasEmbossment(rootCompound, this)) {
		if(Util.hasEmbossment(rootCompound, this, false)) {
			NBTTagCompound modifierTag = new NBTTagCompound();
			NBTTagList tagList = TagUtil.getModifiersTagList(rootCompound);
			int index = TinkerUtil.getIndexInList(tagList, identifier);
			if(index >= 0) {
				modifierTag = tagList.getCompoundTagAt(index);
			}
			applyEffect(rootCompound, modifierTag);
		}
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
			if(Util.getHeadMaterial(rootTag).getAllTraits().contains(this)) {
				lvl++;
			}
		}
		//if(Util.hasEmbossment(rootTag, this)) {
		if(Util.hasEmbossment(rootTag, this, false)) {
			lvl++;
		}
		return lvl;
	}
	
	@Override
	public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
		if(playersWithChest.contains(playerKey(player)) && source.isFireDamage()) {
			if(evt.isCancelable()) {
				evt.setCanceled(true);
			}
			//else {
				//newDamage = 0.0f;
			//}
			newDamage = 0.0f;
			damage = 0.0f;
			if(player.isBurning()) {
				player.extinguish();
			}
			return 0.0f;
		}
		return super.onHurt(armor, player, source, damage, newDamage, evt);
	}
	
	/*@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		if(playersWithChest.contains(playerKey(player)) && source.isFireDamage()) {
			if(evt.isCancelable()) {
				evt.setCanceled(true);
			}
			//else {
				//newDamage = 0.0f;
			//}
			newDamage = 0.0f;
			damage = 0.0f;
			if(player.isBurning()) {
				player.extinguish();
			}
			return 0.0f;
		}
		return super.onDamaged(armor, player, source, damage, newDamage, evt);
	}*/
	
	@SubscribeEvent
	public void updatePlayerAbilityStatus(LivingUpdateEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			boolean hasHead = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD));
			boolean hasChest = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
			boolean hasLegs = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
			boolean hasFeet = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.FEET));
			boolean fullInfi = hasHead && hasChest && hasLegs && hasFeet;
			String key = playerKey(player);
			if(Config.cosmicFullArmorFlight) {
				//String key = playerKey(player);
				if (fullInfinityPlayers.contains(key)) {
					if (fullInfi) {
						player.capabilities.allowFlying = true;
					} else {
						if (!player.capabilities.isCreativeMode) {
							player.capabilities.allowFlying = false;
							player.capabilities.isFlying = false;
						}
						fullInfinityPlayers.remove(key);
					}
				} else if (fullInfi) {
					fullInfinityPlayers.add(key);
					//player.capabilities.allowFlying = true;
				}
			}
			if (playersWithHat.contains(key)) {
				if (hasHead) {
					player.setAir(300);
					PotionEffect nv = player.getActivePotionEffect(MobEffects.NIGHT_VISION);
					if(nv == null) {
						nv = new PotionEffect(MobEffects.NIGHT_VISION, 300,  0, false, false);
						player.addPotionEffect(nv);
					}
					boolean error = false;
					try {
						Field duration = null;
						try {
							duration = PotionEffect.class.getDeclaredField("duration");
						} catch (Exception e) {
							try {
								duration = PotionEffect.class.getDeclaredField("field_76460_b");
								//duration = PotionEffect.class.getDeclaredField("b");
							} catch (Exception e1) {
								if(errorMessageDelay < 1) {
									ToMeTinkers.logger.catching(e);
									ToMeTinkers.logger.catching(e1);
								}
								//ToMeTinkers.logger.catching(e);
								//ToMeTinkers.logger.catching(e1);
								error = true;
							}
						}
						//Field duration = PotionEffect.class.getDeclaredField("duration");
						duration.setAccessible(true);
						duration.set(nv, 300);
					} catch (Exception e) {
						//ToMeTinkers.logger.catching(e);
						if(!error) {
							if(errorMessageDelay < 1) {
								ToMeTinkers.logger.catching(e);
							}
							//ToMeTinkers.logger.catching(e);
							error = true;
						}
					}
					if(error) {
						if(errorMessageDelay < 1) {
							ToMeTinkers.logger.warn("A unknown Error occured while trying to change Potion duration.");
							ToMeTinkers.logger.warn("This doesn't means any feature fails completly but the NightVision effect is flashing most of the time.");
							errorMessageDelay = 100;
						}
						else {
							errorMessageDelay--;
						}
						//ToMeTinkers.logger.warn("A unknown Error occured while trying to change Potion duration.");
						//ToMeTinkers.logger.warn("This doesn't means any feature is completly failing but it is less failsafe.");
						//ToMeTinkers.logger.warn("This doesn't means any feature is completly failing but the NightVision effect is less (lag)failsafe.");
						//ToMeTinkers.logger.warn("This doesn't means any feature is completly failing but the NightVision effect is flashing most of the time.");
						//ToMeTinkers.logger.warn("This doesn't means any feature fails completly but the NightVision effect is flashing most of the time.");
					}
					//ToMeTinkers.logger.warn("A unknown Error occured while trying to change Potion duration.");
					//ToMeTinkers.logger.warn("This doesn't means any feature is completly failing but it is less failsafe.");
					//ToMeTinkers.logger.warn("This doesn't means any feature is completly failing but the NightVision effect is less (lag)failsafe.");
					//ToMeTinkers.logger.warn("This doesn't means any feature is completly failing but the NightVision effect is flashing most of the time.");
					//ToMeTinkers.logger.warn("This doesn't means any feature fails completly but the NightVision effect is flashing most of the time.");
				}
				else {
					playersWithHat.remove(key);
				}
			} else if (hasHead) {
				playersWithHat.add(key);
			}
			if (playersWithChest.contains(key)) {
				if (hasChest) {
					if(player.isBurning()) {
						player.extinguish();
					}
				}
				else {
					playersWithChest.remove(key);
				}
			} else if (hasChest) {
				playersWithChest.add(key);
			}
			if (playersWithLeg.contains(key)) {
				if (hasLegs) {
					boolean flying = player.capabilities.isFlying;
					boolean swimming = player.isInsideOfMaterial(net.minecraft.block.material.Material.WATER) || player.isInWater();
					if (player.onGround || flying || swimming) {
						boolean sneaking = player.isSneaking();
						float speed = 0.15f 
							* (flying ? 1.1f : 1.0f) 
							* (sneaking ? 0.1f : 1.0f);
						if (player.moveForward > 0f) {
							player.moveRelative(0f, 0f, 1f, speed);
						} else if (player.moveForward < 0f) {
							player.moveRelative(0f, 0f, 1f, -speed * 0.3f);
						}
						if (player.moveStrafing != 0f) {
							player.moveRelative(1f, 0f, 0f, speed * 0.5f * Math.signum(player.moveStrafing));
						}
					}
				} else {
					playersWithLeg.remove(key);
				}
			} else if (hasLegs) {
				playersWithLeg.add(key);
			}
			if (playersWithFoot.contains(key)) {
				if (!hasFeet) {
					playersWithFoot.remove(key);
				}
			} else if (hasFeet) {
				playersWithFoot.add(key);
			}
		}
		//if(Config.cosmicFullArmorFlight) {
			//if (event.getEntityLiving() instanceof EntityPlayer) {
				//EntityPlayer player = (EntityPlayer)event.getEntityLiving();
				//boolean hasHead = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD));
				//boolean hasChest = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
				//boolean hasLegs = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
				//boolean hasFeet = hasCosmic(player.getItemStackFromSlot(EntityEquipmentSlot.FEET));
				//int infinityParts = 0;
				//for(ItemStack stack:event.getEntityLiving().getArmorInventoryList()) {
				//for(ItemStack stack:player.getArmorInventoryList()) {
					//if(stack.getItem() instanceof ArmorCore) {
						//if(TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), identifier)) {
						//if(TinkerUtil.hasModifier(TagUtil.getTagSafe(stack), identifier)) {
							//TagUtil.getTraitsTagList(stack);
							//infinityParts++;
						//}
					//}
				//}
				//boolean fullInfi = false;
				//boolean fullInfi = hasHead && hasChest && hasLegs && hasFeet;
				//if(infinityParts > 3) {
					//fullInfi = true;
				//}
				//String key = player.getGameProfile().getName() + ":" + player.world.isRemote;
				//String key = playerKey(player);
				//if (fullInfinityPlayers.contains(key)) {
					//if (fullInfi) {
						//player.capabilities.allowFlying = true;
					//} else {
						//if (!player.capabilities.isCreativeMode) {
							//player.capabilities.allowFlying = false;
							//player.capabilities.isFlying = false;
						//}
						//fullInfinityPlayers.remove(key);
					//}
				//} else if (fullInfi) {
					//fullInfinityPlayers.add(key);
					//player.capabilities.allowFlying = true;
				//}
			//}
		//}
	}
	
	@SubscribeEvent
	public void jumpBoost(LivingJumpEvent event) {
	//@Override
	//public void onJumping(ItemStack armor, EntityPlayer player, LivingEvent.LivingJumpEvent evt) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
		//if (evt.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			String key = playerKey(player);
			if (playersWithFoot.contains(key)) {
				player.motionY += 0.4f;
			}
		}
		//String key = playerKey(player);
		//if (playersWithFoot.contains(key)) {
			//player.motionY += 0.4f;
		//}
	}
	
	//@SubscribeEvent
	//public void noFallDamage(LivingFallEvent event) {
	@Override
	public void onFalling(ItemStack armor, EntityPlayer player, LivingFallEvent evt) {
		//if (event.getEntityLiving() instanceof EntityPlayer) {
			//EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			//String key = playerKey(player);
			//if (playersWithFoot.contains(key)) {
				//if(event.isCancelable()) {
					//event.setCanceled(true);
				//}
			//}
		//}
		String key = playerKey(player);
		if (playersWithFoot.contains(key)) {
			if(evt.isCancelable()) {
				evt.setCanceled(true);
			}
		}
	}
	
	private boolean hasCosmic(ItemStack stack) {
		if(stack.getItem() instanceof ArmorCore) {
			if(TinkerUtil.hasTrait(TagUtil.getTagSafe(stack), identifier)) {
				return true;
			}
		}
		return false;
	}
	
	public static String playerKey(EntityPlayer player) {
		return player.getGameProfile().getName() + ":" + player.world.isRemote;
	}
	
}