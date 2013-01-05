package advtech.ds.item.obby;

import net.minecraft.item.Item;
import advtech.ds.DarkenedSouls;

public class ObbyPlate extends Item {
	public ObbyPlate(int ID){
		super(ID);
		setMaxStackSize(64);

	}
	public String getTextureFile(){
		return "/advtech/ds/resources/terrain.png";
	}

}
