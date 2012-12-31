package advtech.ds.gui;

import advtech.ds.DarkenedSouls;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class Tab extends CreativeTabs
{
         public Tab(int par1, String par2Str)
         {
                         super(par1, par2Str);
         }
         @SideOnly(Side.CLIENT)
         public int getTabIconItemIndex()
         {
                         return DarkenedSouls.ShadeStone.blockID;
                                
         }

         public String getTranslatedTabLabel()
         {
                 return "Darkened Souls";
         }
}