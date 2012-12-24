package advtech.ds.item.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
			return "/advtech/ds/resources/terrain.png";
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