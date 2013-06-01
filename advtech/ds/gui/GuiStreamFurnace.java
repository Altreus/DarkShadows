package advtech.ds.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import advtech.ds.tile.ContainerStreamFurnace;
import advtech.ds.tile.TileEntityStreamFurnace;

public class GuiStreamFurnace extends GuiContainer {

	private TileEntityStreamFurnace streamFurnace;

	public GuiStreamFurnace(InventoryPlayer playerInventory,
			TileEntityStreamFurnace tileEntity) {
		super(new ContainerStreamFurnace(tileEntity, playerInventory));

		streamFurnace = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p1, int p2) {
		fontRenderer.drawString("Stream Furnace", 6, 6, 0xFFFFFF);
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 6,
				ySize - 94, 0xFFFFFF);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		int texture = mc.renderEngine
				.getTexture("/advtech/ds/resources/streamFurnace.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture("darkenedsouls:streamFurnace");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (streamFurnace.isBurning()) {
			int burn = streamFurnace.getBurnTimeRemainingScaled(14);
			drawTexturedModalRect(x + 57, y + 37, 176, 0, 14, burn);
			int update = streamFurnace.getCookProgressScaled(15);
			drawTexturedModalRect(x + 89, y + 55, 176, 31, update, 17);

			System.out.println(burn + ", " + update);
		}
	}

}
