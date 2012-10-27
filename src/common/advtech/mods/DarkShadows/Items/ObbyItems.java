/**
 * 
 */
package advtech.mods.DarkShadows.Items;

import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.CreativeTabs;

/**
 * @author dsape02
 *
 */
public class ObbyItems extends Item {
	
	private static final String[] subset = new String[]{
		"ingot.obby","rivet.obby","plate.obby","arm.obby"
	};
	
	public static final String[] pubset = new String[]{
		"Obsidian Ingot", "Obsidian Rivet", "Obsidian Plate","Obsidian Armplate"
	};
	
	public ObbyItems (int id){
		super(id);
		setHasSubtypes(true);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public int getIconFromDamage(int damageValue){
		return damageValue + 16;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack){
		return getItemName() + "." + subset[itemstack.getItemDamage()];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int i, CreativeTabs tab, List list){
		list.add(new ItemStack(i,1,0));
		list.add(new ItemStack(i,1,1));
		list.add(new ItemStack(i,1,2));
		list.add(new ItemStack(i,1,3));
	}

}
