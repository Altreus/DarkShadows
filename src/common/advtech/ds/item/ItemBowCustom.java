package advtech.ds.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemBowCustom extends ItemBow {

	public int essence;
	public boolean showDamage;
	
	public ItemBowCustom(int id) {
		this(id, false);
	}
	
	public ItemBowCustom(int id, boolean showDamage) {
		super(id);
		this.showDamage = showDamage;
		bFull3D = true;
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldObj, EntityPlayer player, int par4)
    {
        int chargeTime = this.getMaxItemUseDuration(stack) - par4;
        
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, chargeTime);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        chargeTime = event.charge;
        
        boolean shouldUseArrow = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (shouldUseArrow || playerCanShoot(player))
        {
            float chargePerc = (float)chargeTime / 20.0F;
            chargePerc = (chargePerc * chargePerc + chargePerc * 2.0F) / 3.0F;

            if ((double)chargePerc < 0.1D)
            {
                return;
            }

            if (chargePerc > 1.0F)
            {
                chargePerc = 1.0F;
            }

            EntityArrow arrow = applyEffects(new EntityArrowCustom(worldObj, player, chargePerc * 2.0F), chargePerc * 2.0F);

            if (chargePerc == 1.0F)
            {
                arrow.setIsCritical(true);
            }

            int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

            if (power > 0)
            {
                arrow.setDamage(arrow.getDamage() + (double)power * 0.5D + 0.5D);
            }

            int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

            if (punch > 0)
            {
                arrow.setKnockbackStrength(punch);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
            {
                arrow.setFire(100);
            }

            stack.damageItem(1, player);
            worldObj.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + chargePerc * 0.5F);

            if (shouldUseArrow)
            {
                arrow.canBePickedUp = 2;
            }
            else
            {
                player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
            }

            if (!worldObj.isRemote)
            {
                worldObj.spawnEntityInWorld(arrow);
            }
        }
    }

	/**
	 * Applies effects to the arrow. Also used to remove extra items from inventory.
	 * @param entityArrowCustom
	 * @param velocity
	 * @return
	 */
	public EntityArrow applyEffects(EntityArrowCustom entityArrowCustom, float velocity) {
		return entityArrowCustom;
	}
	
	public boolean playerCanShoot(EntityPlayer player) {
		return player.inventory.hasItem(Item.arrow.shiftedIndex) && player.inventory.hasItem(essence);
	}
	
	public void shootEffects(ItemStack stack, EntityPlayer player, ItemStack usingItem, int useRemaining) {}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }
        
        if (par3EntityPlayer.capabilities.isCreativeMode || playerCanShoot(par3EntityPlayer))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }
	
	public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if (usingItem != null && usingItem.getItem() instanceof ItemBow)
        {
        	shootEffects(stack, player, usingItem, useRemaining);
            int k = usingItem.getMaxItemUseDuration() - useRemaining;
            if (k >= 18) {
            	// failed attempt for shaky screen:
            	//net.minecraft.client.Minecraft.getMinecraft().effectRenderer
            	//player.cameraYaw = player.prevCameraYaw = 10;
            	//player.cameraYaw += 1;
            	return getIconIndex(stack) + 3;
            }
            if (k >  13) return getIconIndex(stack) + 2;
            if (k >   0) return getIconIndex(stack) + 1;
           ModLoader.getMinecraftInstance().gameSettings.fovSetting -= (float) k / 100F;
        }
        return getIconIndex(stack);
    }
	
	public void addInformation(ItemStack par1ItemStack, List par2List) {
		if (showDamage) par2List.add("Damage Left: " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) + "/" + par1ItemStack.getMaxDamage());
	}
	
	public String getTextureFile()
    {
		return "/TehPers/TexMagic.png";
    }
}
