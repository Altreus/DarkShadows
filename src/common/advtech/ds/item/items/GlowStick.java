package advtech.ds.item.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GlowStick extends Item {

	public GlowStick(int ID) {
		super(ID);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
		// TODO Auto-generated constructor stub
	}
	public String getTextureFile(){
		return ("/advtech/ds/resources/terrain.png");
	}

}
