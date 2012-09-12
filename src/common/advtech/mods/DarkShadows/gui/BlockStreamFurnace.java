package advtech.mods.DarkShadows.gui;

import java.util.Random;

import cpw.mods.fml.common.asm.transformers.MCPMerger;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import advtech.mods.DarkShadows.DarkShadow;

public class BlockStreamFurnace extends BlockContainer {

	private static boolean keepInventory = false;
	private Random furnaceRand;
	
	public BlockStreamFurnace(int par1, int par2, boolean active) {
		super(par1, par2, Material.circuits);
		setBlockName("streamFurnace");
		setCreativeTab(CreativeTabs.tabDeco);
		furnaceRand = new Random();
		setRequiresSelfNotify();
	}
	
	@Override
	public String getTextureFile() {
		return "/advtech/mods/DarkShadows/terrain.png";
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float h) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		
		player.openGui(DarkShadow.instance, 0, world, x, y, z);
		return true;
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
		
		int i = world.getBlockId(x, y, z-1);
		int j = world.getBlockId(x, y, z+1);
		int k = world.getBlockId(x-1, y, z);
		int l = world.getBlockId(x+1, y, z);
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
	
	@Override
	public int getBlockTexture(IBlockAccess access, int x, int y, int z, int side) {
		int front = 0;
		
		return front;
	}
	
	@Override
	public int getBlockTextureFromSide(int side) {
		switch (side) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 17;
		case 4:
			return 18;
		case 5:
			return 1;
		default:
			return 0;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityStreamFurnace();
	}
}
