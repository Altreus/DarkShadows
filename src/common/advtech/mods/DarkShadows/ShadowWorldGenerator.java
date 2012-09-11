package advtech.mods.DarkShadows;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class ShadowWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.worldType) {
		case -1:
			generateNether(rand, chunkX*16, chunkZ*16, world);
			break;
		case 0:
			generateSurface(rand, chunkX*16, chunkZ*16, world);
			break;
		}
	}
	
	public void generateSurface(Random rand, int chunkX, int chunkZ, World world) {
		for (int i = 0; i < 25; i++) {
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(64);
			int randPosZ = chunkZ + rand.nextInt(16);
				
			(new WorldGenMinable(DarkShadow.oreObby.blockID, 20)).generate(world, rand, randPosX, randPosY, randPosZ);
		}
	}

	public void generateNether(Random rand, int chunkX, int chunkZ, World world) {
		//
	}
	
	

}
