package fmpintegration.api;

import java.util.ArrayList;

import net.minecraft.block.Block;

public class FMPIntegration {
	public static void banBlock(Block block){
		bannedBlocks.add(block);
	}
	
	public static void unbanBlock(Block block){
		bannedBlocks.remove(block);
	}
	
	public static boolean isBanned(Block block){
		return bannedBlocks.contains(block);
	}
	
	public static ArrayList<Block> bannedBlocks = new ArrayList<Block>();
	
	public static void banMod(String modid){
		bannedMods.add(modid);
	}
	
	public static void unbanMod(String modid){
		bannedMods.remove(modid);
	}
	
	public static boolean isBanned(String modid){
		return bannedMods.contains(modid);
	}
	public static ArrayList<String> bannedMods = new ArrayList<String>();
	public static ArrayList<Block> alwaysRender = new ArrayList<Block>();
}