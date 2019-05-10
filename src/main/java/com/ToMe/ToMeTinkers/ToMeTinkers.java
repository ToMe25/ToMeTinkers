package com.ToMe.ToMeTinkers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent.MeltingRegisterEvent;

@EventBusSubscriber
@Mod(modid = ToMeTinkers.MODID, name = ToMeTinkers.NAME, version = ToMeTinkers.VERSION, acceptedMinecraftVersions = ToMeTinkers.MCVERSION, dependencies = ToMeTinkers.DEPENDENCIES)
public class ToMeTinkers {

	public static final String MODID = "tometinkers";
	public static final String NAME = "ToMeTinkers";
	public static final String VERSION = "1.0";
	public static final String MCVERSION = "[1.12,1.12.2]";
	public static final String DEPENDENCIES = "required-after:mantle;" + "required-after:tconstruct;"
			+ "after:extrautils;" + "after:avaritia;" + "after:matteroverdrive;" + "after:conarm";

	@Instance
	public static ToMeTinkers instance;

	// public static Logger logger = LogManager.getLogger(MODID);
	public static Logger logger = LogManager.getLogger(NAME);

	public static Config cfg;

	@SidedProxy(serverSide = "com.ToMe.ToMeTinkers.CommonProxy", clientSide = "com.ToMe.ToMeTinkers.ClientProxy")
	public static CommonProxy proxy;

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void registerItems(RegistryEvent.Register<Item> e) {
		// public static void registerMaterials(RegistryEvent.Register<Item> e) {
		// logger.info("Item Registration");
		// Materials.registerMaterials();
		proxy.registerItems(e);
	}

	// @SubscribeEvent(priority=EventPriority.HIGH)
	// public static void registerModifiers(RegistryEvent.Register<Item> e) {
	// proxy.registerModifiers(e);
	// }

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> e) {
		// Materials.registerFluids();
		// Materials.registerFluids(e);
		proxy.registerBlocks(e);
	}

	@SubscribeEvent
	public static void onMeltingRegister(MeltingRegisterEvent e) {
		proxy.onMeltingRegister(e);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		// logger.info("Pre Init!");
		// Materials.preInit();
		// Materials.registerMaterials();
		// cfg = new Config(e.getSuggestedConfigurationFile());
		proxy.preInit(e);
	}

	@EventHandler
	public void Init(FMLInitializationEvent e) {
		proxy.Init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		// logger.info("Post Init!");
		// logger.info("" + Materials.glassIntegration.isIntegrated());
		// MaterialStats.Glass.remove();
		// MaterialStats.remove();
		proxy.postInit(e);
	}

}