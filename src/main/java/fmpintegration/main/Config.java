package fmpintegration.main;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fmpintegration.helpers.BannedListHelper;

public class Config {

	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		generateBanned(config);
		fullCube = config.get(Configuration.CATEGORY_GENERAL, "fullCube", false, "Set this to true to only register blocks which are a full (1x1x1) cube").getBoolean(fullCube);
		config.save();
	}
	public static boolean fullCube;
	
	public static void generateBanned(Configuration config){
		Property bannedBlocks = config.get(Configuration.CATEGORY_GENERAL, "bannedBlocks", "web,fire");
		bannedBlocks.comment = "List of banned blocks";
	    String[] bannedArray = bannedBlocks.getString().split(",");
	    if(bannedArray != null && bannedArray.length > 0){
	    	for(String s : bannedArray){
	    		Block block = Block.getBlockFromName(s);
	    		if(block != null)
	    			BannedListHelper.bannedBlocks.add(block);
	    	}
	    }
	}
}