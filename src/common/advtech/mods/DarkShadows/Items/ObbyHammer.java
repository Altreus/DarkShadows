package advtech.mods.DarkShadows.Items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ObbyHammer extends Item {
	public ObbyHammer(int i) {
		super(i);
		setCreativeTab(CreativeTabs.tabTools);
		setMaxStackSize(1);
		setNoRepair();
	}
	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack){
		itemStack.setItemDamage(itemStack.getItemDamage()+1);
		return itemStack;
	
	}
	@Override
	public String getTextureFile(){
		return "/advtech/mods/DarkShadows/item.png";
 }
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack){
		return false;
	}

	@Override
   	public boolean getShareTag() {
     		return true;
    	}

}
