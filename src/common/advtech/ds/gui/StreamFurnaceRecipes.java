package advtech.ds.gui;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import advtech.ds.DarkShadows;

public class StreamFurnaceRecipes {
	
	public static final StreamFurnaceRecipes recipes = new StreamFurnaceRecipes();
	
	private Map list = new HashMap();
	private Map exp = new HashMap();
	
	public static final StreamFurnaceRecipes smelting() {
		return recipes;
	}
	
	private StreamFurnaceRecipes() {
		addSmelting(DarkShadows.oreObby.blockID, new ItemStack(Block.obsidian), 0.7F);
		addSmelting(Block.obsidian.blockID, new ItemStack(DarkShadows.obbyIngot),0.7F);
	}
	
	public void addSmelting(int id, ItemStack stack, float experience) {
		list.put(Integer.valueOf(id), stack);
		exp.put(Integer.valueOf(stack.itemID), Float.valueOf(experience));
	}
	
	public ItemStack getSmeltingResult(int id) {
		return (ItemStack)list.get(Integer.valueOf(id));
	}
	
	public Map getSmeltingList() {
		return list;
	}
	
	public float getExperience(int i) {
		return this.exp.containsKey(Integer.valueOf(i)) ? ((Float)exp.get(Integer.valueOf(i))).floatValue() : 0.0F;
	}
}
