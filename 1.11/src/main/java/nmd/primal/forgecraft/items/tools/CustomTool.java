package nmd.primal.forgecraft.items.tools;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.forgecraft.ModInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Created by mminaie on 3/9/17.
 */
public abstract class CustomTool extends ItemTool {

    public Item.ToolMaterial material;
    private String toolClass;
    private final float attackDamage;

    public CustomTool(String name, Item.ToolMaterial material, float attack_damage, float attack_speed, Set<Block> effective_on) {

        super(attack_damage, attack_speed, material, effective_on);

        this.attackDamage = 2.0F + material.getDamageVsEntity();
        this.material = material;
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(ModInfo.TAB_FORGECRAFT);
        this.setMaxStackSize(1);
        this.setNoRepair();

        this.addPropertyOverride(new ResourceLocation("type"), new IItemPropertyGetter()
        {

            /***

             hot . emerald . diamond . redstone . lapis
             0  .    0    .    0    .     0    .   0

             ***/

            @SideOnly(Side.CLIENT)
            public float apply(ItemStack item, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (item.hasTagCompound()) {


                    if (item.getSubCompound("tags").getBoolean("hot") == false) {
                        if (item.getSubCompound("tags").getInteger("modifiers") != 0) {
                            if ((item.getSubCompound("tags").getBoolean("emerald") == true) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.1F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == true) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 1) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.11F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == true) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 2) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.12F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == true) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 1) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 1) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.111F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == true) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 2) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.102F;
                            }

                            // ============

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 1) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.01F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 2) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.02F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 3) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.03F;
                            }

                            //=======

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 1) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.001F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 2) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.002F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 3) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.003F;
                            }

                            //=========

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 1)) {
                                return 0.0001F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 2)) {
                                return 0.0002F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 3)) {
                                return 0.0003F;
                            }

                            //=======

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 1) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 1) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 1)) {
                                return 0.0111F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 2) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 1) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.021F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 1) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 2) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 0)) {
                                return 0.012F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 1) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 2)) {
                                return 0.0012F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 0) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 2) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 1)) {
                                return 0.0021F;
                            }

                            if ((item.getSubCompound("tags").getBoolean("emerald") == false) &&
                                    (item.getSubCompound("tags").getInteger("diamond") == 1) &&
                                    (item.getSubCompound("tags").getInteger("redstone") == 0) &&
                                    (item.getSubCompound("tags").getInteger("lapis") == 2)) {
                                return 0.0102F;
                            }
                        }
                    }
                    if (item.getSubCompound("tags").getBoolean("hot") == true) {
                        return 1.0F;
                    }

                    if (item.getSubCompound("tags").getBoolean("hot") == false) {
                        if (item.getSubCompound("tags").getInteger("modifiers") == 0) {
                            return 0.0F;
                        }
                    }
                }
                return 0.0F;
            }
        });

    }

    public static boolean isHidden()
    {
        return false;
    }

    @Override
    public void onCreated(ItemStack item, World worldIn, EntityPlayer playerIn) {

        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
            NBTTagCompound tags = new NBTTagCompound();

            item.getTagCompound().setTag("tags", tags);

            item.getSubCompound("tags").setBoolean("hot", false);

            item.getSubCompound("tags").setBoolean("emerald", false);
            item.getSubCompound("tags").setInteger("diamond", 0);
            item.getSubCompound("tags").setInteger("redstone", 0);
            item.getSubCompound("tags").setInteger("lapis", 0);

            item.getSubCompound("tags").setInteger("modifiers", 0);
        }

    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity player, int itemSlot, boolean isSelected) {
        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
            NBTTagCompound tags = new NBTTagCompound();

            item.getTagCompound().setTag("tags", tags);

            item.getSubCompound("tags").setBoolean("hot", false);

            item.getSubCompound("tags").setBoolean("emerald", false);
            item.getSubCompound("tags").setInteger("diamond", 0);
            item.getSubCompound("tags").setInteger("redstone", 0);
            item.getSubCompound("tags").setInteger("lapis", 0);

            item.getSubCompound("tags").setInteger("modifiers", 0);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List<String> tooltip, boolean advanced)
    {
        if(player.getEntityWorld().isRemote) {
            tooltip.add(ChatFormatting.GRAY + "Upgrades");
            if (item.getSubCompound("tags").getBoolean("emerald") == true) {
                tooltip.add(ChatFormatting.DARK_GREEN + "Emerald");
            }
            if (item.getSubCompound("tags").getInteger("diamond") > 0) {
                tooltip.add(ChatFormatting.AQUA + "Diamond Level: " + item.getSubCompound("tags").getInteger("diamond"));
            }
            if (item.getSubCompound("tags").getInteger("redstone") > 0) {
                tooltip.add(ChatFormatting.RED + "Redstone Level: " + item.getSubCompound("tags").getInteger("redstone"));
            }
            if (item.getSubCompound("tags").getInteger("lapis") > 0) {
                tooltip.add(ChatFormatting.BLUE + "Lapis Level: " + item.getSubCompound("tags").getInteger("lapis"));
            }
        }


    }

    @Override
    public boolean isRepairable()
    {
        return false;
    }

    public int getItemEnchantability(ItemStack stack)
    {
        return 0;
    }

}
