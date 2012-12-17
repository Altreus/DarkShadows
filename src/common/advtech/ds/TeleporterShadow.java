package advtech.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.*;

public class TeleporterShadow extends Teleporter {
	
	private final WorldServer field_85192_a;

    /** A private Random() function in Teleporter */
    private final Random random;
    private final LongHashMap field_85191_c = new LongHashMap();
    private final List field_85190_d = new ArrayList();
    
    

	public TeleporterShadow(WorldServer world) {
		super(world);
		field_85192_a = world; 
		random = new Random(world.getSeed());
	}
	
	public void placeInPortal(Entity user, double par2, double par4, double par6, float par8)
    {
        if (this.field_85192_a.provider.dimensionId != 1)
        {
            if (!this.placeInExistingPortal(user, par2, par4, par6, par8))
            {
                this.func_85188_a(user);
                this.placeInExistingPortal(user, par2, par4, par6, par8);
            }
        }
        else
        {
            int entityX = MathHelper.floor_double(user.posX);
            int entityY = MathHelper.floor_double(user.posY) - 1;
            int entityZ = MathHelper.floor_double(user.posZ);
            byte var12 = 1;
            byte var13 = 0;

            for (int var14 = -2; var14 <= 2; ++var14)
            {
                for (int var15 = -2; var15 <= 2; ++var15)
                {
                    for (int var16 = -1; var16 < 3; ++var16)
                    {
                        int chunkCoords = entityX + var15 * var12 + var14 * var13;
                        int var18 = entityY + var16;
                        int var19 = entityZ + var15 * var13 - var14 * var12;
                        boolean var20 = var16 < 0;
                        this.field_85192_a.setBlockWithNotify(chunkCoords, var18, var19, var20 ? Block.glowStone.blockID : 0);
                    }
                }
            }

            user.setLocationAndAngles((double)entityX, (double)entityY, (double)entityZ, user.rotationYaw, 0.0F);
            user.motionX = user.motionY = user.motionZ = 0.0D;
        }
    }
	
	public boolean placeInExistingPortal(Entity user, double par2, double par4, double par6, float par8)
    {
        short searchDist = 128;
        double var10 = -1.0D;
        int portalX = 0;
        int portalY = 0;
        int portalZ = 0;
        int userX = MathHelper.floor_double(user.posX);
        int userZ = MathHelper.floor_double(user.posZ);
        long chunkCoords = ChunkCoordIntPair.chunkXZ2Int(userX, userZ);
        boolean newPortal = true;
        double var27;
        int curX;

        if (this.field_85191_c.containsItem(chunkCoords))
        {
            PortalPosition portalLoc = (PortalPosition)this.field_85191_c.getValueByKey(chunkCoords);
            var10 = 0.0D;
            portalX = portalLoc.posX;
            portalY = portalLoc.posY;
            portalZ = portalLoc.posZ;
            portalLoc.field_85087_d = this.field_85192_a.getTotalWorldTime();
            newPortal = false;
        }
        else
        {
            for (curX = userX - searchDist; curX <= userX + searchDist; ++curX)
            {
                double distX = (double)curX + 0.5D - user.posX;

                for (int curZ = userZ - searchDist; curZ <= userZ + searchDist; ++curZ)
                {
                    double distZ = (double)curZ + 0.5D - user.posZ;

                    for (int curY = this.field_85192_a.getActualHeight() - 1; curY >= 0; --curY)
                    {
                        if (this.field_85192_a.getBlockId(curX, curY, curZ) == Block.portal.blockID)
                        {
                            while (this.field_85192_a.getBlockId(curX, curY - 1, curZ) == Block.portal.blockID)
                            {
                                --curY;
                            }

                            var27 = (double)curY + 0.5D - user.posY;
                            double sqDist = distX * distX + var27 * var27 + distZ * distZ;

                            if (var10 < 0.0D || sqDist < var10)
                            {
                                var10 = sqDist;
                                portalX = curX;
                                portalY = curY;
                                portalZ = curZ;
                            }
                        }
                    }
                }
            }
        }

        if (var10 >= 0.0D)
        {
            if (newPortal)
            {
                this.field_85191_c.add(chunkCoords, new PortalPosition(this, portalX, portalY, portalZ, this.field_85192_a.getTotalWorldTime()));
                this.field_85190_d.add(Long.valueOf(chunkCoords));
            }

            double var49 = (double)portalX + 0.5D;
            double var25 = (double)portalY + 0.5D;
            var27 = (double)portalZ + 0.5D;
            int var50 = -1;

            if (this.field_85192_a.getBlockId(portalX - 1, portalY, portalZ) == Block.portal.blockID)
            {
                var50 = 2;
            }

            if (this.field_85192_a.getBlockId(portalX + 1, portalY, portalZ) == Block.portal.blockID)
            {
                var50 = 0;
            }

            if (this.field_85192_a.getBlockId(portalX, portalY, portalZ - 1) == Block.portal.blockID)
            {
                var50 = 3;
            }

            if (this.field_85192_a.getBlockId(portalX, portalY, portalZ + 1) == Block.portal.blockID)
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
                boolean var36 = !this.field_85192_a.isAirBlock(portalX + var32 + var34, portalY, portalZ + var33 + var35) || !this.field_85192_a.isAirBlock(portalX + var32 + var34, portalY + 1, portalZ + var33 + var35);
                boolean var37 = !this.field_85192_a.isAirBlock(portalX + var32, portalY, portalZ + var33) || !this.field_85192_a.isAirBlock(portalX + var32, portalY + 1, portalZ + var33);

                if (var36 && var37)
                {
                    var50 = Direction.footInvisibleFaceRemap[var50];
                    var31 = Direction.footInvisibleFaceRemap[var31];
                    var32 = Direction.offsetX[var50];
                    var33 = Direction.offsetZ[var50];
                    var34 = Direction.offsetX[var31];
                    var35 = Direction.offsetZ[var31];
                    curX = portalX - var34;
                    var49 -= (double)var34;
                    int var22 = portalZ - var35;
                    var27 -= (double)var35;
                    var36 = !this.field_85192_a.isAirBlock(curX + var32 + var34, portalY, var22 + var33 + var35) || !this.field_85192_a.isAirBlock(curX + var32 + var34, portalY + 1, var22 + var33 + var35);
                    var37 = !this.field_85192_a.isAirBlock(curX + var32, portalY, var22 + var33) || !this.field_85192_a.isAirBlock(curX + var32, portalY + 1, var22 + var33);
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
}
