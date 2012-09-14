package advtech.mods.DarkShadows;

import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.EnumHelper;
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
	static EnumToolMaterial obby = EnumHelper.addToolMaterial("obby", 3, 2000, 9F, 6, 14);
	public static Block oreObby = new ObbyOre(201, 16);
	public static Block streamFurnaceIdle = new BlockStreamFurnace(202, 0, false);
	public static Block streamFurnaceActive= new BlockStreamFurnace(203, 0, true);
	public static Item obbySword = new ItemObbySword(509, obby);
	public static Item obbyItem = new ObbyItems(508);
	public static Item portalMaker = new ItemPortalMaker(510);
	public static Item obbyHammer = new ItemObbyHammer(511);
	
	@Instance
	public static DarkShadow instance;
	
	@SidedProxy(clientSide = "advtech.mods.DarkShadows.gui.ClientProxy", serverSide = "advtech.mods.DarkShadows.gui.CommonProxy")
	public static CommonProxy proxy;
	private GuiHandler guiHandler = new GuiHandler();
	
	@Init
	public void init(FMLInitializationEvent event) {
		//Furnace Code
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		GameRegistry.registerTileEntity(TileEntityStreamFurnace.class, "tileEntityStreamFurnace");
		GameRegistry.registerBlock(streamFurnaceIdle);
		LanguageRegistry.addName(streamFurnaceIdle, "Forge");
		//Ore Code
		GameRegistry.registerBlock(oreObby);
		LanguageRegistry.addName(oreObby, "Obsidian Ore");
		//Item Codes
		LanguageRegistry.addName(portalMaker,"Portal Maker");
		LanguageRegistry.addName(new ItemStack(obbyItem,1,0),"Obsidian Ingot");
		LanguageRegistry.addName(new ItemStack(obbyItem,1,1),"Obsidian Rivet");
		LanguageRegistry.addName(new ItemStack(obbyItem,1,2),"Obsidian Plate");
		//Tools Codes
		LanguageRegistry.addName(obbySword, "Obsidian Sword");
		LanguageRegistry.addName(obbyHammer, "Obsidian Hammer");
		//Texture File
		MinecraftForgeClient.preloadTexture("/advtech/mods/DarkShadows/terrain.png");
		//World Generator Code
		GameRegistry.registerWorldGenerator(new ShadowWorldGenerator());
		//Recipes
		GameRegistry.addRecipe(new ItemStack(portalMaker), new Object[] {
			"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Item.redstone
		});
		
		GameRegistry.addRecipe(new ItemStack(streamFurnaceIdle), new Object[] {
			"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Block.stoneOvenIdle
		});
		
		GameRegistry.addRecipe(new ItemStack(obbySword), new Object [] {
			"X  "," X ", "  Z", Character.valueOf('X'), new ItemStack(DarkShadow.obbyItem,1,0), Character.valueOf('Z'),Item.stick
		});
		
		GameRegistry.addRecipe(new ItemStack(obbyHammer), new Object [] {
			"XXX","XXX", " Z ", Character.valueOf('X'), new ItemStack(DarkShadow.obbyItem,1,0), Character.valueOf('Z'),Item.stick
		});
		GameRegistry.addRecipe(new ItemStack(obbyItem,1,0), new Object[] {"###", "###", "###", '#', new ItemStack(DarkShadow.obbyItem,1,1)});
		
		GameRegistry.addRecipe(new ItemStack(DarkShadow.obbyItem,9,1), new Object[] {"#", '#', new ItemStack(DarkShadow.obbyItem,1,0)});
		
		GameRegistry.addRecipe(new ItemStack(DarkShadow.obbyItem,1,2), new Object[]{" * ","###","###", Character.valueOf('*'),DarkShadow.obbyHammer, Character.valueOf('#'), new ItemStack(DarkShadow.obbyItem,1,0)});
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

