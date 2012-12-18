package advtech.ds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.src.*;

public class TeleporterShadow extends Teleporter {
	
	public final WorldServer worldObj;

    public final Random random;
    public final LongHashMap portals = new LongHashMap();
    public final List portalChunks = new ArrayList();

    public TeleporterShadow(WorldServer world)
    {
    	super(world);
        worldObj = world;
        random = new Random(world.getSeed());
    }
    
    public void placeInPortal(Entity user, double par2, double par4, double par6, float par8)
    {
        if (!placeInExistingPortal(user, par2, par4, par6, par8))
        {
            makePortal(user);
            placeInExistingPortal(user, par2, par4, par6, par8);
        }
    }
    
    public boolean placeInExistingPortal(Entity user, double par2, double par4, double par6, float par8)
    {
        short var9 = 128;
        double var10 = -1.0D;
        int var12 = 0;
        int var13 = 0;
        int var14 = 0;
        int var15 = MathHelper.floor_double(user.posX);
        int var16 = MathHelper.floor_double(user.posZ);
        long var17 = ChunkCoordIntPair.chunkXZ2Int(var15, var16);
        boolean var19 = true;
        double var27;
        int var48;

        if (portals.containsItem(var17))
        {
            PortalPosition var20 = (PortalPosition)portals.getValueByKey(var17);
            var10 = 0.0D;
            var12 = var20.posX;
            var13 = var20.posY;
            var14 = var20.posZ;
            var20.field_85087_d = worldObj.getTotalWorldTime();
            var19 = false;
        }
        else
        {
            for (var48 = var15 - var9; var48 <= var15 + var9; ++var48)
            {
                double var21 = (double)var48 + 0.5D - user.posX;

                for (int var23 = var16 - var9; var23 <= var16 + var9; ++var23)
                {
                    double var24 = (double)var23 + 0.5D - user.posZ;

                    for (int var26 = worldObj.getActualHeight() - 1; var26 >= 0; --var26)
                    {
                        if (worldObj.getBlockId(var48, var26, var23) == 0)
                        {
                            while (worldObj.getBlockId(var48, var26 - 1, var23) == 0)
                            {
                                --var26;
                            }

                            var27 = (double)var26 + 0.5D - user.posY;
                            double var29 = var21 * var21 + var27 * var27 + var24 * var24;

                            if (var10 < 0.0D || var29 < var10)
                            {
                                var10 = var29;
                                var12 = var48;
                                var13 = var26;
                                var14 = var23;
                            }
                        }
                    }
                }
            }
        }

        if (var10 >= 0.0D)
        {
            if (var19)
            {
                portals.add(var17, new PortalPosition(this, var12, var13, var14, worldObj.getTotalWorldTime()));
                portalChunks.add(Long.valueOf(var17));
            }

            double var49 = (double)var12 + 0.5D;
            double var25 = (double)var13 + 0.5D;
            var27 = (double)var14 + 0.5D;
            int var50 = -1;

            if (worldObj.getBlockId(var12 - 1, var13, var14) == 0)
            {
                var50 = 2;
            }

            if (worldObj.getBlockId(var12 + 1, var13, var14) == 0)
            {
                var50 = 0;
            }

            if (worldObj.getBlockId(var12, var13, var14 - 1) == 0)
            {
                var50 = 3;
            }

            if (worldObj.getBlockId(var12, var13, var14 + 1) == 0)
            {
                var50 = 1;
            }

            int var30 = user.func_82148_at();

            if (var50 > -1)
            {
                int var31 = Direction.field_71578_g[var50];
                int var32 = Direction.offsetX[var50];
                int var33 = Direction.offsetZ[var50];
                int var34 = Direction.offsetX[var31];
                int var35 = Direction.offsetZ[var31];
                boolean var36 = !worldObj.isAirBlock(var12 + var32 + var34, var13, var14 + var33 + var35) || !worldObj.isAirBlock(var12 + var32 + var34, var13 + 1, var14 + var33 + var35);
                boolean var37 = !worldObj.isAirBlock(var12 + var32, var13, var14 + var33) || !worldObj.isAirBlock(var12 + var32, var13 + 1, var14 + var33);

                if (var36 && var37)
                {
                    var50 = Direction.footInvisibleFaceRemap[var50];
                    var31 = Direction.footInvisibleFaceRemap[var31];
                    var32 = Direction.offsetX[var50];
                    var33 = Direction.offsetZ[var50];
                    var34 = Direction.offsetX[var31];
                    var35 = Direction.offsetZ[var31];
                    var48 = var12 - var34;
                    var49 -= (double)var34;
                    int var22 = var14 - var35;
                    var27 -= (double)var35;
                    var36 = !worldObj.isAirBlock(var48 + var32 + var34, var13, var22 + var33 + var35) || !worldObj.isAirBlock(var48 + var32 + var34, var13 + 1, var22 + var33 + var35);
                    var37 = !worldObj.isAirBlock(var48 + var32, var13, var22 + var33) || !worldObj.isAirBlock(var48 + var32, var13 + 1, var22 + var33);
                }

                float var38 = 0.5F;
                float var39 = 0.5F;

                if (!var36 && var37)
                {
                    var38 = 1.0F;
                }
                else if (var36 && !var37)
                {
                    var38 = 0.0F;
                }
                else if (var36 && var37)
                {
                    var39 = 0.0F;
                }

                var49 += (double)((float)var34 * var38 + var39 * (float)var32);
                var27 += (double)((float)var35 * var38 + var39 * (float)var33);
                float var40 = 0.0F;
                float var41 = 0.0F;
                float var42 = 0.0F;
                float var43 = 0.0F;

                if (var50 == var30)
                {
                    var40 = 1.0F;
                    var41 = 1.0F;
                }
                else if (var50 == Direction.footInvisibleFaceRemap[var30])
                {
                    var40 = -1.0F;
                    var41 = -1.0F;
                }
                else if (var50 == Direction.enderEyeMetaToDirection[var30])
                {
                    var42 = 1.0F;
                    var43 = -1.0F;
                }
                else
                {
                    var42 = -1.0F;
                    var43 = 1.0F;
                }

                double var44 = user.motionX;
                double var46 = user.motionZ;
                user.motionX = var44 * (double)var40 + var46 * (double)var43;
                user.motionZ = var44 * (double)var42 + var46 * (double)var41;
                user.rotationYaw = par8 - (float)(var30 * 90) + (float)(var50 * 90);
            }
            else
            {
                user.motionX = user.motionY = user.motionZ = 0.0D;
            }

            user.setLocationAndAngles(var49, var25, var27, user.rotationYaw, user.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean makePortal(Entity user)
    {
        byte var2 = 16;
        double var3 = -1.0D;
        int userX = MathHelper.floor_double(user.posX);
        int userY = MathHelper.floor_double(user.posY);
        int userZ = MathHelper.floor_double(user.posZ);
        int var8 = userX;
        int var9 = userY;
        int var10 = userZ;
        int var11 = 0;
        int var12 = random.nextInt(4);
        int var13;
        double var14;
        double var17;
        int var16;
        int var19;
        int var21;
        int var20;
        int var23;
        int var22;
        int var25;
        int var24;
        int var27;
        int var26;
        double var31;
        double var32;

        for (var13 = userX - var2; var13 <= userX + var2; ++var13)
        {
            var14 = (double)var13 + 0.5D - user.posX;

            for (var16 = userZ - var2; var16 <= userZ + var2; ++var16)
            {
                var17 = (double)var16 + 0.5D - user.posZ;
                label274:

                for (var19 = worldObj.getActualHeight() - 1; var19 >= 0; --var19)
                {
                    if (worldObj.isAirBlock(var13, var19, var16))
                    {
                        while (var19 > 0 && worldObj.isAirBlock(var13, var19 - 1, var16))
                        {
                            --var19;
                        }

                        for (var20 = var12; var20 < var12 + 4; ++var20)
                        {
                            var21 = var20 % 2;
                            var22 = 1 - var21;

                            if (var20 % 4 >= 2)
                            {
                                var21 = -var21;
                                var22 = -var22;
                            }

                            for (var23 = 0; var23 < 3; ++var23)
                            {
                                for (var24 = 0; var24 < 4; ++var24)
                                {
                                    for (var25 = -1; var25 < 4; ++var25)
                                    {
                                        var26 = var13 + (var24 - 1) * var21 + var23 * var22;
                                        var27 = var19 + var25;
                                        int var28 = var16 + (var24 - 1) * var22 - var23 * var21;

                                        if (var25 < 0 && !worldObj.getBlockMaterial(var26, var27, var28).isSolid() || var25 >= 0 && !worldObj.isAirBlock(var26, var27, var28))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            var32 = (double)var19 + 0.5D - user.posY;
                            var31 = var14 * var14 + var32 * var32 + var17 * var17;

                            if (var3 < 0.0D || var31 < var3)
                            {
                                var3 = var31;
                                var8 = var13;
                                var9 = var19;
                                var10 = var16;
                                var11 = var20 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (var3 < 0.0D)
        {
            for (var13 = userX - var2; var13 <= userX + var2; ++var13)
            {
                var14 = (double)var13 + 0.5D - user.posX;

                for (var16 = userZ - var2; var16 <= userZ + var2; ++var16)
                {
                    var17 = (double)var16 + 0.5D - user.posZ;
                    label222:

                    for (var19 = worldObj.getActualHeight() - 1; var19 >= 0; --var19)
                    {
                        if (worldObj.isAirBlock(var13, var19, var16))
                        {
                            while (var19 > 0 && worldObj.isAirBlock(var13, var19 - 1, var16))
                            {
                                --var19;
                            }

                            for (var20 = var12; var20 < var12 + 2; ++var20)
                            {
                                var21 = var20 % 2;
                                var22 = 1 - var21;

                                for (var23 = 0; var23 < 4; ++var23)
                                {
                                    for (var24 = -1; var24 < 4; ++var24)
                                    {
                                        var25 = var13 + (var23 - 1) * var21;
                                        var26 = var19 + var24;
                                        var27 = var16 + (var23 - 1) * var22;

                                        if (var24 < 0 && !worldObj.getBlockMaterial(var25, var26, var27).isSolid() || var24 >= 0 && !worldObj.isAirBlock(var25, var26, var27))
                                        {
                                            continue label222;
                                        }
                                    }
                                }

                                var32 = (double)var19 + 0.5D - user.posY;
                                var31 = var14 * var14 + var32 * var32 + var17 * var17;

                                if (var3 < 0.0D || var31 < var3)
                                {
                                    var3 = var31;
                                    var8 = var13;
                                    var9 = var19;
                                    var10 = var16;
                                    var11 = var20 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int var29 = var8;
        int var15 = var9;
        var16 = var10;
        int var30 = var11 % 2;
        int var18 = 1 - var30;

        if (var11 % 4 >= 2)
        {
            var30 = -var30;
            var18 = -var18;
        }

        boolean var33;

        if (var3 < 0.0D)
        {
            if (var9 < 70)
            {
                var9 = 70;
            }

            if (var9 > worldObj.getActualHeight() - 10)
            {
                var9 = worldObj.getActualHeight() - 10;
            }

            var15 = var9;

            for (var19 = -1; var19 <= 1; ++var19)
            {
                for (var20 = 1; var20 < 3; ++var20)
                {
                    for (var21 = -1; var21 < 3; ++var21)
                    {
                        var22 = var29 + (var20 - 1) * var30 + var19 * var18;
                        var23 = var15 + var21;
                        var24 = var16 + (var20 - 1) * var18 - var19 * var30;
                        var33 = var21 < 0;
                        worldObj.setBlockWithNotify(var22, var23, var24, var33 ? DarkShadows.shadowStone.blockID : 0);
                    }
                }
            }
        }

        for (var19 = 0; var19 < 4; ++var19)
        {
            worldObj.editingBlocks = true;

            for (var20 = 0; var20 < 4; ++var20)
            {
                for (var21 = -1; var21 < 4; ++var21)
                {
                    var22 = var29 + (var20 - 1) * var30;
                    var23 = var15 + var21;
                    var24 = var16 + (var20 - 1) * var18;
                    var33 = var20 == 0 || var20 == 3 || var21 == -1 || var21 == 3;
                    worldObj.setBlockWithNotify(var22, var23, var24, var33 ? DarkShadows.shadowStone.blockID : 0);
                }
            }

            worldObj.editingBlocks = false;

            for (var20 = 0; var20 < 4; ++var20)
            {
                for (var21 = -1; var21 < 4; ++var21)
                {
                    var22 = var29 + (var20 - 1) * var30;
                    var23 = var15 + var21;
                    var24 = var16 + (var20 - 1) * var18;
                    worldObj.notifyBlocksOfNeighborChange(var22, var23, var24, worldObj.getBlockId(var22, var23, var24));
                }
            }
        }

        return true;
    }

    public void removePortal(long par1)
    {
        if (par1 % 100L == 0L)
        {
            Iterator var3 = portalChunks.iterator();
            long var4 = par1 - 600L;

            while (var3.hasNext())
            {
                Long var6 = (Long)var3.next();
                PortalPosition var7 = (PortalPosition)portals.getValueByKey(var6.longValue());

                if (var7 == null || var7.field_85087_d < var4)
                {
                    var3.remove();
                    portals.remove(var6.longValue());
                }
            }
        }
    }
}