package advtech.ds.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ObbyAxe extends ItemTool {

	 public static final Block[] blocksEffectiveAgainst = new Block[] {Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern};
	 public ObbyAxe (int id, EnumToolMaterial material){
		 super(id, 3, material, blocksEffectiveAgainst);
	 }
	  @Override
	  public String getTextureFile(){
			return "/advtech/ds/resources/terrain.png";
	 }
	 public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	    {
	        return par2Block != null && par2Block.blockMaterial == Material.wood ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	    }

}
