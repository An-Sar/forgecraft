package nmd.primal.forgecraft.items.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.models.ModelPlateHelmet;
import nmd.primal.forgecraft.proxy.ClientProxy;

import javax.annotation.Nullable;

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

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        //ModelBiped armorModel = ItemsRegister.armorModels.get(this);
        ModelBiped armorModel = ClientProxy.armorModels.get(this);

        if (armorModel != null)
        {
            armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
            armorModel.bipedHeadwear.showModel = false;
            armorModel.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST || armorSlot == EntityEquipmentSlot.LEGS;
            armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
            armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
            armorModel.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
            armorModel.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();

        }
        return armorModel;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return ModInfo.MOD_ID + ":textures/armor/hide.png";
    }

}
