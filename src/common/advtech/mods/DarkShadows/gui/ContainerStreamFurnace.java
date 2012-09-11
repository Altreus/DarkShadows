package advtech.mods.DarkShadows.gui;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ContainerStreamFurnace extends Container {

	protected TileEntityStreamFurnace tileEntity;
	
	public ContainerStreamFurnace(TileEntityStreamFurnace tileEntity, InventoryPlayer playerInventory) {
		this.tileEntity = tileEntity;
		addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
		addSlotToContainer(new Slot(tileEntity, 1, 47, 54));
		addSlotToContainer(new Slot(tileEntity, 2, 65, 54));
		addSlotToContainer(new Slot(tileEntity, 3, 116, 35));
		
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
	public ItemStack transferStackInSlot(int slot_index) {
		ItemStack stack = null;
		Slot slot_object = (Slot) inventorySlots.get(slot_index);

		if (slot_object != null && slot_object.getHasStack()) {
			ItemStack stack_in_slot = slot_object.getStack();
			stack = stack_in_slot.copy();

			if (slot_index == 0) {
				if (!mergeItemStack(stack_in_slot, 1, inventorySlots.size(), true)) {
					return null;
				}
			} else if (!mergeItemStack(stack_in_slot, 0, 1, false)) {
				return null;
			}

			if (stack_in_slot.stackSize == 0) {
				slot_object.putStack(null);
			} else {
				slot_object.onSlotChanged();
			}
		}

		return stack;
	}
}
