package nmd.primal.forgecraft.items.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.ModInfo;

import static nmd.primal.forgecraft.ForgeCraft.proxy;

/**
 * Created by mminaie on 7/3/17.
 */
public class CustomLeggings extends ItemArmor{

    public CustomLeggings(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
        super(materialIn, renderIndexIn, equipmentSlotIn);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ModInfo.TAB_FORGECRAFT);

    }

    public String getName() {
        return this.getRegistryName().toString();
    }

    public static boolean isHidden()
    {
        return false;
    }



}
