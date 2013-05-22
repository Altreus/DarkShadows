package advtech.ds.dimension.shadow;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import advtech.ds.DarkenedSouls;

public class BlockShadowStone extends Block {
	public World worldObj;
	public BlockShadowStone(int id) {
		super(id, Material.rock);
		this.setTickRandomly(true);
	}
	@Override
	public void registerIcons(IconRegister index) {
		
	}
	
	
	public void updateTick(World worldObj, int posX, int posY, int posZ, Random rand) {
		((BlockShadowPortal) DarkenedSouls.shadowPortal).tryToCreatePortal(worldObj, posX + 1, posY, posZ);
    	((BlockShadowPortal) DarkenedSouls.shadowPortal).tryToCreatePortal(worldObj, posX - 1, posY, posZ);
    	((BlockShadowPortal) DarkenedSouls.shadowPortal).tryToCreatePortal(worldObj, posX, posY + 1, posZ);
    	((BlockShadowPortal) DarkenedSouls.shadowPortal).tryToCreatePortal(worldObj, posX, posY - 1, posZ);
    	((BlockShadowPortal) DarkenedSouls.shadowPortal).tryToCreatePortal(worldObj, posX, posY, posZ + 1);
    	((BlockShadowPortal) DarkenedSouls.shadowPortal).tryToCreatePortal(worldObj, posX, posY, posZ - 1);
	}
	
	 /*public void travelToDimension(int par1)
	    {
	        if (!this.worldObj.isRemote && !this.isDead)
	        {
	            this.worldObj.theProfiler.startSection("changeDimension");
	            MinecraftServer minecraftserver = MinecraftServer.getServer();
	            int j = this.dimension;
	            WorldServer worldserver = minecraftserver.worldServerForDimension(j);
	            WorldServer worldserver1 = minecraftserver.worldServerForDimension(par1);
	            this.dimension = par1;
	            this.worldObj.removeEntity(this);
	            this.isDead = false;
	            this.worldObj.theProfiler.startSection("reposition");
	            minecraftserver.getConfigurationManager().transferEntityToWorld(this, j, worldserver, worldserver1);
	            this.worldObj.theProfiler.endStartSection("reloading");
	            Entity entity = EntityList.createEntityByName(EntityList.getEntityString(this), worldserver1);

	            if (entity != null)
	            {
	                entity.copyDataFrom(this, true);
	                worldserver1.spawnEntityInWorld(entity);
	            }

	            this.isDead = true;
	            this.worldObj.theProfiler.endSection();
	            worldserver.resetUpdateEntityTick();
	            worldserver1.resetUpdateEntityTick();
	            this.worldObj.theProfiler.endSection();
	        }
	    }*/
}
