package advtech.mods.DarkShadows.gui;

import advtech.mods.DarkShadows.DarkShadow;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockStreamFurnace extends BlockContainer
{
	private Random furnaceRand = new Random();
	private final boolean isActive;
	private static boolean keepFurnaceInventory = false;
	public BlockStreamFurnace(int id,int sprite, boolean active)
	{
		super(id, sprite, Material.iron);
		this.isActive = active;
		this.setBlockName("Forge");
		this.setRequiresSelfNotify();
	}
	public int idDropped(int par1, Random random, int par3)
	{
		return DarkShadow.streamFurnaceIdle.blockID;
	}
	@Override
	public String getTextureFile(){
		return "/advtech/mods/DarkShadows/item.png";
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z)
	{
		if  (!world.isRemote)
		{
			int var5 = world.getBlockId(x, y, z - 1);
			int var6 = world.getBlockId(x, y, z + 1);
			int var7 = world.getBlockId(x - 1, y, z);
			int var8 = world.getBlockId(x + 1, y, z);
			byte var9 = 3;
			
			if(Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
			{
				var9 = 3;
			}
			
			if(Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
			{
				var9 = 2;
			}

			if(Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
			{
				var9 = 5;
			}
			
			if(Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
			{
				var9 = 4;
			}
			world.setBlockMetadataWithNotify(x,y,z, var9);
		}
	}
	@SideOnly(Side.CLIENT)
	public int getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
	{
		if(side == 1)
		{
			return this.blockIndexInTexture + 1;
		}
		else if (side == 0)
		{
			return this.blockIndexInTexture + 1;
		}
		else
		{
			int var6 = par1IBlockAccess.getBlockMetadata(x,y,z);
			return side != var6 ? this.blockIndexInTexture : (this.isActive  ? this.blockIndexInTexture - 1 : this.blockIndexInTexture - 1);
		}
	}
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (this.isActive)
		{
			int var6  = world.getBlockMetadata(x,y,z);
			float var7 = (float)x + 0.5F;
			float var8 = (float)y + 0.0F + random.nextFloat() * 6.0F/16.0F;
			float var9 = (float)z + 0.5F;
			float var10 = 0.52F;
			float var11 = random.nextFloat() * 0.6F - 0.3F;
			
			if (var6 == 4)
			{
				world.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (var6 == 5)
			{
				world.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (var6 == 2)
			{
				world.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
			}
			else if (var6 == 3)
			{
				world.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
			}
		}
	}
	public int getBlockTextureFromSide(int side)
	{
		switch(side){
		case 0:
			return 9;
		case 1:
			return 9;
		case 2:
			return 8;
		case 3:
			return 7;
		case 4:
			return 8;
		case 5:
			return 8;
		default:
			return 8;
		}
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if  (world.isRemote)
		{
			return true;
		}
		else
		{
			TileEntityStreamFurnace var10 = (TileEntityStreamFurnace)world.getBlockTileEntity(x,y,z);
			
			if (var10 != null)
			{
				player.openGui(DarkShadow.instance, 0, world, x,y,z);
			}
			
			return true;
		}
	}
	public static void updateFurnaceBlockState(boolean active, World world, int x, int y, int z)
	{
		int var5 = world.getBlockMetadata(x,y,z);
		TileEntity var6 = world.getBlockTileEntity(x,y,z);
		keepFurnaceInventory = true;
		
		if (active)
		{
			world.setBlockWithNotify(x,y,z, DarkShadow.streamFurnaceActive.blockID);
		}
		else
		{
			world.setBlockWithNotify(x,y,z, DarkShadow.streamFurnaceIdle.blockID);
		}
		
		keepFurnaceInventory = false;
		world.setBlockMetadataWithNotify(x,y,z, var5);
		
		if(var6 != null)
		{
			var6.validate();
			world.setBlockTileEntity(x,y,z, var6);
		}
	}
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityStreamFurnace();
	}
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving)
	{
	
		int var6 = MathHelper.floor_double((double)(entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) &3;
		
		if (var6 == 0)
		{
			world.setBlockMetadataWithNotify(x,y,z, 2);
		}
		
		if (var6 == 1)
		{
			world.setBlockMetadataWithNotify(x,y,z, 5);
		}
		if (var6 == 2)
		{
			world.setBlockMetadataWithNotify(x,y,z, 3);
		}
		if (var6 == 3)
		{
			world.setBlockMetadataWithNotify(x,y,z, 4);
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
	 if(!keepFurnaceInventory)
	 {
		TileEntityStreamFurnace var7 = (TileEntityStreamFurnace)world.getBlockTileEntity(x,y,z);
		
		if (var7 != null)
		{
			for(int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
			{
				ItemStack var9 = var7.getStackInSlot(var8);
				
				if (var9 != null)
				{
					float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
					float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
					float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
					
					while (var9.stackSize > 0)
					{
						int var13 = this.furnaceRand.nextInt(21) + 10;
						
						if (var13 > var9.stackSize)
						{
							var13 = var9.stackSize;
						}
						
						var9.stackSize -= var13;
						EntityItem var14 = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));
						
						if (var9.hasTagCompound())
						{
							var14.item.setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
						}
						
						float var15 = 0.05F;
						var14.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
						var14.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
						var14.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
						world.spawnEntityInWorld(var14);
					}
				}
			}
		}
	}
	
	super.breakBlock(world, x, y, z, par5, par6);
}
}