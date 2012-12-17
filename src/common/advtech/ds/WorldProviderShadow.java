package advtech.ds;

import net.minecraft.src.*;

public class WorldProviderShadow extends WorldProvider {

	@Override
	public String getDimensionName() {
		return "Shadow Realm";
	}

	public double getVoidFogYFactor()
    {
		return 1.0D;
    }
	
	protected void generateLightBrightnessTable()
    {
        float var1 = 0.0F;

        // change the next line to this:
        // for (int var2 = 0; var2 <= 15; ++var2)
        // to make it bright again
        for (int var2 = 0; var2 <= 0; ++var2)
        {
            float var3 = 1.0F - (float)var2 / 15.0F;
            this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
        }
    }
	
	public boolean getWorldHasVoidParticles()
    {
		return true;
    }
	
	public String getWelcomeMessage()
    {
		return "Entering the Shadow Realm";
    }
	
	public String getDepartMessage()
    {
		return "Leaving the Shadow Realm";
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
	
	public boolean isDaytime()
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
