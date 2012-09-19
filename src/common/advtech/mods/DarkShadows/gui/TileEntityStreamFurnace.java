package advtech.mods.DarkShadows.gui;

import net.minecraft.src.Block;
import net.minecraft.src.BlockFurnace;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityStreamFurnace extends TileEntity implements IInventory, ISidedInventory {

	private ItemStack[] inventory;
	public int front = 0;
	
	public int burnTime;
	public int freshBurnTime;
	public int cookTime;
	private boolean isActive;
	
	public TileEntityStreamFurnace() {
		inventory = new ItemStack[4];
		burnTime = 0;
		freshBurnTime = 0;
		cookTime = 0;
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
   public ItemStack decrStackSize(int slotIndex, int amount)
   {
       if (inventory[slotIndex] != null)
       {
           ItemStack var3;

           if (inventory[slotIndex].stackSize <= amount)
           {
               var3 = inventory[slotIndex];
               inventory[slotIndex] = null;
               return var3;
           }
           else
           {
               var3 = this.inventory[slotIndex].splitStack(amount);

               if (inventory[slotIndex].stackSize == 0)
               {
                   inventory[slotIndex] = null;
               }

               return var3;
           }
       }
       else
       {
           return null;
       }
   }

	@Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        if (this.inventory[slotIndex] != null)
        {
            ItemStack stack = this.inventory[slotIndex];
            this.inventory[slotIndex] = null;
            return stack;
        }
        else
        {
            return null;
        }
    }

	public boolean isActive() {
		return isActive;
	}
	
	public int getCookProgressScaled(int i) {
		return (cookTime * i) / 200;
	}
	
	public int getBurnTimeRemainingScaled(int i) {
		if (freshBurnTime == 0) {
			freshBurnTime = 200;
		}
		
		return (burnTime * i) / freshBurnTime;
	}
	
	public boolean isBurning() {
		return burnTime > 0;
	}
	
	public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}
	
	@Override
	public void updateEntity()
    {
        boolean var1 = this.burnTime > 0;
        boolean var2 = false;

        if (this.burnTime > 0)
        {
            --this.burnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.burnTime == 0 && this.canSmelt())
            {
                this.freshBurnTime = this.burnTime = getItemBurnTime(this.inventory[1]);

                if (this.burnTime > 0)
                {
                    var2 = true;

                    if (this.inventory[1] != null)
                    {
                        --this.inventory[1].stackSize;

                        if (this.inventory[1].stackSize == 0)
                        {
                            this.inventory[1] = this.inventory[1].getItem().getContainerItemStack(inventory[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.cookTime;

                if (this.cookTime == 200)
                {
                    this.cookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.cookTime = 0;
            }

            if (var1 != this.burnTime > 0)
            {
                var2 = true;
                BlockStreamFurnace.updateFurnaceBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }
	public static int getItemBurnTime(ItemStack stack) {
		if (stack == null) {
			return 0;
		}
		int i = stack.getItem().shiftedIndex;
		Item var2 = stack.getItem();
		
		if (i == Item.bucketLava.shiftedIndex) return 20000;
		if (i == Item.bucketWater.shiftedIndex)	return 20000;
		return GameRegistry.getFuelValue(stack);
		
		
	}
	
	

	  private boolean canSmelt()
	    {
	        if (inventory[0] == null)
	        {
	            return false;
	        }
	        else
	        {
	            ItemStack stack = StreamFurnaceRecipes.smelting().getSmeltingResult(this.inventory[0].getItem().shiftedIndex);
	            if (stack == null) 
	            	return false;
	            if (inventory[3] == null)
	            	return true;
	            if (!this.inventory[3].isItemEqual(stack))
	            	return false;
	            if (inventory[3].stackSize < getInventoryStackLimit() && inventory[3].stackSize < inventory[3].getMaxStackSize())
	            	return true;
	             return inventory[3].stackSize < stack.getMaxStackSize();
	        }
	    }
	    

	
	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack stack = StreamFurnaceRecipes.smelting().getSmeltingResult(inventory[0].getItem().shiftedIndex);
			
			if (inventory[3] == null) {
				inventory[3] = stack.copy();
			} else if (inventory[3].itemID == stack.itemID) {
				inventory[3].stackSize += stack.stackSize;
			}
			
			--inventory[0].stackSize;
			
			if (inventory[0].stackSize <= 0) {
				inventory[0] = null;
			}
		}
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return "TileEntityStreamFurnace";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
	public void setFrontDirection(int f) {
		this.front = f;
	}
	
	public int getFrontDirection() {
		return this.front;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Inventory");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tag = (NBTTagCompound)tagList.tagAt(i);
            byte slot = tag.getByte("Slot");

            if (slot >= 0 && slot < this.inventory.length)
            {
                this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }

        this.burnTime = tagCompound.getShort("BurnTime");
        this.cookTime = tagCompound.getShort("CookTime");
        this.freshBurnTime = getItemBurnTime(this.inventory[1]);
    }

	// Same as the latter
	@Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setShort("BurnTime", (short)this.burnTime);
        tagCompound.setShort("CookTime", (short)this.cookTime);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(tag);
                tagList.appendTag(tag);
            }
        }

        tagCompound.setTag("Inventory", tagList);
    }

	@Override
	public int getStartInventorySide(ForgeDirection side) {
		if (side == ForgeDirection.UP) return 1;
		if (side == ForgeDirection.DOWN) return 0;
		return 2;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		return 1;
	}
	
}
