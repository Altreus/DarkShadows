package advtech.ds.dimension.shadow;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockShadow extends Block {

	public BlockShadow(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		return false;
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
}
