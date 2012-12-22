package advtech.ds.tile;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import advtech.ds.gui.SlotStreamFurnace;
import advtech.ds.gui.StreamFurnaceRecipes;

public class ContainerStreamFurnace extends Container {

	protected TileEntityStreamFurnace tileEntity;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastFreshBurnTime;

	public ContainerStreamFurnace(TileEntityStreamFurnace tileEntity, InventoryPlayer playerInventory) {
		this.tileEntity = tileEntity;

		lastCookTime = 0;
		lastBurnTime = 0;
		lastFreshBurnTime = 0;

		addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
		addSlotToContainer(new Slot(tileEntity, 1, 47, 54));
		addSlotToContainer(new Slot(tileEntity, 2, 65, 54));
		addSlotToContainer(new SlotStreamFurnace(playerInventory.player, tileEntity, 3, 116, 35));

		bindPlayerInventory(playerInventory);
	}

	protected void bindPlayerInventory(InventoryPlayer playerinventory) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(playerinventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	@Override
	public void updateCraftingResults() {
		super.updateCraftingResults();
		Iterator var1 = this.crafters.iterator();

		while (var1.hasNext()) {
			ICrafting var2 = (ICrafting)var1.next();

			if (this.lastCookTime != this.tileEntity.cookTime) {
				var2.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
			}

			if (this.lastBurnTime != this.tileEntity.burnTime) {
				var2.sendProgressBarUpdate(this, 1, this.tileEntity.burnTime);
			}

			if (this.lastFreshBurnTime != this.tileEntity.freshBurnTime) {
				var2.sendProgressBarUpdate(this, 2, this.tileEntity.freshBurnTime);
			}
		}

		this.lastCookTime = this.tileEntity.cookTime;
		this.lastBurnTime = this.tileEntity.burnTime;
		this.lastFreshBurnTime = this.tileEntity.freshBurnTime;
	}

	@Override
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			tileEntity.cookTime = par2;
		}

		if (par1 == 1) {
			tileEntity.burnTime = par2;
		}

		if (par1 == 2) {
			tileEntity.freshBurnTime = par2;
		}
	}

	public ItemStack transferStackInSlot(int par1) {
		ItemStack itemstack = null;
		SlotStreamFurnace slot = (SlotStreamFurnace)inventorySlots.get(par1);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par1 == 2) {
				if (!mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (par1 == 1 || par1 == 0) {
				if (!mergeItemStack(itemstack1, 3, 39, false)) {
					return null;
				}
			} else if (StreamFurnaceRecipes.smelting().getSmeltingResult(itemstack1.getItem().shiftedIndex) != null) {
				if (!mergeItemStack(itemstack1, 0, 1, false)) {
					return null;
				}
			} else if (TileEntityStreamFurnace.isItemFuel(itemstack1)) {
				if (!mergeItemStack(itemstack1, 1, 2, false)) {
					return null;
				}
			} else if (par1 >= 3 && par1 < 30) {
				if (!mergeItemStack(itemstack1, 30, 39, false)) {
					return null;
				}
			} else if (par1 >= 30 && par1 < 39 && !mergeItemStack(itemstack1, 3, 30, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}
}
