package com.ToMe.ToMeTinkers;

import net.minecraft.launchwrapper.IClassTransformer;
//import net.minecraft.server.MinecraftServer;
//import net.minecraftforge.common.ForgeHooks;
//import net.minecraftforge.common.MinecraftForge;

import java.io.File;
//import java.io.FileOutputStream;
//import java.lang.reflect.Field;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
//import org.objectweb.asm.tree.FieldInsnNode;
//import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
//import org.objectweb.asm.tree.InsnNode;
//import org.objectweb.asm.tree.JumpInsnNode;
//import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ClassTransformer implements IClassTransformer {
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		boolean isObfuscated = !name.equals(transformedName);
		//if(ToMeTinkers.cfg == null) {
			//File conf = new File(".", "config");
			//conf = new File(conf.getPath(), ToMeTinkers.MODID + ".cfg");
			//ToMeTinkers.cfg = new Config(conf);
		//}
		/*if(transformedName.equals("net.minecraft.entity.player.EntityPlayer")) {
		//if(transformedName.equals("net.minecraft.item.Item")) {
			if(ToMeTinkers.cfg == null) {
				File conf = new File(".", "config");
				conf = new File(conf.getPath(), ToMeTinkers.MODID + ".cfg");
				ToMeTinkers.cfg = new Config(conf);
			}
			try {
				if(ToMeTinkers.cfg.boundTweak) {
					//return PatchItemDrop(basicClass, isObfuscated);
					//return PatchItem(basicClass, isObfuscated);
					return PatchEntityPlayer(basicClass, isObfuscated);
				}
				//return PatchItemDrop(basicClass, isObfuscated);
				//return PatchForgeHooks(basicClass, isObfuscated);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else*/ if(transformedName.equals("net.minecraft.item.Item")) {
			if(ToMeTinkers.cfg == null) {
				File conf = new File(".", "config");
				conf = new File(conf.getPath(), ToMeTinkers.MODID + ".cfg");
				ToMeTinkers.cfg = new Config(conf);
			}
			try {
				if(ToMeTinkers.cfg.boundTweak) {
					return PatchItem(basicClass, isObfuscated);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(transformedName.equals("net.minecraft.inventory.Container")) {
			if(ToMeTinkers.cfg == null) {
				File conf = new File(".", "config");
				conf = new File(conf.getPath(), ToMeTinkers.MODID + ".cfg");
				ToMeTinkers.cfg = new Config(conf);
			}
			try {
				if(ToMeTinkers.cfg.boundTweak) {
					return PatchContainer(basicClass, isObfuscated);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return basicClass;
	}
	
	private byte[] PatchItem(byte[] startClass, boolean obfuscated) {
	//private byte[] PatchItemDrop(byte[] startClass, boolean obfuscated) {
	//protected byte[] PatchForgeHooks(byte[] startClass, boolean obfuscated) {
		if(ToMeTinkers.cfg.debug) {
			//ToMeTinkers.logger.info("Started Tweaking EntityPlayer class.");
			ToMeTinkers.logger.info("Started Tweaking Item class.");
		}
		try {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(startClass);
			classReader.accept(classNode, 0);
			boolean changed = false;
			//final String METHOD_NAME = obfuscated ? "a" : "dropItem";
			//final String METHOD_NAME = obfuscated ? "a" : "onDroppedByPlayer";
			final String METHOD_NAME = "onDroppedByPlayer";
			for(MethodNode method:classNode.methods) {
				if(method.name.equals(METHOD_NAME)) {
					AbstractInsnNode targetNode = null;
					for (AbstractInsnNode instruction : method.instructions.toArray()) {
						//if(instruction instanceof MethodInsnNode) {
							//if (instruction.getType() == AbstractInsnNode.METHOD_INSN && ((MethodInsnNode) instruction).name.equals("onPlayerTossEvent")) {
							//if(instruction.getOpcode() == Opcodes.RETURN) {
								//targetNode = instruction;
								//break;
							//}
						//}
						if(instruction.getOpcode() == Opcodes.IRETURN) {
							targetNode = instruction.getPrevious();
							break;
						}
					}
					if (targetNode != null) {
						//String METHOD_DESCRIPTOR = "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;";
						String METHOD_DESCRIPTOR = "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Z";
						InsnList toAdd = new InsnList();
						toAdd.add(new VarInsnNode(Opcodes.ALOAD, 2));
						toAdd.add(new VarInsnNode(Opcodes.ALOAD, 1));
						toAdd.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/ToMe/ToMeTinkers/Hooks", "dropItem", METHOD_DESCRIPTOR));
						method.instructions.insertBefore(targetNode, toAdd);
						method.instructions.remove(targetNode);
						//ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
						//classNode.accept(classWriter);
						if(ToMeTinkers.cfg.debug) {
							ToMeTinkers.logger.info("Tweaked Item.onDroppedByPlayer");
						}
						//return classWriter.toByteArray();
						changed = true;
					}
				}
			}
			//ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			//classNode.accept(classWriter);
			//return classWriter.toByteArray();
			if(changed) {
				ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
				classNode.accept(classWriter);
				return classWriter.toByteArray();
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			ToMeTinkers.logger.catching(e);
		}
		return startClass;
	}
	
	/*private byte[] PatchEntityPlayer(byte[] startClass, boolean obfuscated) {
		if(ToMeTinkers.cfg.debug) {
			ToMeTinkers.logger.info("Started Tweaking EntityPlayer class.");
		}
		try {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(startClass);
			classReader.accept(classNode, 0);
			boolean changed = false;
			final String METHOD_NAME = obfuscated ? "a" : "dropItem";
			for(MethodNode method:classNode.methods) {
				if(method.name.equals(METHOD_NAME)) {
					AbstractInsnNode targetNode = null;
					for (AbstractInsnNode instruction : method.instructions.toArray()) {
						if(instruction instanceof MethodInsnNode) {
							if (instruction.getType() == AbstractInsnNode.METHOD_INSN && ((MethodInsnNode) instruction).name.equals("onPlayerTossEvent")) {
								targetNode = instruction;
								break;
							}
						}
					}
					if (targetNode != null) {
						String METHOD_DESCRIPTOR = "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;";
						InsnList toAdd = new InsnList();
						toAdd.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/ToMe/ToMeTinkers/Hooks", "dropItem", METHOD_DESCRIPTOR));
						method.instructions.insertBefore(targetNode, toAdd);
						method.instructions.remove(targetNode);
						//ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
						//classNode.accept(classWriter);
						if(ToMeTinkers.cfg.debug) {
							ToMeTinkers.logger.info("Tweaked EntityPlayer.dropItem");
						}
						//return classWriter.toByteArray();
						changed = true;
					}
				}
			}
			//ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			//classNode.accept(classWriter);
			//return classWriter.toByteArray();
			if(changed) {
				ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
				classNode.accept(classWriter);
				return classWriter.toByteArray();
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			ToMeTinkers.logger.catching(e);
		}
		return startClass;
	}*/
	
	private byte[] PatchContainer(byte[] startClass, boolean obfuscated) {
		if(ToMeTinkers.cfg.debug) {
			ToMeTinkers.logger.info("Started Tweaking Container class.");
		}
		try {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(startClass);
			classReader.accept(classNode, 0);
			boolean changed = false;
			final String METHOD_NAME = obfuscated ? "a" : "slotClick";
			for(MethodNode method:classNode.methods) {
				if(method.name.equals(METHOD_NAME)) {
					AbstractInsnNode targetNode = null;
					//AbstractInsnNode frameNode = null;
					//AbstractInsnNode labelNode = null;
					//AbstractInsnNode varNode = null;
					//boolean start = false;
					//boolean found = false;
					for (AbstractInsnNode instruction : method.instructions.toArray()) {
						if(instruction instanceof LineNumberNode) {
							//if (((LineNumberNode) instruction).line == 454) {//DEOBF
							//if (((LineNumberNode) instruction).line == 429) {//OBF
							if (((LineNumberNode) instruction).line == (obfuscated ? 429 : 454)) {
								targetNode = instruction;
								//start = true;
								continue;
							}
						}
						//if(!(instruction instanceof MethodInsnNode || instruction instanceof VarInsnNode || instruction instanceof InsnNode)) {
						//if(instruction instanceof FrameNode || instruction instanceof JumpInsnNode || instruction instanceof LabelNode) {
							//if(found && instruction instanceof FrameNode) {
								//frameNode = instruction;
								//labelNode = instruction.getPrevious();
								//varNode = instruction.getPrevious().getPrevious();
								//found = true;
								//continue;
							//}
							//if(start && instruction instanceof FrameNode) {
								//frameNode = instruction;
								//found = true;
								//continue;
							//}
							//else if(start) {
								//System.out.println(instruction);
							//}
							//continue;
						//}
						//if(start && instruction instanceof FrameNode) {
							//targetNode = instruction;
						//}
						//else if(instruction instanceof MethodInsnNode) {
							//if(((MethodInsnNode)instruction).name.equals("dropItem")) {
								//method.instructions.remove(instruction);
								//break;
							//}
							//else {
								//method.instructions.remove(instruction);
							//}
						//}
						//if(start && instruction instanceof MethodInsnNode) {
							//targetNode = instruction;
						//}
						//else if(targetNode != null) {
						if(targetNode != null) {
							//method.instructions.remove(instruction);
							if(instruction instanceof MethodInsnNode) {
								MethodInsnNode methodNode = (MethodInsnNode)instruction;
								//System.out.println(methodNode.name);
								//System.out.println(methodNode.name + ":" + methodNode.owner);
								//if(((MethodInsnNode)instruction).name.equals("dropItem")) {
								//if(((MethodInsnNode)instruction).name.equals(obfuscated ? "a" : "dropItem")) {
								//if(((MethodInsnNode)instruction).name.equals(obfuscated ? "min" : "dropItem")) {
								//if(methodNode.name.equals(obfuscated ? "a" : "dropItem") && methodNode.owner.equals("net/minecraft/entity/player/EntityPlayer")) {
								if(methodNode.name.equals(obfuscated ? "a" : "dropItem") && methodNode.owner.equals(obfuscated ? "aed" : "net/minecraft/entity/player/EntityPlayer")) {
								//if(methodNode.name.equals(obfuscated ? "E" : "dropItem") && methodNode.owner.equals(obfuscated ? "aed" : "net/minecraft/entity/player/EntityPlayer")) {
								//if(methodNode.name.equals(obfuscated ? "E" : "dropItem") && methodNode.owner.equals("net/minecraft/entity/player/EntityPlayer")) {
									method.instructions.remove(instruction);
									break;
								}
							}
							method.instructions.remove(instruction);
							//System.out.println(instruction);
							//System.out.println(instruction + ":" + instruction.getOpcode());
							//if(instruction instanceof VarInsnNode) {
								//System.out.println("" + ((VarInsnNode)instruction).getOpcode() + ";" + ((VarInsnNode)instruction).var);
							//}
							//else if(instruction instanceof FrameNode) {
							//if(instruction instanceof FrameNode) {
							//if(!(instruction instanceof FrameNode)) {
								//String str = "";
								//for(Object s:((FrameNode)instruction).local) {
									//str += s + ";";
								//}
								//System.out.println("" + ((FrameNode)instruction).getOpcode() + ";" + str);
							//}
							//else {
								//method.instructions.remove(instruction);
							//}
						}
						//if(found) {
						//if(found && targetNode == null) {
							//frameNode = instruction;
							//targetNode = instruction;
							//targetNode = instruction.getNext();
							//targetNode = instruction.getNext().getNext().getNext().getNext().getNext();
							//System.out.println(targetNode);
							//System.out.println(((MethodInsnNode)targetNode).name);
						//}
						//if(start && instruction instanceof FrameNode) {
							//targetNode = instruction;
							//found = true;
						//}
					}
					if (targetNode != null) {
						String METHOD_DESCRIPTOR = "(Lnet/minecraft/inventory/Slot;Lnet/minecraft/entity/player/EntityPlayer;I)V";
						//String METHOD_DESCRIPTOR = obfuscated ? "(Lagr;Laed;I)V" : "(Lnet/minecraft/inventory/Slot;Lnet/minecraft/entity/player/EntityPlayer;I)V";
						InsnList toAdd = new InsnList();
						//toAdd.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/inventory/Container", "slot2", "Lnet/minecraft/inventory/Slot;"));
						//toAdd.add(labelNode);
						//toAdd.add(frameNode);
						//toAdd.add(varNode);
						//toAdd.add(new LabelNode());
						//toAdd.add(new FrameNode(Opcodes.F_APPEND, 1, new Object[] {"net/minecraft/inventory/Slot"}, 0, null));
						//toAdd.add(new VarInsnNode(Opcodes.ALOAD, 0));
						//toAdd.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/inventory/Container", "inventorySlots", "Ljava/util/List;"));
						//toAdd.add(new VarInsnNode(Opcodes.ILOAD, 1));
						//toAdd.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/util/List", "get", "(I)Lnet/minecraft/inventory/Slot;"));
						//toAdd.add(new VarInsnNode(Opcodes.ASTORE, 5));
						toAdd.add(new VarInsnNode(Opcodes.ALOAD, 7));
						toAdd.add(new VarInsnNode(Opcodes.ALOAD, 4));
						toAdd.add(new VarInsnNode(Opcodes.ILOAD, 2));
						toAdd.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/ToMe/ToMeTinkers/Hooks", "dropItem", METHOD_DESCRIPTOR));
						toAdd.add(new VarInsnNode(Opcodes.ILOAD, 1));
						//toAdd.add(new VarInsnNode(Opcodes.ALOAD, 3));
						method.instructions.insert(targetNode, toAdd);
						//method.instructions.remove(targetNode);
						if(ToMeTinkers.cfg.debug) {
							ToMeTinkers.logger.info("Tweaked Container.slotClick");
						}
						changed = true;
					}
				}
			}
			//ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			//classNode.accept(classWriter);
			//return classWriter.toByteArray();
			if(changed) {
				ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
				classNode.accept(classWriter);
				return classWriter.toByteArray();
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			ToMeTinkers.logger.catching(e);
		}
		return startClass;
	}
	
}
