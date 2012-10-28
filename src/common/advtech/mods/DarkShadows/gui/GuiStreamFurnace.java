package advtech.mods.DarkShadows.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

public class GuiStreamFurnace extends GuiContainer {

	private TileEntityStreamFurnace streamFurnace;
	
	public GuiStreamFurnace(InventoryPlayer playerInventory, TileEntityStreamFurnace tileEntity) {
		super(new ContainerStreamFurnace(tileEntity, playerInventory));
		
		streamFurnace = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p1, int p2) {
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
		
		if (streamFurnace.isBurning()) {
			int burn = streamFurnace.getBurnTimeRemainingScaled(14);
			drawTexturedModalRect(x + 57, y + 37, 176, 0, 14, burn);
			int update = streamFurnace.getCookProgressScaled(15);
	        drawTexturedModalRect(x + 89, y + 55, 176, 31, update, 17);
	        
	        System.out.println(burn + ", " + update);
		}
	}
	
	

}
