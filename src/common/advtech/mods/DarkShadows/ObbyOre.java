package advtech.mods.DarkShadows;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockOre;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;


public class ObbyOre extends BlockOre {
	public ObbyOre(int par1, int par2) {
		super(par1, par2);
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(Block.soundStoneFootstep);
		setHardness(50F);
		setResistance(2000.0F);
		setBlockName("Obsidian Ore");
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return Block.obsidian.blockID;
	}

	public int quantityDropped(Random par1Random) {
		return 1;
	}
}
