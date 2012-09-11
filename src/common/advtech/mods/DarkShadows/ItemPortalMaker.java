/**
 * 
 */
package advtech.mods.DarkShadows;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;

/**
 * @author Advtech
 *
 */
public class ItemPortalMaker extends Item {

	/**
	 * @param par1
	 */
	public ItemPortalMaker(int i) {
		super(i);
		setIconIndex(28);
		setItemName("Portal Maker");
		setTabToDisplayOn(CreativeTabs.tabTools);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(world, player, true);
		int direction = (int)Math.round(Math.abs(player.rotationYaw) / 90);
		
		if (var4 == null) {
			return stack;
		}
		
		switch (direction) {
		case 0:
		case 2:
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX+1, var4.blockY, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX-1, var4.blockY, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX+2, var4.blockY, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX+2, var4.blockY+1, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX-1, var4.blockY+1, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX+2, var4.blockY+2, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX-1, var4.blockY+2, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX+2, var4.blockY+3, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX-1, var4.blockY+3, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX+2, var4.blockY+4, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX+1, var4.blockY+4, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+4, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX-1, var4.blockY+4, var4.blockZ, Block.obsidian.blockID);
			
			world.setBlock(var4.blockX, var4.blockY+1, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX+1, var4.blockY+1, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY+2, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX+1, var4.blockY+2, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY+3, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX+1, var4.blockY+3, var4.blockZ, Block.portal.blockID);
			break;
		case 1:
		case 3:
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ+1, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ-1, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ+2, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+1, var4.blockZ+2, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+1, var4.blockZ-1, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+2, var4.blockZ+2, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+2, var4.blockZ-1, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+3, var4.blockZ+2, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+3, var4.blockZ-1, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+4, var4.blockZ+2, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+4, var4.blockZ+1, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+4, var4.blockZ, Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY+4, var4.blockZ-1, Block.obsidian.blockID);
			
			world.setBlock(var4.blockX, var4.blockY+1, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY+1, var4.blockZ+1, Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY+2, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY+2, var4.blockZ+1, Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY+3, var4.blockZ, Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY+3, var4.blockZ+1, Block.portal.blockID);
			break;
		}
		
		return stack;
	}

}
