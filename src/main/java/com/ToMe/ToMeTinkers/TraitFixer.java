package com.ToMe.ToMeTinkers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.IFixType;
import net.minecraft.util.datafix.IFixableData;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitFixer implements IFixableData {
	
	@Override
	public int getFixVersion() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
		//if(compound.getString("id").startsWith("tconstruct")) {
		if(compound.getString("id").startsWith("tconstruct") || compound.getString("id").startsWith("conarm")) {
			try {
				NBTTagCompound itemTag = compound.getCompoundTag("tag");
				NBTTagCompound toolTag = TagUtil.getToolTag(itemTag);
				if(MaterialStats.Gold.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.Gold.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_magical")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("magical")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_magical");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_magical")) {
										traits.appendTag(new NBTTagString("tometinkers_magical"));
									}
								}
								//traits.appendTag(new NBTTagString("tometinkers_magical"));
								i++;
							}
						}
					}
				}
				if(MaterialStats.Ruby.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.Ruby.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_healthy")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("healthy")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_healthy");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_healthy")) {
										traits.appendTag(new NBTTagString("tometinkers_healthy"));
									}
								}
								i++;
							}
						}
					}
				}
				if(MaterialStats.Diamond.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.Diamond.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_strengthening")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("strengthening")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_strengthening");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_strengthening")) {
										traits.appendTag(new NBTTagString("tometinkers_strengthening"));
									}
								}
								i++;
							}
						}
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_optimized")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("optimized")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_optimized");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_optimized")) {
										traits.appendTag(new NBTTagString("tometinkers_optimized"));
									}
								}
								i++;
							}
						}
					}
				}
				if(MaterialStats.Sapphire.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.Sapphire.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_magically_protected")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("magicallyprotected")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_magically_protected");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_magically_protected")) {
										traits.appendTag(new NBTTagString("tometinkers_magically_protected"));
									}
								}
								i++;
							}
						}
					}
				}
				if(MaterialStats.AvaritiaInfinity.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.AvaritiaInfinity.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "avaritia_cosmic")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("cosmic")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "avaritia_cosmic");
									if(!TinkerUtil.hasTrait(itemTag, "avaritia_cosmic")) {
										traits.appendTag(new NBTTagString("avaritia_cosmic"));
									}
								}
								i++;
							}
						}
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_unbreakable")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("unbreakable")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_unbreakable");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_unbreakable")) {
										traits.appendTag(new NBTTagString("tometinkers_unbreakable"));
									}
								}
								i++;
							}
						}
					}
				}
				if(MaterialStats.AvaritiaNeutronium.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.AvaritiaNeutronium.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_supermassive")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("supermassive")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_supermassive");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_supermassive")) {
										traits.appendTag(new NBTTagString("tometinkers_supermassive"));
									}
								}
								i++;
							}
						}
					}
				}
				if(MaterialStats.AvaritiaCrystalmatrix.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.AvaritiaCrystalmatrix.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_crystallized")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("crystallized")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_crystallized");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_crystallized")) {
										traits.appendTag(new NBTTagString("tometinkers_crystallized"));
									}
								}
								i++;
							}
						}
					}
				}
				if(MaterialStats.Tritanium.registered || MaterialStats.Dilithium.registered) {
					if(TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.Tritanium.mat) || TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(itemTag)).contains(MaterialStats.Dilithium.mat)) {
						if(!TinkerUtil.hasTrait(itemTag, "tometinkers_reinforced")) {
							NBTTagList traits = TagUtil.getTraitsTagList(itemTag);
							NBTTagList modifiers = TagUtil.getModifiersTagList(itemTag);
							int i = 0;
							while(i < modifiers.tagCount()) {
								if(modifiers.getCompoundTagAt(i).getString("identifier").equals("reinforced")) {
									modifiers.getCompoundTagAt(i).setString("identifier", "tometinkers_reinforced");
									if(!TinkerUtil.hasTrait(itemTag, "tometinkers_reinforced")) {
										traits.appendTag(new NBTTagString("tometinkers_reinforced"));
									}
								}
								i++;
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO: ignore exception
			}
		}
		return compound;
	}
	
}