package fmpintegration.main.init.integration;

import cpw.mods.fml.common.Loader;
import fmpintegration.main.FI;

public class IntergrationLoader {
	public static void init(){
		if(Loader.isModLoaded("IC2")){
			IC2.init();
			printMessage("Industrial Craft 2");}
	}
	
	public static void printMessage(String mod){
		System.out.println("[" + FI.alias + "]" + "You have " + mod + " installed, adding intergration");
	}
}