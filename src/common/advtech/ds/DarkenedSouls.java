package advtech.ds;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import advtech.ds.block.BlockStreamFurnace;
import advtech.ds.block.ObbyOre;
import advtech.ds.block.ShadeStone;
import advtech.ds.core.ShadowWorldGenerator;
import advtech.ds.core.packet.ClientPacketHandler;
import advtech.ds.core.packet.ServerPacketHandler;
import advtech.ds.core.proxy.CommonProxy;
import advtech.ds.dimension.shadow.BlockShadowPortal;
import advtech.ds.dimension.shadow.BlockShadowStone;
import advtech.ds.dimension.shadow.TeleporterShadow;
import advtech.ds.dimension.shadow.WorldProviderShadow;
import advtech.ds.gui.GuiHandler;
import advtech.ds.gui.GuiIngameHud;
import advtech.ds.gui.Tab;
import advtech.ds.gui.TickHandler;
import advtech.ds.item.items.GlowStick;
import advtech.ds.item.items.ObbyArm;
import advtech.ds.item.items.ObbyHammer;
import advtech.ds.item.items.ObbyIngot;
import advtech.ds.item.items.ObbyRivet;
import advtech.ds.item.items.PortalMaker;
import advtech.ds.item.items.SmokeScreen;
import advtech.ds.item.light.SwordLight;
import advtech.ds.item.obby.ObbyArmor;
import advtech.ds.item.obby.ObbyAxe;
import advtech.ds.item.obby.ObbyHoe;
import advtech.ds.item.obby.ObbyPickaxe;
import advtech.ds.item.obby.ObbyPlate;
import advtech.ds.item.obby.ObbyShovel;
import advtech.ds.item.obby.ObbySword;
import advtech.ds.item.shadow.ShadeStick;
import advtech.ds.item.shadow.ShadowStoneDust;
import advtech.ds.lib.BuildInfo;
import advtech.ds.mobs.EntityEnderNinja;
import advtech.ds.mobs.RenderEnderNinja;
import advtech.ds.tile.TileEntityStreamFurnace;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;



@Mod(modid = BuildInfo.modid, name = BuildInfo.modname, version = BuildInfo.modversion)
@NetworkMod(clientSideRequired=true, serverSideRequired=false, clientPacketHandlerSpec = @SidedPacketHandler(channels = {"DarkenedSouls" }, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = {"DarkenedSouls" }, packetHandler = ServerPacketHandler.class))
public class DarkenedSouls {
	public static Block oreObby;
	public static Block streamFurnaceIdle; 
	public static Block streamFurnaceActive;
	public static Block ShadeStone;
		
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
	public static Item helmetLight;
	public static Item chestplateLight;
	public static Item leggingLight;
	public static Item bootLight;
	public static Item helmetShadow;
	public static Item chestplateShadow;
	public static Item leggingShadow;
	public static Item bootShadow;
	public static Item SwordLight;
	public static Item SwordShadow;
	public static Item SoulSword;
	public static Item GlowStick;
	public static Item ShadeStick;
	public static Item ShadowStoneDust;
	
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
	public static int helmetLightID;
	public static int chestplateLightID;
	public static int leggingLightID;
	public static int bootLightID;
	public static int helmetShadowID;
	public static int chestplateShadowID;
	public static int leggingShadowID;
	public static int bootShadowID;
	public static int SwordLightID;
	public static int SwordShadowID;
	public static int SoulSwordID;
	public static int GlowStickID;
	public static int ShadeStickID;
	public static int ShadowStoneDustID;
	
	public static int oreObbyID;
	public static int streamFurnaceIdleID;
	public static int streamFurnaceActiveID;
	public static int ShadeStoneID;
	
	//Shadow Portal Stuff
	public static Block shadowStone;
	public static Block shadowPortal;
	public static int shadowDimensionID = 10;
	public static Teleporter shadowTeleporter;
	
	public static Logger dsLog = Logger.getLogger("DarkenedSoul");
	
	@Instance ("DS")
	public static DarkenedSouls instance;
	
	//@SidedProxy(clientSide = "advtech.mods.DarkShadows.gui.ClientProxy", serverSide = "advtech.mods.DarkShadows.gui.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabs DarkenedSouls;
	private GuiHandler guiHandler = new GuiHandler();
	public static EnumToolMaterial obbyToolMaterial = EnumHelper.addToolMaterial("obby", 3, 2000, 9F, 6, 14);
	public static EnumArmorMaterial obbyArmorMaterial = EnumHelper.addArmorMaterial("OBBY",40,new int[]{10,20,16,14},20);
	public static EnumToolMaterial lightToolMaterial = EnumHelper.addToolMaterial("Light", 3, 2000, 9F, 6, 14);
	public static EnumArmorMaterial lightArmorMaterial = EnumHelper.addArmorMaterial("Light", 200, new int[]{20,30,45,20},20);
	public static EnumToolMaterial shadowToolMaterial = EnumHelper.addToolMaterial("Shadow", 3, 2000, 9F, 6, 14);
	public static EnumArmorMaterial shadowArmorMaterial = EnumHelper.addArmorMaterial("Shadow", 100, new int[]{15,20,30,10}, 20);
	
	
	
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
		DarkenedSouls = new Tab(12,"DarkenedSouls");
		
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
		MinecraftForgeClient.preloadTexture("/advtech/ds/resources/terrain2.png");
		//World Generator Code
		GameRegistry.registerWorldGenerator(new ShadowWorldGenerator());
		//Dimensions
		DimensionManager.registerProviderType(shadowDimensionID, WorldProviderShadow.class, true);
		DimensionManager.registerDimension(shadowDimensionID, shadowDimensionID);
		
		//EnderNinja
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderNinja.class, new RenderEnderNinja());
		EntityRegistry.registerGlobalEntityID(EntityEnderNinja.class, "enderNinja", EntityRegistry.findGlobalUniqueEntityId(), 0xFF0000, 0x000000);
		LanguageRegistry.instance().addStringLocalization("entity.enderNinja.name", "EnderNinja");
		
	}
	
	
	
	public void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		//Items

		obbySwordID = config.getItem(config.CATEGORY_ITEM,"Obsidian Sword", 4349).getInt(4349);
		portalMakerID = config.getItem(config.CATEGORY_ITEM,"Portal Maker", 4350).getInt(4350);
		obbyHammerID = config.getItem(config.CATEGORY_ITEM,"Obsidian Hammer", 4351).getInt(4351);
		helmetObbyID = config.getItem(config.CATEGORY_ITEM,"Obsidian Helmet", 4352).getInt(4352);
		chestplateObbyID = config.getItem(config.CATEGORY_ITEM,"Obidian Chestplate", 4353).getInt(4353);
		leggingObbyID = config.getItem(config.CATEGORY_ITEM,"Obsidian Pants", 4354).getInt(4354);
		bootObbyID = config.getItem(config.CATEGORY_ITEM,"Obsidian Boots", 4355).getInt(4355);
		obbyAxeID = config.getItem(config.CATEGORY_ITEM, "Obsidian Axe", 4356).getInt(4356);
		obbyHoeID = config.getItem(config.CATEGORY_ITEM, "Obsidian Hoe", 4357).getInt(4357);
		obbyShovelID = config.getItem(config.CATEGORY_ITEM, "Obsidian Shovel", 4358).getInt(4358);
		obbyPickaxeID = config.getItem(config.CATEGORY_ITEM, "Obsidian Pickaxe", 4359).getInt(4359);
		smokeScreenID = config.getItem(config.CATEGORY_ITEM, "Smoke Screen", 4360).getInt(4360);
		obbyIngotID = config.getItem(config.CATEGORY_ITEM,"Obsidian Ingot", 4361).getInt(4361);
		obbyPlateID = config.getItem(config.CATEGORY_ITEM, "Obsidian Platemetal", 4362).getInt(4362);
		obbyArmID = config.getItem(config.CATEGORY_ITEM, "Obsidian Arm", 4363).getInt(4363);
		obbyRivetID = config.getItem(config.CATEGORY_ITEM,"Obsidian Rivet", 4364).getInt(4364);
		helmetLightID = config.getItem(config.CATEGORY_ITEM, "Light helmet", 4365).getInt(4365);
		chestplateLightID = config.getItem(config.CATEGORY_ITEM, "Light Chestplate", 4366).getInt(4366);
		leggingLightID = config.getItem(config.CATEGORY_ITEM, "Light Pants", 4367).getInt(4367);
		bootLightID = config.getItem(config.CATEGORY_ITEM, "Light Boots", 4368).getInt(4368);
		GlowStickID = config.getItem(config.CATEGORY_ITEM, "GlowStick", 4369).getInt(4369);
		ShadeStickID = config.getItem(config.CATEGORY_ITEM, "ShadowStick", 4370).getInt(4370);
		ShadowStoneDustID = config.getItem(config.CATEGORY_ITEM, "ShadowDust", 4371).getInt(4370);
		

		//Blocks
		oreObbyID = config.get(config.CATEGORY_BLOCK,"Obsidian Ore", 201).getInt(201);
		streamFurnaceIdleID = config.get(config.CATEGORY_BLOCK,"Forge", 202).getInt(202);
		streamFurnaceActiveID = config.get(config.CATEGORY_BLOCK,"ForgeA", 203).getInt(203);
		ShadeStoneID = config.get(config.CATEGORY_BLOCK, "Shade Stone", 204).getInt(204);
		
		
		config.save();
	}
	public void addBlocks(){
		oreObby = new ObbyOre(oreObbyID, 19).setHardness(50F).setResistance(2000.0F);
		GameRegistry.registerBlock(oreObby, "ObsidianOre");
		LanguageRegistry.addName(oreObby, "Obsidian Ore");
		
		streamFurnaceIdle = new BlockStreamFurnace(streamFurnaceIdleID, 15, false).setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(streamFurnaceIdle,"Forge");
		LanguageRegistry.addName(streamFurnaceIdle, "Forge");
		
		ShadeStone = new ShadeStone(ShadeStoneID, 11, Material.glass).setHardness(0.3F).setBlockName("Shade Stone");
		GameRegistry.registerBlock(ShadeStone, "ShadeStone");
		LanguageRegistry.addName(ShadeStone, "Shade Stone");
		
		shadowStone = new BlockShadowStone(250,4).setHardness(50F).setResistance(2000.0F).setBlockName("shadowStone");
		GameRegistry.registerBlock(shadowStone, "shadowStone");
		LanguageRegistry.addName(shadowStone, "Condensed Shadow");
		
		shadowPortal = new BlockShadowPortal(251, 14).setHardness(-1.0F).setStepSound(Block.soundGlassFootstep).setLightValue(0.75F).setBlockName("portal");
		GameRegistry.registerBlock(shadowPortal, "ShadowPortal");
		LanguageRegistry.addName(shadowPortal, "Shadow Portal");
		
		
		
		
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
		obbyPlate = new ObbyPlate(obbyPlateID).setIconIndex(17).setItemName("Obsidian Platemetal");
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
		smokeScreen = new SmokeScreen(smokeScreenID).setItemName("Smoke Screen").setIconIndex(18);
		LanguageRegistry.addName(smokeScreen, "Insta-Poof");
		GlowStick = new GlowStick(GlowStickID).setItemName("Glow Stick").setIconIndex(1);
		LanguageRegistry.addName(GlowStick,"Glow Stick");
		ShadeStick = new ShadeStick(ShadeStickID).setItemName("Shade Stick").setIconIndex(17);
		LanguageRegistry.addName(ShadeStick, "Shade Stick");
		ShadowStoneDust = new ShadowStoneDust(ShadowStoneDustID).setItemName("Shadow Dust").setIconIndex(12);
		LanguageRegistry.addName(ShadowStoneDust, "Shadow Dust");
		SwordLight = new SwordLight(SwordLightID, lightToolMaterial).setItemName("Sword Light");
		LanguageRegistry.addName(SwordLight,"Sword of Forever Light");
		
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
		GameRegistry.addRecipe(new ItemStack(GlowStick, 4), new Object[] {"#", "#", '#', Item.lightStoneDust});
		GameRegistry.addRecipe(new ItemStack(ShadeStick, 4), new Object[] {"#", "#", '#', ShadowStoneDust});
		
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);

	}
	
	@ServerStarted
	public void serverStarted(FMLServerStartedEvent event) {
		FMLClientHandler.instance().getClient().ingameGUI = new GuiIngameHud(FMLClientHandler.instance().getClient());
		shadowTeleporter = new TeleporterShadow(MinecraftServer.getServer().worldServerForDimension(shadowDimensionID));
	}
	
	@ServerStopping
	public void serverStopped(FMLServerStoppingEvent event) {
		shadowTeleporter = null;
	}

}
