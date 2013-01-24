package advtech.ds.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import advtech.ds.DarkenedSouls;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class Tab extends CreativeTabs
{
         public Tab(String label)
         {
        	 super(label);
         }
         @SideOnly(Side.CLIENT)
         @Override
         public ItemStack getIconItemStack(){
        	 return new ItemStack(DarkenedSouls.smokeScreen);
         }
}