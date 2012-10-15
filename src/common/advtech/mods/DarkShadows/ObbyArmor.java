/**
 * 
 */
package advtech.mods.DarkShadows;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

/**
 * @author advtech
 *
 */
public class ObbyArmor extends ItemArmor implements IArmorTextureProvider {
	public final int armorType;
	public final int renderIndex;
	public final int damageReduceAmount;
	private final EnumArmorMaterial material;
	public ObbyArmor(int ID, EnumArmorMaterial material, int render, int part ){
		super(ID,material, render, part);
		this.material = DarkShadow.obbyArmorMaterial;
		this.armorType = part;
		this.renderIndex = render;
		this.damageReduceAmount = material.getDamageReductionAmount(part);
		setMaxDamage(material.getDurability(part));
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.tabCombat);
		
	}
	

	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		if(itemstack.itemID == DarkShadow.helmetObby.shiftedIndex || itemstack.itemID == DarkShadow.chestplateObby.shiftedIndex||
				itemstack.itemID == DarkShadow.bootObby.shiftedIndex){
			return "armor/obby_1.png";
			
		}
		if (itemstack.itemID == DarkShadow.leggingObby.shiftedIndex){
		return "armor/obby_2.png";
	}
		return "/armor/obby_1.png";
	}
}