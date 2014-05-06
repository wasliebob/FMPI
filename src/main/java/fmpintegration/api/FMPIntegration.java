package fmpintegration.api;

import java.util.ArrayList;

import net.minecraft.block.Block;

public class FMPIntegration {
	public static void banBlock(Block block){
		banned.add(block);
	}
	
	public static void unbanBlock(Block block){
		banned.remove(block);
	}
	
	public static boolean isBanned(Block block){
		return banned.contains(block);
	}
	
	public static ArrayList<Block> banned = new ArrayList<Block>();
}