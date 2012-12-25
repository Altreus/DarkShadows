package advtech.ds.item.shadow;

import net.minecraft.item.Item;

public class ShadowStoneDust extends Item {


	private int potionId;
	private int potionDuration;
	private int potionAmplifier;
	private float potionEffectProbability;
	public ShadowStoneDust(int ID) {
		super(ID);
	}	
	public ShadowStoneDust setPotionEffect(int par1, int par2, int par3, float par4)
    {
        this.potionId = par1;
        this.potionDuration = par2;
        this.potionAmplifier = par3;
        this.potionEffectProbability = par4;
        return this;
    }
}
