package com.ToMe.ToMeTinkers;

import java.util.HashMap;
import java.util.Map;

//import c4.conarm.lib.book.ArmoryBook;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.TinkerBook;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.MaterialRenderInfo.AbstractMaterialRenderInfo;
import slimeknights.tconstruct.library.client.texture.AnimatedColoredTexture;
import slimeknights.tconstruct.library.client.texture.TextureColoredTexture;
import slimeknights.tconstruct.library.client.texture.TinkerTexture;
import slimeknights.tconstruct.library.materials.Material;

public class ClientProxy extends CommonProxy {
	
	//private static BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
	private static Map<Material, String> customRenderInfos = new HashMap<Material, String>();
	
	@Override
	//public void setRenderInfo(Material material, int low, int mid, int high) {
	//public void setRenderInfo(Material material, Block block) {
	public void setRenderInfo(Material material, String resourcelocation) {
		//ToMeTinkers.logger.info("ClientProxy: " + resourcelocation);
		//ToMeTinkers.logger.info("ClientProxy: " + new ModelResourceLocation(resourcelocation).toString());
		//material.setRenderInfo(new MaterialRenderInfo.MultiColor(low, mid, high));
		//material.setRenderInfo(new MaterialRenderInfo.BlockTexture(blockrendererdispatcher.getModelForState(block.getDefaultState()));
		material.setRenderInfo(new MaterialRenderInfo.BlockTexture(new ResourceLocation(resourcelocation)));
		//material.setRenderInfo(new MaterialRenderInfo.AnimatedTexture(resourcelocation));
		//material.setRenderInfo(new MaterialRenderInfo.AnimatedTexture(new ResourceLocation(resourcelocation).toString()));
		//material.setRenderInfo(new MaterialRenderInfo.AnimatedTexture(new ModelResourceLocation(resourcelocation).toString()));
		//customRenderInfos.put(material, resourcelocation);
	}
	
	@Override
	public void registerBookPages() {
		Modifiers.registerBookPages();
		if(Loader.isModLoaded("conarm")) {
			ModifiersArmor.registerBookPages();
		}
		//TinkerBook.INSTANCE.addRepository(new FileRepository(ToMeTinkers.MODID + ":book"));
		//try {
			//ArmoryBook.INSTANCE.addRepository(new FileRepository(ToMeTinkers.MODID + ":book"));
			//ArmoryBook.INSTANCE.addRepository(new FileRepository(ToMeTinkers.MODID + ":bookarmor"));
		//} catch (Exception e) {
		//}
	}
	
	@Override
	public void registerFluidModels() {
		for(Fluid f:MaterialStats.fluids) {
			if(f != null) {
				Block block = f.getBlock();
				if(block != null) {
					FluidStateMapper mapper = new FluidStateMapper(f);
					Item item = Item.getItemFromBlock(block);
					if(item != null && item != Items.AIR) {
						ModelLoader.registerItemVariants(item);
						ModelLoader.setCustomMeshDefinition(item, mapper);
					}
					ModelLoader.setCustomStateMapper(block, mapper);
				}
			}
		}
	}
	
	//@Override
	//public void registerItems(RegistryEvent.Register<Item> e) {
		//super.registerItems(e);
		//for(Material mat:customRenderInfos.keySet()) {
			//ToMeTinkers.logger.info("ClientProxy: " + customRenderInfos.get(mat));
			//mat.setRenderInfo(new MaterialRenderInfo.AnimatedTexture(customRenderInfos.get(mat)));
			//mat.setRenderInfo(new AnimatedTexture(customRenderInfos.get(mat)));
			//mat.setRenderInfo(new AnimatedTexture(new ResourceLocation(customRenderInfos.get(mat))));
			//mat.setRenderInfo(new MaterialRenderInfo.BlockTexture(new ResourceLocation(customRenderInfos.get(mat))));
		//}
	//}
	
	//@Override
	//public void preInit(FMLPreInitializationEvent e) {
		//for(Material mat:customRenderInfos.keySet()) {
			//ToMeTinkers.logger.info("ClientProxy: " + customRenderInfos.get(mat));
			//mat.setRenderInfo(new MaterialRenderInfo.AnimatedTexture(customRenderInfos.get(mat)));
			//mat.setRenderInfo(new MaterialRenderInfo.BlockTexture(new ResourceLocation(customRenderInfos.get(mat))));
		//}
		//super.preInit(e);
	//}
	
	//@Override
	//public void Init(FMLInitializationEvent e) {
		//for(Material mat:customRenderInfos.keySet()) {
			//ToMeTinkers.logger.info("ClientProxy: " + customRenderInfos.get(mat));
			//mat.setRenderInfo(new MaterialRenderInfo.AnimatedTexture(customRenderInfos.get(mat)));
			//mat.setRenderInfo(new MaterialRenderInfo.BlockTexture(new ResourceLocation(customRenderInfos.get(mat))));
		//}
		//super.Init(e);
	//}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
		//for(Material mat:customRenderInfos.keySet()) {
			//ToMeTinkers.logger.info("ClientProxy: " + customRenderInfos.get(mat));
			//mat.setRenderInfo(new MaterialRenderInfo.AnimatedTexture(customRenderInfos.get(mat)));
			//mat.setRenderInfo(new MaterialRenderInfo.BlockTexture(new ResourceLocation(customRenderInfos.get(mat))));
		//}
	}
	
	/*protected class AnimatedTexture extends AbstractMaterialRenderInfo {
		
		protected String texturePath;
		
		public AnimatedTexture(String texturePath) {
			this.texturePath = texturePath;
		}
		
		@Override
		public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
			TextureAtlasSprite blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(texturePath);
			
			if(blockTexture == null) {
				blockTexture = TinkerTexture.loadManually(new ResourceLocation(texturePath));
			}
			
			if(blockTexture == null) {
				blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
			}
			
			TextureColoredTexture sprite = new AnimatedColoredTexture(new ResourceLocation(blockTexture.getIconName()), baseTexture, location);
			return sprite;
		}
	}*/
	
	/*protected class AnimatedTexture extends AbstractMaterialRenderInfo {
		
		protected ResourceLocation texturePath;
		
		public AnimatedTexture(ResourceLocation texturePath) {
			this.texturePath = texturePath;
		}
		
		@Override
		public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
			TextureAtlasSprite blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(texturePath.toString());
			
			if(blockTexture == null) {
				blockTexture = TinkerTexture.loadManually(texturePath);
			}
			
			//AnimatedColoredTexture sprite = new AnimatedColoredTexture(new ResourceLocation(blockTexture.getIconName()), baseTexture, location);
			TextureColoredTexture sprite = new TextureColoredTexture(new ResourceLocation(blockTexture.getIconName()), baseTexture, location);
			sprite.stencil = false;
			return sprite;
		}
	}*/
	
}