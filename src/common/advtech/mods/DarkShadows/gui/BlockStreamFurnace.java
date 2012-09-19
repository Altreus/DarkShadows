package advtech.mods.DarkShadows.gui;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import advtech.mods.DarkShadows.DarkShadow;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockStreamFurnace extends BlockContainer {

	private static boolean keepFurnaceInventory = false;
	private final boolean isActive;
	private Random furnaceRand;
	
	public BlockStreamFurnace(int par1, int par2, boolean Active) {
		super(par1, par2, Material.circuits);
		isActive = Active;
		setBlockName("streamFurnace");
		setCreativeTab(CreativeTabs.tabDeco);
		furnaceRand = new Random();
		setRequiresSelfNotify();
		blockIndexInTexture = 8;
	}
	
	@Override
	public String getTextureFile() {
		return "/advtech/mods/DarkShadows/item.png";
	}
	public int idDropped(int i, Random random, int j){
		return DarkShadow.streamFurnaceIdle.blockID;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float h) {
		if (world.isRemote){
			return true;
		
		}
		else{
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			
		if (tileEntity != null || player.isSneaking()) {
			player.openGui(DarkShadow.instance, 0, world, x, y, z);
		}
		return true;
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, i, j);
	}
	
	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (!(tile_entity instanceof IInventory)) {
			return;
		}

		IInventory inventory = (IInventory) tile_entity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.6F + 0.1F;
				float ry = rand.nextFloat() * 0.6F + 0.1F;
				float rz = rand.nextFloat() * 0.6F + 0.1F;

				EntityItem entity_item = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound()) {
					entity_item.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.5F;

				entity_item.motionX = rand.nextGaussian() * factor;
				entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
				entity_item.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entity_item);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	public void setDefaultDirection(World world, int x, int y, int z) {
		TileEntity blockEntity = world.getBlockTileEntity(x, y, z);
		
		int i = world.getBlockId(x, y, z -1);
		int j = world.getBlockId(x, y, z +1);
		int k = world.getBlockId(x -1, y, z);
		int l = world.getBlockId(x +1, y, z);
		byte byte0 = 3;
		
		if (Block.opaqueCubeLookup[i] && !Block.opaqueCubeLookup[j]) {
			byte0 = 3;
		}
		if (Block.opaqueCubeLookup[j] && !Block.opaqueCubeLookup[i]) {
			byte0 = 2;
		}
		if (Block.opaqueCubeLookup[k] && !Block.opaqueCubeLookup[l]) {
			byte0 = 5;
		}
		if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[k]) {
			byte0 = 4;
		}
		
		((TileEntityStreamFurnace)blockEntity).setFrontDirection(byte0);
	}
@SideOnly(Side.CLIENT)	
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return this.blockIndexInTexture + 1;
        }
        else if (par5 == 0)
        {
            return this.blockIndexInTexture + 1;
        }
        else
        {
            int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
            return par5 != var6 ? this.blockIndexInTexture : (this.isActive ? this.blockIndexInTexture -1 : this.blockIndexInTexture -1);
        }
    }

	   public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	    {
	        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

	        if (var6 == 0)
	        {
	            world.setBlockMetadataWithNotify(par2, par3, par4, 2);
	        }

	        if (var6 == 1)
	        {
	            world.setBlockMetadataWithNotify(par2, par3, par4, 5);
	        }

	        if (var6 == 2)
	        {
	            world.setBlockMetadataWithNotify(par2, par3, par4, 3);
	        }

	        if (var6 == 3)
	        {
	            world.setBlockMetadataWithNotify(par2, par3, par4, 4);
	        }
	    }

	   public static void updateFurnaceBlockState(boolean Active, World world, int i, int j, int k)
	    {
	        int l = world.getBlockMetadata(i, j, k);
	        TileEntity var6 = world.getBlockTileEntity(i, j, k);
	        keepFurnaceInventory = true;

	        if (Active)
	        {
	            world.setBlockWithNotify(i, j, k, DarkShadow.streamFurnaceActive.blockID);
	        }
	        else
	        {
	            world.setBlockWithNotify(i, j, k, DarkShadow.streamFurnaceIdle.blockID);
	        }

	        keepFurnaceInventory = false;
	        world.setBlockMetadataWithNotify(i, j, k, l);

	        if (var6 != null)
	        {
	            var6.validate();
	            world.setBlockTileEntity(i, j, k, var6);
	        }
	    }
	   public void randomDisplayTick(World world, int par2, int par3, int par4, Random par5Random)
	    {
	        if (this.isActive)
	        {
	            int var6 = world.getBlockMetadata(par2, par3, par4);
	            float var7 = (float)par2 + 0.5F;
	            float var8 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
	            float var9 = (float)par4 + 0.5F;
	            float var10 = 0.52F;
	            float var11 = par5Random.nextFloat() * 0.6F - 0.3F;

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
	
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityStreamFurnace();
	}
}
