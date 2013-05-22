package advtech.ds.dimension.shadow;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import advtech.ds.DarkenedSouls;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockShadowPortal extends BlockBreakable {

	public BlockShadowPortal(int par1) {
		super(par1, "ShadowPortal", Material.portal, false);
		this.setTickRandomly(true);
	}

	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);

		if (par1World.provider.isSurfaceWorld()
				&& par5Random.nextInt(2000) < par1World.difficultySetting) {
			int var6;

			for (var6 = par3; !par1World.doesBlockHaveSolidTopSurface(par2,
					var6, par4) && var6 > 0; --var6) {
				;
			}

			if (var6 > 0 && !par1World.isBlockNormalCube(par2, var6 + 1, par4)) {
				Entity var7 = ItemMonsterPlacer.spawnCreature(par1World, 57,
						(double) par2 + 0.5D, (double) var6 + 1.1D,
						(double) par4 + 0.5D);

				if (var7 != null) {
					var7.timeUntilPortal = var7.getPortalCooldown();
				}
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		float var5;
		float var6;

		if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) != this.blockID
				&& par1IBlockAccess.getBlockId(par2 + 1, par3, par4) != this.blockID) {
			var5 = 0.125F;
			var6 = 0.5F;
			this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5,
					1.0F, 0.5F + var6);
		} else {
			var5 = 0.5F;
			var6 = 0.125F;
			this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5,
					1.0F, 0.5F + var6);
		}
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean tryToCreatePortal(World world, int x, int y,
 int z) {
		byte b0 = 0;
		byte b1 = 0;

		if (world.getBlockId(x - 1, y, z) == DarkenedSouls.shadowStone.blockID
				|| world.getBlockId(x + 1, y, z) == DarkenedSouls.shadowStone.blockID) {
			b0 = 1;
		}

		if (world.getBlockId(x, y, z - 1) == DarkenedSouls.shadowStone.blockID
				|| world.getBlockId(x, y, z + 1) == DarkenedSouls.shadowStone.blockID)
			;
		{
			b1 = 1;
		}

		if (b0 == b1) {
			return false;
		} else {
			if (world.getBlockId(x - b0, y, z - b1) == 0) {
				x -= b0;
				z -= b1;
			}

			int l;
			int i1;

			for (l = -1; l <= 2; ++l) {
				for (i1 = -1; i1 <= 3; ++i1) {
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

					if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
						int j1 = world.getBlockId(x + b0 * l, y + i1,
								z + b1 * l);

						if (flag) {
							if (j1 != DarkenedSouls.shadowStone.blockID) {
								return false;
							}
						} else if (j1 != 0 && j1 != Block.fire.blockID) {
							return false;
						}
					}
				}
			}

			for (l = 0; l < 2; ++l) {
				for (i1 = 0; i1 < 3; ++i1) {
					world.setBlock(x + b0 * l, y + i1, z + b1 * l,
							Block.portal.blockID, 0, 2);
				}
			}

			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID) {
			return false;
		} else {
			boolean var6 = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID
					&& par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID;
			boolean var7 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID
					&& par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID;
			boolean var8 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID
					&& par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID;
			boolean var9 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID
					&& par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID;
			boolean var10 = var6 || var7;
			boolean var11 = var8 || var9;
			return var10 && par5 == 4 ? true : (var10 && par5 == 5 ? true
					: (var11 && par5 == 2 ? true : var11 && par5 == 3));
		}
	}

	public void onNeighborBlockChange(World world, int x, int y,
			int z, int par5) {
		byte b0 = 0;
		byte b1 = 1;

		if (world.getBlockId(x - 1, y, z) == this.blockID
				|| world.getBlockId(x + 1, y, z) == this.blockID) {
			b0 = 1;
			b1 = 0;
		}

		int i1;

		for (i1 = y; world.getBlockId(x, i1 - 1, z) == this.blockID; --i1) {
			;
		}

		if (world.getBlockId(x, i1 - 1, z) != DarkenedSouls.shadowStone.blockID) {
			world.setBlockToAir(x, y, z);
		} else {
			int j1;

			for (j1 = 1; j1 < 4
					&& world.getBlockId(x, i1 + j1, z) == this.blockID; ++j1) {
				;
			}

			if (j1 == 3
					&& world.getBlockId(x, i1 + j1, z) == DarkenedSouls.shadowStone.blockID) {
				boolean flag = world.getBlockId(x - 1, y, z) == this.blockID
						|| world.getBlockId(x + 1, y, z) == this.blockID;
				boolean flag1 = world.getBlockId(x, y, z - 1) == this.blockID
						|| world.getBlockId(x, y, z + 1) == this.blockID;

				if (flag && flag1) {
					world.setBlockToAir(x, y, z);
				} else {
					if ((world.getBlockId(x + b0, y, z + b1) != DarkenedSouls.shadowStone.blockID || world
							.getBlockId(x - b0, y, z - b1) != this.blockID)
							&& (world
									.getBlockId(x - b0, y, z - b1) != DarkenedSouls.shadowStone.blockID || world
									.getBlockId(x + b0, y, z + b1) != this.blockID)) {
						world.setBlockToAir(x, y, z);
					}
				}
			} else {
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1) {
		return 0x8a8a8a;
	}

	public int quantityDropped(Random par1Random) {
		return 0;
	}

	public void onEntityCollidedWithBlock(World world, int i, int j, int k,
			Entity entity) {
		if (entity.ridingEntity == null && entity.riddenByEntity == null
				&& entity instanceof EntityPlayerMP) {
			if (entity instanceof EntityPlayerMP) {
				EntityPlayerMP thePlayer = (EntityPlayerMP) entity;
				if (entity.dimension != 70) {
					Minecraft.getMinecraft().thePlayer.timeInPortal = 100000;
					((EntityPlayer) MinecraftServer.getServer()
							.getConfigurationManager().playerEntityList.get(0)).timeUntilPortal = 10000;
					thePlayer.mcServer.getConfigurationManager()
							.transferPlayerToDimension(
									thePlayer,
									70,
									new TeleporterShadow(thePlayer.mcServer
											.worldServerForDimension(70)));
				} else {
					Minecraft.getMinecraft().thePlayer.timeInPortal = 10000;
					((EntityPlayer) MinecraftServer.getServer()
							.getConfigurationManager().playerEntityList.get(0)).timeUntilPortal = 10000;
					thePlayer.mcServer.getConfigurationManager()
							.transferPlayerToDimension(
									thePlayer,
									0,
									new TeleporterShadow(thePlayer.mcServer
											.worldServerForDimension(0)));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		if (par5Random.nextInt(100) == 0) {
			par1World
					.playSoundEffect((double) par2 + 0.5D,
							(double) par3 + 0.5D, (double) par4 + 0.5D,
							"portal.portal", 0.5F,
							par5Random.nextFloat() * 0.4F + 0.8F);
		}

		for (int var6 = 0; var6 < 4; ++var6) {
			double var7 = (double) ((float) par2 + par5Random.nextFloat());
			double var9 = (double) ((float) par3 + par5Random.nextFloat());
			double var11 = (double) ((float) par4 + par5Random.nextFloat());
			double var13 = 0.0D;
			double var15 = 0.0D;
			double var17 = 0.0D;
			int var19 = par5Random.nextInt(2) * 2 - 1;
			var13 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			var15 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			var17 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;

			if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID
					&& par1World.getBlockId(par2 + 1, par3, par4) != this.blockID) {
				var7 = (double) par2 + 0.5D + 0.25D * (double) var19;
				var13 = (double) (par5Random.nextFloat() * 2.0F * (float) var19);
			} else {
				var11 = (double) par4 + 0.5D + 0.25D * (double) var19;
				var17 = (double) (par5Random.nextFloat() * 2.0F * (float) var19);
			}

			par1World.spawnParticle("portal", var7, var9, var11, var13, var15,
					var17);
		}
	}

	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return 0;
	}
}
