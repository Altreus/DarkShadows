package advtech.ds.item;

import java.util.List;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityEnderPearl;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.World;

public class SmokeScreen extends Item{
	
	public SmokeScreen(int ID){
		super(ID);
		maxStackSize = 16;
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		if (player.ridingEntity != null){
			return itemStack;
		}
		else{
			double x = player.posX;
			double y = player.posY;
			double z = player.posZ;

			List list = world.getEntitiesWithinAABB(EntityLiving.class,
					AxisAlignedBB.getBoundingBox(x - 20, y - 20, z - 20, x + 20, y + 20, z + 20));

			Random r = new Random();

			for(int j = 0; j < 1200; j++){
				world.spawnParticle("smoke", x + (r.nextDouble() * 2) - 1D, y - (r.nextDouble() * 3) + 1D, z + (r.nextDouble() * 2) - 1D,
						r.nextDouble() / 6, r.nextDouble() / 6, r.nextDouble() / 6);
			}

			for(int i = 0; i < list.size(); i++){
				EntityLiving target = (EntityLiving) list.get(i);
				if(target instanceof EntityPlayer){
					if(!(((EntityPlayer) target).entityId == player.entityId)){
						target.addPotionEffect(new PotionEffect(15, 1000, 20));
					}
				}
				else{
					target.addPotionEffect(new PotionEffect(15, 1000, 20));
				}

			}

			--itemStack.stackSize;
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!world.isRemote){
				world.spawnEntityInWorld(new EntityEnderPearl(world, player));
			}

			return itemStack;
		}
	}
}
