package advtech.ds.item;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ObbyPlate extends Item {
	public ObbyPlate(int ID){
		super(ID);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);

	}

}
