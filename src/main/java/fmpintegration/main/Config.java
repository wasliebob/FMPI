package fmpintegration.main;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		fullCube = config.get(Configuration.CATEGORY_GENERAL, "fullCube", false, "Set this to true to only register blocks which are a full (1x1x1) cube").getBoolean(fullCube);
		
		config.save();
	}
	public static boolean fullCube;
}