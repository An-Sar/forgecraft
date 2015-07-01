package com.kitsu.medievalcraft.item.tools;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import com.kitsu.medievalcraft.Main;
import com.kitsu.medievalcraft.item.ModItems;
import com.kitsu.medievalcraft.util.CustomTab;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CustomHandleStrongIronLumberAxe extends ItemAxe{

	private String name = "customHandleStrongIronLumberAxe";
	private Item item;
	
	public CustomHandleStrongIronLumberAxe(String name, ToolMaterial mat) {
		super(mat);
		item = this;
		setUnlocalizedName(name);
		setCreativeTab(CustomTab.MedievalCraftTab);
		setTextureName(Main.MODID + ":" + "customHandleIronLumberAxe");
		this.toolMaterial = ModItems.customIronToolRodStrong;
		GameRegistry.registerItem(this, name);
	}
	@Override
	public int getItemEnchantability () {
		return 0;
	}
		
    @Override
    public boolean getIsRepairable(ItemStack item, ItemStack repair)
    {
        return Items.iron_ingot == repair.getItem() ? true : false;
    }
    
	 @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack par1ItemStack)
	    {
	        return false;
	    }
}
	 
