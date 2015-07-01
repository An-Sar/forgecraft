package com.kitsu.medievalcraft.block.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.kitsu.medievalcraft.Main;
import com.kitsu.medievalcraft.util.CustomTab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EggWashedWall extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon iconSide;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconBot;

	public EggWashedWall(String unlocalizedName, Material mat) {
		super(mat);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CustomTab.MedievalCraftTab);
		this.setHardness(3.0F);
		this.setResistance(6.0F);
		this.setStepSound(Block.soundTypeStone);

	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Main.MODID + ":" + "eggwashside");
		this.iconSide = iconRegister.registerIcon(Main.MODID + ":" + "eggwashside");
		this.iconBot  = iconRegister.registerIcon(Main.MODID + ":" + "eggwashtop");
		this.iconTop   = iconRegister.registerIcon(Main.MODID + ":" + "eggwashtop");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		
		return side == 1 ? this.iconTop : (side == 0 ? this.iconBot : (side != metadata ? this.blockIcon : this.iconSide));
	}

}
