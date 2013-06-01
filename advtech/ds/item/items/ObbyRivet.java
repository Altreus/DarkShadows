package advtech.ds.item.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ObbyRivet extends Item {
	public ObbyRivet(int ID){
		super(ID);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	@Override
	public void registerIcons(IconRegister index){
		//TODO Johulk place file here
		// iconIndex = index.registerIcon("");
	}
}
