package advtech.ds.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import advtech.ds.tile.ContainerStreamFurnace;
import advtech.ds.tile.TileEntityStreamFurnace;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (tile_entity instanceof TileEntityStreamFurnace) {
			return new ContainerStreamFurnace((TileEntityStreamFurnace) tile_entity, player.inventory);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (tile_entity instanceof TileEntityStreamFurnace) {
			return new GuiStreamFurnace(player.inventory, (TileEntityStreamFurnace) tile_entity);
		}
		
		return null;
	}
}
