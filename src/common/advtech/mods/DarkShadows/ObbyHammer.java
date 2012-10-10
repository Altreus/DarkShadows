package advtech.mods.DarkShadows;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ObbyHammer extends Item {
	public ObbyHammer(int i) {
		super(i);
		setIconIndex(28);
		setItemName("Obsidian Hammer");
		setCreativeTab(CreativeTabs.tabTools);
	}
	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack){
		itemStack.setItemDamage(itemStack.getItemDamage()+1);
		return itemStack;
	
	}
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack){
		return false;
	}

}
