package fmpintegration.main;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fmpintegration.api.FMPIntegration;

public class Config {

	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		generateBannedMods(config);
		generateBanned(config);
		fullCube = config.get(Configuration.CATEGORY_GENERAL, "fullCube", true, "Set this to true to only register blocks which are a full (1x1x1) cube").getBoolean(fullCube);
		numbering = config.get(Configuration.CATEGORY_GENERAL, "numbering", false, "Set this to true to register blocks which have a number in them (without this only the block with 1 in it will get registered (this will also make all blocks from mods like Chisel get registered!)").getBoolean(numbering);
		fileLocation = config.get(Configuration.CATEGORY_GENERAL, "fileLocation", e.getModConfigurationDirectory().toString()).getString();
		lists = config.get(Configuration.CATEGORY_GENERAL, "lists", true, "Set this to true to generate a file with all mods for banned blocks list").getBoolean(lists);

		config.save();
	}
	public static boolean fullCube;
	public static boolean numbering;
	public static boolean lists;
	public static String fileLocation;
	
	public static void generateBannedMods(Configuration config){
		Property bannedMods = config.get(Configuration.CATEGORY_GENERAL, "bannedMods", "");
		bannedMods.comment = "List of banned mods";
		if(bannedMods != null){
		String[] bannedArray = bannedMods.getString().split(",");
	    if(bannedArray != null && bannedArray.length > 0){
	    	for(String s : bannedArray){
	    		if(s != null)
	    			FMPIntegration.banMod(s);
	    	}
	    }
		}
	}
	
	public static void generateBanned(Configuration config){
		Property bannedBlocks = config.get(Configuration.CATEGORY_GENERAL, "bannedBlocks", "web,fire");
		bannedBlocks.comment = "List of banned blocks";
	    String[] bannedArray = bannedBlocks.getString().split(",");
	    if(bannedArray != null && bannedArray.length > 0){
	    	for(String s : bannedArray){
	    		Block block = Block.getBlockFromName(s);
	    		if(block != null)
	    			FMPIntegration.banBlock(block);
	    	}
	    }
	}
}