/**
 * 
 */
package advtech.ds.item.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author Advtech
 * 
 */
public class PortalMaker extends Item {

	/**
	 * @param par1
	 */
	public PortalMaker(int i) {
		super(i);
		setUnlocalizedName("Portal Maker");
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public void registerIcons(IconRegister index) {
		// TODO Johulk place file here
		// iconIndex = index.registerIcon("");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(
				world, player, true);
		int direction = (int) Math.round(Math.abs(player.rotationYaw) / 90);

		if (var4 == null) {
			return stack;
		}

		switch (direction) {
		case 0:
		case 2:
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX + 1, var4.blockY, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX - 1, var4.blockY, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX + 2, var4.blockY, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX + 2, var4.blockY + 1, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX - 1, var4.blockY + 1, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX + 2, var4.blockY + 2, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX - 1, var4.blockY + 2, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX + 2, var4.blockY + 3, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX - 1, var4.blockY + 3, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX + 2, var4.blockY + 4, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX + 1, var4.blockY + 4, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 4, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX - 1, var4.blockY + 4, var4.blockZ,
					Block.obsidian.blockID);

			world.setBlock(var4.blockX, var4.blockY + 1, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX + 1, var4.blockY + 1, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY + 2, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX + 1, var4.blockY + 2, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY + 3, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX + 1, var4.blockY + 3, var4.blockZ,
					Block.portal.blockID);
			break;
		case 1:
		case 3:
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ + 1,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ - 1,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY, var4.blockZ + 2,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 1, var4.blockZ + 2,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 1, var4.blockZ - 1,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 2, var4.blockZ + 2,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 2, var4.blockZ - 1,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 3, var4.blockZ + 2,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 3, var4.blockZ - 1,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 4, var4.blockZ + 2,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 4, var4.blockZ + 1,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 4, var4.blockZ,
					Block.obsidian.blockID);
			world.setBlock(var4.blockX, var4.blockY + 4, var4.blockZ - 1,
					Block.obsidian.blockID);

			world.setBlock(var4.blockX, var4.blockY + 1, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY + 1, var4.blockZ + 1,
					Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY + 2, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY + 2, var4.blockZ + 1,
					Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY + 3, var4.blockZ,
					Block.portal.blockID);
			world.setBlock(var4.blockX, var4.blockY + 3, var4.blockZ + 1,
					Block.portal.blockID);
			break;
		}

		return stack;
	}

}
