package advtech.ds.item.light;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import advtech.ds.DarkShadows;

public class LightArmor extends ItemArmor implements IArmorTextureProvider {
	public static final int[] maxDamageArray = new int[]{29};
	public final int armorType;
	public final int renderIndex;
	public final int damageReduceAmount;
	private final EnumArmorMaterial material;
	public LightArmor(int ID, EnumArmorMaterial material, int render, int part ){
		super(ID,material, render, part);
		this.material = DarkShadows.lightArmorMaterial;
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
		if(itemstack.itemID == DarkShadows.helmetLight.shiftedIndex || itemstack.itemID == DarkShadows.chestplateLight.shiftedIndex||
				itemstack.itemID == DarkShadows.bootLight.shiftedIndex){
			return "/advtech/ds/resources/obby_1.png";
			
		}
		if (itemstack.itemID == DarkShadows.leggingLight.shiftedIndex){
		return "/advtech/ds/resources/obby_2.png";
	}
		return "/advtech/ds/resources/obby_1.png";
	}
}
