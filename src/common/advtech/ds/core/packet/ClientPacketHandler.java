package advtech.ds.core.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements IPacketHandler {

<<<<<<< HEAD:src/common/advtech/ds/core/packet/ClientPacketHandler.java
	/*@Override
	public void onPacketData(NetworkManager manager, Packet250CustomPayload payload, Player player) {
=======
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player) {
<<<<<<< HEAD
>>>>>>> 332a13af8dc829b0579443a7f136d829d87f2b19:src/common/advtech/mods/DarkShadows/gui/ClientPacketHandler.java
=======
>>>>>>> 3cd30d74137c9ad747abfdbae13a7d74cd7ac706:src/common/advtech/mods/DarkShadows/gui/ClientPacketHandler.java
>>>>>>> Advtech92-master
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
	}*/

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// TODO Auto-generated method stub
		
	}
}
