package advtech.mods.DarkShadows;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemIngotObby extends Item {
	public ItemIngotObby(int i) {
		super(i);
		setIconIndex(28);
		setItemName("Obsidian Ingot");
		setTabToDisplayOn(CreativeTabs.tabMaterials);
	}

}