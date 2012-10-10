package advtech.mods.DarkShadows;

import java.util.logging.Logger;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;

import advtech.mods.DarkShadows.gui.BlockStreamFurnace;
import advtech.mods.DarkShadows.gui.ClientPacketHandler;
import advtech.mods.DarkShadows.gui.CommonProxy;
import advtech.mods.DarkShadows.gui.GuiHandler;
import advtech.mods.DarkShadows.gui.ServerPacketHandler;
import advtech.mods.DarkShadows.gui.TileEntityStreamFurnace;

import cpw.mods.fml.client.registry.RenderingRegistry;

import cpw.mods.fml.common.FMLLog;
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
	
	public static Block oreObby;// = new ObbyOre(201, 16);
	public static Block streamFurnaceIdle; //= new BlockStreamFurnace(202, 8, false);
	public static Block streamFurnaceActive;//= new BlockStreamFurnace(203, 8, true);
	
	public static Item obbySword;
	public static Item portalMaker;
	public static Item obbyHammer;
	public static Item helmetObby;
	public static Item chestplateObby;
	public static Item leggingObby;
	public static Item bootObby;
	public static Item obbyHammer1;
	public static Item obbyItems; // You only need 1 for all 3 items.
	
	public static int obbySwordID;
	public static int portalMakerID;
	public static int obbyHammerID;
	public static int helmetObbyID;
	public static int chestplateObbyID;
	public static int leggingObbyID;
	public static int bootObbyID;
	public static int obbyItemsID;
	
	public static int oreObbyID;
	public static int streamFurnaceIdleID;
	public static int streamFurnaceActiveID;
	
	
	public static Logger dsLog = Logger.getLogger("DarkShadow");
	
	@Instance
	public static DarkShadow instance;
	
	@SidedProxy(clientSide = "advtech.mods.DarkShadows.gui.ClientProxy", serverSide = "advtech.mods.DarkShadows.gui.CommonProxy")
	public static CommonProxy proxy;
	private GuiHandler guiHandler = new GuiHandler();
	static EnumToolMaterial obbyToolMaterial = EnumHelper.addToolMaterial("obby", 3, 2000, 9F, 6, 14);
	static EnumArmorMaterial obbyArmorMaterial = EnumHelper.addArmorMaterial("OBBY",40,new int[]{10,20,16,14},20);
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		dsLog.setParent(FMLLog.getLogger());
		
		dsLog.info("Loading/Creating Config");
		loadConfig(event);
	}
	@Init
	public void init(FMLInitializationEvent event) {
		//Furnace Code
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		
		GameRegistry.registerTileEntity(TileEntityStreamFurnace.class, "tileEntityStreamFurnace");
		
		proxy.registerRenderThings();
		
		dsLog.info("Adding Blocks");
		addBlocks();
		
		dsLog.info("Adding Items");
		addItems();
		
		dsLog.info("Adding Recipes");
		
		addRecipes();
		//Ore Code

		//Item Codes
		//Texture File
		MinecraftForgeClient.preloadTexture("/advtech/mods/DarkShadows/terrain.png");
		//World Generator Code
		GameRegistry.registerWorldGenerator(new ShadowWorldGenerator());
		//Recipes
		
	}
	
	
	
	public void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		//Items
		obbySwordID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Obsidian Sword", 509).getInt(509);
		portalMakerID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Portal Maker",510).getInt(510);
		obbyHammerID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Obsidian Hammer", 511).getInt(511);
		helmetObbyID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Obsidian Helmet", 512).getInt(512);
		chestplateObbyID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Obidian Chestplate", 513).getInt(513);
		leggingObbyID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Obsidian Pants", 514).getInt(514);
		bootObbyID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Obsidian Boots", 515).getInt(515);
		obbyItemsID = config.getOrCreateIntProperty(config.CATEGORY_ITEM,"Obsidian Ingot", 516).getInt(516); // Only one item id needed as all 3 items are this ID, just with metadata

		//Blocks
		oreObbyID = config.getOrCreateIntProperty(config.CATEGORY_BLOCK,"Obsidian Ore", 201).getInt(201);
		streamFurnaceIdleID = config.getOrCreateIntProperty(config.CATEGORY_BLOCK,"Forge", 202).getInt(202);
		streamFurnaceActiveID = config.getOrCreateIntProperty(config.CATEGORY_BLOCK,"Forge2", 203).getInt(203);
		
		config.save();
	}
	public void addBlocks(){
		oreObby = new ObbyOre(oreObbyID, 0).setHardness(50F).setResistance(2000.0F);
		GameRegistry.registerBlock(oreObby);
		LanguageRegistry.addName(oreObby, "Obsidian Ore");
		
		streamFurnaceIdle = new BlockStreamFurnace(streamFurnaceIdleID, 8, false).setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(streamFurnaceIdle);
		LanguageRegistry.addName(streamFurnaceIdle, "Forge");
		
		streamFurnaceActive = new BlockStreamFurnace(streamFurnaceActiveID, 8, false);
		GameRegistry.registerBlock(streamFurnaceActive);
		LanguageRegistry.addName(streamFurnaceActive, "Forge");
	}
	
	public void addItems(){
		helmetObby = new ObbyArmor(helmetObbyID, obbyArmorMaterial, RenderingRegistry.addNewArmourRendererPrefix("Obby"), 0).setIconIndex(15).setItemName("Obsidian Helmet");
		LanguageRegistry.addName(helmetObby, "Obsidian Helmet");
		chestplateObby = new ObbyArmor(chestplateObbyID, obbyArmorMaterial, RenderingRegistry.addNewArmourRendererPrefix("Obby"), 1).setIconIndex((16*1) + 15).setItemName("Obsidian Chestplate");
		LanguageRegistry.addName(chestplateObby, "Obsidian Chest Plate");
		leggingObby = new ObbyArmor(leggingObbyID, obbyArmorMaterial,RenderingRegistry.addNewArmourRendererPrefix("Obby"), 2).setIconIndex((16*2)+15).setItemName("Obsidian Leggings");
		LanguageRegistry.addName(leggingObby, "Obsidian Leggings");
		bootObby = new ObbyArmor(bootObbyID, obbyArmorMaterial,RenderingRegistry.addNewArmourRendererPrefix("Obby"), 3).setIconIndex((16 * 3)+15).setItemName("Obsidian Boots");
		LanguageRegistry.addName(bootObby, "Obsidian Boot");
		portalMaker = new PortalMaker(portalMakerID).setIconIndex(0).setItemName("Portal Maker");
		LanguageRegistry.addName(portalMaker, "Insta-Portal");
		
		obbyItems = new ObbyItems(obbyItemsID).setIconIndex(0).setItemName("obbyItems");
		LanguageRegistry.addName(new ItemStack(obbyItems, 1, 0), "Obsidian Ingot");
		LanguageRegistry.addName(new ItemStack(obbyItems, 1, 1), "Obsidian Rivet");
		LanguageRegistry.addName(new ItemStack(obbyItems, 1, 2), "Obsidian Plate");
		obbyHammer1 = new ObbyHammer(obbyHammerID).setIconIndex(0).setItemName("Obsidian Hammer");
		
		obbyHammer = new ObbyHammer(obbyHammerID).setIconIndex(0).setItemName("Obsidian Hammer").setContainerItem(obbyHammer1);
		LanguageRegistry.addName(obbyHammer, "Hammer of Obby");
		obbySword = new ObbySword(obbySwordID, obbyToolMaterial).setIconIndex(15).setItemName("Obsidian Sword");
		LanguageRegistry.addName(obbySword, "Creeper's Bane");
		
	}
	public void addRecipes(){
		GameRegistry.addRecipe(new ItemStack(portalMaker, 1), new Object[] {"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Item.redstone});
		GameRegistry.addRecipe(new ItemStack(streamFurnaceIdle, 1), new Object[] {"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Block.stoneOvenIdle});
		GameRegistry.addRecipe(new ItemStack(obbySword,1), new Object [] {"X  "," X ", "  Z", Character.valueOf('X'), new ItemStack(obbyItems, 1, 0), Character.valueOf('Z'),Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbyHammer,1), new Object [] {"XXX","XXX", " Z ", Character.valueOf('X'), new ItemStack(obbyItems, 1, 0), Character.valueOf('Z'),Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbyItems, 1, 0), new Object[] {"###", "###", "###", '#', new ItemStack(obbyItems, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(obbyItems, 9, 1), new Object[] {"#", '#', new ItemStack(obbyItems, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(obbyItems, 1, 2), new Object[]{" * ","###","###", Character.valueOf('*'),obbyHammer, Character.valueOf('#'), new ItemStack(obbyItems, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(DarkShadow.chestplateObby,1), new Object[]{" # ","A#A"," # ",Character.valueOf('#'), new ItemStack(obbyItems, 1, 2), Character.valueOf('A'), new ItemStack(obbyItems, 1, 1)});
		
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		//
	}
}

