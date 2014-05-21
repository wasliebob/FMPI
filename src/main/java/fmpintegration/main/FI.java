package fmpintegration.main;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fmpintegration.helpers.FileHelper;
import fmpintegration.proxies.CommonProxy;

@Mod(modid = "FMPIntegration", name = "FMPIntegration", version = "1.0" ,dependencies = "required-after:ForgeMultipart")
public class FI {
    @SidedProxy(clientSide = "fmpintegration.proxies.ClientProxy", serverSide = "fmpintegration.proxies.CommonProxy")
    public static CommonProxy proxy;
 
    @Instance("FMPIntegration")
    public static FI instance;
    public static double version = 1.00;
    public static String modName = "FMPIntegration";
    public static String alias = "FI";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	Config.loadConfig(event);
		proxy.load();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	if(Config.lists){
    		FileHelper.createMainFolder();
    		FileHelper.createBaseFileOutsideFolder("mods", "txt", FileHelper.generateList(Loader.instance().getModList()));
    	}
    	
    	Micropart.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evt){}
}