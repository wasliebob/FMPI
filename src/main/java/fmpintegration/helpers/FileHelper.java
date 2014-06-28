package fmpintegration.helpers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.ModContainer;
import fmpintegration.main.FI;

public class FileHelper {
	
	public static String getSlash()
	{
		if(System.getProperty("os.name") != null)
		{
			String os = System.getProperty("os.name");
			if(os.startsWith("Windows")){
				return "\\";
			}else if(os.startsWith("windows")){
				return "\\";
			}else{
				return "/";
			}
		}else{
			return "\\";
		}
	}
	
	public static void createMainFolder() {
		File file;
		try{
			file = new File(FI.configLocation + getSlash() + "FMPI" + getSlash());
			if(!file.exists())
				file.mkdir();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param fileName
	 * @param extension
	 * @param list
	 */
	public static void createBaseFileOutsideFolder(String fileName, String extension, ArrayList<String> list){
		File file;
		try {
			file = new File(FI.configLocation + getSlash() + "FMPI" + getSlash() + fileName + "." + extension);
			file.createNewFile();
			PrintWriter writer = new PrintWriter(FI.configLocation + getSlash() + "FMPI" + getSlash() + fileName + "." + extension, "UTF-8");
			
			for(int i = 0; i < list.size(); i++)
				if(list.get(i) != null)
					writer.println(list.get(i));

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> generateList(List<ModContainer> list)
	{
		ArrayList<String> al = new ArrayList<String>();
		
		for(ModContainer con : list)
			al.add(con.getModId());
		return al;
	}
}