package advtech.ds;

import net.minecraft.src.*;

public class WorldProviderShadow extends WorldProvider {

	@Override
	public String getDimensionName() {
		return "Shadow Realm";
	}

	public void registerWorldChunkManager()
    {
		super.registerWorldChunkManager();
		this.dimensionId = DarkShadows.shadowDimensionID;
    }
	
	public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderShadow(this.worldObj, this.worldObj.getSeed());
    }
	
	public boolean isSurfaceWorld()
    {
        return false;
    }
	
	public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return false;
    }
	
	public boolean canRespawnHere()
    {
        return false;
    }
	
	public boolean doesXZShowFog(int par1, int par2)
    {
        return true;
    }
}
