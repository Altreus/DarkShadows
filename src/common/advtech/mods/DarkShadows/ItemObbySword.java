/**
 * 
 */
package advtech.mods.DarkShadows;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;
import net.minecraft.src.World;
import net.minecraftforge.common.EnumHelper;

/**
 * @author advtech
 *
 */
public class ItemObbySword extends ItemSword {
	
	static EnumToolMaterial obby = EnumHelper.addToolMaterial("obby", 2, 500, 7F, 2, 14);


	public ItemObbySword(int ItemID, EnumToolMaterial toolMaterial){
		super(509, obby);
		setIconIndex(0);
		setItemName("obbySword");
		this.maxStackSize=1;
		
	}
	
	private int weaponDamage;
	  public boolean isFull3D()
	    {
	        return true;
	    }
	  public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.block;
	    }
	  public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        return par1ItemStack;
	    }
	  public boolean canHarvestBlock(Block par1Block)
	    {
	        return par1Block.blockID == Block.web.blockID;
	    }
	  public int getItemEnchantability()
	    {
	        return this.obby.getEnchantability();
	    }

	    public String func_77825_f()
	    {
	        return this.obby.toString();
	    }
	
}
