package advtech.ds.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;


public class ObbyOre extends BlockOre {
	public ObbyOre(int blockID) {
		super(blockID);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("Obsidian Ore");
	}

	@Override
	public void registerIcons(IconRegister index){
		//TODO Johulk place file here
		// iconIndex = index.registerIcon("");
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return Block.obsidian.blockID;
	}

	public int quantityDropped(Random par1Random) {
		return 1;
	}
}
