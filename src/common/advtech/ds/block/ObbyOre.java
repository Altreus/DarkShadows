package advtech.ds.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;


public class ObbyOre extends BlockOre {
	public ObbyOre(int blockID,int sprite) {
		super(blockID, sprite);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(Block.soundStoneFootstep);
		setHardness(50F);
		setResistance(2000.0F);
		setBlockName("Obsidian Ore");
	}

	@Override
	public String getTextureFile() {
		return "/advtech/ds/resources/terrain.png";
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return Block.obsidian.blockID;
	}

	public int quantityDropped(Random par1Random) {
		return 1;
	}
}
