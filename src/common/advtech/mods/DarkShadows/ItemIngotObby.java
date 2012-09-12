/**
 * 
 */
package advtech.mods.DarkShadows;

import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * @author Advtech
 *
 */
public class ItemIngotObby extends Item {
	public ItemIngotObby(int i) {
			super(i);
	        this.setHasSubtypes(true);
	        this.setMaxDamage(0);
	        this.setTabToDisplayOn(CreativeTabs.tabMaterials);
	    }
	public int getMetaData(int i){
		return i;
	}
	public int getIconFromDamage(int metadata){
		switch(metadata){
		case 0: return 0;
		case 1: return 1;
		case 2: return 2;
		default : return 0;
		}
	}
}



