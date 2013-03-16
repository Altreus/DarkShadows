package advtech.ds.mobs;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderNinja extends EntityMob {

	public static boolean[] carriableBlocks = EntityEnderman.carriableBlocks;
	public int teleportDelay = 0;
    public int field_70826_g = 0;
    public int assaultHits = 0;
    public int assaultDelay = 0;
	
	public EntityEnderNinja(World par1World) {
		super(par1World);
        this.texture = "/mob/enderman.png";
        this.moveSpeed = 0.4F;
        this.setSize(0.6F, 2.9F);
        this.stepHeight = 1.0F;
	}

	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
        this.dataWatcher.addObject(17, new Byte((byte)0));
        this.dataWatcher.addObject(18, new Byte((byte)0));
    }

	@Override
	public int getMaxHealth() {
		return 20;
	}
	
	public void setCarryingData(int par1)
    {
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)(par1 & 255)));
    }
	
    public int getCarryingData()
    {
        return this.dataWatcher.getWatchableObjectByte(17);
    }
	
	public void func_70819_e(boolean par1)
    {
        this.dataWatcher.updateObject(18, Byte.valueOf((byte)(par1 ? 1 : 0)));
    }
	
	public boolean func_70823_r()
    {
        return this.dataWatcher.getWatchableObjectByte(18) > 0;
    }
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		if (par1DamageSource == par1DamageSource.fall) return false;
        if (this.func_85032_ar())
        {
            return false;
        }
        else if (par1DamageSource instanceof EntityDamageSourceIndirect)
        {
            for (int var3 = 0; var3 < 64; ++var3)
            {
            	double prevPosX = posX;
            	double prevPosY = posY;
            	double prevPosZ = posZ;
                if (this.teleportRandomly())
                {
                	EntityDamageSourceIndirect damageSource = (EntityDamageSourceIndirect) par1DamageSource;
                	if (damageSource.getSourceOfDamage() != damageSource.getEntity()) {
                		damageSource.getEntity().setPosition(prevPosX, prevPosY, prevPosZ);
                		damageSource.getEntity().attackEntityFrom(damageSource, par2);
                		entityToAttack = damageSource.getEntity();
                	}
                    return false;
                }
            }

            return false;
        }
        else
        {
            if (par1DamageSource.getEntity() instanceof EntityPlayer)
            {
                this.func_70819_e(true);
            }

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }
	
	public boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer)
    {
        ItemStack var2 = par1EntityPlayer.inventory.armorInventory[3];

        if (var2 != null && var2.itemID == Block.pumpkin.blockID)
        {
            return false;
        }
        else
        {
            Vec3 var3 = par1EntityPlayer.getLook(1.0F).normalize();
            Vec3 var4 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX - par1EntityPlayer.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (par1EntityPlayer.posY + (double)par1EntityPlayer.getEyeHeight()), this.posZ - par1EntityPlayer.posZ);
            double var5 = var4.lengthVector();
            var4 = var4.normalize();
            double var7 = var3.dotProduct(var4);
            return var7 > 1.0D - 0.025D / var5 ? par1EntityPlayer.canEntityBeSeen(this) : false;
        }
    }
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("carried", (short)this.getCarried());
        par1NBTTagCompound.setShort("carriedData", (short)this.getCarryingData());
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setCarried(par1NBTTagCompound.getShort("carried"));
        this.setCarryingData(par1NBTTagCompound.getShort("carriedData"));
    }
	
	public int getAttackStrength(Entity par1Entity)
    {
        return 7;
    }
	
	public void onLivingUpdate()
    {
        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1);
        }

        this.moveSpeed = this.entityToAttack != null ? 6.5F : 0.3F;
        moveSpeed *= 2;
        int var1;

        if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
        {
            int var2;
            int var3;
            int var4;

            if (this.getCarried() == 0)
            {
                if (this.rand.nextInt(20) == 0)
                {
                    var1 = MathHelper.floor_double(this.posX - 2.0D + this.rand.nextDouble() * 4.0D);
                    var2 = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 3.0D);
                    var3 = MathHelper.floor_double(this.posZ - 2.0D + this.rand.nextDouble() * 4.0D);
                    var4 = this.worldObj.getBlockId(var1, var2, var3);

                    if (carriableBlocks[var4])
                    {
                        this.setCarried(this.worldObj.getBlockId(var1, var2, var3));
                        this.setCarryingData(this.worldObj.getBlockMetadata(var1, var2, var3));
                        this.worldObj.setBlockWithNotify(var1, var2, var3, 0);
                    }
                }
            }
            else if (this.rand.nextInt(2000) == 0)
            {
                var1 = MathHelper.floor_double(this.posX - 1.0D + this.rand.nextDouble() * 2.0D);
                var2 = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 2.0D);
                var3 = MathHelper.floor_double(this.posZ - 1.0D + this.rand.nextDouble() * 2.0D);
                var4 = this.worldObj.getBlockId(var1, var2, var3);
                int var5 = this.worldObj.getBlockId(var1, var2 - 1, var3);

                if (var4 == 0 && var5 > 0 && Block.blocksList[var5].renderAsNormalBlock())
                {
                    this.worldObj.setBlockAndMetadataWithNotify(var1, var2, var3, this.getCarried(), this.getCarryingData());
                    this.setCarried(0);
                }
            }
        }

        for (var1 = 0; var1 < 2; ++var1)
        {
            this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float var6 = this.getBrightness(1.0F);

            if (var6 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (var6 - 0.4F) * 2.0F)
            {
                this.entityToAttack = null;
                this.func_70819_e(false);
                this.teleportRandomly();
            }
        }

        if (this.isWet())
        {
            this.entityToAttack = null;
            this.func_70819_e(false);
            this.teleportRandomly();
        }

        this.isJumping = false;

        if (this.entityToAttack != null)
        {
            this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
        }

        if (!this.worldObj.isRemote && this.isEntityAlive())
        {
            if (this.entityToAttack != null)
            {
            	if (assaultHits > 0) {
            		EntityLiving target = (EntityLiving) entityToAttack;
            		
            		if (--assaultDelay <= 0) {
            			if (assaultHits == 3) {
            				teleportDirectlyTo(target.posX, target.posY, target.posZ);
		                	target.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) this), getAttackStrength(target) - 1);
		                	target.addVelocity(0.0D, 1.0D, 0.0D);
		                    assaultDelay = 10;
            			} else if (assaultHits == 2) {
            				teleportDirectlyTo(target.posX, target.posY + 1.0D, target.posZ);
		                	target.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) this), getAttackStrength(target) - 1);
		                	target.addVelocity(0.0D, -1.0D, 0.0D);
		                    assaultDelay = 5;
            			} else if (assaultHits == 1) {
            				teleportTo(target.posX, target.posY, target.posZ);
            				assaultDelay = 200;
            				//target.travelToDimension(10);
            			}
	                    --assaultHits;
	                    System.out.println("Number of hits: " + assaultHits);
            		}
            	} else {
            		if (assaultDelay-- <= 0) {
            			assaultHits = 3;
            			System.out.println("Begin Assault! Number of hits: " + assaultHits);
            		}
	                if (this.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)this.entityToAttack))
	                {
	                    this.moveStrafing = this.moveForward = 0.0F;
	                    this.moveSpeed = 0.0F;
	                    
	                    if (this.entityToAttack.getDistanceSqToEntity(this) < 16.0D)
	                    {
	                        this.teleportRandomly();
	                    }
	
	                    this.teleportDelay = 0;
	                }
	                else if (this.entityToAttack.getDistanceSqToEntity(this) > 256.0D && this.teleportDelay++ >= 30 && this.teleportToEntity(this.entityToAttack))
	                {
	                	if (entityToAttack.getDistanceSqToEntity(this) < 16.0D) {
	                		/*EntityLiving attackEntity = (EntityLiving) entityToAttack;
	                		if (attackEntity.getCurrentItemOrArmor(0) != null) {
	        	        		attackEntity.entityDropItem(attackEntity.getCurrentItemOrArmor(0), 0);
	        	        		attackEntity.setCurrentItemOrArmor(0, null);
	                		}*/
	                		assaultHits = 3;
	                		System.out.println("Begin Assault! Number of hits: " + assaultHits);
	                	}
	                }
            	}
            }
            else
            {
                this.func_70819_e(false);
                this.teleportDelay = 0;
            }
        }

        super.onLivingUpdate();
    }
	
	public void setCarried(int par1)
	{
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)(par1 & 255)));
	}
	public int getCarried()
    {
        return this.dataWatcher.getWatchableObjectByte(16);
    }
	
	protected boolean teleportRandomly()
    {
        double var1 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
        double var3 = this.posY + (double)(this.rand.nextInt(64) - 32);
        double var5 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(var1, var3, var5);
    }
	
    protected boolean teleportToEntity(Entity par1Entity)
    {
        Vec3 var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX - par1Entity.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - par1Entity.posY + (double)par1Entity.getEyeHeight(), this.posZ - par1Entity.posZ);
        var2 = var2.normalize();
        double var3 = 16.0D;
        double var5 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - var2.xCoord * var3;
        double var7 = this.posY + (double)(this.rand.nextInt(16) - 8) - var2.yCoord * var3;
        double var9 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - var2.zCoord * var3;
        return this.teleportTo(var5, var7, var9);
    }
    
    protected void teleportDirectlyTo(double x, double y, double z)
    {
    	double prevX = posX;
		double prevY = posY;
		double prevZ = posZ;
		this.setPosition(x, y, z);
		
		short var30 = 128;
        for (int var18 = 0; var18 < var30; ++var18)
        {
            double var19 = (double)var18 / ((double)var30 - 1.0D);
            float var21 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float var22 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float var23 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            double var24 = prevX + (this.posX - prevX) * var19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
            double var26 = prevY + (this.posY - prevY) * var19 + this.rand.nextDouble() * (double)this.height;
            double var28 = prevZ + (this.posZ - prevZ) * var19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
            this.worldObj.spawnParticle("portal", var24, var26, var28, (double)var21, (double)var22, (double)var23);
        }
        this.worldObj.playSoundEffect(posX, posY, posZ, "mob.endermen.portal", 1.0F, 1.0F);
        this.func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
    }
    
    protected boolean teleportTo(double par1, double par3, double par5)
    {
        double var7 = this.posX;
        double var9 = this.posY;
        double var11 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean var13 = false;
        int var14 = MathHelper.floor_double(this.posX);
        int var15 = MathHelper.floor_double(this.posY);
        int var16 = MathHelper.floor_double(this.posZ);
        int var18;

        if (this.worldObj.blockExists(var14, var15, var16))
        {
            boolean var17 = false;

            while (!var17 && var15 > 0)
            {
                var18 = this.worldObj.getBlockId(var14, var15 - 1, var16);

                if (var18 != 0 && Block.blocksList[var18].blockMaterial.blocksMovement())
                {
                    var17 = true;
                }
                else
                {
                    --this.posY;
                    --var15;
                }
            }

            if (var17)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    var13 = true;
                }
            }
        }

        if (!var13)
        {
            this.setPosition(var7, var9, var11);
            return false;
        }
        else
        {
            short var30 = 128;

            for (var18 = 0; var18 < var30; ++var18)
            {
                double var19 = (double)var18 / ((double)var30 - 1.0D);
                float var21 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float var22 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float var23 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double var24 = var7 + (this.posX - var7) * var19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                double var26 = var9 + (this.posY - var9) * var19 + this.rand.nextDouble() * (double)this.height;
                double var28 = var11 + (this.posZ - var11) * var19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
                this.worldObj.spawnParticle("portal", var24, var26, var28, (double)var21, (double)var22, (double)var23);
            }

            this.worldObj.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
            this.func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }
    
    protected String getLivingSound()
    {
        return this.func_70823_r() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    protected String getHurtSound()
    {
        return "mob.endermen.hit";
    }

    protected String getDeathSound()
    {
        return "mob.endermen.death";
    }

    protected int getDropItemId()
    {
        return Item.enderPearl.shiftedIndex;
    }

    protected void dropFewItems(boolean par1, int par2)
    {
        int var3 = this.getDropItemId();

        if (var3 > 0)
        {
            int var4 = this.rand.nextInt(2 + par2);

            for (int var5 = 0; var5 < var4; ++var5)
            {
                this.dropItem(var3, 1);
            }
        }
    }
}