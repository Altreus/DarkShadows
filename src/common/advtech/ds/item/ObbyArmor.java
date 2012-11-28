/**
 * 
 */
package advtech.ds.item;

import advtech.ds.DarkShadows;
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
	public static final int[] maxDamageArray = new int[]{29};
	public final int armorType;
	public final int renderIndex;
	public final int damageReduceAmount;
	private final EnumArmorMaterial material;
	public ObbyArmor(int ID, EnumArmorMaterial material, int render, int part ){
		super(ID,material, render, part);
		this.material = DarkShadows.obbyArmorMaterial;
		this.armorType = part;
		this.renderIndex = render;
		this.damageReduceAmount = material.getDamageReductionAmount(part);
		setMaxDamage(material.getDurability(part));
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.tabCombat);
		
	}
	
	static int[] getMaxDamageArray(){
		return maxDamageArray;
	}
	@Override
	public String getTextureFile(){
		return "/advtech/ds/resources/terrain.png";
	}
	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		if(itemstack.itemID == DarkShadows.helmetObby.shiftedIndex || itemstack.itemID == DarkShadows.chestplateObby.shiftedIndex||
				itemstack.itemID == DarkShadows.bootObby.shiftedIndex){
			return "/advtech/ds/resources/obby_1.png";
			
		}
		if (itemstack.itemID == DarkShadows.leggingObby.shiftedIndex){
		return "/advtech/ds/resources/obby_2.png";
	}
		return "/advtech/ds/resources/obby_1.png";
	}
}