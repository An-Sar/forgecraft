package nmd.primal.forgecraft.items.tools;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nmd.primal.core.common.helper.PlayerHelper;
import nmd.primal.forgecraft.ModInfo;
import nmd.primal.forgecraft.init.ModItems;
import nmd.primal.forgecraft.util.ToolNBT;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mminaie on 3/21/17.
 */
public class CustomHoe extends ItemHoe  implements ToolNBT {

    private Item drop;

    public CustomHoe(String name, Item.ToolMaterial material, Item damageDrop) {
        super(material);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(ModInfo.TAB_FORGECRAFT);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.drop = damageDrop;
        this.addPropertyOverride(new ResourceLocation("type"), new IItemPropertyGetter() {

            /***

             hot . emerald . diamond . redstone . lapis
             0  .    0    .    0    .     0    .   0

             ***/

            @SideOnly(Side.CLIENT)
            public float apply(ItemStack item, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (item.hasTagCompound()) {

                    if (getHot(item) == false) {
                        if (getModifiers(item) != 0) {
                            if ( (getEmerald(item) == true) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.1F;
                            }

                            if ( (getEmerald(item) == true) &&
                                    (getDiamondLevel(item) == 1) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.11F;
                            }

                            if ( (getEmerald(item) == true) &&
                                    (getDiamondLevel(item) == 2) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.12F;
                            }

                            if ( (getEmerald(item) == true) &&
                                    (getDiamondLevel(item) == 1) &&
                                    (getRedstoneLevel(item) == 1) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.111F;
                            }

                            if ( (getEmerald(item) == true) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 2) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.102F;
                            }

                            // ============

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 1) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.01F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 2) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.02F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 3) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.03F;
                            }

                            //=======

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 1) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.001F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 2) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.002F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 3) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.003F;
                            }

                            //=========

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 1)) {
                                return 0.0001F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 2)) {
                                return 0.0002F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 3)) {
                                return 0.0003F;
                            }

                            //=======

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 1) &&
                                    (getRedstoneLevel(item) == 1) &&
                                    (getLapisLevel(item) == 1)) {
                                return 0.0111F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 2) &&
                                    (getRedstoneLevel(item) == 1) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.021F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 1) &&
                                    (getRedstoneLevel(item) == 2) &&
                                    (getLapisLevel(item) == 0)) {
                                return 0.012F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 1) &&
                                    (getLapisLevel(item) == 2)) {
                                return 0.0012F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 0) &&
                                    (getRedstoneLevel(item) == 2) &&
                                    (getLapisLevel(item) == 1)) {
                                return 0.0021F;
                            }

                            if ( (getEmerald(item) == false) &&
                                    (getDiamondLevel(item) == 1) &&
                                    (getRedstoneLevel(item) == 0) &&
                                    (getLapisLevel(item) == 2) ) {
                                return 0.0102F;
                            }
                        }
                    }
                    if (getHot(item) == true) {
                        return 1.0F;
                    }

                    if (getHot(item) == false) {
                        if (getModifiers(item) == 0) {
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
    public void onCreated(ItemStack item, World world, EntityPlayer playerIn) {

        if(!world.isRemote) {
            if (!item.hasTagCompound()) {
                item.setTagCompound(new NBTTagCompound());
                NBTTagCompound tags = new NBTTagCompound();

                item.getTagCompound().setTag("tags", tags);

                /*setHot(item, false);

                setHot(item, false);
                setEmerald(item, false);
                setDiamondLevel(item, 0);
                setRedstoneLevel(item, 0);
                setLapisLevel(item, 0);
                setModifiers(item, 0);*/
            }
        }

    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity player, int itemSlot, boolean isSelected) {
        if(!world.isRemote) {
            if (!item.hasTagCompound()) {
                item.setTagCompound(new NBTTagCompound());
                NBTTagCompound tags = new NBTTagCompound();

                item.getTagCompound().setTag("tags", tags);

                setHot(item, false);
                setEmerald(item, false);
                setDiamondLevel(item, 0);
                setRedstoneLevel(item, 0);
                setLapisLevel(item, 0);
                setModifiers(item, 0);

            }
        }
    }

    //public void onItemTooltip(ItemTooltipEvent event){

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List<String> tooltip, boolean advanced)
    {
        if(player.getEntityWorld().isRemote) {

            if(item.hasTagCompound()) {

                tooltip.add(ChatFormatting.GRAY + "Upgrades Left: " + (3 - getModifiers(item)) );
                if (getEmerald(item) == true) {
                    tooltip.add(ChatFormatting.DARK_GREEN + "Emerald");
                }
                if (getDiamondLevel(item) > 0) {
                    tooltip.add(ChatFormatting.AQUA + "Diamond Level: " + getDiamondLevel(item));
                }
                if (getRedstoneLevel(item) > 0) {
                    tooltip.add(ChatFormatting.RED + "Redstone Level: " + getRedstoneLevel(item) );
                }
                if (getLapisLevel(item) > 0) {
                    tooltip.add(ChatFormatting.BLUE + "Lapis Level: " + getLapisLevel(item) );
                }
                tooltip.add(ChatFormatting.LIGHT_PURPLE + "Damage: " + item.getItemDamage() );
            }
        }


    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
    {
        if(!player.world.isRemote){
            World world = player.getEntityWorld();

            if(itemstack.getItem() instanceof CustomHoe){
                if( getEmerald(itemstack)){
                    itemstack.addEnchantment(Enchantment.getEnchantmentByID(33), 1);
                }
                /*if( getDiamondLevel(itemstack) > 0 ){
                    itemstack.addEnchantment(Enchantment.getEnchantmentByID(34), getDiamondLevel(itemstack));
                    itemstack.getItem().setHarvestLevel("pickaxe", 3);
                }*/
                /*if( getRedstoneLevel(itemstack) > 0 ){
                    itemstack.addEnchantment(Enchantment.getEnchantmentByID(32), getRedstoneLevel(itemstack));
                    //System.out.println(itemstack.getEnchantmentTagList());
                }*/
                if ( getLapisLevel(itemstack) > 0) {
                    itemstack.addEnchantment(Enchantment.getEnchantmentByID(35), getLapisLevel(itemstack));
                }
            }
        }

        return false;
    }

    @Override
    protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            if(stack.getMaxDamage() - stack.getItemDamage() >1 ) {
                stack.damageItem(1, player);
            } else {
                ItemStack dropStack = new ItemStack(drop, 1);
                dropStack.setItemDamage(stack.getItemDamage());
                dropStack.setTagCompound(new NBTTagCompound());
                NBTTagCompound copyNBT;
                copyNBT = stack.getSubCompound("tags").copy();
                dropStack.setTagCompound(copyNBT);
                PlayerHelper.spawnItemOnPlayer(worldIn, player, dropStack);
                player.renderBrokenItemStack(stack);
                stack.shrink(1);
            }
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if(stack.getMaxDamage() - stack.getItemDamage() >1 ) {
            stack.damageItem(1, attacker);
            return true;
        } else {
            ItemStack dropStack = new ItemStack(drop, 1);
            dropStack.setItemDamage(stack.getItemDamage());
            dropStack.setTagCompound(new NBTTagCompound());
            NBTTagCompound copyNBT;
            copyNBT = stack.getSubCompound("tags").copy();
            dropStack.setTagCompound(copyNBT);

            EntityPlayer player = (EntityPlayer) attacker;
            World world = attacker.getEntityWorld();
            if(!world.isRemote) {
                PlayerHelper.spawnItemOnPlayer(world, player, dropStack);
                attacker.renderBrokenItemStack(stack);
                stack.shrink(1);
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!world.isRemote && (double)state.getBlockHardness(world, pos) != 0.0D)
        {
            if(stack.getMaxDamage() - stack.getItemDamage() >1 ) {
                stack.getTagCompound().removeTag("ench");
                if(getDiamondLevel(stack) > 0) {
                    if(ThreadLocalRandom.current().nextInt(0, getDiamondLevel(stack)) == 0) {
                        stack.damageItem(1, entityLiving);
                    }
                } else stack.damageItem(1, entityLiving);
            } else {
                ItemStack dropStack = new ItemStack(drop, 1, stack.getItemDamage());
                dropStack.setTagCompound(new NBTTagCompound());
                NBTTagCompound copyNBT;
                copyNBT = stack.getSubCompound("tags").copy();
                dropStack.setTagCompound(copyNBT);
                EntityPlayer player = (EntityPlayer) entityLiving;
                PlayerHelper.spawnItemOnPlayer(world, player, dropStack);
                entityLiving.renderBrokenItemStack(stack);
                stack.shrink(1);
            }
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return false;
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
