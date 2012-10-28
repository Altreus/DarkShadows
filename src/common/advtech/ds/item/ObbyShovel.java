package advtech.ds.item;

import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemTool;

public class ObbyShovel extends ItemTool {
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};
    public ObbyShovel(int ID, EnumToolMaterial material){
    	super(ID, 3, material, blocksEffectiveAgainst);
    }
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }
    @Override
    public String getTextureFile(){
		return "/advtech/mods/DarkShadows/item.png";
 }

}
