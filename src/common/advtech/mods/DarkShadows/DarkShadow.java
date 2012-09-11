package advtech.mods.DarkShadows;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Advtech_DarkShadow", name = "Dark Shadow", version = "0.0.1 for MC 1.3.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class DarkShadow {
	public static Block oreObby = new ObbyOre(201,167);
	public static Item portalMaker = new ItemPortalMaker(507);

	@Init
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerBlock(oreObby);
		LanguageRegistry.addName(oreObby, "Obsidian Ore");
		LanguageRegistry.addName(portalMaker,"Portal Maker");
		
		GameRegistry.registerWorldGenerator(new ShadowWorldGenerator());
		
		GameRegistry.addRecipe(new ItemStack(portalMaker), new Object[] {
			"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Item.redstone
		});
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		//
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		//
	}
}

