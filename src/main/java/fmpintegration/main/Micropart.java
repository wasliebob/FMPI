package fmpintegration.main;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fmpintegration.api.FMPIntegration;

public class Micropart {
	public static void init(){
		addExceptions();
		initMicroparts();
	}
	
	public static void addExceptions(){
		FMPIntegration.alwaysRender.add(Blocks.lava);
		FMPIntegration.alwaysRender.add(Blocks.water);
	}
	
	public static void initMicroparts(){
		for(Object o : Block.blockRegistry.getKeys()){
			String s = (String)o;
			Block b = Block.getBlockFromName(s);
			if(MicroMaterialRegistry.getMaterial(s) == null && b != null && Item.getItemFromBlock(b) != null && acceptsFullCube(b) && !isModBanned(b) && !FMPIntegration.isBanned(b)){
				HashSet<String> known = new HashSet<String>();

				for(int meta = 0; meta < 16; meta++){
					String name ;
					try{
						name = new ItemStack(b, 1, meta).getUnlocalizedName();
					}catch(Exception e){
						continue;
					}
					if(name != null && known.add(name) && !containsNumber(name)){
						registerMultipart(b, meta);
					}
				};
			}
		}
	}
	
	public static boolean isModBanned(Block b){
		if(GameRegistry.findUniqueIdentifierFor(b) != null)
			return FMPIntegration.isBanned(GameRegistry.findUniqueIdentifierFor(b).modId);
		else
			return true;
	}
	
	public static boolean acceptsFullCube(Block block){
		if(!Config.fullCube)
			return true;
		else if(Config.fullCube && !FMPIntegration.alwaysRender.contains(block))
			return block.renderAsNormalBlock();
		else
			return true;
	}
	
	public static boolean containsNumber(String name){
		if(!Config.numbering){
			String[] numb = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "11", "0"};
			for(int i = 0; i < numb.length; i++)
				if(name != null && numb != null && name.contains(numb[i]))
					return true;
			return false;
		}
		return false;
	}
	
	public static void registerMetadata(Block block, int maxMeta){
		for (int i = 0; i <= maxMeta; i ++)
			registerMultipart(block, i);
	}

	private static void registerMultipart(Block block, int meta){
		System.out.println("[FMPI] " + " registering multipart for " + block.getLocalizedName());
		MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block, meta), block.getUnlocalizedName() + (meta == 0 ? "" : "_" + meta));
	}
}