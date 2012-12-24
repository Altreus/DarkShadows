package advtech.ds.block;

import java.util.Random;

import net.minecraft.block.BlockGlowStone;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MathHelper;
import advtech.ds.DarkenedSouls;

public class ShadeStone extends BlockGlowStone {

	public ShadeStone(int id, int par2, Material par3Material)
    {
        super(id, par2, Material.glass);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return MathHelper.clamp_int(this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 2 + par1Random.nextInt(3);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return DarkenedSouls.ShadowStoneDust.shiftedIndex;
    }
}
