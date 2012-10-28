package advtech.ds.gui;

import java.util.Iterator;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityXPOrb;
import net.minecraft.src.ICrafting;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Slot;

public class SlotStreamFurnace extends Slot {

	private EntityPlayer thePlayer;
	private int field;

	public SlotStreamFurnace(EntityPlayer player, IInventory playerInventory, int slot, int x, int y) {
		super(playerInventory, slot, x, y);
		this.thePlayer = player;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack decrStackSize(int slot) {
		if (this.getHasStack())
		{
			this.field += Math.min(slot, this.getStack().stackSize);
		}

		return super.decrStackSize(slot);
	}

	public void onPickupFromSlot(ItemStack stack) {
		this.onCrafting(stack);
<<<<<<< HEAD:src/common/advtech/ds/gui/SlotStreamFurnace.java
=======
		//super.onPickupFromSlot(stack);
>>>>>>> 3cd30d74137c9ad747abfdbae13a7d74cd7ac706:src/common/advtech/mods/DarkShadows/gui/SlotStreamFurnace.java
	}

	@Override
	protected void onCrafting(ItemStack stack, int amt) {
		this.field += amt;
		this.onCrafting(stack);
	}

	@Override
	protected void onCrafting(ItemStack stack) {
		stack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field);

		int var2 = this.field;
		float var3 = StreamFurnaceRecipes.smelting().getExperience(stack.itemID);
		int var4;

		if (var3 == 0.0F)
		{
			var2 = 0;
		}
		else if (var3 < 1.0F)
		{
			var4 = MathHelper.floor_float((float)var2 * var3);

			if (var4 < MathHelper.ceiling_float_int((float)var2 * var3) && (float)Math.random() < (float)var2 * var3 - (float)var4)
			{
				++var4;
			}

			var2 = var4;
		}

		while (var2 > 0)
		{
			var4 = EntityXPOrb.getXPSplit(var2);
			var2 -= var4;
			this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, var4));
		}

		this.field = 0;
	}
}

