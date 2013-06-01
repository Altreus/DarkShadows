package advtech.ds.dimension.shadow;

import java.util.Random;

import advtech.ds.DarkenedSouls;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenShadeStone1 extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        if (!par1World.isAirBlock(par3, par4, par5))
        {
            return false;
        }
        else if (par1World.getBlockId(par3, par4 + 1, par5) != Block.netherrack.blockID)
        {
            return false;
        }
        else
        {
            par1World.setBlock(par3, par4, par5, DarkenedSouls.ShadeStone.blockID);

            for (int var6 = 0; var6 < 1500; ++var6)
            {
                int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
                int var8 = par4 - par2Random.nextInt(12);
                int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

                if (par1World.getBlockId(var7, var8, var9) == 0)
                {
                    int var10 = 0;

                    for (int var11 = 0; var11 < 6; ++var11)
                    {
                        int var12 = 0;

                        if (var11 == 0)
                        {
                            var12 = par1World.getBlockId(var7 - 1, var8, var9);
                        }

                        if (var11 == 1)
                        {
                            var12 = par1World.getBlockId(var7 + 1, var8, var9);
                        }

                        if (var11 == 2)
                        {
                            var12 = par1World.getBlockId(var7, var8 - 1, var9);
                        }

                        if (var11 == 3)
                        {
                            var12 = par1World.getBlockId(var7, var8 + 1, var9);
                        }

                        if (var11 == 4)
                        {
                            var12 = par1World.getBlockId(var7, var8, var9 - 1);
                        }

                        if (var11 == 5)
                        {
                            var12 = par1World.getBlockId(var7, var8, var9 + 1);
                        }

                        if (var12 == DarkenedSouls.ShadeStone.blockID)
                        {
                            ++var10;
                        }
                    }

                    if (var10 == 1)
                    {
                        par1World.setBlock(var7, var8, var9, DarkenedSouls.ShadeStone.blockID);
                    }
                }
            }

            return true;
        }
    }
}