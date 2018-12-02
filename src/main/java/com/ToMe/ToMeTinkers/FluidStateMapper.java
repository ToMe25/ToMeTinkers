package com.ToMe.ToMeTinkers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {
	
	private final ModelResourceLocation location;
	
	public FluidStateMapper(Fluid fluid) {
		//this.location = new ModelResourceLocation("fluid_block", fluid.getName());
		this.location = new ModelResourceLocation(new ResourceLocation(ToMeTinkers.MODID, "fluid_block"), fluid.getName());
	}
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		return location;
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		return location;
	}
	
}