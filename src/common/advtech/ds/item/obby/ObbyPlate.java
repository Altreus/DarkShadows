package advtech.ds.item.obby;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ObbyPlate extends Item {
	public ObbyPlate(int ID){
		super(ID);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);

	}
	public String getTextureFile(){
		return "/advtech/ds/resources/terrain.png";
	}

}
