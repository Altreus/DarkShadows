package advtech.mods.DarkShadows.gui;

import java.util.HashMap;
import java.util.Map;

import advtech.mods.DarkShadows.DarkShadow;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class StreamFurnaceRecipes {
	
	public static final StreamFurnaceRecipes recipes = new StreamFurnaceRecipes();
	
	private Map list = new HashMap();
	private Map exp = new HashMap();
	
	public static final StreamFurnaceRecipes smelting() {
		return recipes;
	}
	
	private StreamFurnaceRecipes() {
		addSmelting(DarkShadow.oreObby.blockID, new ItemStack(Block.obsidian, 1), 0.7F);
		addSmelting(Block.obsidian.blockID, new ItemStack(DarkShadow.obbyItem,1,0),0.7F);
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
