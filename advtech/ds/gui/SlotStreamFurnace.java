package advtech.ds.gui;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

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

	public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        this.onSlotChanged();
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

