package advtech.ds.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ObbyRivet extends Item {
	public ObbyRivet(int ID){
		super(ID);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

}
