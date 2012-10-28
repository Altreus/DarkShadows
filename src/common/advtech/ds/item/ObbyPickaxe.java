package advtech.ds.item;

import advtech.ds.DarkShadows;
import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemTool;
import net.minecraft.src.Material;

public class ObbyPickaxe extends ItemTool {
	public static final Block[] blocksEffectiveAgainst = new Block[] {Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, DarkShadows.oreObby};

	public ObbyPickaxe(int ID, EnumToolMaterial material){
		super(ID,3, material, blocksEffectiveAgainst);
	}
	  public boolean canHarvestBlock(Block par1Block)
	    {
	        return par1Block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (par1Block != Block.blockDiamond && par1Block != Block.oreDiamond ? (par1Block == Block.oreEmerald ? this.toolMaterial.getHarvestLevel() >= 2 : (par1Block != Block.blockGold && par1Block != Block.oreGold ? (par1Block != Block.blockSteel && par1Block != Block.oreIron ? (par1Block != Block.blockLapis && par1Block != Block.oreLapis ? (par1Block != Block.oreRedstone && par1Block != Block.oreRedstoneGlowing ? (par1Block.blockMaterial == Material.rock ? true : par1Block.blockMaterial == Material.iron) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2)) : this.toolMaterial.getHarvestLevel() >= 2);
	    }
	  public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	    {
	        return par2Block != null && (par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	    }
	  @Override
	  public String getTextureFile(){
			return "/advtech/ds/resources/item.png";
	 }
}
