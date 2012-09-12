/**
 * 
 */
package advtech.mods.DarkShadows;

import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemSword;
import net.minecraftforge.common.EnumHelper;

/**
 * @author advtech
 *
 */
public class ItemObbySword extends ItemSword {
	static EnumToolMaterial obby = EnumHelper.addToolMaterial("obby", 2, 500, 7F, 2, 14);

	public ItemObbySword(int ItemID, EnumToolMaterial toolMaterial){
		super(508, obby);
		setIconIndex(0);
		setItemName("obbySword");
	}
	
}
