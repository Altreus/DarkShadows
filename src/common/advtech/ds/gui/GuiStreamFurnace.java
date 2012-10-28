package advtech.ds.gui;

import org.lwjgl.opengl.GL11;

import advtech.ds.tile.ContainerStreamFurnace;
import advtech.ds.tile.TileEntityStreamFurnace;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

public class GuiStreamFurnace extends GuiContainer {

	private TileEntityStreamFurnace streamFurnace;
	
	public GuiStreamFurnace(InventoryPlayer playerInventory, TileEntityStreamFurnace tileEntity) {
		super(new ContainerStreamFurnace(tileEntity, playerInventory));
		
		streamFurnace = tileEntity;
	}

<<<<<<< HEAD:src/common/advtech/ds/gui/GuiStreamFurnace.java
	protected void drawGuiContainerForegroundLayer() {
=======
	@Override
	protected void drawGuiContainerForegroundLayer(int p1, int p2) {
<<<<<<< HEAD
>>>>>>> 332a13af8dc829b0579443a7f136d829d87f2b19:src/common/advtech/mods/DarkShadows/gui/GuiStreamFurnace.java
=======
>>>>>>> 3cd30d74137c9ad747abfdbae13a7d74cd7ac706:src/common/advtech/mods/DarkShadows/gui/GuiStreamFurnace.java
>>>>>>> Advtech92-master
		fontRenderer.drawString("Stream Furnace", 6, 6, 0xFFFFFF);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 94, 0xFFFFFF);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int texture = mc.renderEngine.getTexture("/advtech/ds/resource/streamFurnace.png");
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
