package advtech.ds.core.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {

	@Override
<<<<<<< HEAD:src/common/advtech/ds/core/packet/ServerPacketHandler.java
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(null));
=======
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
>>>>>>> 3cd30d74137c9ad747abfdbae13a7d74cd7ac706:src/common/advtech/mods/DarkShadows/gui/ServerPacketHandler.java
		EntityPlayer sender = (EntityPlayer) player;
		
	}
}
