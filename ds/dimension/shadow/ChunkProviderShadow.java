package advtech.ds.dimension.shadow;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;

public class ChunkProviderShadow implements IChunkProvider {

	public Random rand = new Random();
	private int worldBlockId = Block.obsidian.blockID;//DarkShadows.shadowStone.blockID;
	private double[] noiseField;
	double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    private NoiseGeneratorOctaves netherNoiseGen1;
    private NoiseGeneratorOctaves netherNoiseGen2;
    private NoiseGeneratorOctaves netherNoiseGen3;
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves netherNoiseGen6;
    public NoiseGeneratorOctaves netherNoiseGen7;
    private World worldObj;
    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] netherrackExclusivityNoise = new double[256];
    private MapGenBase shadowCaveGenerator = new MapGenCavesHell();
	
    public ChunkProviderShadow(World par1World, long par2) {
    	this.worldObj = par1World;
        this.rand = new Random(par2);
        this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.rand, 10);
        this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.rand, 16);
    }
    
	@Override
	public Chunk provideChunk(int chunkX, int chunkY) {
		rand.setSeed((long)chunkX * 341873128712L + (long)chunkY * 132897987541L);
        byte[] var3 = new byte[32768];
        generateTerrain(chunkX, chunkY, var3);
        replaceBlocksForBiome(chunkX, chunkY, var3);
        shadowCaveGenerator.generate(this, worldObj, chunkX, chunkY, var3);
        Chunk var4 = new Chunk(worldObj, var3, chunkX, chunkY);
        BiomeGenBase[] var5 = worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, chunkX * 16, chunkY * 16, 16, 16);
        byte[] var6 = var4.getBiomeArray();

        for (int var7 = 0; var7 < var6.length; ++var7)
        {
            var6[var7] = (byte)var5[var7].biomeID;
        }

        var4.resetRelightChecks();
        return var4;
	}

	public void generateTerrain(int chunkX, int chunkY, byte[] par3ArrayOfByte)
    {
        byte var4 = 4;
        byte var5 = 32;
        int var6 = var4 + 1;
        byte var7 = 17;
        int var8 = var4 + 1;
        this.noiseField = this.initializeNoiseField(this.noiseField, chunkX * var4, 0, chunkY * var4, var6, var7, var8);

        for (int var9 = 0; var9 < var4; ++var9)
        {
            for (int var10 = 0; var10 < var4; ++var10)
            {
                for (int var11 = 0; var11 < 16; ++var11)
                {
                    double var12 = 0.125D;
                    double var14 = this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var16 = this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var18 = this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var20 = this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var22 = (this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
                    double var26 = (this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
                    double var28 = (this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;

                    for (int var30 = 0; var30 < 8; ++var30)
                    {
                        double var31 = 0.25D;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;

                        for (int var41 = 0; var41 < 4; ++var41)
                        {
                            int var42 = var41 + var9 * 4 << 11 | 0 + var10 * 4 << 7 | var11 * 8 + var30;
                            short var43 = 128;
                            double var44 = 0.25D;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for (int var50 = 0; var50 < 4; ++var50)
                            {
                                int var51 = 0;

                                if (var11 * 8 + var30 < var5)
                                {
                                    var51 = Block.lavaStill.blockID;
                                }

                                if (var46 > 0.0D)
                                {
                                    var51 = worldBlockId;
                                }

                                par3ArrayOfByte[var42] = (byte)var51;
                                var42 += var43;
                                var46 += var48;
                            }

                            var33 += var37;
                            var35 += var39;
                        }

                        var14 += var22;
                        var16 += var24;
                        var18 += var26;
                        var20 += var28;
                    }
                }
            }
        }
    }
	
	private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double var8 = 684.412D;
        double var10 = 2053.236D;
        this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
        this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
        this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 60.0D, var8 / 80.0D);
        this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;
        double[] var14 = new double[par6];
        int var15;

        for (var15 = 0; var15 < par6; ++var15)
        {
            var14[var15] = Math.cos((double)var15 * Math.PI * 6.0D / (double)par6) * 2.0D;
            double var16 = (double)var15;

            if (var15 > par6 / 2)
            {
                var16 = (double)(par6 - 1 - var15);
            }

            if (var16 < 4.0D)
            {
                var16 = 4.0D - var16;
                var14[var15] -= var16 * var16 * var16 * 10.0D;
            }
        }

        for (var15 = 0; var15 < par5; ++var15)
        {
            for (int var36 = 0; var36 < par7; ++var36)
            {
                double var17 = (this.noiseData4[var13] + 256.0D) / 512.0D;

                if (var17 > 1.0D)
                {
                    var17 = 1.0D;
                }

                double var19 = 0.0D;
                double var21 = this.noiseData5[var13] / 8000.0D;

                if (var21 < 0.0D)
                {
                    var21 = -var21;
                }

                var21 = var21 * 3.0D - 3.0D;

                if (var21 < 0.0D)
                {
                    var21 /= 2.0D;

                    if (var21 < -1.0D)
                    {
                        var21 = -1.0D;
                    }

                    var21 /= 1.4D;
                    var21 /= 2.0D;
                    var17 = 0.0D;
                }
                else
                {
                    if (var21 > 1.0D)
                    {
                        var21 = 1.0D;
                    }

                    var21 /= 6.0D;
                }

                var17 += 0.5D;
                var21 = var21 * (double)par6 / 16.0D;
                ++var13;

                for (int var23 = 0; var23 < par6; ++var23)
                {
                    double var24 = 0.0D;
                    double var26 = var14[var23];
                    double var28 = this.noiseData2[var12] / 512.0D;
                    double var30 = this.noiseData3[var12] / 512.0D;
                    double var32 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;

                    if (var32 < 0.0D)
                    {
                        var24 = var28;
                    }
                    else if (var32 > 1.0D)
                    {
                        var24 = var30;
                    }
                    else
                    {
                        var24 = var28 + (var30 - var28) * var32;
                    }

                    var24 -= var26;
                    double var34;

                    if (var23 > par6 - 4)
                    {
                        var34 = (double)((float)(var23 - (par6 - 4)) / 3.0F);
                        var24 = var24 * (1.0D - var34) + -10.0D * var34;
                    }

                    if ((double)var23 < var19)
                    {
                        var34 = (var19 - (double)var23) / 4.0D;

                        if (var34 < 0.0D)
                        {
                            var34 = 0.0D;
                        }

                        if (var34 > 1.0D)
                        {
                            var34 = 1.0D;
                        }

                        var24 = var24 * (1.0D - var34) + -10.0D * var34;
                    }

                    par1ArrayOfDouble[var12] = var24;
                    ++var12;
                }
            }
        }

        return par1ArrayOfDouble;
    }

	public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte)
    {
        byte var4 = 64;
        double var5 = 0.03125D;
        this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var5, var5, 1.0D);
        this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, par1 * 16, 109, par2 * 16, 16, 1, 16, var5, 1.0D, var5);
        this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);

        for (int var7 = 0; var7 < 16; ++var7)
        {
            for (int var8 = 0; var8 < 16; ++var8)
            {
                boolean var9 = this.slowsandNoise[var7 + var8 * 16] + rand.nextDouble() * 0.2D > 0.0D;
                boolean var10 = this.gravelNoise[var7 + var8 * 16] + rand.nextDouble() * 0.2D > 0.0D;
                int var11 = (int)(this.netherrackExclusivityNoise[var7 + var8 * 16] / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
                int var12 = -1;
                byte var13 = (byte)worldBlockId;
                byte var14 = (byte)worldBlockId;

                for (int var15 = 127; var15 >= 0; --var15)
                {
                    int var16 = (var8 * 16 + var7) * 128 + var15;

                    if (var15 < 127 - rand.nextInt(5) && var15 > 0 + rand.nextInt(5))
                    {
                        byte var17 = par3ArrayOfByte[var16];

                        if (var17 == 0)
                        {
                            var12 = -1;
                        }
                        else if (var17 == worldBlockId)
                        {
                            if (var12 == -1)
                            {
                                if (var11 <= 0)
                                {
                                    var13 = 0;
                                    var14 = (byte)worldBlockId;
                                }
                                else if (var15 >= var4 - 4 && var15 <= var4 + 1)
                                {
                                    var13 = (byte)worldBlockId;
                                    var14 = (byte)worldBlockId;

                                    if (var10)
                                    {
                                        var13 = (byte)Block.gravel.blockID;
                                    }

                                    if (var10)
                                    {
                                        var14 = (byte)worldBlockId;
                                    }

                                    if (var9)
                                    {
                                        var13 = (byte)Block.slowSand.blockID;
                                    }

                                    if (var9)
                                    {
                                        var14 = (byte)Block.slowSand.blockID;
                                    }
                                }

                                if (var15 < var4 && var13 == 0)
                                {
                                    var13 = (byte)Block.lavaStill.blockID;
                                }

                                var12 = var11;

                                if (var15 >= var4 - 1)
                                {
                                    par3ArrayOfByte[var16] = var13;
                                }
                                else
                                {
                                    par3ArrayOfByte[var16] = var14;
                                }
                            }
                            else if (var12 > 0)
                            {
                                --var12;
                                par3ArrayOfByte[var16] = var14;
                            }
                        }
                    }
                    else
                    {
                        par3ArrayOfByte[var16] = (byte)Block.bedrock.blockID;
                    }
                }
            }
        }
    }

	
	@Override
	public boolean chunkExists(int var1, int var2) {
		return true;
	}

	@Override
	public Chunk loadChunk(int chunkX, int chunkY) {
		return provideChunk(chunkX, chunkY);
	}

	@Override
	public void populate(IChunkProvider var1, int chunkX, int chunkY) {
        BlockSand.fallInstantly = true;
        int thisChunkX = chunkX * 16;
        int thisChunkY = chunkY * 16;
        int var6;
        int var7;
        int var8;
        int var9;

        for (var6 = 0; var6 < 8; ++var6)
        {
            var7 = thisChunkX + rand.nextInt(16) + 8;
            var8 = rand.nextInt(120) + 4;
            var9 = thisChunkY + rand.nextInt(16) + 8;
            (new WorldGenHellLava(Block.lavaMoving.blockID)).generate(this.worldObj, rand, var7, var8, var9);
        }

        var6 = rand.nextInt(rand.nextInt(10) + 1) + 1;
        int var10;

        for (var7 = 0; var7 < var6; ++var7)
        {
            var8 = thisChunkX + rand.nextInt(16) + 8;
            var9 = rand.nextInt(120) + 4;
            var10 = thisChunkY + rand.nextInt(16) + 8;
            (new WorldGenFire()).generate(this.worldObj, rand, var8, var9, var10);
        }

        var6 = rand.nextInt(rand.nextInt(10) + 1);

        for (var7 = 0; var7 < var6; ++var7)
        {
            var8 = thisChunkX + rand.nextInt(16) + 8;
            var9 = rand.nextInt(120) + 4;
            var10 = thisChunkY + rand.nextInt(16) + 8;
            (new WorldGenGlowStone1()).generate(this.worldObj, rand, var8, var9, var10);
        }

        for (var7 = 0; var7 < 10; ++var7)
        {
            var8 = thisChunkX + rand.nextInt(16) + 8;
            var9 = rand.nextInt(128);
            var10 = thisChunkY + rand.nextInt(16) + 8;
            (new WorldGenGlowStone2()).generate(this.worldObj, rand, var8, var9, var10);
        }
        for (var7 = 0; var7 < var6; ++var7)
        {
        	var8 = thisChunkX + rand.nextInt(16) + 8;
        	var9 = rand.nextInt(120) + 4;
        	var10 = thisChunkY + rand.nextInt(16) + 8;
        	(new WorldGenShadeStone1()).generate(this.worldObj, rand, var8, var9, var10);
        }
        for (var7 = 0; var7 < var6; ++var7)
        {
        	var8 = thisChunkX + rand.nextInt(16) + 8;
        	var9 = rand.nextInt(120) + 4;
        	var10 = thisChunkY + rand.nextInt(16) +8;
        	(new WorldGenShadowStone2()).generate(this.worldObj, rand, var8, var9, var10);
        }

        if (rand.nextInt(1) == 0)
        {
            var7 = thisChunkX + rand.nextInt(16) + 8;
            var8 = rand.nextInt(128);
            var9 = thisChunkY + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(this.worldObj, rand, var7, var8, var9);
        }

        if (rand.nextInt(1) == 0)
        {
            var7 = thisChunkX + rand.nextInt(16) + 8;
            var8 = rand.nextInt(128);
            var9 = thisChunkY + rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(this.worldObj, rand, var7, var8, var9);
        }

        BlockSand.fallInstantly = false;
	}

	@Override
	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		return true;
	}

	@Override
	public boolean unload100OldestChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return null;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4) {
		return null;
	}

	@Override
	public ChunkPosition findClosestStructure(World var1, String var2, int var3, int var4, int var5) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int var1, int var2) {
		
	}

}