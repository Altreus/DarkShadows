package advtech.ds.block;

import java.util.Random;
import advtech.ds.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class BlockShadowStone extends Block {

	public BlockShadowStone(int id) {
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setTickRandomly(true);
	}
	
	public void updateTick(World worldObj, int posX, int posY, int posZ, Random rand) {
		((BlockShadowPortal) DarkShadows.shadowPortal).tryToCreatePortal(worldObj, posX + 1, posY, posZ);
    	((BlockShadowPortal) DarkShadows.shadowPortal).tryToCreatePortal(worldObj, posX - 1, posY, posZ);
    	((BlockShadowPortal) DarkShadows.shadowPortal).tryToCreatePortal(worldObj, posX, posY + 1, posZ);
    	((BlockShadowPortal) DarkShadows.shadowPortal).tryToCreatePortal(worldObj, posX, posY - 1, posZ);
    	((BlockShadowPortal) DarkShadows.shadowPortal).tryToCreatePortal(worldObj, posX, posY, posZ + 1);
    	((BlockShadowPortal) DarkShadows.shadowPortal).tryToCreatePortal(worldObj, posX, posY, posZ - 1);
	}
	
	public void travelToDimension(Entity user)
    {
		int par1 = DarkShadows.shadowDimensionID;
		
        if (!user.worldObj.isRemote && !user.isDead)
        {
        	user.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer var2 = MinecraftServer.getServer();
            int var3 = user.dimension;
            WorldServer var4 = var2.worldServerForDimension(var3);
            WorldServer var5 = var2.worldServerForDimension(par1);
            user.dimension = par1;
            user.worldObj.setEntityDead(user);
            user.isDead = false;
            user.worldObj.theProfiler.startSection("reposition");
            var2.getConfigurationManager().transferEntityToWorld(user, var3, var4, var5);
            user.worldObj.theProfiler.endStartSection("reloading");
            Entity var6 = EntityList.createEntityByName(EntityList.getEntityString(user), var5);

            if (var6 != null)
            {
                var6.copyDataFrom(user, true);
                var5.spawnEntityInWorld(var6);
            }

            user.isDead = true;
            user.worldObj.theProfiler.endSection();
            var4.resetUpdateEntityTick();
            var5.resetUpdateEntityTick();
            user.worldObj.theProfiler.endSection();
        }
    }
}
