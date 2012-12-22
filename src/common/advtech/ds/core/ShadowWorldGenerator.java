package advtech.ds.core;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import advtech.ds.DarkShadows;
import cpw.mods.fml.common.IWorldGenerator;

public class ShadowWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
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
				
			(new WorldGenMinable(DarkShadows.oreObby.blockID, 20)).generate(world, rand, randPosX, randPosY, randPosZ);
		}
	}

	public void generateNether(Random rand, int chunkX, int chunkZ, World world) {
		//
	}
	
	

}
