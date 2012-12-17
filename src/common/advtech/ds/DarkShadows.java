package advtech.ds;

import java.util.logging.Logger;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Teleporter;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import advtech.ds.block.BlockStreamFurnace;
import advtech.ds.block.ObbyOre;
import advtech.ds.core.packet.ClientPacketHandler;
import advtech.ds.core.packet.ServerPacketHandler;
import advtech.ds.core.proxy.CommonProxy;
import advtech.ds.gui.GuiHandler;
import advtech.ds.item.ObbyArm;
import advtech.ds.item.ObbyArmor;
import advtech.ds.item.ObbyAxe;
import advtech.ds.item.ObbyHammer;
import advtech.ds.item.ObbyHoe;
import advtech.ds.item.ObbyIngot;
import advtech.ds.item.ObbyPickaxe;
import advtech.ds.item.ObbyPlate;
import advtech.ds.item.ObbyRivet;
import advtech.ds.item.ObbyShovel;
import advtech.ds.item.ObbySword;
import advtech.ds.item.PortalMaker;
import advtech.ds.item.SmokeScreen;
import advtech.ds.lib.BuildInfo;
import advtech.ds.mobs.EntityEnderNinja;
import advtech.ds.mobs.RenderEnderNinja;
import advtech.ds.tile.TileEntityStreamFurnace;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = BuildInfo.modid, name = BuildInfo.modname, version = BuildInfo.modversion)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"DarkShadow"}, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = 
@SidedPacketHandler(channels = {"DarkShadow"}, packetHandler = ServerPacketHandler.class))
public class DarkShadows {
	public static Block oreObby;
	public static Block streamFurnaceIdle; 
	public static Block streamFurnaceActive;
		
	public static Item obbySword;
	public static Item portalMaker;
	public static Item obbyHammer;
	public static Item helmetObby;
	public static Item chestplateObby;
	public static Item leggingObby;
	public static Item bootObby;
	public static Item obbyIngot;
	public static Item obbyPlate;
	public static Item obbyArm;
	public static Item obbyRivet;
	public static Item obbyAxe;
	public static Item obbyHoe;
	public static Item obbyShovel;
	public static Item obbyPickaxe;
	public static Item smokeScreen;
	
	public static int obbySwordID;
	public static int portalMakerID;
	public static int obbyHammerID;
	public static int helmetObbyID;
	public static int chestplateObbyID;
	public static int leggingObbyID;
	public static int bootObbyID;
	public static int obbyIngotID;
	public static int obbyPlateID;
	public static int obbyArmID;
	public static int obbyRivetID;	
	public static int obbyAxeID;
	public static int obbyHoeID;
	public static int obbyShovelID;
	public static int obbyPickaxeID;
	public static int smokeScreenID;
	
	public static int oreObbyID;
	public static int streamFurnaceIdleID;
	public static int streamFurnaceActiveID;
	
	public static Teleporter teleporterShadow;
		
	
	public static Logger dsLog = Logger.getLogger("DarkShadow");
	
	@Instance
	public static DarkShadows instance;
	
	//@SidedProxy(clientSide = "advtech.mods.DarkShadows.gui.ClientProxy", serverSide = "advtech.mods.DarkShadows.gui.CommonProxy")
	public static CommonProxy proxy;
	private GuiHandler guiHandler = new GuiHandler();
	public static EnumToolMaterial obbyToolMaterial = EnumHelper.addToolMaterial("obby", 3, 2000, 9F, 6, 14);
	public static EnumArmorMaterial obbyArmorMaterial = EnumHelper.addArmorMaterial("OBBY",40,new int[]{10,20,16,14},20);
	
	
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
		
		//proxy.registerRenderThings();
		
		dsLog.info("Adding Blocks");
		addBlocks();
		
		dsLog.info("Adding Items");
		addItems();
		
		dsLog.info("Adding Recipes");
		
		addRecipes();
		//Ore Code

		//Item Codes
		//Texture File
		//MinecraftForgeClient.preloadTexture("/advtech/ds/resources/item.png");
		MinecraftForgeClient.preloadTexture("/advtech/ds/resources/terrain.png");
		//World Generator Code
		//GameRegistry.registerWorldGenerator(new ShadowWorldGenerator());
		//Dimensions
		//DimensionManager.registerDimension(2, 2);
		//DimensionManager.registerProviderType(2, WorldProviderShadow.class, true);
		//teleporterShadow = new Teleporter(WorldProvider.getProviderForDimension(2).worldObj);
		// 1 sec finding the right world object
		//add dimensionShadow?
		
		//EnderNinja
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderNinja.class, new RenderEnderNinja());
		EntityRegistry.registerGlobalEntityID(EntityEnderNinja.class, "enderNinja", EntityRegistry.findGlobalUniqueEntityId(), 0xFF0000, 0x000000);
		LanguageRegistry.instance().addStringLocalization("entity.enderNinja.name", "EnderNinja");
		
	}
	
	
	
	public void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		//Items

		obbySwordID = config.getItem(config.CATEGORY_ITEM,"Obsidian Sword", 509).getInt(509);
		portalMakerID = config.getItem(config.CATEGORY_ITEM,"Portal Maker",510).getInt(510);
		obbyHammerID = config.getItem(config.CATEGORY_ITEM,"Obsidian Hammer", 511).getInt(511);
		helmetObbyID = config.getItem(config.CATEGORY_ITEM,"Obsidian Helmet", 512).getInt(512);
		chestplateObbyID = config.getItem(config.CATEGORY_ITEM,"Obidian Chestplate", 513).getInt(513);
		leggingObbyID = config.getItem(config.CATEGORY_ITEM,"Obsidian Pants", 514).getInt(514);
		bootObbyID = config.getItem(config.CATEGORY_ITEM,"Obsidian Boots", 515).getInt(515);
		obbyAxeID = config.getItem(config.CATEGORY_ITEM, "Obsidian Axe", 516).getInt(516);
		obbyHoeID = config.getItem(config.CATEGORY_ITEM, "Obsidian Hoe", 517).getInt(517);
		obbyShovelID = config.getItem(config.CATEGORY_ITEM, "Obsidian Shovel", 518).getInt(518);
		obbyPickaxeID = config.getItem(config.CATEGORY_ITEM, "Obsidian Pickaxe", 519).getInt(519);
		smokeScreenID = config.getItem(config.CATEGORY_ITEM, "Smoke Screen", 520).getInt(520);
		obbyIngotID = config.getItem(config.CATEGORY_ITEM,"Obsidian Ingot", 521).getInt(521);
		obbyPlateID = config.getItem(config.CATEGORY_ITEM, "Obsidian Platemetal", 522).getInt(522);
		obbyArmID = config.getItem(config.CATEGORY_ITEM, "Obsidian Arm", 523).getInt(523);
		obbyRivetID = config.getItem(config.CATEGORY_ITEM,"Obsidian Rivet", 524).getInt(524);

		//Blocks
		oreObbyID = config.get(config.CATEGORY_BLOCK,"Obsidian Ore", 201).getInt(201);
		streamFurnaceIdleID = config.get(config.CATEGORY_BLOCK,"Forge", 202).getInt(202);
		streamFurnaceActiveID = config.get(config.CATEGORY_BLOCK,"Forge2", 203).getInt(203);
		
		
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
		helmetObby = new ObbyArmor(helmetObbyID, obbyArmorMaterial, RenderingRegistry.addNewArmourRendererPrefix("Obby"), 0).setIconIndex(2).setItemName("Obsidian Helmet");
		LanguageRegistry.addName(helmetObby, "Obsidian Helmet");
		chestplateObby = new ObbyArmor(chestplateObbyID, obbyArmorMaterial, RenderingRegistry.addNewArmourRendererPrefix("Obby"), 1).setIconIndex(4).setItemName("Obsidian Chestplate");
		LanguageRegistry.addName(chestplateObby, "Obsidian Chest Plate");
		leggingObby = new ObbyArmor(leggingObbyID, obbyArmorMaterial,RenderingRegistry.addNewArmourRendererPrefix("Obby"), 2).setIconIndex(3).setItemName("Obsidian Leggings");
		LanguageRegistry.addName(leggingObby, "Obsidian Leggings");
		bootObby = new ObbyArmor(bootObbyID, obbyArmorMaterial,RenderingRegistry.addNewArmourRendererPrefix("Obby"), 3).setIconIndex(1).setItemName("Obsidian Boots");
		LanguageRegistry.addName(bootObby, "Obsidian Boot");
		portalMaker = new PortalMaker(portalMakerID).setIconIndex(7).setItemName("Portal Maker");
		LanguageRegistry.addName(portalMaker, "Insta-Portal");		
		obbyIngot = new ObbyIngot(obbyIngotID).setIconIndex(0).setItemName("obbyIngots");
		LanguageRegistry.addName(obbyIngot, "Obsidian Ingot");
		obbyRivet = new ObbyRivet(obbyRivetID).setIconIndex(9).setItemName("Obsidian Rivers");
		LanguageRegistry.addName(obbyRivet,"Obsidian Rivet");
		obbyPlate = new ObbyPlate(obbyPlateID).setIconIndex(0).setItemName("Obsidian Platemetal");
		LanguageRegistry.addName(obbyPlate,"Obsidian Plate");
		obbyArm = new ObbyArm(obbyArmID).setIconIndex(13).setItemName("Obsidian Arm");
		LanguageRegistry.addName(obbyArm, "Obsidian Plate Arm");
		obbyHammer = new ObbyHammer(obbyHammerID).setIconIndex(11).setItemName("Obsidian Hammer");
		LanguageRegistry.addName(obbyHammer, "Hammer of Obby");
		obbyHammer.setContainerItem(obbyHammer);
		obbySword = new ObbySword(obbySwordID, obbyToolMaterial).setIconIndex(6).setItemName("Obsidian Sword");
		LanguageRegistry.addName(obbySword, "Creeper's Bane");
		obbyAxe = new ObbyAxe(obbyAxeID, obbyToolMaterial).setIconIndex(10).setItemName("Obsidian Axe");
		LanguageRegistry.addName(obbyAxe,"Shadow Chopper");
		obbyHoe = new ObbyHoe(obbyHoeID, obbyToolMaterial).setIconIndex(12).setItemName("Obsidian Hoe");
		LanguageRegistry.addName(obbyHoe,"Tiller of Shadow");
		obbyShovel = new ObbyShovel(obbyShovelID, obbyToolMaterial).setIconIndex(5).setItemName("Obsidian Shovel");
		LanguageRegistry.addName(obbyShovel, "Shadow Digger");
		obbyPickaxe = new ObbyPickaxe(obbyPickaxeID, obbyToolMaterial).setIconIndex(8).setItemName("Obsidian Pickaxe");
		LanguageRegistry.addName(obbyPickaxe, "Shadow Breaker");
		
		smokeScreen = new SmokeScreen(smokeScreenID).setItemName("Smoke Screen");
		LanguageRegistry.addName(smokeScreen, "Insta-Poof");
		/*
		 * 
		 * Placeholder for future items
		 * Shadow Block/Item to allow for hidden areas
		 * 
		 * 
		 */
	}
	public void addRecipes(){
		GameRegistry.addRecipe(new ItemStack(portalMaker, 1), new Object[] {"XXX", "XAX", "XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('A'), Item.redstone});
		GameRegistry.addRecipe(new ItemStack(obbyPickaxe,1), new Object[]{"XXX"," Z "," Z ", Character.valueOf('X'), obbyIngot, Character.valueOf('Z'),Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbyAxe,1), new Object[]{"XX ","XZ "," Z ", Character.valueOf('X'), obbyIngot, Character.valueOf('Z'),Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbyHoe,1), new Object[]{"XX "," Z ", " Z ", Character.valueOf('X'), obbyIngot, Character.valueOf('Z'),Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbyShovel, 1), new Object[] {" X "," Z "," Z ", Character.valueOf('X'), obbyIngot, Character.valueOf('Z'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbyHammer, 1), new Object[]{"XXX","XZX"," Z ", Character.valueOf('X'), obbyIngot, Character.valueOf('Z'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbySword,1), new Object[]{" X "," X ", " Z ", Character.valueOf('X'), obbyIngot, Character.valueOf('Z'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(obbyRivet, 9), new Object[]{"X",Character.valueOf('X'), obbyIngot});
		GameRegistry.addRecipe(new ItemStack(obbyIngot, 1 ), new Object[]{"XXX","XXX","XXX",Character.valueOf('X'),obbyRivet});
		GameRegistry.addRecipe(new ItemStack(streamFurnaceIdle,1), new Object[]{"XXX","XZX","XXX", Character.valueOf('X'), Block.obsidian, Character.valueOf('Z'), Block.stoneOvenIdle});
		GameRegistry.addRecipe(new ItemStack(obbyPlate,1), new Object[]{" Z ","XXX","XXX",Character.valueOf('Z'),obbyHammer, Character.valueOf('X'),obbyIngot});
		GameRegistry.addRecipe(new ItemStack(obbyArm, 1), new Object[]{" X ","ZXZ"," X ",Character.valueOf('X'), obbyPlate, Character.valueOf('Z'), obbyRivet});
		GameRegistry.addRecipe(new ItemStack(chestplateObby,1), new Object[]{"XZX","XZX"," Z ", Character.valueOf('X'), obbyArm, Character.valueOf('Z'),obbyPlate});
		GameRegistry.addRecipe(new ItemStack(leggingObby, 1), new Object[]{"XXX","X X","X X",Character.valueOf('X'), obbyIngot});
		GameRegistry.addRecipe(new ItemStack(helmetObby, 1), new Object[]{"XXX","X X","  ", Character.valueOf('X'), obbyIngot});
		GameRegistry.addRecipe(new ItemStack(bootObby, 1), new Object[]{"   ","X X","X X", Character.valueOf('X'),obbyIngot});
		
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		//
	}
}
