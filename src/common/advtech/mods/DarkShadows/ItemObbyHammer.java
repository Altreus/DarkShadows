package advtech.mods.DarkShadows;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemObbyHammer extends Item {
	public ItemObbyHammer(int i) {
		super(i);
		setIconIndex(10);
		setItemName("Obsidian Hammer");
		setTabToDisplayOn(CreativeTabs.tabTools);
	}
	  @Override
		public String getTextureFile() {
		return "/advtech/mods/DarkShadows/item.png";
	    }

}
