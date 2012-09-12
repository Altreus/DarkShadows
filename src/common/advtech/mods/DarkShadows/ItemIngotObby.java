/**
 * 
 */
package advtech.mods.DarkShadows;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import java.util.List; 
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly
;public class ItemIngotObby extends Item {
	public ItemIngotObby(int i) {
		super(i);
		setTabToDisplayOn(CreativeTabs.tabMaterials);
		}
@SideOnly(Side.CLIENT)
public String getTextureFile(){
	return "/advtech/mods/DarkShadows/terrain.png";
	}
@SideOnly(Side.CLIENT)
public int getIconFromDamage(int i){
	switch(i){ //Switch statement to tell it which texture to use
	case 0 :
		 return 0;
	case 1 :
	 	return 1;
	case 2 :
		return 2;
	default:
		return 0;
		}
	}
@SideOnly(Side.CLIENT)
public void getSubItems(int i, CreativeTabs tab, List list){
	list.add(new ItemStack(i,1,0));
	list.add(new ItemStack(i,1,1));
	list.add(new ItemStack(i,1,2));
	}

}







