package advtech.ds.item.obby;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ObbyHoe extends Item {
	public ObbyHoe(int ID, EnumToolMaterial material){
		super(ID);
		maxStackSize = 1;
		setMaxDamage(material.getMaxUses());
		
	}
	 public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	    {
		 if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
	        {
	            return false;
	        }
	        else
	        {
	            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
	            if (MinecraftForge.EVENT_BUS.post(event))
	            {
	                return false;
	            }

	            if (event.getResult() == Result.ALLOW)
	            {
	                par1ItemStack.damageItem(1, par2EntityPlayer);
	                return true;
	            }

	            int var11 = par3World.getBlockId(par4, par5, par6);
	            int var12 = par3World.getBlockId(par4, par5 + 1, par6);

	            if ((par7 == 0 || var12 != 0 || var11 != Block.grass.blockID) && var11 != Block.dirt.blockID)
	            {
	                return false;
	            }
	            else
	            {
	                Block var13 = Block.tilledField;
	                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), var13.stepSound.getStepSound(), (var13.stepSound.getVolume() + 1.0F) / 2.0F, var13.stepSound.getPitch() * 0.8F);

	                if (par3World.isRemote)
	                {
	                    return true;
	                }
	                else
	                {
	                    par3World.setBlockWithNotify(par4, par5, par6, var13.blockID);
	                    par1ItemStack.damageItem(1, par2EntityPlayer);
	                    return true;
	                }
	            }
	        }
	    }
	  public boolean isFull3D(){
		  return true;
	  }
	  @Override
	  public String getTextureFile(){
			return "/advtech/ds/resources/terrain.png";
	 }

}