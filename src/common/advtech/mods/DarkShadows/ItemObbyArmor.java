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
public class ItemObbyArmor extends ItemArmor implements IArmorTextureProvider {
    /** Holds the 'base' maxDamage that each armorType have. */
    private static final int[] maxDamageArray = new int[] {29};

    /**
     * Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public final int armorType;

    /** Holds the amount of damage that the armor reduces at full durability. */
    public final int damageReduceAmount;

    /**
     * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
     * iron, 3 is diamond and 4 is gold.
     */
    public final int renderIndex;

    /** The EnumArmorMaterial used for this ItemArmor */
    private final EnumArmorMaterial material;

    public ItemObbyArmor(int id, EnumArmorMaterial obby1, int render, int type)
    {
        super(id, obby1, render, type);
        this.material = obby1;
        this.armorType = type;
        this.renderIndex = render;
        this.damageReduceAmount = obby1.getDamageReductionAmount(type);
        setMaxDamage(obby1.getDurability(type));
        maxStackSize = 1;
        setCreativeTab(CreativeTabs.tabCombat);
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    /**
     * Returns the 'max damage' factor array for the armor, each piece of armor have a durability factor (that gets
     * multiplied by armor material factor)
     */
    static int[] getMaxDamageArray()
    {
        return maxDamageArray;
    }

	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return null;
	}
}

