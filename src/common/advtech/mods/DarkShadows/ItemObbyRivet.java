package advtech.mods.DarkShadows;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemObbyRivet extends Item {
	public ItemObbyRivet(int i) {
		super(i);
		setIconIndex(28);
		setItemName("Obsidian Rivet");
		setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
}
