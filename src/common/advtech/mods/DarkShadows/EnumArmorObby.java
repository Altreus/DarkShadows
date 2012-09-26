package advtech.mods.DarkShadows;

import net.minecraft.src.ItemArmor;

public enum EnumArmorObby {
	OBBY(5, new int[]{1, 3, 2, 1}, 15);
	private int maxDamageFactor;
	private int[] damageReductionAmountArray;
	private int enchantability;
	private EnumArmorObby(int damage, int Array[], int lvl)
	{
		maxDamageFactor = damage;
		damageReductionAmountArray = Array;
		enchantability = lvl;
	}
	public int getDurability(int damage)
	{
		 return ItemObbyArmor.getMaxDamageArray()[damage] * maxDamageFactor;
	}
	public int getDamageReductionAmount(int Array)
	{
		return damageReductionAmountArray[Array];
	}
	public int getEnchantability()
	{
		return enchantability;
	}
	

}
