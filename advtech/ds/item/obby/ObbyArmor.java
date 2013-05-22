/**
 * 
 */
package advtech.ds.item.obby;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import advtech.ds.DarkenedSouls;

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
		this.material = DarkenedSouls.obbyArmorMaterial;
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
	public void registerIcons(IconRegister index){
		//TODO Johulk place file here
		// iconIndex = index.registerIcon("");
	}
	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		if(itemstack.itemID == DarkenedSouls.helmetObby.itemID || itemstack.itemID == DarkenedSouls.chestplateObby.itemID||
				itemstack.itemID == DarkenedSouls.bootObby.itemID){
			return "/advtech/ds/resources/obby_1.png";
			
		}
		if (itemstack.itemID == DarkenedSouls.leggingObby.itemID){
		return "/advtech/ds/resources/obby_2.png";
	}
		return "/advtech/ds/resources/obby_1.png";
	}
}