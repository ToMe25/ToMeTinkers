package com.ToMe.ToMeTinkers.traits;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

import com.ToMe.ToMeTinkers.ToMeTinkers;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.network.play.server.SPacketCombatEvent.Event;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.utils.ToolHelper;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TraitWitheringArmor extends AbstractArmorTrait {
	
	static final int INNER_RANGE = 8 * 8;
	static final int OUTER_RANGE = 30 * 30;
	WeakHashMap<EntityPlayerMP, Integer> players = new WeakHashMap<>();
	
	public TraitWitheringArmor() {
		super("xu_withering", 0xFF343434);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void spawnOverride(LivingSpawnEvent.CheckSpawn event) {
		if (players.isEmpty())
			return;
		
		World world = event.getWorld();
		
		if (world.getDifficulty() == EnumDifficulty.PEACEFUL)
			return;
		
		if (world.rand.nextInt(40) != 0)
			return;
		
		EntityLiving entityLiving = (EntityLiving) event.getEntityLiving();
		
		if (!(entityLiving instanceof IMob))
			return;
		
		for (EntityPlayerMP player : players.keySet()) {
			
			double distanceSqToEntity = entityLiving.getDistanceSq(player);
			if (distanceSqToEntity < OUTER_RANGE && distanceSqToEntity > INNER_RANGE) {
				if (!entityLiving.isNotColliding())
					return;
				
				if (world.rand.nextInt(20) != 0)
					return;
				
				IBlockState iblockstate = world.getBlockState((new BlockPos(entityLiving)).down());
				if (!iblockstate.canEntitySpawn(entityLiving))
					return;
				
				entityLiving.setAttackTarget(player);
				
				event.setResult(Event.Result.ALLOW);
				return;
			}
		}
	}
	
	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		//if (!isSelected)
			//return;
		
		if (!world.isRemote && entity instanceof EntityPlayerMP && !(entity instanceof FakePlayer)) {
			if (world.rand.nextInt(4) == 0) {
				BlockPos down = new BlockPos(entity).down();
				IBlockState blockState = world.getBlockState(down);
				if (blockState != Blocks.DIRT.getDefaultState())
					if (blockState.getMaterial() == Material.GRASS || blockState.getMaterial() == Material.GROUND) {
						Item itemDropped = blockState.getBlock().getItemDropped(blockState, random, 0);
						if (itemDropped == Item.getItemFromBlock(Blocks.DIRT)) {
							world.setBlockState(down, Blocks.DIRT.getDefaultState());
						}
					}
			}
			
			players.put((EntityPlayerMP) entity, 10);
		} else if (world.isRemote) {
			try {
				//Class RunnableClient = Class.forName("com.rwtema.extrautils2.RunnableClient");
				Class<?> ClientRunnable = Class.forName("com.rwtema.extrautils2.backend.ClientRunnable");
				Method run = Class.forName("com.rwtema.extrautils2.XUProxy").getDeclaredMethod("run", ClientRunnable);
				Field proxy = Class.forName("com.rwtema.extrautils2.ExtraUtils2").getDeclaredField("proxy");
				Object p = Proxy.newProxyInstance(ClientRunnable.getClassLoader(), new Class<?>[] {ClientRunnable}, new ParticleHandler(entity));
				//System.out.println(p.getClass());
				//for(Class<?> c:p.getClass().getInterfaces()) {
					//System.out.println(c);
				//}
				run.invoke(proxy.get(null), p);
			} catch (Exception e) {
				// TODO: handle exception
				ToMeTinkers.logger.catching(e);
				//if(e.getCause() != null) {
					//ToMeTinkers.logger.catching(e.getCause());
				//}
			}
		}
	}

	@SubscribeEvent
	public void onTick(TickEvent.ServerTickEvent event) {
		if (players.isEmpty()) return;
		Iterator<Map.Entry<EntityPlayerMP, Integer>> iterator = players.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<EntityPlayerMP, Integer> next = iterator.next();
			int value = next.getValue() - 1;
			if (value <= 0) {
				iterator.remove();
			} else {
				next.setValue(value);
			}
		}
	}
	
	@Override
	public int onArmorDamage(ItemStack armor, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot) {
		if (player instanceof FakePlayer) {
			ToolHelper.breakTool(armor, null);
		}
		return super.onArmorDamage(armor, source, damage, newDamage, player, slot);
	}
	
	private class ParticleHandler implements InvocationHandler {
		
		private final Entity entity;
		
		public ParticleHandler(Entity e) {
			entity = e;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if(method.getName().equals("run")) {
				AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
				Random rand = entity.world.rand;
				
				try {
					//ToMeTinkers.logger.info("TraitWitheringArmor: " + "Test!");
					@SuppressWarnings("unchecked")
					Class<? extends Particle> ParticleWither = (Class<? extends Particle>) Class.forName("com.rwtema.extrautils2.particles.ParticleWithering");
					Constructor<? extends Particle> ParticleWithering = ParticleWither.getConstructor(World.class, double.class, double.class, double.class);
					Minecraft.getMinecraft().effectRenderer.addEffect(
							ParticleWithering.newInstance(
									entity.world,
									entityBoundingBox.minX + (entityBoundingBox.maxX - entityBoundingBox.minX) * rand.nextFloat(),
									entityBoundingBox.minY + (entityBoundingBox.maxY - entityBoundingBox.minY) * rand.nextFloat() * 0.2F,
									entityBoundingBox.minZ + (entityBoundingBox.maxZ - entityBoundingBox.minZ) * rand.nextFloat()
							)
					);
				} catch (Exception e) {
					// TODO: handle exception
					ToMeTinkers.logger.catching(e);
				}
			}
			return null;
		}
		
	}
	
}