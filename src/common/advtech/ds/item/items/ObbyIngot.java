package advtech.ds.item.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ObbyIngot extends Item {
	public ObbyIngot(int ID){
		super(ID);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);

	}
	public String getTextureFile(){
		return "/advtech/ds/resources/terrain.png";
	}
}
