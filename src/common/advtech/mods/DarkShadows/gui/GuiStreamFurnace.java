package advtech.mods.DarkShadows.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

public class GuiStreamFurnace extends GuiContainer {

	public GuiStreamFurnace(InventoryPlayer playerInventory, TileEntityStreamFurnace tileEntity) {
		super(new ContainerStreamFurnace(tileEntity, playerInventory));
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		fontRenderer.drawString("Stream Furnace", 6, 6, 0xFFFFFF);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 94, 0xFFFFFF);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int texture = mc.renderEngine.getTexture("/advtech/mods/DarkShadows/gui/streamFurnace.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		this.mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	

}
