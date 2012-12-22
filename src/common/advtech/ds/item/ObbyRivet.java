package advtech.ds.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ObbyRivet extends Item {
	public ObbyRivet(int ID){
		super(ID);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	public String getTextureFile(){
		return "/advtech/ds/resources/terrain.png";
	}

}
