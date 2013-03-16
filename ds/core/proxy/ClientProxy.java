package advtech.ds.core.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.Instance;

public class ClientProxy extends CommonProxy
{
@Override
public void registerRenderInformation() 
  {  
	MinecraftForgeClient.preloadTexture("/advtech/ds/resources/terrain.png");
	MinecraftForgeClient.preloadTexture("/advtech/ds/resources/terrain2.png");
  //RenderingRegistry.instance().registerEntityRenderingHandler(EntityTest.class, new RenderCatTest());//TODO--NPE on texture load
  }

// Override any other methods that need to be handled differently client side.

}// End class ClientProxy