package advtech.ds.block;

import advtech.ds.DarkShadows;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

public class BlockShadowStone extends Block {

	public BlockShadowStone(int id) {
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void onEntityWalking(World worldObj, int posX, int posY, int posZ, Entity entity) {
		//if(worldObj.provider.dimensionId != 10) {
			if (tryToCreatePortal(worldObj, posX, posY + 1, posZ)) {
				entity.travelToDimension(DarkShadows.shadowDimensionID);
				System.out.println("Going to shadow!");
			}
		//}
	}
	
	public boolean tryToCreatePortal(World par1World, int posX, int posY, int posZ)
    {
        byte var5 = 0;
        byte var6 = 0;

        if (par1World.getBlockId(posX - 1, posY, posZ) == DarkShadows.shadowStone.blockID || par1World.getBlockId(posX + 1, posY, posZ) == DarkShadows.shadowStone.blockID)
        {
            var5 = 1;
        }

        if (par1World.getBlockId(posX, posY, posZ - 1) == DarkShadows.shadowStone.blockID || par1World.getBlockId(posX, posY, posZ + 1) == DarkShadows.shadowStone.blockID)
        {
            var6 = 1;
        }

        if (var5 == var6)
        {
            return false;
        }
        else
        {
            if (par1World.getBlockId(posX - var5, posY, posZ - var6) == 0)
            {
                posX -= var5;
                posZ -= var6;
            }

            int var7;
            int var8;

            for (var7 = -1; var7 <= 2; ++var7)
            {
                for (var8 = -1; var8 <= 3; ++var8)
                {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;

                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3)
                    {
                        int var10 = par1World.getBlockId(posX + var5 * var7, posY + var8, posZ + var6 * var7);

                        if (var9)
                        {
                            if (var10 != DarkShadows.shadowStone.blockID)
                            {
                                return false;
                            }
                        }
                        else if (var10 != 0 && var10 != 0) // var10 != air
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

	//Code copied from Entity
	/*
	public void travelToDimension(int par1)
    {
        if (!this.worldObj.isRemote && !this.isDead)
        {
            this.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer var2 = MinecraftServer.getServer();
            int var3 = this.dimension;
            WorldServer var4 = var2.worldServerForDimension(var3);
            WorldServer var5 = var2.worldServerForDimension(par1);
            this.dimension = par1;
            this.worldObj.setEntityDead(this);
            this.isDead = false;
            this.worldObj.theProfiler.startSection("reposition");
            var2.getConfigurationManager().transferEntityToWorld(this, var3, var4, var5);
            this.worldObj.theProfiler.endStartSection("reloading");
            Entity var6 = EntityList.createEntityByName(EntityList.getEntityString(this), var5);

            if (var6 != null)
            {
                var6.copyDataFrom(this, true);
                var5.spawnEntityInWorld(var6);
            }

            this.isDead = true;
            this.worldObj.theProfiler.endSection();
            var4.resetUpdateEntityTick();
            var5.resetUpdateEntityTick();
            this.worldObj.theProfiler.endSection();
        }
    }
    */
}
