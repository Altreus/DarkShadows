package advtech.mods.DarkShadows;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import advtech.mods.DarkShadows.gui.BlockStreamFurnace;
import advtech.mods.DarkShadows.gui.ClientPacketHandler;
import advtech.mods.DarkShadows.gui.CommonProxy;
import advtech.mods.DarkShadows.gui.GuiHandler;
import advtech.mods.DarkShadows.gui.ServerPacketHandler;
import advtech.mods.DarkShadows.gui.TileEntityStreamFurnace;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Advtech_DarkShadow", name = "Dark Shadow", version = "0.0.1 for MC 1.3.2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"DarkShadow"}, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = 
@SidedPacketHandler(channels = {"DarkShadow"}, packetHandler = ServerPacketHandler.class))
public class DarkShadow {
	public static Block oreObby = new ObbyOre(201, 167);
	public static Block streamFurnace = new BlockStreamFurnace(202, 62);
	
	public static Item portalMaker = new ItemPortalMaker(507);
	
	@Instance
	public static DarkShadow instance;
	
	@SidedProxy(clientSide = "advtech.mods.DarkShadows.gui.ClientProxy", serverSide = "advtech.mods.DarkShadows.gui.CommonProxy")
	public static CommonProxy proxy;
	private GuiHandler guiHandler = new GuiHandler();
	
	@Init
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		GameRegistry.registerTileEntity(TileEntityStreamFurnace.class, "tileEntityStreamFurnace");
		GameRegistry.registerBlock(streamFurnace);
		LanguageRegistry.addName(streamFurnace, "Stream Furnace");
		
		GameRegistry.registerBlock(oreObby);
		LanguageRegistry.addName(oreObby, "Obsidian Ore");
		LanguageRegistry.addName(portalMaker,"Portal Maker");
		
		GameRegistry.registerWorldGenerator(new ShadowWorldGenerator());
		
		GameRegistry.addRecipe(new ItemStack(portalMaker), new Object[] {
			"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Item.redstone
		});
		
		GameRegistry.addRecipe(new ItemStack(streamFurnace), new Object[] {
			"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Block.stoneOvenIdle
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

