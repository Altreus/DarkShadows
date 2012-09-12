package advtech.mods.DarkShadows;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemObbyPlate extends Item {
	public ItemObbyPlate(int i) {
		super(i);
		setIconIndex(28);
		setItemName("Obsidian Plate");
		setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
}
